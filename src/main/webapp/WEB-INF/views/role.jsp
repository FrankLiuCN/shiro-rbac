<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<%@include file="common/head.jsp"%>

<script src="<%=basePath%>/js/role.js"></script>

</head>
<body class="hold-transition skin-blue layout-top-nav">
	<div class="wrapper">
		<%@include file="common/nav.jsp"%>
		<div class="content-wrapper">
			<div class="container">
				<div class="page-top">
					<div class="tool">
						<button class="btn btn-primary" onclick="addRole()">
							<i class="glyphicon glyphicon-plus"></i> 添加
						</button>
						<button class="btn btn-primary" onclick="editRole()">
							<i class="glyphicon glyphicon-edit"></i> 编辑
						</button>
						<button class="btn btn-danger" data-confirm="确认要删除所选的用户吗?">
							<i class="glyphicon glyphicon-trash"></i> 删除
						</button>
					</div>
					<div class="search">
						<div class="input-group">
							<input class="form-control" type="text" id="txtFuzzy"
								placeholder="角色名称"> <span class="input-group-btn">
								<button class="btn btn-primary" type="button"
									onclick="search();">
									<i class="glyphicon glyphicon-search"></i> 查询
								</button>
							</span>
						</div>
					</div>
				</div>
				<div class="page-list box">
					<table
						class="table table-striped table-bordered table-hover text-center">
						<thead>
							<tr>
								<th style="width: 40px;"><input type="checkbox" class="all" /></th>
								<th style="width: 80px;">编号</th>
								<th>角色名称</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-sm-4 list-info"></div>
					<div class="col-sm-8">
						<ul class="pagination">
						</ul>
					</div>
				</div>
				
			</div>
		</div>
		<div class="modal fade" tabindex="-1" role="dialog" id="roleModal">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for=""txtRoleName"" class="col-sm-3 control-label">角色名称</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" id="txtRoleName"
										placeholder="角色名称">
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
</html>