package fr.adaming.dao;


import org.hibernate.Query;
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
		s.save(co);
		return co;
	}

	@Override
	public int upDateCommande(Commande co) {
		Session s = sf.getCurrentSession();
		String req = "UPDATE Commande co SET co.dateCommande=:pDate, co.cl.id=:pIdcl WHERE co.id=:pIdco";
		Query q = s.createQuery(req);
		q.setParameter("pIdcl", co.getCl().getId());
		q.setParameter("pDate", co.getDateCommande());
		q.setParameter("pIdco", co.getId());
		return q.executeUpdate();
	}

	@Override
	public int deleteCommande(Commande co) {
		Session s = sf.getCurrentSession();
		String req = "DELETE Commande co WHERE co.id=:pIdco";
		Query q = s.createQuery(req);
		q.setParameter("pIdco", co.getId());
		return q.executeUpdate();
	}

	@Override
	public Commande getCommande(Commande co) {
		Session s = sf.getCurrentSession();
		Commande coOut = (Commande) s.get(Commande.class, co.getId());
		return coOut;
	}

	@Override
	public Commande getAllCommandeByCl(Client cl) {
		Session s=sf.getCurrentSession();
		// ecrire la requette JPQL
		String req = "FROM Commande co WHERE co.cl.id=:pIdcl";
		Query query=s.createQuery(req);
		query.setParameter("pIdcl", cl.getId());
		
		return (Commande) query.uniqueResult();
	}

	
}
