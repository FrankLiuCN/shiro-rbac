function ccNotice(content){
		    new jBox('Notice', {
		        content: content,
		        position: {
		            x: 'center',
		            y: 'center'
		        },
		        animation:'tada',
		        autoClose: 3000
		    });
}

function rbNotice(content){
    new jBox('Notice', {
        content: content,
        position: {
            x: 'right',
            y: 'bottom'
        },
        animation:'tada',
        autoClose: 5000
    });
}

function cConfirm(confirmCallback){	
	new jBox('Confirm', {
    	confirmButton: '确认',
    	cancelButton: '取消',
        animation:'zoomOut',
        confirm:function(){
        	if (confirmCallback) {
        		confirmCallback();
			}
        }
    
    });
}

