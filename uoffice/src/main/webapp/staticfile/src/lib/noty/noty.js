;(function(win, lib){
    var $ = win['$'],
        $win = $(win);

    var templateMain = $('<div class="J_messageBox" style="font: 12px/1.5 Helvetica Neue, Helvetica, Arial, sans-serif;"></div>'),
        templateMessagePrimary = $('<div class="content primary"><span class="msg"></span><span class="close"><i>&times;</i></span></div>'),
        templateMessageWarning = $('<div class="content warning"><span class="msg"></span><span class="close"><i>&times;</i></span></div>'),
        templateMessageDanger = $('<div class="content danger"><span class="msg"></span><span class="close"><i>&times;</i></span></div>'),
        templateMessageSuccess = $('<div class="content success"><span class="msg"></span><span class="close"><i>&times;</i></span></div>');
        viewMessageBox = $('.J_messageBox'),
        stMessageNum = 0;

    var conf = {

        /*
         * @note 提示类型
         * @value 'danger' 危险 错误
         * @value 'success' 成功
         * @value 'warning' 警告
         * @value 'info' 信息
         */
        type : 'info',

        /*
         * @note 提示类型
         * @value 0 永不自动关闭
         * @value Number 多久之后自动关闭提示
         */
        closeTime : 5000,

        /*
         * @note 消息图标样式
         * @value 'default' 使用默认图标配置
         * @value String 使用图标的编号 如&#xf0138;
         * @value false 不使用图标
         */
        iconType : 'default',

        /*
         * @note 将消息显眼的展示
         * @value true 显眼展示
         * @value false 默认展示
         */
        apparent : false,

        /*
         * @note 最大显示消息数量
         * @value Number 消息数量
         */
        maxMessageNum : 4

    };


    var onEvent = function(){

        /*
         * note 显示/隐藏关闭按钮 & 关闭消息
         * author early
         */
        viewMessageBox.on('mouseover', '.content', function(){
            $(this).find('.close').css({
                'right' : '0'
            });
        })
        .on('mouseout', '.content', function(){
            $(this).find('.close').css({
                'right' : '-24px'
            });
        })
        .on('click', '.close', function(){

            var index = viewMessageBox.children('.content').index($(this).parent()),
                $msg = viewMessageBox.children('.content:eq('+index+')');
            view.delMessage($msg);

        });

    };


    var view = {

        /*
         * note 初始化渲染消息盒子
         * author early
         */
        renderMessageBox : function(){

            $('body').append(templateMain);
            viewMessageBox = $('.J_messageBox');
            viewMessageBox.css({
                'position': 'fixed',
                'top': '0',
                'left': '50%',
                'width': '400px',
                'margin-left': '-200px',
                'padding': '6px 10px',
                'z-index': '9999',
                'opacity': '0.97'
            });

        },

        /*
         * note 渲染一条message
         * author early
         * param $msg 渲染msg的jq对象
         * param conf 渲染msg的conf
         */
        renderMessageItem : function( $msg, conf ){

            var type = conf.type,
                background = '#428bca';

            if ( type === 'info' ) {
                background = '#428bca';
            } else if ( type === 'danger' ) {
                background = '#d9534f';
            } else if ( type === 'warning' ) {
                background = '#f0ad4e';
            } else if ( type === 'success' ) {
                background = '#5cb85c';
            }
            
            // 设置警告通用样式
            $msg.css({
                'margin' : '4px 0',
                'color' : '#fff',
                'padding': '6px 28px 6px 10px',
                'border-radius': '4px',
                'line-height' : '1.3em',
                'font-size' : '13px',
                'position' : 'relative',
                'transform' : 'rotateX(-90deg) translateZ(20px) scale(1)',
                'transition' : '0.6s',
                'overflow' : 'hidden',
                'background' : background
            });

            $msg.find('p').css({
                'margin-bottom' : '0'
            });          

            // 设置关闭按钮
            $msg.find('.close').css({
                'position' : 'absolute',
                'display' : 'block',
                'right' : '-24px',
                'top' : '0',
                'width' : '24px',
                'height' : '100%',
                'cursor' : 'pointer',
                'transition' : 'right 0.15s',
                'background' : 'rgba(255, 255, 255, 0.2)',
                'filter' : 'progid:DXImageTransform.Microsoft.gradient(startColorstr=#ffffff20,endColorstr=#ffffff20)'
            });

            $msg.find('.close i').css({
                'font-size' : '12px',
                'position' : 'absolute',
                'top' : '50%',
                'margin-top' : '-8px',
                'left' : '50%',
                'margin-left' : '-6px'
            });

            // 添加图标
            if ( conf.iconType !== false ) {

                var $icon = $('<i></i>');

                // 添加默认图标
                if ( conf.iconType === 'default' ) {

                    if ( type === 'success' ) {
                        $icon.html('&radic;');
                    } else if ( type === 'danger' ) {
                        $icon.html('&times;');
                    } else if ( type === 'warning' ) {
                        $icon.html('-');
                    } else if ( type === 'info' ) {
                        $icon.html('i');
                    }

                } else {
                    $icon.html(conf.iconType);
                }

                $msg.find('span:first').prepend($icon);
                $msg.find($icon).append(' ');

            }

            $msg.find('.msg i').css({
                'background-color': '#fff',
                'color' : background,
                'border-radius': '8px',
                'display': 'inline-block',
                'width': '16px',
                'height': '16px',
                'text-align': 'center',
                'margin-right': '5px'
            });

            if ( conf.apparent ) {

                // 删除其它显眼的信息提示
                self.viewMessageBox.find('.isApparent').fadeOut(500, function(){
                    $(this).remove();
                });

                $msg.addClass('isApparent');

                $msg.css({
                    'width' : '400px',
                    'position' : 'fixed',
                    'top' : '50%',
                    'padding': '12px 28px 12px 10px',
                    'margin-top' : '-50px',
                    'left' : '50%',
                    'margin-left' : '-200px',
                    'text-align' : 'center',
                    'font-size' : '18px'
                });

                $msg.find('span i').css({
                    'font-size' : '18px'
                });

                $msg.find('.close i').css({
                    'margin-top' : '-11px',
                    'font-size' : '12px'
                });
            }

        },

        /*
         * note 删除一条message
         * author early
         * param index msg的索引 或者 一个jq对象
         */
        delMessage : function( index ){

            var $t;

            if ( Object.prototype.toString.call(index) === '[object Number]' ) {
                $t = viewMessageBox.children('.content:not(.isApparent):eq('+index+')');
            } else {
                $t = index;
            }

            $t.css({
                'height' : '0',
                'padding' : '0 28px 0 10px',
                'opacity' : '0',
                'transition' : '0.4s'
            });

            setTimeout(function(){
                $t.remove();
            }, 400);

        }

    };

    view.renderMessageBox();
    onEvent();

    lib.noty = function( message, config ){

        var height;

        config = config || {};
        config = $.extend(true, {}, conf, config);

        var keyMap = {
            danger : 'Danger',
            success : 'Success',
            warning : 'Warning',
            info : 'Primary'
        };
        var $msg; 
        switch (keyMap[config.type]) {
            case 'Danger':
                $msg = templateMessageDanger.clone();
                break;
            case 'Success':
                $msg = templateMessageSuccess.clone();
                break;
            case 'Warning':
                $msg = templateMessageWarning.clone();
                break;
            case 'Primary':
                $msg = templateMessagePrimary.clone();
                break;
            default:
                $msg = templateMessageDanger.clone();
        }

        // renderView($msg, {
        //     msg : message
        // });
        $msg.find('.msg').text(message);

        // 渲染消息
        view.renderMessageItem($msg, config);

        // 插入一条记录
        viewMessageBox.append($msg);

        // 根据不同盒模型计算高度
        if ( $msg.css('box-sizing') === 'border-box' ) {
            height = $msg.outerHeight();
        } else {
            height = $msg.height();
        }

        setTimeout(function(){
            $msg.css({
                'transform' : 'rotateX(0deg) translateZ(45px) scale(1)',
                'height' : height
            });
        });

        // 消息数量＋1
        stMessageNum = viewMessageBox.children('.content').length;

        // 如果超过最大消息数量
        if (stMessageNum > config.maxMessageNum ) {

            for (var i=0; i < (stMessageNum - config.maxMessageNum); i++ ) {
                view.delMessage(i);
            }

        }

        // 如果有默认删除时间
        if ( config.closeTime > 0 ) {

            // 注册一个删除器
            setTimeout(function(){
                view.delMessage($msg);
            }, config.closeTime );

        }

        return this;

    };

})(window, window.lib || (window.lib = {}));