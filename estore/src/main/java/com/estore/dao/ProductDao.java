package com.estore.dao;

import java.util.List;


import com.estore.entity.Product;

public interface ProductDao {

	Product findById(Integer id);
	List<Product> findAll();
	Product Create(Product entity);
	void update(Product entity);
	Product delete(Integer id);
	List<Product> findByCategoryId(Integer categoryId);
	List<Product> findByKeyWord(String keywords);
	List<Product> findByIds(String ids);
	List<Product> findBySpeacial(Integer id);
}
