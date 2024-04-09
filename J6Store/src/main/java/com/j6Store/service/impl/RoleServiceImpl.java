package com.j6Store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j6Store.dao.RoleDAO;
import com.j6Store.entity.Role;
import com.j6Store.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDAO rDAO;
	
	
	@Override
	public List<Role> findAll() {
		return rDAO.findAll();
	}

}
