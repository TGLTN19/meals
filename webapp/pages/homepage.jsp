<%@ page pageEncoding="utf-8" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="head.jsp">
        <jsp:param value="首页" name="title"/>
    </jsp:include>
</head>
<body>
<%
    String ident = (String) session.getAttribute("ident");
    if (ident == null) {
%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12 col-12">
            <nav
                    class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
                <a class="navbar-brand" href="homepage.action"><span
                        class="text-info">网络点餐系统</span></a>
                <ul class="nav justify-content-end">
                    <li class="nav-item"><a class="nav-link" href="#"
                                            data-toggle="modal" data-target="#loginModal">登录</a></li>
                    <li class="nav-item"><a class="nav-link" href="#"
                                            data-toggle="modal" data-target="#registerModal">注册</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<%
} else if (ident.equals("1")) {
%>
<%--<jsp:include page="/pages/admin/admin_nav.jsp"/>--%>
<%--管理员--%>
<%
} else if (ident.equals("0")) {
%>
<jsp:include page="/pages/user/user_nav.jsp"/>
<%--用户--%>
<%
    }
%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 col-12">
            <h4 class="list-group-item active">热点菜品</h4>
            <%
                List<Map<String, String>> hot = (List<Map<String, String>>) request.getAttribute("hot");
                if (hot != null) {
            %>
            <ul class="list-group">
                <%
                    for (Map<String, String> hm : hot) {
                %>
                <li class="list-group-item"><img class="img-rounded"
                                                 src="<%=request.getContextPath() %>/<%=hm.get("picture") %>"/>
                    <a
                            href="<%=request.getContextPath() %>/show_detail.action?id=<%=hm.get("id") %>"
                            target="_blank"><%=hm.get("foodname")%>
                    </a> &nbsp;&nbsp; <span><%=hm.get("price")%>元</span></li>
                <%
                    }
                %>
            </ul>
            <%
                }
            %>
        </div>
        <div class="col-md-4 col-12">
            <h4 class="list-group-item active">今日特价</h4>
            <%
                List<Map<String, String>> special = (List<Map<String, String>>) request.getAttribute("special");
                if (hot != null) {
            %>
            <ul class="list-group">
                <%
                    for (Map<String, String> hm : special) {
                %>
                <li class="list-group-item"><img class="img-rounded"
                                                 src="<%=request.getContextPath() %>/<%=hm.get("picture") %>"/>
                    <a
                            href="<%=request.getContextPath() %>/show_detail.action?id=<%=hm.get("id") %>"
                            target="_blank"><%=hm.get("foodname")%>
                    </a> &nbsp;&nbsp; <span><%=hm.get("comment")%>元</span></li>
                <%
                    }
                %>
            </ul>
            <%
                }
            %>
        </div>
        <div class="col-md-4 col-12">
            <h4 class="list-group-item active">厨师推荐</h4>
            <%
                List<Map<String, String>> recomm = (List<Map<String, String>>) request.getAttribute("recomm");
                if (hot != null) {
            %>
            <ul class="list-group">
                <%
                    for (Map<String, String> hm : recomm) {
                %>
                <li class="list-group-item"><img class="img-rounded"
                                                 src="<%=request.getContextPath() %>/<%=hm.get("picture") %>"/>
                    <a
                            href="<%=request.getContextPath() %>/show_detail.action?id=<%=hm.get("id") %>"
                            target="_blank"><%=hm.get("foodname")%>
                    </a> &nbsp;&nbsp; <span><%=hm.get("price")%>元</span></li>
                <%
                    }
                %>
            </ul>
            <%
                }
            %>
        </div>
    </div>
</div>
<%@ include file="login.jsp" %>
<%@ include file="register.jsp" %>
</body>
</html>