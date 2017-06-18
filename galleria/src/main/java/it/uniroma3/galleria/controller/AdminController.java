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

import it.uniroma3.galleria.model.Ruolo;
import it.uniroma3.galleria.model.Admin;
import it.uniroma3.galleria.service.RuoloService;
import it.uniroma3.galleria.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private RuoloService ruoloService;

	@GetMapping("admin/admins")
	public String showForm(Model model){
		List<Admin> admins = (List<Admin>) adminService.findAll();
		model.addAttribute("admins",admins);
		return "admin/admins";
	}

	@GetMapping("admin/admin/inserisci")
	public String adminForm(Admin admin){
		return "admins/adminForm";

	}

	@PostMapping("admin/admin/inserisci")
	public String checkAdminInfo(@Valid @ModelAttribute Admin admin, 
			BindingResult result, Model model,@RequestParam("id") Long id) throws Exception{
		if (result.hasErrors()) {
			return "admins/adminForm";
		}
		else{
			model.addAttribute(admin);
			try{
			Ruolo ruolo = new Ruolo(admin.getUsername());
			admin.setEnabled(true);
			adminService.add(admin);
			ruoloService.add(ruolo);
			}
			catch(Exception e){
				return "admins/adminForm";
			}
		}
		return "admins/datiAdmin";
	}
	
	@GetMapping("/admin/admin/elimina")
	public ModelAndView deleteAdmin(@RequestParam("id") Long id){
		adminService.delete(id);
		return new ModelAndView("redirect:/admin/admins");
	}

    @GetMapping("/admin/admin/modifica")
	public String modificaAdmin(@RequestParam("id") Long id, Model model){
		Admin admin = this.adminService.findOne(id);
		model.addAttribute(admin);
		return "admins/adminForm";
	}
}