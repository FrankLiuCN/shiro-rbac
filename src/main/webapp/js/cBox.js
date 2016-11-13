function ccNotice(content){
		    new jBox('Notice', {
		        content: content,
		        position: {
		            x: 'center',
		            y: 'center'
		        },
		        animation:'tada',
		        autoClose: 2000
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

