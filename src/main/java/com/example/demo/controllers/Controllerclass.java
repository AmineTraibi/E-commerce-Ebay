package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.Dao;
import com.example.demo.dao.DaoClient;
import com.example.demo.dao.DaoCommande;
import com.example.demo.dao.DaoCourtier;
import com.example.demo.dao.DaoLigneCommande;
import com.example.demo.entity.Client;
import com.example.demo.entity.Commande;
import com.example.demo.entity.Courtier;
import com.example.demo.entity.Ligne_commande;
import com.example.demo.entity.Panier;
import com.example.demo.entity.Produit;






@RestController
@SessionAttributes("client")
public class Controllerclass {
	
	static double total=0;
	/*@Autowired
	private FormClientValidator validator;*/
	
	@Autowired
	private Dao dao;
	
	@Autowired
	private DaoLigneCommande daolignec;
	
	@Autowired
	private DaoClient daocl;
	
	@Autowired
	private DaoCourtier daocr;
	
	@Autowired
	private DaoCommande daoc;
	
	@Autowired
	private daoEbay dao2;
	
	@Autowired 
	private DaoCourtier daoCourtier;
	/*@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}*/
	
	
	/*
	@RequestMapping(value="/authentification", method=RequestMethod.POST)
	public String ajout_produit(Model m,@ModelAttribute("client") @Validated Client client,BindingResult result){

		if(result.hasErrors()) {
			return "index";
		}
	    return "lproduit";
	}
	*/
	  @RequestMapping("/")
	  public ModelAndView home() {
		System.out.println("hhhhhhhhhhhhhhhhhhhh");
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("index");
		return modelandview;
	    }
	  
	  
	  
	  @RequestMapping(value="/lproduit",
	  method=RequestMethod.GET)
	  public ModelAndView index(HttpServletRequest request,@RequestParam("keyword") String id){
	   		    //request.getSession().setAttribute("key", id);
	  ModelAndView modelandview = new ModelAndView();
	  modelandview.setViewName("lesproduits");
	  modelandview.addObject("key", id);
	  List<Produit>l=dao2.listeproduit(id);
	  request.getSession().setAttribute("list", l);
	  modelandview.addObject("lp",l);
	  return modelandview;
	   	}
	  
	  
	  @RequestMapping(value="/showproduit")
	  public ModelAndView showprd(HttpServletRequest request,@RequestParam("idp") String id){
	  Client clt=(Client) request.getSession().getAttribute("client");
	  if(clt == null) {
	  ModelAndView modelandview = new ModelAndView();
	  @SuppressWarnings("unchecked")
	  List<Produit>l=(List<Produit>) request.getSession().getAttribute("list");
	  Produit prd=new Produit();
	  for(int i=0;i<l.size();i++) {
	     if(l.get(i).getId_produit().equals(id)) {
				prd=l.get(i);break;
			}
		}
		modelandview.setViewName("detailproduit");
		modelandview.addObject("prd", prd);

		return modelandview;}
	  else {
		  ModelAndView modelandview1 = new ModelAndView();
		  @SuppressWarnings("unchecked")
		  List<Produit>l=(List<Produit>) request.getSession().getAttribute("list");
		  Produit prd=new Produit();
		  for(int i=0;i<l.size();i++) {
		     if(l.get(i).getId_produit().equals(id)) {
					prd=l.get(i);break;
				}
			}
			modelandview1.setViewName("showProduitClt");
			modelandview1.addObject("prd", prd);
			modelandview1.addObject("taille", Panier.getItems().size());
			modelandview1.addObject("taillecmd",clt.getCommandes().size());
			

			return modelandview1;
	  }
			}
	  
	  
	   @RequestMapping(value="/addtopanier")
		public ModelAndView panier(HttpServletRequest request,@RequestParam("idp") String id,@RequestParam("quantity") String t){
	    ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("panier");
		double mt=0;
		Client clt=(Client) request.getSession().getAttribute("client");
		if(clt!=null) {
		@SuppressWarnings("unchecked")
		List<Produit>l=(List<Produit>) request.getSession().getAttribute("list");
		
		for(int i=0;i<l.size();i++) {
			if(l.get(i).getId_produit().equals(id)) {
				System.out.println("********************yyyyyyyyyyyyy"+l.get(i).getTitre_produit());
				Panier.AddItem(l.get(i),Integer.parseInt(t));break;
			}
		}
		//Map<String,Ligne_commande> lp=Panier.getItems();
        for(Ligne_commande lc : Panier.getItems().values()) {
      	double prix=Double.parseDouble(lc.getPk().getPrd().getPrix_produit());
    	double a=(lc.getQte())*(prix);
    	mt=mt+a;
    	
        }			
        modelandview.addObject("lpanier", Panier.getItems().values());
        modelandview.addObject("total", mt);
        modelandview.addObject("ttc",(mt*10)/100);
        modelandview.addObject("tot_ttc",total+mt*1.1);
		modelandview.addObject("taille", Panier.getItems().size());
		modelandview.addObject("taillecmd",clt.getCommandes().size());
		//modelandview.addObject("list", p.getItems());
		//Ligne_commande lc=p.getItems().get(d.ExisteProduit(key, id).getId_produit());
		
		//il faux chercher le produit par id et le recuperer pour ajouter additem(produit)
		//et cree un variable session ceette panier 'la liste'
		
		return modelandview;
		}
		else 
		{
			ModelAndView modelandview1 = new ModelAndView();
			modelandview1.setViewName("login");
			return modelandview1;
		}
		}
	  
	  
	    
