package gui;

import gui.SellerPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import databasehandeling.DBIOITEM;
import databasehandeling.DBIOSELLER;
import main.UserItem;
import main.UserSeller;

public class AddItem extends JFrame implements ActionListener
{
	private JLabel lblname,lblprice,lblquantity,lblcatagory,lblhd;
	private JTextField txtname,txtprice,txtquantity;
	private JComboBox sellcomboBox;
	private JPanel pnlok,pnlout,pnlhd;
	private JButton btndone,btnaddanother,btncancel;
	private List<UserItem> listitem;
	private int genid;
	private String selleruserid,sellerrealName;
	private List<UserSeller> listseller;
	public AddItem(String UserId)
	{
		listseller=new ArrayList<UserSeller>();
		String[] catagorylist = { "Arts & Crafts","Automobiles","Books","Cosmetics","Confectionary","Electroics", "Garments", "Footwear",
				"Mens","Womens","Kids","Jewellery","Musical Systems","Home & kitchens","Entertainment","Sports","Select Catagory" };
		
		this.selleruserid=UserId;
		
		listitem=new ArrayList<UserItem>();
		try{	listitem=DBIOITEM.readFromItemDB();
		}catch(Exception exp)
		{	System.out.println("File not found");}
		
	
	lblhd=new JLabel(" Add Your Items Here");
	lblhd.setFont(new Font("Serif",Font.ROMAN_BASELINE,20));
	
	lblname=new JLabel("Item Name");
	lblprice=new JLabel("Price");
	lblcatagory=new JLabel("Select Catagory");
	lblquantity=new JLabel("Quantity");
	txtname=new JTextField(10);txtname.setRequestFocusEnabled(true);
	txtprice=new JTextField(10);
	txtquantity=new JTextField(10);
	sellcomboBox = new JComboBox(catagorylist);
	sellcomboBox.setEditable(false);
	sellcomboBox.setSelectedIndex(16);
	 
    btndone=new JButton("Done");
    btncancel=new JButton("Cancel");
    btnaddanother=new JButton("Add Another Item");
    pnlok=new JPanel();
    pnlout=new JPanel();
    pnlhd=new JPanel();
   // this.setLayout(new GridLayout(4,1,50,50));
    pnlok.add(lblname);
    pnlok.add(txtname);
    pnlok.add(lblprice);
    pnlok.add(txtprice);
    pnlok.add(lblcatagory);
    pnlok.add(sellcomboBox);
    pnlok.add(btndone);
	pnlok.add(btnaddanother);
	pnlok.add(btncancel);
	pnlok.add(lblquantity);
	pnlok.add(txtquantity);
	pnlok.setOpaque(false);
	pnlok.setBounds(103, 85, 400, 100);
	
	pnlout.setLayout(null);		
	pnlout.setBorder(BorderFactory.createLineBorder(Color.black));
	pnlout.setBounds(125, 65, 440, 220);
	pnlout.setBackground(new Color(0,0,0,40));
	
	pnlhd.add(lblhd);
	pnlhd.setLayout(null);
	pnlhd.setForeground(Color.darkGray);
	pnlhd.setBounds(10, 6, 300, 40);
	pnlhd.setOpaque(false);
	lblhd.setBounds(20, 5, 200, 30);
	
	btndone.setBackground(new Color(72,209,204));
	btndone.setForeground(Color.WHITE);
	btndone.setFocusPainted(false);
	btndone.setFont(new Font("Tahoma", Font.BOLD, 11));
	
	btnaddanother.setBackground(new Color(72,209,204));
	btnaddanother.setForeground(Color.WHITE);
	btnaddanother.setFocusPainted(false);
	btnaddanother.setFont(new Font("Tahoma", Font.BOLD, 11));
	
	btncancel.setBackground(new Color(72,209,204));
	btncancel.setForeground(Color.WHITE);
	btncancel.setFocusPainted(false);
	btncancel.setFont(new Font("Tahoma", Font.BOLD, 11));
	
	
	
	//pnlok.setLayout(new GridLayout(6,2,15,12));
	this.setContentPane(new JLabel(new ImageIcon("image\\b (3).jpg")));
	this.add(pnlok);
	this.add(pnlhd);
	this.add(pnlout);
	pnlok.setSize(480,550);
	this.setVisible(true);
	this.setLocation(488,240);
	this.setResizable(false);
	this.setSize(700, 400);
	this.setLocation(340, 180);	
	this.setTitle("Add Item");
	
	//placing 
	pnlok.setLayout(null);
	lblname.setBounds(100, 10, 100, 30);
	txtname.setBounds(220, 10, 170, 30);
	lblprice.setBounds(100, 40, 100, 30);
	txtprice.setBounds(220, 40, 170, 30);
	lblcatagory.setBounds(100,70,100,30);
	sellcomboBox.setBounds(220,70,170,30);
	lblquantity.setBounds(100,100,100,30);
	txtquantity.setBounds(220,102,170,30);
	btndone.setBounds(100,140,63,30);
	btnaddanother.setBounds(170,140,137,30);
	btncancel.setBounds(311, 140, 75, 30);
	
	//registering
	btndone.addActionListener(this);
	btnaddanother.addActionListener(this);
	btncancel.addActionListener(this);
	sellcomboBox.addActionListener(this);
	}
	
	
public void actionPerformed(ActionEvent event){
		
    	
		if(event.getSource()==btndone){additem();this.dispose();new SellerPanel(selleruserid); }
		if(event.getSource()==btncancel){new SellerPanel(selleruserid);this.dispose(); }
		if(event.getSource()==btnaddanother){additem();new AddItem(selleruserid);this.dispose(); }
  }

public void additem() {
	if(checkEmpty()==false){
	try{	
		checkuser(selleruserid);
		if(listitem.size()==0)
			genid=1;
		else
			genid=listitem.get(listitem.size()-1).getItemid()+1;
		
		listitem.add(getadditem());
		DBIOITEM.writeToItemDB(listitem);
		this.dispose();
	}catch(Exception exp){exp.printStackTrace();	}  }
}

public boolean checkEmpty() {
	if(txtname.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter Product Name " , "Error Massage", JOptionPane.ERROR_MESSAGE);
	return true;}
	else if(sellcomboBox.getSelectedItem().equals("Select Catagory")){
		JOptionPane.showMessageDialog(null,"Select Catagory" , "Error Massage", JOptionPane.ERROR_MESSAGE);  return true;}
	else if(txtprice.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter Price","Error Massage",JOptionPane.ERROR_MESSAGE);
	return true;}
	else if(txtquantity.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Fill Product Name " , "Error Massage",JOptionPane.ERROR_MESSAGE);
	return true;}
	else return false;
	
}


public void checkuser(String selleruserid){
try{
	listseller=DBIOSELLER.readFromSellerDB();
}catch(Exception exp){System.out.println("Sorry file not found");}

if(listseller.size()>0){
	//read from array list
	for(UserSeller userseller:listseller){
		if(selleruserid.equals(userseller.getUserloginid())){this.sellerrealName=userseller.getName();
			}
		
		}}}


//constructor
public UserItem getadditem(){
	String seller=sellerrealName;
	String itemname=txtname.getText();
	String itemcatagory=(String) sellcomboBox.getSelectedItem();
	double itemprice=Double.parseDouble(txtprice.getText());
	int id=genid;
	int quantity=Integer.parseInt(txtquantity.getText());
	
	UserItem useritem=new UserItem(id,seller,itemname,itemcatagory,itemprice,quantity);
	return useritem;
	
}

//displaying items to console

}
