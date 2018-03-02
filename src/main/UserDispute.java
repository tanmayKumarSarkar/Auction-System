package main;

import java.io.Serializable;

public class UserDispute implements Serializable
{

	private int id;
	private String report,username,catagory,date,status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UserDispute(int id, String report, String username, String catagory,
			String date, String status) {
		super();
		this.id = id;
		this.report = report;
		this.username = username;
		this.catagory = catagory;
		this.date = date;
		this.status = status;
	}
	
	
	
	
}
