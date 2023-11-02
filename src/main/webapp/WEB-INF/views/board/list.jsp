<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board List Page
				<button id="regBtn" type="button" class="btn btn-xs pull-right">Register
					New Board</button>
			</div>

            <!-- /.panel-heading -->
            <div class="panel-body">

                <div class="row">

                </div>
                <table width="100%" class="table table-striped table-bordered table-hover"
                    id="dataTables-example">
                    <thead>
                        <tr>
                            <th>#번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>수정일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty list}">
                                <tr class="odd gradeX">
                                    <td colspan="5">[없음]
                                </tr>
                            </c:when>

                            <c:when test="${!empty list}">
                                <c:forEach items="${list}" var="board">
                                    <tr class="odd gradeX">
                                        <td><c:out value="${board.bno}" /></td>
                                        <td><a class="" href="/board/get?bno=<c:out value="${board.bno}" />">
                                            <c:out value="${board.title}" />
                                        </a></td>
                                        <td><c:out value="${board.writer}" /></td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd"
                                                value="${board.regdate}" /></td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd"
                                                value="${board.updatedate}" /></td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </tbody>
                </table>
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                        aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                </div>
                                <div class="modal-body">처리가 완료되었습니다.</div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default"
                                        data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Save
                                        changes</button>
                                </div>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                        <!-- /.modal-dialog -->
                    </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
						var result = '<c:out value="${result}"/>';

						checkModal(result);

						history.replaceState({}, null, null);

						function checkModal(result) {

							if (result === '' || history.state) {
								return;
							}

							if (parseInt(result) > 0) {
								$(".modal-body").html(
										"게시글 " + parseInt(result)
												+ " 번이 등록되었습니다.");

							}

							$("#myModal").modal("show");
						}

						$('#regBtn').on('click', function() {
							self.location = "/board/register";
						});

						var actionForm = $("#actionForm");
						$(".paginate_button a").on("click",
								function(e) {
									e.preventDefault();
									actionForm.find("input[name='pageNum']")
											.val($(this).attr("href"));
									actionForm.submit();
								});




										$(".move").on("click", function(e) {
											e.preventDefault();
											actionForm
													.append("<input type='hidden' name='bno' value='"
															+ $(this).attr(
																	"href")
															+ "'/>");
											actionForm.attr("action",
													"/board/get");
											actionForm.submit();
										});

						var searchForm = $('#searchForm');
						$('#searchForm button').on("click", function(e){
							if(!searchForm.find("option:selected").val()){
								alert("검색종류를 선택하세요.");
								return false;
							}
							if(!searchForm.find("input[name='keyword']").val()){
								alert("키워드를 입력하세요.");
								return false;
							}
							searchForm
							.find("input[name='pageNum']").val("1");
							e.preventDefault();
							searchForm.submit();
						});


					});
</script>

<%@include file="../includes/footer.jsp"%>