	   @RequestMapping(value="/register")
		public ModelAndView register(){
	    ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("register");
		return modelandview;
			}
	   
	   
	   @RequestMapping(value="/gologin")
		public ModelAndView gologin(){
	    ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("login");
		return modelandview;
			}	
	   
	   @RequestMapping(value="/contact")
		public ModelAndView about(){
	    ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("contact_us");
		return modelandview;
			}
	   
	   @RequestMapping(value="/team")
		public ModelAndView team(){
	    ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("team");
		return modelandview;
			}
	   
	   
	   
		@RequestMapping(value="/adduser")
		public ModelAndView adduser(@RequestParam("nom") String nom,HttpServletRequest request,
				                   @RequestParam("prenom") String prenom ,
				                   @RequestParam("adresse") String adresse,
				                   @RequestParam("numcin") String cin,
				                   @RequestParam("typecin") String typecin,
				                   @RequestParam("nationalite") String nat,
				                   @RequestParam("telephone") String tel,
				                   @RequestParam("login") String login,
				                   @RequestParam("password") String password){
		if(nat==""&&tel=="") 
		{
		ModelAndView modelandview = new ModelAndView();
		Client clt=new Client(login, nom, prenom,typecin, cin, password, adresse);
		daocl.save(clt);
		modelandview.addObject("client", clt);
		modelandview.setViewName("InterfaceClient");
		List<Produit>l=dao2.listeproduit("table");
		request.getSession().setAttribute("list", l);
		modelandview.addObject("lp", l);
		modelandview.addObject("taille", Panier.getItems().size());
		modelandview.addObject("taillecmd",clt.getCommandes().size());
		return modelandview;
		               
		}
		ModelAndView modelandview1 = new ModelAndView();
		Courtier crt=new Courtier(nom, prenom, tel, typecin, cin, 0, adresse, login, password);
		daocr.save(crt);
		modelandview1.addObject("courtier", crt);
		modelandview1.setViewName("administationCourtier");
		List<Commande> l=daoc.findAll();
		List<Commande> lc=new ArrayList<>();
	    for(int i=0;i<l.size();i++) {
	    	if(l.get(i).getEtat_cmd().equals("En cours")) {
	    		lc.add(l.get(i));
	    	}
	        }
	    modelandview1.addObject("courtier",crt);
	    request.getSession().setAttribute("courtier", crt);
	    modelandview1.addObject("lc", lc);
		modelandview1.addObject("taillec", lc.size());
		modelandview1.addObject("mescmd", crt.getCommandes().size());
		modelandview1.addObject("solde",crt.getSolde());
		return modelandview1;
			} 

