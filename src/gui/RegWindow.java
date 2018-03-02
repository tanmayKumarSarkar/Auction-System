package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.UserBuyer;
import main.UserSeller;
import databasehandeling.DBIOBUYER;
import databasehandeling.DBIOSELLER;

@SuppressWarnings("serial")
public class RegWindow extends JFrame implements ActionListener{
	//creating Instance Variables..
	private JLabel lblloginid,lbluseraddress,lbluserphnNo,lbluserpinCode,lbluseremail,lbluserpassword,lblname,lblreg;
	private JTextField txtloginid,txtname,txtuseraddress,txtuserphnNo,txtuserpinCode,txtuseremail;
	private JPasswordField pwpassword;
	private JButton btnbuyerreg,btnsellerreg,btnback,btnexit;
	private int genid;
	private JPanel pnlout,pnlin,pnltxt;
	private List<UserBuyer> listbuyer;
	private List<UserSeller>listseller;
	
	public  RegWindow()
	{
		listbuyer=new ArrayList<UserBuyer>();
		listseller=new ArrayList<UserSeller>();
		
		
		//Creating Backup Files...
		try{	listbuyer=DBIOBUYER.readFromBuyerDB();
		}catch(Exception exp)
		{	System.out.println("File not found");}
		
		try{	listseller=DBIOSELLER.readFromSellerDB();
		}catch(Exception exp)
		{	System.out.println("File not found");}
		
		//labelField
		lblname=new JLabel("   Name");
		lblloginid=new JLabel("   Set LoginId");
		lbluseraddress=new JLabel("   Address");
		lbluserphnNo=new JLabel("   Phone No");
		lbluserpinCode=new JLabel("   Pin Code");
		lbluseremail=new JLabel("   Email Id");
		lbluserpassword=new JLabel("   Password");
		lblreg=new JLabel("Register Yourself");
		pnlin=new JPanel();
		pnlout=new JPanel();
		pnltxt=new JPanel();
		
		//textField
		txtname=new JTextField(10);
		txtname.setRequestFocusEnabled(true);//prblm
		txtloginid=new JTextField(10);
		txtuseraddress=new JTextField(10);
		txtuserphnNo=new JTextField(10);
		txtuserpinCode=new JTextField(10);
		txtuseremail=new JTextField(10);
		//passwordField
		pwpassword=new JPasswordField(10);
		//ButtonField
		btnbuyerreg=new JButton("Buyer Registration");
		btnsellerreg=new JButton("Seller Registration");
		btnback=new JButton("Back");
		btnexit=new JButton("Exit");
		
		this.setContentPane(new JLabel(new ImageIcon("E:\\Program Files\\Java\\workspace\\Myproject\\Final_Project\\backgrnd\\b (3).jpg")));
		pnlin.setLayout(new GridLayout(9,2,5,5));
		pnlin.add(lblname);
		pnlin.add(txtname);
		pnlin.add(lbluseraddress);
		pnlin.add(txtuseraddress);
		pnlin.add(lbluserphnNo);
		pnlin.add(txtuserphnNo);
		pnlin.add(lbluserpinCode);
		pnlin.add(txtuserpinCode);
		pnlin.add(lbluseremail);
		pnlin.add(txtuseremail);
		pnlin.add(lblloginid);
		pnlin.add(txtloginid);
		pnlin.add(lbluserpassword);
		pnlin.add(pwpassword);
		pnlin.add(btnbuyerreg);
		pnlin.add(btnsellerreg);
		pnlin.add(btnback);
		pnlin.add(btnexit);
		
		
		pnlout.add(pnlin);
		pnlout.setLayout(null);
		pnlin.setOpaque(false);
		
		pnltxt.add(lblreg);
		pnltxt.setLayout(null);
		pnltxt.setOpaque(false);
		pnltxt.setBounds(30, 5, 565,300 );
		lblreg.setBounds(33, 0, 200, 30);
		lblreg.setFont(new Font("Serif",Font.ITALIC+Font.HANGING_BASELINE,19));
		lblreg.setForeground(new Color(139,34,82,199));
		
		pnlout.getComponent(0).setBounds(77,30, 400, 250);
		pnlout.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlout.setBounds(63, 35, 565,300 );
		pnlout.setBackground(new Color(0,0,0,45));
		
		btnbuyerreg.setBackground(new Color(72,209,204));
		btnbuyerreg.setForeground(Color.WHITE);
		btnbuyerreg.setFocusPainted(false);
		btnbuyerreg.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnsellerreg.setBackground(new Color(72,209,204));
		btnsellerreg.setForeground(Color.WHITE);
		btnsellerreg.setFocusPainted(false);
		btnsellerreg.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnback.setBackground(new Color(72,209,204));
		btnback.setForeground(Color.WHITE);
		btnback.setFocusPainted(false);
		btnback.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnexit.setBackground(new Color(72,209,204));
		btnexit.setForeground(Color.WHITE);
		btnexit.setFocusPainted(false);
		btnexit.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		this.add(pnltxt);
		this.add(pnlout);
		this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Registration Window");
        this.setSize(700,400);
		this.setLocation(340, 180);
		this.setResizable(false);
        
        //registration
        btnbuyerreg.addActionListener(this);
        btnsellerreg.addActionListener(this);
        btnback.addActionListener(this);
        btnexit.addActionListener(this);
        
	}
	
	
	public void actionPerformed(ActionEvent event)
	{	
		@SuppressWarnings("unused")
		String userbuyer="buyer",userseller="seller";
		String useraddress=(txtuseraddress.getText());
		String name=(txtname.getText());
		String phnno=(txtuserphnNo.getText());
		String pincode=(txtuserpinCode.getText());
		String emailid=(txtuseremail.getText());
		String loginid=(txtloginid.getText());
		String pass=new String(pwpassword.getPassword());
		
		//For Buyer..
		if(event.getSource()==btnbuyerreg)
		{
			idGenBuyer();
	if(name.isEmpty()||loginid.isEmpty()||phnno.isEmpty()||pincode.isEmpty()||emailid.isEmpty()||pass.isEmpty()){checkEmpty();}  
			else{
					if(validationChecking()==true){
				//checking for USER ID already exist or not
				if (checkForSameBuyerUserId() ==true){
						JOptionPane.showMessageDialog(null, "Login ID Already Exist Try Another Login ID", pass, JOptionPane.ERROR_MESSAGE);
				}
				else{
				try{   		
				listbuyer.add(getRegisterBuyer());
				DBIOBUYER.writeToBuyerDB(listbuyer);refreshall();
				new BuyerPanel(loginid);this.dispose();				
			}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
			
			} }
	}}
		
		//for Seller...
	   if(event.getSource()==btnsellerreg)
		{
		idGenSeller();
	 if(name.isEmpty()||loginid.isEmpty()||phnno.isEmpty()||pincode.isEmpty()||emailid.isEmpty()||pass.isEmpty()){checkEmpty();}
		else{
				if(validationChecking()==true){
			//checking for USER ID already exist or not
			if (checkForSameSellerUserId() ==true){
				JOptionPane.showMessageDialog(null, "Login ID Already Exist Try Another Login ID","ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else{
			try{
				listseller.add(getRegisterSeller());
				DBIOSELLER.writeToSellerDB(listseller);refreshall();
				new SellerPanel(loginid);this.dispose();
			}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
				} }
	}}
	
	   if(event.getSource()==btnback){new LoginPanel();this.dispose();}
		if(event.getSource()==btnexit){System.exit(0);}
	}
	
	//method to reset all filled fields after registration
	public void refreshall(){
		txtname.setText("");
		txtloginid.setText("");
		txtuseraddress.setText("");
		txtuserphnNo.setText("");
		txtuserpinCode.setText("");
		txtuseremail.setText("");
		pwpassword.setText("");}
	
	public int idGenBuyer(){
		//id generation for buyers & sellers
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
	String pass=new String(pwpassword.getPassword());
	
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
		String pass=new String(pwpassword.getPassword());
		
			UserSeller userseller=new UserSeller(idn,name,useraddress,phnno,pincode,emailid,loginid,pass);
			return userseller;
		
		}
	
	//checking Empty Fields...
	public void checkEmpty(){

		String name=(txtname.getText());
		String useraddress=(txtuseraddress.getText());
		String phnno=(txtuserphnNo.getText());
		String pincode=(txtuserpinCode.getText());
		String emailid=(txtuseremail.getText());
		String loginid=(txtloginid.getText());
		String pass=new String(pwpassword.getPassword());
		
		if(name.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Name","ERROR", JOptionPane.ERROR_MESSAGE);}
		else if(useraddress.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Address ", "ERROR", JOptionPane.ERROR_MESSAGE);}
		else if(phnno.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Phone Number", "ERROR", JOptionPane.ERROR_MESSAGE);}
		else if(pincode.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Pincode", "ERROR", JOptionPane.ERROR_MESSAGE);}
		else if(emailid.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Email", "ERROR", JOptionPane.ERROR_MESSAGE);}
		else if(loginid.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Login ID", "ERROR", JOptionPane.ERROR_MESSAGE);}
		else if(pass.isEmpty()){JOptionPane.showMessageDialog(null, "Please Fill Password Field", "ERROR", JOptionPane.ERROR_MESSAGE);}
		
	}
	
	public boolean checkForSameBuyerUserId(){
		try{	listbuyer=DBIOBUYER.readFromBuyerDB();
		}catch(Exception exp)
		{	System.out.println("File not found");}
		for(UserBuyer userbuyer:listbuyer){
			if((txtloginid.getText()).equals(userbuyer.getUserloginid())){return true;}
		}
		 return false;  }
	
	public boolean checkForSameSellerUserId(){
		try{	listseller=DBIOSELLER.readFromSellerDB();
		}catch(Exception exp)
		{	System.out.println("File not found");}
		for(UserSeller userseller:listseller){
			if((txtloginid.getText()).equals(userseller.getUserloginid())){return true;}
		}
		 return false;  }
	
	//validating with regular expression
	public boolean validationChecking(){
		String regexPH = "^(?:(?:\\+|00)(\\d{1,3})[\\s-]?)?(\\d{10})$";
		String strph = txtuserphnNo.getText();
		Pattern ph = Pattern.compile(regexPH);
		Matcher mph = ph.matcher(strph);
		if (mph.matches()==false){
			JOptionPane.showMessageDialog(null, "Enter A Valid Phone Number"," PHONE NO. NOT VALID", JOptionPane.ERROR_MESSAGE);
			return false;}
		
		String regexPIN = "^([1-9])([0-9]){5}$";
		String strpin = txtuserpinCode.getText();
		Pattern pin = Pattern.compile(regexPIN);
		Matcher mpin = pin.matcher(strpin);
		if (mpin.matches()==false){
			JOptionPane.showMessageDialog(null, "Enter A Valid PIN Number"," PIN NOT VALID", JOptionPane.ERROR_MESSAGE);
			return false;}
		
		String pattern="^[a-zA-Z][a-zA-Z0-9._].*@[a-zA-Z].*\\.[a-zA-Z]{2,3}$";
		Scanner iscan=new Scanner(txtuseremail.getText());
		String matched=iscan.findInLine(pattern);
		if(matched==null){JOptionPane.showMessageDialog(null, "Enter A Valid Email Address"," EMAIL NOT VALID", JOptionPane.ERROR_MESSAGE);
		return false;}
		
		return true;		
	}
	
}
