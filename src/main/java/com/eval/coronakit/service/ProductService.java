package com.eval.coronakit.service;

import java.util.List;

import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.exception.ProductException;

public interface ProductService {

	public ProductMaster addNewProduct(ProductMaster product) throws ProductException;
	public List<ProductMaster> getAllProducts();
	public boolean deleteProduct(int productId)throws ProductException;
	public ProductMaster getProductById(int productId);
}
