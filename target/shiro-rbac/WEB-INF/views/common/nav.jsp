<%@page language="java" pageEncoding="utf-8"%>
<header class="main-header">
	<nav class="navbar navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<a href="<%=basePath%>/main" class="navbar-brand"><b>权限管理系统</b></a>
			</div>
			<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="<%=basePath%>/user">用戶管理</a></li>
					<li><a href="<%=basePath%>/role">角色管理</a></li>
					<li><a href="<%=basePath%>/menu">菜单管理</a></li>
				</ul>
			</div>
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<shiro:user>  
						<li><a href="#"> <i class="glyphicon glyphicon-user"></i> <shiro:principal property="nickName" /></a></li>
						<li><a href="<%=basePath%>/logout"><i class="glyphicon glyphicon-off"></i> 注销</a></li>
					</shiro:user>
				</ul>
			</div>
		</div>
	</nav>
</header>