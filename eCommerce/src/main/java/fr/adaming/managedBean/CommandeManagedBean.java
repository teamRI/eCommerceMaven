package fr.adaming.managedBean;

import java.io.Serializable;
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
import fr.adaming.model.CreatePdf;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Mail;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "coMB")
@RequestScoped
public class CommandeManagedBean implements Serializable {

	@ManagedProperty(value = "#{coService}")
	private ICommandeService coSer;
	@ManagedProperty(value = "#{lcoService}")
	private ILigneCommandeService lcoSer;
	@ManagedProperty(value = "#{prService}")
	private IProduitService prSer;

	private static final long serialVersionUID = 1L;

	Mail envoyermail = new Mail();

	CreatePdf createpdf = new CreatePdf();

	private Commande co;
	private Client cl;
	private List<LigneCommande> listeLco;
	private boolean i;
	HttpSession maSession;

	public CommandeManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {
		this.cl = new Client();
		this.co = new Commande();
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		i = false;

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

	public List<LigneCommande> getListeLco() {
		return listeLco;
	}

	public void setListeLco(List<LigneCommande> listeLco) {
		this.listeLco = listeLco;
	}

	public boolean isI() {
		return i;
	}

	public void setI(boolean i) {
		this.i = i;
	}

	public void setCoSer(ICommandeService coSer) {
		this.coSer = coSer;
	}

	public void setLcoSer(ILigneCommandeService lcoSer) {
		this.lcoSer = lcoSer;
	}

	public void setPrSer(IProduitService prSer) {
		this.prSer = prSer;
	}

	public String addCommande() {
		this.co.setCl(this.cl);
		this.co = coSer.addCommande(this.co);
		if (co != null) {
			i = true;
			return "acceuil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué!"));
			return "addcommande";
		}
	}

	public String deleteCommande() {
		List<LigneCommande> listlco = (List<LigneCommande>) maSession.getAttribute("listlco");
		for (LigneCommande lco : listlco) {

			int verif1 = lcoSer.deleteLigneCommande(lco);
		}
		int verif = coSer.deleteCommande(this.co);
		if (verif != 0) {
			i = true;
			return "acceuil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a échoué!"));
			return "pannier";
		}
	}

	public String upDateCommande() {
		this.co.setCl(this.cl);
		this.co = coSer.upDateCommande(this.co);
		if (this.co != null) {
			i = true;
			return "acceuil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la modification a échoué!"));
			return "updatecommande";
		}
	}

	public String getCommande() {
		this.co = coSer.getCommande(this.co);
		if (this.co != null) {
			System.out.println(co);
			i = true;
			return "getcommande";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a échoué!"));
			return "getcommande";
		}
	}

	public String getAllComandeByCl() {
		this.co = coSer.getAllCommandeByCl(this.cl);
		if (this.co != null) {
			i = true;
			return "getallcommande";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a échoué!"));
			return "getallcommande";
		}

	}

	public String validerCommande() {
		this.cl = (Client) maSession.getAttribute("client");
		this.co = cl.getCo();
		List<LigneCommande> listlco = (List<LigneCommande>) maSession.getAttribute("listlco");
		for (LigneCommande lco : listlco) {
			Produit prOut= prSer.getProduit(lco.getPr());
			prOut.setQuantite(prOut.getQuantite()-lco.getQuantiteCo());
			prSer.upDateProduit(prOut);
			int verif1 = lcoSer.deleteLigneCommande(lco);
		}
		int verif = coSer.deleteCommande(this.co);

		if (verif != 0) {
			envoyermail.sendMail(co.getCl(), co, listlco);
			i = true;
			return "acceuil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a échoué!"));
			return "pannier";
		}
	}
}
