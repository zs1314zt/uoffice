define(function (require, exports, module) {
    var lightbox = require('../../lib/lightbox/lightbox.js');
    var $publishMes = $('.btn-commond'),
        $message = $('.c-message'),
        $officeId = $('.officeId'),
        $verifyCodeimg = $('.J_verifyImg'),
        $verifyCode = $('.J_verify'),
        tpl = $("#J-message").html(),
        $messageCont = $('.messageCont'),
        nav = $('#J_nav'),
        items = $('.c-info').find('.item'),
        currentId = "",
        detail = $('#J_details').offset().top,
        verifyStatus = 0;

    _.templateSettings = {
        interpolate: /\{\{(.+?)\}\}/g
    };
    
    $verifyCodeimg.on('click', function (e) {
        $.ajax({
            type: 'POST',
            url: window.location.origin + '/uoffice/getVeriryCodeImage',
            dataType: 'json',
            success: function (data) {
                var verifyCodeStr = 'data:image/png;base64,' + data.verifyCodeStr;
                $verifyCodeimg.attr('src',verifyCodeStr);
            }
        })
    });
    
    $publishMes.on('click', function (e) {
        var message = $message.val(),
            officeId = $officeId.val(),
            verifyCode = $verifyCode.val();
            
        if (message == null || message == undefined || message == "") {
            window.lib.noty("请输入留言信息", {
                type: 'danger'
            });
        } else{
            $.ajax({
                type: 'POST',
                url: window.location.origin + '/uoffice/publishMes',
                data: 'message.messContent=' + message + '&message.officeId=' + officeId +'&verifyCode=' + verifyCode,
                success: function (data) {
                    var verifyCodeStr = 'data:image/png;base64,' + data.verifyCodeStr;
                    console.log(data);
                    if (data.code === '200') {
                        var message = data.Message;
                        $verifyCodeimg.addClass('f-dn'),
                        $verifyCode.addClass('f-dn'),
                        console.log(message);
                        $messageCont.append(_.template(tpl, message));
                        $message.val("");
                    } else if(data.code === '401'){
                        $verifyCodeimg.removeClass('f-dn'),
                        $verifyCode.removeClass('f-dn'),
                        $verifyCodeimg.attr('src',verifyCodeStr);
                        window.lib.noty(data.message, {
                            type: 'danger'
                        });
                    }
                    else {
                        window.lib.noty(data.message, {
                            type: 'danger'
                        });
                    }
                }
            })
        }
        $verifyCode.val('');
        return false;
    });


    var $deleteOff = $('.J_deleteMessage'),
        $changeOff = $('.J_changeMessage');

    $deleteOff.on('click', function (e) {
        officeId = $officeId.val();
        $.ajax({
            type: 'GET',
            url: window.location.origin + '/uoffice/deleteOfficeInfo',
            data: 'officeId=' + officeId,
            success: function (data) {
                if (data.code === '200') {
                    window.lib.noty(data.message, {
                        type: 'success'
                    });
                } else {
                    window.lib.noty(data.message, {
                        type: 'danger'
                    });
                }
            }
        })
        return false;

    });
    nav.children('.nav-item').on('click', function (){
       nav.addClass("f-fixed");
       var m = $(this).index();
       var mtop = $('.target_'+m).offset().top;
       $(window).scrollTop(mtop - 49);
    });

    $changeOff.on('click', function (e) {
        officeId = $officeId.val();
        window.location.href = window.location.origin + '/uoffice/ModifyView?officeId=' + officeId;
    });
    nav.removeClass("f-fixed");
    $(window).scroll(function () {
        var top = $(document).scrollTop();
        if (top > detail) {
            nav.addClass("f-fixed");
        }
        else {
            nav.removeClass("f-fixed");
        }
        items.each(function () {
            var m = $(this);
            var itemTop = m.offset().top;
            
            if (top > itemTop -50) {
                currentId = m.attr("id");
            } else {
                return false;
            }
        });
        var currentLink = nav.find('.current');
        if (currentId && currentLink.attr("date") != currentId) {
            currentLink.removeClass("current");
            nav.find("[date=" + currentId + "]").addClass("current");
        }
    });
    
    $('#J_booking').on('click', function(e){
        e.preventDefault();
        var officeid = $officeId.val(),
            officename = $('.c-officeName label').html();

        miniDialog({
            title: '请填下表，我们将尽快联系您。',      //标题，可以传入html片段渲染
            content: document.getElementById('J_bookingid').innerHTML,  //内容区，可以传入html片段渲染
            width: 300,    //宽度，默认为200
            height: 250,    //高度，默认为150
            closeX: false,    //closeX属性为true则显示右上角的关闭按钮。默认为false，不显示
            buttons: [    //相应的按钮内容和点击事件， 默认传空数组[]则没有按钮。点击事件里return true能关闭对话框。
                {
                    'value': '提交',
                    'click': function (e, thisDialog) {
                        var form = $('.J_replay')[0],
                            param = {
                                phone: form.phone.value,
                                email: form.email.value,
                            },
                            isEmpty = false;
                            
                        if(!param.phone &&!param.email){
                            isEmpty = true;
                        }
                       
                        if (isEmpty === true) {

                            window.lib.noty('请至少填写一种联系方式', {
                                type:'danger'
                            });
                            return false;
                        }

                        $.ajax({
                            type : 'POST',
                            url : window.location.origin + '/uoffice/sendOrder',
                            data: 'order.officeId=' + officeid + '&order.officeName=' + 
                                officename + '&order.phone=' + param.phone + 
                                '&order.email=' + param.email,
                            success: function(data){
                                if(data.code == "200"){
                                    window.lib.noty(data.message, {
                                        type:'success'
                                    });
                                }
                                else if(data.code == "199"){
                                     window.lib.noty(data.message, {
                                        type:'danger'
                                    });
                                }
                            }
                        });
                        thisDialog.remove();
                    }
                },
                {
                    'value': '取消',
                    'click': function (e, thisDialog) {
                        $('.miniDialog_wrapper').hide();
                        $('.miniDialog_mask').hide();
                        thisDialog.remove();
                    }
                }
            ]
        });
        
        

    });
    
    
    
    $('.weixin').on('click', function(e){
        e.preventDefault();
        
        $.ajax({
            type : 'POST',
            url : window.location.origin + '/uoffice/createQRCode',
            data:'url=' + window.location.href,
            success: function(data){
                var weixinImage = 'data:image/png;base64,' + data.QRCodeImage;
            
                miniDialog({
                    title: '分享到微信朋友圈',      //标题，可以传入html片段渲染
                    content: document.getElementById('weibofenxiang').innerHTML,  //内容区，可以传入html片段渲染
                    width: 300,    //宽度，默认为200
                    height: 350,    //高度，默认为150
                    closeX: true,    //closeX属性为true则显示右上角的关闭按钮。默认为false，不显示
                    buttons: [    //相应的按钮内容和点击事件， 默认传空数组[]则没有按钮。点击事件里return true能关闭对话框。
                        {
                            'value': '关闭',
                            'click': function (e, thisDialog) {
                                $('.miniDialog_wrapper').hide();
                                $('.miniDialog_mask').hide();
                                thisDialog.remove();
                            }
                        }
                    ]
                });
                
                $("#weiboerweima").attr("src", weixinImage);
            }
        });
       
    });    
    
    
})