<!DOCTYPE html>
<%@page import="com.reg.beans.Student"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generate Load</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBc4O7oJJvVwrdeCTTONFOQREWV4DCZmHM&v=3.exp&signed_in=false&libraries=places,geometry,drawing"></script>
<script type="text/javascript" src="assets/js/loadGoogleMap.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="assets/css/main.css" />
<link rel="stylesheet" type="text/css" href="assets/css/indexStyle.css" />
<style>
/* header {
    background-color: black;
    display: block;
} */
header {
	width: 100%;
	height: 50px;
	background: #4072b4;
	position: fixed;
	top: 0;
}

footer {
	width: 100%;
	height: 60px;
	background: #4072b4;
	position: fixed;
	bottom: 0;
}

label {
	white-space: nowrap;
}
</style>

<%
Student userData = (Student) session.getAttribute("student");

%>

</head>
<body>

	<div id="wrapper">
		<div id="header" align="center">
			<span style="font-size: 20pt;">Course Selection Page</span>
		</div>
		<div id="welcometext"
			style="text-align: right; margin-right: 25px; color: white; font-weight: bold; font-size: 10pt;">
			Welcome <a href="getUpdateProfile.jsp"> <strong> <%= userData.getSname() %></strong></a>
			&nbsp;&nbsp;
			<form action="logout">
				<input type="submit" value="Log Out">
			</form>
		</div>
		<hr />
		<div id="content" style="line-height: 30pt;">

			<div id="parent" style="width: 100%;">
				<form action="selectCourse">

					<table style="width: 80%">
						<h2>Select Course</h2>
						<tr>
							<td><label>COURSE SEARCH </label></td>
							<td><select id="coursePreference" name="coursePreference" size="10"	style="width: 100%;">
									<option value="CS">Computer Science</option>
									<option value="IS">Information Systems</option>
									<option value="ECE">Computer Engineering</option>
							</select></td>
						</tr>

					</table>
					<input type="submit" id="btnlogin" value="Course Search"/>
					&nbsp;

				</form>
			</div>

		</div>
	</div>



</body>
</html>