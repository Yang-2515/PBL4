<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./css/common.css">
    <link rel="icon" type="image/png" href="./img/logo.png"/>
    <script src="https://kit.fontawesome.com/4b0b15a158.js" crossorigin="anonymous"></script>
	<title>Edit file</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
    	<div class="main">
    		<c:if test="${mess != null}">
    			<button class="btn" onclick="javascript:history.back(2)"><i class="fas fa-arrow-circle-left"></i> Back</button>
    		</c:if>
    		<c:if test="${mess == null}">
    			<button class="btn" onclick="javascript:history.back()"><i class="fas fa-arrow-circle-left"></i> Back</button>
    		</c:if>
    		<h3 style="margin: 10px 0;">Edit file</h3>
    		<form action="edit-file" method="post" >
    			<input type="hidden" name="file-id" value="${file.getFileID()}">
    			<p class="text-danger" style="text-align: center;margin-bottom: 20px;">${mess}</p>
	    		<table style="width: 60%; margin: 0 auto;">
					<tr>
						<th>Filename:</th>
						<td><input type="text" class="input-focus" name="file-name" value="${file.getName()}" required maxlength="100"></td>
					</tr>
					<tr>
						<th>Descripton:</th>
						<td><input type="text" class="input-focus" name="description" value="${file.getDescription()}" required maxlength="30"></td>
					</tr>
					<tr>
						<th>Category:</th>
						<td>
							<select name="category" style="padding:10px; margin-bottom: 10px" required>
								<option disabled selected value="">Choose category</option>
								<c:forEach items="${listCategory}" var="c">
						    		<option value="${c.getCateID()}">${c.getName()}</option>
						    	</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2"><button class="btn" type="submit" style="margin:auto"><i class="fas fa-user-plus"></i> Save changes</button></td>
					</tr>
				</table>
	  		</form>
    	</div>
    	<jsp:include page="sidebar.jsp"></jsp:include>
    </div>
</body>
</html>