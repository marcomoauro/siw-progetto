package it.uniroma3.galleria.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.uniroma3.galleria.model.Autore;
import it.uniroma3.galleria.service.AutoreService;

@Controller
public class AutoreController {
	
	@Autowired
	private AutoreService autoreService;
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	} 
		
	@GetMapping("/autori")
	public String showAutori(Model model){
		List<Autore> autori = (List<Autore>) autoreService.findAll();
		model.addAttribute("autori", autori);
		return "admin/autori";
	}

	@GetMapping("/autore")
	public String showForm(Autore autore){
		return "autore/autoreForm";
	}
	
	@GetMapping("/autore/details")
	public String showAutore(@RequestParam("id")long id, Model model){
		Autore a = autoreService.findOne(id);
		model.addAttribute("autore", a);
		return "autore/autoreDetails";
	}
	
	@GetMapping("autore/cancella")
	public ModelAndView deleteAutore(@RequestParam("id")long id, Model model){
		autoreService.delete(id);
		return new ModelAndView("redirect:/autori");
	}

	@PostMapping("/autore")
	public String checkAutoreInfo(@Valid @ModelAttribute Autore autore, 
			BindingResult result, Model model){

		if(result.hasErrors()){
			return "autore/autoreForm";
		}else{
			model.addAttribute(autore);
			autoreService.add(autore);
		}

		return "autore/autoreInserito";

	}

}