		@RequestMapping(value="/chechuser")
		public ModelAndView authentification(HttpServletRequest request,@RequestParam("email") String login,@RequestParam("password") String password){
	    ModelAndView modelandview;
	    ModelAndView modelandview1;
	    ModelAndView modelandview2;
	    
	    if(daocl.existsById(login)) {
	    	Client clt=daocl.getOne(login);
	    	if(clt.getPassword().equals(password)) {
	        modelandview= new ModelAndView();
		    modelandview.setViewName("InterfaceClient");
			List<Produit>l=dao2.listeproduit("table");
			request.getSession().setAttribute("list", l);
			modelandview.addObject("client", clt);
			modelandview.addObject("lp", l);
			modelandview.addObject("taille", Panier.getItems().size());
			modelandview.addObject("taillecmd",clt.getCommandes().size());
			return modelandview;
			}
	        else { 
	    	modelandview1= new ModelAndView();
	 	    modelandview1.setViewName("login");
		    return modelandview1;
	        }
			}
	    
	    
	    else if(daoCourtier.existsById(login)) {
	    	Courtier crt=daoCourtier.getOne(login);
	    	if(crt.getPassword().equals(password)) {
	    		modelandview2= new ModelAndView();
			    modelandview2.setViewName("administationCourtier");
			    List<Commande> l=daoc.findAll();
				List<Commande> lc=new ArrayList<>();
			    for(int i=0;i<l.size();i++) {
			    	if(l.get(i).getEtat_cmd().equals("En cours")) {
			    		lc.add(l.get(i));
			    	}
	    	        }
			    modelandview2.addObject("courtier",crt);
			    request.getSession().setAttribute("courtier", crt);
			    modelandview2.addObject("lc", lc);
				modelandview2.addObject("taillec", lc.size());
				modelandview2.addObject("mescmd", crt.getCommandes().size());
				modelandview2.addObject("solde",crt.getSolde());
				return modelandview2;
		    }
	    	else { 
		    	modelandview1= new ModelAndView();
		 	    modelandview1.setViewName("login");
			    return modelandview1;
		        }
	    }
	    modelandview1= new ModelAndView();
 	    modelandview1.setViewName("login");
	    return modelandview1;
		}
	       
		
		@RequestMapping(value="/lproduits")
		public ModelAndView retour(HttpServletRequest request){
		Client clt=(Client)request.getSession().getAttribute("client");
		if(clt==null) {
	 //request.getSession().setAttribute("key", id);
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("lesproduits");
		@SuppressWarnings("unchecked")
		List<Produit>l=(List<Produit>) request.getSession().getAttribute("list");
		modelandview.addObject("lp",l);
		return modelandview;
		  }
		 else {
		ModelAndView modelandview1 = new ModelAndView();
		modelandview1.setViewName("InterfaceClient");
		@SuppressWarnings("unchecked")
		List<Produit>l=(List<Produit>) request.getSession().getAttribute("list");
		modelandview1.addObject("lp",l);
		modelandview1.addObject("taille", Panier.getItems().size());
		modelandview1.addObject("taillecmd",clt.getCommandes().size());
		return modelandview1;
		    }
			}
	
	 
	      
	
	
	      
	
