package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import main.UserItem;
import main.UserSold;
import databasehandeling.DBIOITEM;
import databasehandeling.DBIOSOLD;


public class SearchItembyBuyer {
	
	//row header
	Object colNames[] = {"select","ItemID","Catagory","Item Name","Price","Available","Seller Name"};
	Object[][] data = {};
	DefaultTableModel dtm;
	JTable table;
	private int rowno;
	private List<UserItem> listitem;UserItem ref;
	private String userid,ctgry,itmnm;
	private List<UserSold>listsold;
	
	
public SearchItembyBuyer( String itemname, String itemcatagory,String buyeruserid){
		listsold = new ArrayList<UserSold>();
		dtm = new DefaultTableModel(data,colNames);
	    table = new JTable(dtm);
	    
	    this.userid=buyeruserid;
	    this.ctgry=itemcatagory;
	    this.itmnm=itemname;
	    int count=0;//initailisation of counter
	    try{
			listsold=DBIOSOLD.readFromSoldDB();
		}catch(Exception exp){System.out.println("File not Found");}
 
	     
	     try{
			listitem=DBIOITEM.readFromItemDB();
		}catch(Exception exp){System.out.println("File not Found");}
 
	    //when item name isn't given
	    if(itemname.isEmpty()){
	    	for(UserItem item:listitem){
	    		if(itemcatagory.equals(item.getCatagory())){
	    			++count;
				}
	    	}this.rowno=count;
	    
	    if(listitem.size()>0){
	    for(UserItem item:listitem)
	    {if(itemcatagory.equalsIgnoreCase(item.getCatagory())){
	      dtm.addRow(new Object[]{new Boolean(false),item.getItemid(),item.getCatagory(),item.getItemname(),""+item.getPrice(),""+item.getQuantity(),item.getSeller()});
	     }
	    }
	   }
	  }
	    
	    //if catagory isn't given
	    else if(itemcatagory.isEmpty()){
		for(UserItem item:listitem){
			if(itemname.equals(item.getItemname())){
				++count;}
					}this.rowno=count;
					
		for(UserItem item:listitem){
			if(itemname.equalsIgnoreCase(item.getItemname())){
		dtm.addRow(new Object[]{new Boolean(false),item.getItemid(),item.getCatagory(),item.getItemname(),""+item.getPrice(),""+item.getQuantity(),item.getSeller()});
	    }}}
	    
	    
	    //other case
	    else{count=0;
			for(UserItem item:listitem){
				if(itemcatagory.equalsIgnoreCase(item.getCatagory())&&itemname.equalsIgnoreCase(item.getItemname())){
				++count;}
			}this.rowno=count;
			
		for(UserItem item:listitem){
				if(itemcatagory.equalsIgnoreCase(item.getCatagory())&&itemname.equalsIgnoreCase(item.getItemname())){
		dtm.addRow(new Object[]{new Boolean(false),item.getItemid(),item.getCatagory(),item.getItemname(),""+item.getPrice(),""+item.getQuantity(),item.getSeller()});
		}}}
	    
	    
	    JTableHeader header = table.getTableHeader();
	    header.setBackground(Color.yellow);
	    JScrollPane sp = new JScrollPane(table);
	    TableColumn tc = table.getColumnModel().getColumn(0);
	   	tc.setCellEditor(table.getDefaultEditor(Boolean.class));
	    tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	    
	    JPanel btnPnl = new JPanel(new BorderLayout());
	    JPanel bottombtnPnl = new JPanel(new FlowLayout());
	    JButton btnback = new JButton("BACK");
	    JButton btnbuy = new JButton("BUY");
	    bottombtnPnl.add(btnback);
	    bottombtnPnl.add(btnbuy);
	    btnPnl.add(bottombtnPnl, BorderLayout.CENTER);
	    table.enableInputMethods(false);
	    
	    //designing with JFrame
	    final JFrame frame = new JFrame("Buy Items");
	    frame.setLayout(new BorderLayout());
	    frame.getContentPane().add(sp);
	    frame.add(table.getTableHeader(), BorderLayout.NORTH);
	    frame.add(table, BorderLayout.CENTER);
	    frame.add(btnPnl, BorderLayout.SOUTH);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    frame.setSize(700,400);
	    frame.setLocation(340, 180);
	    frame.setResizable(false);
	   
	    //customizing buttons
	    btnback.setBackground(new Color(72,209,204));
		btnback.setForeground(Color.WHITE);
		btnback.setFocusPainted(false);
		btnback.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnbuy.setBackground(new Color(72,209,204));
		btnbuy.setForeground(Color.WHITE);
		btnbuy.setFocusPainted(false);
		btnbuy.setFont(new Font("Tahoma", Font.BOLD, 12));
	  
	    btnbuy.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            
	        	int ck=0;
	        	try{
	        		int x=rowno+1;
	        	    for( int i=0;i<x;i++){
	        	    	Date dateob=new Date();
	        	    	DateFormat dt;
	        	    	dt=DateFormat.getDateInstance(DateFormat.SHORT);
	        	    	String dts=dt.format(dateob);
	        	    	
	           	    	boolean nnf=(boolean) table.getModel().getValueAt(i,0);
	        	    	String nameseller=(String) table.getModel().getValueAt(i,6);
	        	    	String nameitem=(String) table.getModel().getValueAt(i,3);
	        	    	String itemqnt=(String) table.getModel().getValueAt(i,5);
	        	    	String itemprc=(String) table.getModel().getValueAt(i,4);
	        	    	String catagory=(String) table.getModel().getValueAt(i,2);
	        	    	int itemid=(int)table.getModel().getValueAt(i,1);
	        	    	
	        	    	int itemquantity=Integer.parseInt(itemqnt);
	        	    	double price=Double.parseDouble(itemprc);
	        	    		        	    	
	        	    	if(nnf==true){
	        	    		int quntity=0;			
	        	    		String qntt=(JOptionPane.showInputDialog("How Many "+nameitem+" you want to Buy"));
	        	    		if(!"".equals(qntt) || qntt!=null){try{
	        	    			
	        	    		quntity=Integer.parseInt(qntt);
	        	    		
	        	    		if(quntity>itemquantity){JOptionPane.showMessageDialog(null, "You can buy only "+itemquantity+" items");ck=1;}
	        	    		
	        	    		if(quntity==0||qntt==null){JOptionPane.showMessageDialog(null, "You can't but zero "+nameitem,"Error",JOptionPane.ERROR_MESSAGE);}
	        	    		else if(quntity<=itemquantity) {
	        	    			try{
	        	    				int itemavailable=(itemquantity-quntity);
	        	    				new SellingItem(itemid,nameseller,nameitem,catagory,price,itemavailable);
	        	    				JOptionPane.showMessageDialog(null,"You bougth "+quntity+" "+nameitem+" From "+nameseller);ck=1;
	        	    				listsold.add(sendsolditems(itemid,nameitem,catagory,nameseller,userid,quntity,price,dts));
	        	    				DBIOSOLD.writeToSoldDB(listsold); 
	        	    				frame.dispose();
	        	    				new SearchItembyBuyer(itmnm,ctgry,userid);
	        	    				}
	 catch(Exception exp){
	        				exp.printStackTrace();
	        			 }
	        	        }
	        	       }catch (NumberFormatException o) {
	                        //System.out.println("enter a number");
	                    }
	        	      }ck=1;	        	    
	        	    }
	        	  }
	        	    
	        	}catch(ArrayIndexOutOfBoundsException ex) {
	               //System.out.println("Array index out of bounds.");
	            } 
	        	if(ck==0){JOptionPane.showMessageDialog(null,"no item selected");}}});
	    
	    //action button back
	    btnback.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {frame.dispose();new BuyerPanel(userid);}});
	    
	    //if searched item is not available
	    //if(rowno==0){JOptionPane.showMessageDialog(null,"Sorry item is not availabe");frame.dispose();}
     }//end of the constructor

public UserSold sendsolditems(int id1, String nameitem, String catagory, String nameseller, String userid, int itemquantity, double price, String dts){
	
	UserSold usersold=new UserSold(id1,nameitem,catagory,nameseller,userid,itemquantity,price,dts);
	return usersold;
	
 }
}