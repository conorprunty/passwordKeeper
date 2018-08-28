package com.conor.spring.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.conor.spring.model.Passwords;
import com.conor.spring.model.User;
import com.conor.spring.service.PasswordsService;
import com.conor.spring.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserService userService;

	@Autowired
	PasswordsService pService;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		return "index";
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		if (!userService.isUserUnique(user.getId(), user.getName())) {
			FieldError uniqueNameError = new FieldError("user", "name",
					messageSource.getMessage("non.unique.name", new String[] { user.getName() }, Locale.getDefault()));
			result.addError(uniqueNameError);
			return "registration";
		}

		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());

		return "registrationsuccess";
	}

	@RequestMapping(value = { "/passwords" }, method = RequestMethod.GET)
	public String passwordPage(ModelMap model) {
		String userName = getPrincipal();
		List<Passwords> passwords = pService.findAllPasswords(userName);
		model.addAttribute("passwords", passwords);
		return "passwords";
	}

	@RequestMapping(value = { "/passwords/new" }, method = RequestMethod.GET)
	public String newPassword(ModelMap model) {
		Passwords passwords = new Passwords();
		String userName = getPrincipal();
		model.addAttribute("userName", userName);
		model.addAttribute("passwords", passwords);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addnewpassword";
	}

	@RequestMapping(value = { "/passwords/new" }, method = RequestMethod.POST)
	public String savePassword(@Valid Passwords passwords, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "addnewpassword";
		}

		pService.savePasswords(passwords);

		// need to add in all passwords again
		String userName = getPrincipal();
		List<Passwords> allPasswords = pService.findAllPasswords(userName);
		model.addAttribute("passwords", allPasswords);
		model.addAttribute("success", "Password saved for " + passwords.getSystem());
		model.addAttribute("loggedinuser", getPrincipal());
		return "passwords";
	}

	@RequestMapping(value = { "/passwords/delete-{id}" }, method = RequestMethod.GET)
	public String deletePassword(@PathVariable int id) {
		pService.deletePasswordsById(id);
		return "redirect:/passwords";
	}

	@RequestMapping(value = { "/passwords/edit-password-{id}" }, method = RequestMethod.GET)
	public String editPasswords(@PathVariable int id, ModelMap model) {
		Passwords passwords = pService.findById(id);
		model.addAttribute("passwords", passwords);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "editPasswords";
	}

	@RequestMapping(value = { "/passwords/edit-password-{id}" }, method = RequestMethod.POST)
	public String updatePassword(@Valid Passwords passwords, BindingResult result, ModelMap model,
			@PathVariable int id) {

		if (result.hasErrors()) {
			return "editPasswords";
		}

		pService.updatePasswords(passwords);
		model.addAttribute("success", "Password updated for " + passwords.getSystem());
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/passwords";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "redirect:/passwords";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

}
