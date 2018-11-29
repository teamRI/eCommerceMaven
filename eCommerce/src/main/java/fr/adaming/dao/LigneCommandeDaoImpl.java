package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Repository
public class LigneCommandeDaoImpl implements ILigneCommandeDao {

	@Autowired
	private SessionFactory sf;

	// ***************SETTER POUR INJECTION DE DEPENDENCE*********************

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	// ***********************************************************************

	@Override
	public LigneCommande addLigneCommande(LigneCommande lco) {
		Session s = sf.getCurrentSession();
		s.save(lco);
		return lco;
	}

	@Override
	public int upDateLigneCommande(LigneCommande lco) {
		Session s = sf.getCurrentSession();
		String req = "UPDATE LigneCommande lco SET lco.pr.id=:pIdpr, lco.quantiteCo=:pQco, lco.prixfinal=:pPf, lco.co.id=:pIdco WHERE lco.id=:pIdlco";
		Query q = s.createQuery(req);
		q.setParameter("pIdpr", lco.getPr().getId());
		q.setParameter("pIdco", lco.getCo().getId());
		q.setParameter("pQco", lco.getQuantiteCo());
		q.setParameter("pPf", lco.getPrixfinal());
		q.setParameter("pIdlco", lco.getId());
		return q.executeUpdate();
	}

	@Override
	public LigneCommande getLigneCommande(LigneCommande lco) {
		Session s = sf.getCurrentSession();
		LigneCommande lcoOut = (LigneCommande) s.get(LigneCommande.class, lco.getId());
		return lcoOut;
	}

	@Override
	public List<LigneCommande> getAllLigneCommandeByCo(Commande co) {
		Session s = sf.getCurrentSession();
		// ecrire la requette JPQL
		String req = "FROM LigneCommande lco WHERE lco.co.id=:pIdco";
		Query query = s.createQuery(req);
		query.setParameter("pIdco", co.getId());

		return (List<LigneCommande>) query.list();
	}

	@Override
	public int deleteLigneCommande(LigneCommande lco) {
		Session s = sf.getCurrentSession();
		String req = "DELETE LigneCommande lco WHERE lco.id=:pIdlco";
		Query q = s.createQuery(req);
		q.setParameter("pIdlco", lco.getId());
		return q.executeUpdate();
	}
}
