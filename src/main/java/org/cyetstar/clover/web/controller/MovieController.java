package org.cyetstar.clover.web.controller;

import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	MovieService movieService;

	@RequestMapping
	public String list() {
		return "movies/list";
	}

	@RequestMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		return "movies/show";
	}

	@RequestMapping("/new")
	public String add() {
		return "movies/add";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Movie movie) {
		return "redirect:movies/add";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id) {
		return "movies/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Movie movie) {
		return "redirect:movies/edit";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable Long id) {
		return "redirect:movies/list";
	}
}
