$(function(){
	var $user = $('.J_userEmail'),
		$psw = $('.J_password'),
		$pswConfirm = $('.J_password_confirm'),
		$getCodeBtn = $('.J_getCode'),
		$code = $('.J_code'),
		$findpswBtn = $('.J_findpsw_btn');
		//表单校验
	var verify = function(val){
		var phoneReg = /^1[3|4|5|8]\d{9}$/,
			passwordReg = /^[a-z0-9_-]{6,18}$/,
			emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/,
			msg = '';
		return {
			getMsg : function(){
				return msg;
			},
			phone : function(val){
				if (phoneReg.test(val)) {
					return true;
				}
				msg = '用户名输入错误，请输入11位手机号';
				return false;
			},
			email: function(val) {
				if (emailReg.test(val)) {
					return true;
				}
				msg = '邮箱输入格式有误，请输入正确的邮箱';
				return false;
			},
			psw: function(val){
				if (passwordReg.test(val)) {
					return true;
				}
				msg = '密码验证不通过，请输入6到18位的字母数字';
				return false;
			},
		};
	};
	//倒计时一分钟
	var wait = function ($o,waittime) {
        if (wait === 0) {
            $o.removeAttr("disabled");   
            $o.text("重新获取验证码");
            waittime = 60;
        } else {
            $o.attr('disabled', true);
            $o.text(wait + '秒后重新获取');
            waittime -= 1;
            setTimeout(function() {
                time($o,waittime);
            },
            1000);
        }
    };
	//验证邮箱并获得验证码
	$getCodeBtn.on('click',function(m){
		var userEmail = $user.val(),
			type = '02',
			checkReg = verify(),
			$btn = $getCodeBtn;
			// alert(userPhone);
		if(checkReg.email(userEmail)){
			$.ajax({
				type : 'Post',
				url	: window.location.origin + '/uoffice/createVerificationCode',
				data : 'loginName=' + userEmail + '&type=' +type ,
				success: function(data){
					alert(data.message);
					if(data.code === '200'){
						time($btn,60);
					}
					
				}
			})
		}else{
			alert(checkReg.getMsg());
		}

		return false;
	});

	//修改密码
	$findpswBtn.on('click', function(){
		var userEmail = $user.val(),
			password = $psw.val(),
			verificationCode = $code.val(),
			pswconfirm = $pswConfirm.val(),
			$form = $('.J_findpsw_form'),
			checkReg = verify();
		if (password === pswconfirm) {
			if (checkReg.email(userEmail) && checkReg.psw(password)) {
				$.ajax({
					type : 'POST',
					url	: window.location.origin + '/uoffice/findPassword',
					data: 'loginName=' + userEmail + '&verificationCode=' + verificationCode + '&newPassword=' + password,
					success: function(data){
						if (data.code === '200') {
							alert(data.message);
							setTimeout(function(){
								window.location.href= window.location.origin + '/uoffice/view/screen/login/login.jsp';
							}, 3000);
							
						}else{
							alert(data.message);
						}

					}
				})
			} else {
				alert(checkReg.getMsg());
			}
		} else {
			alert('两次密码输入不相同，请检查后再提交');
		}
		return false;
		
	});
	
	
})
