<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<div>
		<h2>회원가입</h2>
		<form action="../JoinController" method="post">

			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="userid" required></td>
				</tr>
				<tr>
					<td>이름:</td>
					<td><input type="text" name="name" required></td>
				</tr>
				<tr>
					<td>비밀번호:</td>
					<td><input type="password" name="pwd" required></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" required></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="date" name="birth" required></td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="회원가입">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>