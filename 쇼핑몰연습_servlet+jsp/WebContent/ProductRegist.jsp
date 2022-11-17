<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../include/header.jsp"%>
	<h1>&nbsp SSAFY 상품관리</h1>
	<div style="margin-left: 10px;">
	<form method="post" style="width: 50%" action="main">
		<fieldset>
		<legend>정보 입력</legend>
		<!-- front-controller pattern에서 요청을 구분하기 위한 parameter -->
		<input type="hidden" name="action" value="ProductRegist">		
		<label for="code">상품 코드</label>
		<input type="text" id="code" name="code"><br><br>
		<label for="name">상품 이름</label>
		<input type="text" id="name" name="name"><br><br>
		<label for="price">가격</label>
		<input type="number" id="price" name="price"><br><br>
		<label for="mount">수량</label>
		<input type="number" id="mount" name="mount"><br><br>
		<label for="brand">브랜드</label>
		<input type="text" id="brand" name="brand"><br><br>
		<label for="exp">설명</label><br>
		<input type="text" style="width: 95%; height: 80px;" id="exp" name="exp"><br><br>
		<input type="submit" value="등록">
		<input type="reset" value="초기화">
		</fieldset>
	</form>
	<br>
	<form method="post" action="main">
		<input type="hidden" name="action" value="ProductList">
		<button type="form" >상품목록으로 이동하기</button>
	</form>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</body>
</html>