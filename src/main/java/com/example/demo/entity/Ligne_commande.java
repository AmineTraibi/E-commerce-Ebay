package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity 
@Table(name="ligne_commande") 
public class Ligne_commande {

	@Id
	private Ligne_commande_PK pk; 
	private int qte; 
	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Ligne_commande() {
		pk=new Ligne_commande_PK();
	} 
	public Ligne_commande(Commande cmd, Produit prd, int a,String description) { 
		this.pk = new Ligne_commande_PK(cmd,prd); 
		this.qte = a;
		this.description=description;}
	
	@Transient
	public Produit getProduit() {
		return pk.getPrd();
	}
	@Transient
	public Commande getCommande() {
		return pk.getCmd();
	}
	public Ligne_commande_PK getPk() {
		return pk;
	}
	public void setPk(Ligne_commande_PK pk) {
		this.pk = pk;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	
	
	
	
}
