<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/homepage.css">
    <link rel="icon" type="image/png" href="./img/logo.png"/>
    <script src="https://kit.fontawesome.com/4b0b15a158.js" crossorigin="anonymous"></script>
	<title>Search result</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
    	<div class="main">
    		<div class="new-upload">
    			<c:if test="${listFile.size() >= 2}">
    				<h3 class="box-label-one"><i class="fas fa-file"></i> ${listFile.size()} files found</h3>
    			</c:if>
    			<c:if test="${listFile.size() <= 1}">
    				<h3 class="box-label-one"><i class="fas fa-file"></i> ${listFile.size()} file found</h3>
    			</c:if>
	    		<ul>
	    			<c:forEach items="${listFile}" var="f">
	    				<li><a href="file-info?file-id=${f.getFileID()}">${f.getName()}</a> was uploaded by ${f.getUser().getName()} in ${f.getCategoryString()}</li>
	    			</c:forEach>
	    		</ul>
    		</div>
    	</div>
    	<jsp:include page="sidebar.jsp"></jsp:include>
    </div>
</body>
</html>