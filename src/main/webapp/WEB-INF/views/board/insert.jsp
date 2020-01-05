<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>



<div>
	<form role="form" method="post">
		<input type='hidden' name='no' value="">
		제목: <input type='text' name='title' value="" size="50"> <br>
		내용: <input type="text" name='content' value="" size="80">
	</form>
</div>

<br>

<div>
	<button type="submit" class="btn btn-insert">추가</button>
	<button type="submit" class="btn btn-primary">목록보기</button>
</div>

<script>				
	$(document).ready(function(){
		
		var formObj = $("form[role='form']");
		
		console.log(formObj);
		
		$(".btn-insert").on("click", function(){
			formObj.attr("action", "/sample/board/insert");
			formObj.submit();
		});

		
		$(".btn-primary").on("click", function(){
			self.location = "/sample/board/listAll";
		});
		
	});
</script>



<%@include file="../include/footer.jsp"%>
