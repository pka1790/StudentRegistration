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

import com.reg.beans.CourseBean;
import com.reg.service.CRService;

/**
 * Servlet implementation class CourseSecController
 */
@WebServlet("/selectCourse")
public class CourseSecController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseSecController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In Get"+request.getParameter("coursePreference"));
		List<CourseBean> courseList=new ArrayList<CourseBean>();
		String option=request.getParameter("coursePreference");
		CRService service= new CRService();
		courseList=service.getCourseDetails(option);
		HttpSession session= request.getSession();
		if(!courseList.isEmpty()){
			for (CourseBean courseBean : courseList) {
				System.out.println(courseBean);
			}
			session.setAttribute("courselist", courseList);
			request.getRequestDispatcher("coursepage.jsp").forward(request, response);
		}else{
			response.sendError(404, "NO COURSES AVAILABLE THIS TIME ");
		}


	}

}
