package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Service("coService")
@Transactional
public class CommandeServiceImpl implements ICommandeService{

	@Autowired
	private ICommandeDao coDao;
	
	//**************************************SETTER********************************************
	
	public void setCoDao(ICommandeDao coDao) {
		this.coDao = coDao;
	}
//*********************************************************************************
	@Override
	public Commande addCommande(Commande co) {
		
		return coDao.addCommande(co);
	}

	@Override
	public Commande upDateCommande(Commande co) {
		int verif=coDao.upDateCommande(co);
		if(verif!=0) {
		Commande coOut=coDao.getCommande(co);
		return coOut;
		}else {
			return null;
		}
	}

	@Override
	public int deleteCommande(Commande co) {
		
		return coDao.deleteCommande(co);
	}

	@Override
	public Commande getCommande(Commande co) {
		
		return coDao.getCommande(co);
	}

	@Override
	public Commande getAllCommandeByCl(Client cl) {
		
		return coDao.getAllCommandeByCl(cl);
	}

}
