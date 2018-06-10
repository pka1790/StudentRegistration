package com.reg.apps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.reg.beans.SectionBean;
import com.reg.beans.Student;
import com.reg.service.CRService;

/**
 * Servlet implementation class SectionController
 */
@WebServlet("/getSectioninfo")
public class SectionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SectionController: "+request.getParameter("courseId"));
		int crsId=Integer.valueOf(request.getParameter("courseId"));
		List<SectionBean> sectionList= new ArrayList<SectionBean>();
		CRService crService= new CRService();
		sectionList=crService.getsectionList(crsId);
		HttpSession session = request.getSession();
		Student userData = (Student) session.getAttribute("student");
		System.out.println("Student: "+userData.getSname());
		if(!sectionList.isEmpty()){
			session.setAttribute("sectionList", sectionList);
			for (SectionBean sectionBean : sectionList) {
				System.out.println("\n "+sectionBean);
			}
			request.getRequestDispatcher("sectionpage.jsp").forward(request, response);
			
			
		}else{
			response.sendError(404, "No Section Available Now");
		}
	}


}
