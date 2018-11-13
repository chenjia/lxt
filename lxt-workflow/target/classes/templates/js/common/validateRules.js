$.extend($.fn.validatebox.defaults.rules, {
	chinese:{
		validator:function(value, param){
			if(param != undefined) {
                if (value.length < param[0] || value.length > param[1]) {
                    $.fn.validatebox.defaults.rules.chinese.message = '长度为{0}到{1}个字符，当前长度为'+value.length;
                    return false;
                }
            }
            var reg = /^[\u4E00-\u9FA5\uF900-\uFA2D]/;
            if(!reg.exec(value)){
            	$.fn.validatebox.defaults.rules.username.message = '只能输入汉字！';
                return false;
            }
            return true;
		},
		message:'只能输入汉字！'
	},
	number:{
		validator:function(value, param){
			if(param != undefined) {
                if (value.length < param[0] || value.length > param[1]) {
                    $.fn.validatebox.defaults.rules.number.message = '长度为{0}到{1}个字符，当前长度为'+value.length;
                    return false;
                }
            }
            var reg = /^[0-9]+$/;
            if(!reg.exec(value)){
            	$.fn.validatebox.defaults.rules.number.message = '只能输入数字！';
                return false;
            }
            return true;
		},
		message:'只能输入数字！'
	},
	chineseOrLetterOrNumber:{
		validator:function(value, param){
			if(param != undefined) {
                if (value.length < param[0] || value.length > param[1]) {
                    $.fn.validatebox.defaults.rules.chinese.message = '长度为{0}到{1}个字符';
                    return false;
                }
            }
            var reg = /^[\u4E00-\u9FA5\uF900-\uFA2D]/;
            if(!reg.exec(value)){
            	$.fn.validatebox.defaults.rules.username.message = '只能输入汉字、字母或数字！';
                return false;
            }
            return true;
		},
		message:'包含非法字符(只能输入汉字、字母或数字！)'
	},
	letterOrNumber:{
		validator:function(value, param){
			if(param != undefined) {
                if (value.length < param[0] || value.length > param[1]) {
                    $.fn.validatebox.defaults.rules.chinese.message = '长度为{0}到{1}个字符';
                    return false;
                }
            }
            var reg = /^[A-Za-z0-9]+$/;
            if(!reg.exec(value)){
            	$.fn.validatebox.defaults.rules.username.message = '只能输入字母或数字！';
                return false;
            }
            return true;
		},
		message:'只能输入字母或数字！'
	},
	letter:{
		validator:function(value, param){
			if(param != undefined) {
                if (value.length < param[0] || value.length > param[1]) {
                    $.fn.validatebox.defaults.rules.chinese.message = '长度为{0}到{1}个字符';
                    return false;
                }
            }
            var reg = /^[A-Za-z]+$/;
            if(!reg.exec(value)){
            	$.fn.validatebox.defaults.rules.username.message = '只能输入字母！';
                return false;
            }
            return true;
		},
		message:'只能输入字母！'
	},
	identityNo:{
		validator:function(value){
            var reg = /^[\d]{6}(19|20)*[\d]{2}((0[1-9])|(11|12))([012][\d]|(30|31))[\d]{3}[xX\d]*$/;
            return (value.length==15 || value.length==18) && reg.test(value);
        },
        message:'请正确填写身份证号码！'
	},
    mobile:{
        validator:function(value){
            var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})+$/;
            return value.length == 11 && reg.test(value);
        },
        message:'请正确填写手机号码！'
    },
    email:{
    	validator:function(value, param){
    		if(param != undefined) {
                if (value.length < param[0] || value.length > param[1]) {
                    $.fn.validatebox.defaults.rules.email.message = '长度为{0}到{1}个字符';
                    return false;
                }
            }
    		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/; 
    		if(!reg.test(value)){
    			$.fn.validatebox.defaults.rules.email.message = '请输入正确的格式，如：abc@sina.com';
                return false;
    		}
            return true;
        },
        message:'请输入正确的格式，如：abc@sina.com'
    },
	username:{
		validator:function (value, param) {
			if(param != undefined) {
                if (value.length < param[0] || value.length > param[1]) {
                    $.fn.validatebox.defaults.rules.username.message = '长度为{0}到{1}个字符';
                    return false;
                }
            }
            var reg = /^[a-zA-Z0-9]+$/;
            if(!reg.exec(value)){
            	$.fn.validatebox.defaults.rules.username.message = '包含非法字符(只能输入英文字母、数字)！';
                return false;
            }
            var isValid = false;
            $.ajax({
            	url:'user!isUserExists.action',
            	data:{'param.username':value},
            	async:false,
            	success:function(data){
            		if(data){
            			$.fn.validatebox.defaults.rules.username.message = '用户名正在使用，请更换其他用户名！';
                        isValid = false;
            		}else{
            			isValid = true;
            		}
            	}
            });
            return isValid;
        },
        message: '非法的用户名！'
    }
});