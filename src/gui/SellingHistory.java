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
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import databasehandeling.DBIOSOLD;
import main.UserItem;
import main.UserSold;

public class SellingHistory {

	Object colNames[] = {"ID","catagory","Item Name","Price","Quantity","Buyer","Date"};
	Object[][] data = {};
	DefaultTableModel dtm;
	JTable table;
	private JButton btnback;
	private int rc=0;
	private List<UserSold> listbuyitem;
	private JButton btn;
	UserItem ref;
	private String namebuyer;
	public SellingHistory(String sellername){
		
			dtm = new DefaultTableModel(data,colNames);
		    table = new JTable(dtm);
		    btn=new JButton("btn");
		    this.namebuyer=sellername;
		    
		    try{
				listbuyitem=DBIOSOLD.readFromSoldDB();
			}catch(Exception exp){System.out.println("File not Found");}

		    
		    if(listbuyitem.size()>0){
		    for(UserSold item:listbuyitem)
		    {
		    	if(sellername.equalsIgnoreCase(item.getSeller())){
		    		rc++;
		    		dtm.addRow(new Object[]{""+item.getItemid(),item.getCatagory(),item.getItemname(),""+item.getPrice(),""+item.getQuantity(),item.getBuyer(),item.getBuyingdate()});
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
		    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		    table.getColumnModel().getColumn(0).setPreferredWidth(27);
		    table.getColumnModel().getColumn(1).setPreferredWidth(100);
		    table.getColumnModel().getColumn(2).setPreferredWidth(176);
		    table.getColumnModel().getColumn(3).setPreferredWidth(90);
		    table.getColumnModel().getColumn(4).setPreferredWidth(68);
		    table.getColumnModel().getColumn(5).setPreferredWidth(100);
		    
		    final JFrame frame = new JFrame("Total Sold Items");
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
		    
		    btnback.setBackground(new Color(72,209,204));
			btnback.setForeground(Color.WHITE);
			btnback.setFocusPainted(false);
			btnback.setFont(new Font("Tahoma", Font.BOLD, 12));
		    
		    if(rc==0){JOptionPane.showMessageDialog(null, "No Items Sold");;frame.dispose();}
		
		    btnback.addActionListener(new ActionListener() {

		        @Override
		        public void actionPerformed(ActionEvent e) {frame.dispose(); }});
	}
}
