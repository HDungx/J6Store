package com.j6Store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j6Store.dao.AccountDAO;
import com.j6Store.entity.Account;
import com.j6Store.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDAO adao;

	@Override
	public Account findById(String username) {
		
		return adao.findById(username).get();
	}

	@Override
	public List<Account> getAdministrators() {
		return adao.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
