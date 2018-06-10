<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>

<link rel="stylesheet" type="text/css" href="assets/css/registerStyle.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</head>
<body>
	<div id="wrapper">

		<div id="header" align="center">
			<span style="font-size: 35pt;">NJIT Student Registration Application</span>
		</div>
		<hr />

		<div id="content" >
			<form action="register" method="post">

				<h2>Register Student</h2>
				
				<div class="input-fields">
					<label>Student's SSN</label> <input type="text" id="stssn"
						class="regfields" name="stssn" placeholder="SSN NUMBER"
						required="required" />
				</div>

				<div class="input-fields">
					<label>NAME</label> <input type="text" id="sname"
						class="regfields" name="sname" placeholder="STUDENT NAME"
						required="required" />
				</div>

				<div class="input-fields">
					<label>PASSWORD</label> <input type="password" id="password"
						class="regfields" name="password" placeholder="********"
						required="required" />
				</div>
				<div class="input-fields">
					<label>MAJOR</label> <input type="text" id="major"
						class="regfields" name="major" placeholder="Computer science"
						required="required" />
				</div>
				<div class="input-fields">
					<label>EMAIL</label> <input type="email" id="emailid"
						name="emailid" class="regfields" placeholder="name@example.com"
						required="required"
						pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" />
				</div>
				
				<div class="input-fields">
					<label>ADDRESS</label> <input type="text" id="address"
						class="regfields" name="address" placeholder="ADDRESS"
						required="required" />
				</div>
				
				<div class="input-fields">
					<label>BIRTH DATE</label> <br /> <input type="text"
						name="birthdate" id="birthdate" placeholder="mm/dd/yyyy"
						required="required" readonly="readonly" />
				</div>

				<div>
					<input type="submit" id="btn-create-account" name="register"
						value="Sign Up" />
				</div>
				
				
				

				<hr>

			</form>
		</div>
	</div>

	<script>
  $(function() {
    $( "#birthdate" ).datepicker();
  });
  </script>
</body>
</html>