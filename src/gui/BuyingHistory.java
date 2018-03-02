package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

import databasehandeling.DBIOSOLD;
import main.UserItem;
import main.UserSold;

public class BuyingHistory{

	Object colNames[] = {"ID","Catagory","Item Name","Price","Quantity","Seller","Date"};
	Object[][] data = {};
	DefaultTableModel dtm;
	JTable table;
	private List<UserSold> listitem;
	@SuppressWarnings("unused")
	private JButton btn;
	UserItem ref;
	@SuppressWarnings("unused")
	private String namebuyer;
	private int cr;
	public BuyingHistory(String buyername){
		
			dtm = new DefaultTableModel(data,colNames);
		    table = new JTable(dtm);
		    btn=new JButton("btn");
		    this.namebuyer=buyername;
		    
		    try{
				listitem=DBIOSOLD.readFromSoldDB();
			}catch(Exception exp){System.out.println("File not Found");}

		    
		    if(listitem.size()>0){
		    for(UserSold item:listitem)
		    {
		    	if(buyername.equalsIgnoreCase(item.getBuyer())){
		      dtm.addRow(new Object[]{""+item.getItemid(),item.getCatagory(),item.getItemname(),""+item.getPrice(),""+item.getQuantity(),item.getSeller(),item.getBuyingdate()});
		      ++cr;
		    	}}
		    }
		    JTableHeader header = table.getTableHeader();
		    header.setBackground(Color.yellow);
		    JScrollPane sp = new JScrollPane(table);
		    
		    
		    JPanel btnPnl = new JPanel(new BorderLayout());
		    JPanel bottombtnPnl = new JPanel(new FlowLayout());
		    JButton btnback = new JButton("Close");
		   
		    bottombtnPnl.add(btnback);
		    btnPnl.add(bottombtnPnl, BorderLayout.CENTER);
		    table.enableInputMethods(false);
		    
		    final JFrame frame = new JFrame("Total Items Bought");
		    frame.setLayout(new BorderLayout());
		    frame.getContentPane().add(sp);
		    frame.add(table.getTableHeader(), BorderLayout.NORTH);
		    frame.add(table, BorderLayout.CENTER);
		    frame.add(btnPnl, BorderLayout.SOUTH);
		    frame.pack();
		    frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
		    frame.setResizable(false);
		    frame.setVisible(true);
		    frame.setSize(700,400);
		    frame.setLocation(340, 180);
	
		    
		    
		    btnback.addActionListener(new ActionListener() {
		    	@Override
		        public void actionPerformed(ActionEvent e) {frame.dispose(); }});
		if(cr==0){JOptionPane.showMessageDialog(null, "You have not buy any thing yet");
		frame.dispose();}
	}
	
}
