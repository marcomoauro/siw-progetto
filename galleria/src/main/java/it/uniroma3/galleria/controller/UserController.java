package it.uniroma3.galleria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.galleria.model.Ruolo;
import it.uniroma3.galleria.model.User;
import it.uniroma3.galleria.service.RuoloService;
import it.uniroma3.galleria.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private RuoloService ruoloService;

	@GetMapping("/signUp")
	public String showForm(User user){
		return "signUp";
	}
	
	@PostMapping(value={"/signUp"})
	public String signUp(@Valid @ModelAttribute User user, 
			BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
			return "signUp";
		}else {
			model.addAttribute(user);
			try{
				userService.add(user); 
				ruoloService.add(new Ruolo(user.getUsername()));
			}catch(Exception e){
				return "signUp";
			}
		}
		return "datiUtente";
	}
}
