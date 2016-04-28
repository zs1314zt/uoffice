<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="../../../view/layout/header.jsp">
	<jsp:param name="pageName" value="userinfo" />
	<jsp:param name="pageTitle" value="个人信息维护" />
</jsp:include>
	<div class="g-bd">
		<form class=" J_userinfo_form m-form">
		    	<legend class="title f-tac">个人信息维护</legend>
		        <div class="c-group">
		            <label for="phoneNum f-tar"
		            class="c-label">邮箱:</label>
			        <input class="J_useremail c-input" name="userEmail" type="text"  value="${user.userEmail}" disabled>
		        </div>
		        <div class="c-group">
		            <label for="code"
		            class="c-label f-tar">昵称:</label>
		        	<input class="J_username c-input" name="userName" type="text" placeholder="请输入昵称" value="${user.userName}">
		        </div>
				<div class="c-group">
		            <label for="code"
		            class="c-label f-tar">真实姓名:</label>
		        	<input class="J_realname c-input" name="realName" type="text" placeholder="请输入真实姓名" value="${user.realName}">
		        </div>
				<div class="c-group">
		            <label for="password"
		            class="c-label f-tar">手机号:</label>
		        	<input class="J_userphone c-input" name="userPhone" type="text" placeholder="请输入手机号码" value="${user.userPhone}">
		        </div>
		        <div class="pure-controls">
		            <button class="pure-button J_userinfo_btn c-userinfo-btn">确认修改</button>
		        </div>
		</form>
	</div>
<jsp:include page="../../../view/layout/footer.jsp">
	<jsp:param name="pageName" value="userinfo" />
</jsp:include>