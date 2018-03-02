package databasehandeling;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import main.UserSeller;



public class DBIOSELLER
{

	
	public static void writeToSellerDB(List<UserSeller> listseller)throws Exception
	{
		ObjectOutputStream out;
		out=new ObjectOutputStream(new FileOutputStream("SellerDB.txt"));
		out.writeObject(listseller);
		out.close();
	}
public static List<UserSeller> readFromSellerDB() throws Exception
{
	ObjectInputStream in;
	in=new ObjectInputStream(new FileInputStream("SellerDB.txt"));
	List<UserSeller> list=(List<UserSeller>)in.readObject();
	in.close();
	return list;
	
}
}

