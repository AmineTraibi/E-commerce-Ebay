package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Client;
import com.example.demo.entity.Commande;
import com.example.demo.entity.Ligne_commande;
import com.example.demo.entity.Produit;


public interface Dao extends JpaRepository<Produit, String>{

	
	/*
	public void saveProduit(Produit prd);
	public void saveCommande(Commande cmd);
	public void saveligneCommande(Ligne_commande lg);
	public List<Produit>listeproduit(String tag);
	public void saveClient(Client clt);
	public Client Existe_clt(String login,String password);*/
}
