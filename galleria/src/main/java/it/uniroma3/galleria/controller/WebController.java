package it.uniroma3.galleria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.galleria.model.Admin;
import it.uniroma3.galleria.service.AdminService;

@Controller
public class WebController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/admin")
	public String admin(){
		return "admin";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPost(@RequestParam("username") String username, Model model){
		Admin admin = adminService.findByUsername(username);
		model.addAttribute(admin);
		return "admin";
	}


	@RequestMapping(value="/403")
	public String Error403(){
		return "403";
	}
}