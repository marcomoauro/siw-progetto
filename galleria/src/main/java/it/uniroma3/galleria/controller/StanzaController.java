package it.uniroma3.galleria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.uniroma3.galleria.model.Stanza;
import it.uniroma3.galleria.service.StanzaService;

@Controller
public class StanzaController {
	
	@Autowired
	private StanzaService stanzaService;
	
	@GetMapping("/admin/stanze")
	public String gestisciStanze(Model model){
		List<Stanza> stanze = (List<Stanza>) stanzaService.findAll();
		model.addAttribute("stanze",stanze);
		return "admin/stanze";
	}
	
	@GetMapping("/admin/stanza/inserisci")
    public String stanzaForm(Stanza stanza){
    	return "stanza/stanzaForm";
    }
	
	@PostMapping("/admin/stanza/inserisci")
    public ModelAndView checkstanzaInfo(@Valid @ModelAttribute Stanza stanza, 
    									BindingResult bindingResult, Model model) {
    	
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/admin/stanza/inserisci");
        }
        else {
        	model.addAttribute(stanza);
            stanzaService.add(stanza); 
        }
        return new ModelAndView("stanza/datiStanza");
    }
	
	@GetMapping("/admin/stanza/elimina")
	public ModelAndView deleteStanza(@RequestParam("id")long id, Model model){
		stanzaService.delete(id);
		return new ModelAndView("redirect:/admin/stanze");
	}

}
