package main;
import java.io.*;

public class UserBuyer implements Serializable {
	
	private String name,useraddress,userphnno,pincode,useremail,userloginid,userpassword;
	private int id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
	public String getuserphnno() {
		return userphnno;
	}
	public void setuserphnno(String userphnno) {
		this.userphnno = userphnno;
	}
	public String getpincode() {
		return pincode;
	}
	public void setpincode(String pincode) {
		this.pincode = pincode;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserloginid() {
		return userloginid;
	}
	public void setUserloginid(String userloginid) {
		this.userloginid = userloginid;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserBuyer(int id, String name, String useraddress, String userphnno,
			String pincode, String useremail, String userloginid,
			String userpassword) {
		super();
		this.id = id;
		this.name = name;
		this.useraddress = useraddress;
		this.userphnno = userphnno;
		this.pincode = pincode;
		this.useremail = useremail;
		this.userloginid = userloginid;
		this.userpassword = userpassword;
	}
	
}


