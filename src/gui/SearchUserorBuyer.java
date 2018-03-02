package gui;

import java.util.List;

import javax.swing.*;

import databasehandeling.DBIOBUYER;
import databasehandeling.DBIOSELLER;
import main.*;

public class SearchUserorBuyer extends JFrame {
	
	private JTable Table;
	private List<UserSeller> listseller;
	private List<UserBuyer> listbuyer;
	
	
	public SearchUserorBuyer(String username,String usertype){
		
		String[] heading={"Name","Phone num","pincode","Address","email"};
		
		if(usertype.equalsIgnoreCase("seller")){
			int count=1;
			try{
				listseller=DBIOSELLER.readFromSellerDB();
				}catch(Exception exp){System.out.println("File not Found");}
			for(UserSeller userseller:listseller){ if(username.equals(userseller.getName())){++count;}}
			String[][] data=new String[count][5];
			if(listseller.size()>0){
				
				//read from arraylist
				int row=0,col=0;
				for(UserSeller userseller:listseller){
				
					if(username.equalsIgnoreCase(userseller.getName())){
					
					data[row][col]=userseller.getName();
					data[row][++col]=userseller.getuserphnno();
					data[row][++col]=userseller.getpincode();
					data[row][++col]=userseller.getUseraddress();
					
					data[row][++col]=userseller.getUseremail();
				
					col=0;++row;}
				}
				Table=new JTable(data,heading);
				add(new JScrollPane(Table));
		}
		
		
	}
		else{int count=1;
			try{
			listbuyer=DBIOBUYER.readFromBuyerDB();
		}catch(Exception exp){System.out.println("File not Found");}
			for(UserBuyer userbuyer:listbuyer){ if(username.equals(userbuyer.getName())){++count;}}
			String[][] data=new String[count][5];
		if(listbuyer.size()>0){
			
			//read from arraylist
			int row=0,col=0;
			for(UserBuyer userbuyer:listbuyer){
				if(username.equalsIgnoreCase(userbuyer.getName())){
				
				data[row][col]=userbuyer.getName();
				data[row][++col]=userbuyer.getuserphnno();
				data[row][++col]=userbuyer.getpincode();
				data[row][++col]=userbuyer.getUseraddress();
				
				data[row][++col]=userbuyer.getUseremail();
				
				col=0;++row;}
			}
			Table=new JTable(data,heading);
			add(new JScrollPane(Table));	
		}

}
		
		this.setVisible(true);
		this.setSize(600,500);
	    this.setLocation(180, 180);
	    this.setTitle("View User");
	}
	
}

