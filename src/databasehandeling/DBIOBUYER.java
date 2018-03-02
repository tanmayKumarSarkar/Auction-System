package databasehandeling;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import main.UserBuyer;



public class DBIOBUYER
{

	
	public static void writeToBuyerDB(List<UserBuyer> listbuyer)throws Exception
	{
		ObjectOutputStream out;
		out=new ObjectOutputStream(new FileOutputStream("BuyerDB.txt"));
		out.writeObject(listbuyer);
		out.close();
	}
public static List<UserBuyer> readFromBuyerDB() throws Exception
{
	ObjectInputStream in;
	in=new ObjectInputStream(new FileInputStream("BuyerDB.txt"));
	List<UserBuyer> list=(List<UserBuyer>)in.readObject();
	in.close();
	return list;
	
}
}