package fr.adaming.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class Client {
	
	//1************************ATTRIBUTS*******************************
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cl")
	private Long id;
	private String nom;
	private String adresse;
	private String email;
	private String tel;
	
	@OneToOne(mappedBy="cl",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Commande Co;
	
	//2*************CONSTRUCTEURS***************************************************************
	
	
	
	public Client() {
		super();
	}



	public Client(String nom, String adresse, String email, String tel) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
	}



	public Client(Long id, String nom, String adresse, String email, String tel) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
	}

	//3*****************************GETTERS AND SETTERS*******************************************************

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTel() {
		return tel;
	}

	public Commande getCo() {
		return Co;
	}

	public void setCo(Commande co) {
		Co = co;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", email=" + email + ", tel=" + tel + "]";
	}

}
