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
import fr.adaming.service.IFormateurService;

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
			List<Categorie> catliste = caSer.getAllCategorie();
			maSession.setAttribute("catliste", catliste);
			return "acceuil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué!"));
			return "addclient";
		}
	}

	public String upDateClient() {
		Client clOut = clSer.upDateClient(cl);
		if (clOut != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le client a été modifier!"));
			return "updateclient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la modification a échoué!"));
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a échoué!"));
			return "getclient";
		}
	}

	public String deleteClient() {
		int verif = clSer.deleteClient(cl);
		if (verif != 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le client a été supprimé!"));
			return "deleteclient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a échoué!"));
			return "deleteclient";
		}
	}
}
