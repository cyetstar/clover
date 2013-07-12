package org.cyetstar.clover.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class BaseController {

	public String reponseViewPath(String childPath) {
		RequestMapping requestMapping = this.getClass().getAnnotation(RequestMapping.class);
		String[] values = requestMapping.value();
		StringBuffer rootPath = new StringBuffer();
		if (values != null && values.length == 1) {
			rootPath.append(values[0]);
		}
		return rootPath.append("/").append(childPath).toString();
	}
}
