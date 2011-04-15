package edu.unlv.cs.rebelhotel.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import edu.unlv.cs.rebelhotel.domain.Major;
import edu.unlv.cs.rebelhotel.domain.Student;
import edu.unlv.cs.rebelhotel.domain.WorkEffort;
import edu.unlv.cs.rebelhotel.domain.WorkRequirement;
import edu.unlv.cs.rebelhotel.form.FormWorkEffortForStudent;
import edu.unlv.cs.rebelhotel.form.FormWorkEffortQuery;
import edu.unlv.cs.rebelhotel.form.QuerySortOptions;
import edu.unlv.cs.rebelhotel.service.UserInformation;
import edu.unlv.cs.rebelhotel.service.WorkEffortQueryService;
import edu.unlv.cs.rebelhotel.validators.WorkEffortForStudentValidator;
import edu.unlv.cs.rebelhotel.validators.WorkEffortQueryValidator;
import edu.unlv.cs.rebelhotel.validators.WorkEffortValidator;

import org.hibernate.classic.Session;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("workeffortslist")
@RooWebScaffold(path = "workefforts", formBackingObject = WorkEffort.class, exposeFinders = false)
@RequestMapping("/workefforts")
@Controller
public class WorkEffortController {

	@Autowired
	WorkEffortQueryService workeffortqueryservice;

	@Autowired
	WorkEffortQueryValidator workeffortqueryvalidator;

	void setWorkEffortQueryService(WorkEffortQueryService workeffortqueryservice) {
		this.workeffortqueryservice = workeffortqueryservice;
	}

	void setWorkEffortQueryValidator(
			WorkEffortQueryValidator workeffortqueryvalidator) {
		this.workeffortqueryvalidator = workeffortqueryvalidator;
	}

	@Autowired
	private UserInformation userInformation;

	@Autowired
	private WorkEffortValidator workEffortValidator;

	@Autowired
	private WorkEffortForStudentValidator workEffortForStudentValidator;

	public void setWorkEffortValidator(WorkEffortValidator workEffortValidator) {
		this.workEffortValidator = workEffortValidator;
	}

	public void setWorkEffortForStudentValidator(
			WorkEffortForStudentValidator workEffortForStudentValidator) {
		this.workEffortForStudentValidator = workEffortForStudentValidator;
	}

	@RequestMapping(params = "query", method = RequestMethod.POST)
	public String queryList(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@Valid FormWorkEffortQuery form, BindingResult result, Model model,
			HttpServletRequest request) {
		workeffortqueryvalidator.validate(form, result);
		if (result.hasErrors()) {
			model.addAttribute("formworkeffortquery", form);
			addDateTimeFormatPatterns(model);
			return "workefforts/findWorkEfforts";
		}

		List<WorkEffort> workEffortsList = workeffortqueryservice
				.queryWorkEfforts(form);

		model.addAttribute("workeffortslist", workEffortsList);
		model.addAttribute("page", (page == null) ? "1" : page.toString());
		model.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/workefforts?query&page="
				+ ((page == null) ? "1" : page.toString()) + "&size="
				+ ((size == null) ? "10" : size.toString());
	}

	@RequestMapping(params = "query", method = RequestMethod.GET)
	public String queryList(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@ModelAttribute("workeffortslist") List<WorkEffort> workEffortsList,
			BindingResult result, Model model, HttpServletRequest request) {

		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			int pageNo = page == null ? 1 : page.intValue();
			int from = sizeNo * pageNo < workEffortsList.size() ? sizeNo
					* pageNo : workEffortsList.size();
			int to = (pageNo - 1) * sizeNo;
			model.addAttribute("workefforts", workEffortsList.subList(to, from));
			float nrOfPages = (float) workEffortsList.size() / sizeNo;
			model.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			model.addAttribute("workefforts", workEffortsList);
		}

