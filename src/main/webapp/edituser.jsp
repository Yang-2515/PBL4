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
	<title>Edit User</title>
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
    	<c:if test="${mess != null}">
  			<button class="btn" onclick="javascript:history.back(2)"><i class="fas fa-arrow-circle-left"></i> Back</button>
  		</c:if>
  		<c:if test="${mess == null}">
  			<button class="btn" onclick="javascript:history.back()"><i class="fas fa-arrow-circle-left"></i> Back</button>
  		</c:if>
    	<form action="edit-user" method="post">
    		<input type="hidden"name="user-id" value="${user.getUserID()}">
    		<h2 style="text-align: center;margin-bottom: 20px;">Edit user</h2>
    		<p class="text-danger" style="text-align: center;margin-bottom: 20px;">${mess}</p>
    		<table style="width: 60%; margin: 0 auto;">
				<tr>
					<th>Name:</th>
					<td><input type="text" class="input-focus" placeholder="Nguyen Van A" name="name" value="${user.getName()}" required></td>
				</tr>
				<tr>
					<th>Email:</th>
					<td><input type="text" class="input-focus" placeholder="e@mail.com" name="email" value="${user.getEmail()}" required></td>
				</tr>
				<tr>
					<th>Room:</th>
					<td><input type="text" class="input-focus" placeholder="A102" name="room" value="${user.getRoom()}" required></td>
				</tr>
				<tr>
					<th>Student ID:</th>
					<td><input type="text" class="input-focus" placeholder="102190222" name="mssv" value="${user.getMssv()}" required></td>
				</tr>
				<tr>
					<th>Role:</th>
					<td>
						<select name="role" style="padding:10px; margin-bottom: 10px" required>
							<c:if test="${user.getRoleID() == 1}">
								<option value="1" selected>Admin</option>
								<option value="2">User</option>
							</c:if>
							<c:if test="${user.getRoleID() == 2}">
								<option value="1">Admin</option>
								<option value="2" selected>User</option>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<th>Username:</th>
					<td><input type="text" class="input-focus" placeholder="102190222" name="username" value="${user.getLogin().getUsername()}" readonly></td>
				</tr>
				<tr>
					<th>Change password:</th>
					<td><input type="password" class="input-focus" placeholder="Type new password" id="password" name="password" maxlength="24"></td>
				</tr>
				<tr>
					<td colspan="2"><button class="btn" type="submit" style="margin:auto"><i class="far fa-save"></i> Save changes</a></button></td>
				</tr>
			</table>
		</form>
    	</div>
    </div>
</body>
</html>