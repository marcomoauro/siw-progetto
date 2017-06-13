package it.uniroma3.galleria.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.galleria.model.Opera;
import it.uniroma3.galleria.model.User;
import it.uniroma3.galleria.service.UserService;

@Controller
public class WebController {

//	@RequestMapping(value={"/","/home"})
//	public String home(){
//		return "home";
//	}
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/signUp"})
	public String signUp(@Valid @ModelAttribute User user, 
			BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
            return "signUp";
        }
        else {
        	model.addAttribute(user);
            userService.add(user); 
        }
        return "datiUtente";
    }
	
	@RequestMapping(value={"/welcome"})
	public String welcome(){
		return "welcome";
	}

	@RequestMapping(value="/admin")
	public String admin(){
		return "admin";
	}

	@RequestMapping(value={"/login"})
	public String login(){
		return "login";
	}


	@RequestMapping(value="/403")
	public String Error403(){
		return "403";
	}
}