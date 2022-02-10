<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/adminpage.css">
    <link rel="icon" type="image/png" href="./img/logo.png"/>
    <script src="https://kit.fontawesome.com/4b0b15a158.js" crossorigin="anonymous"></script>
	<title>Admin - User management</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
    	<div class="main" style="width: 100%">
    		<button class="btn" onclick="window.location.href='add-user'"><i class="fas fa-plus-circle"></i> Add user</button>
    		<section class="admin-section">
    		<h1><i class="fas fa-users"></i> All Users</h1>
			<div class="tbl-header">
				<table cellpadding="0" cellspacing="0" border="0">
					<thead>
						<tr>
							<th width="10px"></th>
				            <th width="10px">#</th>
				            <th>Name</th>
				            <th>Username</th>
				            <th>Email</th>
				            <th>Room</th>
				            <th>Student ID</th>
				            <th>Role</th>
				            <th width="150px">Action</th>
						</tr>
					</thead>
				</table>
		    </div>
		    <div class="tbl-content">
				<table cellpadding="0" cellspacing="0" border="0">
					<tbody>
						<c:forEach items="${listUser}" var="user">
							<tr>
								<td width="10px"><i class="fas fa-user fa-lg" style="margin-right: 4px"></i></td>
					            <td width="10px">${user.getUserID()}</td>
					            <td>${user.getName()}</td>
					            <td>${user.getLogin().getUsername()}</td>
					            <td>${user.getEmail()}</td>
					            <td>${user.getRoom()}</td>
					            <td>${user.getMssv()}</td>
					            <td>${user.getRoleID() == 1 ? "Admin":"User"}</td>
					            <td class="action" width="150px">
					            	<a href="edit-user?user-id=${user.getUserID()}"><i class="fas fa-edit fa-lg item"></i></a>
					            	<form id="deleteUser+${user.getUserID()}" action="deleteUser" method="post" style="display: inline">
					            		<input type="hidden" name="user-id" value="${user.getUserID()}">
									    <a href="#" onclick="document.getElementById('deleteUser+${user.getUserID()}').submit()"><i class="fas fa-trash-alt fa-lg item"></i></a>
									</form>
					            </td>
			        		</tr>
		        		</c:forEach>
					</tbody>
				</table>
			</div>
			</section>
    	</div>
    </div>
</body>
</html>