package fr.adaming.dao;

import java.util.List;



import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;


public interface IProduitDao {

	public List<Produit> getAllProduit(Categorie c);
	
	public Produit addProduit(Produit pr);
	
	public Produit getProduit(Produit pr);
	
	public Produit upDateProduit(Produit pr);
	
	public int delateProduit(Produit pr);
	
	public List<Produit> getProduitByNom(Produit pr);
	
	
}
