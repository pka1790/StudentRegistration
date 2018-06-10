<%@page import="com.reg.beans.CourseBean"%>
<%@page import="com.reg.beans.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>COURSES</title>

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
<script src="//cdn.datatables.net/plug-ins/1.10.7/integration/jqueryui/dataTables.jqueryui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="//cdn.datatables.net/plug-ins/1.10.7/integration/jqueryui/dataTables.jqueryui.css">
<link rel="stylesheet" type="text/css" href="assets/css/main.css" />
<%
	
Student userData = (Student) session.getAttribute("student");
List<CourseBean> courseList= (List<CourseBean>) session.getAttribute("courselist");	   
%>
</head>
<body>
 <div id="wrapper">
	<div id="header" align="center">
			<span style="font-size: 20pt;">NJIT Course Page</span>
		</div>
		<div id="welcometext" style="text-align: right; margin-right: 25px; color: white; font-weight: bold; font-size: 10pt;">
			Welcome <a href="getUpdateProfile.jsp"> <strong> <%=userData.getSname()%></strong></a>
			&nbsp;&nbsp;<form action="logout">
			<input type="submit" value="Log Out">
			</form>
		</div>
		 
		<hr/>
		
		<h3>Available Subjects</h3>
		
	
	<div id="content" style="width:100%;">
		
	<table  border="1" id="example" class="display" cellspacing="0" width="100%" style="font-size:8pt;">
	<thead>	
	<tr>
		<td>COURSE ID</td>
		<td>COURSE NAME</td>
		<td>DESCRIPTION</td>
		<td></td>
	</tr>
	</thead>
 <% 
 for (CourseBean courseBean : courseList) {
 
 %>
		
		<tr>
			<td><%= courseBean.getCourseid() %></td>
			<td><%= courseBean.getCname() %></td>
			<td><%= courseBean.getDesc() %></td>
			<td><input type="button" value="Sections" onclick="location.href='getSectioninfo?courseId='+<%= courseBean.getCourseid() %>"/></td>
		</tr>	
 <%}%>
 </table>
	</div>
	<br/><br/>
	 
<script>
$(document).ready(function() {
    $('#example').dataTable({
    	"order": [[ 0, "desc" ]],
    	"scrollX": true
    });
    
} );

</script>	
	
	
	
 

</body>
</html>