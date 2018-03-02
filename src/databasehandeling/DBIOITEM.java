package databasehandeling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import main.UserItem;



public class DBIOITEM
{

	
	public static void writeToItemDB(List<UserItem> listitem)throws Exception
	{
		ObjectOutputStream out;
		out=new ObjectOutputStream(new FileOutputStream("ITEMLIST.txt"));
		out.writeObject(listitem);
		out.close();
	}
public static List<UserItem> readFromItemDB() throws Exception
{
	ObjectInputStream in;
	in=new ObjectInputStream(new FileInputStream("ITEMLIST.txt"));
	List<UserItem> listitem=(List<UserItem>)in.readObject();
	in.close();
	return listitem;
	
}
}


