package it.uniroma3.galleria.controller;

import java.util.ArrayList;
import java.util.Collections;
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

import it.uniroma3.galleria.comparator.ComparatorePerAnno;
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
    									@RequestParam("stanzaId") String staId,
    									@RequestParam("id") Long id) {
    	
        if (bindingResult.hasErrors()) {
        	List<Autore> autoriX = (List<Autore>) autoreService.findAll();
        	List<Stanza> stanzeX = (List<Stanza>) stanzaService.findAll();
        	model.addAttribute("autori",autoriX);
        	model.addAttribute("stanze",stanzeX);
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
			}
			if(id != null) opera.setId(id);
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
    
    @GetMapping("/admin/opera/modifica")
	public String modificaOpera(@RequestParam("id") Long id, Model model){
		Opera opera = this.operaService.findOne(id);
		model.addAttribute(opera);
		List<Autore> autori = (List<Autore>) autoreService.findAll();
    	List<Stanza> stanze = (List<Stanza>) stanzaService.findAll();
    	model.addAttribute("autori",autori);
    	model.addAttribute("stanze",stanze);
		return "opera/operaForm";
	}
    
    @PostMapping("/ricercaOpera")
    public String cercaOpera(Model model, @RequestParam("nomeOpera") String nome){
    	List<Opera> opere = (List<Opera>) operaService.findAll();
    	List<Opera> risultati = new ArrayList<>();
    	for(Opera opera: opere){
    		if(opera.getNome().toUpperCase().contains(nome.toUpperCase()))
    			risultati.add(opera);
    	}
    	model.addAttribute("risultati", risultati);
    	return "opera/risultatoRicerca";
    }
    
    @GetMapping("/lista/opere")
    public String listaOpere(Model model, @RequestParam("comp") String comp){
    	List<Opera> opere = (List<Opera>) operaService.findAll();
    	if(comp.equals("nome")){
    		Collections.sort(opere);
    	}else if(comp.equals("anno")){
    		ComparatorePerAnno comparatore = new ComparatorePerAnno();
    		Collections.sort(opere,comparatore);
    	}else if(comp.equals("null")){
        	model.addAttribute("opere",opere);
    	}
    	model.addAttribute("opere",opere);
    	return "opera/listaOpere";
    }
}

