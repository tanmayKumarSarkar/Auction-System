package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import main.UserBuyer;
import main.UserSeller;
import databasehandeling.DBIOBUYER;
import databasehandeling.DBIOSELLER;

public class LoginPanel extends JFrame implements ActionListener{


private JLabel lblloginid,lblpassword,lblerror;
private JTextField txtloginid;
private JPasswordField pwdpassword;
private JButton btnlogin,btnregister;
private JPanel pnlckbx,pnllog,pnlset,pnlseto;
private JRadioButton rbadmin,rbbuyer,rbseller;
private ButtonGroup group;
private List<UserBuyer> listbuyer;
private List<UserSeller>listseller;

public LoginPanel(){
	
	lblloginid=new JLabel("Login ID");
	lblpassword=new JLabel("Password");
	lblerror=new JLabel("");lblerror.setVisible(false);lblerror.setForeground(Color.WHITE);	//creating a lebel for Error showing
	txtloginid=new JTextField(15);
	pwdpassword=new JPasswordField(15);
	btnlogin=new JButton("LOGIN");
	btnregister=new JButton("REGISTER");
	
	pnlckbx =new JPanel();
	pnllog =new JPanel();
	pnlset=new JPanel();
	pnlseto=new JPanel();
	
	rbadmin =new JRadioButton("Admin");
	rbbuyer =new JRadioButton("Buyer");
	rbseller =new JRadioButton("Seller");
	group=new ButtonGroup();	//creating ButtonGroup
	
	listbuyer=new ArrayList<UserBuyer>();
	listseller=new ArrayList<UserSeller>();
	
	//adding Radio Button  to group
	group.add(rbadmin);group.add(rbbuyer);group.add(rbseller);
	
	
	this.setContentPane(new JLabel(new ImageIcon("image//b (3).jpg")));
	
	lblloginid.setForeground(new Color(0,0,0,199));
	lblloginid.setFont(new Font("Serif",Font.ITALIC+Font.BOLD,15));
	
	lblpassword.setForeground(new Color(0,0,0,199));
	lblpassword.setFont(new Font("Serif",Font.ITALIC+Font.BOLD,15));
	
	pnllog.setLayout(new GridLayout(3,2,7,7));
	//pnlckbx.setLayout(new GridLayout());
	pnlckbx.setLayout(new FlowLayout(FlowLayout.LEFT));
	pnlset.setLayout(null);
	pnlseto.setLayout(null);
	
	pnllog.add(lblloginid);
	pnllog.add(txtloginid);txtloginid.setRequestFocusEnabled(true);
	pnllog.add(lblpassword);
	pnllog.add(pwdpassword);
	pnllog.add(btnlogin);
	pnllog.add(btnregister);
	pnllog.setOpaque(false);
	pnlckbx.add(rbadmin);pnlckbx.add(rbbuyer);pnlckbx.add(rbseller);pnlckbx.add(lblerror);
	pnlckbx.setOpaque(false);
	pnlckbx.setVisible(true);
	rbadmin.setOpaque(false);
	rbseller.setOpaque(false);
	rbbuyer.setOpaque(false);
	
	pnlset.add(pnllog);
	pnlseto.add(pnlckbx);
	pnlset.getComponent(0).setBounds(60,27, 270, 110);
	pnlset.setBorder(BorderFactory.createLineBorder(Color.black));
	pnlset.setBounds(150, 70, 400, 200);
	pnlset.setBackground(new Color(0,0,0,75));
	
	pnlseto.getComponent(0).setBounds(150,0, 470, 30);
	pnlseto.setBounds(80, 220, 470, 50);
	pnlseto.setOpaque(false);
	
	//coloring buttons..
		btnlogin.setBackground(new Color(72,209,204));
		btnlogin.setForeground(Color.WHITE);
		btnlogin.setFocusPainted(false);
		btnlogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnregister.setBackground(new Color(72,209,204));
		btnregister.setForeground(Color.WHITE);
		btnregister.setFocusPainted(false);
		btnregister.setFont(new Font("Tahoma", Font.BOLD, 12));
	
	
	this.add(pnlseto);
	this.add(pnlset);
	this.setResizable(false);
	this.setLayout(null);
	this.setVisible(true);
	this.setSize(700, 400);
	this.setLocation(340, 180);
	this.setTitle("My LOGIN WINDOW");
	
	//register to the listener
	
	btnlogin.addActionListener(this);
	btnregister.addActionListener(this);
	rbadmin.addActionListener(this);
	rbbuyer.addActionListener(this);
	rbseller.addActionListener(this);
	
}

public void actionPerformed(ActionEvent event){
	String UserId=txtloginid.getText();
	String password=new String(pwdpassword.getPassword());
	String rbText="";
	if(event.getSource()==btnregister){new RegWindow();this.dispose();}
	if(event.getSource()==btnlogin){
		if(rbadmin.isSelected()){rbText=rbadmin.getText();}
		if(rbbuyer.isSelected()){rbText=rbbuyer.getText();}
		if(rbseller.isSelected()){rbText=rbseller.getText();}
		checkEmpty(UserId,password,rbText);		//checking for empty Field
	}
}

public void checkEmpty(String UserId,String password,String rbText){
	lblerror.setVisible(false);		//setting lblerror to not visible
	if(UserId.isEmpty()){
		JOptionPane.showMessageDialog(null,"Fill Login ID " , "Error Massage", JOptionPane.ERROR_MESSAGE);}
	else if(password.isEmpty()){
		JOptionPane.showMessageDialog(null,"Fill Password" , "Error Massage", JOptionPane.ERROR_MESSAGE);
	}
	else if(rbText.isEmpty()){lblerror.setText("Select a Radio Button");lblerror.setVisible(true);}
	
	//checking for login And password
	
	else if((this.checkLoginPassword(UserId, password)==true)&& rbText=="Admin"){
			new AdminLogin();
			this.dispose();}
	else if((this.checkLoginPasswordseller(UserId, password)==true)&& rbText=="Seller"){
			new SellerPanel(UserId);
			this.dispose();}
	else if((this.checkLoginPasswordbuyer(UserId, password)==true)&& rbText=="Buyer"){
			new BuyerPanel(UserId);
			this.dispose();}
	else{JOptionPane.showMessageDialog(null,"Invalid login ID and Password " , "Error Massage", JOptionPane.ERROR_MESSAGE);}
}
public boolean checkLoginPasswordbuyer(String login,String password){
	try{
		listbuyer=DBIOBUYER.readFromBuyerDB();
	}catch(Exception exp){System.out.println("No One Registered Yet");}
	
	if(listbuyer.size()>0){
		//read from array list
		for(UserBuyer userbuyer:listbuyer){
			if(login.equals(userbuyer.getUserloginid()) && password.equals(new String(userbuyer.getUserpassword()))){
			return true;}		
			}}
	
	return false;
}

public boolean checkLoginPasswordseller(String login,String password){
	try{
		listseller=DBIOSELLER.readFromSellerDB();
	}catch(Exception exp){System.out.println("No One Registered Yet");}
	
	if(listseller.size()>0){
		//read from array list
		for(UserSeller userseller:listseller){
			if(login.equals(userseller.getUserloginid()) && password.equals(new String(userseller.getUserpassword()))){
				return true;}		
			}}
	
	return false;
}

public boolean checkLoginPassword(String login,String password){
	String login1="Admin";
	String password1="123";
	if(login1.equals(login) && password.equals(password1))
		return true;
	else 
		return false;
}

public static void main(String[] args){
	new LoginPanel();
	}
}