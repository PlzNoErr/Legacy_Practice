<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="manager.ProductManager" import="dto.Product" import="dto.Basket"
	import="java.util.*" pageEncoding="UTF-8"%>
<%!ProductManager manager = ProductManager.getInstance();
	ArrayList<Product> list = manager.getList();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<style>
	label {
		display: inline-block;
		width: 80px;
	}
</style>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<title>SSAFY 쇼핑몰</title>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">SSAFY 쌰핑몰</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
      </ul>     
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
      
      <c:if test="${empty loginUser }">
        <li class="nav-item">
          <a class="nav-link" href="/user/signup.jsp">회원가입</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/user/login.jsp">로그인</a>
        </li>
      </c:if>
      
      <c:if test="${!empty loginUser }">
        <li class="nav-item">
          <p class="nav-link" >${ loginUser.userName }님 환영합니다.</p>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/main?action=logout">로그아웃</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/main?action=myBasket&userId=${loginUser.userId}">장바구니</a>
        </li>
      </c:if>
      </ul>
    </div>
  </div>
</nav>
