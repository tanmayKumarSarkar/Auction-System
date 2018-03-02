package main;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserSold implements Serializable{
	
	private int itemid;
	private String itemname,catagory,seller,buyer;
	private int quantity;
	private double price;
	private String buyingdate;
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
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
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBuyingdate() {
		return buyingdate;
	}
	public void setBuyingdate(String buyingdate) {
		this.buyingdate = buyingdate;
	}
	public UserSold(int itemid, String itemname, String catagory,
			String seller, String buyer, int quantity, double price,
			String buyingdate) {
		this.itemid = itemid;
		this.itemname = itemname;
		this.catagory = catagory;
		this.seller = seller;
		this.buyer = buyer;
		this.quantity = quantity;
		this.price = price;
		this.buyingdate = buyingdate;
	}
	
	
	
	
	
	

}
