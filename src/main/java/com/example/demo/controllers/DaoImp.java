package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.ebay.EbayDriver;
import com.example.demo.entity.Produit;

@Repository(value="daoebay")
public class DaoImp implements daoEbay {

	
	List<Produit> l;

	
	public List<Produit> listeproduit(String tag) {
		EbayDriver driver = new EbayDriver();
			try {
				l=driver.run(java.net.URLEncoder.encode(tag, "UTF-8"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("hhhhhhhhhhhhhhhhhhhhhh"+l.size());
		return l;
		
	}


	
}
