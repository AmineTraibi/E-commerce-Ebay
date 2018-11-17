package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashMap;

import java.util.Map;



public class Panier implements Serializable {
	
private static Map<String,Ligne_commande> items = new HashMap<String, Ligne_commande>();
	
	public static Ligne_commande AddItem(Produit prd,int qte) {
		
		Ligne_commande lc=items.get(prd.getId_produit());
		
		if(lc==null) {	
			Ligne_commande lc1=new Ligne_commande();
			lc1.getPk().setPrd(prd);
			lc1.setQte(qte);
			items.put(prd.getId_produit(),lc1);
		     }
		else {
			System.out.println("*****D�ja existe*****");
			lc.setQte(lc.getQte()+1);
		     }
		return lc;
	}
	
public static Ligne_commande DeleteItem(String idProduit) {
		
	Ligne_commande lc=items.get(idProduit);
		if(lc.getQte()>1) {
			lc.setQte(lc.getQte()-1);
			System.out.println("jjjjjjjjjjjj"+lc.getQte());
			return lc;
		}
		else {
			items.remove(idProduit);
			System.out.println("*****........99%.....100%......supprimer *****");
		    
		return lc;
		}
	}
	
	
	public Panier() {
		super();
	}

	public static Map<String, Ligne_commande> getItems() {
		return items;
	}

	public static void setItems(Map<String, Ligne_commande> items) {
		Panier.items = items;
	}

	public static void clearpanier() {
		items.clear();
		
	}

	

}
