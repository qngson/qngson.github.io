package com.estore.dao;

import java.util.List;

import com.estore.entity.Category;

public interface CategoryDao {

	Category findById(Integer id);
	List<Category> findAll();
	Category Create(Category entity);
	void update(Category entity);
	Category delete(Integer id);
}
