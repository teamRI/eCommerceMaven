package fr.adaming.dao;

import java.util.List;



import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;


public interface ILigneCommandeDao {

	public LigneCommande addLigneCommande(LigneCommande lco);
	public int upDateLigneCommande(LigneCommande lco);
	public LigneCommande getLigneCommande(LigneCommande lco);
	public List<LigneCommande> getAllLigneCommandeByCo(Commande co);
	public int deleteLigneCommande(LigneCommande lco);
}
