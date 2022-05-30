<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.PersonVo"%>
<%@ page import="java.util.*"%>

<%
	List<PersonVo> pList = (List<PersonVo>)request.getAttribute("pList");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>전화번호부</h1>
		<p>입력한 정보의 내역입니다.</p>
		
		<%for(int i=0; i<pList.size(); i++) {%>
			<table border="1">
				<tr>
					<td>이름(name)</td>
					<td><%=pList.get(i).getName() %></td>
				</tr>
				<tr>
					<td>휴대전화(hp)</td>
					<td><%=pList.get(i).getHp() %></td>
				</tr>
				<tr>
					<td>회사번호(company)</td>
					<td><%=pList.get(i).getCompany() %></td>
				</tr>
				<tr>
					<td><a href="./pbc?action=updateForm&id=<%=pList.get(i).getPersonId() %>">[수정]</a></td>
					<td><a href="./pbc?action=delete&id=<%=pList.get(i).getPersonId() %>">[삭제]</a></td>
				</tr>
			</table>
			<br>
		<%} %>
		
		<a href="./pbc?action=writeForm">추가번호 등록</a>
	</body>
</html>