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
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "acMB")
@RequestScoped
public class AcceuilManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{clService}")
	private IClientService clSer;
	@ManagedProperty(value="#{caService}")
	private ICategorieService caSer;
	@ManagedProperty(value="#{prService}")
	private IProduitService prSer;

	private Client cl;
	private boolean i;
	private List<Produit> prodliste;
	private List<Categorie> catliste;
	private int size;
	HttpSession maSession;

	public AcceuilManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {
		this.cl = new Client();
		this.i = false;
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
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

	public List<Produit> getProdliste() {
		return prodliste;
	}

	public void setProdliste(List<Produit> prodliste) {
		this.prodliste = prodliste;
	}

	public List<Categorie> getCatliste() {
		return catliste;
	}

	public void setCatliste(List<Categorie> catliste) {
		this.catliste = catliste;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	public void setClSer(IClientService clSer) {
		this.clSer = clSer;
	}

	public void setCaSer(ICategorieService caSer) {
		this.caSer = caSer;
	}

	public void setPrSer(IProduitService prSer) {
		this.prSer = prSer;
	}

	public String Entrer() {

		catliste = caSer.getAllCategorie();
		size = catliste.size() - 1;
		for (int j = 0; j <= size; j++) {
			prodliste = prSer.getAllProduit(catliste.get(j));
			maSession.setAttribute("prodliste" + j, this.prodliste);
		}
		maSession.setAttribute("catliste", this.catliste);
		return "acceuil";
	}

	public String login() {
		Client clOut = clSer.isExist(this.cl);
		this.cl = clOut;
		System.out.println(this.cl.getId());
		if (clOut != null) {
			i=true;
			maSession.setAttribute("icl", this.i);
			maSession.setAttribute("client", this.cl);
			catliste = caSer.getAllCategorie();
			maSession.setAttribute("catliste", this.catliste);
			return "acceuil";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email et/ou nom erroné(s)"));
		return "loginCl";
	}
}
