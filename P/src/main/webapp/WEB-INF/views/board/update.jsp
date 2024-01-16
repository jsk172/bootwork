<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<div class="wrap">
		<h2>글수정</h2>
		<form action="/board/update" method="post">
		<input type="hidden" name="id" value="${board.id}">
			<table class="tbl_write">
				<tbody>
					<tr>
						<td>
							<input type="text" name="title" value="${board.title}">
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" name="writer" value="${board.writer}">
						</td>
					</tr>
					<tr>
						<td>
							<textarea rows="5" cols="50" name="content">${board.content}</textarea>
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="수정">
							<input type="reset" value="취소">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>