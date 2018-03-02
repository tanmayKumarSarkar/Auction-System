package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import databasehandeling.DBIOBUYER;
import databasehandeling.DBIODISPUTE;
import databasehandeling.DBIOSELLER;
import main.UserBuyer;
import main.UserDispute;
import main.UserSeller;

public class AdminLogin extends JFrame implements ActionListener{
	private JMenuBar menubar;
	private JMenu mnufile,mnulist,mnureport;
	private JMenuItem mnuitemseller,mnuitembuyer,mnuitemlogout,mnuitemexit,mnuitemdispute,mnuitemsell;
	private JButton btnlogout,btnlistbuyer,btnlistseller,btnlistdispute,btnexit;
	private JLabel lvladmin,lvlseller,lvlbuyer,lvldispute;
	private JPanel pnllevel,pnlinfo,pnladd;
	private JTextField txtseller,txtbuyer,txtdispute;
	private int slr=0,byr=0,dspt=0;
	private List<UserSeller> listseller;
	private List<UserBuyer> listbuyer;
	private List<UserDispute> listdispute;
	
	public AdminLogin(){
		
		try{
			listseller=DBIOSELLER.readFromSellerDB();
		}catch(Exception exp){System.out.println("File not Found");}
		if(listseller.size()>0){for(UserSeller userseller:listseller){++slr;}}
		try{
			listbuyer=DBIOBUYER.readFromBuyerDB();
		}catch(Exception exp){System.out.println("File not Found");}
		if(listbuyer.size()>0){for(UserBuyer userbuyer:listbuyer){++byr;}}
		try{
			listdispute=DBIODISPUTE.readFromDisputeDB();
		}catch(Exception exp){System.out.println("File not Found");}
		if(listdispute.size()>0){for(UserDispute userdispute:listdispute){++dspt;}}
		
		menubar=new JMenuBar();
		mnufile=new JMenu("File");
		mnulist=new JMenu("User List");
		mnureport=new JMenu("Report");
		//create submenu--
		mnuitemseller=new JMenuItem("Registered Seller");
		mnuitembuyer=new JMenuItem("Registered Buyer");
		mnuitemlogout=new JMenuItem("Log Out");
		mnuitemexit=new JMenuItem("Exit");
		mnuitemdispute=new JMenuItem("Dispute Report");
		mnuitemsell=new JMenuItem("Selling Report");
		//creating panel,lebel,button
		
		btnlogout=new JButton(new ImageIcon(((new ImageIcon("image//btn.png").getImage().getScaledInstance(40, 40,java.awt.Image.SCALE_SMOOTH)))));	
		btnlistbuyer=new JButton("See List");
		btnlistseller=new JButton("See List");
		btnlistdispute=new JButton("See List");
		
		
		
		txtseller=new JTextField("  "+slr+"  ");txtseller.setEditable(false);
		txtbuyer=new JTextField("  "+byr+"  ");txtbuyer.setEditable(false);
		txtdispute=new JTextField("  "+dspt+"  ");txtdispute.setEditable(false);
		
		lvladmin=new JLabel("Welcome Admin");
		lvlseller=new JLabel("Number of total Registered Sellers     ");
		lvlseller.setForeground(new Color(0,0,0,199));
		lvlseller.setFont(new Font("Serif",Font.BOLD,17));
		lvlbuyer=new JLabel("Number of total Register Buyer           ");
		lvlbuyer.setForeground(new Color(0,0,0,199));
		lvlbuyer.setFont(new Font("Serif",Font.BOLD,17));
		lvldispute=new JLabel("You have total Dispute Reports            ");
		lvldispute.setForeground(new Color(0,0,0,199));
		lvldispute.setFont(new Font("Serif",Font.BOLD,17));
		
		lvladmin.setFont(new Font("Serif",Font.HANGING_BASELINE,25));
		lvladmin.setForeground(Color.darkGray);
		
		pnllevel=new JPanel();
		pnlinfo=new JPanel();
		pnladd=new JPanel();
		
		//attach submenu to menu--
		mnulist.add(mnuitembuyer);
		mnulist.add(mnuitemseller);
		mnufile.add(mnuitemlogout);
		mnufile.add(mnuitemexit);
		mnureport.add(mnuitemdispute);
		mnureport.add(mnuitemsell);
		//attach menu to menubar-- 
		menubar.add(mnufile);
		menubar.add(mnulist);
		menubar.add(mnureport);
		//attach menubar to window
		this.setJMenuBar(menubar);
		this.setContentPane(new JLabel(new ImageIcon("image\\b (3).jpg")));
		
		pnladd.setLayout(new FlowLayout());
		pnladd.setOpaque(false);
		
		pnladd.add(lvlbuyer);
		pnladd.add(txtbuyer);
		pnladd.add(btnlistbuyer);
		
		pnladd.add(lvlseller);
		pnladd.add(txtseller);
		pnladd.add(btnlistseller);
		
		pnladd.add(lvldispute);
		pnladd.add(txtdispute);
		pnladd.add(btnlistdispute);
		
		pnlinfo.setLayout(null);
		pnlinfo.add(pnladd);
		pnlinfo.getComponent(0).setBounds(17,50, 430, 100);
		pnlinfo.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlinfo.setBounds(115, 70, 460, 195);
		pnlinfo.setBackground(new Color(0,0,0,40));
		
		//adding items to panel
		pnllevel.add(lvladmin);
		pnllevel.add(btnlogout);
		pnllevel.setLayout(null);
		lvladmin.setBounds(250, 15, 200, 30);
		btnlogout.setBounds(600, 5,60,60);
		pnllevel.setBounds(0, 0, 700, 400);
		pnllevel.setOpaque(false);
		


		btnlogout.setOpaque(false);
		btnlogout.setContentAreaFilled(false);
		btnlogout.setBorderPainted(false);
		btnlogout.setFocusPainted(false);
		btnlogout.setToolTipText("Logout");
		
		btnlistbuyer.setBackground(new Color(72,209,204));
		btnlistbuyer.setForeground(Color.WHITE);
		btnlistbuyer.setFocusPainted(false);
		btnlistbuyer.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnlistseller.setBackground(new Color(72,209,204));
		btnlistseller.setForeground(Color.WHITE);
		btnlistseller.setFocusPainted(false);
		btnlistseller.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnlistdispute.setBackground(new Color(72,209,204));
		btnlistdispute.setForeground(Color.WHITE);
		btnlistdispute.setFocusPainted(false);
		btnlistdispute.setFont(new Font("Tahoma", Font.BOLD, 12));

		
		add(pnllevel);pnllevel.setVisible(true);
		add(pnlinfo);
		
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(700, 400);
		this.setLocation(340, 180);
			
		this.setTitle("ADMIN PANEL");
		mnuitemseller.addActionListener(this);
		mnuitembuyer.addActionListener(this);
		mnuitemlogout.addActionListener(this);
		mnuitemexit.addActionListener(this);
		mnuitemdispute.addActionListener(this);
		mnuitemsell.addActionListener(this);
		btnlogout.addActionListener(this);
		btnlistbuyer.addActionListener(this);
		btnlistseller.addActionListener(this);
		btnlistdispute.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent event){
		
		if(event.getSource()==mnuitemexit){System.exit(0);}
		if(event.getSource()==btnlogout){new LoginPanel();this.dispose();}
		if(event.getSource()==mnuitemlogout){new LoginPanel();this.dispose();}
		if(event.getSource()==mnuitembuyer){new ViewBuyer();}
		if(event.getSource()==mnuitemseller){new ViewSeller();}
		if(event.getSource()==mnuitemsell){new ViewSoldItem();}
		if(event.getSource()==mnuitemdispute){new ViewDisputeByAdmin();this.dispose();}
		if(event.getSource()==btnlistbuyer){new ViewBuyer();}
		if(event.getSource()==btnlistseller){new ViewSeller();}
		if(event.getSource()==btnlistdispute){new ViewDisputeByAdmin();this.dispose();}
	}

	public static void main(String[] args){
		
		//new AdminLogin();
		
		} 
	}	


