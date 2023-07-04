<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath' value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
%>
<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Boar Read</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
			<form role='form' action='/board/modify' method='post'>
				<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/> '>
				<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/> '>
				<input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>' />
				<input type='hidden' name='type' value='<c:out value="${cri.type}"/>' />
				<div class="form-group">
					<label>Bno</label> <input class="form-control" name="bno"
						value='<c:out value="${board.bno}" />' readonly="readonly">
				</div>
				<div class="form-group">
					<label>Title</label> <input class="form-control" name="Title"
						value='<c:out value="${board.title}" />' >
				</div>
				<div class="form-group">
					<label>Text area</label>
					<textarea class="form-control" rows="3" name="content"
						><c:out value="${board.content}" />
						 </textarea>
				</div>
				<div class="form-group">
					<label>Writer</label> <input class="form-control" name="writer"
						name="writer" value="${board.writer}" readonly="readonly">
				</div>
				<div class="form-group">
					<label>RegData</label>
					<input class="form-control" name='regData' value='<fmt:formatDate pattern='yyyy/MM/dd' value="${board.regdate}" />'
					readonly='readonly' />
				</div>
				<div class="form-group">
					<label>Update Data</label>
					<input class="form-control" name='updateData' value='<fmt:formatDate pattern='yyyy/MM/dd' value="${board.updateDate}" />'
					readonly='readonly' />
				</div>
				
				<button type='submit' data-oper='modify' class='btn btn-default'>Modify</button>
				<button type='submit' data-oper='remove' class='btn btn-danger'>Remove</button>
				<button type='submit' data-oper='list' class='btn btn-info'>List</button>
				</form>
				<!-- /.table-responsive -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
</div>
</div>
<!-- /#page-wrapper -->
<script >
$(document).ready(function(){
	
	var formObj=$('form');
	//form 을 제어하는 펑션 
	
	$('button').on("click",function(e){
		e.preventDefault();
		//버튼을 눌렀을떄 다른것은 실행되지마라 form문을 실행하지 못하게 막는다
		var operation =$(this).data("oper");
		//버튼의 누른 oper데이터 값을 가져온다
		
		console.log(operation);
		//값이 들어왔는지 값을 확인한다
		if(operation ==='remove'){
			//들어온값이 remove와 같으면 이걸 시행
			formObj.attr("action","/board/remove");
			//얘는 bno를 가져가야하기 떄문에 내용을 지울 필요가없다
			//삭제에서는 가져가는값은 bno값만 골라가가져가기떄문에 굳이 지워줄 필요가없다 
		}else if(operation==='list'){
		//	self.location="/board/list";
	    //  return;
	    formObj.attr("action","/board/list").attr("method",'get');
	    var pageNum=$("input[name='pageNum']").clone();
	    var amount=$("input[name='amount']").clone();
	    var keyword=$("input[name='keyword']").clone();
	    var type=$("input[name='type']").clone();
	    //form의 액션과 메소드를 이걸로 변경시켜라
	    formObj.empty();
	    formObj.append(pageNum);
	    formObj.append(amount);
	    formObj.append(keyword);
	    formObj.append(type);
	    //form 문안에있는 내용을 다삭제하고 실행시켜라
		}
		//만약 modify가 들어오면 if문에서 걸리지않기떄문에 맨아래있는 submit가 실행된다 
		formObj.submit();
		//서브밋 시켜라 
	});
});
</script>

<%@include file="../includes/footer.jsp"%>
