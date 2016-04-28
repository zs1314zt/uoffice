define(function(require,exports,module){

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