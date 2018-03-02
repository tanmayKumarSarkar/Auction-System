package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import databasehandeling.DBIOITEM;
import main.UserItem;



public class ViewSellerItem {

	Object colNames[] = {"select","Product ID","Item Name","Item Catagory","Item Price","Item Quantity","Seller Name"};
	Object[][] data = {};
	DefaultTableModel dtm;
	JTable table;
	private List<UserItem> listitem;
	private JButton btn;
	UserItem ref;
	private int rowno;
	private String sellerID;
	
	public ViewSellerItem(String sellerid,String sellername){
		
		dtm = new DefaultTableModel(data,colNames);
	    table = new JTable(dtm);
	    btn=new JButton("btn");
	    this.sellerID=sellerid;
	    int count=0;
	    
	    try{
			listitem=DBIOITEM.readFromItemDB();
		}catch(Exception exp){System.out.println("File not Found");}

	    
	    if(listitem.size()>0){
	    for(UserItem item:listitem)
	    {if(sellername.equalsIgnoreCase(item.getSeller())){
	    	
	      dtm.addRow(new Object[]{new Boolean(false),""+item.getItemid(),item.getItemname(),item.getCatagory(),""+item.getPrice(),""+item.getQuantity(),item.getSeller()});
	      ++count;
	    }this.rowno=count;}
	    }
	    JTableHeader header = table.getTableHeader();
	    header.setBackground(Color.yellow);
	    JScrollPane sp = new JScrollPane(table);
	    TableColumn tc = table.getColumnModel().getColumn(0);
	    tc.setCellEditor(table.getDefaultEditor(Boolean.class));
	    tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	    
	    JPanel btnPnl = new JPanel(new BorderLayout());
	    JPanel bottombtnPnl = new JPanel(new FlowLayout());
	    JButton btnback = new JButton("Back");
	    JButton btnupdate = new JButton("Update");
	    JButton btndelete=new JButton("DELETE");
	    bottombtnPnl.add(btnback);
	    bottombtnPnl.add(btnupdate);
	    bottombtnPnl.add(btndelete);
	    btnPnl.add(bottombtnPnl, BorderLayout.CENTER);
	    table.enableInputMethods(false);
	    
	    final JFrame frame = new JFrame("My Items");
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
	   
	    
	    btnback.setBackground(new Color(72,209,204));
		btnback.setForeground(Color.WHITE);
		btnback.setFocusPainted(false);
		btnback.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnupdate.setBackground(new Color(72,209,204));
		btnupdate.setForeground(Color.WHITE);
		btnupdate.setFocusPainted(false);
		btnupdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btndelete.setBackground(new Color(72,209,204));
		btndelete.setForeground(Color.WHITE);
		btndelete.setFocusPainted(false);
		btndelete.setFont(new Font("Tahoma", Font.BOLD, 12));
	    
	    //action when button update is clicked
	    btnupdate.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            
	        	int ck=0;
	        	try{
	        		int x=rowno;
	        	    for( int i=0;i<x;i++){

	           	    	boolean nnf=(boolean) table.getModel().getValueAt(i,0);
	           	    	String itemid=(String)table.getModel().getValueAt(i,1);
	           	    	String nameitem=(String) table.getModel().getValueAt(i,2);
	           	    	String catagory=(String) table.getModel().getValueAt(i,3);
	           	    	String itemprc=(String) table.getModel().getValueAt(i,4);
	        	    	String itemqnt=(String) table.getModel().getValueAt(i,5);
	        	    	String nameseller=(String) table.getModel().getValueAt(i,6);
	        	    	
	        	    	int itemID=Integer.parseInt(itemid);
	        	    	int itemquantity=Integer.parseInt(itemqnt);
	        	    	double price=Double.parseDouble(itemprc);
	        	    		        	    	
	        	    	if(nnf==true){
	        	    		//JOptionPane.showMessageDialog(null, "working");
	        	    		ck=1;
	        	    		new	UpdateItem(itemID,nameitem,catagory,price,itemquantity, nameseller);
	        	    		frame.dispose();
	        	    		}
	        	    	
	        	    } }catch(ArrayIndexOutOfBoundsException ex) {
	        	    			// System.out.println("Array index out of bounds.");
	        	    		} 
	        	if(ck==0){JOptionPane.showMessageDialog(null,"no item selected");}}});

	    
	    btnback.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent e) { frame.dispose();}});
	    
	    //delete button action
	    btndelete.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent e) {
	    		int ck=0;
	        	try{
	        		int x=rowno;
	        	    for( int i=0;i<x;i++){

	           	    	boolean nnf=(boolean) table.getModel().getValueAt(i,0);
	           	    	String itemid=(String)table.getModel().getValueAt(i,1);
	           	    	String nameitem=(String) table.getModel().getValueAt(i,2);	
	        	    	String nameseller=(String) table.getModel().getValueAt(i,6);
	        	    	
	        	    	int itemID=Integer.parseInt(itemid);
		        	    	
	        	    	if(nnf==true){
	        	    		int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete "+nameitem,"Confirm Delete", JOptionPane.YES_NO_OPTION);
	                        if (reply == JOptionPane.YES_OPTION){
	    		               new DeleteItem(itemID,nameseller,nameitem);
	    		               frame.dispose();
	    		               new ViewSellerItem(sellerID,nameseller);}
	        	    	}}}
	        	catch(ArrayIndexOutOfBoundsException ex) {
	    			// System.out.println("Array index out of bounds.");
	    		}}});
	    		
	    
	    
  }//end of the constructor
}
