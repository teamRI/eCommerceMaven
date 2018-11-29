package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;

@Repository
public class CategorieDaoImpl implements ICategorieDao{

	 @Autowired
		private SessionFactory sf;
	    
	 //*********************************SETTER POUR INJECTION DE DEPENDENCE***********************************************

		public void setSf(SessionFactory sf) {
			this.sf = sf;
		}
	//*********************************************************************************************************************

	
	
	@Override
	public List<Categorie> getAllCategorie() {
		Session s= sf.getCurrentSession();
		String req="SELECT c FROM Categorie c";
		Query query= s.createQuery(req);
		List<Categorie> listOut= query.list();
		for(Categorie c: listOut) {
			c.setImage("data:image/png);base64," + Base64.encodeBase64String(c.getPhoto()));
		}
		
		return listOut;
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		Session s= sf.getCurrentSession();
		s.save(c);
		return c;
	}

	@Override
	public Categorie getCategorie(Categorie c) {
		Session s= sf.getCurrentSession();
		
		Categorie cOut= (Categorie) s.get(Categorie.class, c.getId());
		cOut.setImage("data:image/png);base64," + Base64.encodeBase64String(cOut.getPhoto()));
	
		return cOut;
	}

	@Override
	public Categorie upDateCategorie(Categorie c) {
		Session s= sf.getCurrentSession();
		
		String req="UPDATE Categorie c SET c.nomCat=:pNomCat, c.photo=:pPhoto, c.description=:pDescription WHERE c.id=:pId";
		Query query= s.createQuery(req);
		query.setParameter("pNomCat", c.getNomCat());
		query.setParameter("pPhoto", c.getPhoto());
		query.setParameter("pDescription", c.getDescription());
		query.setParameter("pId", c.getId());
		
		return (Categorie) s.merge(c);
	}

	@Override
	public int delateCategorie(Categorie c) {
		Session s= sf.getCurrentSession();
		Categorie cOut= (Categorie) s.get(Categorie.class, c.getId());
		s.delete(cOut);
		return 0;
	}

}
