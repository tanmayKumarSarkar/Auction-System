package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.UserBuyer;
import main.UserSeller;
import databasehandeling.DBIOBUYER;
import databasehandeling.DBIOSELLER;

public class EditProfile extends JFrame implements ActionListener{
	//creating Instance Variables..
	private JLabel lblloginid,lbluseraddress,lbluserphnNo,lbluserpinCode,lbluseremail,lbluserpassword,lblname;
	private JTextField txtloginid,txtname,txtuseraddress,txtuserphnNo,txtuserpinCode,txtuseremail,txtpassword;
	private JButton btnok,btncancel;
	private int genid,varid;
	private String varname,varloginid,varaddress,varphno,varpin,varemail,varpassword;
	String BuyerUserId,SellerUserId,UserEditType,UserEditLoginId;
	private List<UserBuyer> listbuyer;
	private List<UserSeller>listseller;
	public  EditProfile(String UserId,String UserType)
	{
		
	if(UserType.equalsIgnoreCase("buyer")){
		
		this.BuyerUserId=UserId;this.UserEditType=UserType;this.UserEditLoginId=UserId;
			listbuyer=new ArrayList<UserBuyer>();
			//Creating Backup Files...
			try{	listbuyer=DBIOBUYER.readFromBuyerDB();
			}catch(Exception exp)
			{	System.out.println("File not found");}
			for(UserBuyer userbuyer:listbuyer){
				if(BuyerUserId.equals(userbuyer.getUserloginid())){
				varid=userbuyer.getId();varname=userbuyer.getName();varloginid=userbuyer.getUserloginid();
				varaddress=userbuyer.getUseraddress();varphno=userbuyer.getuserphnno();varpin=userbuyer.getpincode();
				varemail=userbuyer.getUseremail();varpassword=userbuyer.getUserpassword();
				}
			}
	}
	else{	
			this.SellerUserId=UserId;this.UserEditType=UserType;this.UserEditLoginId=UserId;
			listseller=new ArrayList<UserSeller>();	
			//Creating Backup Files...	
			try{	listseller=DBIOSELLER.readFromSellerDB();
			}catch(Exception exp)
			{	System.out.println("File not found");}
			for(UserSeller userseller:listseller){
				if(SellerUserId.equals(userseller.getUserloginid())){
				varid=userseller.getId();varname=userseller.getName();varloginid=userseller.getUserloginid();
				varaddress=userseller.getUseraddress();varphno=userseller.getuserphnno();varpin=userseller.getpincode();
				varemail=userseller.getUseremail();varpassword=userseller.getUserpassword();
				}
			}
	}	
			//labelField
			lblname=new JLabel("Name");
			lblloginid=new JLabel("Set LoginId");
			lbluseraddress=new JLabel("Address");
			lbluserphnNo=new JLabel("Phone No.");
			lbluserpinCode=new JLabel("PIN Code");
			lbluseremail=new JLabel("Email");
			lbluserpassword=new JLabel("Password");
			//textField
			txtname=new JTextField(varname);txtname.setEditable(false);
			txtloginid=new JTextField(varloginid);txtloginid.setEditable(false);
			txtuseraddress=new JTextField(varaddress);txtuseraddress.setEditable(true);
			txtuserphnNo=new JTextField(varphno);txtuserphnNo.setEditable(true);
			txtuserpinCode=new JTextField(varpin);txtuserpinCode.setEditable(true);
			txtuseremail=new JTextField(varemail);txtuseremail.setEditable(true);
			txtpassword=new JTextField(varpassword);txtpassword.setEditable(true);
			//ButtonField
			btnok=new JButton("OK");
			btncancel=new JButton("CANCEL");
		
			this.setLayout(new GridLayout(8,2,5,5));
			add(lblname);
			add(txtname);
			add(lbluseraddress);
			add(txtuseraddress);
			add(lbluserphnNo);
			add(txtuserphnNo);
			add(lbluserpinCode);
			add(txtuserpinCode);
			add(lbluseremail);
			add(txtuseremail);
			add(lblloginid);
			add(txtloginid);
			add(lbluserpassword);
			add(txtpassword);
			add(btnok);
			add(btncancel);
        	this.setVisible(true);
        	this.setTitle("EDIT YOUR PROFILE");
        	this.setSize(400,300);
        	this.setLocation(488,240);
    		this.setResizable(false);
        
        	//registration
        	btnok.addActionListener(this);
        	btncancel.addActionListener(this);
        
	}
	
	
	public void actionPerformed(ActionEvent event)
	{
		
	String name=(txtname.getText());
	String useraddress=(txtuseraddress.getText());
	String phnno=(txtuserphnNo.getText());
	String pincode=(txtuserpinCode.getText());
	String emailid=(txtuseremail.getText());
	String loginid=(txtloginid.getText());
	String pass=new String(txtpassword.getText());
	
		if(event.getSource()==btncancel){this.dispose();}
		if(event.getSource()==btnok){
			if(name.isEmpty()||loginid.isEmpty()||phnno.isEmpty()||pincode.isEmpty()||emailid.isEmpty()||pass.isEmpty()){checkEmpty();}
			else{
							
				if(UserEditType.equalsIgnoreCase("buyer")){
					idGenBuyer();	
					
					//new updated buyer register
				{
						try{   		
							listbuyer.add(getRegisterBuyer());
							DBIOBUYER.writeToBuyerDB(listbuyer);
							new ViewProfile(loginid,"buyer");this.dispose();				
						}
					catch(Exception exp)
					{
						exp.printStackTrace();
					}	}							
				}
				else{		idGenSeller();
				
			//new updated seller register
		{
						try{   		
							listseller.add(getRegisterSeller());
							DBIOSELLER.writeToSellerDB(listseller);
							new ViewProfile(loginid,"seller");this.dispose();				
						}
					catch(Exception exp)
					{
						exp.printStackTrace();
					}	}					
				}
				if(UserEditType.equalsIgnoreCase("buyer")){new DeleteProfile(varid,UserEditLoginId,"buyer");}
				else{new DeleteProfile(varid,UserEditLoginId,"seller");	}	
				
			}
		}
		
	}
	//id generation for buyers & sellers
	public int idGenBuyer(){
				if(listbuyer.size()==0)
					genid=1;
				else
					genid=listbuyer.get(listbuyer.size()-1).getId()+1;
				return genid;
		}
	public int idGenSeller(){
				if(listseller.size()==0)
					genid=1;
				else
					genid=listseller.get(listseller.size()-1).getId()+1;
				return genid;				
	}
	
