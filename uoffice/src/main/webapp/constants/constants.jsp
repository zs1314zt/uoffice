<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% request.setAttribute("webUrl", "/uoffice"); %>

<%
 	if(request.getParameterValues("pluginJs") != null && request.getParameterValues("pluginJs").length > 0){
		for(int i = 0 ; i < request.getParameterValues("pluginJs").length ; i++){
			String pluginUrl = request.getParameterValues("pluginJs")[i];
			out.println("<script type='text/javascript' src='/uoffice/staticfile/"+pluginUrl+".js' />");
		}
	} 
	if(request.getParameterValues("pluginCss") != null && request.getParameterValues("pluginCss").length > 0){
		for(int i = 0 ; i < request.getParameterValues("pluginCss").length ; i++){
			String pluginUrl = request.getParameterValues("pluginCss")[i];
			out.println("<link rel='stylesheet' href='/uoffice/staticfile/"+pluginUrl+".css' />");
		}
	} 

%> 


