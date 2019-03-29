package com.bmi.controller.app;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bmi.app.entity.Utilisateur;
import com.bmi.app.repository.UtilisateurRepository;
import com.bmi.rapport.entity.Bmiequ;
import com.bmi.rapport.repository.BmiequRepository;
import com.bmi.service.app.ServicerResponseApi;

@RestController
public class ControllerApiApp {
	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired
	BmiequRepository bmiequRepository;

	@PostMapping("/saveUser")
	public ResponseEntity<Object> addUser(@RequestBody Utilisateur utilisateur) {

		if (utilisateur.getUtilisateurType() == null) {
			utilisateur.setUtilisateurType("USER");
		}
		utilisateurRepository.save(utilisateur);
		ServicerResponseApi response = new ServicerResponseApi();
		response.setData(null);
		response.setStatus("success");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@GetMapping("/delete/{id}")
	void deleteAccount(@PathVariable(name = "id") long utilisateur) {
		utilisateurRepository.deleteById(utilisateur);
	}

	@GetMapping(path = { "/Users/{email}", "/Users/" })
	public ResponseEntity<Object> getAllUseres(@PathVariable(name = "email", required = false) Optional<String> email) {
		List<Utilisateur> user;
		utilisateurRepository.findAll();

		if (email.isPresent()) {
			String newEmail = newEmail = email.get();
			user = utilisateurRepository.findLikeMailOrFirstNameOrLastName(newEmail);
		} else {
			user = utilisateurRepository.findAll();
		}

		ServicerResponseApi response = new ServicerResponseApi();
		response.setData(user);
		response.setStatus("success");
		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

	@GetMapping("/testdb1")
	List<Utilisateur> db1() {
		return utilisateurRepository.findAll();
	}
	@GetMapping("/testdb2")
	List<Bmiequ> db2() {
		
		
		return bmiequRepository.findAll();
	}

}
