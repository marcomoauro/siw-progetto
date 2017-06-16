package it.uniroma3.galleria.controller;

import java.util.ArrayList;
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

import it.uniroma3.galleria.model.Autore;
import it.uniroma3.galleria.model.Opera;
import it.uniroma3.galleria.model.Stanza;
import it.uniroma3.galleria.service.AutoreService;
import it.uniroma3.galleria.service.OperaService;
import it.uniroma3.galleria.service.StanzaService;

@Controller
public class OperaController  {
	
    @Autowired
    private OperaService operaService; 
    @Autowired
    private StanzaService stanzaService;
    @Autowired
    private AutoreService autoreService;

    @GetMapping("/admin/opere")
	public String gestisciOpere(Model model){
		List<Opera> opere = (List<Opera>) operaService.findAll();
		model.addAttribute("opere", opere);
		return "admin/opere";
	}

    @GetMapping("/admin/opera/inserisci")
    public String operaForm(Model model, Opera opera,ArrayList<Autore> autori,ArrayList<Stanza> stanze){
    	List<Autore> autoriX = (List<Autore>) autoreService.findAll();
    	List<Stanza> stanzeX = (List<Stanza>) stanzaService.findAll();
    	model.addAttribute("autori",autoriX);
    	model.addAttribute("stanze",stanzeX);
    	return "opera/operaForm";
    }
    
    @PostMapping("/admin/opera/inserisci")
    public String checkOperaInfo(@Valid @ModelAttribute Opera opera, 
    									BindingResult bindingResult, Model model,
    									@RequestParam("autoreId") String autId,
    									@RequestParam("stanzaId") String staId) {
    	
        if (bindingResult.hasErrors()) {
            return "opera/operaForm";
        }
        else {
        	Long autoreId = Long.parseLong(autId);
        	Long stanzaId = Long.parseLong(staId);
        	Autore autore = autoreService.findOne(autoreId);
			opera.setAutore(autore);
			autore.getOpere().add(opera);
			if(stanzaId != 0){
				Stanza stanza = stanzaService.findOne(stanzaId);
				opera.setStanza(stanza);
				stanza.getOpere().add(opera);
				model.addAttribute(stanza);
			}
			model.addAttribute(autore);
			model.addAttribute(opera);
            operaService.add(opera); 
        }
        return "opera/datiOpera";
    }
    
    @GetMapping("/admin/opera/elimina")
	public ModelAndView deleteOpera(@RequestParam("id")long id, Model model){
		operaService.delete(id);
		return new ModelAndView("redirect:/admin/opere");
	}
}

