package it.uniroma3.galleria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// controller to access the login page
@Controller
public class MainController {

  // Login form
  @RequestMapping("/login")
  public String login() {
//	@RequestParam("username") 
//	String u1;
    return "login";
  }

  // Login form with error
  @RequestMapping("/login-error.html")
  public String loginError(Model model) {
    model.addAttribute("loginError", true);
    return "login";
  }

}