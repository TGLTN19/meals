<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<div class="modal fade" id="registerModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-info">用户注册</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="<%=request.getContextPath() %>/register.action">
                <div class="modal-body">
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">用户名</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="username" name="un" type="text"
                                   required/> <span class="text-danger" id="checkInfo"></span>
                        </div>

                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">密码</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="pw" type="password" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">电话</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="tel" type="number" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">地址</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="addr" type="text" required/>
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