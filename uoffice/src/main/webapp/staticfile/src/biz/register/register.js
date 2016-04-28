$(function(){
    var $user = $('.J_username'),
        $psw = $('.J_password'),
        $pswConfirm = $('.J_password_confirm'),
        $getCodeBtn = $('.J_getCode'),
        $code = $('.J_code'),
        $registerBtn = $('.J_register_btn');
        // $confirmcb = $('.J_confirm_cb');

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
    //倒计时60秒
    var wait = function ($o,waittime) {
        if (waittime === 0) {
            $o.removeAttr("disabled");   
            $o.text("重新获取验证码");
            waittime = 60;
        } else {
            $o.attr('disabled', true);
            $o.text(waittime + '秒后重新获取');
            waittime -= 1;
            setTimeout(function() {
                wait($o,waittime);
            },
            1000);
        }
    };
    //判断checkbox是否勾选
    //  $confirmcb.on('click',function() {
    //      var flag = $confirmcb.prop('checked');
    //      if (flag === true) {
    //          $registerBtn.removeAttr('disabled');
    //      }else{
    //          $registerBtn.attr('disabled','true');
    //      }
    //  });
    //验证邮箱并获得验证码
    $getCodeBtn.on('click',function(m){
        var userEmail = $user.val(),
            type = '01',
            checkReg = verify(),
            $btn = $getCodeBtn;
            // alert(userPhone);
        if(checkReg.email(userEmail)){
            $.ajax({
                type : 'Post',
                url : window.location.origin + '/uoffice/createVerificationCode',
                data : 'loginName=' + userEmail + '&type=' +type ,
                success: function(data){
                    window.lib.noty(data.message, {
                        type:'info'
                    });
                    if(data.code === '200'){
                        wait($btn,60);
                    }
                }
            })
        }else{
            window.lib.noty(checkReg.getMsg(), {
                type:'danger'
            });
        }

        return false;
    });

    //注册
    $registerBtn.on('click', function(){
        var userEmail = $user.val(),
            password = $psw.val(),
            verificationCode = $code.val(),
            pswconfirm = $pswConfirm.val(),
            $form = $('.J_register_form'),
            checkReg = verify();
        if (checkReg.email(userEmail) && checkReg.psw(password)) {
            $.ajax({
                type : 'POST',
                url : window.location.origin + '/uoffice/register',
                data: 'registerName=' + userEmail + '&userPwd=' + password + '&verificationCode=' + verificationCode,
                success: function(data){
                    if (data.code === '200') {
                        window.lib.noty(data.message, {
                            type:'success'
                        });
                        setTimeout(function(){
                            window.location.href= window.location.origin + '/uoffice/view/screen/login/login.jsp';
                        }, 3000);
                        
                    }else{
                        window.lib.noty(data.message, {
                            type:'danger'
                        });
                    }

                }
            })
        } else {
            window.lib.noty(checkReg.getMsg(), {
                type:'danger'
            });
        }
        
        return false;
        
    });
    
    
})