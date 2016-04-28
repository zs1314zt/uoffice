<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="../../../view/layout/header.jsp">
	<jsp:param name="pageName" value="findpsw" />
	<jsp:param name="pageTitle" value="密码找回" />
</jsp:include>

	<div class="g-bd">
		<form class=" J_findpsw_form m-form">
		    
		    	<legend class="title f-tac">密码找回</legend>
		        <div class="c-group">
		            <label for="userEmail f-tar"
		            class="c-label">邮箱:</label>
			        <input class="J_userEmail c-input small" name="username" type="text" placeholder="邮箱" >
			        <button class="pure-button J_getCode c-getCode-btn">获取验证码</button>
		        </div>
		        <div class="c-group">
		            <label for="code"
		            class="c-label f-tar">验证码:</label>
		        	<input class="J_code c-input" name="code" type="text" placeholder="输入邮箱收到的验证码">
		        </div>
				<div class="c-group">
		            <label for="password"
		            class="c-label f-tar">新密码:</label>
		        	<input class="J_password c-input" name="password" type="password" placeholder="输入6-12个字母数字符号的组成">
		        </div>
		        <div class="c-group">
		            <label for="password" class="c-label f-tar">密码确认:</label>
		        	<input class="J_password_confirm c-input" name="confirmpassword" type="password" placeholder="请再一次输入密码">
		        </div>
		        <div class="pure-controls">
		            
		            <button class="pure-button J_findpsw_btn c-findpsw-btn">修改密码</button>
		        </div>      
		</form>
	</div>
<jsp:include page="../../../view/layout/footer.jsp">
	<jsp:param name="pageName" value="findpsw" />
</jsp:include>
