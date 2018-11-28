package fr.adaming.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commandes")
public class Commande {

	// 1************************ATTRIBUTS***************************************************

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_co")
	private Long id;
	private Date dateCommande;

	@OneToOne
	@JoinColumn(name = "cl_id", referencedColumnName = "id_cl")
	private Client cl;
	
	@OneToMany(mappedBy="co",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<LigneCommande> listelco;
	// 2*************CONSTRUCTEURS***************************************************************

	public Commande() {
		super();
	}

	public Commande(Date dateCommande) {
		super();
		this.dateCommande = dateCommande;
	}

	public Commande(Long id, Date dateCommande) {
		super();
		this.id = id;
		this.dateCommande = dateCommande;
	}

	// 3*****************************GETTERS AND
	// SETTERS*******************************************************

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Client getCl() {
		return cl;
	}

	public void setCl(Client cl) {
		this.cl = cl;
	}

	public List<LigneCommande> getListelco() {
		return listelco;
	}

	public void setListelco(List<LigneCommande> listelco) {
		this.listelco = listelco;
	}

}
