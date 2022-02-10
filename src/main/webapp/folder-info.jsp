<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/userpage.css">
    <link rel="icon" type="image/png" href="./img/logo.png"/>
    <script src="https://kit.fontawesome.com/4b0b15a158.js" crossorigin="anonymous"></script>
	<title>Folder - ${folderName}</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
    	<div class="main">
    		<section class="user-section">
				<h1><i class="fas fa-folder-alt"></i> ${folderName}</h1>
				<div class="tbl-header">
					<table cellpadding="0" cellspacing="0" border="0">
						<thead>
							<tr>
								<th width="10px"></th>
					            <th width="320px">Name</th>
					            <th>Size</th>
					            <th>Description</th>
					            <th>Category</th>
							</tr>
						</thead>
					</table>
			    </div>
			    <div class="tbl-content">
					<table cellpadding="0" cellspacing="0" border="0">
						<tbody>
							<c:forEach items="${listFile}" var="file">
								<tr id="${file.getFileID()}">
									<td width="10px"><i class="fas fa-file fa-lg" style="margin-right: 4px"></i></td>
						            <td width="320px">
						            	<form id="fileInfo+${file.getFileID()}" action="file-info" method="post" style="display: inline">
										    <input type="hidden" name="file-id" value="${file.getFileID()}"/>
										    <a href="#" onclick="document.getElementById('fileInfo+${file.getFileID()}').submit()">${file.getName()}</a>
										</form>
						            </td>
						            <td>${file.getReadableFileSize()}</td>
						            <td>${file.getDescription()}</td>
						            <td>${file.getCategoryString()}</td>
				        		</tr>
			        		</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
    	</div>
    	<jsp:include page="sidebar.jsp"></jsp:include>
    </div>
</body>
</html>