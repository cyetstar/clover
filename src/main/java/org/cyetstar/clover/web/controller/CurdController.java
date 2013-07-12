package org.cyetstar.clover.web.controller;

import java.io.Serializable;

import org.cyetstar.clover.service.Service;
import org.cyetstar.core.spring.DataDomainHelper;
import org.cyetstar.utils.Reflections;
import org.joda.time.DateTime;
import org.springframework.core.Conventions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class CurdController<T, ID extends Serializable> extends BaseController {

	protected abstract Service<T, ID> getService();

	@RequestMapping
	public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(required = false) String sort, WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		return doList(page, size, sort, webRequest, model, redirectAttr);
	}

	@RequestMapping("/{id}")
	public String view(@PathVariable ID id, WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		return doView(id, webRequest, model, redirectAttr);
	}

	@RequestMapping("/add")
	public String add(WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		return doAdd(webRequest, model, redirectAttr);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(T entity, WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		return doCreate(entity, webRequest, model, redirectAttr);
	}

	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable ID id, WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		return doEdit(id, webRequest, model, redirectAttr);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute T entity, WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		return doUpdate(entity, webRequest, model, redirectAttr);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable ID id, WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		return doDelete(id, webRequest, model, redirectAttr);
	}

	protected String doList(int page, int size, String sort, WebRequest webRequest, Model model,
			RedirectAttributes redirectAttr) {
		Pageable pageable = new PageRequest(page, size, DataDomainHelper.parseSort(sort));
		Page<T> pages = getService().findAll(null, pageable);
		model.addAttribute("status", true);
		model.addAttribute("pages", pages);
		return reponseViewPath("list");
	}

	protected String doView(ID id, WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		T entity = getService().findOne(id);
		afterEntityView(entity, webRequest, model);
		model.addAttribute("status", true);
		model.addAttribute(Conventions.getVariableName(entity), entity);
		return reponseViewPath("view");
	}

	protected void afterEntityView(T entity, WebRequest webRequest, Model model) {

	}

	protected String doAdd(WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		beforeEntityAdd(webRequest, model);
		model.addAttribute("status", true);
		return reponseViewPath("add");
	}

	protected void beforeEntityAdd(WebRequest webRequest, Model model) {

	}

	protected String doCreate(T entity, WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		beforeEntityCreate(entity, webRequest, model);
		entity = getService().save(entity);
		afterEntityCreate(entity, webRequest, model);
		model.addAttribute("status", true);
		model.addAttribute(Conventions.getVariableName(entity), entity);
		return reponseViewPath("edit");
	}

	protected void beforeEntityCreate(T entity, WebRequest webRequest, Model model) {
		Reflections.invokeSetter(entity, "createdAt", DateTime.now());
	}

	protected void afterEntityCreate(T entity, WebRequest webRequest, Model model) {

	}

	protected String doEdit(ID id, WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		T entity = getService().findOne(id);
		beforeEntityEdit(entity, webRequest, model);
		model.addAttribute("status", true);
		model.addAttribute(Conventions.getVariableName(entity), entity);
		return reponseViewPath("edit");
	}

	protected void beforeEntityEdit(T entity, WebRequest webRequest, Model model) {

	}

	protected String doUpdate(T entity, WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		beforeEntityUpdate(entity, webRequest, model);
		entity = getService().save(entity);
		afterEntityUpdate(entity, webRequest, model);
		model.addAttribute("status", true);
		model.addAttribute(Conventions.getVariableName(entity), entity);
		return reponseViewPath("edit");
	}

	protected void beforeEntityUpdate(T entity, WebRequest webRequest, Model model) {
		Reflections.invokeSetter(entity, "updatedAt", DateTime.now());
	}

	protected void afterEntityUpdate(T entity, WebRequest webRequest, Model model) {

	}

	protected String doDelete(ID id, WebRequest webRequest, Model model, RedirectAttributes redirectAttr) {
		getService().delete(id);
		model.addAttribute("status", true);
		return reponseViewPath("delete");
	}

	@ModelAttribute
	protected void getEntity(@RequestParam(required = false) ID id, Model model) {
		if (id != null) {
			T entity = getService().findOne(id);
			model.addAttribute(Conventions.getVariableName(entity), entity);
		}
	}

}
