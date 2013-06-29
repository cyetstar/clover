package org.cyetstar.clover.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.cyetstar.clover.entity.Celebrity;
import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.entity.MovieAka;
import org.cyetstar.clover.entity.MovieCountry;
import org.cyetstar.clover.entity.MovieCredit;
import org.cyetstar.clover.entity.MovieFile;
import org.cyetstar.clover.entity.MovieGenre;
import org.cyetstar.clover.entity.MovieLanguage;
import org.cyetstar.clover.service.DictionaryService;
import org.cyetstar.clover.service.MovieFileService;
import org.cyetstar.clover.service.MovieService;
import org.cyetstar.clover.service.PosterService;
import org.cyetstar.clover.web.JSONResponse;
import org.cyetstar.core.domain.Clause;
import org.cyetstar.core.domain.Fetch;
import org.cyetstar.core.spring.SpecificationCreater;
import org.cyetstar.utils.Reflections;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Controller
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	MovieService movieService;

	@Autowired
	MovieFileService fileService;

	@Autowired
	PosterService posterService;

	@Autowired
	DictionaryService dictionaryService;

	@RequestMapping
	public String list(@RequestParam(required = false) String keywords,
			@RequestParam(value = "no", defaultValue = "1") int pageNum,
			@RequestParam(value = "sz", defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "createdAt:desc") String sort, Model model) {
		Page<Movie> page = movieService.findMovie(keywords, pageNum - 1, pageSize, sort);
		model.addAttribute("page", page);
		Map<String, String> map = Maps.newHashMap();
		map.put("keywords", keywords);
		model.addAttribute("params", map);
		return "movies/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		Specification<Movie> spec = SpecificationCreater.searchByWith(Clause.instance().eq("id", id), new Fetch("genres"),
				new Fetch("akas"), new Fetch("countries"), new Fetch("languages"), new Fetch("directors.celebrity"), new Fetch(
						"casts.celebrity"), new Fetch("writers.celebrity"));
		Movie movie = movieService.findMovie(spec);
		List<MovieFile> files = fileService.findFilesByMovieId(id);
		model.addAttribute("movie", movie);
		model.addAttribute("files", files);
		model.addAttribute("smallAccessPath", posterService.getSmallAccessPath());
		return "movies/show";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		getAllDict(model);
		return "movies/add";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Movie movie) {
		movieService.saveMovie(movie);
		return "redirect:movies/add";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		Specification<Movie> spec = SpecificationCreater.searchByWith(Clause.instance().eq("id", id), new Fetch("genres"),
				new Fetch("akas"), new Fetch("countries"), new Fetch("languages"), new Fetch("directors.celebrity"), new Fetch(
						"casts.celebrity"), new Fetch("writers.celebrity"));
		Movie movie = movieService.findMovie(spec);
		model.addAttribute("movie", movie);
		getAllDict(model);
		return "movies/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute Movie movie, HttpServletRequest request) {
		movie.setAkas(requetsAkaSet(request, movie));
		movie.setDirectors(requestCreditSet(request, MovieCredit.DIRECTOR, movie));
		movie.setWriters(requestCreditSet(request, MovieCredit.WRITER, movie));
		movie.setCasts(requestCreditSet(request, MovieCredit.CAST, movie));
		movie.setGenres(requestDictSet(request, MovieGenre.class));
		movie.setLanguages(requestDictSet(request, MovieLanguage.class));
		movie.setCountries(requestDictSet(request, MovieCountry.class));
		movie.setUpdatedAt(DateTime.now());
		movieService.saveMovie(movie);
		return "redirect:/movies/edit/" + movie.getId();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
		String rootPath = session.getServletContext().getRealPath("/");
		movieService.deleteMovie(id);
		posterService.remove(id, rootPath);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/movies";
	}

	@RequestMapping(value = "/uploadPoster/{id}", method = RequestMethod.GET)
	public String uploadPoster(@PathVariable Long id, Model model) {
		Movie movie = movieService.findMovie(id);
		model.addAttribute("movie", movie);
		return "movies/uploadPoster";
	}

	@RequestMapping(value = "/uploadPoster/{id}", method = RequestMethod.POST)
	public String uploadPoster(@PathVariable Long id, @RequestParam(value = "file") MultipartFile file, Model model)
			throws IOException {
		if (!file.isEmpty()) {
			String poster = posterService.upload(id, file);
			Movie movie = movieService.findMovie(id);
			movie.setPoster(poster);
			model.addAttribute("originAccessPath", posterService.getOriginAccessPath());
			model.addAttribute("smallAccessPath", posterService.getOriginAccessPath());
			model.addAttribute("movie", movie);
		}
		return "movies/uploadPoster";
	}

	@RequestMapping(value = "/cropPoster/{id}")
	public String cropPoster(@PathVariable Long id, @RequestParam double width, @RequestParam double height,
			@RequestParam double x, @RequestParam double y, Model model) throws IOException {
		String poster = posterService.crop(id, width, height, x, y);
		Movie movie = movieService.updateMoviePoster(id, poster);
		String random = String.valueOf(DateTime.now().getMillis());
		movie.setPoster(poster + "?" + random);
		model.addAttribute("originAccessPath", posterService.getOriginAccessPath());
		model.addAttribute("smallAccessPath", posterService.getSmallAccessPath());
		model.addAttribute("movie", movie);
		model.addAttribute("success", true);
		return "movies/uploadPoster";
	}

	@RequestMapping(value = "/fetch", method = RequestMethod.POST)
	public String fetch(@RequestParam String doubanId, RedirectAttributes redirectAttributes) {
		Movie movie = movieService.fetchMovie(doubanId);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/movies/edit/" + movie.getId();
	}

	@RequestMapping(value = "/updateRating", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse updateRating(@RequestParam Long id, @RequestParam String doubanId) {
		JSONResponse response = new JSONResponse();
		Movie movie = movieService.updateMovieRating(id, doubanId);
		response.setSuccess(true);
		return response;
	}

	@ModelAttribute
	protected void getMovie(@RequestParam(required = false) Long id, Model model) {
		if (id != null) {
			Movie movie = movieService.findMovie(id);
			model.addAttribute("movie", movie);
		}
	}

	private void getAllDict(Model model) {
		List<MovieGenre> genres = dictionaryService.findAllMovieGenre();
		List<MovieCountry> countries = dictionaryService.findAllMovieCountry();
		List<MovieLanguage> languages = dictionaryService.findAllMovieLanguage();
		model.addAttribute("genres", genres);
		model.addAttribute("countries", countries);
		model.addAttribute("languages", languages);
	}

	private Set<MovieAka> requetsAkaSet(HttpServletRequest request, Movie movie) {
		String[] akaIdArr = request.getParameterValues("akaId");
		String[] akaTitleArr = request.getParameterValues("akaTitle");
		Set<MovieAka> set = Sets.newLinkedHashSet();
		for (int i = 0; i < akaIdArr.length; i++) {
			MovieAka aka = new MovieAka(akaTitleArr[i]);
			try {
				aka.setId(Long.parseLong(akaIdArr[i]));
			} catch (NumberFormatException e) {
			}
			aka.setMovie(movie);
			set.add(aka);
		}
		return set;
	}

	private Set<MovieCredit> requestCreditSet(HttpServletRequest request, String role, Movie movie) {
		String[] IdArr = request.getParameterValues(role + "Id");
		String[] celebrityIdArr = request.getParameterValues(role + "CelebrityId");
		Set<MovieCredit> set = Sets.newLinkedHashSet();
		for (int i = 0; i < IdArr.length; i++) {
			MovieCredit credit = new MovieCredit();
			try {
				try {
					credit.setId(Long.valueOf(IdArr[i]));
				} catch (NumberFormatException e) {
				}
				credit.setCelebrity(new Celebrity(Long.parseLong(celebrityIdArr[i])));
				credit.setRole(role);
				credit.setMovie(movie);
				set.add(credit);
			} catch (NumberFormatException e) {
			}
		}
		return set;
	}

	private <T> Set<T> requestDictSet(HttpServletRequest request, Class<T> clazz) {
		String type = StringUtils.uncapitalize(ClassUtils.getSimpleName(clazz));
		String[] IdArr = request.getParameterValues(type + "Id");
		if (IdArr == null) {
			return null;
		}
		Set<T> set = Sets.newLinkedHashSet();
		for (int i = 0; i < IdArr.length; i++) {
			Long id = Long.parseLong(IdArr[i]);
			T entity = Reflections.getInstance(clazz);
			Reflections.invokeSetter(entity, "id", id);
			set.add(entity);
		}
		return set;
	}

}
