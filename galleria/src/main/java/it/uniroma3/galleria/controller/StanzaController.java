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
import it.uniroma3.galleria.model.Opera;
import it.uniroma3.galleria.model.Stanza;
import it.uniroma3.galleria.service.OperaService;
import it.uniroma3.galleria.service.StanzaService;

@Controller
public class StanzaController {
	
	@Autowired
	private StanzaService stanzaService;
	@Autowired
	private OperaService operaService;
	
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
    public String checkstanzaInfo(@Valid @ModelAttribute Stanza stanza, 
    									BindingResult bindingResult, Model model,
    									@RequestParam("id") Long id) {
    	
        if (bindingResult.hasErrors()) {
            return "stanza/stanzaForm";
        }
        else {
        	if(id != null) stanza.setId(id);
        	model.addAttribute(stanza);
            stanzaService.add(stanza); 
        }
        return "stanza/datiStanza";
    }
	
	@GetMapping("/admin/stanza/elimina")
	public ModelAndView deleteStanza(@RequestParam("id")long id, Model model){
		stanzaService.delete(id);
		return new ModelAndView("redirect:/admin/stanze");
	}
	
	@GetMapping("/admin/stanza/modifica")
	public String modificaStanza(@RequestParam("id") Long id, Model model){
	Stanza stanza = this.stanzaService.findOne(id);
		model.addAttribute(stanza);
		return "stanza/stanzaForm";
	}
	
	@GetMapping("/lista/stanze")
	public String showStanza(Model model){
		List<Stanza> stanze = (List<Stanza>) stanzaService.findAll();
		model.addAttribute("stanze",stanze);
		return "stanza/listaStanze";
	}
	
	@GetMapping("/dettagli/stanza")
	public String dettagliStanza(@RequestParam("id") Long id, Model model){
		Stanza stanza = stanzaService.findOne(id);
		model.addAttribute(stanza);
		List<Opera> opere = operaService.findByStanza(id);
		model.addAttribute("opere",opere);
		return "stanza/dettagliStanza";
	}
}