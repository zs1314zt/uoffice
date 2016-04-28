define(function(require,exports,module){
    var carousel = require('../../common/carousel/carousel.js');
    var countUp = require('../../lib/countUp/countUp.min.js');  
    var datetimepicker = require('../../lib/jquery-datetimepicker/jquery.datetimepicker.js');
    var minidialog = require('../../lib/miniDialog/miniDialog.js');
    var $window = $(window);
    
    //轮播图
    carousel.carousel();

    var options = {
        useEasing : true,//是否渐停
        useGrouping : false,//是否有位数符
        separator : ',', 
        decimal : '.', 
        prefix: '',
        suffix: ''
    };
    /*
    CountUp(target, startVal, endVal, decimals, duration, options)，
        target = 目标元素的 ID；
        startVal = 开始值；
        endVal = 结束值；
        decimals = 小数位数，默认值是0；
        duration = 动画延迟秒数，默认值是2；
    */
    user = new CountUp("J_userCount", 0, $('#J_userCount').attr('data-num') || 0, 0, 3, options);
    office = new CountUp("J_officeCount", 0, $('#J_officeCount').attr('data-num') || 0, 0, 3, options);
    success = new CountUp("J_successCount", 0, $('#J_successCount').attr('data-num') || 0, 0, 3, options);
    $window.on('scroll', function() {
        if ($window.scrollTop() >= 200) {
            user.start();
            office.start();
            success.start();
            $window.off('scroll');
        }
    });
    
    $('.J_scr .c-city').on('click', function(e) {
        var $J_selector = $(".J_selector");
        var $J_selector_parent = $('.J_selector_parent');
        if ( $J_selector_parent.css('display') === 'block' ) {
            $J_selector_parent.css('display', 'none');
        } else {
            $(".J_selector").empty();
            $.ajax({
                type : 'POST',
                url : window.location.origin + '/uoffice/getAllCity',
                success: function(data){
                    $.each(data, function(i,val){
                        var html = "<span data-id='"+val.locationCode+"'>"+val.locationName+"</span>";
                        $J_selector.append(html);
                      }); 
                    
                    $J_selector_parent.css('display', 'block');
                }
            });
            
        }
    })
    $('.J_selector').on('click', 'span', function(e){
        var $this = $(this),
            id = $this.attr('data-id'),
            name = $this.text(),
            $scr = $('.J_scr'),
            $locationName = $scr.find('.J_location_name');
            $locationName.text(name);
        
        $.ajax({
            type : 'POST',
            url : window.location.origin + '/uoffice/changeCity',
            data: 'offLocation.locationCode=' + id + 
            '&offLocation.locationName=' + name
        });
    })

});