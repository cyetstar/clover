package org.cyetstar.clover.web.controller;

import java.util.List;

import org.cyetstar.clover.entity.Celebrity;
import org.cyetstar.clover.service.BaseService;
import org.cyetstar.clover.service.CelebrityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/celebrities")
public class CelebrityController extends CurdController<Celebrity, Long> {

	@Autowired
	CelebrityService celebrityService;

	@Override
	protected BaseService<Celebrity, Long> getService() {
		return celebrityService;
	}

	@RequestMapping(value = "/autocomplete")
	@ResponseBody
	public List<Celebrity> autocomplete(@RequestParam String name,
			@RequestParam(value = "sz", defaultValue = "10") int pageSize) throws JsonProcessingException {
		Page<Celebrity> page = celebrityService.findCelebrity(name, 0, pageSize);
		return page.getContent();
	}

}
