package com.mode.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tache {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_tache;
	private String texte;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	public Tache( String texte) {
		super();
		this.texte = texte;
	}

	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId_tache() {
		return id_tache;
	}

	public void setId_tache(int id_tache) {
		this.id_tache = id_tache;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Tache [id_tache=" + id_tache + ", texte=" + texte + "]";
	}

}
