<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>



<div>
	<form role="form" method="post">
		<input type='hidden' name='no' value="${boardVO.no}">
		title: <input type='text' name='title' value="${boardVO.title}" size="50"><br>
		Content: <input type="text" name='content' value="${boardVO.content}"  size="80">
	</form>
</div>

<br>

<div>
	<button type="submit" class="btn btn-warning">수정</button>
	<button type="submit" class="btn btn-danger">삭제</button>
	<button type="submit" class="btn btn-primary">목록보기</button>
</div>

<script>				
	$(document).ready(function(){
		
		var formObj = $("form[role='form']");
		
		console.log(formObj);
		
		$(".btn-warning").on("click", function(){
			formObj.attr("action", "/sample/board/update");
			formObj.submit();
		});

		$(".btn-danger").on("click", function(){
			formObj.attr("action", "/sample/board/delete");
			formObj.submit();
		});
		
		$(".btn-primary").on("click", function(){
			self.location = "/sample/board/listAll";
		});
		
	});
</script>



<%@include file="../include/footer.jsp"%>
