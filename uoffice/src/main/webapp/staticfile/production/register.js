$(function(){var e=$(".J_username"),t=$(".J_password"),n=$(".J_password_confirm"),i=$(".J_getCode"),o=$(".J_code"),r=$(".J_register_btn"),a=$(".J_confirm_cb"),c=function(e){var t=/^1[3|4|5|8]\d{9}$/,n=/^[a-z0-9_-]{6,18}$/,i=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/,o="";return{getMsg:function(){return o},phone:function(e){return t.test(e)?!0:(o="用户名输入错误，请输入11位手机号",!1)},email:function(e){return i.test(e)?!0:(o="邮箱输入格式有误，请输入正确的邮箱",!1)},psw:function(e){return n.test(e)?!0:(o="密码验证不通过，请输入6到18位的字母数字",!1)}}};a.on("click",function(){var e=a.prop("checked");e===!0?r.removeAttr("disabled"):r.attr("disabled","true")}),i.on("click",function(t){var n=e.val(),i="01",o=c();return o.email(n)?$.ajax({type:"Post",url:window.location.origin+"/uoffice/createVerificationCode",data:"loginName="+n+"&type="+i,success:function(e){alert(e.message),"200"===e.code&&time($btn,60)}}):alert(o.getMsg()),!1}),r.on("click",function(){var i=e.val(),r=t.val(),a=o.val(),s=(n.val(),$(".J_register_form"),c());return s.email(i)&&s.psw(r)?$.ajax({type:"POST",url:window.location.origin+"/uoffice/register",data:"registerName="+i+"&userPwd="+r+"&verificationCode="+a,success:function(e){"200"===e.code?(alert(e.message),setTimeout(function(){window.location.href=window.location.origin+"/uoffice/view/screen/login/login.jsp"},3e3)):alert(e.message)}}):alert(s.getMsg()),!1})});
