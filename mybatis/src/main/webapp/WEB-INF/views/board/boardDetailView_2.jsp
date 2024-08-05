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
					<input type="hidden" name="insertedReply" id="insertedReply" value=>
				</th>

			</tr>
			<tr>
				<th colspan="3" style="text-align: center">댓글(${replyList.size() })</th>
			</tr>
			<tbody id="replyList">
			<c:if test="${not empty replyList }">
			<c:forEach var="r" items="${replyList }">

				<tr>
					<td>${r.replyWriter }</td>
					<td>${r.replyContent }</td>
					<td>${r.createDate }</td>
				</tr>
			</c:forEach>
			</c:if>
			<!-- 			<tr>
				<td>user02</td>
				<td>ㅋㅋㅋ</td>
				<td>2022-12-17</td>
			</tr>
 -->
			</tbody>
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
					
					$reply=$.ajax({
						url : "insertReply.bo",
						data : {
							content : $content,
							boardNo : $boardNo
						},
						type : "post",
						success : function(result) {
							if (result == "true") {
								$.ajax({
									url : "detail.bo",
									data : {bno : $boardNo},
									type : "post",
									success : function(result){
										let html="";
										for(let i =0;i<result.length;i++){
										html += "<tr>"
												   + "    <td>"+result[i].replyWriter +"</td>"
												   + "    <td>"+result[i].replyContent+"</td>"
												   + "    <td>"+result[i].createDate.substring(0,10)+"</td>"
												   + "</tr>"
										}
										$("#replyList").html(html);
										$("#content").val("");
									},
									error : function(){
										console.log("ajax 통신 실패");
									}
								})
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