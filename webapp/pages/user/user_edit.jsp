<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" import="model.UserService"%>
<!-- Modal -->
<%
	
     String id = (String) session.getAttribute("loginID");//获取登录用户的id号
	//System.out.print(id);
    if(id !=null) {
        //表示用户已登录
        // 根据id获取该用户信息
       System.out.print(id);
        Map<String,String> u = new UserService().getUserById(id);

%>
<div class="modal fade" id="user_edit" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-info">信息修改</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="<%=request.getContextPath() %>/user_edit.action">
            <input type="hidden" name="id" value="<%=u.get("id")%>"><br>
                <div class="modal-body">
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">用户名</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="username" value="<%=u.get("username") %>" name="un" type="text"
                                   required/> <span class="text-danger" id="checkInfo"></span>
                        </div>

                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">密码</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="pw" value="<%=u.get("password") %>" type="password" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">电话</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="tel" value="<%=u.get("telephone") %>" type="text" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">地址</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="addr" value="<%=u.get("address")%>" type="text" required/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="submit" class="btn btn-primary">确定</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%}%>