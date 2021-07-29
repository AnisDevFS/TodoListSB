package com.mode.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_user;
	private String nom;
	private String email;
	private String password;

	private int age;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user")
	private List<Tache> taches = new ArrayList<Tache>();

	public User(String nom, String email, String password, int age) {
		super();
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.age = age;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", nom=" + nom + ", email=" + email + ", age=" + age + ", taches=" + taches
				+ "]";
	}

	public void addTache(Tache tache) {
		if (this.getTaches() != null) {
			List<Tache> taches = this.getTaches();
			taches.add(tache);
			this.setTaches(taches);
		}

	}

	public void deleteTache(int idTache) {
		if (this.getTaches() != null) {
			List<Tache> taches = this.getTaches();
			for (int i = 0; i < taches.size(); i++) {
				if (idTache == taches.get(i).getId_tache()) {
					taches.remove(i);
					break;
				}
			}
			this.setTaches(taches);
		}

	}

}
