package it.uniroma3.galleria.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import it.uniroma3.galleria.model.Opera;
import it.uniroma3.galleria.service.OperaService;

@Controller
public class WebController {

	@Autowired
	private OperaService operaService;

	@GetMapping("/")
	public String home(Model model){
		List<Opera> opere = (List<Opera>) operaService.findAll();
		List<Opera> recenti = new ArrayList<>();
		int i = opere.size()-1;
		int c = 0;
		while(c < 6){
			recenti.add(opere.get(i));
			i--;
			c++;
		}
		model.addAttribute("recenti", recenti);
		return "index";

	}

	@RequestMapping(value="/admin")
	public String admin(){
		return "admin";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}


	@RequestMapping(value="/403")
	public String Error403(){
		return "403";
	}
}