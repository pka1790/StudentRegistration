package com.reg.apps;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.reg.beans.Student;
import com.reg.service.CRService;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("password"));
		String sname,password,major,emailid,birthdate,address;
		int stssn;
		stssn= Integer.valueOf(request.getParameter("stssn"));
		sname=request.getParameter("sname");
		password= request.getParameter("password");
		emailid= request.getParameter("emailid");
		major=request.getParameter("major");
		birthdate= request.getParameter("birthdate");
		address= request.getParameter("address");
		HttpSession session= request.getSession();//getting session
		
		CRService service= new CRService();
		Student student= new Student(sname, birthdate, emailid, address, major, stssn,password);
		System.out.println(student);
		//authenticated and registered
		if(service.registerUser(student)){
			session.setAttribute("student", student);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}


	}

}
