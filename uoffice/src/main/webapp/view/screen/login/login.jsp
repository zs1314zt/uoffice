<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="../../../view/layout/header.jsp">
	<jsp:param name="pageName" value="login" />
	<jsp:param name="pageTitle" value="用户登陆" />
</jsp:include>
	<div class="g-bd">
		<form class="m-form J_login_form">	
		    	<legend class="f-tac title">登  录</legend>
		        <div class="c-group">
		            <label for="phoneNum f-tar" class="c-label">邮箱 :</label>
			        <input class="J_username c-input" name="username" type="text" placeholder="请填写有效邮箱">
		        </div>
				<div class="c-group">
		            <label for="password f-tar" class="c-label">密码 :</label>
		        	<input class="J_password c-input" name="password" type="password" placeholder="输入6-18个字母数字符号">
		        </div>
		        <div class="pure-controls">
		            <button type="submit" class="pure-button c-login-btn J_login_btn">登 录</button>
		            <a href="${webUrl}/view/screen/findpsw/findpsw.jsp" class="c-forget">忘记密码</a>     
		        </div>
		        	
		</form>
	</div>
<jsp:include page="../../../view/layout/footer.jsp">
	<jsp:param name="pageName" value="login" />
</jsp:include>
