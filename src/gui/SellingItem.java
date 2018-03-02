package gui;

import java.util.ArrayList;
import java.util.List;

import main.UserItem;

import databasehandeling.DBIOITEM;

public class SellingItem {
	
	private int genid,itemid,quantity;
	private String nameseller,nameitem,catagory;
	double price;
	private List<UserItem> listitem;

	
	public SellingItem(int itemid,String nameseller,String nameitem,String catagory,double price,int quantity){
				
				this.itemid=itemid;
				this.nameseller=nameseller;
				this.nameitem=nameitem;
				this.catagory=catagory;
				this.price=price;
				this.quantity=quantity;
				
				listitem=new ArrayList<UserItem>();
				try{	listitem=DBIOITEM.readFromItemDB();
				}catch(Exception exp)
				{	System.out.println("File not found");}
				
		
				if(quantity==0){new DeleteItem(itemid,nameseller,nameitem);}
				else{updateitem(); new DeleteItem(itemid,nameseller,nameitem); }
	}
	
	public void updateitem() {
		
		try{	

			if(listitem.size()==0)
				genid=1;
			else
				genid=listitem.get(listitem.size()-1).getItemid()+1;
			
			listitem.add(getadditem());
			DBIOITEM.writeToItemDB(listitem);
			//displayItem();
		}catch(Exception exp){exp.printStackTrace();}
	}
	
	public UserItem getadditem(){
		String seller=nameseller;
		String itemname=nameitem;
		String itemcatagory=catagory;
		double itemprice=price;
		int id=genid;
		int quantity2=quantity;
		
		UserItem useritem=new UserItem(id,seller,itemname,itemcatagory,itemprice,quantity2);
		return useritem;
		
	}

}
