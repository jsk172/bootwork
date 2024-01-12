<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<div class="wrap">
		<h2>글목록</h2>
			<table class="tbl_write">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${boardList}" var="board">
						<tr>
							<td><a href="/board/detail?id=${board.id}"}>${board.id}</a></td>
							<td>${board.title}</td>
							<td>${board.writer}</td>
							<td><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="/"><button type="button">홈으로</button></a>
	</div>
</body>
</html>