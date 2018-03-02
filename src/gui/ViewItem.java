package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import databasehandeling.DBIOITEM;
import main.UserItem;
public class ViewItem extends JFrame implements ActionListener{
	private JTable Table;
	private JButton btnback;
	private List<UserItem> listitem;
	public ViewItem(){
		JButton btnedit=new JButton("Edit");
		btnedit.addActionListener(this);
		String[] heading={"ID","Seller Name","Item Name","Catagory","Price","Quantity"};
		//read from file
		try{
			listitem=DBIOITEM.readFromItemDB();
		}catch(Exception exp){System.out.println("File not Found");}
		if(listitem.size()>0){
			String[][] data=new String[listitem.size()][7];
			//read from arraylist
			int row=0,col=0;
			for(UserItem useritem:listitem){
				data[row][col]=""+useritem.getItemid();
				data[row][++col]=useritem.getSeller();
				data[row][++col]=useritem.getItemname();
				data[row][++col]=useritem.getCatagory();
				data[row][++col]=""+useritem.getPrice();
				data[row][++col]=""+useritem.getQuantity();
				col=0;++row;
			}
			 Table=new JTable(data,heading);
			 add(new JScrollPane(Table));
		}
		JTableHeader header = Table.getTableHeader();
	    header.setBackground(Color.yellow);
	    
	    Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    Table.getColumnModel().getColumn(0).setPreferredWidth(30);
	    Table.getColumnModel().getColumn(1).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(2).setPreferredWidth(175);
	    Table.getColumnModel().getColumn(3).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(4).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(5).setPreferredWidth(75);
	    
	    JPanel btnPnl= new JPanel();
	    btnback = new JButton("Close");
	    btnPnl.add(btnback);
	   
	    
		this.setVisible(true);
		this.setSize(700,400);
		this.setLocation(340, 180);
		this.setTitle("View All Items");
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
