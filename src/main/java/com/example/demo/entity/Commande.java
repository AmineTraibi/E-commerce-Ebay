package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity 
@Table(name="commande")
public class Commande {

	//les Attributs de la commande
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="Ref_cmd") 
	private int ref;
	@Column(nullable=false) 
	private LocalDate date;
	@Column(name="Adresse_de_Livraison", length=30) 
	private String adresselivraison ;
	@Column(name="etat_cmd", length=30) 
	private String etat_cmd ;
	@Column(name="montant_cmd", length=30)
	private double montant;
	//fin des attributs
	
	@ManyToOne
	@JoinColumn(name="login_crt")
	private Courtier courtier;
	
	public Courtier getCourtier() {
		return courtier;
	}

	public void setCourtier(Courtier courtier) {
		this.courtier = courtier;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getEtat_cmd() {
		return etat_cmd;
	}

	public void setEtat_cmd(String etat_cmd) {
		this.etat_cmd = etat_cmd;
	}

	//liaison avec le client
	@ManyToOne
	@JoinColumn(name="login_clt")
	private Client client;
	
	//liaison avec la facture

	//liaison avec bon de livraison

	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	//liaison avec produit via ligne_commande
	@OneToMany(mappedBy="pk.cmd")
	private List<Ligne_commande>cnt;

	//les constructeurs
	public Commande(LocalDate date, String adresselivraison) {
		super();
		this.date = date;
		this.adresselivraison = adresselivraison;
	}
	
	public Commande(LocalDate date) {
		super();
		this.date = date;
		
	}
	public Commande() {}
	
	// setters & getters
	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getAdresselivraison() {
		return adresselivraison;
	}

	public void setAdresselivraison(String adresselivraison) {
		this.adresselivraison = adresselivraison;
	}

	
	public List<Ligne_commande> getCnt() {
		return cnt;
	}

	public void setCnt(List<Ligne_commande> cnt) {
		this.cnt = cnt;
	}
	
	
}
