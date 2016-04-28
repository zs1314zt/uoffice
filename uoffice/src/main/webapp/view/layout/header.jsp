<%@ page language="java" import="java.util.*" import="com.pre.team.uoffice.domain.*" pageEncoding="utf-8"%>
<% request.setAttribute("webUrl", "/uoffice"); %>
<%
    User user = (User)request.getSession().getAttribute("current_user");
    String userName = user == null ? "":user.getUserName(); 
    String userType = user == null ? "":user.getUserType(); 
%>
<!DOCTYPE html>
<html xmlns:wb="http://open.weibo.com/wb">
<head>
    <jsp:include page="../../constants/constants.jsp"></jsp:include>
    <title><%=request.getParameter("pageTitle")%></title>
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
    <link rel="icon" href="${webUrl}/img/partners/none.png" type="image/png" sizes="16*16">
    <link rel="stylesheet" href="${webUrl}/staticfile/development/base.css">
    <%
        if(request.getParameterValues("pluginCss") != null && request.getParameterValues("pluginCss").length > 0){
            for(int i = 0 ; i < request.getParameterValues("pluginCss").length ; i++){
                String pluginUrl = request.getParameterValues("pluginCss")[i];
                out.println("<link rel='stylesheet' href='/uoffice/staticfile/"+pluginUrl+".css' />");
            }
        }
    %>
    <link rel="stylesheet" href="${webUrl}/staticfile/src/common/header/header.css">
    <link rel="stylesheet" href="${webUrl}/staticfile/src/biz/<%=request.getParameter("pageName")%>/<%=request.getParameter("pageName")%>.css">
	<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
    <div class="g-hd" >
        <div class="c-hd-bar">
            <div class="c-hd-bar-main f-clearfix">
                <a href="${webUrl}/public" class="c-logo-wrapper">
                    <img src="${webUrl}/img/Needoffice.png" class="c-logo" alt=""/>
                </a>
                <ul class="nav-R">
                <%
                    if(user == null){
                %>
                    <li class="nav-list">
                        <a href="${webUrl}/view/screen/server/server.jsp" class="nav-list-item">服务</a>
                    </li>
                    <li class="nav-list">
                        <a href="${webUrl}/view/screen/register/register.jsp" class="nav-list-item">注册</a>
                    </li>
                    <li class="nav-list">
                        <a href="${webUrl}/view/screen/login/login.jsp" class="nav-list-item">登录</a>
                    </li>
                    <li class="nav-list">
                        <a href="#" class="pure-button c-btn-free J_helpfind">帮我找房</a>
                    </li>
                <%
                    }else{
                %>
                    <li class="nav-list">
                        <a href="${webUrl}/showPersonalInfo" class="nav-list-item">欢迎您><%=userName %></a>
                    </li>
                        <li class="nav-list">
                        <a href="${webUrl}/logout" class="nav-list-item">退出</a>
                    </li>
                    <% 
                        if(userType != null && "02".equals(userType)){
                    %>
                        <li class="nav-list">
                            <a href="${webUrl}/publishOfficeView" class="pure-button c-btn-free">发布房间</a>
                        </li>
                    <%
                        }else{
                            
                    %>
                        <li class="nav-list">
                            <a href="#" class="pure-button c-btn-free J_helpfind">帮我找房</a>
                        </li>
                    <%
                        }
                }
                %>
                </ul>
            </div>
        </div>
    </div>


