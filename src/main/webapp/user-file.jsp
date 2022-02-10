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
	<title>My Files</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
    	<div class="main">
    		<form action="add-folder" method="post">
				<input type="text" class="input-focus" placeholder="Enter folder name" name="folder-name">
				<input type="text" class="input-focus" placeholder="Enter folder description" name="description">
				<button class="btn" type="submit" style="display:inline-block">Add folder</button>
	  		</form>
	  		<p class="text-danger" style="margin-bottom:10px;">${mess}</p>
    		<section class="user-section">
				<h1><i class="fas fa-file-alt"></i> MY FILES</h1>
				<div class="tbl-header">
					<table cellpadding="0" cellspacing="0" border="0">
						<thead>
							<tr>
								<th width="10px"></th>
					            <th width="250px" class="action">Name <a href="#" onclick="sortTable(1)"><i class="fas fa-sort"></i></a></th>
					            <c:if test="${folderID != 0}"><th>Size</th></c:if>
					            <c:if test="${folderID != 0}"><th>Description</th></c:if>
					            <c:if test="${folderID == 0}"><th colspan="3">Description</th></c:if>
					            <c:if test="${folderID != 0}"><th>Category</th></c:if>
					            <th width="180px">Action</th>
							</tr>
						</thead>
					</table>
			    </div>
			    <div class="tbl-content">
					<table cellpadding="0" cellspacing="0" border="0" id="myTable">
						<tbody>
							<c:forEach items="${listFolder}" var="folder">
								<tr>
									<td width="10px"><i class="fas fa-folder fa-lg" style="margin-right: 4px"></i></td>
						            <td width="250px">
						            	<form id="folderInfo+${folder.getFolderID()}" action="my-files" method="post" style="display: inline">
										    <input type="hidden" name="folder-id" value="${folder.getFolderID()}"/>
										    <a href="#" onclick="document.getElementById('folderInfo+${folder.getFolderID()}').submit()">${folder.getName()}</a>
										</form>
						            </td>
						            <td colspan="3">${folder.getDescription()}</td>
						            <td class="action" width="180px">
						            	<a href="share-folder?folder-id=${folder.getFolderID()}"><i class="fas fa-share-square fa-lg item"></i></a>
						            	<a href="edit-folder?folder-id=${folder.getFolderID()}"><i class="fas fa-edit fa-lg item"></i></a>
						            	<form id="deleteFolder+${folder.getFolderID()}" action="delete" method="post" style="display: inline">
										    <input type="hidden" name="delFolder" value="${folder.getFolderID()}"/>
										    <a href="#" onclick="document.getElementById('deleteFolder+${folder.getFolderID()}').submit()"><i class="fas fa-trash-alt fa-lg item"></i></a>
										</form>
						            </td>
				        		</tr>
			        		</c:forEach>
			        		<c:if test="${folderID != 0}">
				                <tr>
									<td width="10px"><i class="fas fa-folder fa-lg" style="margin-right: 4px"></i></td>
						            <td width="250px"><a href="my-files">...</a></td>
						            <td></td>
						            <td></td>
						            <td></td>
						            <td width="180px"></td>
				        		</tr>
								<c:forEach items="${listFile}" var="file">
									<tr>
										<td width="10px"><i class="fas fa-file fa-lg" style="margin-right: 4px"></i></td>
							            <td width="250px">
							            	<form id="fileInfo+${file.getFileID()}" action="file-info" method="post" style="display: inline">
											    <input type="hidden" name="file-id" value="${file.getFileID()}"/>
											    <a href="#" onclick="document.getElementById('fileInfo+${file.getFileID()}').submit()">${file.getName()}</a>
											</form>
							            </td>
							            <td>${file.getReadableFileSize()}</td>
							            <td>${file.getDescription()}</td>
							            <td>${file.getCategoryString()}</td>
							            <td class="action" width="180px">
							            	<a href="share-file?file-id=${file.getFileID()}"><i class="fas fa-share-square fa-lg item" ></i></a>
							            	<a href="edit-file?file-id=${file.getFileID()}"><i class="fas fa-edit fa-lg item"></i></a>
							            	<form id="deleteFile+${file.getFileID()}" action="delete" method="post" style="display: inline">
							            		<input type="hidden" name="IDfolder" value="${folderID}"/>
											    <input type="hidden" name="file-id" value="${file.getFileID()}"/>
											    <a href="#" onclick="document.getElementById('deleteFile+${file.getFileID()}').submit()"><i class="fas fa-trash-alt fa-lg item"></i></a>
											</form>
							            </td>
					        		</tr>
				        		</c:forEach>
			        		</c:if>
						</tbody>
					</table>
				</div>
			</section>
    	</div>
    	<jsp:include page="sidebar.jsp"></jsp:include>
    </div>
    <script src="./js/sorttable.js"></script>
</body>
</html>