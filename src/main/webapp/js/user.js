$(function() {
	$(".select2").select2();
	$("#btnSave").click(function() {

		saveNewUser()
	});

	getUserList(1);
});

function addUser() {
	$('#userModal').modal('show')
}

function checkAddData() {
	if ($('#txtNickName').val().trim() === '') {
		return '用户名不能为空!';
	}
	if ($('#txtLoginName').val().trim() === '') {
		return '登录名不能为空!';
	}
	if ($('#txtPassword').val().trim() === '') {
		return '密码不能为空!';
	}
	return '';
}

function saveNewUser() {
	var checkResult = checkAddData();
	if ('' !== checkResult) {
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
			getUserList(1);
		} else {
			ccNotice(result.msg);
		}
	});
}

function getUserList(pageNum) {
	loading('.page-list');
	$('.page-list table tbody').html('');
	$.post(basePath + "/user/list", {pageNum:pageNum,pageSize:10}, function(result) {
		if (result.code == 0) {
			var data = result.data.models;
			loadPageInfo(result.data);
			$.each(data, function(i, item) {
				var row = $('<tr/>');
				$('<td/>').text(item.nickName).appendTo(row);
				$('<td/>').text(item.loginName).appendTo(row);
				$('<td/>').text(item.statusName).appendTo(row);
				$('<td/>').text(jsonDateFormat(item.createTime)).appendTo(row);
				$('<td/>').text(jsonDateFormat(item.lastLoginTime)).appendTo(
						row);
				$('<td/>').appendTo(row);
				row.appendTo($('.page-list table tbody'));
			});
		} else {
			ccNotice(result.msg);
		}
		hideLoading();
	});
}

function loadPageInfo(pageInfo) {
	$('.list-info').text('显示第' + pageInfo.startRow + '到第' + pageInfo.endRow + '条记录，共'
					+ pageInfo.total + '条记录');
	var pagination=$('.pagination');
	pagination.html('');
	var li;	
	if (pageInfo.prePage===0) {
		li = $('<li/>',{
			'class':'disabled'
		});
	}else{
		li= $('<li/>');
		li.click(function(){
			getUserList(pageInfo.prePage);
		});
	}
	$('<a/>').text('上一页').appendTo(li);
	li.appendTo(pagination);
	$.each(pageInfo.navigatepageNums,function(i,num){
		if (num===pageInfo.pageNum) {
			li = $('<li/>',{
				'class':'active'
			});
		}else{
			li= $('<li/>');
			li.click(function(){
				getUserList(num);
			});
		}
		$('<a/>').text(num).appendTo(li);
		li.appendTo(pagination);
	});
	
	if (pageInfo.pageNum===pageInfo.lastPage) {
		li = $('<li/>',{
			'class':'disabled'
		});
	}else{
		li= $('<li/>');
		li.click(function(){
			getUserList(pageInfo.nextPage);
		});
	}
	$('<a/>').text('下一页').appendTo(li);
	li.appendTo(pagination);
}
