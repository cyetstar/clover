package org.cyetstar.clover.web.controller;

import org.cyetstar.clover.entity.MovieSet;
import org.cyetstar.clover.service.MovieSetService;
import org.cyetstar.clover.web.JSONResponse;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/movieSets")
public class MovieSetController {

	@Autowired
	MovieSetService movieSetService;

	@RequestMapping("/addIn")
	public String addIn(@RequestParam Long movieId, @RequestParam(defaultValue = "0") Integer pageNum, Model model) {
		Page<MovieSet> page = movieSetService.findUnAddInSets(movieId, 1, 5);
		model.addAttribute("movieId", movieId);
		model.addAttribute("page", page);
		return "movieSets/addIn";
	}

	@RequestMapping("/add")
	public String add(@RequestParam Long movieId, Model model) {
		model.addAttribute("movieId", movieId);
		return "movieSets/add";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse create(MovieSet movieSet, Model model) {
		JSONResponse response = new JSONResponse();
		try {
			movieSet.setCreatedAt(DateTime.now());
			movieSetService.saveMovieSet(movieSet);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
		}
		return response;
	}

	@ModelAttribute
	protected void getMovieSet(@RequestParam(required = false) Long id, Model model) {
		if (id != null) {
		}
	}

}
