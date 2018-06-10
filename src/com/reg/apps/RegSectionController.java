package com.reg.apps;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.reg.beans.Student;
import com.reg.service.CRService;

/**
 * Servlet implementation class RegSectionController
 */
@WebServlet("/registerSection")
public class RegSectionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int secId=Integer.valueOf(request.getParameter("secId"));
		int crsId=Integer.valueOf(request.getParameter("crsId"));
		int cap=Integer.valueOf(request.getParameter("cap"));
		int act=Integer.valueOf(request.getParameter("act"));
		int rem=Integer.valueOf(request.getParameter("rem"));
		HttpSession session= request.getSession();
		Student userData = (Student) session.getAttribute("student");
		
		
		CRService crService= new CRService();
		System.out.println(secId+" "+act+" "+rem+" "+cap+" "+userData.getSname());
		if(act<cap){
			act+=1;
			rem-=1;
			System.out.println(""+act+" "+rem);
			if(crService.updateSection(act,rem,userData.getStssn(),secId,crsId)){
				request.getRequestDispatcher("regsuccess.jsp").forward(request, response);
				
				System.out.println("success!!!");
				
			}
			
		}else if (act==cap) {
			response.sendError(404,"Cannot Registered now. Class is already Full.");
			
		}
	
	
	
	
	}

	

}
