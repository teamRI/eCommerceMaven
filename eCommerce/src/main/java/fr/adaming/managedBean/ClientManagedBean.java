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

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;


@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{clService}")
	private IClientService clSer;
	@ManagedProperty(value="#{caService}")
	private ICategorieService caSer;
	
	public void setClSer(IClientService clSer) {
		this.clSer = clSer;
	}

	public void setCaSer(ICategorieService caSer) {
		this.caSer = caSer;
	}

	private Client cl;
	private List<LigneCommande> pannier;
	private Commande co;
	private boolean i;
	HttpSession maSession;

	public ClientManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {
		this.cl = new Client();
		this.co = new Commande();

	}

	public Client getCl() {
		return cl;
	}

	public void setCl(Client cl) {
		this.cl = cl;
	}

	public List<LigneCommande> getPannier() {
		return pannier;
	}

	public void setPannier(List<LigneCommande> pannier) {
		this.pannier = pannier;
	}

	public Commande getCo() {
		return co;
	}

	public void setCo(Commande co) {
		this.co = co;
	}

	public boolean isI() {
		return i;
	}

	public void setI(boolean i) {
		this.i = i;
	}

	public String addClient() {
		Client clOut = clSer.addClient(this.cl);
		if (clOut != null) {
			FacesContext.getCurrentInstance().addMessage("SUCCESS",
					new FacesMessage("Bienvenue chez nous Mr!"+clOut.getNom()));
			return "acceuil";
		} else {
			FacesContext.getCurrentInstance().addMessage("FAILURE", new FacesMessage("l'ajout a �chou�!"));
			return "addclient";
		}
	}

	public String upDateClient() {
		Client clOut = clSer.upDateClient(cl);
		if (clOut != null) {
			FacesContext.getCurrentInstance().addMessage("SUCCESS", new FacesMessage("Les modification sont enregistr�!"));
			return "acceuil";
		} else {
			FacesContext.getCurrentInstance().addMessage("FAILURE", new FacesMessage("la modification a �chou�!"));
			return "updateclient";
		}
	}

	public String getClient() {
		Client clOut = clSer.getClient(cl);
		if (clOut != null) {
			i = true;
			this.cl = clOut;
			return "getclient";
		} else {
			i = false;
			FacesContext.getCurrentInstance().addMessage("FAILURE", new FacesMessage("la recherche a �chou�!"));
			return "getclient";
		}
	}

	public String deleteClient() {
		int verif = clSer.deleteClient(cl);
		if (verif != 0) {
			FacesContext.getCurrentInstance().addMessage("SUCCESS", new FacesMessage("Votre compte � bien �t� supprimer!"));
			return "acceuil";
		} else {
			FacesContext.getCurrentInstance().addMessage("FAILURE", new FacesMessage("la suppression a �chou�!"));
			return "deleteclient";
		}
	}
}