	@RequestMapping(value="/deleteproduit")
	public ModelAndView deleteproduit(@RequestParam("idp") String id,@SessionAttribute("client") Client clt){
		double mt=0;
    ModelAndView modelandview = new ModelAndView();
    Panier.DeleteItem(id);
	modelandview.setViewName("panier");
	for(Ligne_commande lc : Panier.getItems().values()) {
      	double prix=Double.parseDouble(lc.getPk().getPrd().getPrix_produit());
    	double a=(lc.getQte())*(prix);
    	mt=mt+a;
    	
        }			
        modelandview.addObject("lpanier", Panier.getItems().values());
        modelandview.addObject("total", mt);
        modelandview.addObject("ttc",(mt*10)/100);
        modelandview.addObject("tot_ttc",total+mt*1.1);
	modelandview.addObject("taille",Panier.getItems().size());
	modelandview.addObject("taillecmd",clt.getCommandes().size());
	
	return modelandview;
		}

	
    

	

	
	@RequestMapping(value="/afficherpanier")
	public ModelAndView showpanier(@SessionAttribute("client") Client clt){
		double mt=0;
    Map<String,Ligne_commande> l=Panier.getItems();	        
    ModelAndView modelandview = new ModelAndView();
    modelandview.setViewName("panier");
    for(Ligne_commande lc : Panier.getItems().values()) {
      	double prix=Double.parseDouble(lc.getPk().getPrd().getPrix_produit());
    	double a=(lc.getQte())*(prix);
    	mt=mt+a;
    	
        }			
        modelandview.addObject("lpanier", Panier.getItems().values());
        modelandview.addObject("total", mt);
        modelandview.addObject("ttc",(mt*10)/100);
        modelandview.addObject("tot_ttc",total+mt*1.1);
	modelandview.addObject("taille",Panier.getItems().size());
	modelandview.addObject("taillecmd",clt.getCommandes().size());
	return modelandview;
		}
	
	
	@RequestMapping(value="/passercommande")
	public ModelAndView passercommande(@SessionAttribute("client") Client clt){
	LocalDate localDate;
	double mt=0;
	Commande c =new Commande(LocalDate.now());
	c.setAdresselivraison("test");
	c.setEtat_cmd("à payer");
	c.setClient(clt);
	System.out.println("on est laaaaaaaaaa");
    daoc.save(c);
    for(Ligne_commande lc : Panier.getItems().values()) {
    	lc.setDescription(lc.getPk().getPrd().getTitre_produit());
    	//int qte=lc.getQte();
    	dao.save(lc.getPk().getPrd());
    	lc.getPk().setPrd(lc.getPk().getPrd());
    	lc.getPk().setCmd(c);
    	double prix=Double.parseDouble(lc.getPk().getPrd().getPrix_produit());
    	double a=(lc.getQte())*(prix);
    	mt=mt+a;
    	lc.getPk().getCmd().setMontant(mt);
    	daolignec.save(lc);
           }
    Panier.clearpanier();
	ModelAndView modelandview = new ModelAndView();
	modelandview.setViewName("mescommandes");
	modelandview.addObject("lpanier",Panier.getItems().values());
	modelandview.addObject("taille",Panier.getItems().size());
	modelandview.addObject("taillecmd",clt.getCommandes().size());
	return modelandview;
			}
	
	
	
	@RequestMapping(value="/mescommandes")
	public ModelAndView mesCmd(@SessionAttribute("client") Client clt){
    ModelAndView modelandview = new ModelAndView();
    modelandview.setViewName("mescommandes");
    modelandview.addObject("lc",clt.getCommandes());
	modelandview.addObject("taille",Panier.getItems().size());
	modelandview.addObject("taillecmd",clt.getCommandes().size());
	return modelandview;
		}
	
	@RequestMapping(value="/detailcommande")
	public ModelAndView detailCmd(@SessionAttribute("client") Client clt,@RequestParam("idc") int code){
    ModelAndView modelandview = new ModelAndView();
    modelandview.setViewName("detailcommandes");
    Commande cmd=daoc.getOne(code);
    modelandview.addObject("ligne",cmd.getCnt());
    System.out.println("je suissssssssssssssss laa"+cmd.getCnt().get(0).getPk().getPrd().getId_produit());
	modelandview.addObject("taille",Panier.getItems().size());
	modelandview.addObject("taillecmd",clt.getCommandes().size());
	return modelandview;
		}
}
