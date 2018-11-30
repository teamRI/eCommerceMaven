package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao{

	 @Autowired
		private SessionFactory sf;
	    
	 //*********************************SETTER POUR INJECTION DE DEPENDENCE***********************************************

		public void setSf(SessionFactory sf) {
			this.sf = sf;
		}
	//*********************************************************************************************************************

	@Override
	public List<Produit> getAllProduit(Categorie c) {
		Session s= sf.getCurrentSession();
		String req="SELECT p FROM Produit p WHERE p.categorie.id=:pCategorie";
		
		Query query= s.createQuery(req);
		query.setParameter("pCategorie", c.getId());
		
		List<Produit> listOut= query.list();
		for(Produit pr: listOut) {
			pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
		}
		return listOut;
	}

	@Override
	public Produit addProduit(Produit pr) {
		Session s= sf.getCurrentSession();
		s.save(pr);
		return pr;
	}

	@Override
	public Produit getProduit(Produit pr) {
		Session s= sf.getCurrentSession();
	Produit pOut=	(Produit) s.get(Produit.class, pr.getId());
	pOut.setImage("data:image/png);base64," + Base64.encodeBase64String(pOut.getPhoto()));
		return pOut;
	}

	@Override
	public Produit upDateProduit(Produit pr) {
		Session s= sf.getCurrentSession();
		Produit pOut=	(Produit) s.get(Produit.class, pr.getId());
		pOut.setDescription(pr.getDescription());
		pOut.setDesignation(pr.getDesignation());
		pOut.setPrix(pr.getPrix());
		pOut.setQuantite(pr.getQuantite());
		pOut.setCategorie(pr.getCategorie());
		return (Produit) s.merge(pOut);
	}

	@Override
	public int delateProduit(Produit pr) {
		Session s= sf.getCurrentSession();
		Produit pOut=	(Produit) s.get(Produit.class, pr.getId());
		s.delete(pOut);
		return 0;
	}

	@Override
	public List<Produit> getProduitByNom(Produit pr) {
		Session s= sf.getCurrentSession();
		String req="SELECT p FROM Produit as p WHERE p.designation LIKE :pNom";
		Query query= s.createQuery(req);
		query.setParameter("pNom", "%"+pr.getDesignation()+"%");
		List<Produit> listOut=query.list();
		for(Produit prd: listOut) {
			prd.setImage("data:image/png);base64," + Base64.encodeBase64String(prd.getPhoto()));
		}
		
		return listOut;
	}
}
