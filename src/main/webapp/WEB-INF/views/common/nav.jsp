<%@page language="java" pageEncoding="utf-8"%>
<header class="main-header">
	<nav class="navbar navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<a href="<%=basePath%>/main" class="navbar-brand"><b>权限管理系统</b></a>
			</div>
			<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
				<ul class="nav navbar-nav">
					<shiro:hasPermission name="user:view">
						<li class="user"><a href="<%=basePath%>/user">用戶管理</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="role:view">
						<li class="role"><a href="<%=basePath%>/role">角色管理</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="menu:view">
						<li class="menu"><a href="<%=basePath%>/menu">菜单管理</a></li>
					</shiro:hasPermission>			
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