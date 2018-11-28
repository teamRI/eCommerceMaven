package fr.adaming.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Repository
public class CommandeDaoImpl implements ICommandeDao {

	 @Autowired
		private SessionFactory sf;
	    
	 //*********************************SETTER POUR INJECTION DE DEPENDENCE***********************************************

		public void setSf(SessionFactory sf) {
			this.sf = sf;
		}
	//*********************************************************************************************************************
		
		
	@Override
	public Commande addCommande(Commande co) {
		Session s= sf.getCurrentSession();
		s.persist(co);
		return co;
	}

	@Override
	public Commande upDateCommande(Commande co) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCommande(Commande co) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Commande getCommande(Commande co) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commande getAllCommandeByCl(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
