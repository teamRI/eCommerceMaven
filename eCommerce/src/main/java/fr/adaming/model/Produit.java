package fr.adaming.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="products")
public class Produit {

	//1************************ATTRIBUTS***************************************************
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pr")
	private long id;
	private String designation;
	private String description;
	private double prix;
	private int quantite;
	
	
	@Lob
	private byte[] photo;
	
	@Transient
	private String image;
	
	
	   //1.1******************Relations*****************************
	
	   @ManyToOne
	   @JoinColumn(name="cat_id",referencedColumnName="id_cat")
       private Categorie categorie;
	   
	   @OneToMany(mappedBy="pr")
	   private List<LigneCommande> lignesCommandes;
	
	//2*************CONSTRUCTEURS***************************************************************
	
	public Produit() {
		super();
	}


	public Produit(String designation, String description, double prix, int quantite, byte[] photo) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.photo = photo;

	}



	public Produit(long id, String designation, String description, double prix, int quantite, byte[] photo) {
		super();
		this.id = id;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.photo = photo;
	}


	//3*****************************GETTERS AND SETTERS*******************************************************
	
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public List<LigneCommande> getLignesCommandes() {
		return lignesCommandes;
	}


	public void setLignesCommandes(List<LigneCommande> lignesCommandes) {
		this.lignesCommandes = lignesCommandes;
	}




	
	
	
	
	
}
