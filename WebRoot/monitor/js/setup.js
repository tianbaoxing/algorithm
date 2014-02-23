+(function($){
	// the alarm info
	var info = ["您还有参数没有设置哦","端口的范围需要在1024~65535之间哦"];
	console.log($("form"));
	$("form").on("submit",function(event){
		switch(fncheckInput($(this))){
			case 0:
				return true;
				break;
			case "LackOfPara":
				fnAlarm(info[0], "Alarm");
				return false;
				break;
			case "typeError":
				fnAlarm(info[1], "Alarm");
				return false;
				break;
			default:
				alert("出问题了，请联系苦逼的程序员");
				break;
		}
	})

	function fncheckInput($form){
		var flag   = 0;
		var inputs = $form.find("input");

		for (var i = inputs.length - 1; i >= 0; i--) {
			if($(inputs[i]).val().length === 0){
				flag = "LackOfPara";
				break;					
			}
			if($(inputs[i]).hasClass("port")){
				if( isNaN($(inputs[i]).val()) || $(inputs[i]).val() > 65536 || $(inputs[i]).val() < 1024){
					flag = "typeError";
					break;
				}				
			}

		};
		return flag;	
	}


	function fnAlarm(text,elementid){
		$('#'+elementid).text(text).stop().fadeIn(200);
		setTimeout(function(){
			$('#'+elementid).fadeOut(200);
		},2000);
	}
})(window.jQuery)