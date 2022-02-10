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
	<title>Admin - File management</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
    	<div class="main" style="width: 100%">
    		<section class="admin-section">
    		<h1><i class="fas fa-file-alt"></i> All Files</h1>
			<div class="tbl-header">
				<table cellpadding="0" cellspacing="0" border="0">
					<thead>
						<tr>
							<th width="10px"></th>
				            <th width="250px">Name</th>
				            <th>Size</th>
				            <th>User</th>
				            <th>Student ID</th>
				            <th width="150px">Action</th>
						</tr>
					</thead>
				</table>
		    </div>
		    <div class="tbl-content">
				<table cellpadding="0" cellspacing="0" border="0">
					<tbody>
						<c:forEach items="${listFile}" var="file">
							<tr id="${file.getFileID()}" onmouseover="getID(this)">
								<td width="10px"><i class="fas fa-file fa-lg" style="margin-right: 4px"></i></td>
					            <td width="250px">
					            	<form id="fileInfo+${file.getFileID()}" action="file-info" method="post" style="display: inline">
									    <input type="hidden" name="file-id" value="${file.getFileID()}"/>
									    <a href="#" onclick="document.getElementById('fileInfo+${file.getFileID()}').submit()">${file.getName()}</a>
									</form>
					            </td>
					            <td>${file.getReadableFileSize()}</td>
					            <td>${file.getUser().getName()}</td>
					            <td>${file.getUser().getMssv()}</td>
					            <td class="action" width="150px">
					            	<form id="deleteFile+${file.getFileID()}" action="delete" method="post" style="display: inline">
									    <input type="hidden" name="file-id" value="${file.getFileID()}"/>
									    <a href="#" onclick="document.getElementById('deleteFile+${file.getFileID()}').submit()"><i class="fas fa-trash-alt fa-lg item"></i></a>
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