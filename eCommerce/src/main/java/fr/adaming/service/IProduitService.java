package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;


public interface IProduitService {

	 public List<Produit> getAllProduit(Categorie c);
		
		public Produit addProduit(Produit pr, Categorie c);
		
		public Produit getProduit(Produit pr);
		
		public Produit upDateProduit(Produit pr);
		
		public int delateProduit(Produit pr);
		
		public List<Produit> getProduitByNom(Produit pr);

}
