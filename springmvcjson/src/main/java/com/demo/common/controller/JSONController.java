package com.demo.common.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.common.model.Product;
import com.demo.common.model.Shop;

@Controller
@RequestMapping("/kfc/brands")
public class JSONController {

	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody
	Shop getShopInJSON(@PathVariable String name) {
		ArrayList<Product> list = new ArrayList<Product>();
		Shop shop = new Shop();
		shop.setName(name);
		shop.setStaffNames(new String[] { "tom", "harry" });
		
		Product p1 = new Product(101, "Veggie", 7.34);
		Product p2 = new Product(202, "Egg Delight", 9.99);
		Product p3 = new Product(303, "Chicken 65", 12.95);
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		shop.setList(list);
		
		//list.add(shop2);
		//list.add(shop);
		

		return shop;

	}

}