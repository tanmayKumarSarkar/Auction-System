package databasehandeling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import main.UserSold;



public class DBIOSOLD
{

	
	public static void writeToSoldDB(List<UserSold> listsold)throws Exception
	{
		ObjectOutputStream out;
		out=new ObjectOutputStream(new FileOutputStream("listsold.txt"));
		out.writeObject(listsold);
		out.close();
	}
public static List<UserSold> readFromSoldDB() throws Exception
{
	ObjectInputStream in;
	in=new ObjectInputStream(new FileInputStream("listsold.txt"));
	List<UserSold> listsold=(List<UserSold>)in.readObject();
	in.close();
	return listsold;
	
}
}


