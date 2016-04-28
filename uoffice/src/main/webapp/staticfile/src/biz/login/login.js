$(function(){
    var $user = $('.J_username'),
        $psw = $('.J_password'),
        $loginBtn = $('.J_login_btn');
    
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
                msg = '手机格式输入错误，请输入11位手机号';
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

    $loginBtn.on('click', function(e){
        var username = $user.val(),
            password = $psw.val(),
            $form = $('.J_login_form'),
            checkReg = verify();
        if (checkReg.email(username)  && checkReg.psw(password)) {
            $.ajax({
                type : 'POST',
                url : window.location.origin + '/uoffice/login',
                data: 'username=' + username + '&password=' + password,
                success: function(data){
                    if (data.code === '200') {
                        var referrer_url=document.referrer;
                        if(referrer_url==window.location.origin + '/uoffice/view/screen/login/login.jsp'||
                                referrer_url==window.location.origin+'/uoffice/logout'||
                                referrer_url==window.location.origin+'/uoffice/showPersonalInfo'||
                                referrer_url==window.location.origin+'/uoffice/view/screen/register/register.jsp'||
                                referrer_url==window.location.origin+'/uoffice/view/screen/findpsw/findpsw.jsp'){
                            referrer_url = window.location.origin + '/uoffice/public';
                        }
                        window.location.href= referrer_url;
                    } else {
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