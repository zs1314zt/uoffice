$(function(){
    var $useremail = $('.J_useremail'),
        $username = $('.J_username'),
        $realname = $('.J_realname'),
        $userphone = $('.J_userphone'),
        $userinfoBtn = $('.J_userinfo_btn');
        

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
                msg = '手机号输入错误，请输入11位手机号';
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


    //修改个人信息
    $userinfoBtn.on('click', function(e){
        var username = $username.val(),
            realname = $realname.val(),
            userphone = $userphone.val(),
            $form = $('.J_userinfo_form'),
            checkReg = verify();
        if (checkReg.phone(userphone) || userphone === '') {
            $.ajax({
                type : 'POST',
                url : window.location.origin + '/uoffice/modifyPersonalInfo',
                data: 'user.userName=' + username + '&user.realName=' + realname + '&user.userPhone=' + userphone,
                success: function(data){    
                    if (data.code === '200') {              
                        window.lib.noty("个人信息修改成功", {
                            type:'success'
                        });
                    } else {
                        window.lib.noty("修改失败", {
                            type:'danger'
                        });
                    }
                }
            })
        } else {
            window.lib.noty(checkReg.getMsg(), {
                type:'success'
            });
        }
        
        return false;
        
    });
    
    
})