package it.uniroma3.galleria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.galleria.model.Admin;

@Controller
public class WebController {

	@RequestMapping(value="/admin")
	public String admin(){
		return "admin";
	}

	@RequestMapping(value={"/login"})
	public String login(@ModelAttribute Admin admin, Model model){
		model.addAttribute(admin);
		return "login";
	}


	@RequestMapping(value="/403")
	public String Error403(){
		return "403";
	}
}