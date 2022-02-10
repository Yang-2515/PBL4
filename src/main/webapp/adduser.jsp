<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./css/common.css">
    <link rel="icon" type="image/png" href="./img/logo.png"/>
    <script src="https://kit.fontawesome.com/4b0b15a158.js" crossorigin="anonymous"></script>
	<title>DUT Dormitory Document - Add user</title>
	<style type="text/css">
		th{
			text-align: left;
		}
		.input-focus{
			width: 100%;
		}
	</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
    	<div class="main" style="width: 100%">
    	<button class="btn" onclick="javascript:history.back()"><i class="fas fa-arrow-circle-left"></i> Back</a></button>
    	<form action="add-user" method="post">
    		<h2 style="text-align: center;margin-bottom: 20px;">Add user</h2>
    		<p class="text-danger" style="text-align: center;margin-bottom: 20px;">${mess}</p>
    		<table style="width: 60%; margin: 0 auto;">
				<tr>
					<th>Name:</th>
					<td><input type="text" class="input-focus" placeholder="Nguyen Van A" name="name" required maxlength="50"></td>
				</tr>
				<tr>
					<th>Username:</th>
					<td><input type="text" class="input-focus" placeholder="nguyenvana" name="username" required  maxlength="50"></td>
				</tr>
				<tr>
					<th>Password:</th>
					<td><input type="password" class="input-focus" placeholder="Enter password" name="password" required  maxlength="24"></td>
				</tr>
				<tr>
					<th>Email:</th>
					<td><input type="text" class="input-focus" placeholder="e@mail.com" name="email" required  maxlength="50"></td>
				</tr>
				<tr>
					<th>Room:</th>
					<td><input type="text" class="input-focus" placeholder="A102" name="room" required  maxlength="10"></td>
				</tr>
				<tr>
					<th>Student ID:</th>
					<td><input type="text" class="input-focus" placeholder="102190222" name="mssv" required maxlength="50"></td>
				</tr>
				<tr>
					<th>Role:</th>
					<td>
						<select name="role" style="padding:10px; margin-bottom: 10px" required>
							<option value="" disabled selected>Choose role</option>
							<option value="1">Admin</option>
							<option value="2">User</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><button class="btn" type="submit" style="margin:auto"><i class="fas fa-user-plus"></i> Add User</a></button></td>
				</tr>
			</table>
		</form>
    	</div>
    </div>
</body>
</html>