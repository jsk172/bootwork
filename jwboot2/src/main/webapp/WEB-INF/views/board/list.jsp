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
<jsp:include page="../index.jsp"/>
	<div class="wrap">
		<h2>글목록</h2>
		<div class="row content">
		<div class="col">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">Search</h5>
					<form action="/todo/paging" method="get">
						<div class="mb-3">
							<input type="checkbox" name="types" value="t" ${pageRequest.checkType("t") ? 'checked' : ''}>제목
							<input type="checkbox" name="types" value="w" ${pageRequest.checkType("w") ? 'checked' : ''}>작성자
							<input type="text" name="keyword" class="form-control" value="${pageRequestDTO.keyword}">
						</div>
						<div class="mb-3">
							<div class="float-end">
								<button type="submit" class="btn btn-primary">Search</button>
								<button type="reset" class="btn btn-info btnClear">Clear</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
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