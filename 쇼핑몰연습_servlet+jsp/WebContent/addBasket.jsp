<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../include/header.jsp"%>
<c:if test="${empty loginUser }">
로그인을 필요로 하는 서비스 입니다.
</c:if>

<c:if test="${!empty loginUser }">
<h1>장바구니 담기</h1>
<div style="margin-left: 10px;">
	<form method="post" style="width: 50%" action="main">
		<fieldset>
		<input type="hidden" name="action" value="addBasket">
		<input type="hidden" name="code" value="${Product.code}">		
		<label for="code">상품 코드</label>
		<input type="text" id="code" name="code" value="${Product.code}"><br><br>
		<label for="name">상품 이름</label>
		<input type="text" id="name" name="name" value="${Product.name}"><br><br>
		<label for="price">가격</label>
		<input type="number" id="price" name="price" value="${Product.price}"><br><br>
		<label for="mount">수량</label>
		<input type="number" id="mount" name="mount"><br><br>
		<input type="submit" value="장바구니에 담기">
		</fieldset>
	</form>
		<form method="post" action="main">
			<input type="hidden" name="action" value="ProductList">
			<button type="form">상품목록으로 이동하기</button>
		</form>
</div>
</c:if>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>	
</body>
</html>

