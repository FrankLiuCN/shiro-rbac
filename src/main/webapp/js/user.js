var _fuzzy;
var _userID;// 当前选择要编辑的用户ID
var _sltRole;
$(function() {
	$('.main-header .user').addClass('active');
	initHeadCheck();
	_sltRole = $(".select2").select2();
	$("#btnSave").click(function() {
		var title = $('#userModal .modal-title').text();
		if (title === '添加用户') {
			saveNewUser()
		} else {
			saveEditUser();
		}
	});

	// 加载 用户列表
	getUserList();
	cConfirm(deleteUser);
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
function deleteUser() {

	var checkboxes = $('input.check');
	var userIDs = new Array();
	$.each(checkboxes, function(index, item) {
		if (true == $(item).is(':checked')) {
			userIDs.push($(item).attr('userid'));
		}
	});
	if (userIDs.length < 1) {
		ccNotice('请选择要删除的记录！');
		return;
	}
	$.post(basePath + '/user/delete', {
		userIDs : userIDs
	}, function(result) {
		if (result.code == 0) {
			rbNotice(result.data);
			getUserList();
		} else {
			ccNotice(result.msg);
		}
	});
}

function addUser() {
	emptyModal();
	getAllRole();
	$('#userModal .modal-title').text("添加用户");
	$('#userModal').modal('show')
}

// 保存之前检查数据完整性
function checkData(isEdit) {
	if ($('#txtNickName').val().trim() === '') {
		return '用户名不能为空!';
	}
	if ($('#txtLoginName').val().trim() === '') {
		return '登录名不能为空!';
	}
	if ($('#txtPassword').val().trim() === '' && !isEdit) {
		return '密码不能为空!';
	}
	var roleList = _sltRole.val();
	if (roleList.length < 1) {
		return '请选择角色!';
	}

	return '';
}

// 点击编辑按钮事件
function editUser() {
	emptyModal();
	getAllRole();
	var checkboxes = $('input.check');
	_userID = undefined;
	$.each(checkboxes, function(index, item) {
		if (true == $(item).is(':checked')) {
			_userID = $(item).attr('userid');
			return false;
		}
	});
	if (!_userID) {
		ccNotice('请选择一条记录！');
		return;
	}
	$('#userModal .modal-title').text("编辑用户");
	$('#userModal').modal('show')

	// 根据用户ID获取用户详细信息
	$.post(basePath + '/user/' + _userID + '/detail', function(result) {
		if (result.code == 0) {
			var userModel = result.data;
			$('#txtNickName').val(userModel.nickName);
			$('#txtLoginName').val(userModel.loginName);
			$('#sltStatus').val(userModel.status);
			if (userModel.roles.length>0) {
				var roleArray=[];
				$.each(userModel.roles,function(i,role){
					roleArray.push(role.roleID);
				});
				_sltRole.val(roleArray).trigger("change");
			}

		} else {
			ccNotice(result.msg);
		}
	});
}
// 清空modal里的数据
function emptyModal() {
	$('#txtNickName').val('');
	$('#txtLoginName').val('');
	$('#txtPassword').val('');
	$('#sltRole').html('');
}

// 保存新增用户信息
function saveNewUser() {
	var checkResult = checkData();
	if ('' !== checkResult) {
		ccNotice(checkResult);
		return;
	}

	var roleList = _sltRole.val();
	var roles = new Array();
	$.each(roleList, function(i, roleID) {
		var role = {
			roleID : roleID
		};
		roles.push(role);
	});
	
	var userModel = {
		nickName : $("#txtNickName").val(),
		loginName : $("#txtLoginName").val(),
		password : $("#txtPassword").val(),
		status : $("#sltStatus").val(),
		roles : roles
	};
	
	$.ajax({
		url : basePath + "/user/add",
		type : "POST",
		data : JSON.stringify(userModel),
		success : function(result) {
			if (result.code == 0) {
				$('#userModal').modal('toggle')
				rbNotice(result.msg);
				getUserList();
			} else {
				ccNotice(result.msg);
			}
		},
		dataType : "json",
		contentType : "application/json"
	});
}

// 保存编辑用户信息
function saveEditUser() {
	var checkResult = checkData(true);
	if ('' !== checkResult) {
		ccNotice(checkResult);
		return;
	}

	var roleList = _sltRole.val();
	var roles = new Array();
	$.each(roleList, function(i, roleID) {
		var role = {
			roleID : roleID
		};
		roles.push(role);
	});
	var user = {
		userID : _userID,
		nickName : $("#txtNickName").val(),
		loginName : $("#txtLoginName").val(),
		password : $("#txtPassword").val(),
		status : $("#sltStatus").val(),
		roles : roles
	};

	$.ajax({
		url : basePath + "/user/edit",
		type : "POST",
		data : JSON.stringify(user),
		success : function(result) {
			if (result.code == 0) {
				$('#userModal').modal('toggle')
				rbNotice(result.msg);
				getUserList();
			} else {
				ccNotice(result.msg);
			}
		},
		dataType : "json",
		contentType : "application/json"
	});
}

function getUserList(pageNum) {
	// loading('.page-list');
	if (!pageNum) {
		pageNum = 1;
	}
	$('.page-list table tbody').html('');
	$.post(basePath + "/user/list", {
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
					'userid' : item.userID
				}).appendTo(first);
				$('<td/>').text(item.nickName).appendTo(row);
				$('<td/>').text(item.loginName).appendTo(row);
				$('<td/>').text(item.statusName).appendTo(row);
				$('<td/>').text(jsonDateFormat(item.createTime)).appendTo(row);
				$('<td/>').text(jsonDateFormat(item.lastLoginTime)).appendTo(
						row);
				var roles='';
				$.each(item.roles,function(j,role){
					roles+=role.roleName+' ';
				});
				$('<td/>').text(roles).appendTo(row);
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
	getUserList();
}
