package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="paiements")
public class Paiement {
	
	//les Attributs de la commande
	@Id 
	@Column(name="id") 
	private String id;
	
	@Column(name="documentBancaire")
	private String document_bancaire;
	
	
	
	@OneToOne
	@JoinColumn(name="Ref_cmd")
	private Commande commande;
	
	@ManyToOne
	@JoinColumn(name="login_clt")
	private Client client;
	
	
	
	
	
	
	public Paiement() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	public String getDocument_bancaire() {
		return document_bancaire;
	}
	public void setDocument_bancaire(String document_bancaire) {
		this.document_bancaire = document_bancaire;
	}
	
	//fin des attributs
	
	
	//getters setters
	
	

}
