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
		
	@GetMapping("/admin/autori")
	public String gestisciAutori(Model model){
		List<Autore> autori = (List<Autore>) autoreService.findAll();
		model.addAttribute("autori", autori);
		return "admin/autori";
	}

	@GetMapping("/admin/autore/inserisci")
	public String showForm(Autore autore){
		return "autore/autoreForm";
	}
	
	@PostMapping("/admin/autore/inserisci")
	public String checkAutoreInfo(@Valid @ModelAttribute Autore autore, 
			BindingResult result, Model model,@RequestParam("id") Long id){
		
		if(result.hasErrors()){
			return "autore/autoreForm";
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(true);
			String dataNascita = dateFormat.format(autore.getDataNascita());
			model.addAttribute("dataNascita",dataNascita);
			try {
				String dataMorte = dateFormat.format(autore.getDataMorte());
				model.addAttribute("dataMorte",dataMorte);
				model.addAttribute(autore);
			} catch (Exception e) {
				model.addAttribute(autore);
			}
			if(id != null) autore.setId(id);
			autoreService.add(autore); 
		}		
		return "autore/datiAutore";
		
	}
	
	@GetMapping("/admin/autore/elimina")
	public ModelAndView deleteAutore(@RequestParam("id") Long id, Model model){
		autoreService.delete(id);
		return new ModelAndView("redirect:/admin/autori");
	}
	
	@GetMapping("/admin/autore/modifica")
	public String modificaAutore(@RequestParam("id") Long id, Model model){
		Autore autore = this.autoreService.findOne(id);
		model.addAttribute(autore);
		return "autore/autoreForm";
	}


}