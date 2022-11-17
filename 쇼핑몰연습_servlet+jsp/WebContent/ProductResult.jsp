<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../include/header.jsp"%>
<h1>&nbsp 등록결과</h1>
<h3>&nbsp 등록된 총 리스트 수 : <%out.print(list.size());%></h3>
   <div style="width: 50%">
    <table border="1">
	<tr>
	    <td>코드명</td>
	    <td><%out.print(list.get(list.size()-1).getCode());%></td>
	    
	</tr>
	<tr>
	    <td>상품이름</td>
	    <td><%out.print(list.get(list.size()-1).getName());%></td>
	</tr>
		<tr>
	    <td>상품가격</td>
	    <td><%out.print(list.get(list.size()-1).getPrice());%></td>
	</tr>
		<tr>
	    <td>상품수량</td>
	    <td><%out.print(list.get(list.size()-1).getMount());%></td>
	</tr>
		<tr>
	    <td>브랜드명</td>
	    <td><%out.print(list.get(list.size()-1).getBrand());%></td>
	</tr>
		<tr>
	    <td>상품설명</td>
	    <td><%out.print(list.get(list.size()-1).getExp());%></td>
	</tr>
    </table>
    	<form method="post" action="main">
		<input type="hidden" name="action" value="showRegistPage">
		<button type="form">등록창으로 돌아가기</button>
	</form>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</body>
</html>