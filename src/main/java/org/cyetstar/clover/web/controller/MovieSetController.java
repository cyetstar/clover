package org.cyetstar.clover.web.controller;

import java.util.List;

import org.cyetstar.clover.entity.MovieSet;
import org.cyetstar.clover.entity.MovieSetItem;
import org.cyetstar.clover.service.MovieSetService;
import org.cyetstar.clover.web.JSONResponse;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/addIn", method = RequestMethod.GET)
	public String addIn(@RequestParam Long movieId, Model model) {
		List<MovieSet> list = movieSetService.findUnAddInSets(movieId);
		model.addAttribute("movieId", movieId);
		model.addAttribute("list", list);
		return "movieSets/addIn";
	}

	@RequestMapping(value = "/addIn", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse addIn(MovieSetItem movieSetItem, Model model) {
		JSONResponse response = new JSONResponse();
		try {
			movieSetItem.setCreatedAt(DateTime.now());
			movieSetItem.setIdx((int) (movieSetItem.getCreatedAt().getMillis() / 1000));
			movieSetService.saveMovieSetItem(movieSetItem);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
		}
		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse create(MovieSet movieSet, Model model) {
		JSONResponse response = new JSONResponse();
		try {
			movieSet.setCreatedAt(DateTime.now());
			movieSetService.saveMovieSet(movieSet);
			response.setSuccess(true);
			response.setData(movieSet);
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
