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
