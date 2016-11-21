var _fuzzy;
var _menuID;// 当前选择要编辑的菜单ID
$(function() {
	$('.main-header .menu').addClass('active');
	initHeadCheck();
	$("#btnSave").click(function() {
		var title = $('#menuModal .modal-title').text();
		if (title === '添加菜单') {
			saveNewMenu()
		} else {
			saveEditMenu();
		}
	});

	// 加载 用户列表
	getMenuList();
	cConfirm(deleteMenu);
});

function getAllRole() {
	$.get(basePath + '/role/all', function(result) {
		if (result.code == 0) {
			$.each(result.data, function(i, role) {
				$('<option/>', {
					value : role.roleID
				}).text(role.roleName).appendTo($('#sltRole'));
			});
		} else {
			ccNotice(result.msg);
		}
	});
}

// 删除用户
function deleteMenu() {

	var checkboxes = $('input.check');
	var menuIDs = new Array();
	$.each(checkboxes, function(index, item) {
		if (true == $(item).is(':checked')) {
			menuIDs.push($(item).attr('menuid'));
		}
	});
	if (menuIDs.length < 1) {
		ccNotice('请选择要删除的记录！');
		return;
	}
	$.post(basePath + '/menu/delete', {
		menuIDs : menuIDs
	}, function(result) {
		if (result.code == 0) {
			rbNotice(result.data);
			getMenuList();
		} else {
			ccNotice(result.msg);
		}
	});
}

function addMenu() {
	emptyModal();
	getAllRole();
	$('#menuModal .modal-title').text("添加菜单");
	$('#menuModal').modal('show');
}

// 保存之前检查数据完整性
function checkData() {
	if ($('#txtMenuName').val().trim() === '') {
		return '菜单名称不能为空!';
	}
	return '';
}

// 点击编辑按钮事件
function editMenu() {
	emptyModal();
	var checkboxes = $('input.check');
	_menuID = undefined;
	$.each(checkboxes, function(index, item) {
		if (true == $(item).is(':checked')) {
			_menuID = $(item).attr('menuid');
			return false;
		}
	});
	if (!_menuID) {
		ccNotice('请选择一条记录！');
		return;
	}
	$('#menuModal .modal-title').text("编辑菜单");
	$('#menuModal').modal('show')

	// 根据用户ID获取用户详细信息
	$.post(basePath + '/menu/' + _menuID + '/detail', function(result) {
		if (result.code == 0) {
			var menu = result.data;
			$('#txtMenuName').val(menu.menuName);
			$('#txtSort').val(menu.sort);
			$('#txtHref').val(menu.href);
			$('#txtRemark').val(menu.remark);
			$('#sltStatus').val(menu.status);
		} else {
			ccNotice(result.msg);
		}
	});
}
// 清空modal里的数据
function emptyModal() {
	$('#txtMenuName').val('');
	$('#txtSort').val('');
	$('#txtRemark').val('');
	$('#txtHref').val('');
}

// 保存新增用户信息
function saveNewMenu() {
	var checkResult = checkData();
	if ('' !== checkResult) {
		ccNotice(checkResult);
		return;
	}
	var menu = {
		menuName : $("#txtMenuName").val(),
		sort : $("#txtSort").val(),
		href : $("#txtHref").val(),
		remark : $("#txtRemark").val(),
		status : $("#sltStatus").val()
	};

	$.post(basePath + '/menu/add', menu, function(result) {
		if (result.code == 0) {
			$('#menuModal').modal('toggle')
			rbNotice(result.msg);
			getMenuList();
		} else {
			ccNotice(result.msg);
		}
	});
}

// 保存编辑用户信息
function saveEditMenu() {
	var checkResult = checkData(true);
	if ('' !== checkResult) {
		ccNotice(checkResult);
		return;
	}

	var menu = {
		menuID : _menuID,
		menuName : $("#txtMenuName").val(),
		sort : $("#txtSort").val(),
		href : $("#txtHref").val(),
		remark : $("#txtRemark").val(),
		status : $("#sltStatus").val()
	};

	$.post(basePath + '/menu/edit', menu, function(result) {
		if (result.code == 0) {
			$('#menuModal').modal('toggle')
			rbNotice(result.msg);
			getMenuList();
		} else {
			ccNotice(result.msg);
		}
	});
}

