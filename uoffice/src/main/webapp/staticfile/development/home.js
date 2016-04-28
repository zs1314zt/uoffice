/*!
 * miniDialog
 * Author: jieyou Qin
 * see https://github.com/jieyou/miniDialog
 */
;(function($,document,window){
	var defaultOptions = {
			title:'消息',
			content:'',
			width:200,
			height:150,
			mask:true,
			closeX:false,
			autoShow:true,
			clickCloseX:function(){},
			init:function(){},
			warpperCssClass:'miniDialog_wrapper',
			titleCssClass:'miniDialog_title',
			contentOuterCssClass:'miniDialog_content_outer',
			contentCssClass:'miniDialog_content',
			buttonsAreaCssClass:'miniDialog_buttons_area',
			buttonCssClass:'miniDialog_button',
			otherTypeButtonCssClassPrefix:'miniDialog_button_',
			lastButtonCssClass:'miniDialog_last_button',
			closeXCssClass:'miniDialog_close_x',
			buttons:[
				{
					'value':'确定'
				}
			]
		},
		otherOptions = {
			maskCssClass:'miniDialog_mask'
		},
		$body = $('body'),
		$document = $(document),
		$window = $(window),
		$mask,
		maskUserCount = 0,
		isIE6 = /msie 6/i.test(navigator.userAgent)

	function position(miniDialog){
		var this$ = miniDialog.$,
			left = ($window.width()-this$.outerWidth(true))/2,
			top = ($window.height()-this$.outerHeight(true))/(1+1.61803398875)
		if(this$){
			if(isIE6){
				this$.css({
					'left':document.documentElement.scrollLeft + left,
					'top':document.documentElement.scrollTop + top
				})
			}else{
				this$.css({
					'left':left,
					'top':top
				})
			}
		}
	}
	function setMaskWH($mask){
		$mask.css({
			width:$document.width(),
			height:$document.height()
		})
	}

	function MiniDialog(options){
		var tmp,
			$tmpButton,
			self = this,
			buttonsCount
		if($.isPlainObject(options)){
			$.each(defaultOptions,function(k,v){
				if(defaultOptions.hasOwnProperty(k) && (!options.hasOwnProperty(k) || (typeof(options[k]) != typeof(defaultOptions[k])))){
					options[k] = v
				}
			})
		}else{
			options = defaultOptions
		}
		$.each(options,function(k,v){
			self[k] = v
		})
		self.isShow = false
		self.$ = $(document.createElement('DIV')).addClass(self.warpperCssClass).css({
			'width':self.width,
			'height':self.height
		})
		self.$title = $(document.createElement('DIV')).addClass(self.titleCssClass).html(self.title).appendTo(self.$)
		self.$contentOuter = $(document.createElement('DIV')).addClass(self.contentOuterCssClass).appendTo(self.$)
		self.$content = $(document.createElement('DIV')).addClass(self.contentCssClass).html(self.content).appendTo(self.$contentOuter)
		tmp = []
		buttonsCount = self.buttons.length
		$.each(self.buttons,function(i,e){
			$tmpButton = $(document.createElement('a')).addClass(self.buttonCssClass).attr('href',e.href || 'javascript:;').html(e.value).on('click',function(event){
				if(e.click && typeof(e.click) == 'function'){
					if(e.click.call(this,event,self) !== false){
						self.hide()
					}
				}else{
					self.hide()
				}
			})
			if(buttonsCount == (i+1)){
				$tmpButton.addClass(self.lastButtonCssClass)
			}
			if(e.type){
				$tmpButton.addClass(self.otherTypeButtonCssClassPrefix+e.type)
			}
			tmp.push($tmpButton)
		})
		self.$buttonsArea = $(document.createElement('DIV')).addClass(self.buttonsAreaCssClass)
		self.$buttonsArea.append.apply(self.$buttonsArea,tmp).appendTo(self.$)
		if(self.closeX){
			self.$closeX = $(document.createElement('DIV')).addClass(self.closeXCssClass).appendTo(self.$)
			
			// 添加x by early
			self.$closeX.html('<i class="iconfont">×</i>')

			self.$closeX.on('click',function(event){
				if(self.clickCloseX && typeof(self.clickCloseX) == 'function'){
					if(self.clickCloseX.call(this,event,self) !== false){
						self.hide()
					}
				}else{
					self.hide()
				}
			})
		}
		position(self)
		$window.on('resize',function(){
			position(self)
		})
		if(isIE6){
			$window.on('scroll',function(){
				position(self)
			})
		}
		self.$.appendTo($body)
		if(self.init && typeof(self.init) == 'function'){
			self.init.call(self)
		}
		if(self.autoShow){
			self.show()
		}
	}
	MiniDialog.prototype.show = function(){
		if(!this.isShow && this.$){
			if(this.mask){
				if($mask){
					$mask.show()
				}else{
					$mask = $(document.createElement('DIV')).addClass(otherOptions.maskCssClass)
					if(isIE6){
						setMaskWH($mask)
						$window.on('resize',function(){
							setMaskWH($mask)
						})
					}
					$mask.appendTo($body)
				}
				maskUserCount++
			}
			this.$.show()
			this.isShow = true
		}
	}

	MiniDialog.prototype.hide = function(){
		if(this.isShow){
			this.isShow = false
			if(this.mask){
				maskUserCount--
				if($mask && maskUserCount == 0){
					$mask.hide()
				}
			}
			if(this.$){
				this.$.hide()
			}
		}
	}
	MiniDialog.prototype.remove = function(){
		this.hide()
		this.$.remove()
	}

	function out(options){
		return new MiniDialog(options)
	}
	out.config = {
		defaultOptions:defaultOptions,
		otherOptions:otherOptions
	}
	out.alert = function(msg,okFunction,options,noRemoveWhenClose){
		return new MiniDialog($.extend({
			content:msg,
			buttons:[
				{
					'value':'确定',
					'click':function(e,miniDialogObjectInstance){
						okFunction.call(this,e,miniDialogObjectInstance)
						if(!noRemoveWhenClose){
							miniDialogObjectInstance.remove()
						}
					}
				}
			]
		},options))
	}
	out.confirm = function(msg,okFunction,cancelFunction,options,noRemoveWhenClose){
		return new MiniDialog($.extend({
			content:msg,
			buttons:[
				{
					'href' : options.okHref,
					'value': options.okValue ||'确定',
					'click':function(e,miniDialogObjectInstance){
						okFunction.call(this,e,miniDialogObjectInstance)
						if(!noRemoveWhenClose){
							miniDialogObjectInstance.remove()
						}
					}
				},
				{
					'value': options.cancelValue || '取消',
					'type':'secondary',
					'click':function(e,miniDialogObjectInstance){
						cancelFunction.call(this,e,miniDialogObjectInstance)
						if(!noRemoveWhenClose){
							miniDialogObjectInstance.remove()
						}
					}
				}
			]
		},options))
	}
	out.prompt = function(msg,defaultPrompt,okFunction,cancelFunction,options,noRemoveWhenClose){
		var inputClass = 'miniDialog_prompt_input'

		return new MiniDialog($.extend({
			content:['<div style="margin-bottom:5px">',msg,'</div><div><input class="',inputClass,'" style="width:175px;padding:6px 4px" value="',defaultPrompt,'"/></div>'].join(''),
			buttons:[
				{
					'value':'确定',
					'click':function(e,miniDialogObjectInstance){
						okFunction.call(this,e,miniDialogObjectInstance,miniDialogObjectInstance.$content.find('input.'+inputClass).val())

						if(!noRemoveWhenClose){
							miniDialogObjectInstance.remove();
						}
					}
				},
				{
					'value':'取消',
					'type':'secondary',
					'click':function(e,miniDialogObjectInstance){
						cancelFunction.call(this,e,miniDialogObjectInstance,miniDialogObjectInstance.$content.find('input.'+inputClass).val())
						if(!noRemoveWhenClose){
							miniDialogObjectInstance.remove()
						}
					}
				}
			]
		},options))
	}

	// $(new Image()).attr('src','b.png')
	window.miniDialog = out
})($,document,window)
define('jquery.datetimepicker',function(require,exports,module){
	/**
	 * @preserve jQuery DateTimePicker plugin v2.2.8
	 * @homepage http://xdsoft.net/jqplugins/datetimepicker/
	 * (c) 2014, Chupurnov Valeriy.
	 */
	(function( $ ) {
		'use strict';
		var default_options  = {
			i18n:{
				bg:{ // Bulgarian
					months:[
						"Януари", "Февруари", "Март", "Април", "Май", "Юни", "Юли", "Август", "Септември", "Октомври", "Ноември", "Декември"
					],
					dayOfWeek:[
						"Нд", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"
					]
				},
				fa:{ // Persian/Farsi
					months:[
						'فروردین', 'اردیبهشت', 'خرداد', 'تیر', 'مرداد', 'شهریور', 'مهر', 'آبان', 'آذر', 'دی', 'بهمن', 'اسفند'
					],
					dayOfWeek:[
						'یکشنبه', 'دوشنبه', 'سه شنبه', 'چهارشنبه', 'پنجشنبه', 'جمعه', 'شنبه'
					]
				},
				ru:{ // Russian
					months:[
						'Январь','Февраль','Март','Апрель','Май','Июнь','Июль','Август','Сентябрь','Октябрь','Ноябрь','Декабрь'
					],
					dayOfWeek:[
						"Вск", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"
					]
				},
				en:{ // English
					months: [
						"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
					],
					dayOfWeek: [
						"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
					]
				},
				el:{ // Ελληνικά
			                months: [
			                    "Ιανουάριος", "Φεβρουάριος", "Μάρτιος", "Απρίλιος", "Μάιος", "Ιούνιος", "Ιούλιος", "Αύγουστος", "Σεπτέμβριος", "Οκτώβριος", "Νοέμβριος", "Δεκέμβριος"
			                ],
				        dayOfWeek: [
			                    "Κυρ", "Δευ", "Τρι", "Τετ", "Πεμ", "Παρ", "Σαβ"
			                ]
			      },
				de:{ // German
					months:[
						'Januar','Februar','März','April','Mai','Juni','Juli','August','September','Oktober','November','Dezember'
					],
					dayOfWeek:[
						"So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"
					]
				},
				nl:{ // Dutch
					months:[
						"januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december"
					],
					dayOfWeek:[
						"zo", "ma", "di", "wo", "do", "vr", "za"
					]
				},
				tr:{ // Turkish
					months:[
						"Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"
					],
					dayOfWeek:[
						"Paz", "Pts", "Sal", "Çar", "Per", "Cum", "Cts"
					]
				},
				fr:{ //French
					months:[
				    "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"
					],
					dayOfWeek:[
						"Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam"
					]
				},
				es:{ // Spanish
					months: [
						"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
					],
					dayOfWeek: [
						"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"
					]
				},
				th:{ // Thai
					months:[
						'มกราคม','กุมภาพันธ์','มีนาคม','เมษายน','พฤษภาคม','มิถุนายน','กรกฎาคม','สิงหาคม','กันยายน','ตุลาคม','พฤศจิกายน','ธันวาคม'
					],
					dayOfWeek:[
						'อา.','จ.','อ.','พ.','พฤ.','ศ.','ส.'
					]
				},
				pl:{ // Polish
					months: [
						"styczeń", "luty", "marzec", "kwiecień", "maj", "czerwiec", "lipiec", "sierpień", "wrzesień", "październik", "listopad", "grudzień"
					],
					dayOfWeek: [
						"nd", "pn", "wt", "śr", "cz", "pt", "sb"
					]
				},
				pt:{ // Portuguese
					months: [
						"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
					],
					dayOfWeek: [
						"Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"
					]
				},
				ch:{ // Simplified Chinese
					months: [
						"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"
					],
					dayOfWeek: [
						"日", "一","二","三","四","五","六"
					]
				},
				se:{ // Swedish
					months: [
						"Januari", "Februari", "Mars", "April", "Maj", "Juni", "Juli", "Augusti", "September","Oktober", "November", "December"
					],
					dayOfWeek: [
						"Sön", "Mån", "Tis", "Ons", "Tor", "Fre", "Lör"
					]
				},
				kr:{ // Korean
					months: [
	                    "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"
					],
					dayOfWeek: [
	                    "일", "월", "화", "수", "목", "금", "토"
					]
				},
				it:{ // Italian
					months: [
						"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"
					],
					dayOfWeek: [
						"Dom", "Lun", "Mar", "Mer", "Gio", "Ven", "Sab"
					]
				},
				da:{ // Dansk
					months: [
						"January", "Februar", "Marts", "April", "Maj", "Juni", "July", "August", "September", "Oktober", "November", "December"
					],
					dayOfWeek: [
						"Søn", "Man", "Tir", "ons", "Tor", "Fre", "lør"
					]
				},
				ja:{ // Japanese
					months: [
						"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"
					],
					dayOfWeek: [
						"日", "月", "火", "水", "木", "金", "土"
					]
				},
				vi:{ // Vietnamese
					months: [
						"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
					],
					dayOfWeek: [
						"CN", "T2", "T3", "T4", "T5", "T6", "T7"
					]
				}
			},
			value:'',
			lang:'en',
			
			format:	'Y/m/d H:i',
			formatTime:	'H:i',
			formatDate:	'Y/m/d',
			
			startDate:	false, // new Date(), '1986/12/08', '-1970/01/05','-1970/01/05', 
			
			step:60,
			monthChangeSpinner:true,
			closeOnDateSelect:false,
			closeOnWithoutClick:true,
			
			timepicker:true,
			datepicker:true,
		
			minDate:false,
			maxDate:false,
			minTime:false,
			maxTime:false,
			
			allowTimes:[],
			opened:false,
			initTime:true,
			inline:false,
			
			onSelectDate:function() {},
			onSelectTime:function() {},
			onChangeMonth:function() {},
			onChangeDateTime:function() {},
			onShow:function() {},
			onClose:function() {},
			onGenerate:function() {},
			
			withoutCopyright:true,
			
			inverseButton:false,
			hours12:false,
			next:	'xdsoft_next',
			prev : 'xdsoft_prev',
			dayOfWeekStart:0,
			
			timeHeightInTimePicker:25,
			timepickerScrollbar:true,
			
			todayButton:true, // 2.1.0
			defaultSelect:true, // 2.1.0
			
			scrollMonth:true,
			scrollTime:true,
			scrollInput:false,
			
			lazyInit:false,
			
			mask:false,
			validateOnBlur:true,
			allowBlank:true,
			
			yearStart:1950,
			yearEnd:2050,
			
			style:'',
			id:'',
			
			roundTime:'round', // ceil, floor
			className:'',
			
			weekends	: 	[],
			yearOffset:0
		};
		
		// fix for ie8
		if ( !Array.prototype.indexOf ) {
			Array.prototype.indexOf = function(obj, start) {
				 for (var i = (start || 0), j = this.length; i < j; i++) {
					 if (this[i] === obj) { return i; }
				 }
				 return -1;
			}
		};
		
		Date.prototype.countDaysInMonth = function(){
			return new Date(this.getFullYear(), this.getMonth()+1, 0).getDate();
		};
		
		$.fn.xdsoftScroller = function( _percent ) {
			return this.each(function() {
				var timeboxparent = $(this);
				if( !$(this).hasClass('xdsoft_scroller_box') ) {
					var pointerEventToXY = function( e ) {
							var out = {x:0, y:0};
							if( e.type == 'touchstart' || e.type == 'touchmove' || e.type == 'touchend' || e.type == 'touchcancel' ) {
								var touch = e.originalEvent.touches[0] || e.originalEvent.changedTouches[0];
								out.x = touch.pageX;
								out.y = touch.pageY;
							}else if (e.type == 'mousedown' || e.type == 'mouseup' || e.type == 'mousemove' || e.type == 'mouseover'|| e.type=='mouseout' || e.type=='mouseenter' || e.type=='mouseleave') {
								out.x = e.pageX;
								out.y = e.pageY;
							}
							return out;
						},
						move = 0,
						timebox = timeboxparent.children().eq(0),
						parentHeight = timeboxparent[0].clientHeight,
						height = timebox[0].offsetHeight,
						scrollbar = $('<div class="xdsoft_scrollbar"></div>'),
						scroller = $('<div class="xdsoft_scroller"></div>'),
						maximumOffset = 100,
						start = false;
	
					scrollbar.append(scroller);
	
					timeboxparent.addClass('xdsoft_scroller_box').append(scrollbar);
					scroller.on('mousedown.xdsoft_scroller',function ( event ) {
						if( !parentHeight )
							timeboxparent.trigger('resize_scroll.xdsoft_scroller',[_percent]);
						var pageY = event.pageY,
							top = parseInt(scroller.css('margin-top')),
							h1 = scrollbar[0].offsetHeight;
						$(document.body).addClass('xdsoft_noselect');
						$([document.body,window]).on('mouseup.xdsoft_scroller',function arguments_callee() {
							$([document.body,window]).off('mouseup.xdsoft_scroller',arguments_callee)
								.off('mousemove.xdsoft_scroller',move)
								.removeClass('xdsoft_noselect');
						});
						$(document.body).on('mousemove.xdsoft_scroller',move = function(event) {
							var offset = event.pageY-pageY+top;
							if( offset<0 )
								offset = 0;
							if( offset+scroller[0].offsetHeight>h1 )
								offset = h1-scroller[0].offsetHeight;
							timeboxparent.trigger('scroll_element.xdsoft_scroller',[maximumOffset?offset/maximumOffset:0]);
						});
					});
	
					timeboxparent
						.on('scroll_element.xdsoft_scroller',function( event,percent ) {
							if( !parentHeight )
								timeboxparent.trigger('resize_scroll.xdsoft_scroller',[percent,true]);
							percent = percent>1?1:(percent<0||isNaN(percent))?0:percent;
							scroller.css('margin-top',maximumOffset*percent);
							timebox.css('marginTop',-parseInt((height-parentHeight)*percent))
						})
						.on('resize_scroll.xdsoft_scroller',function( event,_percent,noTriggerScroll ) {
							parentHeight = timeboxparent[0].clientHeight;
							height = timebox[0].offsetHeight;
							var percent = parentHeight/height,
								sh = percent*scrollbar[0].offsetHeight;
							if( percent>1 )
								scroller.hide();
							else{
								scroller.show();
								scroller.css('height',parseInt(sh>10?sh:10));
								maximumOffset = scrollbar[0].offsetHeight-scroller[0].offsetHeight;
								if( noTriggerScroll!==true )
									timeboxparent.trigger('scroll_element.xdsoft_scroller',[_percent?_percent:Math.abs(parseInt(timebox.css('marginTop')))/(height-parentHeight)]);
							}
						});
					timeboxparent.mousewheel&&timeboxparent.mousewheel(function(event, delta, deltaX, deltaY) {
						var top = Math.abs(parseInt(timebox.css('marginTop')));
						timeboxparent.trigger('scroll_element.xdsoft_scroller',[(top-delta*20)/(height-parentHeight)]);
						event.stopPropagation();
						return false;
					});
					timeboxparent.on('touchstart',function( event ) {
						start = pointerEventToXY(event);
					});
					timeboxparent.on('touchmove',function( event ) {
						if( start ) {
							var coord = pointerEventToXY(event), top = Math.abs(parseInt(timebox.css('marginTop')));
							timeboxparent.trigger('scroll_element.xdsoft_scroller',[(top-(coord.y-start.y))/(height-parentHeight)]);
							event.stopPropagation();
							event.preventDefault();
						};
					});
					timeboxparent.on('touchend touchcancel',function( event ) {
						start = false;
					});
				}
				timeboxparent.trigger('resize_scroll.xdsoft_scroller',[_percent]);
			});
		};
		$.fn.datetimepicker = function( opt ) {
			var KEY0 = 48,
				KEY9 = 57,
				_KEY0 = 96,
				_KEY9 = 105,
				CTRLKEY = 17,
				DEL = 46,
				ENTER = 13,
				ESC = 27,
				BACKSPACE = 8,
				ARROWLEFT = 37,
				ARROWUP = 38,
				ARROWRIGHT = 39,
				ARROWDOWN = 40,
				TAB = 9,
				F5 = 116,
				AKEY = 65,
				CKEY = 67,
				VKEY = 86,
				ZKEY = 90,
				YKEY = 89,
				ctrlDown	=	false,
				options = ($.isPlainObject(opt)||!opt)?$.extend(true,{},default_options,opt):$.extend({},default_options),
	
				lazyInitTimer = 0,
	
				lazyInit = function( input ){
					input
						.on('open.xdsoft focusin.xdsoft mousedown.xdsoft',function initOnActionCallback(event) {
							if( input.is(':disabled')||input.is(':hidden')||!input.is(':visible')||input.data( 'xdsoft_datetimepicker') )
								return;
					
							clearTimeout(lazyInitTimer);
							
							lazyInitTimer = setTimeout(function() {
	
								if( !input.data( 'xdsoft_datetimepicker') )
									createDateTimePicker(input);
									
								input
									.off('open.xdsoft focusin.xdsoft mousedown.xdsoft',initOnActionCallback)
									.trigger('open.xdsoft');
							},100);
							
						});
				},
				
				createDateTimePicker = function( input ) {
					
					var datetimepicker = $('<div '+(options.id?'id="'+options.id+'"':'')+' '+(options.style?'style="'+options.style+'"':'')+' class="xdsoft_datetimepicker xdsoft_noselect '+options.className+'"></div>'),
						xdsoft_copyright = $('<div class="xdsoft_copyright"><a target="_blank" href="http://xdsoft.net/jqplugins/datetimepicker/">xdsoft.net</a></div>'),
						datepicker = $('<div class="xdsoft_datepicker active"></div>'),
						mounth_picker = $('<div class="xdsoft_mounthpicker"><button type="button" class="xdsoft_prev"></button><button type="button" class="xdsoft_today_button"></button><div class="xdsoft_label xdsoft_month"><span></span></div><div class="xdsoft_label xdsoft_year"><span></span></div><button type="button" class="xdsoft_next"></button></div>'),
						calendar = $('<div class="xdsoft_calendar"></div>'),
						timepicker = $('<div class="xdsoft_timepicker active"><button type="button" class="xdsoft_prev"></button><div class="xdsoft_time_box"></div><button type="button" class="xdsoft_next"></button></div>'),
						timeboxparent = timepicker.find('.xdsoft_time_box').eq(0),
						timebox = $('<div class="xdsoft_time_variant"></div>'),
						scrollbar = $('<div class="xdsoft_scrollbar"></div>'),
						scroller = $('<div class="xdsoft_scroller"></div>'),
						monthselect =$('<div class="xdsoft_select xdsoft_monthselect"><div></div></div>'),
						yearselect =$('<div class="xdsoft_select xdsoft_yearselect"><div></div></div>');
	
					//constructor lego
					mounth_picker
						.find('.xdsoft_month span')
							.after(monthselect);
					mounth_picker
						.find('.xdsoft_year span')
							.after(yearselect);
	
					mounth_picker
						.find('.xdsoft_month,.xdsoft_year')
							.on('mousedown.xdsoft',function(event) {
								mounth_picker
									.find('.xdsoft_select')
										.hide();
										
								var select = $(this).find('.xdsoft_select').eq(0),
									val = 0,
									top = 0;
	
								if( _xdsoft_datetime.currentTime )
									val = _xdsoft_datetime.currentTime[$(this).hasClass('xdsoft_month')?'getMonth':'getFullYear']();
	
								select.show();
								
								for(var items = select.find('div.xdsoft_option'),i = 0;i<items.length;i++) {
									if( items.eq(i).data('value')==val ) {
										break;
									}else top+=items[0].offsetHeight;
								}
	
								select.xdsoftScroller(top/(select.children()[0].offsetHeight-(select[0].clientHeight)));
								event.stopPropagation();
								
								return false;
							});
	
					mounth_picker
						.find('.xdsoft_select')
							.xdsoftScroller()
							.on('mousedown.xdsoft',function( event ) {
								event.stopPropagation();
								event.preventDefault();
							})
							.on('mousedown.xdsoft','.xdsoft_option',function( event ) {
								if( _xdsoft_datetime&&_xdsoft_datetime.currentTime )
									_xdsoft_datetime.currentTime[$(this).parent().parent().hasClass('xdsoft_monthselect')?'setMonth':'setFullYear']($(this).data('value'));
								
								$(this).parent().parent().hide();
								
								datetimepicker.trigger('xchange.xdsoft');
								options.onChangeMonth&&options.onChangeMonth.call&&options.onChangeMonth.call(datetimepicker,_xdsoft_datetime.currentTime,datetimepicker.data('input'));
							});
	
	
					// set options
					datetimepicker.setOptions = function( _options ) {
						options = $.extend(true,{},options,_options);
						
						if( _options.allowTimes && $.isArray(_options.allowTimes) && _options.allowTimes.length ){
							options['allowTimes'] = $.extend(true,[],_options.allowTimes);
						}
						
						if( _options.weekends && $.isArray(_options.weekends) && _options.weekends.length ){
							options['weekends'] = $.extend(true,[],_options.weekends);
						}
						
						if( (options.open||options.opened)&&(!options.inline) ) {
							input.trigger('open.xdsoft');
						}
	
						if( options.inline ) {
							triggerAfterOpen = true;
							datetimepicker.addClass('xdsoft_inline');
							input.after(datetimepicker).hide();
						}
	
						if( options.inverseButton ) {
							options.next = 'xdsoft_prev';
							options.prev = 'xdsoft_next';
						}
	
						if( options.datepicker )
							datepicker.addClass('active');
						else
							datepicker.removeClass('active');
							
						if( options.timepicker )
							timepicker.addClass('active');
						else
							timepicker.removeClass('active');
	
						if( options.value ){
							input&&input.val&&input.val(options.value);
							_xdsoft_datetime.setCurrentTime(options.value);
						}
	
						if( isNaN(options.dayOfWeekStart)||parseInt(options.dayOfWeekStart)<0||parseInt(options.dayOfWeekStart)>6 )
							options.dayOfWeekStart = 0;
						else
							options.dayOfWeekStart = parseInt(options.dayOfWeekStart);
	
						if( !options.timepickerScrollbar )
							scrollbar.hide();
						
						if( options.minDate && /^-(.*)$/.test(options.minDate) ){
							options.minDate = _xdsoft_datetime.strToDateTime(options.minDate).dateFormat( options.formatDate );
						}
						
						if( options.maxDate &&  /^\+(.*)$/.test(options.maxDate) ) {
							options.maxDate = _xdsoft_datetime.strToDateTime(options.maxDate).dateFormat( options.formatDate );
						}
						
						mounth_picker
							.find('.xdsoft_today_button')
								.css('visibility',!options.todayButton?'hidden':'visible');
	
						if( options.mask ) {
							var e,
								getCaretPos = function ( input ) {
									try{
										if ( document.selection && document.selection.createRange ) {
											var range = document.selection.createRange();
											return range.getBookmark().charCodeAt(2) - 2;
										}else
											if ( input.setSelectionRange )
												return input.selectionStart;
									}catch(e) {
										return 0;
									}
								},
								setCaretPos = function ( node,pos ) {
									var node = (typeof node == "string" || node instanceof String) ? document.getElementById(node) : node;
									if(!node) {
										return false;
									}else if(node.createTextRange) {
										var textRange = node.createTextRange();
										textRange.collapse(true);
										textRange.moveEnd(pos);
										textRange.moveStart(pos);
										textRange.select();
										return true;
									}else if(node.setSelectionRange) {
										node.setSelectionRange(pos,pos);
										return true;
									}
									return false;
								},
								isValidValue = function ( mask,value ) {
									var reg = mask
										.replace(/([\[\]\/\{\}\(\)\-\.\+]{1})/g,'\\$1')
										.replace(/_/g,'{digit+}')
										.replace(/([0-9]{1})/g,'{digit$1}')
										.replace(/\{digit([0-9]{1})\}/g,'[0-$1_]{1}')
										.replace(/\{digit[\+]\}/g,'[0-9_]{1}');
									return RegExp(reg).test(value);
								};
							input.off('keydown.xdsoft');
							switch(true) {
								case ( options.mask===true ):
								
									options.mask = options.format
										.replace(/Y/g,'9999')
										.replace(/F/g,'9999')
										.replace(/m/g,'19')
										.replace(/d/g,'39')
										.replace(/H/g,'29')
										.replace(/i/g,'59')
										.replace(/s/g,'59');
										
								case ( $.type(options.mask) == 'string' ):
								
									if( !isValidValue( options.mask,input.val() ) )
										input.val(options.mask.replace(/[0-9]/g,'_'));
	
									input.on('keydown.xdsoft',function( event ) {
										var val = this.value,
											key = event.which;
											
										switch(true) {
											case (( key>=KEY0&&key<=KEY9 )||( key>=_KEY0&&key<=_KEY9 ))||(key==BACKSPACE||key==DEL):
												var pos = getCaretPos(this),
													digit = ( key!=BACKSPACE&&key!=DEL )?String.fromCharCode((_KEY0 <= key && key <= _KEY9)? key-KEY0 : key):'_';
												
												if( (key==BACKSPACE||key==DEL)&&pos ) {
													pos--;
													digit='_';
												}
												
												while( /[^0-9_]/.test(options.mask.substr(pos,1))&&pos<options.mask.length&&pos>0 )
													pos+=( key==BACKSPACE||key==DEL )?-1:1;
	
												val = val.substr(0,pos)+digit+val.substr(pos+1);
												if( $.trim(val)=='' ){
													val = options.mask.replace(/[0-9]/g,'_');
												}else{
													if( pos==options.mask.length )
														break;
												}
												
												pos+=(key==BACKSPACE||key==DEL)?0:1;
												while( /[^0-9_]/.test(options.mask.substr(pos,1))&&pos<options.mask.length&&pos>0 )
													pos+=(key==BACKSPACE||key==DEL)?-1:1;
													
												if( isValidValue( options.mask,val ) ) {
													this.value = val;
													setCaretPos(this,pos);
												}else if( $.trim(val)=='' )
													this.value = options.mask.replace(/[0-9]/g,'_');
												else{
													input.trigger('error_input.xdsoft');
												}
											break;
											case ( !!~([AKEY,CKEY,VKEY,ZKEY,YKEY].indexOf(key))&&ctrlDown ):
											 case !!~([ESC,ARROWUP,ARROWDOWN,ARROWLEFT,ARROWRIGHT,F5,CTRLKEY,TAB,ENTER].indexOf(key)):
											return true;
										}
										event.preventDefault();
										return false;
									});
								break;
							}
						}
						if( options.validateOnBlur ) {
							input
								.off('blur.xdsoft')
								.on('blur.xdsoft', function() {
									if( options.allowBlank && !$.trim($(this).val()).length ) {
										$(this).val(null);
										datetimepicker.data('xdsoft_datetime').empty();
									}else if( !Date.parseDate( $(this).val(), options.format ) ) {
										$(this).val((_xdsoft_datetime.now()).dateFormat( options.format ));
										datetimepicker.data('xdsoft_datetime').setCurrentTime($(this).val());
									}
									else{
										datetimepicker.data('xdsoft_datetime').setCurrentTime($(this).val());
	 								}
									datetimepicker.trigger('changedatetime.xdsoft');
								});
						}
						options.dayOfWeekStartPrev = (options.dayOfWeekStart==0)?6:options.dayOfWeekStart-1;
						
						datetimepicker
							.trigger('xchange.xdsoft')
							.trigger('afterOpen.xdsoft')
					};
	
					datetimepicker
						.data('options',options)
						.on('mousedown.xdsoft',function( event ) {
							event.stopPropagation();
							event.preventDefault();
							yearselect.hide();
							monthselect.hide();
							return false;
						});
	
					var scroll_element = timepicker.find('.xdsoft_time_box');
					scroll_element.append(timebox);
					scroll_element.xdsoftScroller();
					
					datetimepicker.on('afterOpen.xdsoft',function() {
						scroll_element.xdsoftScroller();
					});
	
					datetimepicker
						.append(datepicker)
						.append(timepicker);
	
					if( options.withoutCopyright!==true )
						datetimepicker
							.append(xdsoft_copyright);
	
					datepicker
						.append(mounth_picker)
						.append(calendar);
	
					$('body').append(datetimepicker);
	
					var _xdsoft_datetime = new function() {
						var _this = this;
						_this.now = function() {
							var d = new Date();
							if( options.yearOffset )
								d.setFullYear(d.getFullYear()+options.yearOffset);
							return d;
						};
	
						_this.currentTime = this.now();
						_this.isValidDate = function (d) {
							if ( Object.prototype.toString.call(d) !== "[object Date]" )
								return false;
							return !isNaN(d.getTime());
						};
	
						_this.setCurrentTime = function( dTime) {
							_this.currentTime = (typeof dTime == 'string')? _this.strToDateTime(dTime) : _this.isValidDate(dTime) ? dTime: _this.now();
							datetimepicker.trigger('xchange.xdsoft');
						};
	
						_this.empty = function() {
							_this.currentTime = null;
						};
	
						_this.getCurrentTime = function( dTime) {
							return _this.currentTime;
						};
	
						_this.nextMonth = function() {
							var month = _this.currentTime.getMonth()+1;
							if( month==12 ) {
								_this.currentTime.setFullYear(_this.currentTime.getFullYear()+1);
								month = 0;
							}
							_this.currentTime.setDate(
								Math.min(
									Date.daysInMonth[month],
									_this.currentTime.getDate()
								)
							);
							_this.currentTime.setMonth(month);
							options.onChangeMonth&&options.onChangeMonth.call&&options.onChangeMonth.call(datetimepicker,_xdsoft_datetime.currentTime,datetimepicker.data('input'));
							datetimepicker.trigger('xchange.xdsoft');
							return month;
						};
	
						_this.prevMonth = function() {
							var month = _this.currentTime.getMonth()-1;
							if( month==-1 ) {
								_this.currentTime.setFullYear(_this.currentTime.getFullYear()-1);
								month = 11;
							}
							_this.currentTime.setDate(
								Math.min(
									Date.daysInMonth[month],
									_this.currentTime.getDate()
								)
							);
							_this.currentTime.setMonth(month);
							options.onChangeMonth&&options.onChangeMonth.call&&options.onChangeMonth.call(datetimepicker,_xdsoft_datetime.currentTime,datetimepicker.data('input'));
							datetimepicker.trigger('xchange.xdsoft');
							return month;
						};
	
						_this.strToDateTime = function( sDateTime ) {
							var tmpDate = [],timeOffset,currentTime;
						
							if( ( tmpDate = /^(\+|\-)(.*)$/.exec(sDateTime) )  && ( tmpDate[2]=Date.parseDate(tmpDate[2], options.formatDate) ) ) {
								timeOffset = tmpDate[2].getTime()-(tmpDate[2].getTimezoneOffset())*60000;
								currentTime = new Date((_xdsoft_datetime.now()).getTime()+parseInt(tmpDate[1]+'1')*timeOffset);
							}else
								currentTime = sDateTime?Date.parseDate(sDateTime, options.format):_this.now();
								
							if( !_this.isValidDate(currentTime) )
								currentTime = _this.now();
								
							return currentTime;
						};
	
						_this.strtodate = function( sDate ) {
							var currentTime = sDate?Date.parseDate(sDate, options.formatDate):_this.now();
							if( !_this.isValidDate(currentTime) )
								currentTime = _this.now();
							return currentTime;
						};
	
						_this.strtotime = function( sTime ) {
							var currentTime = sTime?Date.parseDate(sTime, options.formatTime):_this.now();
							if( !_this.isValidDate(currentTime) )
								currentTime = _this.now();
							return currentTime;
						};
	
						_this.str = function() {
							return _this.currentTime.dateFormat(options.format);
						};
					};
					mounth_picker
						.find('.xdsoft_today_button')
							.on('mousedown.xdsoft',function() {
								datetimepicker.data('changed',true);
								_xdsoft_datetime.setCurrentTime(0);
								datetimepicker.trigger('afterOpen.xdsoft');
							}).on('dblclick.xdsoft',function(){
								input.val( _xdsoft_datetime.str() );
								datetimepicker.trigger('close.xdsoft');
							});
					mounth_picker
						.find('.xdsoft_prev,.xdsoft_next')
							.on('mousedown.xdsoft',function() {
								var $this = $(this),
									timer = 0,
									stop = false;
	
								(function arguments_callee1(v) {
									var month =  _xdsoft_datetime.currentTime.getMonth();
									if( $this.hasClass( options.next ) ) {
										_xdsoft_datetime.nextMonth();
									}else if( $this.hasClass( options.prev ) ) {
										_xdsoft_datetime.prevMonth();
									}
									if (options.monthChangeSpinner) {
										!stop&&(timer = setTimeout(arguments_callee1,v?v:100));
									}
								})(500);
	
								$([document.body,window]).on('mouseup.xdsoft',function arguments_callee2() {
									clearTimeout(timer);
									stop = true;
									$([document.body,window]).off('mouseup.xdsoft',arguments_callee2);
								});
							});
	
					timepicker
						.find('.xdsoft_prev,.xdsoft_next')
							.on('mousedown.xdsoft',function() {
								var $this = $(this),
									timer = 0,
									stop = false,
									period = 110;
								(function arguments_callee4(v) {
									var pheight = timeboxparent[0].clientHeight,
										height = timebox[0].offsetHeight,
										top = Math.abs(parseInt(timebox.css('marginTop')));
									if( $this.hasClass(options.next) && (height-pheight)- options.timeHeightInTimePicker>=top ) {
										timebox.css('marginTop','-'+(top+options.timeHeightInTimePicker)+'px')
									}else if( $this.hasClass(options.prev) && top-options.timeHeightInTimePicker>=0  ) {
										timebox.css('marginTop','-'+(top-options.timeHeightInTimePicker)+'px')
									}
									timeboxparent.trigger('scroll_element.xdsoft_scroller',[Math.abs(parseInt(timebox.css('marginTop'))/(height-pheight))]);
									period= ( period>10 )?10:period-10;
									!stop&&(timer = setTimeout(arguments_callee4,v?v:period));
								})(500);
								$([document.body,window]).on('mouseup.xdsoft',function arguments_callee5() {
									clearTimeout(timer);
									stop = true;
									$([document.body,window])
										.off('mouseup.xdsoft',arguments_callee5);
								});
							});
	
					var xchangeTimer = 0;
					// base handler - generating a calendar and timepicker
					datetimepicker
						.on('xchange.xdsoft',function( event ) {
							clearTimeout(xchangeTimer);
							xchangeTimer = setTimeout(function(){
								var table 	=	'',
										start	= new Date(_xdsoft_datetime.currentTime.getFullYear(),_xdsoft_datetime.currentTime.getMonth(),1, 12, 0, 0),
										i = 0,
										today = _xdsoft_datetime.now();
									
									while( start.getDay()!=options.dayOfWeekStart )
										start.setDate(start.getDate()-1);
	
									//generate calendar
									table+='<table><thead><tr>';
	
									// days
									for(var j = 0; j<7; j++) {
										table+='<th>'+options.i18n[options.lang].dayOfWeek[(j+options.dayOfWeekStart)>6?0:j+options.dayOfWeekStart]+'</th>';
									}
	
									table+='</tr></thead>';
									table+='<tbody><tr>';
									var maxDate = false, minDate = false;
									
									if( options.maxDate!==false ) {
										maxDate = _xdsoft_datetime.strtodate(options.maxDate);
										maxDate = new Date(maxDate.getFullYear(),maxDate.getMonth(),maxDate.getDate(),23,59,59,999);
									}
									
									if( options.minDate!==false ) {
										minDate = _xdsoft_datetime.strtodate(options.minDate);
										minDate = new Date(minDate.getFullYear(),minDate.getMonth(),minDate.getDate());
									}
									
									var d,y,m,classes = [];
									
									while( i<_xdsoft_datetime.currentTime.countDaysInMonth()||start.getDay()!=options.dayOfWeekStart||_xdsoft_datetime.currentTime.getMonth()==start.getMonth() ) {
										classes = [];
										i++;
	
										d = start.getDate(); y = start.getFullYear(); m = start.getMonth();
	
										classes.push('xdsoft_date');
	
										if( ( maxDate!==false && start > maxDate )||(  minDate!==false && start < minDate ) ){
											classes.push('xdsoft_disabled');
										}
	
										if( _xdsoft_datetime.currentTime.getMonth()!=m ){
											classes.push('xdsoft_other_month');
										}
	
										if( (options.defaultSelect||datetimepicker.data('changed')) && _xdsoft_datetime.currentTime.dateFormat( options.formatDate )==start.dateFormat( options.formatDate ) ) {
											classes.push('xdsoft_current');
										}
	
										if( today.dateFormat( options.formatDate )==start.dateFormat( options.formatDate ) ) {
											classes.push('xdsoft_today');
										}
	
										if( start.getDay()==0||start.getDay()==6||~options.weekends.indexOf(start.dateFormat( options.formatDate )) ) {
											classes.push('xdsoft_weekend');
										}
	
										if(options.beforeShowDay && typeof options.beforeShowDay == 'function')
	                                    					{
	                                        					classes.push(options.beforeShowDay(start))
	                                    					}
	
										table+='<td data-date="'+d+'" data-month="'+m+'" data-year="'+y+'"'+' class="xdsoft_date xdsoft_day_of_week'+start.getDay()+' '+ classes.join(' ')+'">'+
													'<div>'+d+'</div>'+
												'</td>';
	
										if( start.getDay()==options.dayOfWeekStartPrev ) {
											table+='</tr>';
										}
	
										start.setDate(d+1);
									}
									table+='</tbody></table>';
	
									calendar.html(table);
	
									mounth_picker.find('.xdsoft_label span').eq(0).text(options.i18n[options.lang].months[_xdsoft_datetime.currentTime.getMonth()]);
									mounth_picker.find('.xdsoft_label span').eq(1).text(_xdsoft_datetime.currentTime.getFullYear());
	
									// generate timebox
									var time = '',
										h = '',
										m ='',
										line_time = function line_time( h,m ) {
											var now = _xdsoft_datetime.now();
											now.setHours(h);
											h = parseInt(now.getHours());
											now.setMinutes(m);
											m = parseInt(now.getMinutes());
	
											classes = [];
											if( (options.maxTime!==false&&_xdsoft_datetime.strtotime(options.maxTime).getTime()<now.getTime())||(options.minTime!==false&&_xdsoft_datetime.strtotime(options.minTime).getTime()>now.getTime()))
												classes.push('xdsoft_disabled');
											if( (options.initTime||options.defaultSelect||datetimepicker.data('changed')) && parseInt(_xdsoft_datetime.currentTime.getHours())==parseInt(h)&&(options.step>59||Math[options.roundTime](_xdsoft_datetime.currentTime.getMinutes()/options.step)*options.step==parseInt(m))) {
												if( options.defaultSelect||datetimepicker.data('changed')) {
													classes.push('xdsoft_current');
												} else if( options.initTime ) {
													classes.push('xdsoft_init_time');
												}
											}
											if( parseInt(today.getHours())==parseInt(h)&&parseInt(today.getMinutes())==parseInt(m))
												classes.push('xdsoft_today');
											time+= '<div class="xdsoft_time '+classes.join(' ')+'" data-hour="'+h+'" data-minute="'+m+'">'+now.dateFormat(options.formatTime)+'</div>';
										};
	
									if( !options.allowTimes || !$.isArray(options.allowTimes) || !options.allowTimes.length ) {
										for( var i=0,j=0;i<(options.hours12?12:24);i++ ) {
											for( j=0;j<60;j+=options.step ) {
												h = (i<10?'0':'')+i;
												m = (j<10?'0':'')+j;
												line_time( h,m );
											}
										}
									}else{
										for( var i=0;i<options.allowTimes.length;i++ ) {
											h = _xdsoft_datetime.strtotime(options.allowTimes[i]).getHours();
											m = _xdsoft_datetime.strtotime(options.allowTimes[i]).getMinutes();
											line_time( h,m );
										}
									}
	
									timebox.html(time);
	
									var opt = '',
										i = 0;
	
									for( i = parseInt(options.yearStart,10)+options.yearOffset;i<= parseInt(options.yearEnd,10)+options.yearOffset;i++ ) {
										opt+='<div class="xdsoft_option '+(_xdsoft_datetime.currentTime.getFullYear()==i?'xdsoft_current':'')+'" data-value="'+i+'">'+i+'</div>';
									}
									yearselect.children().eq(0)
															.html(opt);
	
									for( i = 0,opt = '';i<= 11;i++ ) {
										opt+='<div class="xdsoft_option '+(_xdsoft_datetime.currentTime.getMonth()==i?'xdsoft_current':'')+'" data-value="'+i+'">'+options.i18n[options.lang].months[i]+'</div>';
									}
									monthselect.children().eq(0).html(opt);
									$(datetimepicker)
										.trigger('generate.xdsoft');
							},10);
							event.stopPropagation();
						})
						.on('afterOpen.xdsoft',function() {
							if( options.timepicker ) {
								var classType;
								if( timebox.find('.xdsoft_current').length ) {
									classType = '.xdsoft_current';
								} else if( timebox.find('.xdsoft_init_time').length ) {
									classType = '.xdsoft_init_time';
								}
								
								if( classType ) {
									var pheight = timeboxparent[0].clientHeight,
										height = timebox[0].offsetHeight,
										top = timebox.find(classType).index()*options.timeHeightInTimePicker+1;
									if( (height-pheight)<top )
										top = height-pheight;
									timeboxparent.trigger('scroll_element.xdsoft_scroller',[parseInt(top)/(height-pheight)]);
								}else{
									timeboxparent.trigger('scroll_element.xdsoft_scroller',[0]);
								}
							}
						});
					
					var timerclick = 0;
					
					calendar
						.on('click.xdsoft', 'td', function (xdevent) {
						  xdevent.stopPropagation();  // Prevents closing of Pop-ups, Modals and Flyouts in Bootstrap
							timerclick++;
							var $this = $(this),
								currentTime = _xdsoft_datetime.currentTime;
							if( $this.hasClass('xdsoft_disabled') )
								return false;
	
							currentTime.setDate( 1 );
							currentTime.setFullYear( $this.data('year') );
							currentTime.setMonth( $this.data('month') );
							currentTime.setDate( $this.data('date') );
							
							datetimepicker.trigger('select.xdsoft',[currentTime]);
	
							input.val( _xdsoft_datetime.str() );
							if( (timerclick>1||(options.closeOnDateSelect===true||( options.closeOnDateSelect===0&&!options.timepicker )))&&!options.inline ) {
								datetimepicker.trigger('close.xdsoft');
							}
	
							if( options.onSelectDate &&	options.onSelectDate.call ) {
								options.onSelectDate.call(datetimepicker,_xdsoft_datetime.currentTime,datetimepicker.data('input'));
							}
	
							datetimepicker.data('changed',true);
							datetimepicker.trigger('xchange.xdsoft');
							datetimepicker.trigger('changedatetime.xdsoft');
							setTimeout(function(){
								timerclick = 0;
							},200);
						});
	
					timebox
						.on('click.xdsoft', 'div', function (xdevent) {
						    xdevent.stopPropagation(); // NAJ: Prevents closing of Pop-ups, Modals and Flyouts
							var $this = $(this),
								currentTime = _xdsoft_datetime.currentTime;
							if( $this.hasClass('xdsoft_disabled') )
								return false;
							currentTime.setHours($this.data('hour'));
							currentTime.setMinutes($this.data('minute'));
							datetimepicker.trigger('select.xdsoft',[currentTime]);
	
							datetimepicker.data('input').val( _xdsoft_datetime.str() );
	
							!options.inline&&datetimepicker.trigger('close.xdsoft');
	
							if( options.onSelectTime&&options.onSelectTime.call ) {
								options.onSelectTime.call(datetimepicker,_xdsoft_datetime.currentTime,datetimepicker.data('input'));
							}
							datetimepicker.data('changed',true);
							datetimepicker.trigger('xchange.xdsoft');
							datetimepicker.trigger('changedatetime.xdsoft');
						});
	
					datetimepicker.mousewheel&&datepicker.mousewheel(function(event, delta, deltaX, deltaY) {
						if( !options.scrollMonth )
							return true;
						if( delta<0 )
							_xdsoft_datetime.nextMonth();
						else
							_xdsoft_datetime.prevMonth();
						return false;
					});
	
					datetimepicker.mousewheel&&timeboxparent.unmousewheel().mousewheel(function(event, delta, deltaX, deltaY) {
						if( !options.scrollTime )
							return true;
						var pheight = timeboxparent[0].clientHeight,
							height = timebox[0].offsetHeight,
							top = Math.abs(parseInt(timebox.css('marginTop'))),
							fl = true;
						if( delta<0 && (height-pheight)-options.timeHeightInTimePicker>=top ) {
							timebox.css('marginTop','-'+(top+options.timeHeightInTimePicker)+'px');
							fl = false;
						}else if( delta>0&&top-options.timeHeightInTimePicker>=0 ) {
							timebox.css('marginTop','-'+(top-options.timeHeightInTimePicker)+'px');
							fl = false;
						}
						timeboxparent.trigger('scroll_element.xdsoft_scroller',[Math.abs(parseInt(timebox.css('marginTop'))/(height-pheight))]);
						event.stopPropagation();
						return fl;
					});
					
					var triggerAfterOpen = false;
					datetimepicker
						.on('changedatetime.xdsoft',function() {
							if( options.onChangeDateTime&&options.onChangeDateTime.call ) {
								var $input = datetimepicker.data('input');
								options.onChangeDateTime.call(datetimepicker, _xdsoft_datetime.currentTime, $input);
								$input.trigger('change');
							}
						})
						.on('generate.xdsoft',function() {
							if( options.onGenerate&&options.onGenerate.call )
								options.onGenerate.call(datetimepicker,_xdsoft_datetime.currentTime,datetimepicker.data('input'));
							if( triggerAfterOpen ){
								datetimepicker.trigger('afterOpen.xdsoft');
								triggerAfterOpen = false;
							}
						})
						.on( 'click.xdsoft', function( xdevent )
						{
							xdevent.stopPropagation();  // Prevents closing of Pop-ups, Modals and Flyouts in Bootstrap
						});
	
					var current_time_index = 0;
					input.mousewheel&&input.mousewheel(function( event, delta, deltaX, deltaY ) {
						if( !options.scrollInput )
							return true;
						if( !options.datepicker && options.timepicker ) {
							current_time_index = timebox.find('.xdsoft_current').length?timebox.find('.xdsoft_current').eq(0).index():0;
							if( current_time_index+delta>=0&&current_time_index+delta<timebox.children().length )
								current_time_index+=delta;
							timebox.children().eq(current_time_index).length&&timebox.children().eq(current_time_index).trigger('mousedown');
							return false;
						}else if( options.datepicker && !options.timepicker ) {
							datepicker.trigger( event, [delta, deltaX, deltaY]);
							input.val&&input.val( _xdsoft_datetime.str() );
							datetimepicker.trigger('changedatetime.xdsoft');
							return false;
						}
					});
					var setPos = function() {
						var offset = datetimepicker.data('input').offset(), top = offset.top+datetimepicker.data('input')[0].offsetHeight-1, left = offset.left;
						if( top+datetimepicker[0].offsetHeight>$(window).height()+$(window).scrollTop() )
							top = offset.top-datetimepicker[0].offsetHeight+1;
							if (top < 0)
								top = 0;
						if( left+datetimepicker[0].offsetWidth>$(window).width() )
							left = offset.left-datetimepicker[0].offsetWidth+datetimepicker.data('input')[0].offsetWidth;
						datetimepicker.css({
							left:left,
							top:top
						});
					};
					datetimepicker
						.on('open.xdsoft', function() {
							var onShow = true;
							if( options.onShow&&options.onShow.call) {
								onShow = options.onShow.call(datetimepicker,_xdsoft_datetime.currentTime,datetimepicker.data('input'));
							}
							if( onShow!==false ) {
								datetimepicker.show();
								setPos();
								$(window)
									.off('resize.xdsoft',setPos)
									.on('resize.xdsoft',setPos);
	
								if( options.closeOnWithoutClick ) {
									$([document.body,window]).on('mousedown.xdsoft',function arguments_callee6() {
										datetimepicker.trigger('close.xdsoft');
										$([document.body,window]).off('mousedown.xdsoft',arguments_callee6);
									});
								}
							}
						})
						.on('close.xdsoft', function( event ) {
							var onClose = true;
							if( options.onClose&&options.onClose.call ) {
								onClose=options.onClose.call(datetimepicker,_xdsoft_datetime.currentTime,datetimepicker.data('input'));
							}
							if( onClose!==false&&!options.opened&&!options.inline ) {
								datetimepicker.hide();
							}
							event.stopPropagation();
						})
						.data('input',input);
	
					var timer = 0,
						timer1 = 0;
	
					datetimepicker.data('xdsoft_datetime',_xdsoft_datetime);
					datetimepicker.setOptions(options);
					
					function getCurrentValue(){
						var ct = options.value?options.value:(input&&input.val&&input.val())?input.val():'';
					
						if( ct && _xdsoft_datetime.isValidDate(ct = Date.parseDate(ct, options.format)) ) {
							datetimepicker.data('changed',true);
						}else
							ct = '';
						
						if( !ct && options.startDate!==false ){
							ct = _xdsoft_datetime.strToDateTime(options.startDate);
						}
						
						return ct?ct:0;
					}
					
					_xdsoft_datetime.setCurrentTime( getCurrentValue() );
	
					input
						.data( 'xdsoft_datetimepicker',datetimepicker )
						.on('open.xdsoft focusin.xdsoft mousedown.xdsoft',function(event) {
							if( input.is(':disabled')||input.is(':hidden')||!input.is(':visible') )
								return;
							clearTimeout(timer);
							timer = setTimeout(function() {
								if( input.is(':disabled')||input.is(':hidden')||!input.is(':visible') )
									return;
									
								triggerAfterOpen = true;
								_xdsoft_datetime.setCurrentTime(getCurrentValue());
								
								datetimepicker.trigger('open.xdsoft');
							},100);
						})
						.on('keydown.xdsoft',function( event ) {
							var val = this.value,
								key = event.which;
							switch(true) {
								case !!~([ENTER].indexOf(key)):
									var elementSelector = $("input:visible,textarea:visible");
									datetimepicker.trigger('close.xdsoft');
									elementSelector.eq(elementSelector.index(this) + 1).focus();
								return false;
								case !!~[TAB].indexOf(key):
									datetimepicker.trigger('close.xdsoft');
								return true;
							}
						});
				},
				destroyDateTimePicker = function( input ) {
					var datetimepicker = input.data('xdsoft_datetimepicker');
					if( datetimepicker ) {
						datetimepicker.data('xdsoft_datetime',null);
						datetimepicker.remove();
						input
							.data( 'xdsoft_datetimepicker',null )
							.off( 'open.xdsoft focusin.xdsoft focusout.xdsoft mousedown.xdsoft blur.xdsoft keydown.xdsoft' );
						$(window).off('resize.xdsoft');
						$([window,document.body]).off('mousedown.xdsoft');
						input.unmousewheel&&input.unmousewheel();
					}
				};
			$(document)
				.off('keydown.xdsoftctrl keyup.xdsoftctrl')
				.on('keydown.xdsoftctrl',function(e) {
					if ( e.keyCode == CTRLKEY )
						ctrlDown = true;
				})
				.on('keyup.xdsoftctrl',function(e) {
					if ( e.keyCode == CTRLKEY )
						ctrlDown = false;
				});
			return this.each(function() {
				var datetimepicker;
				if( datetimepicker = $(this).data('xdsoft_datetimepicker') ) {
					if( $.type(opt) === 'string' ) {
						switch(opt) {
							case 'show':
								$(this).select().focus();
								datetimepicker.trigger( 'open.xdsoft' );
							break;
							case 'hide':
								datetimepicker.trigger('close.xdsoft');
							break;
							case 'destroy':
								destroyDateTimePicker($(this));
							break;
							case 'reset':
								this.value = this.defaultValue;
								if(!this.value || !datetimepicker.data('xdsoft_datetime').isValidDate(Date.parseDate(this.value, options.format)))
									datetimepicker.data('changed',false);
								datetimepicker.data('xdsoft_datetime').setCurrentTime(this.value);
							break;
						}
					}else{
						datetimepicker
							.setOptions(opt);
					}
					return 0;
				}else
					if( ($.type(opt) !== 'string') ){
						if( !options.lazyInit||options.open||options.inline ){
							createDateTimePicker($(this));
						}else
							lazyInit($(this));
					}
			});
		};
	})( jQuery );
	
	/*
	 * Copyright (c) 2013 Brandon Aaron (http://brandonaaron.net)
	 *
	 * Licensed under the MIT License (LICENSE.txt).
	 *
	 * Thanks to: http://adomas.org/javascript-mouse-wheel/ for some pointers.
	 * Thanks to: Mathias Bank(http://www.mathias-bank.de) for a scope bug fix.
	 * Thanks to: Seamus Leahy for adding deltaX and deltaY
	 *
	 * Version: 3.1.3
	 *
	 * Requires: 1.2.2+
	 */
	(function(factory) {if(typeof define==='function'&&define.amd) {define(['jquery'],factory)}else if(typeof exports==='object') {module.exports=factory}else{factory(jQuery)}}(function($) {var toFix=['wheel','mousewheel','DOMMouseScroll','MozMousePixelScroll'];var toBind='onwheel'in document||document.documentMode>=9?['wheel']:['mousewheel','DomMouseScroll','MozMousePixelScroll'];var lowestDelta,lowestDeltaXY;if($.event.fixHooks) {for(var i=toFix.length;i;) {$.event.fixHooks[toFix[--i]]=$.event.mouseHooks}}$.event.special.mousewheel={setup:function() {if(this.addEventListener) {for(var i=toBind.length;i;) {this.addEventListener(toBind[--i],handler,false)}}else{this.onmousewheel=handler}},teardown:function() {if(this.removeEventListener) {for(var i=toBind.length;i;) {this.removeEventListener(toBind[--i],handler,false)}}else{this.onmousewheel=null}}};$.fn.extend({mousewheel:function(fn) {return fn?this.bind("mousewheel",fn):this.trigger("mousewheel")},unmousewheel:function(fn) {return this.unbind("mousewheel",fn)}});function handler(event) {var orgEvent=event||window.event,args=[].slice.call(arguments,1),delta=0,deltaX=0,deltaY=0,absDelta=0,absDeltaXY=0,fn;event=$.event.fix(orgEvent);event.type="mousewheel";if(orgEvent.wheelDelta) {delta=orgEvent.wheelDelta}if(orgEvent.detail) {delta=orgEvent.detail*-1}if(orgEvent.deltaY) {deltaY=orgEvent.deltaY*-1;delta=deltaY}if(orgEvent.deltaX) {deltaX=orgEvent.deltaX;delta=deltaX*-1}if(orgEvent.wheelDeltaY!==undefined) {deltaY=orgEvent.wheelDeltaY}if(orgEvent.wheelDeltaX!==undefined) {deltaX=orgEvent.wheelDeltaX*-1}absDelta=Math.abs(delta);if(!lowestDelta||absDelta<lowestDelta) {lowestDelta=absDelta}absDeltaXY=Math.max(Math.abs(deltaY),Math.abs(deltaX));if(!lowestDeltaXY||absDeltaXY<lowestDeltaXY) {lowestDeltaXY=absDeltaXY}fn=delta>0?'floor':'ceil';delta=Math[fn](delta/lowestDelta);deltaX=Math[fn](deltaX/lowestDeltaXY);deltaY=Math[fn](deltaY/lowestDeltaXY);args.unshift(event,delta,deltaX,deltaY);return($.event.dispatch||$.event.handle).apply(this,args)}}));
	
	
	// Parse and Format Library
	//http://www.xaprb.com/blog/2005/12/12/javascript-closures-for-runtime-efficiency/
	/*
	 * Copyright (C) 2004 Baron Schwartz <baron at sequent dot org>
	 *
	 * This program is free software; you can redistribute it and/or modify it
	 * under the terms of the GNU Lesser General Public License as published by the
	 * Free Software Foundation, version 2.1.
	 *
	 * This program is distributed in the hope that it will be useful, but WITHOUT
	 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
	 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more
	 * details.
	 */
	Date.parseFunctions={count:0};Date.parseRegexes=[];Date.formatFunctions={count:0};Date.prototype.dateFormat=function(b){if(b=="unixtime"){return parseInt(this.getTime()/1000);}if(Date.formatFunctions[b]==null){Date.createNewFormat(b);}var a=Date.formatFunctions[b];return this[a]();};Date.createNewFormat=function(format){var funcName="format"+Date.formatFunctions.count++;Date.formatFunctions[format]=funcName;var code="Date.prototype."+funcName+" = function() {return ";var special=false;var ch="";for(var i=0;i<format.length;++i){ch=format.charAt(i);if(!special&&ch=="\\"){special=true;}else{if(special){special=false;code+="'"+String.escape(ch)+"' + ";}else{code+=Date.getFormatCode(ch);}}}eval(code.substring(0,code.length-3)+";}");};Date.getFormatCode=function(a){switch(a){case"d":return"String.leftPad(this.getDate(), 2, '0') + ";case"D":return"Date.dayNames[this.getDay()].substring(0, 3) + ";case"j":return"this.getDate() + ";case"l":return"Date.dayNames[this.getDay()] + ";case"S":return"this.getSuffix() + ";case"w":return"this.getDay() + ";case"z":return"this.getDayOfYear() + ";case"W":return"this.getWeekOfYear() + ";case"F":return"Date.monthNames[this.getMonth()] + ";case"m":return"String.leftPad(this.getMonth() + 1, 2, '0') + ";case"M":return"Date.monthNames[this.getMonth()].substring(0, 3) + ";case"n":return"(this.getMonth() + 1) + ";case"t":return"this.getDaysInMonth() + ";case"L":return"(this.isLeapYear() ? 1 : 0) + ";case"Y":return"this.getFullYear() + ";case"y":return"('' + this.getFullYear()).substring(2, 4) + ";case"a":return"(this.getHours() < 12 ? 'am' : 'pm') + ";case"A":return"(this.getHours() < 12 ? 'AM' : 'PM') + ";case"g":return"((this.getHours() %12) ? this.getHours() % 12 : 12) + ";case"G":return"this.getHours() + ";case"h":return"String.leftPad((this.getHours() %12) ? this.getHours() % 12 : 12, 2, '0') + ";case"H":return"String.leftPad(this.getHours(), 2, '0') + ";case"i":return"String.leftPad(this.getMinutes(), 2, '0') + ";case"s":return"String.leftPad(this.getSeconds(), 2, '0') + ";case"O":return"this.getGMTOffset() + ";case"T":return"this.getTimezone() + ";case"Z":return"(this.getTimezoneOffset() * -60) + ";default:return"'"+String.escape(a)+"' + ";}};Date.parseDate=function(a,c){if(c=="unixtime"){return new Date(!isNaN(parseInt(a))?parseInt(a)*1000:0);}if(Date.parseFunctions[c]==null){Date.createParser(c);}var b=Date.parseFunctions[c];return Date[b](a);};Date.createParser=function(format){var funcName="parse"+Date.parseFunctions.count++;var regexNum=Date.parseRegexes.length;var currentGroup=1;Date.parseFunctions[format]=funcName;var code="Date."+funcName+" = function(input) {\nvar y = -1, m = -1, d = -1, h = -1, i = -1, s = -1, z = -1;\nvar d = new Date();\ny = d.getFullYear();\nm = d.getMonth();\nd = d.getDate();\nvar results = input.match(Date.parseRegexes["+regexNum+"]);\nif (results && results.length > 0) {";var regex="";var special=false;var ch="";for(var i=0;i<format.length;++i){ch=format.charAt(i);if(!special&&ch=="\\"){special=true;}else{if(special){special=false;regex+=String.escape(ch);}else{obj=Date.formatCodeToRegex(ch,currentGroup);currentGroup+=obj.g;regex+=obj.s;if(obj.g&&obj.c){code+=obj.c;}}}}code+="if (y > 0 && z > 0){\nvar doyDate = new Date(y,0);\ndoyDate.setDate(z);\nm = doyDate.getMonth();\nd = doyDate.getDate();\n}";code+="if (y > 0 && m >= 0 && d > 0 && h >= 0 && i >= 0 && s >= 0)\n{return new Date(y, m, d, h, i, s);}\nelse if (y > 0 && m >= 0 && d > 0 && h >= 0 && i >= 0)\n{return new Date(y, m, d, h, i);}\nelse if (y > 0 && m >= 0 && d > 0 && h >= 0)\n{return new Date(y, m, d, h);}\nelse if (y > 0 && m >= 0 && d > 0)\n{return new Date(y, m, d);}\nelse if (y > 0 && m >= 0)\n{return new Date(y, m);}\nelse if (y > 0)\n{return new Date(y);}\n}return null;}";Date.parseRegexes[regexNum]=new RegExp("^"+regex+"$");eval(code);};Date.formatCodeToRegex=function(b,a){switch(b){case"D":return{g:0,c:null,s:"(?:Sun|Mon|Tue|Wed|Thu|Fri|Sat)"};case"j":case"d":return{g:1,c:"d = parseInt(results["+a+"], 10);\n",s:"(\\d{1,2})"};case"l":return{g:0,c:null,s:"(?:"+Date.dayNames.join("|")+")"};case"S":return{g:0,c:null,s:"(?:st|nd|rd|th)"};case"w":return{g:0,c:null,s:"\\d"};case"z":return{g:1,c:"z = parseInt(results["+a+"], 10);\n",s:"(\\d{1,3})"};case"W":return{g:0,c:null,s:"(?:\\d{2})"};case"F":return{g:1,c:"m = parseInt(Date.monthNumbers[results["+a+"].substring(0, 3)], 10);\n",s:"("+Date.monthNames.join("|")+")"};case"M":return{g:1,c:"m = parseInt(Date.monthNumbers[results["+a+"]], 10);\n",s:"(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)"};case"n":case"m":return{g:1,c:"m = parseInt(results["+a+"], 10) - 1;\n",s:"(\\d{1,2})"};case"t":return{g:0,c:null,s:"\\d{1,2}"};case"L":return{g:0,c:null,s:"(?:1|0)"};case"Y":return{g:1,c:"y = parseInt(results["+a+"], 10);\n",s:"(\\d{4})"};case"y":return{g:1,c:"var ty = parseInt(results["+a+"], 10);\ny = ty > Date.y2kYear ? 1900 + ty : 2000 + ty;\n",s:"(\\d{1,2})"};case"a":return{g:1,c:"if (results["+a+"] == 'am') {\nif (h == 12) { h = 0; }\n} else { if (h < 12) { h += 12; }}",s:"(am|pm)"};case"A":return{g:1,c:"if (results["+a+"] == 'AM') {\nif (h == 12) { h = 0; }\n} else { if (h < 12) { h += 12; }}",s:"(AM|PM)"};case"g":case"G":case"h":case"H":return{g:1,c:"h = parseInt(results["+a+"], 10);\n",s:"(\\d{1,2})"};case"i":return{g:1,c:"i = parseInt(results["+a+"], 10);\n",s:"(\\d{2})"};case"s":return{g:1,c:"s = parseInt(results["+a+"], 10);\n",s:"(\\d{2})"};case"O":return{g:0,c:null,s:"[+-]\\d{4}"};case"T":return{g:0,c:null,s:"[A-Z]{3}"};case"Z":return{g:0,c:null,s:"[+-]\\d{1,5}"};default:return{g:0,c:null,s:String.escape(b)};}};Date.prototype.getTimezone=function(){return this.toString().replace(/^.*? ([A-Z]{3}) [0-9]{4}.*$/,"$1").replace(/^.*?\(([A-Z])[a-z]+ ([A-Z])[a-z]+ ([A-Z])[a-z]+\)$/,"$1$2$3");};Date.prototype.getGMTOffset=function(){return(this.getTimezoneOffset()>0?"-":"+")+String.leftPad(Math.floor(Math.abs(this.getTimezoneOffset())/60),2,"0")+String.leftPad(Math.abs(this.getTimezoneOffset())%60,2,"0");};Date.prototype.getDayOfYear=function(){var a=0;Date.daysInMonth[1]=this.isLeapYear()?29:28;for(var b=0;b<this.getMonth();++b){a+=Date.daysInMonth[b];}return a+this.getDate();};Date.prototype.getWeekOfYear=function(){var b=this.getDayOfYear()+(4-this.getDay());var a=new Date(this.getFullYear(),0,1);var c=(7-a.getDay()+4);return String.leftPad(Math.ceil((b-c)/7)+1,2,"0");};Date.prototype.isLeapYear=function(){var a=this.getFullYear();return((a&3)==0&&(a%100||(a%400==0&&a)));};Date.prototype.getFirstDayOfMonth=function(){var a=(this.getDay()-(this.getDate()-1))%7;return(a<0)?(a+7):a;};Date.prototype.getLastDayOfMonth=function(){var a=(this.getDay()+(Date.daysInMonth[this.getMonth()]-this.getDate()))%7;return(a<0)?(a+7):a;};Date.prototype.getDaysInMonth=function(){Date.daysInMonth[1]=this.isLeapYear()?29:28;return Date.daysInMonth[this.getMonth()];};Date.prototype.getSuffix=function(){switch(this.getDate()){case 1:case 21:case 31:return"st";case 2:case 22:return"nd";case 3:case 23:return"rd";default:return"th";}};String.escape=function(a){return a.replace(/('|\\)/g,"\\$1");};String.leftPad=function(d,b,c){var a=new String(d);if(c==null){c=" ";}while(a.length<b){a=c+a;}return a;};Date.daysInMonth=[31,28,31,30,31,30,31,31,30,31,30,31];Date.monthNames=["January","February","March","April","May","June","July","August","September","October","November","December"];Date.dayNames=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];Date.y2kYear=50;Date.monthNumbers={Jan:0,Feb:1,Mar:2,Apr:3,May:4,Jun:5,Jul:6,Aug:7,Sep:8,Oct:9,Nov:10,Dec:11};Date.patterns={ISO8601LongPattern:"Y-m-d H:i:s",ISO8601ShortPattern:"Y-m-d",ShortDatePattern:"n/j/Y",LongDatePattern:"l, F d, Y",FullDateTimePattern:"l, F d, Y g:i:s A",MonthDayPattern:"F d",ShortTimePattern:"g:i A",LongTimePattern:"g:i:s A",SortableDateTimePattern:"Y-m-d\\TH:i:s",UniversalSortableDateTimePattern:"Y-m-d H:i:sO",YearMonthPattern:"F, Y"};
});
!function(a,b){"function"==typeof define&&define.amd?define('countUp.min',b):"object"==typeof exports?module.exports=b(require,exports,module):a.CountUp=b()}(this,function(){var d=function(a,b,c,d,e,f){for(var g=0,h=["webkit","moz","ms","o"],i=0;i<h.length&&!window.requestAnimationFrame;++i)window.requestAnimationFrame=window[h[i]+"RequestAnimationFrame"],window.cancelAnimationFrame=window[h[i]+"CancelAnimationFrame"]||window[h[i]+"CancelRequestAnimationFrame"];window.requestAnimationFrame||(window.requestAnimationFrame=function(a){var c=(new Date).getTime(),d=Math.max(0,16-(c-g)),e=window.setTimeout(function(){a(c+d)},d);return g=c+d,e}),window.cancelAnimationFrame||(window.cancelAnimationFrame=function(a){clearTimeout(a)}),this.options={useEasing:!0,useGrouping:!0,separator:",",decimal:"."};for(var j in f)f.hasOwnProperty(j)&&(this.options[j]=f[j]);""===this.options.separator&&(this.options.useGrouping=!1),this.options.prefix||(this.options.prefix=""),this.options.suffix||(this.options.suffix=""),this.d="string"==typeof a?document.getElementById(a):a,this.startVal=Number(b),isNaN(b)&&(this.startVal=Number(b.match(/[\d]+/g).join(""))),this.endVal=Number(c),isNaN(c)&&(this.endVal=Number(c.match(/[\d]+/g).join(""))),this.countDown=this.startVal>this.endVal,this.frameVal=this.startVal,this.decimals=Math.max(0,d||0),this.dec=Math.pow(10,this.decimals),this.duration=1e3*Number(e)||2e3;var k=this;this.version=function(){return"1.5.3"},this.printValue=function(a){var b=isNaN(a)?"--":k.formatNumber(a);"INPUT"==k.d.tagName?this.d.value=b:"text"==k.d.tagName?this.d.textContent=b:this.d.innerHTML=b},this.easeOutExpo=function(a,b,c,d){return 1024*c*(-Math.pow(2,-10*a/d)+1)/1023+b},this.count=function(a){k.startTime||(k.startTime=a),k.timestamp=a;var b=a-k.startTime;k.remaining=k.duration-b,k.frameVal=k.options.useEasing?k.countDown?k.startVal-k.easeOutExpo(b,0,k.startVal-k.endVal,k.duration):k.easeOutExpo(b,k.startVal,k.endVal-k.startVal,k.duration):k.countDown?k.startVal-(k.startVal-k.endVal)*(b/k.duration):k.startVal+(k.endVal-k.startVal)*(b/k.duration),k.frameVal=k.countDown?k.frameVal<k.endVal?k.endVal:k.frameVal:k.frameVal>k.endVal?k.endVal:k.frameVal,k.frameVal=Math.round(k.frameVal*k.dec)/k.dec,k.printValue(k.frameVal),b<k.duration?k.rAF=requestAnimationFrame(k.count):k.callback&&k.callback()},this.start=function(a){return k.callback=a,isNaN(k.endVal)||isNaN(k.startVal)||k.startVal===k.endVal?(console.log("countUp error: startVal or endVal is not a number"),k.printValue(c)):k.rAF=requestAnimationFrame(k.count),!1},this.pauseResume=function(){k.paused?(k.paused=!1,delete k.startTime,k.duration=k.remaining,k.startVal=k.frameVal,requestAnimationFrame(k.count)):(k.paused=!0,cancelAnimationFrame(k.rAF))},this.reset=function(){k.paused=!1,delete k.startTime,k.startVal=b,cancelAnimationFrame(k.rAF),k.printValue(k.startVal)},this.update=function(a){cancelAnimationFrame(k.rAF),k.paused=!1,delete k.startTime,k.startVal=k.frameVal,k.endVal=Number(a),k.countDown=k.startVal>k.endVal,k.rAF=requestAnimationFrame(k.count)},this.formatNumber=function(a){a=a.toFixed(k.decimals),a+="";var b,c,d,e;if(b=a.split("."),c=b[0],d=b.length>1?k.options.decimal+b[1]:"",e=/(\d+)(\d{3})/,k.options.useGrouping)for(;e.test(c);)c=c.replace(e,"$1"+k.options.separator+"$2");return k.options.prefix+c+d+k.options.suffix},k.printValue(k.startVal)};return d});
define('carousel',function(require,exports,module){

	function carousel(){
		var container = document.getElementById("carousel-container");
		var width = container.clientWidth;
		var list = document.getElementById("carousel-list");
		var buttons = document.getElementById("carousel-buttons").getElementsByTagName("span");
		var prev = document.getElementById('carousel-prev');
		var next = document.getElementById('carousel-next');
		//用于保存当前显示的是第几张图片
		var index = 1;
		//动画状态，保存动画是否已经在运动
		var animated = false;
		//存放定时器，用于自动切换时候鼠标以上就终止
		var timer;
		
		//初始化轮播图
		carouselInit();

		//设置按钮样式				
		function carousel_showButton(){
			//所有按钮置灰
			for(var i = 0; i < buttons.length; i++){
				if(buttons[i].className == 'carousel-on'){
					buttons[i].className = '';
					break;
				}
			}
			//亮起按钮
			buttons[index-1].className = 'carousel-on';
		}

		//图片切换函数
		function carousel_animate(offset) {
			animated = true;
			//将要运动到的位置
			var newLeft = list.offsetLeft + offset;
			//位移总时间
			var time = 600;
			//位移间隔时间
			var interval = 20;
			//每次的位移量,往左是正，往右是负
			var speed = Math.floor(offset/(time/interval));
			//最初和最末尾图片的位置
			var first = -width;
			var last = -(width*buttons.length);

			function carousel_go(){
				if(speed < 0 && list.offsetLeft > newLeft) {
					//如果是向右滑动的话
					if(list.offsetLeft + speed < newLeft){
						list.style.left = newLeft + 'px';
						
					} else {
						list.style.left = list.offsetLeft+ speed + 'px';
					}
					setTimeout(carousel_go, interval);

				} else if(speed > 0 && list.offsetLeft < newLeft) {
					//如果是向左滑动的话
					if(list.offsetLeft + speed > newLeft){
						list.style.left = newLeft + 'px';
					} else {
						list.style.left = list.offsetLeft+ speed + 'px';
					}
					setTimeout(carousel_go, interval);
				} else {
					carousel_end()
					//debugger;
				}
			}
			function carousel_end(){
				//位移完成后的工作
				animated = false;

				list.style.left = newLeft + 'px';

				//如果图片要从第一张切换到最后一张
				if(newLeft > first){
					list.style.left = last + 'px';
				}

				//如果图片要从最后一张切换到第一张
				if(newLeft < last){
					list.style.left = first + 'px';
				}
			}
			if (navigator.appName === 'Microsoft Internet Explorer') {
				//如果是IE，不进行滚动切换
				//位移完成后的工作
				animated = false;

				list.style.left = newLeft + 'px';

				//如果图片要从第一张切换到最后一张
				if(newLeft > first){
					list.style.left = last + 'px';
				}

				//如果图片要从最后一张切换到第一张
				if(newLeft < last){
					list.style.left = first + 'px';
				}
			} else {
				carousel_go();
			}
			
		}

		//轮播图自动切换功能
		function carousel_play(){
			timer = setInterval(function(){
				next.onclick();
			}, 5000);
		}
		//轮播图停止切换
		function carousel_stop(){
			//清除定时器
			clearInterval(timer);
		}
		next.onclick = function(){
			//如果当前已经有动画在跑则什么都不作
			if(animated){
				return;
			}
			if(index == 5){
				index = 1;
			} else {
				index += 1;
			}
			
			carousel_showButton();
			carousel_animate(-width);
			

		}
		prev.onclick = function(){
			//如果当前已经有动画在跑则什么都不作
			if(animated){
				return;
			}
			if(index == 1){
				index = 5;
			} else {
				index -= 1;
			}
		
			carousel_showButton();
			carousel_animate(width);
		}

		for(var i = 0; i < buttons.length; i++){
			buttons[i].onclick = function(){
				//点击的元素是已经在显示了的或者有有动画正在运行的，就不执行接下来的代码
				if(this.className == 'on' || animated){
					return;
				}
				//获取被点击元素的序列
				var myIndex = parseInt(this.getAttribute('index'));
				var offset = (-width) * (myIndex - index);
				carousel_animate(offset);
				index = myIndex;
				carousel_showButton();

			}
		}

		//设置初始自动移动
		container.onmouseover = carousel_stop;
		container.onmouseout = carousel_play;
		
	}

	//初始化轮播图
	function carouselInit(){
		//设置每个img的大小适合容器container
		var container = document.getElementById("carousel-container");
		var list = document.getElementById("carousel-list");
		var imgs = list.getElementsByTagName("img");
		for(var i = 0, j = imgs.length; i < j; i++){
			imgs[i].style.height = '100%';
			imgs[i].style.width = container.clientWidth + 'px';
		}
		list.style.width = imgs.length * container.clientWidth + 'px';
		list.style.left =  -container.clientWidth + 'px';
	}

	exports.carousel = carousel;
});
define('home',['carousel','countUp.min','jquery.datetimepicker','miniDialog'],function(require,exports,module){
    var carousel = require('carousel');
    var countUp = require('countUp.min');  
    var datetimepicker = require('jquery.datetimepicker');
    var minidialog = require('miniDialog');
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


    //帮我找
    $('.J_helpfind').on('click', function(e){
        e.preventDefault();

        if (!!$('.miniDialog_wrapper').length) {
            $('.miniDialog_wrapper').show();
            $('.miniDialog_mask').show();
        } else {
            miniDialog({
                title: '请填下表，我们将尽快联系您。',      //标题，可以传入html片段渲染
                content: document.getElementById('J_minid').innerHTML,  //内容区，可以传入html片段渲染
                width: 300,    //宽度，默认为200
                height: 430,    //高度，默认为150
                closeX: false,    //closeX属性为true则显示右上角的关闭按钮。默认为false，不显示
                buttons: [    //相应的按钮内容和点击事件， 默认传空数组[]则没有按钮。点击事件里return true能关闭对话框。
                    {
                        'value': '提交',
                        'click': function () {
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
                                alert('请填完字段再提交')
                                return false;
                            }

                            $.ajax({
                                type : 'POST',
                                url : window.location.origin + '/uoffice/sendReplay',
                                data: 'replay.userName=' + param.userName + '&replay.phone=' + 
                                    param.phone + '&replay.city=' + param.city + 
                                    '&replay.message=' + param.message,
                                success: function(data){
                                    alert(data.message);
                                    return true;
                                }
                            });
                            return true;
                        }
                    },
                    {
                        'value': '取消',
                        'click': function () {
                            $('.miniDialog_wrapper').hide();
                            $('.miniDialog_mask').hide();
                            return true;
                        }
                    }
                ]
            });
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
