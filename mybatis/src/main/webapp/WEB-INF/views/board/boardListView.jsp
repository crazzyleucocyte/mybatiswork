<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
outer {
	width: 900px;
	margin: 0 auto;
}
</style>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<div class="outer">
		<h1 align="center">게 시 판</h1>

		<table class="table table-striped table-hover" align="center">
			<thead>
				<tr>
					<th>글번호</th>
					<th width="35%">제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="b" items="${list }" varStatus="s">
					<tr>
					
						<td>${pi.totalRecord-((pi.nowPage-1)*pi.numPerPage)-s.index }</td>
						<td>
							<a href="detail.bo?bno=${b.boardNo }">
								${b.boardTitle }
							</a>
						</td>
						<td>${b.boardWriter }</td>
						<td>${b.count }</td>
						<td>${b.createDate }</td>
				</c:forEach>



			</tbody>
		</table>

		<div align="center">
			nowPage= ${pi.nowPage }
			totalPage=${pi.totalPage}
			<c:if test="${pi.nowPage > 1 }">
				<a href="list.bo?nowPage=${pi.nowPage-1 }&keyField=${pi.keyField}&keyWord=${pi.keyWord}">[이전]</a>
			</c:if>

			<c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
				<c:choose>
					<c:when test="${pi.nowPage == p }">
						<a href="list.bo?nowPage=${p}&keyField=${pi.keyField}&keyWord=${pi.keyWord}" style="color:lightskyblue">[${p}]</a>
					</c:when>
					<c:otherwise>
						<a href="list.bo?nowPage=${p}&keyField=${pi.keyField}&keyWord=${pi.keyWord}">[${p}]</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pi.nowPage ne pi.totalPage }">
				<a href="list.bo?nowPage=${pi.nowPage + 1 }&keyField=${pi.keyField}&keyWord=${pi.keyWord}">[다음]</a>
				
			</c:if>
		</div>

		<div align="center">
			<form action="list.bo">
				<select name="keyField">
					<option value="writer">작성자</option>
					<option value="title">제목</option>
					<option value="content">내용</option>

				</select> 
				<input name="keyWord" value=${pi.keyWord }>
				<button>검색</button>
				<input type="hidden" name="nowPage" value="1">
			</form>
			<button type="button" onclick="location.href='list.bo?nowPage=${pi.nowPage}&keyWord=&keyField='">검색어 초기화</button>
		</div>

	</div>
</body>
</html>