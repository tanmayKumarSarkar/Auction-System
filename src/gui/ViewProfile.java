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

public class ViewProfile extends JFrame implements ActionListener{
	//creating Instance Variables..
	private JLabel lblloginid,lbluseraddress,lbluserphnNo,lbluserpinCode,lbluseremail,lbluserpassword,lblname;
	private JTextField txtloginid,txtname,txtuseraddress,txtuserphnNo,txtuserpinCode,txtuseremail,txtpassword;
	private JButton btnok,btnedit;
	private int genid;
	private String varname,varloginid,varaddress,varphno,varpin,varemail,varpassword;
	String BuyerUserId,SellerUserId,useredittype,usereditid;
	private List<UserBuyer> listbuyer;
	private List<UserSeller>listseller;
	public  ViewProfile(String UserId,String UserType)
	{
		
	if(UserType.equalsIgnoreCase("buyer")){
		
		this.BuyerUserId=UserId;this.useredittype=UserType;this.usereditid=UserId;
			listbuyer=new ArrayList<UserBuyer>();
			//Creating Backup Files...
			try{	listbuyer=DBIOBUYER.readFromBuyerDB();
			}catch(Exception exp)
			{	System.out.println("File not found");}
			for(UserBuyer userbuyer:listbuyer){
				if(BuyerUserId.equals(userbuyer.getUserloginid())){
				varname=userbuyer.getName();varloginid=userbuyer.getUserloginid();
				varaddress=userbuyer.getUseraddress();varphno=userbuyer.getuserphnno();varpin=userbuyer.getpincode();
				varemail=userbuyer.getUseremail();varpassword=userbuyer.getUserpassword();
				}
			}
	}
	else{	
			this.SellerUserId=UserId;this.useredittype=UserType;this.usereditid=UserId;
			listseller=new ArrayList<UserSeller>();	
			//Creating Backup Files...	
			try{	listseller=DBIOSELLER.readFromSellerDB();
			}catch(Exception exp)
			{	System.out.println("File not found");}
			for(UserSeller userseller:listseller){
				if(SellerUserId.equals(userseller.getUserloginid())){
				varname=userseller.getName();varloginid=userseller.getUserloginid();
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
			txtuseraddress=new JTextField(varaddress);txtuseraddress.setEditable(false);
			txtuserphnNo=new JTextField(varphno);txtuserphnNo.setEditable(false);
			txtuserpinCode=new JTextField(varpin);txtuserpinCode.setEditable(false);
			txtuseremail=new JTextField(varemail);txtuseremail.setEditable(false);
			txtpassword=new JTextField(varpassword);txtpassword.setEditable(false);
			//ButtonField
			btnok=new JButton("OK");
			btnedit=new JButton("EDIT");
		
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
			add(btnedit);
        	this.setVisible(true);
        	this.setTitle(" MY PROFILE ");
        	this.setSize(400,300);
        	this.setLocation(488,240);
    		this.setResizable(false);
        
        	//registration
        	btnok.addActionListener(this);
        	btnedit.addActionListener(this);
        
	}
	
	
	public void actionPerformed(ActionEvent event)
	{	
		if(event.getSource()==btnok){this.dispose();}
		if(event.getSource()==btnedit){new EditProfile(usereditid,useredittype);this.dispose();}
	}
	
	
	
	
	
	//public static void main (String[] args){new ViewProfile();}
}
