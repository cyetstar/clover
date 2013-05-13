package org.cyetstar.clover.web.controller;

import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	MovieService movieService;

	@RequestMapping
	public String list(@RequestParam(required = false) String key, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "created_at_asc") String sort,
			Model model) {
		Page<Movie> list = movieService.findMovie(key, page, size, sort);
		model.addAttribute("list", list);
		return "movies/list";
	}

	@RequestMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		Movie movie = movieService.findMovie(id);
		model.addAttribute("movie", movie);
		return "movies/show";
	}

	@RequestMapping("/new")
	public String add() {
		return "movies/add";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Movie movie) {
		movieService.saveMovie(movie);
		return "redirect:movies/add";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Movie movie = movieService.findMovie(id);
		model.addAttribute("movie", movie);
		return "movies/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute Movie movie) {
		movieService.saveMovie(movie);
		return "redirect:movies/edit";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable Long id) {
		movieService.deleteMovie(id);
		return "redirect:movies/list";
	}

	@ModelAttribute
	private void getMovie(@RequestParam(required = false) Long id, Model model) {
		if (id != null) {
			Movie movie = movieService.findMovie(id);
			model.addAttribute("movie", movie);
		}
	}
}
