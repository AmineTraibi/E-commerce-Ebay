package com.example.demo.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="courtier")
public class Courtier {
	
	@Id 
	@Column(name="login_crt") 
	private String login_crt;
	@Column(name="nom_coutier") 
	private String nom;
	@Column(name="prenom_courtier", length=30) 
	private String prenom ;
	@Column(name="numero")
	private String numero_telephone;
	@Column(name="type_identifient")
	private String typeId;
	@Column(name="num_identifient")
	private String numId;
	@Column(name="solde", length=30) 
	private double solde ;
	/*@Column(name="nationalite", length=30) 
	private String nationalite ;*/
	@Column(name="adresse_courtier", length=30) 
	private String adresse; 
	@Column(name="password", length=30) 
	private String password;
	
	
	public Courtier() {
		super();
	}

	@OneToMany(mappedBy="courtier")
	private List<Commande>commandes;
	
	
	public List<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	public String getLogin_crt() {
		return login_crt;
	}
	public void setLogin_crt(String login_crt) {
		this.login_crt = login_crt;
	}
	public Courtier(String nom, String prenom, String numero_telephone, String typeId, String numId,double solde,
			String adresse, String login, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.numero_telephone = numero_telephone;
		this.typeId = typeId;
		this.numId = numId;
		this.solde = solde;
		this.adresse = adresse;
		this.login_crt = login;
		this.password = password;
	}
	public String getNumId() {
		return numId;
	}
	public void setNumId(String numId) {
		this.numId = numId;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNumero_telephone() {
		return numero_telephone;
	}
	public void setNumero_telephone(String numero_telephone) {
		this.numero_telephone = numero_telephone;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
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

	

}
