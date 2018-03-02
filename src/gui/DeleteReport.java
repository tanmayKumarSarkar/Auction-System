package gui;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import main.UserDispute;
import main.UserItem;

public class DeleteReport {
	
	private int itemid;
	private String nameuser,catagory;
	

		public DeleteReport(int itmid,String user,String Catagory) 
		{
			this.itemid=itmid;
			this.nameuser=user;
			this.catagory=Catagory;
			String outputStr=new String("Search Unsuccessful !!!");
			ArrayList<UserDispute> listdispute=new ArrayList<UserDispute>();
			UserDispute refreport; 
			
			try 
			{
				FileInputStream fin=new FileInputStream("DisputeDB.txt"); 
				ObjectInputStream oin=new ObjectInputStream(fin); 
				listdispute=(ArrayList<UserDispute>)oin.readObject();
				int x= listdispute.size();
				for(int i = 0;i < x ;i++)
				{
					refreport = listdispute.get(i);
if ((( refreport).getId()==itemid) &&((refreport).getUsername().equalsIgnoreCase(nameuser))&&((refreport).getCatagory().equalsIgnoreCase(catagory)))
					{
						outputStr="Successfully Solved Report"; 
						outputStr+="\n\n Report Id= "+refreport.getId() ;
						outputStr+="\n Diputer's UserID = "+refreport.getUsername(); 
						listdispute.remove(i); 
						}
					}

	JOptionPane.showMessageDialog(null, outputStr);
	}
			catch(Exception e) 
			{
				
			}
			try
			{ 
				FileOutputStream fout=new FileOutputStream("DisputeDB.txt"); 
				ObjectOutputStream oout=new ObjectOutputStream(fout); 
				oout.writeObject(listdispute);
				}
			catch(Exception e)
			{
				
			} 
			}
		public static void main(String[] args){//new DeleteItem();
			}
		}
	
