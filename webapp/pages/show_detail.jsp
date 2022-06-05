<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp">
<jsp:param value="菜品详细信息" name="title"/>
</jsp:include>
<style>
table.table tr th{
vertical-align:middle;
text-align:center;
width:30%
}
</style>
<meta charset="UTF-8">
</head>
<body>
<body>
	<p></p>
	<%
		Map<String, String> food = (Map<String, String>) request.getAttribute("food");
		if (food != null) {
	%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3 col-0"></div>
			<div class="col-md-6 col-12">
				<table class="table table-hover table-bordered">
					<tbody>
						<tr class="table-primary">
							<th scope="col">菜 名</th>
							<td><%=food.get("foodname")%></td>
						</tr>
						<tr class="table-secondary">
							<th scope="row">特 色</th>
							<td><%=food.get("feature")%></td>
						</tr>
						<tr class="table-success">
							<th scope="row">食 材</th>
							<td><%=food.get("material")%></td>
						</tr>
						<tr class="table-danger">
							<th scope="row">类 型</th>
							<td><%=food.get("typename")%></td>
						</tr>
						<tr class="table-warning">
							<th scope="row">价 格</th>
							<td><%=food.get("price")%>元</td>
						</tr>
						<tr class="table-info">
							<th scope="row">图 片</th>
							<td><img class="img-rounded" alt="Bootstrap Image Preview"
								src="<%=food.get("picture")%>" /></td>
						</tr>
						<tr class="table-danger">
							<th scope="row">点 餐 率</th>
							<td><%=food.get("hits")%>次</td>
						</tr>
						<tr class="table-success">
							<th scope="row">备 注</th>
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
						</tr>
					</tbody>
				</table>
				<form method="post"
					action="<%=request.getContextPath() %>/user/user_add_dc.action">
					<input type="hidden" name="ids" value="${food.id }">
					<div class="form-group">
						<button type="submit" class="btn btn-danger btn-block">将
							菜 品 添 加 到 点 餐 车</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%
		}
	%>
</body>
</body>
</html>