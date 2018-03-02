package databasehandeling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import main.UserDispute;



public class DBIODISPUTE
{

	
	public static void writeToDisputeDB(List<UserDispute> listdispute)throws Exception
	{
		ObjectOutputStream out;
		out=new ObjectOutputStream(new FileOutputStream("DisputeDB.txt"));
		out.writeObject(listdispute);
		out.close();
	}
public static List<UserDispute> readFromDisputeDB() throws Exception
{
	ObjectInputStream in;
	in=new ObjectInputStream(new FileInputStream("DisputeDB.txt"));
	List<UserDispute> listdispute=(List<UserDispute>)in.readObject();
	in.close();
	return listdispute;
	
}
}


