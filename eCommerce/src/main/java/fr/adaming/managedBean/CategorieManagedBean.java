package fr.adaming.managedBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;


import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//1***********************UML EN JAVA***********************************************************************************
	@ManagedProperty(value="#{catService}")
	private ICategorieService catService;
	
	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}

	@ManagedProperty(value="#{prService}")
	private IProduitService prService;
	
	//2************************ATTRIBUTS***********************************************************************************
	
	
	private Categorie categorie;
	
	private List<Categorie> listCategorie;
	
	private List<Produit> listProduit;

	HttpSession adminSession;
	
	private UploadedFile file;
	
	private boolean i;
	
	private boolean prCat;
	
	
	//3*************************************CONSTRUCTEUR VIDE**************************************************************
	
	public CategorieManagedBean() {
		super();
	}
	
	//4*************************************OBJETS****************************************************************************
	
	@PostConstruct
	public void init(){
		this.adminSession= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		adminSession.getAttribute("verifSession");
		this.categorie= new Categorie();
		this.listCategorie= catService.getAllCategorie();
			this.file= new UploadedFile() {
				
				@Override
				public void write(String arg0) throws Exception {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public long getSize() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public InputStream getInputstream() throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getFileName() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public byte[] getContents() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getContentType() {
					// TODO Auto-generated method stub
					return null;
				}
			};
			
		i=false;
	}


	
	
	//5************************************GETTERS AND SETTERS**********************************************************
	
		public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}
	
	public HttpSession getAdminSession() {
		return adminSession;
	}

	public void setAdminSession(HttpSession adminSession) {
		this.adminSession = adminSession;
	}
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	
	public List<Categorie> getListCategorie() {
		return listCategorie;
	}

	public void setListCategorie(List<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}
	
	
	public boolean isI() {
		return i;
	}

	public void setI(boolean i) {
		this.i = i;
	}
	
	
	public boolean isPrCat() {
		return prCat;
	}

	public void setPrCat(boolean prCat) {
		this.prCat = prCat;
	}
	
	//6*****************************************AUTRES METHODES*************************************************************
	




	public String addCategorie() {
	   this.categorie.setPhoto(file.getContents());
		Categorie c= catService.addCategorie(categorie);
		
		if(c.getId()!=0) {
			i=true;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catListe", catService.getAllCategorie());
		
		return "acceuil";
		
			
		}else {
FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'est pas fait"));
			i=false;
			return "addcategorie";
		}
	}

	public String getCategories() {
		this.categorie= catService.getCategorie(categorie);
		
		if(this.categorie!=null) {
			i=true;
			return "getcategorie";
		}else {
			
			i=false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La categorie n'existe pas"));
			return "getcategorie";
		}
	}
	
	public String upDateLienCategorie() {
		return "updatecategorie";
	}
	
	public String upDateCategorie() {
		this.categorie.setPhoto(file.getContents());
		Categorie cOut= catService.upDateCategorie(this.categorie);
		if(cOut!=null) {
			List<Categorie> list= catService.getAllCategorie();
			i=true;
			this.listCategorie=list;
			return "acceuil";
		}else {
			
			i=false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La Categorie n'est pas modifié"));
			return "updatecategorie";
		}
	}
	
	public String deleteCategorie() {
		catService.delateCategorie(this.categorie);
		if(this.categorie!=null) {
			List<Categorie> list= catService.getAllCategorie();
			this.listCategorie= list;
			
			return "acceuil";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La categorie n'est pas effacé"));
			return "deletecategorie";
		}
	}
	
	public String getListProduits() {
		prCat=true;
		this.listProduit= prService.getAllProduit(this.categorie);
		return "catetpr";
	}
}
