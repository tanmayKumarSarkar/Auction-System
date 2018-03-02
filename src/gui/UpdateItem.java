package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import databasehandeling.DBIOITEM;
import databasehandeling.DBIOSELLER;
import main.UserItem;
import main.UserSeller;

@SuppressWarnings("serial")
public class UpdateItem extends JFrame implements ActionListener
{
	private JLabel lblname,lblprice,lblquantity,lblcatagory,lblhd;
	private JTextField txtname,txtprice,txtquantity,txtcatagory;
	private JPanel pnlok,pnlout,pnlhd;
	private JButton btndone,btncancel;
	private int id;
	private String seller;
	private List<UserSeller>listseller;
	
	
	public UpdateItem(int id, String nameitem, String catagory,double price, int itemquantity, String nameseller )
	{
		new ArrayList<UserSeller>();
		this.id=id;
		this.seller=nameseller;
		new ArrayList<UserItem>();
		try{	DBIOITEM.readFromItemDB();
		}catch(Exception exp)
		{	System.out.println("File not found");}
		
	lblhd=new JLabel(" Update Your Items Here");
	lblhd.setFont(new Font("Serif",Font.ROMAN_BASELINE,20));
		
	lblname=new JLabel("Item Name");
	lblprice=new JLabel("Price");
	lblcatagory=new JLabel("Select Catagory");
	lblquantity=new JLabel("Quantity");
	txtname=new JTextField(10);
	txtprice=new JTextField(10);
	txtquantity=new JTextField(10);
	txtcatagory=new JTextField();
    btndone=new JButton("Done");
    btncancel=new JButton("Cancel");
    
    this.setContentPane(new JLabel(new ImageIcon("image\\b (3).jpg")));
    
    pnlok=new JPanel();
    pnlout=new JPanel();
    pnlhd=new JPanel();
    
    pnlout.setLayout(null);		
	pnlout.setBorder(BorderFactory.createLineBorder(Color.black));
	pnlout.setBounds(125, 65, 440, 220);
	pnlout.setBackground(new Color(0,0,0,40));
	
	pnlhd.add(lblhd);
	lblhd.setBounds(20, 5, 200, 30);
	
	pnlhd.add(lblhd);
	pnlhd.setLayout(null);
	pnlhd.setForeground(Color.darkGray);
	pnlhd.setBounds(10, 6, 300, 40);
	pnlhd.setOpaque(false);
	lblhd.setBounds(20, 5, 200, 30);
    
   // this.setLayout(new GridLayout(4,1,50,50));
    pnlok.add(lblname);
    pnlok.add(txtname);
    pnlok.add(lblprice);
    pnlok.add(txtprice);
    pnlok.add(lblcatagory);
    pnlok.add(txtcatagory);
    pnlok.add(btndone);
	pnlok.add(btncancel);
	pnlok.add(lblquantity);
	pnlok.add(txtquantity);
	pnlok.setOpaque(false);
	//pnlok.setLayout(new GridLayout(6,2,15,12));
	this.add(pnlhd);
	this.add(pnlok);
	pnlok.setSize(480,550);
	this.add(pnlout);
	this.setVisible(true);
	this.setResizable(false);
	this.setSize(700, 400);
	this.setLocation(340, 180);	
	this.setTitle("Update Your Item");
	
	//placing 
	pnlok.setLayout(null);
	pnlok.setBounds(100, 80, 400, 300);
	lblname.setBounds(100, 10, 100, 30);
	txtname.setBounds(220, 10, 170, 30);
	lblprice.setBounds(100, 40, 100, 30);
	txtprice.setBounds(220, 40, 170, 30);
	lblcatagory.setBounds(100,70,100,30);
	txtcatagory.setBounds(220,70,170,30);
	lblquantity.setBounds(100,100,100,30);
	txtquantity.setBounds(220,102,170,30);
	btndone.setBounds(100,160,132,26);
	btncancel.setBounds(252,160,133,26);
	
	btndone.setBackground(new Color(72,209,204));
	btndone.setForeground(Color.WHITE);
	btndone.setFocusPainted(false);
	btndone.setFont(new Font("Tahoma", Font.BOLD, 11));
	
	btncancel.setBackground(new Color(72,209,204));
	btncancel.setForeground(Color.WHITE);
	btncancel.setFocusPainted(false);
	btncancel.setFont(new Font("Tahoma", Font.BOLD, 11));
	
	
	this.txtname.setText(nameitem);txtname.setEditable(false);
	this.txtcatagory.setText(catagory);txtcatagory.setEditable(false);
	this.txtprice.setText(""+price);
	this.txtquantity.setText(""+itemquantity);
	
	//registering
	btndone.addActionListener(this);
	btncancel.addActionListener(this);
	
	}//end of the constructor
	
	public void findUsername(String sellername,int i) {
		try{
			listseller=DBIOSELLER.readFromSellerDB();
		}catch(Exception exp){System.out.println("No One Registered Yet");}
		
		if(listseller.size()>0){
			//read from array list
			for(UserSeller userseller:listseller){
				if(sellername.equals(userseller.getName())){
					if(i==1){new SellerPanel(userseller.getUserloginid());}
					else if(i==2){new ViewSellerItem(userseller.getUserloginid(),sellername);}
				}		
				}}}
	
	
public void actionPerformed(ActionEvent event){

	
	String itemname=txtname.getText();
	String catagory=txtcatagory.getText();
	//for done button	
	if(event.getSource()==btndone){
		try{
			double itemprice=Double.parseDouble(txtprice.getText());
			int quantity=Integer.parseInt(txtquantity.getText());
			new SellingItem(id,seller,itemname,catagory,itemprice,quantity);this.dispose();
			findUsername(seller,2);}
			catch(NumberFormatException exp) {
				//showing runtime exception
				//JOptionPane.showMessageDialog(null,"Fill The Box","Error IN FEELING THE BOXES",JOptionPane.ERROR);
	  }
	}
	
	//for cancel button
	if(event.getSource()==btncancel){this.dispose();}
		
   }//end of actions
 }