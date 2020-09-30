package com.eval.coronakit.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.exception.ProductException;
import com.eval.coronakit.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/home")
	public String home() {
		return "admin-home";
	}
	
	@GetMapping("/product-entry")
	public String productEntry(Model model) {			
		model.addAttribute("product", new ProductMaster());
		return "add-new-item";
	}
	
	@PostMapping("/product-save")
	public String productSave(@ModelAttribute("product") @Valid ProductMaster product, BindingResult result, Model model ) throws ProductException {
		
		String view=null;
		if (!result.hasErrors()) {
			productService.addNewProduct(product);		
			model.addAttribute("productlist", productService.getAllProducts());	
			view="show-all-item-admin";
		}
		else
		{
			view="add-new-item";
		}
		
		return view;
	}
	

	@GetMapping("/product-list")
	public String productList(Model model) {
		model.addAttribute("productlist", productService.getAllProducts());		
		return "show-all-item-admin";
	}
	
	@GetMapping("/product-delete")
	public String productDelete(@RequestParam("productId") int productId,Model model) throws ProductException {
		System.out.println(productId);
		productService.deleteProduct(productId);
		model.addAttribute("productlist", productService.getAllProducts());			
		return "show-all-item-admin";
	}
	
}
