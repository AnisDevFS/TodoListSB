package com.mode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mode.entities.User;
import com.mode.repositories.UserRepo;


@Controller
public class Control {
	
	@Autowired
	private UserRepo uR;
	
	@RequestMapping(value = "/login" , method=RequestMethod.GET)
	public String login() {
		return "connexion";
	}
	
	@RequestMapping(value = "/")
	public String home() {
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/ajout")
	public String goToAjoutForm() {
		return "ajoutForm";
	}
	
	@RequestMapping(value = "/connexion" , method=RequestMethod.POST)
	public String connexion(
			Model model , 
			@RequestParam(name = "email", defaultValue="")  String email ,
			@RequestParam(name = "password", defaultValue="")  String password ) {
//		String username = request.getParametres("username") ce qu'on fait avant
		
//		model.addAttribute("username", username);
		System.out.println("--------------" +email);
		List<User> users = uR.findAll();
		boolean UserExist = false;
		for (User user : users) {
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				UserExist = true;
				model.addAttribute("connectedUser", user);
			}
				
		}
		if (UserExist)
		return "connected";
		else return "redirect:/login";
	}
	
	
	
	
	
	
	@RequestMapping(value = "/creerProfil" , method=RequestMethod.POST)
	public String creerProfil(
			Model model , 
			@RequestParam(name = "nom", defaultValue="")  String nom ,
			@RequestParam(name = "password", defaultValue="")  String password ,
			@RequestParam(name = "email", defaultValue="")  String email ,
			@RequestParam(name = "age", defaultValue="")  int age) {
		
		System.out.println("--------------" +nom);
		
		User newUser = new User(nom, email, password, age);
		uR.save(newUser);
		model.addAttribute("connectedUser", newUser);
		return "connected";
	}

}
