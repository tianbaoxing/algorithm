+(function($){
	var info = ["没有填写哦","必须是数字哦"]
	$("form").on("submit", function(){
		var result = validateForm($(this));
		if(result !== true){
			fnAlarm(result.text+result.errorType,"Alarm");
			return false;
		}
	})

	function validateForm(form){
		var inputs = form.find("input[type='text']");
		var len = inputs.length;
		var i ;
		for (i=0;i<len;i++) {
			// 检查是否填写
			if($(inputs[i]).hasClass("required")){
				var flag = CheckUtil.checkLength($(inputs[i]));
				if(!flag ){
					return {
						text: $(inputs[i]).prev().text(),
						errorType: info[0]
					}
				}
			}
			// check the type
			if($(inputs[i]).hasClass("number")){
				var flag = CheckUtil.checkValueType($(inputs[i]));
				if (!flag) {
					return {
						text: $(inputs[i]).prev().text(),
						errorType: info[1]
					}
				};
			}
		};
		return true;
	}


	// create an Object for all check method
	// 创建一个对象封装所有的表单检验方法；
	var CheckUtil = {

		checkLength:function(input){
			if(input.val().length === 0){
				return false;
			}else{
				return true;
			}
		},

		checkValueType:function(input){
			// 如果input是可选项目，并且需要填写数字
			// 先验证是否有默认值，没有的话，则不必验证；
			// 如果有默认值在内的话，需要验证这个值是否为数字；
			if ( input.val().length !==0 ) {
				if(isNaN(input.val())){
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		},

		checkValueRange:function(input, lower, higher){
			var val = input.val();
			if(isNaN(input.val())){
				return "not a Number";
			}else if(val<higher && val>lower){
				return true;
			}else{
				return false;
			}
		}

	}

	function fnAlarm(text,elementid){
		$('#'+elementid).text(text).stop().fadeIn(200);
		setTimeout(function(){
			$('#'+elementid).fadeOut(200);
		},2000);
	}
})(window.jQuery)