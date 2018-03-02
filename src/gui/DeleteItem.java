package gui;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.UserItem;

public class DeleteItem {
	
	private int itemid;
	private String nameseller,nameitem;
	

		public DeleteItem(int itmid,String seller,String nameitm) 
		{
			this.itemid=itmid;
			this.nameseller=seller;
			this.nameitem=nameitm;
			String outputStr=new String("Search Unsuccessful !!!");
			ArrayList<UserItem> listitem=new ArrayList<UserItem>();
			UserItem refitem; 
			//searchitemid=Integer.parseInt(JOptionPane.showInputDialog("Please enter the Item ID..."));
			try 
			{
				FileInputStream fin=new FileInputStream("ITEMLIST.txt"); 
				ObjectInputStream oin=new ObjectInputStream(fin); 
				listitem=(ArrayList<UserItem>)oin.readObject();
				int x= listitem.size();
				for(int i = 0;i < x ;i++)
				{
					refitem = listitem.get(i);
if ((( refitem).getItemid()==itemid) &&((refitem).getSeller().equalsIgnoreCase(nameseller))&&((refitem).getItemname().equalsIgnoreCase(nameitem)))
					{
						outputStr="Successfully deleted Item"; 
						outputStr+="\n\n Item Id= "+refitem.getItemid() ;
						outputStr+="\n Item's name = "+refitem.getItemname(); 
						listitem.remove(i); 
						}
					}

	JOptionPane.showMessageDialog(null, outputStr);
	}
			catch(Exception e) 
			{
				
			}
			try
			{ 
				FileOutputStream fout=new FileOutputStream("ITEMLIST.txt"); 
				ObjectOutputStream oout=new ObjectOutputStream(fout); 
				oout.writeObject(listitem);
				}
			catch(Exception e)
			{
				
			} 
			}
		public static void main(String[] args){//new DeleteItem();
			}
		}
	
