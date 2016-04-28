$(function(){
    
    var $window = $(window);
    //帮我找
    $('.J_helpfind').on('click', function(e){
        e.preventDefault();

        miniDialog({
            title: '请填下表，我们将尽快联系您。',      //标题，可以传入html片段渲染
            content: document.getElementById('J_minid').innerHTML,  //内容区，可以传入html片段渲染
            width: 300,    //宽度，默认为200
            height: 460,    //高度，默认为150
            closeX: false,    //closeX属性为true则显示右上角的关闭按钮。默认为false，不显示
            buttons: [    //相应的按钮内容和点击事件， 默认传空数组[]则没有按钮。点击事件里return true能关闭对话框。
                {
                    'value': '提交',
                    'click': function (e, thisDialog) {
                        var form = $('.J_replay')[0],
                            param = {
                                userName: form.userName.value,
                                phone: form.phone.value,
                                city: form.city.value,
                                message: form.message.value
                            },
                            isEmpty = false;

                        _.each(param, function(v, k){
                            if (!v) {
                                isEmpty = true;
                                return;
                            }
                        });

                        if (isEmpty === true) {

                            window.lib.noty('请填完字段再提交', {
                                type:'danger'
                            });
                            return false;
                        }

                        $.ajax({
                            type : 'POST',
                            url : window.location.origin + '/uoffice/sendReplay',
                            data: 'replay.userName=' + param.userName + '&replay.phone=' + 
                                param.phone + '&replay.city=' + param.city + 
                                '&replay.message=' + param.message,
                            success: function(data){
                                window.lib.noty(data.message, {
                                    type:'success'
                                });
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

});