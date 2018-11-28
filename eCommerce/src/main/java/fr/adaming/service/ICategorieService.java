package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;


public interface ICategorieService {
	
    public List<Categorie> getAllCategorie();
	
	public Categorie addCategorie(Categorie c);
	
	public Categorie getCategorie(Categorie c);
	
	public Categorie upDateCategorie(Categorie c);
	
	public int delateCategorie(Categorie c);

}
