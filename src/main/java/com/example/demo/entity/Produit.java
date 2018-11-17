package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name="produit_from_Ebay")
public class Produit {


	@Id
	@Column(name="id", length=100) 
	private String id_produit; 
	@Column(name="categ", length=100)
	private String cat_produit;
	@Column(name="titre_prd", length=100) 
	private String titre_produit;
	@Column(name="sub_titre_prd", length=100) 
	private String sub_titre_produit;
	@Column(name="Prix_prd", length=100) 
	private String prix_produit;
	@Column(name="url_img_prd", length=100) 
	private String img_produit;
	
	
	
	public String getSub_titre_produit() {
		return sub_titre_produit;
	}

	public void setSub_titre_produit(String sub_titre_produit) {
		this.sub_titre_produit = sub_titre_produit;
	}
	@OneToMany(mappedBy="pk.prd")
	private Set<Ligne_commande>cnt=new HashSet<>();
	//les constructeurs
	
	public Produit() {
		super();
	}
	
	public Produit(String id_produit, String cat_produit, String titre_produit,String sub_titre_produit, String prix_produit, String img_produit) {
		super();
		this.id_produit = id_produit;
		this.sub_titre_produit = sub_titre_produit;
		this.cat_produit = cat_produit;
		this.titre_produit = titre_produit;
		this.prix_produit = prix_produit;
		this.img_produit = img_produit;
	}
	
	public String getCat_produit() {
		return cat_produit;
	}

	public void setCat_produit(String cat_produit) {
		this.cat_produit = cat_produit;
	}

	public Set<Ligne_commande> getCnt() {
		return cnt;
	}

	public void setCnt(Set<Ligne_commande> cnt) {
		this.cnt = cnt;
	}

	//getter & setter
	public String getId_produit() {
		return id_produit;
	}
	public void setId_produit(String id_produit) {
		this.id_produit = id_produit;
	}
	public String getTitre_produit() {
		return titre_produit;
	}
	public void setTitre_produit(String titre_produit) {
		this.titre_produit = titre_produit;
	}
	public String getPrix_produit() {
		return prix_produit;
	}
	public void setPrix_produit(String prix_produit) {
		this.prix_produit = prix_produit;
	}
	public String getImg_produit() {
		return img_produit;
	}
	public void setImg_produit(String img_produit) {
		this.img_produit = img_produit;
	}
	
}
