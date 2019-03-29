package com.bmi.controller.app;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bmi.app.entity.Utilisateur;
import com.bmi.app.repository.UtilisateurRepository;

@Controller
public class HomeController {
@Autowired
UtilisateurRepository utilisateurRepository;
	@RequestMapping("/")
	String home() {
		return "index";
	}
	@RequestMapping("/index")
	String test(Principal principal) {
		
		return "index";
	}
	@RequestMapping("/sujet")
	String sujet() {
		return "sujet";
	}

	@RequestMapping(path = "/cfg-account")
	String configUsers() {
		return "cfguser";
	}

}
