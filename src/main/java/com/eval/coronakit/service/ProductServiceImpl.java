package com.eval.coronakit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.coronakit.dao.ProductMasterRepository;
import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.exception.ProductException;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMasterRepository repository;
	
	@Override
	@Transactional
	public ProductMaster addNewProduct(ProductMaster product) throws ProductException {
		if (product!=null) {	
//			System.out.println(product.getProductName());
//			
//			System.out.println(repository.existsByProductName(product.getProductName()));
			if(repository.existsByProductName(product.getProductName())) {
				throw new ProductException("Product Name already exists");
			}
			repository.save(product);
		}
		return product;
	}

	@Override
	public List<ProductMaster> getAllProducts() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	@Transactional
	public boolean deleteProduct(int productId) throws ProductException {
		System.out.println(repository.existsById(productId));
		if(!repository.existsById(productId)) {
			
			throw new ProductException("Product id doesn't exists");
		}
		repository.deleteById(productId);
		
		return true;
	}

	@Override
	public ProductMaster getProductById(int productId) {
		// TODO Auto-generated method stub
		return repository.findById(productId).orElse(null);
	}

}
