package com.j6Store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.j6Store.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String>{

}
