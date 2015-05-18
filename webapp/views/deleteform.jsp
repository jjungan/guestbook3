<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	${no }
	<form method="post" action="/guestbook/gb?a=delete">
	<input type='hidden' name="no" value="${no }">
	<table>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password"></td>
			<td><input type="submit" value="확인"></td>
			<td style="color:Blue">${msg }</td>
			<td><a href="/guestbook/gb">메인으로 돌아가기</a></td>
		</tr>
	</table>
	</form>
</body>
</html>