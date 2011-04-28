package edu.unlv.cs.rebelhotel.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import edu.unlv.cs.rebelhotel.domain.Student;
import edu.unlv.cs.rebelhotel.domain.UserAccount;
import edu.unlv.cs.rebelhotel.email.UserEmailService;
import edu.unlv.cs.rebelhotel.service.RebelUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RooWebScaffold(path = "useraccounts", formBackingObject = UserAccount.class)
@RequestMapping("/useraccounts")
@Controller
public class UserAccountController {
	
	@Autowired
	UserEmailService userEmailService;
	
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@RequestMapping(method = RequestMethod.POST)
    public String create(@Valid UserAccount userAccount, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("userAccount", userAccount);
            return "useraccounts/create";
        }
        userAccount.persist();
        return "redirect:/useraccounts/" + encodeUrlPathSegment(userAccount.getId().toString(), request);
    }
    
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        return "useraccounts/create";
    }
    
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid UserAccount userAccount, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("userAccount", userAccount);
            return "useraccounts/update";
        }
        userAccount.merge();
        return "redirect:/useraccounts/" + encodeUrlPathSegment(userAccount.getId().toString(), request);
    }
    
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userAccount", UserAccount.findUserAccount(id));
        return "useraccounts/update";
    }
    
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        UserAccount.findUserAccount(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/useraccounts?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
	
	
	@RequestMapping( "/forgotPassword")
	public String createforgotPasswordForm(Model model){
		
		return "useraccounts/forgotPassword";
	}
	
	
	@RequestMapping( value="/createNewPassword", method = RequestMethod.GET )
	public String createNewPassword(@RequestParam("userId") String userId, Model model){
		
		try{
		UserAccount userAccount = UserAccount.findUserAccountsByUserId(userId).getSingleResult();
		String password = userAccount.generatePassword();
	    userEmailService.sendNewPassword(userAccount, password);
	    Student student = Student.findStudentsByUserAccount(userAccount).getSingleResult();
		model.addAttribute("student",student);
		}
		catch(org.springframework.dao.EmptyResultDataAccessException exception){
			model.addAttribute("userId",userId);
			return "useraccounts/forgotPassword";
		}
		
		return "useraccounts/confirmPasswordSent";
	}
	
	
	
}
