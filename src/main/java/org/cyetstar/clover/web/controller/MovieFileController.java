package org.cyetstar.clover.web.controller;

import org.cyetstar.clover.service.MovieService;
import org.cyetstar.clover.service.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movies")
public class MovieFileController {

	@Autowired
	MovieService movieService;

	@Autowired
	PosterService posterService;

	@RequestMapping(value = "/addFile", method = RequestMethod.GET)
	public String add(@RequestParam String movieId, Model model) {
		return "movies/addFile";
	}

}
