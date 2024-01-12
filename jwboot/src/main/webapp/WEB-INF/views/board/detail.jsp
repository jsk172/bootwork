<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세보기</title>
<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<div class="wrap">
		<h2>글상세보기</h2>
			<table class="tbl_write">
				<tbody>
					<tr>
						<td>
							<input type="text" name="title" value="${board.title}" readonly>
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" name="writer" value="${board.writer}" readonly>
						</td>
					</tr>
					<tr>
						<td>
							<textarea rows="5" cols="50" name="content" readonly>${board.content}</textarea>
						</td>
					</tr>
					<tr>
						<td>
							<a href="/board/update?id=${board.id}">
								<button type="button">수정</button>
							</a>
							<a href="/board/delete?id=${board.id}">
								<button type="button" onclick="return confirm('삭제하시겠습니까?')">삭제</button>
							</a>
							<a href="/board/"><button type="button">목록</button></a>
						</td>
					</tr>
				</tbody>
			</table>
	</div>
</body>
</html>