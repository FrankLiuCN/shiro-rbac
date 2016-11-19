var _fuzzy;
var _role;// 当前选择要编辑的用户ID
$(function() {
	$('.main-header .role').addClass('active');
	initHeadCheck();

	$("#btnSave").click(function() {
		var title = $('#roleModal .modal-title').text();
		if (title === '添加角色') {
			saveNewRole();
		} else {
			saveEditRole();
		}
	});

	// 加载 用户列表
	getRoleList();

	cConfirm(deleteRole);
});

function deleteRole(){
	var checkboxes = $('input.check');
	var roleIDs=new Array();
	$.each(checkboxes, function(index, item) {
		if (true == $(item).is(':checked')) {
			roleIDs.push($(item).attr('roleid'));
		}
	});
	if (roleIDs.length<1) {
		ccNotice('请选择要删除的记录！');
		return;
	}
	$.post(basePath + '/role/delete',{roleIDs:roleIDs}, function(result) {
		if (result.code == 0) {
			rbNotice(result.msg);
			getRoleList();
		} else {
			ccNotice(result.msg);
		}
	});	
}

function getRoleList(pageNum) {
	if (!pageNum) {
		pageNum = 1;
	}
	$('.page-list table tbody').html('');
	$.post(basePath + "/role/list", {
		pageNum : pageNum,
		pageSize : 10,
		fuzzy : _fuzzy
	}, function(result) {
		if (result.code == 0) {
			var models = result.data.models;
			loadPageInfo(result.data);// 加载分页信息
			$.each(models, function(i, item) {
				var row = $('<tr/>');
				var first = $('<td/>').appendTo(row);
				var checkBox = $('<input/>', {
					type : 'checkbox',
					class : 'check',
					'roleid' : item.roleID
				}).appendTo(first);
				$('<td/>').text(item.roleID).appendTo(row);
				$('<td/>').text(item.roleName).appendTo(row);
				row.appendTo($('.page-list table tbody'));
			});
			initBodyCheck();
		} else {
			ccNotice(result.msg);
		}
	});

}

function addRole() {
	$('#txtRoleName').val('');
	$('#roleModal .modal-title').text("添加角色");
	$('#roleModal').modal('show')
}

function editRole() {

	var checkboxes = $('input.check');
	_role = undefined;
	$.each(checkboxes, function(index, item) {
		if (true == $(item).is(':checked')) {
			_role = {
				roleID : $(item).attr('roleid'),
				roleName : $(item).parent().parent().next().next().text()
			};
			return false;
		}
	});
	if (!_role) {
		ccNotice('请选择一条记录！');
		return;
	}

	$('#roleModal .modal-title').text("编辑角色");
	$('#roleModal').modal('show')
	$('#txtRoleName').val(_role.roleName);
}

function saveNewRole() {
	var roleName = $('#txtRoleName').val().trim();
	if (roleName === "") {
		ccNotice('角色名称不能为空!');
		return;
	}
	$.post(basePath + "/role/add", {
		roleName : roleName
	}, function(result) {
		if (result.code === 0) {
			$('#roleModal').modal('toggle');
			getRoleList();
		} else {
			ccNotice(result.msg);
		}
	});
}

function saveEditRole() {
	var roleName = $('#txtRoleName').val().trim();
	if (roleName === "") {
		ccNotice('角色名称不能为空!');
		return;
	}
	_role.roleName = roleName;
	$.post(basePath + "/role/edit", _role, function(result) {
		if (result.code === 0) {
			$('#roleModal').modal('toggle');
			getRoleList();
		} else {
			ccNotice(result.msg);
		}
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
	getRoleList();
}