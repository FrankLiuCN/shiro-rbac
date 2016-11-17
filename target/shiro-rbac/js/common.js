//json日期格式转换为正常格式
function jsonDateFormat(jsonDate) {
	if (!jsonDate) {
		return "";
	}
	try {
		var date = new Date(parseInt(jsonDate));
		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1)
				: date.getMonth() + 1;
		var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
		var hours = date.getHours() < 10 ? "0" + date.getHours() : date
				.getHours();
		var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
				.getMinutes();
		var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
				.getSeconds();
		return date.getFullYear() + "-" + month + "-" + day + " " + hours + ":"
				+ minutes + ":" + seconds;
	} catch (ex) {
		return "";
	}
}

function loading(container) {
	$('<div/>', {
		'class' : 'load-overlay'
	}).appendTo($(container));
	
	var content= $('<div/>', {
		'class' : 'load-content'
	}).appendTo($(container));
	
	$('<li/>', {
		'class' : 'fa fa-spin fa-refresh'
	}).appendTo($(content));
	
	$('<span/>').text(' 加载中……').appendTo($(content));
}

function hideLoading(){
	$('.load-content').remove();
	$('.load-overlay').remove();
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
