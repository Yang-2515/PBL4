<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/file_info.css">
    <link rel="icon" type="image/png" href="./img/logo.png"/>
    <script src="https://kit.fontawesome.com/4b0b15a158.js" crossorigin="anonymous"></script>
	<title>File - ${file.getName()}</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
    	<div class="main">
    		<button class="btn" onclick="javascript:history.back()"><i class="fas fa-arrow-circle-left"></i> Back</button>
    		<div id="table_info">
    			<table id="file_info">
					<tr>
						<th>File Name</th>
						<td>${file.getName()}</td>
					</tr>
					<tr>
						<th>Uploader</th>
						<td>${file.getUser().getName()}</td>
					</tr>
					<tr>
						<th>Upload Date</th>
						<td>${file.getCreateAt()}</td>
					</tr>
					<tr>
						<th>Description</th>
						<td>${file.getDescription()}</td>
					</tr>
				</table> 
    		</div>
    		<button class="btn-download" onclick="window.location.href='download?file-id=${file.getFileID()}'"><i class="fas fa-download"></i> Download</button>
    	</div>
    	<jsp:include page="sidebar.jsp"></jsp:include>
    </div>
</body>
</html>