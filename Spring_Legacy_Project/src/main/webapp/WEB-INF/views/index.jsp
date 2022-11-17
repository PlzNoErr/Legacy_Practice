<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>샤핑몰</title>
</head>
<body>
	<%-- header.jsp를 include해서 재사용하기 --%>
	<%@ include file="./includes/header.jsp"%>
	<ul>
		<li><a href="${pageContext.request.contextPath}/product/list">상품목록</a></li>
		<li><a href="${pageContext.request.contextPath}/product/regist">상품등록</a></li>
	</ul>
</body>
</html>