	//Registering values for buyers and sellers	
		public UserBuyer getRegisterBuyer(){
		
		int id=genid;
		String name=(txtname.getText());
		String useraddress=(txtuseraddress.getText());
		String phnno=(txtuserphnNo.getText());
		String pincode=(txtuserpinCode.getText());
		String emailid=(txtuseremail.getText());
		String loginid=(txtloginid.getText());
		String pass=new String(txtpassword.getText());
		
			UserBuyer userbuyer=new UserBuyer(id,name,useraddress,phnno,pincode,emailid,loginid,pass);
			return userbuyer;
		
		}	
		public UserSeller getRegisterSeller(){
		
		int idn=genid;
		String name=(txtname.getText());
		String useraddress=(txtuseraddress.getText());
		String phnno=(txtuserphnNo.getText());
		String pincode=(txtuserpinCode.getText());
		String emailid=(txtuseremail.getText());
		String loginid=(txtloginid.getText());
		String pass=new String(txtpassword.getText());
		
			UserSeller userseller=new UserSeller(idn,name,useraddress,phnno,pincode,emailid,loginid,pass);
			return userseller;
		
		}

	//ckecking Empty Fields...
		public void checkEmpty(){

			String name=(txtname.getText());
			String useraddress=(txtuseraddress.getText());
			String phnno=(txtuserphnNo.getText());
			String pincode=(txtuserpinCode.getText());
			String emailid=(txtuseremail.getText());
			String loginid=(txtloginid.getText());
			String pass=new String(txtpassword.getText());
			
			if(name.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Name", pass, JOptionPane.ERROR_MESSAGE);}
			else if(useraddress.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Address ", pass, JOptionPane.ERROR_MESSAGE);}
			else if(phnno.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Phone Number", pass, JOptionPane.ERROR_MESSAGE);}
			else if(pincode.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Pincode", pass, JOptionPane.ERROR_MESSAGE);}
			else if(emailid.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Email", pass, JOptionPane.ERROR_MESSAGE);}
			else if(loginid.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Login ID", pass, JOptionPane.ERROR_MESSAGE);}
			else if(pass.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Password Field", pass, JOptionPane.ERROR_MESSAGE);}
			
		}
	
	
	
	
	
	//public static void main (String[] args){new EditProfile();}
}
