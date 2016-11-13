var _templateRow;

$(function(){
	$(".select2").select2();
	$("#btnSave").click(function(){		
		addUser()
	});
	
	getUserList();
});

function addUser(){
	
	var userModel={
			nickName:$("#txtNickName").val(),
			loginName:$("#txtLoginName").val(),
			password:$("#txtPassword").val(),
			status:$("#sltStatus").val()
	};
	
	$.post(basePath + "/user/add",{userModel:userModel},function(result){
		
		
	});
}

function getUserList(){	
	$.post(basePath + "/user/list", {}, function(result) {
		if (result.code == 0) {
			var data=result.data;
			_templateRow=$('.page-list table tbody tr').clone();
			$('.page-list table tbody tr').remove();
			$.each(data,function(i,item){
				_templateRow.find('td').eq(0).text(item.nickName);
				_templateRow.find('td').eq(1).text(item.loginName);
				_templateRow.find('td').eq(2).text(item.statusName);
				_templateRow.find('td').eq(3).text(jsonDateFormat(item.createTime));
				_templateRow.find('td').eq(4).text(jsonDateFormat(item.lastLoginTime));
				_templateRow.appendTo($('.page-list table tbody'));
			});
		} else {
			ccNotice(result.msg);
		}
	});
}

