package org.cyetstar.clover.web.controller;

import org.cyetstar.clover.entity.Movie;
import org.cyetstar.clover.entity.MovieFile;
import org.cyetstar.clover.service.MovieFileService;
import org.cyetstar.clover.web.JSONResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/movieFiles")
public class MovieFileController {

	@Autowired
	MovieFileService fileService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@RequestParam Long movieId, Model model) {
		model.addAttribute("movieId", movieId);
		return "movieFiles/add";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse create(@RequestParam Long movieId, @RequestParam String filename) {
		JSONResponse response = new JSONResponse();
		try {
			MovieFile file = new MovieFile();
			file.setMovie(new Movie(movieId));
			file.setFilename(filename);
			fileService.createFile(file);
			response.setSuccess(true);
			response.setData(file);
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		MovieFile file = fileService.findFile(id);
		model.addAttribute("file", file);
		return "movieFiles/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse update(@ModelAttribute MovieFile file) {
		JSONResponse response = new JSONResponse();
		try {
			file = fileService.updateFile(file);
			response.setSuccess(true);
			response.setData(file);
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse delete(@PathVariable Long id) {
		JSONResponse response = new JSONResponse();
		try {
			fileService.deleteFile(id);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@ModelAttribute
	protected void getMovieFile(@RequestParam(required = false) Long id, Model model) {
		if (id != null) {
			MovieFile file = fileService.findFile(id);
			model.addAttribute("movieFile", file);
		}
	}

}
