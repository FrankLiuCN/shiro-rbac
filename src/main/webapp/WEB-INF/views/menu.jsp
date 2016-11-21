<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>菜单管理</title>
<%@include file="common/head.jsp"%>

<link href="<%=basePath%>/libs/select2/select2.css" rel="stylesheet" />
<link href="<%=basePath%>/css/user.css" rel="stylesheet" />
<script src="<%=basePath%>/libs/select2/select2.full.min.js"></script>
<script src="<%=basePath%>/js/menu.js"></script>
</head>
<body class="hold-transition skin-blue layout-top-nav">
	<div class="wrapper">
		<%@include file="common/nav.jsp"%>
		<div class="content-wrapper">
			<div class="container">
				<div class="page-top">
					<div class="tool">
						<button class="btn btn-primary" onclick="addMenu()">
							<i class="glyphicon glyphicon-plus"></i> 添加
						</button>
						<button class="btn btn-primary" onclick="editMenu()">
							<i class="glyphicon glyphicon-edit"></i> 编辑
						</button>
						<button class="btn btn-primary" onclick="setFunction()">
							<i class="glyphicon glyphicon-edit"></i> 设置方法
						</button>
						<button class="btn btn-danger" data-confirm="确认要删除所选的菜单吗?">
							<i class="glyphicon glyphicon-trash"></i> 删除
						</button>
					</div>
					<div class="search">
						<div class="input-group">
							<input class="form-control" type="text" id="txtFuzzy"
								placeholder="菜单名称"> <span class="input-group-btn">
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
								<th>菜单名称</th>
								<th style="width: 400px;">链接</th>
								<th style="width: 100px;">排序</th>
								<th style="width: 100px;">是否启用</th>
								<th style="width: 250px;">备注</th>
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
		<div class="modal fade" tabindex="-1" role="dialog" id="menuModal">
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
								<label for="txtMenuName" class="col-sm-2 control-label">菜单名称</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="txtMenuName"
										placeholder="菜单名称">
								</div>
								<label for="txtLoginName" class="col-sm-2 control-label">排序</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="txtSort"
										placeholder="排序">
								</div>
							</div>
							<div class="form-group">
								<label for="sltStatus" class="col-sm-2 control-label">状态</label>
								<div class="col-sm-4">
									<select class="form-control" id="sltStatus">
										<option value="1">启用</option>
										<option value="0">禁用</option>
									</select>
								</div>
								<label for="txtPassword" class="col-sm-2 control-label">备注</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="txtRemark"
										placeholder="备注">
								</div>
							</div>
							<div class="form-group">
								<label for="txtHref" class="col-sm-2 control-label">链接</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="txtHref"
										placeholder="链接">
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

		<div class="modal fade" tabindex="-1" role="dialog" id="functionModal">
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
						<div class="form-horizontal">
							<div class="form-group">
								<label for="txtFunctionName" class="col-sm-2 control-label">方法名称</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="txtFunctionName"
										placeholder="方法名称">
								</div>
								<label for="" txtPemission"" class="col-sm-2 control-label">权限标识</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="txtPemission"
										placeholder="权限标识">
								</div>
								<div class="col-sm-2">
									<button class="btn btn-primary" onclick="saveFunction()">添加
									</button>
								</div>
							</div>
						</div>
						<div style="min-height: 200px;" class="funtion-list box">
							<table
								class="table table-striped table-bordered table-hover text-center">
								<thead>
									<tr>
										<th style="width:45%">方法名称</th>
										<th style="width:45%">权限标识</th>
										<th style="width:10%">删除</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
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

