<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@include file="../include/header.jsp"%>





<table border="1">
	<tr>
		<th style="width:50px">글번호</th>
		<th style="width:100px">제목</th>
		<th>작성일자</th>
		<th style="width:50px">조회수</th>
	</tr>


<c:forEach items="${list}" var="boardVO">

	<tr>
		<td style="width:50px" align="center"> ${boardVO.no}</td>
		<td style="width:250px"><a href='/cloud/board/read?no=${boardVO.no}'>${boardVO.title}</a></td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.day}" /></td>
		<td style="width:50px" align="center"><span>${boardVO.hit }</span></td>
	</tr>

</c:forEach>

</table>


<div align="left">
	<a href='/cloud/board/insert'>글쓰기</a>
</div>
			


<%@include file="../include/footer.jsp"%>