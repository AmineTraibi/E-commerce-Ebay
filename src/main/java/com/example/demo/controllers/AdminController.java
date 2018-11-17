package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.DaoClient;
import com.example.demo.dao.DaoCommande;
import com.example.demo.dao.DaoCourtier;
import com.example.demo.dao.DaoPaiement;
import com.example.demo.entity.Commande;

@RestController
public class AdminController {

	@Autowired
	private DaoCommande doac;
	
	@Autowired 
	private DaoCourtier daoCourtier;
	
	
	@Autowired
	private DaoPaiement daoPaiement;
	
	@Autowired
	private DaoClient daoclient;
	
	
	@RequestMapping("/admin")
	 public ModelAndView homeAdmini() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("admin");
		modelandview.addObject("lc", doac.findAll());
		modelandview.addObject("taillec", doac.findAll().size());
		List<Commande> l = doac.findAll();
		List<Commande> lc=new ArrayList<>();
		for(int i=0;i<l.size();i++) {
			if(l.get(i).getEtat_cmd().equals("Livrer")) {
				lc.add(l.get(i));
			}
		}
		
		modelandview.addObject("tailleAchat", lc.size());
		modelandview.addObject("taillePaiement", daoPaiement.findAll().size());
		modelandview.addObject("tailleCourtier", daoCourtier.findAll().size());