function getMenuList(pageNum) {
	// loading('.page-list');
	if (!pageNum) {
		pageNum = 1;
	}
	$('.page-list table tbody').html('');
	$.post(basePath + "/menu/list", {
		pageNum : pageNum,
		pageSize : 10,
		fuzzy : _fuzzy
	}, function(result) {
		if (result.code == 0) {
			var data = result.data.models;
			loadPageInfo(result.data);// 加载分页信息
			$.each(data, function(i, item) {
				var row = $('<tr/>');
				var first = $('<td/>').appendTo(row);
				var checkBox = $('<input/>', {
					type : 'checkbox',
					class : 'check',
					'menuid' : item.menuID
				}).appendTo(first);
				$('<td/>').text(item.menuName).appendTo(row);
				$('<td/>').text(item.href).appendTo(row);
				$('<td/>').text(item.sort).appendTo(row);
				$('<td/>').text(item.statusName).appendTo(row);
				$('<td/>').text(item.remark).appendTo(row);
				row.appendTo($('.page-list table tbody'));
			});
			initBodyCheck();
		} else {
			ccNotice(result.msg);
		}
		// hideLoading();
	});
}

function initHeadCheck() {
	var checkAll = $('input.all');
	checkAll.iCheck({
		checkboxClass : 'icheckbox_square-blue'
	});
	checkAll.on('ifChecked ifUnchecked', function(event) {
		var checkboxes = $('input.check');
		if (event.type == 'ifChecked') {
			checkboxes.iCheck('check');
		} else {
			checkboxes.iCheck('uncheck');
		}
	});
}

// 初始化复选框
function initBodyCheck() {
	var checkAll = $('input.all');
	var checkboxes = $('input.check');
	checkboxes.iCheck({
		checkboxClass : 'icheckbox_square-blue'
	});
	checkboxes.on('ifChanged', function(event) {
		if (checkboxes.filter(':checked').length == checkboxes.length) {
			checkAll.prop('checked', 'checked');
		} else {
			checkAll.removeProp('checked');
		}
		checkAll.iCheck('update');
	});
}
// 模糊查询
function search() {
	_fuzzy = $('#txtFuzzy').val();
	getMenuList();
}

function setFunction() {
	var checkboxes = $('input.check');
	_menuID = undefined;
	$.each(checkboxes, function(index, item) {
		if (true == $(item).is(':checked')) {
			_menuID = $(item).attr('menuid');
			return false;
		}
	});
	if (!_menuID) {
		ccNotice('请选择一条记录！');
		return;
	}
	$('#functionModal .modal-title').text("设置方法");
	$('#functionModal').modal('show');
	loadFunctionByMenuID();
}

function loadFunctionByMenuID() {
	$('.funtion-list table tbody').html('');
	$.get(basePath + '/function/' + _menuID + '/list', function(result) {
		if (result.code == 0) {
			var data = result.data;
			$.each(data, function(i, item) {
				var row = $('<tr/>');
				$('<td/>').text(item.functionName).appendTo(row);
				$('<td/>').text(item.permission).appendTo(row);
				var deleteTD= $('<td/>').appendTo(row);
				$('<i/>',{
					class:'glyphicon glyphicon-trash',
					click:function(){
						deleteFunction(item.functionID);
					}
				}).css({
				    cursor: 'pointer'
				}).appendTo(deleteTD);
				row.appendTo($('.funtion-list table tbody'));
			});
		} else {
			ccNotice(result.msg);
		}
	});
}

function deleteFunction(functionID){
	$.post(basePath + '/function/delete', {
		functionID:functionID
	}).done(function(result){ 
		if (result.code == 0) {
			loadFunctionByMenuID();
		} else {
			ccNotice(result.msg);
		}
	}).fail(function(xhr, status, error) {
    	ccNotice(xhr.responseText);
    });
}

// 添加方法
function saveFunction() {
	var functionName = $('#txtFunctionName').val().trim();
	var permission = $('#txtPemission').val().trim();
	if (functionName === '') {
		ccNotice('方法名称不能为空!');
		return;
	}
	if (permission === '') {
		ccNotice('权限标识不能为空!');
		return;
	}

	var func = {
		menuID : _menuID,
		functionName : functionName,
		permission : permission
	};

	$.post(basePath + '/function/add', func, function(result) {
		if (result.code == 0) {
			$('#txtFunctionName').val('');
			$('#txtPemission').val('');
			loadFunctionByMenuID();
		} else {
			ccNotice(result.msg);
		}
	});
}
