<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../include/header.jsp"%>
	<h1>상품이름 : ${Product.name}</h1>
	<br>
	<br> 상품코드 : ${Product.code}
	<br> 상품가격 : ${Product.price}
	<br>
	<form method="post" action="main">
		<input type="hidden" name="action" value="ProductList">
		<button type="form">상품목록으로 이동하기</button>
	</form>
		<form method="post" action="main">
		<input type="hidden" name="action" value="goaddBasket">
		<input type="hidden" name="code" value="${Product.code}">
		<button type="form">장바구니로 이동하기</button>
	</form>
	<hr>
	<h1>리뷰</h1>
	<div style="width: 50%">
	<c:if test="${ !empty Rlist and fn:length(Rlist) > 0 }">
		<c:forEach items="${Rlist}" var="Rlist">
			<p>${Rlist.content} &nbsp &nbsp by &nbsp ${Rlist.writer}</p>
		</c:forEach>
	</c:if>
	<c:if test="${ empty Rlist or fn:length(Rlist) == 0 }">
			  달린 리뷰가 없습니다.
	</c:if>
	</div>
	<hr>
	
	<div style="width: 50%">
	<h3>리뷰작성</h3>
	<form method="post" action="main">
		<input type="hidden" name="action" value="registReview"> <input
			type="hidden" name="code" value="${Product.code}">
		<div>
			<label for="title" class="form-label">작성자</label> <input type="text"
				class="form-control" id="writer" name="writer">
		</div>
		<div>
			<label for="content" class="form-label">작성내용</label>
			<textarea rows="4" id="content" name="content"></textarea>
		</div>
		<input type="submit" value="등록">
	</form>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</body>
</html>