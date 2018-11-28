package fr.adaming.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="lignesCommandes")
public class LigneCommande {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_lco")
	private int id;
	
	@OneToOne
	@JoinColumn(name="pr_id", referencedColumnName="id_pr")
	private Produit pr;
	
	private int quantiteCo;
	private double prixfinal;
	
	@ManyToOne
	@JoinColumn(name = "co_id", referencedColumnName = "id_co")
	private Commande co;
	
	public LigneCommande() {
		super();
	}
	public LigneCommande(Produit pr, int quantite) {
		super();
		this.pr = pr;
		this.quantiteCo = quantite;
		this.prixfinal = quantite*pr.getPrix();
	}
	public LigneCommande(int id, Produit pr, int quantite) {
		super();
		this.id = id;
		this.pr = pr;
		this.quantiteCo = quantite;
		this.prixfinal = quantite*pr.getPrix();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Produit getPr() {
		return pr;
	}
	public void setPr(Produit pr) {
		this.pr = pr;
	}
	public int getQuantiteCo() {
		return quantiteCo;
	}
	public void setQuantiteCo(int quantite) {
		this.quantiteCo = quantite;
	}
	public double getPrixfinal() {
		return prixfinal;
	}
	public void setPrixfinal(double prixfinal) {
		this.prixfinal = prixfinal;
	}
	public Commande getCo() {
		return co;
	}
	public void setCo(Commande co) {
		this.co = co;
	}
	
	

}
