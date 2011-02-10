// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.unlv.cs.rebelhotel.web;

import edu.unlv.cs.rebelhotel.domain.Student;
import edu.unlv.cs.rebelhotel.domain.Term;
import edu.unlv.cs.rebelhotel.domain.WorkEffort;
import edu.unlv.cs.rebelhotel.domain.WorkRequirement;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect StudentController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String StudentController.create(@Valid Student student, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("student", student);
            return "students/create";
        }
        student.persist();
        return "redirect:/students/" + encodeUrlPathSegment(student.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String StudentController.createForm(Model uiModel) {
        uiModel.addAttribute("student", new Student());
        return "students/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String StudentController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("student", Student.findStudent(id));
        uiModel.addAttribute("itemId", id);
        return "students/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String StudentController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("students", Student.findStudentEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Student.countStudents() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("students", Student.findAllStudents());
        }
        return "students/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String StudentController.update(@Valid Student student, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("student", student);
            return "students/update";
        }
        student.merge();
        return "redirect:/students/" + encodeUrlPathSegment(student.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String StudentController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("student", Student.findStudent(id));
        return "students/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String StudentController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Student.findStudent(id).remove();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/students";
    }
    
    @RequestMapping(params = { "find=ByFirstNameLike", "form" }, method = RequestMethod.GET)
    public String StudentController.findStudentsByFirstNameLikeForm(Model uiModel) {
        return "students/findStudentsByFirstNameLike";
    }
    
    @RequestMapping(params = { "find=ByMajor2Equals", "form" }, method = RequestMethod.GET)
    public String StudentController.findStudentsByMajor2EqualsForm(Model uiModel) {
        return "students/findStudentsByMajor2Equals";
    }
    
    @RequestMapping(params = { "find=ByNSHEEquals", "form" }, method = RequestMethod.GET)
    public String StudentController.findStudentsByNSHEEqualsForm(Model uiModel) {
        return "students/findStudentsByNSHEEquals";
    }
    
    @RequestMapping(params = { "find=ByMajor1Equals", "form" }, method = RequestMethod.GET)
    public String StudentController.findStudentsByMajor1EqualsForm(Model uiModel) {
        return "students/findStudentsByMajor1Equals";
    }
    
    @RequestMapping(params = { "find=ByFirstNameEquals", "form" }, method = RequestMethod.GET)
    public String StudentController.findStudentsByFirstNameEqualsForm(Model uiModel) {
        return "students/findStudentsByFirstNameEquals";
    }
    
    @ModelAttribute("students")
    public Collection<Student> StudentController.populateStudents() {
        return Student.findAllStudents();
    }
    
    @ModelAttribute("terms")
    public Collection<Term> StudentController.populateTerms() {
        return Term.findAllTerms();
    }
    
    @ModelAttribute("workefforts")
    public Collection<WorkEffort> StudentController.populateWorkEfforts() {
        return WorkEffort.findAllWorkEfforts();
    }
    
    @ModelAttribute("workrequirements")
    public Collection<WorkRequirement> StudentController.populateWorkRequirements() {
        return WorkRequirement.findAllWorkRequirements();
    }
    
    String StudentController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
