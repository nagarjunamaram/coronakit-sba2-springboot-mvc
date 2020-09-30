package com.eval.coronakit.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eval.coronakit.entity.CoronaKit;
import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.entity.UserAddress;
import com.eval.coronakit.exception.ProductException;
import com.eval.coronakit.service.CoronaKitService;
import com.eval.coronakit.service.KitDetailService;
import com.eval.coronakit.service.ProductService;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CoronaKitService coronaKitService;
	
	@Autowired
	KitDetailService kitDetailService;
	
	@RequestMapping("/home")
	public String home() {
		
		return "user-home";
	}
	
	@RequestMapping("/show-cart")
	public String showKit() {		
		
		return "show-cart";
	}

	@RequestMapping("/show-list")
	public String showList(Model model,HttpSession session) {
		List<ProductMaster> products = productService.getAllProducts();
		session.setAttribute("productlist", products);
		
		HashMap<Integer,Integer> quantitymap =new HashMap<Integer,Integer>();
		for (ProductMaster productMaster : products) {
			quantitymap.put(productMaster.getId(), 0);		
			}
		session.setAttribute("quantitymap", quantitymap);
		session.removeAttribute("cartproduct");
		session.removeAttribute("cartaddedproduct");
		session.removeAttribute("Qtymap");
		return "show-all-item-user";
	}
	
	@RequestMapping("/add-to-cart") 
	public String showKit(@RequestParam("productId") int productId,HttpSession session,Model model) {
		Map<Integer,Integer> tempmap=(Map<Integer,Integer>)session.getAttribute("quantitymap");
		tempmap.put(productId, tempmap.get(productId)+1);
		session.setAttribute("quantitymap", tempmap);
			
		return "show-all-item-user";
	}	
	@RequestMapping("/checkout")
	public String Checkout(HttpSession session,Model model) throws ProductException {
		model.addAttribute("Address", new UserAddress());
		return "checkout-address";
		
	}

	@PostMapping("/finalize")
	public String finalizeOrder(@ModelAttribute("Address") @Valid UserAddress userAddress,BindingResult rs,Model model,HttpSession session) throws ProductException {
		String view=null;
		if (!rs.hasErrors())
		{		
		KitDetail k;
		int Totalamount=0;		
		List<ProductMaster> Addedproductstocart=(List<ProductMaster>)session.getAttribute("productlist");
		Map<Integer,Integer> hm=(Map<Integer,Integer>)session.getAttribute("quantitymap");
		for(ProductMaster p:Addedproductstocart) {
			Totalamount=Totalamount+(hm.get(p.getId())*p.getCost());
		}
				
		model.addAttribute("Address",userAddress.getAddress());
		model.addAttribute("City",userAddress.getCity());
		model.addAttribute("State",userAddress.getState());
		
	
		CoronaKit kit=new CoronaKit();

		kit.setDeliveryAddress(userAddress);
		kit.setOrderDate(LocalDate.now());
		kit.setTotalAmount(Totalamount);
		coronaKitService.saveKit(kit);
		System.out.println(kit.getId());
		for(ProductMaster p:Addedproductstocart) {
			
			k= new KitDetail(kit.getId(),p.getId(),p.getProductName(),hm.get(p.getId()),(hm.get(p.getId())*p.getCost()));
			kitDetailService.addKitItem(k);
		}
		List<KitDetail> details=kitDetailService.getAllKitItemsOfAKit(kit.getId());
		model.addAttribute("kitdetails", details);
		session.setAttribute("Totalamount", Totalamount);
		session.setAttribute("OrderID", kit.getId());
		view= "show-summary";
		}
		else
		{
			view="checkout-address";
		}
		return view;
	}
	
	@RequestMapping("/delete")
	public String deleteItem(@RequestParam("productId") int itemId,HttpSession session,Model model) {
		List<ProductMaster> Addedproductstocart=(List<ProductMaster>)session.getAttribute("cartaddedproduct");
		
		Map<Integer,Integer> tempmap=(Map<Integer,Integer>)session.getAttribute("quantitymap");
		
		if (tempmap.get(itemId)>0) {
			tempmap.put(itemId, tempmap.get(itemId)-1);
			session.setAttribute("quantitymap", tempmap);			
		} 
					
		return "show-all-item-user";
	}
}
