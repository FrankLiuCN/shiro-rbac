<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>用户管理</title>
<%@include file="common/head.jsp"%>

<link href="<%=basePath%>/libs/select2/select2.css" rel="stylesheet" />
<link href="<%=basePath%>/css/user.css" rel="stylesheet" />
<script src="<%=basePath%>/libs/select2/select2.full.min.js"></script>
<script src="<%=basePath%>/js/user.js"></script>
</head>
<body class="hold-transition skin-blue layout-top-nav">
	<div class="wrapper">
		<%@include file="common/nav.jsp"%>
		<div class="content-wrapper">
			<div class="container">
				<div class="page-top">
					<div class="tool">
						<button class="btn btn-primary" onclick="addUser()">
							<i class="glyphicon glyphicon-plus"></i> 添加
						</button>
						<button class="btn btn-primary" onclick="editUser()">
							<i class="glyphicon glyphicon-edit"></i> 编辑
						</button>
						<button class="btn btn-danger">
							<i class="glyphicon glyphicon-trash"></i> 删除
						</button>
					</div>
					<div class="search">
						<div class="input-group">
							<input class="form-control" type="text" placeholder="用户名/登录名">
							<span class="input-group-btn">
								<button class="btn btn-primary" type="button">
									<i class="glyphicon glyphicon-search"></i> 查询
								</button>
							</span>
						</div>
					</div>
				</div>
				<div class="page-list box">
					<table class="table table-striped table-bordered table-hover text-center">
						<thead>
							<tr>
								<th>用户名</th>
								<th>登录名</th>
								<th>状态</th>
								<th>创建时间</th>
								<th>最后登录时间</th>
								<th>角色</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-sm-4 list-info">
						显示第1到第20条记录，共205条记录
					</div>
					<div class="col-sm-8">
					 <ul class="pagination">
					    <li class="disabled"><a href="#">上一页</a></li>
					    <li class="active"><a href="#">1</a></li>
					    <li><a href="#">2</a></li>
					    <li><a href="#">3</a></li>
					    <li><a href="#">4</a></li>
					    <li><a href="#">5</a></li>
					    <li><a href="#">下一页</a></li>
					  </ul>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" tabindex="-1" role="dialog" id="userModal">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">添加用户</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for=""txtNickName"" class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="txtNickName"
										placeholder="用户">
								</div>
								<label for="txtLoginName" class="col-sm-2 control-label">登录名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="txtLoginName"
										placeholder="登录名">
								</div>
							</div>
							<div class="form-group">
								<label for="txtPassword" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-4">
									<input type="password" class="form-control" id="txtPassword"
										placeholder="密码">
								</div>
								<label for="sltStatus" class="col-sm-2 control-label">状态</label>
								<div class="col-sm-4">
									<select class="form-control" id="sltStatus">
										<option value="1">启用</option>
										<option value="0">禁用</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">角色</label>
								<div class="col-sm-10">
				                <select class="form-control select2" multiple="multiple" data-placeholder="选择一个角色" style="width: 100%;">
				                  <option>Alabama</option>
				                  <option>Alaska</option>
				                </select>
				                </div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="btnSave">保存</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		<%@include file="common/footer.jsp"%>
	</div>
</body>
</body>
</html>

