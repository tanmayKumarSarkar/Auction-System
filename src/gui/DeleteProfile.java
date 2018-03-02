package gui;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import main.UserBuyer;
import main.UserSeller;

public class DeleteProfile {
	

		public DeleteProfile(int id,String loginid,String usertype) 
		{
			int searchbuyerid,searchsellerid;
			String searchbuyerloginid,searchsellerloginid;
			String outputStr=new String("Search Unsuccessful for Seller id!!!");
			
			System.out.println(id+"/n"+loginid+"/n"+usertype);
			
			if(usertype.equals("buyer")){
				ArrayList<UserBuyer> listbuyer=new ArrayList<UserBuyer>();
				UserBuyer refbuyer; 
				searchbuyerid=id;
				searchbuyerloginid=loginid;
						
				try {
					FileInputStream finbuyer=new FileInputStream("BuyerDB.txt"); 
					ObjectInputStream oinbuyer=new ObjectInputStream(finbuyer); 
					listbuyer=(ArrayList<UserBuyer>)oinbuyer.readObject();
					int b= listbuyer.size();
					for(int i = 0;i < b ;i++){
						refbuyer = listbuyer.get(i);
						if ((( refbuyer).getId()==searchbuyerid )&&((refbuyer).getUserloginid().equals(searchbuyerloginid)))
						{
							outputStr="Successfully deleted Buyer"; 
							outputStr+="\n\n Buyer Id= "+refbuyer.getId() ;
							outputStr+="\n Buyer's name = "+refbuyer.getName(); 
							listbuyer.remove(i);
						}
					}
					JOptionPane.showMessageDialog(null, outputStr);
				}
				catch(Exception e) {}
				try{ 
					FileOutputStream foutbuyer=new FileOutputStream("BuyerDB.txt"); 
					ObjectOutputStream ooutbuyer=new ObjectOutputStream(foutbuyer); 
					ooutbuyer.writeObject(listbuyer);
					}
				catch(Exception e){} 
			}			
							
			
			else{
				ArrayList<UserSeller> listseller=new ArrayList<UserSeller>();
				UserSeller refseller; 
				searchsellerid=id;
				searchsellerloginid=loginid;
				
				try {
					FileInputStream finseller=new FileInputStream("SellerDB.txt"); 
					ObjectInputStream oinseller=new ObjectInputStream(finseller); 
					listseller=(ArrayList<UserSeller>)oinseller.readObject();
					int s= listseller.size();
					for(int i = 0;i < s ;i++){
						refseller = listseller.get(i);
						if ((( refseller).getId()==searchsellerid )&&((refseller).getUserloginid().equals(searchsellerloginid)))
						{
							outputStr="Successfully deleted Seller"; 
							outputStr+="\n\n Seller Id= "+refseller.getId() ;
							outputStr+="\n Seller's name = "+refseller.getName(); 
							listseller.remove(i);
						}
					}
					JOptionPane.showMessageDialog(null, outputStr);
				}
				catch(Exception e) {}
				try{ 
					FileOutputStream foutseller=new FileOutputStream("SellerDB.txt"); 
					ObjectOutputStream ooutseller=new ObjectOutputStream(foutseller); 
					ooutseller.writeObject(listseller);
					}
				catch(Exception e){} 
			}			
				
		}
		public static void main(String[] args){//new DeleteProfile(4,"raja","buyer");
			}
		}
	
