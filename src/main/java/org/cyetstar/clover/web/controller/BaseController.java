package org.cyetstar.clover.web.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class BaseController {

	protected void message(RedirectAttributes redirectAttributes, String message) {
		redirectAttributes.addFlashAttribute("message", message);
	}

}
