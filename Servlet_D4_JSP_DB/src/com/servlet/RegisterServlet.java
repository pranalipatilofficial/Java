package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.models.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con =  null;
		Statement smt = null;
		
		User u = new User();
		u.setUsername(req.getParameter("username"));
		u.setName(req.getParameter("name"));
		u.setPassword(req.getParameter("password"));
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Servlet_D4_JSP_DB","root","root");
			String insertData = "insert into user(name,username,password) values('"+u.getName()+"', '"+u.getUsername()+"' , '"+u.getPassword()+"' )";
			smt = con.createStatement();
			smt.execute(insertData);
			System.out.println("Your data is saved !!!");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*finally {
			
				try {
					smt.close();
					con.close();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			
		}*/
		
		RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
		rd.forward(req, resp);
			
	}
}
