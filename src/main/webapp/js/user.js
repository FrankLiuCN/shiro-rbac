$(function() {
	$(".select2").select2();
	$("#btnSave").click(function() {

		saveNewUser()
	});

	getUserList();
});

function addUser(){
	$('#userModal').modal('show')
}

function checkAddData(){
	if ($('#txtNickName').val().trim()==='') {
		return '用户名不能为空!';
	}
	if ($('#txtLoginName').val().trim()==='') {
		return '登录名不能为空!';
	}
	if ($('#txtPassword').val().trim()==='') {
		return '密码不能为空!';
	}
	return '';
}

function saveNewUser() {
	var checkResult=checkAddData();
	if (''!==checkResult) {
		ccNotice(checkResult);
		return;
	}
	var userModel = {
		nickName : $("#txtNickName").val(),
		loginName : $("#txtLoginName").val(),
		password : $("#txtPassword").val(),
		status : $("#sltStatus").val()
	};

	$.post(basePath + "/user/add", userModel, function(result) {
		if (result.code == 0) {
			$('#userModal').modal('toggle')
			rbNotice(result.data);
			getUserList();
		}else {
			ccNotice(result.msg);
		}
	});
}

function getUserList() {
	loading('.page-list');
	$('.page-list table tbody').html('');
	$.post(basePath + "/user/list", {}, function(result) {
		if (result.code == 0) {
			var data = result.data;
			$.each(data, function(i, item) {			
				var row= $('<tr/>');
				$('<td/>').text(item.nickName).appendTo(row);
				$('<td/>').text(item.loginName).appendTo(row);
				$('<td/>').text(item.statusName).appendTo(row);
				$('<td/>').text(jsonDateFormat(item.createTime)).appendTo(row);
				$('<td/>').text(jsonDateFormat(item.lastLoginTime)).appendTo(row);
				$('<td/>').appendTo(row);
				row.appendTo($('.page-list table tbody'));
			});
		} else {
			ccNotice(result.msg);
		}
		hideLoading();
	});
}
