<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<div class="g-ft">
		<div class="wrapper f-clearfix">
			<div class="c-infobox office">
				<p class="title">Needoffice</p>
				<p class="intro">Needoffice“你的办公”是提供办公场地租赁及其他企业硬服务的C2B,O2O平台。真正的双向免费，真实房源保证。关心，从办公室开始。</p>
			</div>
			<div class="c-infobox about ">
				<p class="title">关于</p>
				<p><a href="${webUrl}/view/screen/server/server.jsp#about" target="_blank" class="link">关于我们</a></p>
				<p><a href="${webUrl}/view/screen/server/server.jsp#contact" target="_blank" class="link">联系我们</a></p>
				<p><a href="${webUrl}/view/screen/server/server.jsp#join" target="_blank" class="link">加入我们</a></p>
			</div>
			<div class="c-infobox help">
				<p class="title">用户帮助</p>
				<p><a href="${webUrl}/view/screen/server/server.jsp#find" target="_blank" class="link">如何寻找办公室</a></p>
				<p><a href="${webUrl}/view/screen/server/server.jsp#release" target="_blank" class="link">如何发布办公室</a></p>
				<p class="title"><a href="${webUrl}/view/screen/server/server.jsp#biz" target="_blank" class="link">商务合作</a></p>
			</div>
			<div class="c-infobox contact">
				<p class="title">客服电话</p>
				<p>18868875384</p>
				<p class="title">客服邮箱</p>
				<p>service@needoffice.cn</p>
			</div>
			<div class="c-infobox attention">
				<p class="title">关注我们</p>
				<p><a href="javascript:void(0)"> <!-- <img class="wechat-img" src="${webUrl}/img/wechat-concern.png" width="31" height="30"/> --></a></p>
				<img src="${webUrl}/img/2code.jpg" width="120" height="120" alt="">

			</div>
			
		</div>
		<div class="wrapper">
			<p class="c-copyright">Copyright &nbsp;<span class="fa fa-copyright"></span>2015&nbsp;&nbsp;	 杭州猛犸网络科技有限公司&nbsp; 版权所有 &nbsp; 网站备案/许可证号： &nbsp;浙ICP备15032612</p>
		</div>
	</div>
	<script type="text" id="J_minid">
        <form class="pure-form pure-form-stacked J_replay c-minid">
            <label>称呼</label>
            <input type="text" name="userName"/>

            <label>手机</label>
            <input type="text" name="phone"/>

            <label>城市</label>
            <select name="city">
                <option value="0101">杭州</option>
            </select>

            <label>留言</label>
            <textarea name="message" id="" rows="10" placeholder="您对办公室的具体要求" style="resize:none;height: 114px;"></textarea>
        </form>
    </script>
	<script src="${webUrl}/staticfile/src/lib/seajs/sea-debug.js"></script>
	<script src="${webUrl}/staticfile/development/base.js"></script>
	<script src="${webUrl}/staticfile/src/lib/miniDialog/miniDialog.js"></script>
	<script src="${webUrl}/staticfile/src/common/header/header.js"></script>

	<%
		if(request.getParameterValues("pluginJs") != null && request.getParameterValues("pluginJs").length > 0){
			for(int i = 0 ; i < request.getParameterValues("pluginJs").length ; i++){
				String pluginUrl = request.getParameterValues("pluginJs")[i];
				out.println("<script type='text/javascript' src='/uoffice/staticfile/src/"+pluginUrl+".js'></script>");
			}
		} 
	%>
	
	<script>
		
		// seajs 的简单配置
		seajs.config({
		    base : '${webUrl}/staticfile/src/biz',
		    charset: 'utf-8'
		});
		
		// 加载入口模块
		seajs.use("${webUrl}/staticfile/src/biz/<%=request.getParameter("pageName")%>/<%=request.getParameter("pageName")%>.js");
	
	</script>
</body>
</html>