package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "lcoMB")
@RequestScoped
public class LigneCommandeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;


	
	private LigneCommande lco;
	private List<LigneCommande> listelco;
	private Commande co;
	private Client cl;
	private boolean i;
	private Produit pr;
	HttpSession maSession;
	private double prixTotal;

	public LigneCommandeManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {
		this.lco = new LigneCommande();
		this.cl = new Client();
		this.co = new Commande();
		this.i = false;
		this.pr = new Produit();
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public LigneCommande getLco() {
		return lco;
	}

	public void setLco(LigneCommande lco) {
		this.lco = lco;
	}

	public List<LigneCommande> getListelco() {
		return listelco;
	}

	public void setListelco(List<LigneCommande> listelco) {
		this.listelco = listelco;
	}

	public Commande getCo() {
		return co;
	}

	public void setCo(Commande co) {
		this.co = co;
	}

	public Client getCl() {
		return cl;
	}

	public void setCl(Client cl) {
		this.cl = cl;
	}

	public boolean isI() {
		return i;
	}

	public void setI(boolean i) {
		this.i = i;
	}

	public Produit getPr() {
		return pr;
	}

	public void setPr(Produit pr) {
		this.pr = pr;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public String addLigneCommande() {
		this.cl = (Client) maSession.getAttribute("client");
		this.co.setCl(this.cl);
		if (cl.getCo() != null) {
			this.pr=prSer.getProduit(pr);
			this.lco.setCo(this.co);
			this.lco.setPr(pr);
			this.lco.setQuantiteCo(1);
			this.lco = lcoSer.addLigneCommande(this.lco);
			if (lco != null) {
				i = true;
				return "catetpr";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué!"));
				return "acceuil";
			}
		} else {
			Date dateAct = new Date();
			this.co.setDateCommande(dateAct);
			System.out.println("**************************************");
			System.out.println(co.getDateCommande());
			this.co = coSer.addCommande(co);
			System.out.println("**************************************");
			System.out.println(co.getId());
			this.pr=prSer.getProduit(pr);
			this.lco.setCo(this.co);
			this.lco.setPr(pr);
			this.lco.setQuantiteCo(1);
			this.lco = lcoSer.addLigneCommande(this.lco);
			if (lco != null) {
				i = true;
				return "catetpr";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué!"));
				return "acceuil";
			}
		}
	}

	public String deleteLigneCommande() {
		int verif = lcoSer.deleteLigneCommande(this.lco);
		if (verif != 0) {
			i = true;
			return "pannier";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a échoué!"));
			return "pannier";
		}
	}

	public String upDatePlusLigneCommande() {
		this.cl = (Client) maSession.getAttribute("client");
		int q = lcoSer.getLigneCommande(this.lco).getQuantiteCo();
		this.lco.setQuantiteCo(q + 1);
		this.lco = lcoSer.upDateLigneCommande(this.lco);
		System.out.println(q);
		if (this.lco != null) {
			this.listelco = lcoSer.getAllLigneCommandeByCo(cl.getCo());
			maSession.setAttribute("listlco", this.listelco);
			for (LigneCommande lco : this.listelco) {
				this.prixTotal = this.prixTotal + lco.getPrixfinal();
			}
			i = true;
			return "pannier";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la modification a échoué!"));
			return "pannier";
		}
	}

	public String upDateMoinsLigneCommande() {
		this.cl = (Client) maSession.getAttribute("client");
		int q = lcoSer.getLigneCommande(this.lco).getQuantiteCo();
		this.lco.setQuantiteCo(q - 1);
		this.lco = lcoSer.upDateLigneCommande(this.lco);
		System.out.println(q);
		if (this.lco != null) {
			this.listelco = lcoSer.getAllLigneCommandeByCo(cl.getCo());
			maSession.setAttribute("listlco", this.listelco);
			for (LigneCommande lco : this.listelco) {
				this.prixTotal = this.prixTotal + lco.getPrixfinal();
			}
			i = true;
			return "pannier";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la modification a échoué!"));
			return "pannier";
		}
	}

	public String getLigneCommande() {
		this.lco = lcoSer.getLigneCommande(this.lco);
		if (this.lco != null) {
			System.out.println(lco.getPr().getId());
			i = true;
			return "getlignecommande";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a échoué!"));
			return "getlignecommande";
		}
	}

	public String getAllLigneComandeByCo() {
		this.cl = (Client) maSession.getAttribute("client");
		try {
			this.listelco = lcoSer.getAllLigneCommandeByCo(cl.getCo());
			maSession.setAttribute("listlco", this.listelco);
			for (LigneCommande lco : this.listelco) {
				this.prixTotal = this.prixTotal + lco.getPrixfinal();
			}
			i = true;
			return "pannier";
		} catch (Exception e) {

		}
		return "loginCl";
	}

}
