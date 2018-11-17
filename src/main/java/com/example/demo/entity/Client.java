package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity 
@Table(name="client")
public class Client {

	//Les Attributs du client
	@Id  
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="login_clt") 
	private String login; 
	@Column(name="Nom", length=30) 
	private String nom; 
	@Column(name="Prenom", length=30) 
	private String prenom; 
	@Column(name="Code_cin") 
	private String cin; 
	@Column(name="type_cin") 
	private String type_cin; 
	@Column(name="password", length=30) 
	private String password;
	@Column(name="adresse_clt", length=30) 
	private String adresse; 
    //fin des Attributs
	
	//liaison avec les commandes
	@OneToMany(mappedBy="client")
	private List<Commande>commandes=new ArrayList<>();

	
	public Client(String login, String nom, String prenom,String type, String cin, String password, String adresse) {
		super();
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.type_cin=type;
		this.cin = cin;
		this.password = password;
		this.adresse = adresse;
	}
	public String getType_cin() {
		return type_cin;
	}

	public void setType_cin(String type_cin) {
		this.type_cin = type_cin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Client() {}
	 
	//setters & getters
	
	public List<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
	
	
	
	
}
