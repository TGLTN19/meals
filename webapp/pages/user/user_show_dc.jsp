<%@ page pageEncoding="utf-8" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/pages/head.jsp">
	<jsp:param value="用户点餐车" name="title" />
</jsp:include>
</head>
<body>
	<jsp:include page="/pages/user/user_nav.jsp">
		<jsp:param value="show" name="fun" />
	</jsp:include>
	<%
		List<Map<String, String>> dc = (List<Map<String, String>>) request.getAttribute("dc");
		if (dc != null) {
	%>
		<div class="container-fluid">
			<div class="card border">
				<div class="card-body border">
					<form
						action="<%=request.getContextPath()%>/user/user_del_dc.action"
						method="post">
						<div class="table-responsive-xl">
							<table class="table table-striped table-hover table-sm">
								<thead>
									<tr>
										<th>#</th>
										<th>菜名</th>
										<th>特色</th>
										<th>主料</th>
										<th>价格</th>
										<th>分类</th>
										<th>图片</th>
										<th>点餐率</th>
										<th>备注</th>
										<th>选择</th>
									</tr>
								</thead>
								<tbody>
									<%
									int num = 0;
										for (Map<String, String> food : dc) {
											num++;
								%>
								<tr>
									<th scope="row"><%=num%></th>
									<td><%=food.get("foodname")%></td>
									<td><%=food.get("feature")%></td>
									<td><%=food.get("material")%></td>
									<td><%=food.get("price")%></td>
									<td><%=food.get("typename")%></td>
									<td><img class="img-rounded"
										src="<%=request.getContextPath()%>/<%=food.get("picture")%>" />
									</td>
									<td><%=food.get("hits")%></td>
									<td>
									<%
										if (food.get("comment").equals("0")) {
											out.println("厨师推荐");
										} else if (food.get("comment").equals("-1")) {
											out.println("&nbsp;");
										} else {
											out.println("特价" + food.get("comment") + "元");
										}
									%>
									</td>
									<td><input type="checkbox" name="ids"
										value="<%=food.get("dcid")%>"></td>
								</tr>
								<%
									}
								%>
								</tbody>
							</table>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-danger btn-block">将
								菜 品 从 点 餐 车 删 除</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	<%
		}
	%>
</body>
</html>