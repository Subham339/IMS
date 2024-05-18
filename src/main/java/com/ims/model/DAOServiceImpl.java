package com.ims.model;

import java.sql.*;

public class DAOServiceImpl implements DAOService{
    private Connection con;
    private Statement stmt;
	@Override
	public void connectDb() {
		try {
			
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
			}
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MVC","root","Test");
			//System.out.println(con);
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public boolean verifyLogin(String email, String password) {
	    try {
	    	ResultSet res = stmt.executeQuery("Select * from login where email='"+email+"' and password='"+password+"'");
			return res.next();
		} catch (Exception e) {
             e.printStackTrace();
		}
		
	    return false;
	}

	@Override
	public void addInquiry(String name, String email, String mobile, String course) {
		try {
			stmt.executeUpdate("insert into student values('"+name+"','"+email+"','"+mobile+"','"+course+"')");
		} catch (Exception e) {
           e.printStackTrace();
		}
	}

	@Override
	public void deleteInquiry(String email) {
		try {
			stmt.executeUpdate("Delete from student where email='"+email+"'");
		} catch (Exception e) {
           e.printStackTrace();
		}
	}

	@Override
	public void updateInquiry(String email, String mobile) {
		try {
			stmt.executeUpdate("UPDATE  student SET mobile='"+mobile+"' where email='"+email+"'");
		} catch (Exception e) {
           e.printStackTrace();
		}
	}

	@Override
	public void closeDB() {
		
	}

	@Override
	public ResultSet getAllInquiries() {

		try {
	    	ResultSet res = stmt.executeQuery("Select * from student");
			return res;
		} catch (Exception e) {
             e.printStackTrace();
		}
		
		
		
		return null;
	}
  
}
