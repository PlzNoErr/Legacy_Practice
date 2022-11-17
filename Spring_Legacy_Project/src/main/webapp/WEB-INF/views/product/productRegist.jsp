<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>샤핑몰</title>
<style>
	label{
		display: inline-block;
		width: 80px;
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
	<form method="post" action="${pageContext.request.contextPath}/product/regist" enctype="multipart/form-data">
		<fieldset>
		<legend>상품 입력</legend>
		<label for="pCode">상품코드</label>
		<input type="text" id="pCode" name="pCode"><br><br>
		<label for="pName">상품이름</label>
		<input type="text" id="pName" name="pName"><br><br>
		<label for="price">가격</label>
		<input type="number" id="price" name="price"><br><br>
		<label for="quantity">수량</label>
		<input type="number" id="quantity" name="quantity"><br><br>
		<label for="brand">브랜드</label>
		<input type="text" id="brand" name="brand"><br><br>
		<label for="desc">설명</label>
		<br>
		<textarea id="desc" name="pDesc"></textarea><br>
		<label for="file">첨부파일</label>
		<input type="file" id="file" name="file"><br><br>
		<input type="submit" value="등록">
		<input type="reset" value="취소">
		</fieldset>
	</form>
</body>
</html>