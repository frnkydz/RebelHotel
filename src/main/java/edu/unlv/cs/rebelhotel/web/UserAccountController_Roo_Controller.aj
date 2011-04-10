// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.unlv.cs.rebelhotel.web;

import edu.unlv.cs.rebelhotel.domain.UserAccount;
import edu.unlv.cs.rebelhotel.domain.enums.UserGroup;
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

privileged aspect UserAccountController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String UserAccountController.create(@Valid UserAccount userAccount, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("userAccount", userAccount);
            return "useraccounts/create";
        }
        userAccount.persist();
        return "redirect:/useraccounts/" + encodeUrlPathSegment(userAccount.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String UserAccountController.createForm(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        return "useraccounts/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String UserAccountController.show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("useraccount", UserAccount.findUserAccount(id));
        model.addAttribute("itemId", id);
        return "useraccounts/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String UserAccountController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("useraccounts", UserAccount.findUserAccountEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) UserAccount.countUserAccounts() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("useraccounts", UserAccount.findAllUserAccounts());
        }
        return "useraccounts/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String UserAccountController.update(@Valid UserAccount userAccount, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("userAccount", userAccount);
            return "useraccounts/update";
        }
        userAccount.merge();
        return "redirect:/useraccounts/" + encodeUrlPathSegment(userAccount.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String UserAccountController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userAccount", UserAccount.findUserAccount(id));
        return "useraccounts/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String UserAccountController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        UserAccount.findUserAccount(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/useraccounts?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @RequestMapping(params = { "find=ByUserId", "form" }, method = RequestMethod.GET)
    public String UserAccountController.findUserAccountsByUserIdForm(Model model) {
        return "useraccounts/findUserAccountsByUserId";
    }
    
    @RequestMapping(params = "find=ByUserId", method = RequestMethod.GET)
    public String UserAccountController.findUserAccountsByUserId(@RequestParam("userId") String userId, Model model) {
        model.addAttribute("useraccounts", UserAccount.findUserAccountsByUserId(userId).getResultList());
        return "useraccounts/list";
    }
    
    @ModelAttribute("usergroups")
    public Collection<UserGroup> UserAccountController.populateUserGroups() {
        return Arrays.asList(UserGroup.class.getEnumConstants());
    }
    
    String UserAccountController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
        String enc = request.getCharacterEncoding();
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
