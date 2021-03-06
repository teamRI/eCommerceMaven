package fr.adaming.service;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public interface ICommandeService {

	public Commande addCommande(Commande co);
	public Commande upDateCommande(Commande co);
	public int deleteCommande(Commande co);
	public Commande getCommande(Commande co);
	public Commande getAllCommandeByCl(Client cl);
}
