package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "lcoMB")
@RequestScoped
public class LigneCommandeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{coService}")
	private ICommandeService coSer;
	@ManagedProperty(value = "#{caService}")
	private ICategorieService caSer;
	@ManagedProperty(value = "#{prService}")
	private IProduitService prSer;
	@ManagedProperty(value = "#{lcoService}")
	private ILigneCommandeService lcoSer;

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

	public void setCoSer(ICommandeService coSer) {
		this.coSer = coSer;
	}

	public void setCaSer(ICategorieService caSer) {
		this.caSer = caSer;
	}

	public void setPrSer(IProduitService prSer) {
		this.prSer = prSer;
	}

	public void setLcoSer(ILigneCommandeService lcoSer) {
		this.lcoSer = lcoSer;
	}

	public String addLigneCommande() {
		System.out.println("je suis dans la methode");
		this.cl = (Client) maSession.getAttribute("client");
		System.out.println(this.cl.getId());
		this.co = coSer.getAllCommandeByCl(this.cl);
		System.out.println(this.co);
		if (this.co != null) {
			this.pr = prSer.getProduit(pr);
			this.lco.setCo(this.co);
			this.lco.setPr(pr);
			this.lco.setQuantiteCo(1);
			this.lco = lcoSer.addLigneCommande(this.lco);
			if (lco != null) {
				FacesContext.getCurrentInstance().addMessage("SUCCESS",
						new FacesMessage("le produit à bien été ajouter au pannier!"));
				i = true;
				return "catetpr";
			} else {
				FacesContext.getCurrentInstance().addMessage("FAILURE", new FacesMessage("l'ajout a échoué!"));
				return "acceuil";
			}
		} else {
			this.co = new Commande();
			this.co.setCl(this.cl);
			Date dateAct = new Date();
			this.co.setDateCommande(dateAct);
			System.out.println("**************************************");
			System.out.println(co.getDateCommande());
			this.co = coSer.addCommande(co);
			System.out.println("**************************************");
			System.out.println(co.getId());
			this.pr = prSer.getProduit(pr);
			this.lco.setCo(this.co);
			this.lco.setPr(pr);
			this.lco.setQuantiteCo(1);
			this.lco = lcoSer.addLigneCommande(this.lco);
			if (lco != null) {
				this.cl.setCo(this.co);
				maSession.setAttribute("client", this.cl);
				FacesContext.getCurrentInstance().addMessage("SUCCESS",
						new FacesMessage("le produit à bien été ajouter au pannier!"));
				i = true;
				return "catetpr";
			} else {
				FacesContext.getCurrentInstance().addMessage("FAILURE", new FacesMessage("l'ajout a échoué!"));
				return "acceuil";
			}
		}
	}

	public String deleteLigneCommande() {
		int verif = lcoSer.deleteLigneCommande(this.lco);
		if (verif != 0) {
			FacesContext.getCurrentInstance().addMessage("SUCCESS",
					new FacesMessage("la suppression à bien été prise en compte!"));
			return "pannier";
		} else {
			FacesContext.getCurrentInstance().addMessage("FAILURE", new FacesMessage("la suppression a échoué!"));
			return "pannier";
		}
	}

	public String upDatePlusLigneCommande() {
		this.cl = (Client) maSession.getAttribute("client");
		this.lco = lcoSer.getLigneCommande(this.lco);
		int q = this.lco.getQuantiteCo();
		this.pr = prSer.getProduit(lco.getPr());
		int qpr = this.pr.getQuantite();
		if (q + 1 <= qpr) {
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
				FacesContext.getCurrentInstance().addMessage("FAILURE", new FacesMessage("l'ajout a échoué!"));
				return "pannier";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("FAILURE", new FacesMessage("le stock n'est pas suffisant!"));
			return "pannier";
		}
	}

	public String upDateMoinsLigneCommande() {
		this.cl = (Client) maSession.getAttribute("client");
		this.lco = lcoSer.getLigneCommande(this.lco);
		int q = this.lco.getQuantiteCo();
		this.pr = prSer.getProduit(lco.getPr());
		if (q - 1 >= 0) {
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
				FacesContext.getCurrentInstance().addMessage("FAILURE", new FacesMessage("la modification a échoué!"));
				return "pannier";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("FAILURE",
					new FacesMessage("la quantité ne peut pas être inferieur à 0!"));
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
			FacesContext.getCurrentInstance().addMessage("FAILURE", new FacesMessage("la recherche a échoué!"));
			return "getlignecommande";
		}
	}

	public String getAllLigneComandeByCo() {
		this.cl = (Client) maSession.getAttribute("client");
		try {
			if (this.cl != null) {
				this.listelco = lcoSer.getAllLigneCommandeByCo(cl.getCo());
				maSession.setAttribute("listlco", this.listelco);
				for (LigneCommande lco : this.listelco) {
					this.prixTotal = this.prixTotal + lco.getPrixfinal();
				}
				i = true;
				return "pannier";
			} else {
				FacesContext.getCurrentInstance().addMessage("FAILURE",
						new FacesMessage("vous devez vous connecter avant d'accéder à votre pannier!"));
				return "loginCl";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage("FAILURE",
				new FacesMessage("vous devez vous connecter avant d'accéder à votre pannier!"));
		return "loginCl";

	}

}
