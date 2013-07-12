package org.cyetstar.clover.web.controller;

import org.cyetstar.clover.entity.MovieSet;
import org.cyetstar.clover.service.BaseService;
import org.cyetstar.clover.service.MovieSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movieSets")
public class MovieSetController extends CurdController<MovieSet, Long> {

	@Autowired
	MovieSetService movieSetService;

	@Override
	protected BaseService<MovieSet, Long> getService() {
		return movieSetService;
	}

}
