<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- DTO를 참조하기 위해서 import 처리가 필요하다. -->
<%@ page import="com.ssafy.hw.model.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>샤핑몰</title>
<style>
#product-list {
	border-collapse: collapse;
	width: 100%;
}

#product-list td, #product-list th {
	border: 1px solid black;
}
</style>
</head>
<body>
	<%-- header.jsp를 include해서 재사용하기 --%>
	<%@ include file="../includes/header.jsp"%>
	<h1>상품 목록</h1>
	<table id=product-list>
		<thead>
			<tr>
				<th>상품코드</th>
				<th>상품이름</th>
				<th>가격</th>
				<th>수량</th>
				<th>브랜드</th>
				<th>설명</th>
			</tr>
		</thead>
		<tbody>
			<%-- request 영역에 products로 등록된 자료를 반복문을 이용해 출력한다. --%>
			<c:forEach items="${productList }" var="product" varStatus="vs">
			<tr>
				<td>${product.pCode }</td>
				<td><a href="${pageContext.request.contextPath}/product/detail?pCode=${product.pCode }">${product.pName }</a></td>
				<td>${product.price }</td>
				<td>${product.quantity }</td>
				<td>${product.brand }</td>
				<td>${product.pDesc }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a href="${pageContext.request.contextPath}/product/regist">상품등록</a>
	</div>
</body>
</html>