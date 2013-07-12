package org.cyetstar.clover.web.controller;

import org.cyetstar.clover.entity.MovieFile;
import org.cyetstar.clover.service.BaseService;
import org.cyetstar.clover.service.MovieFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/movieFiles")
public class MovieFileController extends CurdController<MovieFile, Long> {

	@Autowired
	MovieFileService fileService;

	@Override
	protected BaseService<MovieFile, Long> getService() {
		return fileService;
	}

	@Override
	protected void beforeEntityAdd(WebRequest webRequest, Model model) {
		super.beforeEntityAdd(webRequest, model);
		model.addAttribute("movieId", webRequest.getParameter("movieId"));
	}

}
