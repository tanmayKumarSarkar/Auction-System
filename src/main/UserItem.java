package main;

import java.io.Serializable;

public class UserItem implements Serializable
{
	private int itemid;
	private String seller,itemname,catagory;
	private double price;
	private int quantity;
	
	
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public UserItem(int itemid, String seller, String itemname,
			String catagory, double price, int quantity) {
		super();
		this.itemid = itemid;
		this.seller = seller;
		this.itemname = itemname;
		this.catagory = catagory;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	
	
}
