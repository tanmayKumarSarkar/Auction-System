package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import databasehandeling.DBIOBUYER;
import databasehandeling.DBIOITEM;
import databasehandeling.DBIOSOLD;
import main.UserBuyer;
import main.UserItem;
import main.UserSold;

@SuppressWarnings("serial")
public class BuyerPanel extends JFrame implements ActionListener{
	private JMenuBar menubar;
	private JMenu mnulist,mnusellers,mnureport;
	private JMenuItem mnuitemlogout,mnuitmmydetails,mnuitmviewslrs,mnuitemhistory,mnuitmdispute;
	private JButton btnsearch,btnlistitem,btnlistslr,btnlogout;
	private JTextField txtsearch,txtitem,txtseller,txtlastitem;
	private JComboBox comboBox;
	private JLabel lblwlcm,lblsearch,lblitem,lblseller,lbllastitem;
	private JPanel pnllevel,pnlsearch,pnlcnt,pnlout;
	String buyeruserid;
	private List<UserBuyer>listbuyer;
	private List<UserSold>listsold;UserSold refitem;
	private List<UserItem>listitem;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BuyerPanel(String UserId){
		this.buyeruserid=UserId;
		int soldrc=0,itemrc=0;String lastitem = "";int countr=0;int cnto = 0;
		try{
			listsold=DBIOSOLD.readFromSoldDB();
		}catch(Exception exp){System.out.println("File not Found");}
		
		try{
			listitem=DBIOITEM.readFromItemDB();
		}catch(Exception exp){System.out.println("File not Found");}
		
		if(listsold.size()>0){
		    for(UserSold item:listsold){if(buyeruserid.equals(item.getBuyer())){++soldrc;countr=item.getItemid();}}
		    for(UserSold item:listsold){
		    	if(countr==item.getItemid()){
		    		lastitem=item.getItemname();}}
		    	if(soldrc==0){lastitem="No Items Bought";}}
		    
		    
		    	
		
		if(listitem.size()>0){for(UserItem item:listitem){++itemrc;}}
		
		
		
		menubar=new JMenuBar();
		mnulist=new JMenu("My List");
		mnusellers=new JMenu("Menu");
		mnureport=new JMenu ("Report");
		//create submenu--
		mnuitemlogout=new JMenuItem("Log Out");
		mnuitmviewslrs=new JMenuItem("View Sellers");
		mnuitmmydetails=new JMenuItem("My Details");
		mnuitemhistory=new JMenuItem("Buying History");
		mnuitmdispute=new JMenuItem("Dispute Report");
		
		//creating panel,lebel,button,combobox,textfield
		btnsearch=new JButton("Search");
		btnlistslr=new JButton("See List");
		btnlistitem=new JButton("See List");
		btnlogout=new JButton(new ImageIcon(((new ImageIcon("image//btn.png").getImage().getScaledInstance(40, 40,java.awt.Image.SCALE_SMOOTH)))));
		
		txtsearch=new JTextField();
		txtitem=new JTextField("     "+soldrc+" ",7);txtitem.setEditable(false);	
		txtseller=new JTextField("     "+itemrc+"  ",7);txtseller.setEditable(false);
		txtlastitem=new JTextField(" "+lastitem,15);txtlastitem.setEditable(false);
		
		lblwlcm=new JLabel("Welcome   "+ UserId.toUpperCase());
		lblwlcm.setFont(new Font("Serif",Font.HANGING_BASELINE+Font.BOLD,25));
		lblwlcm.setForeground(Color.darkGray);
		
		lblsearch=new JLabel(" Search Items ");
		lblsearch.setForeground(new Color(0,0,0,199));
		lblsearch.setFont(new Font("Serif",Font.ROMAN_BASELINE+Font.BOLD,16));
		
		lblseller=new JLabel(" Total Items Available         ");
		lblseller.setForeground(new Color(0,0,0,199));
		lblseller.setFont(new Font("Serif",Font.BOLD,17));
		
		lblitem=new JLabel(" Total Items Bought             ");
		lblitem.setForeground(new Color(0,0,0,199));
		lblitem.setFont(new Font("Serif",Font.BOLD,17));
		
		lbllastitem=new JLabel(" Last Item Bought                 ");
		lbllastitem.setForeground(new Color(0,0,0,199));
		lbllastitem.setFont(new Font("Serif",Font.BOLD,17));
		
		
		
		comboBox = new JComboBox();comboBox.setEditable(false);
	    comboBox.addItem("Catagory");
	    comboBox.addItem("category1");
	    comboBox.addItem("category2");
	    
	    pnllevel=new JPanel();
		pnlsearch=new JPanel();
		pnlcnt=new JPanel();
		pnlout=new JPanel();
		
		
		//attach submenu to menu--
		mnulist.add(mnuitemlogout);
		mnulist.add(mnuitmmydetails);
		mnusellers.add(mnuitmviewslrs);
		mnusellers.add(mnuitemhistory);
		mnureport.add(mnuitmdispute);
		
		//attach menu to menubar-- 
		menubar.add(mnulist);
		menubar.add(mnusellers);
		menubar.add(mnureport);
		
		//attach menubar to window
		this.setJMenuBar(menubar);
		this.setContentPane(new JLabel((new ImageIcon("image\\b (3).jpg"))));
		this.add(btnlogout);
		
		
		
		
		
		
		
		//catagory String
		String[] categorylist = { "Arts & Crafts","Automobiles","Books","Cosmetics","Confectionary","Electroics", "Garments", "Footwear",
		"Mens","Womens","Kids","Jewellery","Musical Systems","Home & kitchens","Entertainment","Sports","Catagory" };
		
		comboBox = new JComboBox(categorylist);
		comboBox.setEditable(false);
		comboBox.setSelectedIndex(16);
		
		//adding items to panel
		pnllevel.add(lblwlcm);
		pnlsearch.add(lblsearch);
		pnlsearch.add(txtsearch);
		pnlsearch.add(comboBox);
		pnlsearch.add(btnsearch);
		
		this.add(pnllevel);
		
		this.add(pnlsearch);
		pnlsearch.setBounds(130,85,430,26);
		pnlsearch.setOpaque(false);
		
		pnllevel.setBounds(68, 6, 300, 40);
		pnllevel.setOpaque(false);
		pnlsearch.setLayout(new GridLayout(1,2,8,15));
		
		
		pnlcnt.add(lblseller);
		pnlcnt.add(txtseller);
		pnlcnt.add(btnlistslr);
		pnlcnt.add(lblitem);
		pnlcnt.add(txtitem);
		pnlcnt.add(btnlistitem);
		pnlcnt.add(lbllastitem);
		pnlcnt.add(txtlastitem);
		
		pnlcnt.setLayout(new FlowLayout());
		pnlcnt.setOpaque(false);
		pnlcnt.setBounds(17, 30, 430, 100);
		
		
		pnlout.add(pnlcnt);
		pnlout.setLayout(null);		
		pnlout.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlout.setBounds(115, 145, 460, 160);
		pnlout.setBackground(new Color(0,0,0,40));
		
		btnlistitem.setBackground(new Color(72,209,204));
		btnlistitem.setForeground(Color.WHITE);
		btnlistitem.setFocusPainted(false);
		btnlistitem.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnlistslr.setBackground(new Color(72,209,204));
		btnlistslr.setForeground(Color.WHITE);
		btnlistslr.setFocusPainted(false);
		btnlistslr.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnsearch.setBackground(new Color(72,209,204));
		btnsearch.setForeground(Color.WHITE);
		btnsearch.setFocusPainted(false);
		btnsearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnlogout.setBounds(600, 5,60,60);
		btnlogout.setOpaque(false);
		btnlogout.setContentAreaFilled(false);
		btnlogout.setBorderPainted(false);
		btnlogout.setFocusPainted(false);
		btnlogout.setToolTipText("Logout");
		
		
		this.add(pnlout);
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(700, 400);
		this.setLocation(340, 180);
		this.setResizable(false);
		this.setTitle("Welcome "+buyeruserid);
		
		//Registering 
		mnuitemlogout.addActionListener(this);
		mnusellers.addActionListener(this);
		mnuitemhistory.addActionListener(this);
		mnuitmmydetails.addActionListener(this);
		btnsearch.addActionListener(this);
		mnuitmviewslrs.addActionListener(this);
		comboBox.addActionListener(this);
		mnuitmdispute.addActionListener(this);
		btnlistitem.addActionListener(this);
		btnlistslr.addActionListener(this);
		btnlogout.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent event){
		
		if(event.getSource()==mnuitemhistory||event.getSource()==btnlistitem){findUsername(buyeruserid);}
		if(event.getSource()==mnuitmmydetails){new ViewProfile(buyeruserid,"buyer"); }
		if(event.getSource()==mnuitmviewslrs||event.getSource()==btnlistslr){new ViewItem();}
		if(event.getSource()==mnuitemlogout||event.getSource()==btnlogout){new LoginPanel();this.dispose();}
		if(event.getSource()==mnuitmdispute){new DisputeUsers(buyeruserid,"buyer");}
		if(event.getSource()==btnsearch){
			String itemname=txtsearch.getText(); String itemcategory = (String) comboBox.getSelectedItem();
			
			if(itemname.isEmpty() && comboBox.getSelectedIndex()==16){
				JOptionPane.showMessageDialog(null,"Select Any category" , "Error Massage", JOptionPane.ERROR_MESSAGE);}
			else if(itemname.isEmpty()){new SearchItembyBuyer("",itemcategory,buyeruserid);this.dispose();}
			else if(comboBox.getSelectedIndex()==16){new SearchItembyBuyer(itemname,"",buyeruserid);this.dispose();}
			else{new SearchItembyBuyer(itemname,itemcategory,buyeruserid);this.dispose();}
		    }
	
	}

	//find user name from buyer database
	public void findUsername(String buyeruserid) {
		try{
			listbuyer=DBIOBUYER.readFromBuyerDB();
		}catch(Exception exp){System.out.println("No One Registered Yet");}
		
		if(listbuyer.size()>0){
			//read from array list
			for(UserBuyer userbuyer:listbuyer){
				if(buyeruserid.equals(userbuyer.getUserloginid())){new BuyingHistory(userbuyer.getUserloginid());}		
				}
			}
		}		 
	}	


