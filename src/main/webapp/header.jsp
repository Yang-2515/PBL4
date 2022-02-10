<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<header>
	<div class="nav-menu">
	    <ul>
	        <li><a href="homepage">Home</a></li>
	        <c:if test="${sessionScope.acc != null}">
	        	<li><a href="my-files">My Files</a></li>
	        </c:if>
	        <c:if test="${sessionScope.role == 1}">
	        	<li><a href="admin-file">All Files</a></li>
	        	<li><a href="admin-user">User</a></li>
	        </c:if>
	        <c:if test="${sessionScope.acc != null}">
                <li class="nav-item">
                    Hello <span onclick="window.location.href='user-info?user-id=${sessionScope.acc.getUserID()}'" style="cursor:pointer" class="usrname">${sessionScope.acc.username}</span>
                    <a href="logout">Logout</a>
                </li>
            </c:if>
	        <c:if test="${sessionScope.acc == null}">
	        	<li class="nav-login" ><a href="login">Login</a></li>
	        </c:if>
	    </ul>
	</div>
	<div class="clearfix"></div>
	<a href="homepage"><img src="./img/logo.png" alt="DUT Dormitory Documents" id="logo"></a>
	<div id="title-area">
		<h1 id="title">DUT Dormitory Documents</h1>
		<div class="clearfix"></div>
		<p id="subtitle">Study together <i class="far fa-file"></i></p>
	</div>
	
</header>