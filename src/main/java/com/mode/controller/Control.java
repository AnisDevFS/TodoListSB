package com.mode.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mode.entities.Tache;
import com.mode.entities.User;
import com.mode.repositories.TacheRepo;
import com.mode.repositories.UserRepo;


@Controller
public class Control {
	
	@Autowired
	private UserRepo uR;
	
	@Autowired
	private TacheRepo tR;
	
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
	
	@RequestMapping(value = "/acceuil", method = RequestMethod.GET)
	public String accueil(Model model,
		int idConnectedUser) {
		User connectedUser = uR.getById(idConnectedUser);
		if (connectedUser != null)
			return connexion(model, connectedUser.getEmail(), connectedUser.getPassword());
		else
			return home();
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
				model.addAttribute("listTaches", user.getTaches());
			}
				
		}
		if (UserExist)
		return "connected";
		else return "redirect:/login";
	}
	
	
	@RequestMapping(value = "/index" , method = RequestMethod.POST)
	public String index(Model model , 
	int idConnectedUser,
	@RequestParam(name = "mc") String mc) {
		
		User connectedUser = uR.getById(idConnectedUser);
		System.out.println(connectedUser);
		List<Tache> tachesOfCU = connectedUser.getTaches();
		
		if (mc.length() == 0 || mc.equals(null)) {
			System.out.println(tachesOfCU.size() + "mc.length() == 0");

			model.addAttribute("listTaches", tachesOfCU);
		}
		else {
			List<Tache> tachesTrouvees = tR.chercher("%"+mc.toLowerCase()+"%" , connectedUser.getId_user());
			System.out.println(tachesTrouvees.size() + "mc.length() != 0");
			System.out.println(mc);
			System.out.println(tachesTrouvees);
			model.addAttribute("listTaches", tachesTrouvees);
		}
		
		model.addAttribute("mc", mc);
		model.addAttribute("connectedUser", connectedUser);
		return "connected";
	}
	
	@RequestMapping(value = "/addTache")
	public String frmNewCmd(Model model, int idConnectedUser) {

		User connectedUser = uR.getById(idConnectedUser);
		if (connectedUser != null) {

			model.addAttribute("idConnectedUser", idConnectedUser);		

			return "ajoutTache";
		} else
			return home();
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
	
	
	
	@RequestMapping(value = "/saveTache", method = RequestMethod.POST)
	public String saveCmd(Model model, 
			int idConnectedUser, 
			@Valid Tache tache,
			BindingResult bindingRes) {
		System.out.println("dans la mehtod save");
		System.out.println(bindingRes.toString());
				
		if (bindingRes.hasErrors()) {
			System.out.println("J'ai des erreurs dans la saisie");
			model.addAttribute("newTache", tache);
			model.addAttribute("idConnectedUser", idConnectedUser);
			return "ajoutTache";
		}
		
		User connectedUser =  uR.getById(idConnectedUser);

		if (connectedUser != null) {

			tache.setUser(connectedUser);
 
			System.out.println(tache.toString());
			tR.save(tache);
			model.addAttribute("idConnectedUser", idConnectedUser);
			model.addAttribute("newTache", tache);
//			return "viewConfirmationTache";
			return connexion(model, connectedUser.getEmail(), connectedUser.getPassword());
			
		} else
			return home();
	}

}
