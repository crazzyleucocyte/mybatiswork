<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a {
	text-decoration: none;
	color: black;
	cursor: pointer;
}

a:hover {
	color: lightcoral;
}

.nav-area {
	text-align: center;
}

.nav-area {
	background-color: bisque;
}

.nav-area>div {
	width: 24.5%;
	height: 60px;
	line-height: 60px;
	display: inline-block;
}

.nav-area>div:hover {
	color: lightcoral;
	background-color: rgb(255, 201, 135);
}
outer{
	width:900px;
	margin:0 auto;
	
}
.menu:hover{
	cursor:pointer;
}
</style>
</head>
<body>
	여기는 메뉴바
	<h1 align="center">Welcon to Mybatis</h1>

	<div class="login-area" align="right">
		<c:choose>
			<c:when test="${empty loginUser }">
				<!-- 로그인 전 -->
				<form action="login.me" method="post">
					<table>
						<tr>
							<td>아이디</td>
							<td><input type="text" name="userId" required></td>

							<td rowspan="2"><button style="height: 50px">로그인</button></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="userPwd" required></td>
						</tr>
						<tr>
							<td colspan="3">
								<a href="enrollForm.me">회원가입</a>
								<a href="">아이디/비밀번호찾기</a>
							</td>

						</tr>
					</table>
				</form>
			</c:when>
			<c:otherwise>
				<!-- 로그인 후 -->

				<table class="login-area">
					<tr>
						<td colspan="2">
							<h3>${loginUser.userName }님 환영합니다</h3>
						</td>
					</tr>
					<tr>
						<td><a href="">마이페이지</a></td>
						<td><a href="logout.me">로그아웃</a></td>
					</tr>
				</table>

			</c:otherwise>
		</c:choose>
	</div>

	<div>
		<nav class="nav-area">
			<div class="menu">HOME</div>
			<div class="menu">공지사항</div>
			<div class="menu" onclick="location.href='list.bo?nowPage=1'">게시판</div>
			<div class="menu">ETC</div>
		</nav>
	</div>





















</body>
</html>