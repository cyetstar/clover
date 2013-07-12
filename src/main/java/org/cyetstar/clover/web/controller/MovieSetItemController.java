package org.cyetstar.clover.web.controller;

import java.util.List;

import org.cyetstar.clover.entity.MovieSet;
import org.cyetstar.clover.entity.MovieSetItem;
import org.cyetstar.clover.service.BaseService;
import org.cyetstar.clover.service.MovieSetItemService;
import org.cyetstar.clover.service.MovieSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/movieSetItems")
public class MovieSetItemController extends CurdController<MovieSetItem, Long> {

	@Autowired
	MovieSetItemService movieSetItemService;

	@Autowired
	MovieSetService movieSetService;

	@Override
	protected BaseService<MovieSetItem, Long> getService() {
		return movieSetItemService;
	}

	@Override
	protected void beforeEntityAdd(WebRequest webRequest, Model model) {
		super.beforeEntityAdd(webRequest, model);
		Long movieId = Long.parseLong(webRequest.getParameter("movieId"));
		List<MovieSet> list = movieSetService.findUnAddInSets(movieId);
		model.addAttribute("movieId", movieId);
		model.addAttribute("list", list);
	}

	@Override
	protected void beforeEntityCreate(MovieSetItem entity, WebRequest webRequest, Model model) {
		super.beforeEntityCreate(entity, webRequest, model);
		entity.setIdx((int) (entity.getCreatedAt().getMillis() / 1000));
	}



}
