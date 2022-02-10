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
    <script type="text/javascript">
    	function check(){
    		var select = document.shareform.sharemode
    		var group = document.shareform.listshare
    		if (select.value !== "group") group.setAttribute("disabled", "disabled")
    		else group.removeAttribute("disabled")
    	}
    </script>
	<title>${file.getName()} - File Sharing</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
    	<div class="main">
    		<button class="btn" onclick="javascript:history.back()"><i class="fas fa-arrow-circle-left"></i> Back</a></button>
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
    		<form action="share-file" method="post" style="margin-top: 15px; text-align: center;" name="shareform">
    			<input type="hidden" value="${file.getFileID()}" name="fileID">
    			Sharing Mode: 
    			<% String mode = (String) request.getAttribute("mode"); %>
    			<select name="sharemode" style="padding: 10px; width: 200px; margin-bottom: 15px;" onchange="check()">
    				<option value="public" <% if (mode.equals("public")) {%> selected <% } %>>Public</option>
    				<option value="group" <% if (mode.equals("group")) {%> selected <% } %>>Group</option>
    				<option value="private" <% if (mode.equals("private")) {%> selected <% } %>>Private</option>
    			</select>
    			<p>List Share ID: <br><textarea name="listshare" style="padding: 10px; width: 200px; height: 150px;margin-bottom: 15px;" <% if (!mode.equals("group")) { %>disabled<% } %> placeholder="102190222, 102129000, ...">${listShareID}</textarea></p>
    			<p>Link: <input type="text" value="${myuri}?file-id=${file.getFileID()}" style="padding: 10px; width: 40%; display:inline" readonly></p>
    			<button class="btn" type="submit" style="margin: 10px auto">Confirm</button>
    		</form>
    	</div>
    	<jsp:include page="sidebar.jsp"></jsp:include>
    </div>
</body>
</html>