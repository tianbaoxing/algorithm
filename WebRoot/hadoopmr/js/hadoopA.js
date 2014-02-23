+(function($){
	var info = ["您还有路径没有填写哦","写入信息还没有填写哦"];

	$("form").on("submit",function(){
		var flag = checkForm($(this));
		switch(flag){
			case "Pass":
				break;
			case "LackOfPara-text":
				fnAlarm(info[0], "Alarm");
				return false;
				break;
			case "LackOfPara-area":
				fnAlarm(info[1], "Alarm");
				return false;
				break;
			default:
				alert("程序除了意外，请联系程序员同志!");
				break;
		}
	})


	// the validate function
	function checkForm(form){
		var inputs = form.find("input[type='text']");
		var textareas = form.find("textarea");
		if(inputs.length !== 0 ){
			for (var i = inputs.length - 1; i >= 0; i--) {
				if(inputs[i].value.length === 0){
					return "LackOfPara-text";
				}
			};
		}
		if(textareas.length !== 0 ){
			for (var i = textareas.length - 1; i >= 0; i--) {
				if(textareas[i].value.length === 0){
					return "LackOfPara-area";
				}	
			};	
		}
		return "Pass";
	}



	function fnAlarm(text,elementid){
		$('#'+elementid).text(text).stop().fadeIn(200);
		setTimeout(function(){
			$('#'+elementid).fadeOut(200);
		},2000);
	}

})(window.jQuery)
