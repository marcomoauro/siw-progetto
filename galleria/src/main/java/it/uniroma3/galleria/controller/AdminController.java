package it.uniroma3.galleria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.uniroma3.galleria.model.Ruolo;
import it.uniroma3.galleria.model.User;
import it.uniroma3.galleria.service.RuoloService;
import it.uniroma3.galleria.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RuoloService ruoloService;
	
	@GetMapping("admin/mostraUtenti")
	public String listaUtenti(Model model){
		List<Ruolo> ruoli = (List<Ruolo>) ruoloService.findAll();
		model.addAttribute("ruoli",ruoli);
		return "nominaAdmin";
	}
	
	@GetMapping("admin/nominaAdmin")
	public ModelAndView nominaAdmin(@RequestParam("username") String username){
		Ruolo ruolo = new Ruolo(username,"ROLE_ADMIN");
		ruoloService.add(ruolo);
		return new ModelAndView("redirect:/admin/mostraUtenti");
		
	}
	
}
