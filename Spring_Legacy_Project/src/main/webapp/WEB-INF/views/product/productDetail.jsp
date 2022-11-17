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
.product {
	border: 2px solid black;
	border-radius: 50px 50px 50px 50px;
}

.product>ul {
	list-style-type: none;
}

li span {
	border-bottom: 2px solid skyblue;
	padding-bottom: 10px;
}
</style>
</head>
<body>
	<%-- header.jsp를 include해서 재사용하기 --%>
	<%@ include file="../includes/header.jsp"%>
	<div class="product">
		<ul>
			<li>
				<h2>상품 이름 : ${product.getpName() }</h2>
				<h4>상품코드 : ${product.getpCode() }</h4>
				<h4>가격 : ${product.getPrice() }</h4> 
			</li>
			<li><a href="${pageContext.request.contextPath }/download?fileName=${product.fileName }" >첨부파일</a></li>
		</ul>
	</div>
	<hr>
		<button onclick="location.href='${pageContext.request.contextPath}/product/delete?pCode=${product.getpCode()}'">삭제</button>
		<button onclick="location.href='${pageContext.request.contextPath}/product/update?pCode=${product.getpCode()}'">수정</button>
		<button onclick="location.href='${pageContext.request.contextPath}/product/list'">상품 목록</button>
	<hr>
	<h4>리뷰</h4>
	<c:if test="${empty reviews}">
		<h5>등록된 리뷰가 없습니다</h5>
	</c:if>
	<c:forEach items="${reviews}" var="review" varStatus="vs">
		<div>
			<h5>${review.content} by ${review.writer }</h5>
			<input type="button" value="삭제"
				onclick="location.href='${pageContext.request.contextPath}/review/delete?reviewId=${review.reviewId}&pCode=${product.pCode }'" />
		</div>
	</c:forEach>
	<br>
	<form method="post" 
		action="${pageContext.request.contextPath}/review/regist">
	<input type="hidden" name = "pCode" id="pCode" value="${product.pCode }">
		<fieldset>
			<legend>리뷰 작성</legend>
			<table>
				<tr>
					<td><label for="writer">작성자</label></td>
					<td><input type="text" id="writer" name="writer"></td>
				</tr>
				<tr>
					<td><label for="content">내 용</label></td>
					<td><textarea cols="50" id="content" name="content"></textarea></td>
				</tr>
			</table>
			<input type="submit" value="등록">
		</fieldset>
	</form>
</body>
</html>