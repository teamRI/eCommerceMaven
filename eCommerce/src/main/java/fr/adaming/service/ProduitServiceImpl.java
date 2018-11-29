package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Service("prService")
@Transactional
public class ProduitServiceImpl implements IProduitService{

	@Autowired
	private IProduitDao prDao;
	
	
	public void setPrDao(IProduitDao prDao) {
		this.prDao = prDao;
		
	}
	
	
	

	@Override
	public List<Produit> getAllProduit(Categorie c) {
	
		return prDao.getAllProduit(c);
	}

	@Override
	public Produit addProduit(Produit pr, Categorie c) {
		pr.setCategorie(c);
		return prDao.addProduit(pr);
	}

	@Override
	public Produit getProduit(Produit pr) {
		
		return prDao.getProduit(pr);
	}

	@Override
	public Produit upDateProduit(Produit pr, Categorie c) {
		pr.setCategorie(c);
		return prDao.upDateProduit(pr);
	}

	@Override
	public int delateProduit(Produit pr) {
		
		return prDao.delateProduit(pr);
	}

	@Override
	public Produit getProduitByNom(Produit pr) {
		
		return null;
	}

	
}
