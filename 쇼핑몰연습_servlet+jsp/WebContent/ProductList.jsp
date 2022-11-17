<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../include/header.jsp"%>
	<h1>상품목록</h1>
	<div style="width: 50%">
	<table>
		<th>상품 코드</th>
		<th>상품 이름</th>
		<th>상품 가격</th>
		<th>상품 수량</th>
		<th>상품 브랜드</th>
		<th>상품 설명</th>
		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getCode()%></td>
			<td><a href="/main?action=showDetail&code=<%=list.get(i).getCode()%>"><%=list.get(i).getName()%></a></td>
			<td><%=list.get(i).getPrice()%></td>
			<td><%=list.get(i).getMount()%></td>
			<td><%=list.get(i).getBrand()%></td>
			<td><%=list.get(i).getExp()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<form method="post" action="main">
		<input type="hidden" name="action" value="showRegistPage">
		<button type="form">추가등록</button>
	</form>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</body>
</html>