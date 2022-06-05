<%@page pageEncoding="utf-8" %>
<!-- Modal -->
<div class="modal" id="loginModal" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-info">用户登录</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="<%=request.getContextPath() %>/login.action">
                <div class="modal-body">
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">用户名</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="un" type="text" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">密码</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="pw" type="password" required/>
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