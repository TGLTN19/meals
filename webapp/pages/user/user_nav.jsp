<%@ page pageEncoding="utf-8"%>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12 col-12">
			<nav
				class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
				<a class="navbar-brand"
					href="<%=request.getContextPath() %>/homepage.action"><span
					class="text-info">网络点餐系统</span></a>
				<ul class="navbar-nav mr-auto">
					<li class="nav-item <%="index".equals(request.getParameter("fun"))?"active":"" %>">
						<a class="nav-link"
						href="<%=request.getContextPath() %>/user/user_index.action">正在点餐</a>
					</li>
					<li class="nav-item <%="show".equals(request.getParameter("fun"))?"active":"" %>">
						<a class="nav-link"
						href="<%=request.getContextPath() %>/user/user_show_dc.action">我的点餐</a>
					</li>
				</ul>
				<ul class="nav justify-content-end">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
							<%=session.getAttribute("loginName") %> </a>
						<div class="dropdown-menu dropdown-menu-right">
							<a class="dropdown-item" href="#" data-toggle="modal"
                               data-target="#user_edit">修改个人资料</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item"
								href="<%=request.getContextPath() %>/user/logout.action">退出登录</a>
						</div></li>
				</ul>
			</nav>
		</div>
	</div>
</div>
<%@ include file="user_edit.jsp" %>