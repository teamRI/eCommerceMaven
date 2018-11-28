package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Formateur;

@Repository
public class ClientDaoImpl implements IClientDao {

	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Client addClient(Client cl) {
		Session s = sf.getCurrentSession();
		s.save(cl);
		return cl;
	}

	@Override
	public int upDateClient(Client cl) {
		Session s = sf.getCurrentSession();
		String req = "UPDATE Client cl SET cl.nom=:pNom, cl.adresse=:pAdr, cl.email=:pMail, cl.tel=:pTel WHERE cl.id=:pIdc";
		Query q = s.createQuery(req);
		q.setParameter("pIdc", cl.getId());
		q.setParameter("pNom", cl.getNom());
		q.setParameter("pAdr", cl.getAdresse());
		q.setParameter("pMail", cl.getEmail());
		q.setParameter("pTel", cl.getTel());
		return q.executeUpdate();

	}

	@Override
	public int deleteClient(Client cl) {
		Session s = sf.getCurrentSession();
		String req = "DELETE Client cl WHERE cl.id=:pIdc";
		Query q = s.createQuery(req);
		q.setParameter("pIdc", cl.getId());
		return q.executeUpdate();
	}

	@Override
	public Client getClient(Client cl) {
		Session s = sf.getCurrentSession();
		Client clOut = (Client) s.get(Client.class, cl.getId());
		return clOut;
	}

	@Override
	public Client isExist(Client c) {
		Session s = sf.getCurrentSession();

		// HQL
		String req = "FROM Client as cl WHERE cl.email=:pMail AND cl.nom=:pNom";

		Query query = s.createQuery(req);
		query.setParameter("pMail", c.getEmail());
		query.setParameter("pMdp", c.getNom());

		return (Client) query.uniqueResult();
	}

}
