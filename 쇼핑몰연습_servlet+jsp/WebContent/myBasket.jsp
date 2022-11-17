<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../include/header.jsp"%>
	
	<c:if test="${ !empty myBasket and fn:length(myBasket) > 0 }">
		<div style="margin-left: 50px">
			<h2>나의 장바구니</h2>
			<table style="margin: auto; text-align: center;">
				<th>상품 코드</th>
				<th>상품 이름</th>
				<th>상품 수량</th>
				<th>상품 가격</th>
				<c:forEach items="${myBasket}" var="myBasket">
					<tr>
						<td>${myBasket.pCode}</td>
						<td>${myBasket.pName}</td>
						<td>${myBasket.cnt}</td>
						<td>${myBasket.pPrice}</td>
					</tr>
				</c:forEach>
					<tr>
						<td colspan='3'>가격 총합</td>
						<td>${Sum}</td>					
					</tr>
			</table>
		</div>
	</c:if>
	
	
	<c:if test="${ empty myBasket or fn:length(myBasket) == 0 }">
			  장바구니에 담겨있는 상품이 없습니다.
	</c:if>
	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>	
</body>
</html>

