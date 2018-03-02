package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

import databasehandeling.DBIODISPUTE;
import main.UserDispute;




public class ViewDisputeByAdmin {//implements ActionListener{

	Object colNames[] = {"select","ID","User Name","Report","User Type","Report Date","Status"};
	Object[][] data = {};
	DefaultTableModel dtm;
	JTable table;
	private List<UserDispute> listdispute;
	@SuppressWarnings("unused")
	private JButton btn;
	private int cr=0;
	private JTextArea textArea;
    private JScrollPane scrollPane;
	
	
	public ViewDisputeByAdmin(){
		
		dtm = new DefaultTableModel(data,colNames);
	    table = new JTable(dtm);
	    btn=new JButton("btn");
	    
	    
	    try{
			listdispute=DBIODISPUTE.readFromDisputeDB();
		}catch(Exception exp){System.out.println("File not Found");}

	    
	    if(listdispute.size()>0){
	    for(UserDispute dspt:listdispute)
	    {
	      dtm.addRow(new Object[]{new Boolean(false),""+dspt.getId(),dspt.getUsername(),dspt.getReport(),dspt.getCatagory(),dspt.getDate(),dspt.getStatus()});
	      ++cr;
	    }
	    }
		
	    
	    
	    
	    JScrollPane sp = new JScrollPane(table);
	    TableColumn tc = table.getColumnModel().getColumn(0);
	    tc.setCellEditor(table.getDefaultEditor(Boolean.class));
	    tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	    
	    JPanel btnPnl = new JPanel(new BorderLayout());
	    JPanel bottombtnPnl = new JPanel(new FlowLayout());
	    textArea=new JTextArea(4,55);
	    JButton btnback = new JButton("Close");
	    JButton btnsolve = new JButton("Solved");
	    textArea.setEditable(false);
	    textArea.setBorder(new LineBorder(Color.BLACK, 1));
	    textArea.setBackground(new Color(152,251,152));
	    textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
	    textArea.setPreferredSize(new Dimension(100,34));
	    
	    JScrollPane scrlpn=new JScrollPane(textArea);
	    textArea.setWrapStyleWord(true);
	    textArea.setLineWrap(true);      
	    
	    bottombtnPnl.add(scrlpn);
	    bottombtnPnl.add(textArea);    
	    bottombtnPnl.add(btnback);
	    bottombtnPnl.add(btnsolve);
	    bottombtnPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
	    
	    btnPnl.add(bottombtnPnl, BorderLayout.CENTER);
	    table.enableInputMethods(false);
	    
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    table.getColumnModel().getColumn(0).setPreferredWidth(30);
	    table.getColumnModel().getColumn(1).setPreferredWidth(20);
	    table.getColumnModel().getColumn(2).setPreferredWidth(100);
	    table.getColumnModel().getColumn(3).setPreferredWidth(230);
	    table.getColumnModel().getColumn(4).setPreferredWidth(70);
	    table.getColumnModel().getColumn(5).setPreferredWidth(70);
	    table.getColumnModel().getColumn(6).setPreferredWidth(70);
	    
	    final JFrame frame = new JFrame("ALL DISPUTE REPORTS");
	    JTableHeader header = table.getTableHeader();
	    header.setBackground(Color.yellow);
	    frame.setLayout(new BorderLayout());
	    frame.getContentPane().add(scrlpn);
	    frame.add(table.getTableHeader(), BorderLayout.NORTH);
	    frame.add(table, BorderLayout.CENTER);
	    frame.add(btnPnl, BorderLayout.SOUTH);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    frame.setSize(700,400);
		frame.setLocation(340, 180);
		frame.setResizable(false);
		
		btnsolve.setBackground(new Color(72,209,204));
		btnsolve.setForeground(Color.WHITE);
		btnsolve.setFocusPainted(false);
		btnsolve.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnback.setBackground(new Color(72,209,204));
		btnback.setForeground(Color.WHITE);
		btnback.setFocusPainted(false);
		btnback.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(table.getSelectedRow() != -1) {int cellrw=table.getSelectedRow();
                	boolean nnf=(boolean) table.getModel().getValueAt(cellrw,0);
        	    	if(nnf==true && table.getSelectedColumn()==0){
                	
                	String data=(String) table.getModel().getValueAt(cellrw,3);
                    textArea.setText(data);
                }
        	    	else if(nnf==false){textArea.setText("");}
                }
            }
        });
	       
		
	    btnsolve.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	       
	        	int ck=0;
	        	try{
	        		int x=listdispute.size();
	        	    for( int i=0;i<x;i++){
	        	    	
	        	    	
	           	    	boolean chkbx=(boolean) table.getModel().getValueAt(i,0);
	        	    	String nameuser=(String) table.getModel().getValueAt(i,2);
	        	    	String catagory=(String) table.getModel().getValueAt(i,4);
	        	    	
	        	    	String itmid=(String)table.getModel().getValueAt(i,1);
	        	    	int itemid=Integer.parseInt(itmid);  	    	
	        	    	
	        	    	if(chkbx==true){ck=1;
	        	    	new DeleteReport(itemid, nameuser,catagory );
	        	    	frame.dispose();
	        	    	new ViewDisputeByAdmin();
	        	    	
	        	    	}       	    			
	        	}}catch(ArrayIndexOutOfBoundsException ex) {
	               // System.out.println("Array index out of bounds.");
	            } 
	        	if(ck==0){JOptionPane.showMessageDialog(null,"No Report selected");}
	              
	            
	        }
	    });
	
	    btnback.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	frame.dispose();new AdminLogin();
	        }
	    });
	    
	    //if no dispute available
	    if(cr==0){JOptionPane.showMessageDialog(null,"No Report available");
	    frame.dispose();new AdminLogin();} 
	
	}
	public static void main(String[] args){new ViewDisputeByAdmin();}
}