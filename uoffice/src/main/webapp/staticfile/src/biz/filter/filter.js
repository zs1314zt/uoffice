define(function(require,exports,module){
    
    //分页参数
    var $window = $(window),
        tpl = $("#J_office").html(),
        $scrParam = $('.J_scrparam'),
        page = +$scrParam.attr('data-page'),
        total = +$scrParam.attr('data-total');
    
    /****
    * 校验滚动条位置
    */
    function checkScrollSite(){
        
        //滚动条距离顶部的距离
        var scrollTop = $(window).scrollTop() + $(window).height(),
            lastHeight = $('.g-ft').offset().top;
        
        //页面高度
        return scrollTop > lastHeight ? true : false;
        
    }

    /****
    * 无限滚动请求加载函数
    */
    function ajaxRequest(){
        var location = window.location.search.slice(1);
        page += 1;
        $.ajax({
            type: 'GET',
            url: window.location.origin + '/uoffice/searchAjax',
            data: location + '&page=' + page + '&pagesize=12',
            dataType: 'json',
            beforeSend: function(){         
            },
            success: function(data){
                var officeList = data.officeList,
                    html = '';
                for (var i = 0, j = officeList.length; i < j; i++) {
                    html += _.template(tpl, officeList[i]);
                }
                $(".J_list").append(html);
            },
            complete: function(){
                ajaxState = true;
                $scrParam.attr('data-page', page);
            }
        });
    }
    
    var ajaxState = true;
    $window.on('scroll', function() {
        console.log(checkScrollSite(), ajaxState);
        if (checkScrollSite() && ajaxState) {

            ajaxState = false;
            ajaxRequest();
        }
        if(page === total) {
            console.log(page);
            $window.off('scroll');
        }
    });
    
    _.templateSettings = {
        interpolate : /\{\{(.+?)\}\}/g
    };
    

    var backTop = function(){
        var obtn = $('.J_back')[0];
        //获取页面可视区的高度
        var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
        var timer = null;
        var isTop = true;
            
        window.onscroll = function(){
            //获取滚动条距离顶部的高度
            var osTop =  document.documentElement.scrollTop || document.body.scrollTop;
            if (osTop >= clientHeight){
                //显示按钮
                obtn.style.display = 'block';
            } else {
                //隐藏按钮
                obtn.style.display = 'none';
            }
            if (!isTop){
                clearInterval(timer);
            }
            isTop = false;
        }
        obtn.onclick = function(){
            //设置定时器
            timer = setInterval(function(){
                //获取滚动条距离顶部的高度
                var osTop =  document.documentElement.scrollTop || document.body.scrollTop;
                var ispeed = Math.floor(-osTop / 6);
                document.documentElement.scrollTop = document.body.scrollTop = osTop +ispeed;
                    
                isTop = true;
                if (osTop == 0){
                    clearInterval(timer);
                }
            },30);
        }
    }
    backTop();
});