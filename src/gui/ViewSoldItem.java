package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import databasehandeling.DBIOSOLD;
import main.UserSold;
public class ViewSoldItem extends JFrame implements ActionListener{
	private JTable Table;
	private JButton btnback;
	private List<UserSold> listsold;
	private int rc=0;
	
	public ViewSoldItem(){
		
		String[] heading={" ID", "Item Name","Catagory","Seller","Buyer","Quantity","Price","Date Of Buying"};
		//read from file
		try{
			listsold=DBIOSOLD.readFromSoldDB();
		}catch(Exception exp){System.out.println("File not Found");}
		if(listsold.size()>0){
			String[][] data=new String[listsold.size()][8];
			//read from arraylist
			int row=0,col=0;
			for(UserSold usersold:listsold){
				
				data[row][col]=""+usersold.getItemid();
				data[row][++col]=usersold.getItemname();
				data[row][++col]=usersold.getCatagory();
				data[row][++col]=usersold.getSeller();
				data[row][++col]=usersold.getBuyer();
				data[row][++col]=""+usersold.getQuantity();
				data[row][++col]=""+usersold.getPrice();
				data[row][++col]=""+usersold.getBuyingdate();
				col=0;++row;rc++;
			}
			 Table=new JTable(data,heading);
			 add(new JScrollPane(Table));
		}
		JTableHeader header = Table.getTableHeader();
		header.setBackground(Color.yellow);
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    Table.getColumnModel().getColumn(0).setPreferredWidth(27);
	    Table.getColumnModel().getColumn(1).setPreferredWidth(120);
	    Table.getColumnModel().getColumn(2).setPreferredWidth(120);
	    Table.getColumnModel().getColumn(3).setPreferredWidth(90);
	    Table.getColumnModel().getColumn(4).setPreferredWidth(68);
	    Table.getColumnModel().getColumn(5).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(6).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(7).setPreferredWidth(100);
	    
		
		JPanel btnPnl= new JPanel();
		btnback = new JButton("Close");
		btnPnl.add(btnback);
		
		//this.setResizable(false);
		this.setVisible(true);
		this.setSize(700,400);
		this.setLocation(340, 180);
		this.setLayout(new BorderLayout());
		this.add(Table.getTableHeader(), BorderLayout.NORTH);
		this.add(Table, BorderLayout.CENTER);
		this.add(btnPnl, BorderLayout.SOUTH);
		this.setTitle("Viewing All Sold Report");
		
		btnback.setBackground(new Color(72,209,204));
		btnback.setForeground(Color.WHITE);
		btnback.setFocusPainted(false);
		btnback.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnback.addActionListener(this);
		
		if(rc==0){JOptionPane.showMessageDialog(null, "No Item sold");;this.dispose();}
	}
	public void actionPerformed(ActionEvent evnt){
		if(evnt.getSource()==btnback){this.dispose();}
	}
}
