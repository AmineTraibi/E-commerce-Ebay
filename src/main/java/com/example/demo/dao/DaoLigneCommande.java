package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Ligne_commande;
import com.example.demo.entity.Ligne_commande_PK;

public interface DaoLigneCommande extends JpaRepository<Ligne_commande, Integer> {

}
