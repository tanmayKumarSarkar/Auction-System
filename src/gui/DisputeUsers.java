package gui;



import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import databasehandeling.DBIOBUYER;
import databasehandeling.DBIODISPUTE;
import databasehandeling.DBIOSELLER;
import main.UserBuyer;
import main.UserDispute;
import main.UserSeller;


public class DisputeUsers extends JFrame implements ActionListener
{
private JTextArea txtcell;
private JPanel pnltxtcell;
private JLabel lbl; 
private JButton btndone,btncancel;
private JScrollPane sp;
private int genid;
private List<UserBuyer> listbuyer;
private List<UserSeller>listseller;
private List<UserDispute>listdispute;
private String UserId,UserType,UserName,dts;

Font titleFont = new Font("serif", Font.HANGING_BASELINE, 24);

public  DisputeUsers(String Userid,String Usertype )
{
	this.UserId=Userid;
	this.UserType=Usertype;
	String lblname,title;
	
	listbuyer=new ArrayList<UserBuyer>();
	listseller=new ArrayList<UserSeller>();
	listdispute=new ArrayList<UserDispute>();
	
	//Creating Backup Files...
			try{	listbuyer=DBIOBUYER.readFromBuyerDB();
			}catch(Exception exp)
			{	System.out.println("File not found");}
			
			try{	listseller=DBIOSELLER.readFromSellerDB();
			}catch(Exception exp)
			{	System.out.println("File not found");}
			
			try{	listdispute=DBIODISPUTE.readFromDisputeDB();
			}catch(Exception exp)
			{	System.out.println("File not found");}
	
	if(UserType.equalsIgnoreCase("buyer")){
		lblname="Lodge Your Complain";
		title="Dispute Report For Buyers";
		
	}
	
	else{
		lblname="Your Complain";
		title="Dispute Report For Sellers";
		
	}
	
	pnltxtcell=new JPanel();
	
	lbl=new JLabel(lblname);
	txtcell=new JTextArea("",12,25);
	sp=new JScrollPane(txtcell);
	btndone=new JButton("Done");
	btncancel=new JButton("Cancel");
	pnltxtcell.add(lbl);
	pnltxtcell.add(sp);
	pnltxtcell.add(btndone);
	pnltxtcell.add(btncancel);
	add(pnltxtcell);
	
	pnltxtcell.setLayout(null);
	 txtcell.setWrapStyleWord(true);
	 txtcell.setLineWrap(true); 
	
	lbl.setBounds(10,10,250,30);
	sp.setBounds(40,50,325,150);
	btndone.setBounds(100,220,100,30);
	btncancel.setBounds(230,220,100,30);
	lbl.setFont(titleFont);
	lbl.setForeground(Color.BLUE);

	btndone.addActionListener(this);
	btncancel.addActionListener(this);
	
	btndone.setBackground(new Color(72,209,204));
	btndone.setForeground(Color.WHITE);
	btndone.setFocusPainted(false);
	btndone.setFont(new Font("Tahoma", Font.BOLD, 12));
	btncancel.setBackground(new Color(72,209,204));
	btncancel.setForeground(Color.WHITE);
	btncancel.setFocusPainted(false);
	btncancel.setFont(new Font("Tahoma", Font.BOLD, 12));
	
	 this.setVisible(true);
     this.setTitle("Dispute Reporte For Sellers");
     this.setSize(420,300);
     this.setLocation(480, 240);
     this.setTitle(title);
     this.setResizable(false);
}

public void actionPerformed(ActionEvent event) {
	Date dateob=new Date();
	DateFormat dt;
	dt=DateFormat.getDateInstance(DateFormat.SHORT);
	dts=dt.format(dateob);
	
	if(event.getSource()==btncancel){this.dispose();}
	if(event.getSource()==btndone){
		idGenDispute();
		//finding user name
		if(UserType.equalsIgnoreCase("buyer")){findBuyerName();}
		else{findSellerName();}
		if(checkEmpty()==false){
			
			try{
				listdispute.add(getRegisterDispute());
				DBIODISPUTE.writeToDisputeDB(listdispute);
				this.dispose();
			}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
			
			
		}
			}
	
}


//Registering values for buyers and sellers	
	public UserDispute getRegisterDispute(){
	
	int id=genid;
	String report=txtcell.getText();
	String username=UserName;
	String catagory=UserType;
	String date=dts;
	String status="Unsolved";
	
	
		UserDispute userdispute=new UserDispute(id,report,username,catagory,dts,status);
		return userdispute;
	
	}
	

public boolean checkEmpty(){
	if(txtcell.getText().isEmpty()){
		JOptionPane.showMessageDialog(null, "Please Fill The Text Field ","ERROR", JOptionPane.ERROR_MESSAGE); return true; }
	else return false;	
	}

public int idGenDispute(){
	//id generation for dispute reports
			if(listdispute.size()==0)
				genid=1;
			else
				genid=listdispute.get(listdispute.size()-1).getId()+1;
			return genid;
	}


public String findBuyerName(){
	for(UserBuyer userbuyer:listbuyer){
		if(UserId.equals(userbuyer.getUserloginid())){UserName= userbuyer.getName();}
	}
	return UserName;  }

public String findSellerName(){
	for(UserSeller userseller:listseller){
		if(UserId.equals(userseller.getUserloginid())){UserName= userseller.getName();}
	}
	return UserName;  }

public static void main(String args[])
{
	// new DisputeUsers("gour","seller");
}


}
