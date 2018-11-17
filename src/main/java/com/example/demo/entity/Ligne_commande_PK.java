package com.example.demo.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class Ligne_commande_PK implements Serializable {

	/**
	 * 
	 */
	//les attributs
	private static final long serialVersionUID = 1L;
	@ManyToOne 
	@JoinColumn(name="id_produit") 
	private  Produit prd; 
	@ManyToOne 
	@JoinColumn(name="Ref_cmd") 
	private Commande cmd; 
	//fin des attributs
	
	//les constructeurs
	public Ligne_commande_PK(Commande cmd, Produit prd) {
		super();
		this.prd = prd;
		this.cmd = cmd;
	}
	public Ligne_commande_PK() {
		super();
	}
	
	//les getters & setters
	public Produit getPrd() {
		return prd;
	}
	public void setPrd(Produit prd) {
		this.prd = prd;
	}
	public Commande getCmd() {
		return cmd;
	}
	public void setCmd(Commande cmd) {
		this.cmd = cmd;
	}
	
	
	
	
}
