package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Produit;


public interface daoEbay {

	public List<Produit>listeproduit(String tag);

}
