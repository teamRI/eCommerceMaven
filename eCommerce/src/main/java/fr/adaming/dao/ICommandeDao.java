package fr.adaming.dao;



import fr.adaming.model.Client;
import fr.adaming.model.Commande;


public interface ICommandeDao {

	public Commande addCommande(Commande co);
	public int upDateCommande(Commande co);
	public int deleteCommande(Commande co);
	public Commande getCommande(Commande co);
	public Commande getAllCommandeByCl(Client cl);
}
