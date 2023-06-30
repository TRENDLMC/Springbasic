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
		<h1 class="page-header">Board List</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board List Page
			<button id='regBtn' type='button' class='btn btn-xs pull-right'>
				Register New Board
			</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<c:forEach items="${list}" var="board">
						<tr>
							<td><c:out value="${board.bno}" /></td>
							<td>
							<a class="move" href="<c:out value="${board.bno}"/>"> <!--  target='_blank' -->
							<c:out value="${board.title}" />
							</a>
							</td>
							<td><c:out value="${board.writer}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.regdate}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.updateDate}" /></td>
						</tr>
					</c:forEach>
				</table>
				<form id="searchForm" action="/board/list" method="get">
				<select name="type">
				<option value=""
				<c:out value="${pageMaker.cri.type==null?'selected':''}"/>
				>검색옵션</option>
				<option value="T"
				<c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>
				>제목</option>
				<option value="C"
				<c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
				<option value="W"
				<c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
				<option value="TC"
				<c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목or내용</option>
				<option value="TW"
				<c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>제목or작성자</option>
				<option value="TCW"
				<c:out value="${pageMaker.cri.type eq 'TCW'?'selected':''}"/>>내용or내용or작성자</option>
				</select>
				<input type='text' name='keyword'
				value="<c:out value='${pageMaker.cri.keyword}'/>" />
				<input type='hidden' name='pageNum' value="<c:out value='${pageMaker.cri.pageNum}'/>" />
				<input type='hidden' name='amount' value="<c:out value='${pageMaker.cri.amount}'/>" />
				<button class="btn btn-default" >Search</button>
				</form>
				<h3>${pageMaker}</h3>
				<div class='pull-right'>
				<ul class='pagination'>
				<c:if test="${pageMaker.prev}">
				<li class='paginate_button previous'>
				<a href="${pageMaker.startPage-1}"> prev</a>
				</c:if>
				<c:forEach begin='${pageMaker.startPage}' end="${pageMaker.endPage}" var='num'>
					<li class='paginate_button ${pageMaker.cri.pageNum==num?"active":""}'><a href="${num}">${num}</a>
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next}">
				<li class='paginate_button next'>
				<a href="${pageMaker.endPage+1 }"> Next</a>
				</c:if>
				</ul>
				</div>
				<form action='/board/list' method='get' id='actionForm'>
				<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
				<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
				<input type='hidden' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>' />
				<input type='hidden' name='type' value='<c:out value="${pageMaker.cri.type}"/>' />
				</form>
				<!-- /.table-responsive -->
				<!-- 새로입력 모달 시작 -->
				<div id="myModal" class="modal" tabindex="-1" role="dialog">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Modal title</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<p>처리가 완료 되었습니다.</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary">Save
									changes</button>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<!-- 새로입력 모달 끝 -->
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

<!-- 모달 스크립트 펑션  시작 -->				
<script>
$(document).ready(function(){
	var result = '<c:out value = "${result}"/>';
	
	checkModal(result);
	
	history.replaceState({},null,null);
	// 글을 작성한후  리스트로 이동한후 뒤로가기 버튼을 누르면 글작성으로 돌아가게되는데
	//그후 다시 앞으로 가기를 누르면 글이 재등록되는데 위에 history를 지워줌으로써 앞으로 가거나 뒤로가도 글이 등록되지않는다
	function checkModal(result){
		if (result === ''|| history.state){
			return;
		}
	
		if(parseInt(result) > 0){
			//조건문 result를 숫자타입으로 변경해서 0보다 크다면 실행해라 
			$(".modal-body").html(
				"게시물 " + parseInt(result) + " 번이 등록되었습니다.");
			//모달 바디라는 아이디에다가  html을 넣어라 내용은 게시물+번호+등록되었습니다라는 텍스트를.
		}
		$("#myModal").modal("show");
		//마이 모달이라는 아이디를가진애를 modal.show 모달을 보여줘라.
	}
	
	$("#regBtn").on("click", function(){
		//regBtn이라는 아이디를 가진 것이 onclick 클릭되면 실행해라 펑션을 
		self.location="/board/register";
		//혼자 주소를 이동해라 board/register으로 
	});
	
	var actionFrom=$("#actionForm");
	
	$('.paginate_button a').on("click",function(e){
		e.preventDefault();
		console.log('click');
		actionFrom.find("input[name='pageNum']").val($(this).attr("href"));
		actionFrom.submit();
	})
	
 	$('.move').on("click",function(e){
		e.preventDefault();
		actionFrom.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
		actionFrom.attr('action','/board/get');
		actionFrom.submit();
	})
	
	var searchForm=$("#searchForm");
	
	$("#searchForm button").on("click",(e)=>{
		if(!searchForm.find("option:selected").val()){
			alert("검색 종류를 선택하세요");
			return false;
		}
		
		if(!searchForm.find("option:keyword").val()){
			alert("키워드를 입력해주세요");
			return false;	
		}
		
		searchForm.find("input[name='pageNum']").val("1");
		e.pereventDefault();
		
		searchForm.submit();
	});
});
</script>
<!-- 모달 스크립트 펑션 끝 -->

<%@include file="../includes/footer.jsp"%>
