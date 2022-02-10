<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="sidebar">
	<c:if test="${folderID == 0 || folderID == null}">
		<form action="search" method="post">
			<div class="simple-search">
				<input type="text" name="filename" placeholder="Type file name" maxlength="50" required/>
	        	<button type="submit"><i class="fas fa-search"></i></button>
	    	</div>
	    </form>
    </c:if>
	<c:if test="${sessionScope.acc != null && folderID != 0 && folderID != null}">
  		<div class="upload-section">
   			<form action="upload"  method="post" enctype="multipart/form-data">
   				<div class='file-input'>
    				<input type="file" id="file" name="file" required/>
    				<span class='button'>Choose</span>
  					<span class='label' data-js-label>No file selected</span>
 				</div>
 				<input type="hidden" name="folder" value="${folderID}">
				<input type="text" placeholder="Add description..." name="description" maxlength="50">
				<select name="category" required>
					<option value="" disabled selected>Choose category</option>
					<c:forEach items="${listCategory}" var="c">
			    		<option value="${c.getCateID()}">${c.getName()}</option>
			    	</c:forEach>
				</select>
				<input type="submit" value="Upload">
   			</form>
   		</div>
   	</c:if>
  	<h3 class="box-label-two"><i class="fas fa-file-alt"></i> Category</h3>
  	<div class="category">
  		<ul>
	   		<c:forEach items="${listCategory}" var="c">
	    		<li><a href="category?id=${c.getCateID()}">${c.getName()}</a></li>
	    	</c:forEach>
   		</ul>
	</div>
</div>
<script src="./js/uploadfile.js"></script>
<script src="./js/checkfilesize.js"></script>