$(function(){
    var $publishMes = $('.btn-commond'),
        $message = $('.c-message'),
        $officeId = $('.officeId'),
        tpl = $("#J-message").html(),
        $messageCont = $('.messageCont'),
        nav = $('#J_nav'),
        items = $('.content').find('.item'),
        currentId = "",
        detail = $('#J_details').offset().top;
        
    _.templateSettings = {
        interpolate : /\{\{(.+?)\}\}/g
    };
    $publishMes.on('click', function(e){
        var message = $message.val(),
            officeId = $officeId.val();
            if(message == null || message == undefined || message == ""){
                alert("请输入留言信息");
            }else{
                $.ajax({
                    type : 'POST',
                    url : window.location.origin + '/uoffice/publishMes',
                    data: 'message.messContent=' + message + '&message.officeId=' + officeId,
                    success: function(data){
                    	console.log(data);
                        if (data.code === '200') {
                            var message = data.Message;
                            console.log(message);
                            $messageCont.append( _.template(tpl, message));
                        } else {
                            alert(data.message);
                        }
                    }
                })  
            }
        return false;
        
    });
    
    
    var $deleteOff = $('.J_deleteMessage'),
        $changeOff = $('.J_changeMessage');
    
    $deleteOff.on('click', function(e){
            officeId = $officeId.val();
            $.ajax({
                type : 'GET',
                url : window.location.origin + '/uoffice/deleteOfficeInfo',
                data: 'officeId=' + officeId,
                success: function(data){
                    if (data.code === '200') {
                        alert(data.message);
                    } else {
                        alert(data.message);
                    }
                }
            })  
        return false;
        
    });
    
    
    $changeOff.on('click', function(e){
        officeId = $officeId.val();
        window.location.href = window.location.origin + '/uoffice/ModifyView?officeId='+officeId;
    });
    nav.removeClass("f-fixed");
    $(window).scroll(function(){
       var top = $(document).scrollTop();
           
           
       if(top>detail){
           nav.addClass("f-fixed");
       }
       else{
           nav.removeClass("f-fixed");
       }
       items.each(function () {
          var m = $(this);
          var itemTop = m.offset().top;
          if(top > itemTop -300){
              currentId = "#" + m.attr("id");
          } else{
              return false;
          }
       });
       console.log(currentId);
       var currentLink = nav.find('.current');
       if(currentId && currentLink.attr("href") != currentId){
           currentLink.removeClass("current");
           nav.find("[href=" + currentId + "]").addClass("current");
       }
    });
    
    
})
