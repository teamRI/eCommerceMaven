package fr.adaming.managedBean;

import java.io.Serializable;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="aMB")
@SessionScoped
public class AdminManagedBean implements Serializable{
	
	//1************************ATTRIBUTS***********************************************************************************

	private static final long serialVersionUID = 1L;

	private String login;
	
	private String password;


	
	//2*************************************CONSTRUCTEUR VIDE**************************************************************
	
		public AdminManagedBean() {
		super();
	}
		
		
	//4************************************GETTERS AND SETTERS**********************************************************	
		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		
	//5*****************************************AUTRES METHODES*************************************************************
		
		public String loginAdmin() {
			
			if(login.equals("admin") && password.equals("admin")) {
				
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("verifSession", true);
			
				return "acceuil";
				
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le login ou mot de passe erroné(s)"));
				return "login";
			}
		

			}
		
		public String logoutAdmin() {
			
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return "acceuil";
		}
		}
		

