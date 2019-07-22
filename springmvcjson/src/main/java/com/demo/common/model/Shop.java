package com.demo.common.model;

import java.util.ArrayList;

public class Shop {

	String name;
	String staffNames[];
	ArrayList<Product> list;
	
	
	/**
	 * @return the list
	 */
	public ArrayList<Product> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<Product> list) {
		this.list = list;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getStaffNames() {
		return staffNames;
	}
	public void setStaffNames(String[] staffNames) {
		this.staffNames = staffNames;
	}
	public Shop() {
	} 
	
}