		return "workefforts/queryList";
	}

	@RequestMapping(params = { "query", "form" }, method = RequestMethod.GET)
	public String query(Model model) {
		FormWorkEffortQuery fweq = new FormWorkEffortQuery();
		model.addAttribute("formworkeffortquery", fweq);
		addDateTimeFormatPatterns(model);
		return "workefforts/findWorkEfforts";
	}

	// NOTE : the params string should not be equivalent to any of the fields in
	// the form
	// otherwise the validator (?) will assume the params value is set to null
	// (?) ... very annoying bug
	@RequestMapping(value = "/{sid}", params = "forstudent", method = RequestMethod.POST)
	public String createStudent(@PathVariable("sid") Long sid,
			FormWorkEffortForStudent formWorkEffortForStudent,
			BindingResult result, Model model, HttpServletRequest request) {
		workEffortForStudentValidator
				.validate(formWorkEffortForStudent, result);
		if (result.hasErrors()) {
			model.addAttribute("formWorkEffortForStudent",
					formWorkEffortForStudent);
			addDateTimeFormatPatterns(model);
			Student student = Student.findStudent(sid);
			Set<Major> majors = student.getMajors();
			model.addAttribute("studentmajors", majors);
			model.addAttribute("sid", sid);
			return "workefforts/createFromStudent";
		}
		WorkEffort workEffort = formWorkEffortForStudent.getWorkEffort();
		Set<Major> majors = formWorkEffortForStudent.getMajors();
		Set<WorkRequirement> workRequirements = new HashSet();
		for (Major major : majors) {
	//		Set<WorkRequirement> lwrs = major.getWorkRequirements();
	//		for (WorkRequirement workRequirement : lwrs) {
	//			workRequirements.add(workRequirement);
	//		}
		}
		workEffort.setWorkRequirements(workRequirements);
		workEffort.persist();

		Student student = workEffort.getStudent();
		student.addWorkEffort(workEffort);
		student.merge();
		return "redirect:/workefforts/"
				+ encodeUrlPathSegment(formWorkEffortForStudent.getWorkEffort()
						.getId().toString(), request);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(WorkEffort workEffort, BindingResult result,
			Model model, HttpServletRequest request) {
		workEffortValidator.validate(workEffort, result);
		if (result.hasErrors()) {
			model.addAttribute("workEffort", workEffort);
			addDateTimeFormatPatterns(model);
			return "workefforts/create";
		}
		workEffort.persist();
		workEffort.getStudent().addWorkEffort(workEffort);
		workEffort.getStudent().merge();
		return "redirect:/workefforts/"
				+ encodeUrlPathSegment(workEffort.getId().toString(), request);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(WorkEffort workEffort, BindingResult result,
			Model model, HttpServletRequest request) {
		workEffortValidator.validate(workEffort, result);
		if (result.hasErrors()) {
			model.addAttribute("workEffort", workEffort);
			return "workefforts/update";
		}
		workEffort.merge();
		return "redirect:/workefforts/"
				+ encodeUrlPathSegment(workEffort.getId().toString(), request);
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("workEffort", new WorkEffort());
		addDateTimeFormatPatterns(model);
		List dependencies = new ArrayList();
		if (Student.countStudents() == 0) {
			dependencies.add(new String[] { "student", "students" });
		}
		model.addAttribute("dependencies", dependencies);
		return "workefforts/create";
	}

	@RequestMapping(value = "/{sid}", params = "forstudent", method = RequestMethod.GET)
	public String createStudentForm(@PathVariable("sid") Long sid, Model model) {
		model.addAttribute("formWorkEffortForStudent",
				new FormWorkEffortForStudent());
		addDateTimeFormatPatterns(model);
		List dependencies = new ArrayList();
		if (Student.countStudents() == 0) {
			dependencies.add(new String[] { "student", "students" });
		}
		Student student = Student.findStudent(sid);
		Set<Major> majors = student.getMajors();
		model.addAttribute("studentmajors", majors);
		model.addAttribute("dependencies", dependencies);
		model.addAttribute("sid", sid);
		// TODO check if one is able to place the value of the student here
		// without relying on the hidden form element
		// RESULT apparently it cannot be done
		return "workefforts/createFromStudent";
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("workEffort", WorkEffort.findWorkEffort(id));
		addDateTimeFormatPatterns(model);
		return "workefforts/update";
	}

	/*
	 * @RequestMapping(value= "/{id}", params = "forstudent" ,
	 * method=RequestMethod.GET) public String
	 * randomValidation(@PathVariable("id") Long id, Model model) {
	 * 
	 * return ""; }
	 */

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(params = "mywork", method = RequestMethod.GET)
	public String listPersonalWork(Model model) {
		model.addAttribute("str", "A list to contain your completed jobs");
		Student student = userInformation.getStudent();
		List<WorkEffort> workEfforts = WorkEffort
				.findWorkEffortsByStudentEquals(student).getResultList();
		model.addAttribute("workefforts", workEfforts);
		return "workefforts/mywork";
	}

	void addDateTimeFormatPatterns(Model model) {
		model.addAttribute(
				"workEffortDuration_startdate_date_format",
				DateTimeFormat.patternForStyle("S-",
						LocaleContextHolder.getLocale()));
		model.addAttribute(
				"workEffortDuration_enddate_date_format",
				DateTimeFormat.patternForStyle("S-",
						LocaleContextHolder.getLocale()));
	}

	@ModelAttribute("sortOptions")
	public Collection<QuerySortOptions> populateQuerySortOptions() {
		return Arrays.asList(QuerySortOptions.class.getEnumConstants());
	}
}