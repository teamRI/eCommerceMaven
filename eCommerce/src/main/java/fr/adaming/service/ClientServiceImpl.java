package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;

import fr.adaming.model.Client;

@Service("clService")
@Transactional
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clDao;
	
	public void setClDao(IClientDao clDao) {
		this.clDao = clDao;
	}

	@Override
	public Client addClient(Client cl) {
		
		return clDao.addClient(cl);
	}

	@Override
	public Client upDateClient(Client cl) {
		int verif=clDao.upDateClient(cl);
		if (verif!=0) {
			Client clOut=clDao.getClient(cl);
			return clOut;
		}else {
		return null;
		}
	}

	@Override
	public int deleteClient(Client cl) {
	
		return clDao.deleteClient(cl);
	}

	@Override
	public Client getClient(Client cl) {
		
		return clDao.getClient(cl);
	}

	@Override
	public Client isExist(Client c) {
		Client clOut = clDao.isExist(c);
		if (clOut != null) {
			return clOut;
		} else {
			return null;
		}
	}
}
