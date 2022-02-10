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
	<title>Edit folder</title>
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
    		<h3 style="margin: 10px 0;">Edit folder</h3>
    		<form action="edit-folder" method="post">
    			<input type="hidden" name="folder-id" value="${folder.getFolderID()}">
				<input type="text" class="input-focus" placeholder="Enter folder name" name="folder-name" value="${folder.getName()}" required maxlength="50">
				<input type="text" class="input-focus" placeholder="Enter folder description" name="description" value="${folder.getDescription()}"  maxlength="30">
				<button class="btn" type="submit" style="display:inline-block">Confirm</button>
	  		</form>
	  		<p class="text-danger">${mess}</p>
    	</div>
    	<jsp:include page="sidebar.jsp"></jsp:include>
    </div>
</body>
</html>