package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import databasehandeling.DBIOITEM;
import databasehandeling.DBIOSELLER;
import databasehandeling.DBIOSOLD;
import main.UserItem;
import main.UserSeller;
import main.UserSold;

@SuppressWarnings("serial")
public class SellerPanel extends JFrame implements ActionListener{
	private JMenuBar menubar;
	private JMenu mnuprofile,mnuitem,mnuhistory,mnureport;
	private JMenuItem mnuitemadditems,mnuitemlogout,mnuitemmydetails,mnuitemsolditem,mnuitemviewitem,mnuitmdispute;
	private JButton btnlogout,btnitm,btnslditm,btnadditm,btnupdateitm,btnreport;
	private JLabel lblwlcm,lblname,lblitm,lblslditm;
	private JTextField txtitm,txtslditm;
	private JPanel pnllevel,pnlcnt,pnlout,pnlbtn;
	private String selleruserid,selleroname;
	private List<UserSeller>listseller;
	private List<UserSold>listsold;
	private List<UserItem>listitem;UserItem refitem;
	
	public SellerPanel(String UserId){
		this.selleruserid=UserId;
		int count=0,solditem=0;
		
		try{
			listsold=DBIOSOLD.readFromSoldDB();
		}catch(Exception exp){System.out.println("File not Found");}
		
		try{
			listitem=DBIOITEM.readFromItemDB();
		}catch(Exception exp){System.out.println("File not Found");}
		
		try{
			listseller=DBIOSELLER.readFromSellerDB();
		}catch(Exception exp){System.out.println("No One Registered Yet");}
		
		if(listseller.size()>0){
			//read from array list
			for(UserSeller userseller:listseller){
				if(selleruserid.equals(userseller.getUserloginid())){this.selleroname=userseller.getName();}}
		}
		
		if(listitem.size()>0){
		    for(UserItem item:listitem)
		    {if(selleroname.equalsIgnoreCase(item.getSeller())){
		      ++count;
		    }}}
		
		if(listsold.size()>0){
		    for(UserSold item:listsold){if(selleroname.equals(item.getSeller())){++solditem;}
		}}
		    
		
		menubar=new JMenuBar();
		mnuprofile=new JMenu("My Profile");
		mnuitem=new JMenu("Items");
		mnuhistory=new JMenu("View History");
		mnureport=new JMenu("Report");
		
		//create submenu--
		mnuitemadditems=new JMenuItem("Add Items");
		mnuitemlogout=new JMenuItem("Log Out");
		mnuitemmydetails=new JMenuItem("My Details");
		mnuitemsolditem=new JMenuItem("Sold Items");
		mnuitemviewitem=new JMenuItem("View Items");
		mnuitmdispute=new JMenuItem("Dispute Report");
		//creating panel,lebel,button,combobox,textfield
		btnlogout=new JButton(new ImageIcon(((new ImageIcon("image//btn.png").getImage().getScaledInstance(40, 40,java.awt.Image.SCALE_SMOOTH)))));
		btnitm=new JButton("See List");
		btnslditm=new JButton("See List");
		btnadditm=new JButton("Add Items");
		btnupdateitm=new JButton("Update Item");
		btnreport=new JButton("Report");
		
		lblwlcm=new JLabel("Welcome ");lblwlcm.setFont(new Font("Serif",Font.ROMAN_BASELINE,20));
		lblname=new JLabel(selleroname);lblname.setFont(new Font("Serif",Font.ROMAN_BASELINE,20));
		
		lblitm=new JLabel(" Your Total Items     ");
		lblitm.setForeground(new Color(0,0,0,199));
		lblitm.setFont(new Font("Serif",Font.BOLD,17));
		
		lblslditm=new JLabel(" Your Sold Items      ");
		lblslditm.setForeground(new Color(0,0,0,199));
		lblslditm.setFont(new Font("Serif",Font.BOLD,17));
		
		pnllevel=new JPanel();
		pnlcnt=new JPanel();
		pnlout=new JPanel();
		pnlbtn=new JPanel();
		
		txtitm=new JTextField(" "+count+" ",6);txtitm.setEditable(false);
		txtslditm=new JTextField(" "+solditem+" ",6);txtslditm.setEditable(false);
				
		
		//attach submenu to menu--
		mnuitem.add(mnuitemadditems);
		mnuitem.add(mnuitemviewitem);
		mnuprofile.add(mnuitemlogout);
		mnuprofile.add(mnuitemmydetails);
		mnuhistory.add(mnuitemsolditem);
		mnureport.add(mnuitmdispute);
		//attach menu to menubar-- 
		menubar.add(mnuprofile);
		menubar.add(mnuitem);
		menubar.add(mnuhistory);
		menubar.add(mnureport);
		
		//attach menubar to window
		this.setJMenuBar(menubar);
		this.setContentPane(new JLabel(new ImageIcon("image\\b (3).jpg")));
		
		//adding items to panel
		pnllevel.add(lblwlcm);
		pnllevel.add(lblname);
		
		pnlbtn.add(btnadditm);
		pnlbtn.add(btnupdateitm);
		pnlbtn.add(btnreport);
		pnlbtn.setLayout(new GridLayout(1,3,20,20));
		pnlbtn.setBounds(136,236, 420,26);
		pnlbtn.setOpaque(false);
		
		pnlcnt.add(lblitm);
		pnlcnt.add(txtitm);
		pnlcnt.add(btnitm);
		pnlcnt.add(lblslditm);
		pnlcnt.add(txtslditm);
		pnlcnt.add(btnslditm);
		
		
		
		pnlcnt.setLayout(new FlowLayout());
		pnlcnt.setOpaque(false);
		pnlcnt.setBounds(17, 35, 430, 100);
		
		pnlout.add(pnlcnt);
		pnlout.setLayout(null);
		pnlout.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlout.setBounds(125, 65, 440, 220);
		pnlout.setBackground(new Color(0,0,0,40));
		
		this.add(btnlogout);
		btnlogout.setBounds(440, 9, 78, 25);
		btnlogout.setBounds(600, 5,60,60);
		btnlogout.setOpaque(false);
		btnlogout.setContentAreaFilled(false);
		btnlogout.setBorderPainted(false);
		btnlogout.setFocusPainted(false);
		btnlogout.setToolTipText("Logout");
		
		this.add(pnllevel);
		this.add(pnlbtn);
		this.add(pnlout);
		pnllevel.setLayout(new FlowLayout());
		pnllevel.setBounds(128, 6, 300, 40);
		pnllevel.setOpaque(false);

		this.setLayout(null);
		this.setVisible(true);
		this.setSize(700, 400);
		this.setLocation(340, 180);
		this.setResizable(false);
		this.setTitle("WELCOME  "+selleruserid);
		
		btnreport.setBackground(new Color(72,209,204));
		btnreport.setForeground(Color.WHITE);
		btnreport.setFocusPainted(false);
		btnreport.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnupdateitm.setBackground(new Color(72,209,204));
		btnupdateitm.setForeground(Color.WHITE);
		btnupdateitm.setFocusPainted(false);
		btnupdateitm.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnadditm.setBackground(new Color(72,209,204));
		btnadditm.setForeground(Color.WHITE);
		btnadditm.setFocusPainted(false);
		btnadditm.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnslditm.setBackground(new Color(72,209,204));
		btnslditm.setForeground(Color.WHITE);
		btnslditm.setFocusPainted(false);
		btnslditm.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnitm.setBackground(new Color(72,209,204));
		btnitm.setForeground(Color.WHITE);
		btnitm.setFocusPainted(false);
		btnitm.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		mnuitemadditems.addActionListener(this);
		mnuitemlogout.addActionListener(this);
		mnuhistory.addActionListener(this);
		mnuitem.addActionListener(this);
		mnuitemmydetails.addActionListener(this);
		btnlogout.addActionListener(this);
		mnuitemviewitem.addActionListener(this);
		mnuitemsolditem.addActionListener(this);
		mnuitmdispute.addActionListener(this);
		btnitm.addActionListener(this);;
		btnslditm.addActionListener(this);;
		btnadditm.addActionListener(this);;
		btnupdateitm.addActionListener(this);;
		btnreport.addActionListener(this);
		

	
	}
	public void actionPerformed(ActionEvent event){
		
		if(event.getSource()==mnuitemmydetails){new ViewProfile(selleruserid,"seller");}
		if(event.getSource()==mnuitemlogout||event.getSource()==btnlogout){new LoginPanel();this.dispose();}
		if(event.getSource()==mnuitemadditems||event.getSource()==btnadditm){new AddItem(selleruserid);this.dispose();}
		if(event.getSource()==mnuitemviewitem||event.getSource()==btnupdateitm||event.getSource()==btnitm){findUsername(selleruserid,1);}
		if(event.getSource()==mnuitemsolditem||event.getSource()==btnslditm){findUsername(selleruserid,2);}
		if(event.getSource()==mnuitmdispute||event.getSource()==btnreport){new DisputeUsers(selleruserid,"seller");}
	}

	public void findUsername(String selleruserid,int i) {
		try{
			listseller=DBIOSELLER.readFromSellerDB();
		}catch(Exception exp){System.out.println("No One Registered Yet");}
		
		if(listseller.size()>0){
			//read from array list
			for(UserSeller userseller:listseller){
				if(i==1){
				if(selleruserid.equals(userseller.getUserloginid())){this.selleroname=userseller.getName();new ViewSellerItem(selleruserid,userseller.getName());}		
				}
				else if(i==2){if(selleruserid.equals(userseller.getUserloginid())){this.selleroname=userseller.getName();new SellingHistory(selleroname);}}
			  }
			}
		
	}
	public static void main(String[] args){
		//new SellerPanel();
		} 
	}	


