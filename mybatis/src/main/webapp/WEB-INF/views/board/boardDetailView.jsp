<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.study.mybatis.member.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%
Member m = (Member)session.getAttribute("loginUser");
String userId = m.getUserId();
%>
<meta charset="UTF-8">
<title>Board List</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<style>
table {
	border: 2px solid;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<div class="outer" align="center">
		<h1 align="center">게시판 상세조회</h1>

		<table border="1">
			<tr>
				<td width="100">글번호</td>
				<td width="500">${boardDetail.boardNo}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${boardDetail.boardTitle}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${boardDetail.boardWriter}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${boardDetail.count}</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${boardDetail.createDate }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td height="100">${boardDetail.boardContent }</td>
			</tr>
		</table>
		<br>
		<!-- <form action="insertReply.bo" method="get"> -->
		<table border="1">
			<tr>
				<th width="100">댓글작성</th>
				<th width="400"><textarea cols="53" rows="3" name="content" id="content"></textarea></th>
				<th width="100">
					<button id="replyInsert">등록</button> 
					<input type="hidden" name="boardNo" value="${boardDetail.boardNo}">
					<input type="hidden" name="userId" id="userId" value="${userId}">
					<input type="hidden" name="insertedReply" id="insertedReply" value=0>
				</th>

			</tr>
			<tr>
				<th colspan="3" style="text-align: center">댓글(${replyList.size() })</th>
			</tr>
			<c:forEach var="r" items="${replyList }">

				<tr>
					<td>${r.replyWriter }</td>
					<td>${r.replyContent }</td>
					<td>${r.createDate }</td>
				</tr>
			</c:forEach>
			<!-- 			<tr>
				<td>user02</td>
				<td>ㅋㅋㅋ</td>
				<td>2022-12-17</td>
			</tr>
 -->
		</table>
		<!-- </form> -->
	</div>
	<script>
		console.log(userId);
		$('#replyInsert').click(
				function() {
					const $userId =$("input[name=userId]").val();
					const $content=$("#content").val();
					const $boardNo=$("input[name=boardNo]").val();
					if (userId == null) {
						alert("로그인을 해주세요");
					}
					if (content == null || content == "null") {
						alert("댓글을 입력해주세요");
					}
					console.log("sdfsdf222");
					
					$.ajax({
						url : "insertReply.bo",
						data : {
							content : $content,
							boardNo : $boardNo
						},
						type : "post",
						success : function(result) {
							if (result == "true") {
								console.log("ajax ㅅㅂ")
								$("#insertedReply").val(1);
								location.href = "detail.bo?bno="+ $boardNo;
							}
						},
						error : function() {
							console.log("ajax 통신 실패");
						}
					})
				})
	</script>
</body>
</html>