<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>



<div>
	<form role="form" method="post">
		<input type='hidden' name='no' id = 'no' value="${boardVO.no}">
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


<br>

<div>
댓글: <input type="text" id="newReplyText">
</div>
<br>

<div>
	<button type="submit" class="btn ">댓글보기</button>
	<button type="submit" class="btn replyAddBtn">댓글쓰기</button>
	<button type="submit" class="btn ">댓글삭제</button>
</div>

<br>

<script>				
	$(document).ready(function(){
		
		var formObj = $("form[role='form']");
		
		console.log(formObj);
		
		$(".btn-warning").on("click", function(){
			formObj.attr("action", "/cloud/board/update");
			formObj.submit();
		});

		$(".btn-danger").on("click", function(){
			formObj.attr("action", "/cloud/board/delete");
			formObj.submit();
		});
		
		$(".btn-primary").on("click", function(){
			self.location = "/cloud/board/listAll";
		});
		
		
		
		$(".replyAddBtn").on("click",function(){
			 
			 var replytext    = $("#newReplyText").val();
			 var no           = $("#no").val();
			 
			  $.ajax({
				  	
				  type : 'post',
					url : '/cloud/replies',
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
					dataType : 'text',
					data : JSON.stringify({
						no : no,
						content : replytext
					}),
					success : function(result) {
		
						if (result == 'SUCCESS') {
							alert("등록 되었습니다.");
							
							selectReply(no);
						}
						
					},
			        
			        error: function(result) { 
			        	console.log(result); 
			        }
			});
		});
	});
	
function selectReply(no){
	
	var str;
	
	$.getJSON(
			"/cloud/replies/all/" + no,
			
			function(data) {	
				
				console.log(data);
				
				var str = "";
				$(data).each(
							function() {
										str += this.no
											+  " : "
											+  this.content;
								        }
							);

				console.log(str);
				
			});
}
	

</script>



<%@include file="../include/footer.jsp"%>
