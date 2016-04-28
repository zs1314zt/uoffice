(function($){
	$.fn.imagezoom=function(options){

		var settings={
			xzoom:310,
			yzoom:310,
			offset:10,
			position:'BTR',
			preload:1,
			width:'800',
            height:'800'
		};
		if(options){$.extend(settings,options);}
		
		var noalt='', 
			self=this,
            big = self.attr('rel');

        // 判断图片是否加载完毕 
        function loadImage(url){
            var o= new Image();
                o.src = url;
            if(o.complete){
                showImagezoom();
            }else{
                o.onload = function(){
                  showImagezoom();
                };
                o.onerror = function(){
                  return;
                };
            }
        }
        // 执行放大镜插件
        loadImage(big);
        // 图片加载完毕之后 执行 imagezoom 放大镜插件
        function showImagezoom(){
            setTimeout(function(){
                self.on('mousemove',function(ev){
                    var bigSplit = big.split(/[0-9]\_/)[1].split('.jpg')[0],
                        bigWidth = bigSplit.split('x')[0],
                        bigHeight = bigSplit.split('x')[1];

                    if(Number(settings.width) > Number(bigWidth) ||  Number(settings.height) > Number(bigHeight)){
                        self.css('cursor','pointer');
                        return false;
                    }
                    var imageLeft=self.offset().left,
                        imageTop=self.offset().top,
                        imageWidth=self.get(0).offsetWidth,
                        imageHeight=self.get(0).offsetHeight,
                        boxLeft=self.offset().left,
                        boxTop=self.offset().top,
                        boxWidth=self.width(),
                        boxHeight=self.height(),
                        bigimage=self.attr('rel');
                    noalt=self.attr('alt');
                    self.attr('alt','');
                    if($('div.zoomDiv').get().length==0){
                        $(document.body).append("<div class='zoomDiv'><img class='bigimg' src='"+bigimage+"'/></div><div class='zoomMask'>&nbsp;</div>");
                    }
                    if(settings.position=='BTR'){
                        if(boxLeft+boxWidth+settings.offset+settings.xzoom>screen.width){
                            leftpos=boxLeft-settings.offset-settings.xzoom;
                        }else{
                            leftpos=boxLeft+boxWidth+settings.offset;
                        }
                    }else{
                        leftpos=imageLeft-settings.xzoom-settings.offset;
                        if(leftpos<0){leftpos=imageLeft+imageWidth+settings.offset;}
                    }
                    $('div.zoomDiv').css({top:boxTop,left:leftpos});
                    $('div.zoomDiv').width(settings.xzoom);
                    $('div.zoomDiv').height(settings.yzoom);
                    $('div.zoomDiv').show();
                    self.css('cursor','crosshair');
                    $(document.body).mousemove(function(e){
                        mouse=new MouseEvent(e);
                        if(mouse.x<imageLeft||mouse.x>imageLeft+imageWidth||mouse.y<imageTop||mouse.y>imageTop+imageHeight){mouseOutImage();return;}
                        var bigwidth=$('.bigimg').get(0).offsetWidth,
                            bigheight=$('.bigimg').get(0).offsetHeight,
                            scaley='x';var scalex='y';
                        if(isNaN(scalex)|isNaN(scaley)){
                            var scalex=(bigwidth/imageWidth),
                                scaley=(bigheight/imageHeight);
                            $('div.zoomMask').width((settings.xzoom)/scalex);
                            $('div.zoomMask').height((settings.yzoom)/scaley);
                            $('div.zoomMask').css('visibility','visible');
                        }
                        xpos=mouse.x-$('div.zoomMask').width()/2;
                        ypos=mouse.y-$('div.zoomMask').height()/2;
                        xposs=mouse.x-$('div.zoomMask').width()/2-imageLeft;
                        yposs=mouse.y-$('div.zoomMask').height()/2-imageTop;
                        xpos=(mouse.x-$('div.zoomMask').width()/2<imageLeft)?imageLeft:(mouse.x+$('div.zoomMask').width()/2>imageWidth+imageLeft)?(imageWidth+imageLeft-$('div.zoomMask').width()):xpos;ypos=(mouse.y-$('div.zoomMask').height()/2<imageTop)?imageTop:(mouse.y+$('div.zoomMask').height()/2>imageHeight+imageTop)?(imageHeight+imageTop-$('div.zoomMask').height()):ypos;
                        $('div.zoomMask').css({top:ypos,left:xpos});
                        $('div.zoomDiv').get(0).scrollLeft=xposs*scalex;
                        $('div.zoomDiv').get(0).scrollTop=yposs*scaley;
                    });
                });
                function mouseOutImage(){
                    $(self).attr('alt',noalt);
                    $(document.body).off('mousemove');
                    $('div.zoomMask').remove();
                    $('div.zoomDiv').remove();
                }
                count=0;
                if(settings.preload){
                    $('body').append("<div style='display:none;' class='jqPreload"+count+"'></div>");
                    self.each(function(){
                        var imagetopreload=self.attr('rel'),
                            content=jQuery('div.jqPreload'+count+'').html();
                        jQuery('div.jqPreload'+count+'').html(content+'<img src=\"'+imagetopreload+'\">');
                    });
                }
            },1000);
        }
        
	}
})(jQuery);
function MouseEvent(e){this.x=e.pageX;this.y=e.pageY;}