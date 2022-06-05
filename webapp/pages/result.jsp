<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp">
	<jsp:param value="结果页面" name="title" />
</jsp:include>
<script>
	$(function() {
		$('#resultModal').modal('show');
		$('#okBtn').click(function(){
			location.replace('<%=request.getAttribute("href") %>');
		});
	});
</script>
</head>
<body>
	<!-- Modal -->
	<div class="modal fade" id="resultModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h5 class="modal-title text-warning">提示信息</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body"><%=request.getAttribute("msg") %></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="okBtn">确定</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>