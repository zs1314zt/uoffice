<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<title>很抱歉，您要访问的页面不存在！</title>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../../../constants/constants.jsp"></jsp:include>
	<title>出错了</title>
	<link rel="stylesheet" href="${webUrl}/staticfile/src/biz/error/error.css">
	<link href='http://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister' rel='stylesheet' type='text/css'>
</head>
	<body>
	 <div class="wrap">
		<div class="logo">
				<p>很抱歉，您要访问的页面不存在！</p>
				<p>Sorry,Could't Find it</p>
				<img src="${webUrl}/img/404-1.png"/>
		</div>
	 </div>	
	<div class="footer">
		<a href="${webUrl}/public">@Needoffice</a>
	</div>
	</body>
</html>
