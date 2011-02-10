// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.unlv.cs.rebelhotel.web;

import edu.unlv.cs.rebelhotel.domain.Term;
import edu.unlv.cs.rebelhotel.domain.enums.Semester;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Arrays;
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

privileged aspect TermController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String TermController.create(@Valid Term term, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("term", term);
            return "terms/create";
        }
        term.persist();
        return "redirect:/terms/" + encodeUrlPathSegment(term.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String TermController.createForm(Model uiModel) {
        uiModel.addAttribute("term", new Term());
        return "terms/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String TermController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("term", Term.findTerm(id));
        uiModel.addAttribute("itemId", id);
        return "terms/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String TermController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("terms", Term.findTermEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Term.countTerms() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("terms", Term.findAllTerms());
        }
        return "terms/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String TermController.update(@Valid Term term, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("term", term);
            return "terms/update";
        }
        term.merge();
        return "redirect:/terms/" + encodeUrlPathSegment(term.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String TermController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("term", Term.findTerm(id));
        return "terms/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String TermController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Term.findTerm(id).remove();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/terms";
    }
    
    @ModelAttribute("terms")
    public Collection<Term> TermController.populateTerms() {
        return Term.findAllTerms();
    }
    
    @ModelAttribute("semesters")
    public Collection<Semester> TermController.populateSemesters() {
        return Arrays.asList(Semester.class.getEnumConstants());
    }
    
    String TermController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
