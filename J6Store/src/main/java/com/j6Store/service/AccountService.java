package com.j6Store.service;

import java.util.List;

import com.j6Store.entity.Account;

public interface AccountService {
	Account findById(String username);

	List<Account> getAdministrators();

	List<Account> findAll();
}
