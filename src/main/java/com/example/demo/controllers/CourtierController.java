package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.DaoCommande;
import com.example.demo.dao.DaoCourtier;
import com.example.demo.entity.Commande;
import com.example.demo.entity.Courtier;

@RestController
@SessionAttributes("courtier")
public class CourtierController {

	@Autowired
	private DaoCommande doac;
	
	@Autowired 
	private DaoCourtier daoCourtier;
	
	
	
	
	
	
	
	  
	
	
	
	

	
	@RequestMapping("/interCourtier")
	public ModelAndView adminCourtier(@SessionAttribute("courtier") Courtier crt) {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("administationCourtier");
		List<Commande> l=doac.findAll();
		List<Commande> lc=new ArrayList<>();
	    for(int i=0;i<l.size();i++) {
	    	if(l.get(i).getEtat_cmd().equals("En cours")) {
	    		lc.add(l.get(i));
	    	}
	    }
	    modelandview.addObject("courtier",crt);
	    modelandview.addObject("lc", lc);
		modelandview.addObject("taillec", lc.size());
		modelandview.addObject("mescmd", crt.getCommandes().size());
		modelandview.addObject("solde",crt.getSolde());


		return modelandview;
	}
	
	@RequestMapping(value="/voircommande")
	public ModelAndView detailCmd(@SessionAttribute("courtier") Courtier crt,@RequestParam("idc") int code){
    ModelAndView modelandview = new ModelAndView();
    modelandview.setViewName("AdminCourtierDetails");
    Commande cmd=doac.getOne(code);
    modelandview.addObject("ligne",cmd.getCnt());
    List<Commande> l=doac.findAll();
	List<Commande> lc=new ArrayList<>();
    for(int i=0;i<l.size();i++) {
    	if(l.get(i).getEtat_cmd().equals("En cours")) {
    		lc.add(l.get(i));
    	}
    }
	modelandview.addObject("taillec", lc.size());
	modelandview.addObject("ref", cmd.getRef());
	modelandview.addObject("mescmd", crt.getCommandes().size());
	modelandview.addObject("solde",crt.getSolde());
	return modelandview;
		}
	
	@RequestMapping("/modifiercommande")
    public ModelAndView updatecmd(@SessionAttribute("courtier") Courtier crt,@RequestParam("idc") String id
    		                      ) {
	ModelAndView modelandview = new ModelAndView();
	modelandview.setViewName("administationCourtier");
	Commande cmd=doac.getOne(Integer.parseInt(id));
	
		cmd.setEtat_cmd("Affecter");
		cmd.setCourtier(crt);
		doac.save(cmd);
		
	List<Commande> l=doac.findAll();
	List<Commande> lc=new ArrayList<>();
    for(int i=0;i<l.size();i++) {
    	if(l.get(i).getEtat_cmd().equals("En cours")) {
    		lc.add(l.get(i));
    	}
    }
    modelandview.addObject("lc", lc);
	modelandview.addObject("taillec", lc.size());
	modelandview.addObject("mescmd", crt.getCommandes().size());
	modelandview.addObject("solde",crt.getSolde());


	return modelandview;
    }
	
	@RequestMapping("/modifier2commande")
    public ModelAndView update2cmd(@SessionAttribute("courtier") Courtier crt,@RequestParam("idc") String id
    		                      ) {
		
	ModelAndView modelandview = new ModelAndView();
	modelandview.setViewName("cmdCourtier");
	Commande cmd=doac.getOne(Integer.parseInt(id));
	if(cmd.getEtat_cmd().equals("En cours de livraison")) {
		ModelAndView modelandview1 = new ModelAndView();
		modelandview1.setViewName("cmdCourtier");
		List<Commande> l=doac.findAll();
		List<Commande> lc=new ArrayList<>();
	    for(int i=0;i<l.size();i++) {
	    	if(l.get(i).getEtat_cmd().equals("En cours")) {
	    		lc.add(l.get(i));
	    	}
	    }
	    modelandview1.addObject("lc", crt.getCommandes());
		modelandview1.addObject("taillec", lc.size());
		modelandview1.addObject("mescmd", crt.getCommandes().size());
		modelandview1.addObject("solde",crt.getSolde());


		return modelandview1;
	}
	else {
		cmd.setEtat_cmd("En cours de livraison");
		//cmd.setCourtier(crt);
		doac.save(cmd);
		crt.setSolde(crt.getSolde()+cmd.getMontant());
		daoCourtier.save(crt);
	List<Commande> l=doac.findAll();
	List<Commande> lc=new ArrayList<>();
    for(int i=0;i<l.size();i++) {
    	if(l.get(i).getEtat_cmd().equals("En cours")) {
    		lc.add(l.get(i));
    	}
    }
    modelandview.addObject("lc", crt.getCommandes());
	modelandview.addObject("taillec", lc.size());
	modelandview.addObject("mescmd", crt.getCommandes().size());
	modelandview.addObject("solde",crt.getSolde());


	return modelandview;
	}
    }
	
	@RequestMapping("/Courtiermescommande")
    public ModelAndView mscmd(@SessionAttribute("courtier") Courtier crt) {
	ModelAndView modelandview = new ModelAndView();
	modelandview.setViewName("cmdCourtier");	
	List<Commande> l=doac.findAll();
	List<Commande> lc=new ArrayList<>();
	System.out.println("waaaaaaaaaaaaaaaaaaaa"+crt.getLogin_crt());
	for(int i=0;i<l.size();i++) {
    	if(l.get(i).getEtat_cmd().equals("En cours")) {
    		lc.add(l.get(i));
    	}
    }
    System.out.println("avant");
    modelandview.addObject("lc", crt.getCommandes());
  
	modelandview.addObject("taillec", lc.size());
	modelandview.addObject("mescmd", crt.getCommandes().size());
	modelandview.addObject("solde",crt.getSolde());


	return modelandview;
    }
	}
