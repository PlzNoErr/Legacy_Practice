<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>샤핑몰</title>
<style>
	label{
		display: inline-block;
		width: 100px;
	}
	textarea{
		width: 100%;
	}
</style>
</head>
<body>
	<%-- header.jsp를 include해서 재사용하기 --%>
	<%@ include file="../includes/header.jsp"%>
	<h1>SSAFY 상품 관리</h1>
	<form method="post" action="${pageContext.request.contextPath}/product/update">
		<fieldset>
		<legend>상품 수정</legend>
		<input type="hidden" name="pCode" value="${product.pCode}">
		<label for="pName">상품이름</label>
		<input type="text" id="pName" name="pName" value="${product.pName }" readonly><br><br>
		<label for="price">가격</label>
		<input type="number" id="price" name="price" value="${product.price }"><br><br>
		<label for="quantity">수량</label>
		<input type="number" id="quantity" name="quantity" value="${product.quantity }"><br><br>
		<label for="brand">브랜드</label>
		<input type="text" id="brand" name="brand" value="${product.brand }"><br><br>
		<label for="pDesc">설명</label>
		<br>
		<textarea id="pDesc" name="pDesc">${product.pDesc }</textarea><br>
		<input type="submit" value="등록">
		<input type="reset" value="취소">
		</fieldset>
	</form>
</body>
</html>