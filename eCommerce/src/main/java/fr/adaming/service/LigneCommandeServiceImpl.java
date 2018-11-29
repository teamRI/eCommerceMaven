package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Service("lcoService")
@Transactional
public class LigneCommandeServiceImpl implements ILigneCommandeService{

	@Autowired
	private ILigneCommandeDao lcoDao;
	
	@Autowired
	private IProduitDao prDao;
	
	public void setLcoDao(ILigneCommandeDao lcoDao) {
		this.lcoDao = lcoDao;
	}

	public void setPrDao(IProduitDao prDao) {
		this.prDao = prDao;
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lco) {
		Produit pr=prDao.getProduit(lco.getPr());
		lco.setPr(pr);
		lco.setPrixfinal(pr.getPrix()*lco.getQuantiteCo());
		LigneCommande lcoOut=lcoDao.addLigneCommande(lco);
		return lcoOut;
	}

	@Override
	public LigneCommande upDateLigneCommande(LigneCommande lco) {
		LigneCommande lco1= lcoDao.getLigneCommande(lco);
		lco1.setQuantiteCo(lco.getQuantiteCo());
		lco1.setPrixfinal(lco1.getPr().getPrix()*lco1.getQuantiteCo());
		int verif=lcoDao.upDateLigneCommande(lco1);
		if (verif!=0) {
			return lcoDao.getLigneCommande(lco1);
		}else {
			return null;
		}
		
	}

	@Override
	public LigneCommande getLigneCommande(LigneCommande lco) {
		
		return lcoDao.getLigneCommande(lco);
	}

	@Override
	public List<LigneCommande> getAllLigneCommandeByCo(Commande co) {
		
		return lcoDao.getAllLigneCommandeByCo(co);
	}

	@Override
	public int deleteLigneCommande(LigneCommande lco) {
		
		return lcoDao.deleteLigneCommande(lco);
	}

	
}
