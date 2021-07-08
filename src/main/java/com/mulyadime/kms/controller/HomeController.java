/**
 * 
 */
package com.mulyadime.kms.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mulyadime.kms.entity.Account;
import com.mulyadime.kms.payload.SignUpForm;
import com.mulyadime.kms.repository.RoleRepository;
import com.mulyadime.kms.services.AccountService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Hamid Mulyadi
 *
 */
@Slf4j
@Controller
public class HomeController {
	
	private static final String SIGNIN_VIEW_NAME = "/signin";
	
	private static final String SIGNUP_VIEW_NAME = "/signup";

	private static final String REDIRECT_PATH = "redirect:/";
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RoleRepository roleRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/signIn")
	public String signIn() {
		return SIGNIN_VIEW_NAME;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/signUp")
	public String signUp(Model model) {
		model.addAttribute("form", new SignUpForm());
		
		return SIGNUP_VIEW_NAME;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/signUp")
	public String signUpPost(@Validated SignUpForm form, Errors errors) {
		if (errors.hasErrors()) {
			return SIGNUP_VIEW_NAME;
		}
		accountService.autoSignIn(accountService.save(createAccount(form)));
		
		return REDIRECT_PATH;
	}

	private Account createAccount(SignUpForm form) {
		Account account = new Account();
		account.setUsername(form.getUsername());
		account.setPassword(form.getPassword());
		account.setRoles(Arrays.asList(roleRepository.findByName("USER")));
		log.debug("Parameter SignUpForm {}", account.toString());
		
		return account;
	}

}
