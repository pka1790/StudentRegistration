package com.reg.apps;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.util.Base64;

import com.reg.beans.Student;
import com.reg.service.CRService;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username,password;
		System.out.println(request.getParameter("sname"));
		username=request.getParameter("sname");
		password=request.getParameter("password");
		//LoginService loginService= new LoginService();
		//boolean result=loginService.authenticate(username, password);
		
		CRService service= new CRService();
		Student student=service.logIn(username);
		
		if(student!=null){
			if(student.getSname().equals(username) && student.getPassword().equals(password)){
				HttpSession session= request.getSession();
				session.setAttribute("student", student);
				
					response.sendRedirect("index.jsp");
				
			}else{
				
				response.sendError(404, "INVALID CREDENTIALS");
			}
			
		}else{

			response.sendError(404, "USER NOT FOUND .PLEASE ENTER VALID USER");

			
		}
		
//		 if(result){
//			 User user=loginService.getUserDetail(username);
//			 request.setAttribute("user", user);
//			 //response.sendRedirect("success.jsp");
//			 RequestDispatcher dispatcher= request.getRequestDispatcher("success.jsp");
//			 dispatcher.forward(request, response);
//			 return;
//		 }
//		 else {
//			response.sendRedirect("login.jsp");
//			return;
//		}
		 
	}

}