		return modelandview;
	    }
	
	   @RequestMapping("/deletecommande")
	    public ModelAndView deletecmd(@RequestParam("idc") String id) {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("admin");
		doac.deleteById(Integer.parseInt(id));
		modelandview.addObject("lc", doac.findAll());
		modelandview.addObject("taillec", doac.findAll().size());
		modelandview.addObject("tailleAchat", doac.findAll().size());
		modelandview.addObject("taillePaiement", daoPaiement.findAll().size());
		modelandview.addObject("tailleCourtier", daoCourtier.findAll().size());



		return modelandview;
	    }
	   
	   @RequestMapping("/deleteclient")
	    public ModelAndView deleteclt(@RequestParam("idc") String id) {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("adminClient");
		daoclient.deleteById(id);
		modelandview.addObject("lc", daoclient.findAll());
		modelandview.addObject("taillec", doac.findAll().size());
		modelandview.addObject("tailleAchat", doac.findAll().size());
		modelandview.addObject("taillePaiement", daoPaiement.findAll().size());
		modelandview.addObject("tailleCourtier", daoCourtier.findAll().size());



		return modelandview;
	    }
	   
	   @RequestMapping("/deletecourtier")
	    public ModelAndView deletecourtier(@RequestParam("id") String id) {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("adminCourtier");
		daoCourtier.deleteById(id);
		modelandview.addObject("lc", daoCourtier.findAll());
		modelandview.addObject("taillec", doac.findAll().size());
		modelandview.addObject("tailleAchat", doac.findAll().size());
		modelandview.addObject("taillePaiement", daoPaiement.findAll().size());
		modelandview.addObject("tailleCourtier", daoCourtier.findAll().size());



		return modelandview;
	    }
	   
	@RequestMapping("/updatecommande")
    public ModelAndView updatecmd(@RequestParam("idc") String id,
    		@RequestParam("etat") String etat 
    		                      ) {
	ModelAndView modelandview = new ModelAndView();
	Commande cmd=doac.getOne(Integer.parseInt(id));
	switch (etat) {
	case "à payer":
		cmd.setEtat_cmd("En cours");
		break;
	case "En cours":
		cmd.setEtat_cmd("En cours de livraison");
		break;
	case "En cours de livraison":
		cmd.setEtat_cmd("Livrer");
		break;
	}
	doac.save(cmd);
	modelandview.setViewName("adminCommandes");
	List<Commande> l = doac.findAll();
	List<Commande> lc=new ArrayList<>();
	for(int i=0;i<l.size();i++) {
		if(l.get(i).getEtat_cmd().equals("Livrer")) {
			lc.add(l.get(i));
		}
	}
	
	modelandview.addObject("lc", doac.findAll());
	modelandview.addObject("taillec", doac.findAll().size());
	modelandview.addObject("tailleAchat", lc.size());
	modelandview.addObject("taillePaiement", daoPaiement.findAll().size());
	modelandview.addObject("tailleCourtier", daoCourtier.findAll().size());



	return modelandview;
    }
	
	@RequestMapping("/adminCommandes")
	public ModelAndView adminCommandes() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("adminCommandes");
		modelandview.addObject("lc", doac.findAll());
		modelandview.addObject("taillec", doac.findAll().size());
		List<Commande> l = doac.findAll();
		List<Commande> lc=new ArrayList<>();
		for(int i=0;i<l.size();i++) {
			if(l.get(i).getEtat_cmd().equals("Livrer")) {
				lc.add(l.get(i));
			}
		}
		
		modelandview.addObject("tailleAchat", lc.size());
		modelandview.addObject("taillePaiement", daoPaiement.findAll().size());
		modelandview.addObject("tailleCourtier", daoCourtier.findAll().size());

		
		
		return modelandview;
	}
	
	@RequestMapping("/adminAchats")
	public ModelAndView adminAchats() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("adminAchats");
		List<Commande> l = doac.findAll();
		List<Commande> lc=new ArrayList<>();
		for(int i=0;i<l.size();i++) {
			if(l.get(i).getEtat_cmd().equals("Livrer")) {
				lc.add(l.get(i));
			}
		}
		
		modelandview.addObject("lc",lc);

		modelandview.addObject("taillec", doac.findAll().size());

		modelandview.addObject("tailleAchat", lc.size());
		modelandview.addObject("taillePaiement", daoPaiement.findAll().size());
		modelandview.addObject("tailleCourtier", daoCourtier.findAll().size());


		modelandview.addObject("taillec", doac.findAll().size());

		return modelandview;
	}
	
	@RequestMapping("/adminPaiement")
	public ModelAndView adminPaiements() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("adminPaiement");
		modelandview.addObject("lc", daoPaiement.findAll());
		modelandview.addObject("taillec", doac.findAll().size());
		List<Commande> l = doac.findAll();
		List<Commande> lc=new ArrayList<>();
		for(int i=0;i<l.size();i++) {
			if(l.get(i).getEtat_cmd().equals("Livrer")) {
				lc.add(l.get(i));
			}
		}
		
		
		modelandview.addObject("tailleAchat", lc.size());
		modelandview.addObject("tailleCourtier", daoCourtier.findAll().size());

		modelandview.addObject("taillePaiement", daoPaiement.findAll().size());

		return modelandview;
	}
	
	@RequestMapping("/adminCourtier")
	public ModelAndView adminCourtier() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("adminCourtier");
		modelandview.addObject("lc", daoCourtier.findAll());
		modelandview.addObject("taillec", doac.findAll().size());

		
		List<Commande> l = doac.findAll();
		List<Commande> lc=new ArrayList<>();
		for(int i=0;i<l.size();i++) {
			if(l.get(i).getEtat_cmd().equals("Livrer")) {
				lc.add(l.get(i));
			}
		}
		
		modelandview.addObject("tailleAchat", lc.size());
		modelandview.addObject("tailleCourtier", daoCourtier.findAll().size());
		modelandview.addObject("taillePaiement", daoPaiement.findAll().size());


		return modelandview;
	}
	
	@RequestMapping("/adminClient")
	public ModelAndView adminClient() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("adminClient");
		modelandview.addObject("lc", daoclient.findAll());
		modelandview.addObject("taillec", doac.findAll().size());
		List<Commande> l = doac.findAll();
		List<Commande> lc=new ArrayList<>();
		for(int i=0;i<l.size();i++) {
			if(l.get(i).getEtat_cmd().equals("Livrer")) {
				lc.add(l.get(i));
			}
		}
		
		modelandview.addObject("tailleAchat", lc.size());
		modelandview.addObject("taillePaiement", daoPaiement.findAll().size());
		modelandview.addObject("tailleCourtier", daoCourtier.findAll().size());



		return modelandview;
}}
