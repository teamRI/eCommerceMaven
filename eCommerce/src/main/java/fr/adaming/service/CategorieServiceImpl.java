package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Service("catService")
@Transactional
public class CategorieServiceImpl implements ICategorieService{

	@Autowired
	private ICategorieDao catDao;
	
	
	
	
	public void setCatDao(ICategorieDao catDao) {
		this.catDao = catDao;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		
		return catDao.getAllCategorie();
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		
		return catDao.addCategorie(c);
	}

	@Override
	public Categorie getCategorie(Categorie c) {
		
		return catDao.getCategorie(c);
	}

	@Override
	public Categorie upDateCategorie(Categorie c) {
		
		return catDao.upDateCategorie(c);
	}

	@Override
	public int delateCategorie(Categorie c) {
	
		return catDao.delateCategorie(c);
	}

	
	
	
}
