package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import databasehandeling.DBIOBUYER;
import main.UserBuyer;
public class ViewBuyer extends JFrame implements ActionListener{
	private JTable Table;
	private JButton btnback;
	private List<UserBuyer> listbuyer;
	public ViewBuyer(){
		
		String[] heading={"ID","User Name","Address","phone","PIN Code","Email Address","Login ID"};
		//read from file
		try{
			listbuyer=DBIOBUYER.readFromBuyerDB();
		}catch(Exception exp){System.out.println("File not Found");}
		if(listbuyer.size()>0){
			String[][] data=new String[listbuyer.size()][7];
			//read from arraylist
			int row=0,col=0;
			for(UserBuyer userbuyer:listbuyer){
				data[row][col]=""+userbuyer.getId();
				data[row][++col]=userbuyer.getName();
				data[row][++col]=userbuyer.getUseraddress();
				data[row][++col]=userbuyer.getuserphnno();
				data[row][++col]=userbuyer.getpincode();
				data[row][++col]=userbuyer.getUseremail();
				data[row][++col]=userbuyer.getUserloginid();
				col=0;++row;
			}
			 Table=new JTable(data,heading);
			 add(new JScrollPane(Table));
		}
		JTableHeader header = Table.getTableHeader();
	    header.setBackground(Color.yellow);
	    
	    Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    Table.getColumnModel().getColumn(0).setPreferredWidth(27);
	    Table.getColumnModel().getColumn(1).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(2).setPreferredWidth(120);
	    Table.getColumnModel().getColumn(3).setPreferredWidth(90);
	    Table.getColumnModel().getColumn(4).setPreferredWidth(68);
	    Table.getColumnModel().getColumn(5).setPreferredWidth(176);
	    Table.getColumnModel().getColumn(6).setPreferredWidth(100);
	    
	    JPanel btnPnl= new JPanel();
	    btnback = new JButton("Close");
	    btnPnl.add(btnback);
	   
	    
		this.setVisible(true);
		this.setSize(700,400);
		this.setLocation(340, 180);
		this.setTitle("View All Registered Buyer");
		this.setLayout(new BorderLayout());
		this.add(Table.getTableHeader(), BorderLayout.NORTH);
	    this.add(Table, BorderLayout.CENTER);
	    this.add(btnPnl, BorderLayout.SOUTH);
		
	    btnback.setBackground(new Color(72,209,204));
		btnback.setForeground(Color.WHITE);
		btnback.setFocusPainted(false);
		btnback.setFont(new Font("Tahoma", Font.BOLD, 12));
	   btnback.addActionListener(this);
	    
	}
	public void actionPerformed(ActionEvent evnt){
		if(evnt.getSource()==btnback){this.dispose();}
	}

}
