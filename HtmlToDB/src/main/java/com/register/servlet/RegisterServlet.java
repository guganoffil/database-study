package com.register.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final String INSERT_QUERY ="INSERT INTO DETAILS(NAME,CITY,MOBILE,DATE) VALUES(?,?,?,?)";
	
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	PrintWriter pw = resp.getWriter();
	resp.setContentType("text/html");
	String name = req.getParameter("name");
	String city = req.getParameter("city");
	String mobile = req.getParameter("mobile");
	String dob = req.getParameter("date");

	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try(Connection con = DriverManager.getConnection("jdbc:mysql:///gugan","root","Gugan@123");
			PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
		ps.setString(1, name);
		ps.setString(2, city);
		ps.setString(3, mobile);
		ps.setString(4, dob);
		
		int count=ps.executeUpdate();
		if(count==0) {
			pw.println(" not submitted");
		}else {
			pw.println("submitted");
			
		}
		
		
	}catch(SQLException se) {
		pw.println(se.getMessage());
		se.printStackTrace();
		
	}catch(Exception e) {
		pw.println(e.getMessage());
		e.printStackTrace();
	}
	
	pw.close();
	
	
	
	
}


@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}


}
