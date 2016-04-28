<%@ page language="java" import="java.util.*" import="com.pre.team.uoffice.domain.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../../../view/layout/header.jsp">
	<jsp:param name="pageName" value="filter" />
	<jsp:param name="pageTitle" value="Needoffice | 搜索" />
</jsp:include>

<div class="g-bd">
	<div class="wrapper">
		<div class="c-filter-bread">
		</div>
	</div>
	<div class="wrapper">
		<div class="c-filter ">
			<div class="filter-list f-clearfix">
				<h3 class="filter-nav">租赁时间</h3>	
				<div class="filter-content">
					<ul class="filter-items J_date">
						<s:iterator value="filterList" id="id">
							<s:if test="#id.type==01">
								<li class="item <s:property value="#id.lightFlag" />" data-id="<s:property value="#id.locationCode" />"><a href="<s:property value="#id.hrefUrl" />"><s:property value="#id.paramValue" /></a></li>
							</s:if>
						</s:iterator>
					</ul>
				</div>			
			</div>
			
 			<div class="filter-list f-clearfix">
				<h3 class="filter-nav">租赁区域</h3>	
				<div class="filter-content">
					<ul class="filter-items J_code" style="height: auto;padding-bottom:10px;">
						<s:iterator value="filterList" id="id">
							<s:if test="#id.type==02">
								<li class="item <s:property value="#id.lightFlag" />"><a href="<s:property value="#id.hrefUrl" />"><s:property value="#id.paramValue" /></a></li>
							</s:if>
						</s:iterator>
					</ul>
				</div>			
			</div>
			<div class="filter-list f-clearfix">
				<h3 class="filter-nav">公司人数</h3>	
				<div class="filter-content">
					<ul class="filter-items J_size">
						<s:iterator value="filterList" id="id">
							<s:if test="#id.type==03">
								<li class="item <s:property value="#id.lightFlag" />"><a href="<s:property value="#id.hrefUrl" />"><s:property value="#id.paramValue" /></a></li>
							</s:if>
						</s:iterator>
					</ul>
				</div>			
			</div>
			<div class="filter-list f-clearfix">
				<h3 class="filter-nav">月租金</h3>	
				<div class="filter-content">
					<ul class="filter-items J_money">
						<s:iterator value="filterList" id="id">
							<s:if test="#id.type==04">
								<li class="item <s:property value="#id.lightFlag" />"><a href="<s:property value="#id.hrefUrl" />"><s:property value="#id.paramValue" /></a></li>
							</s:if>
						</s:iterator>
					</ul>
				</div>			
			</div>
			<div class="filter-list f-clearfix">
				<h3 class="filter-nav">办公室类型</h3>	
				<div class="filter-content">
					<ul class="filter-items J_type">
						<s:iterator value="filterList" id="id">
							<s:if test="#id.type==05">
								<li class="item <s:property value="#id.lightFlag" />"><a href="<s:property value="#id.hrefUrl" />"><s:property value="#id.paramValue" /></a></li>
							</s:if>
						</s:iterator>
					</ul>
				</div>			
			</div> 
			
			<%-- <s:iterator value="officeList" id="id">
				<div>
					办公室id： <s:property value="#id.officeId" />
					办公室图片：<s:property value="#id.photoUrl" />
					办公室名：<s:property value="#id.officeName" />
					办公室类型：<s:property value="#id.officeType" />
					租金：<s:property value="#id.officeMoney" />
					办公室人数：<s:property value="#id.officeSize" />
					起租期：<s:property value="#id.rentDate" />
				</div>
			</s:iterator>
			
			第几页：<s:property value="#request.page" />
			总页数:<s:property value="#request.totalPage" /> --%>
			<input type="hidden" data-page="<s:property value="#request.page" />" 
				data-total="<s:property value="#request.totalPage" />" class="J_scrparam"/>
		</div>
		<hr>
		
		<div class="c-office-container">
			<ul class="c-office-list J_list">
				<s:iterator value="officeList" id="item">
					<li class="item">
						<div  class="link" data-id="<s:property value="#item.officeId" />">

							<img src="<s:property value="#item.photoUrl" />" alt="" class="image"/>
							<p class="name"><s:property value="#item.officeName" /></p>
							<div class="detail">
								<span class="summary"><s:property value="#item.officeDesc" /></span>
								<div class="price">
									&yen;<b><s:property value="#item.officeMoney" /></b>/月
								</div>
							</div>
						</div>
						<div class="shadow">
							<a target="_blank" class="url" href="${webUrl}/officeDetail?officeId=<s:property value="#item.officeId" />"></a>
						</div>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
</div>

<a href="javascript:;" class="c-back J_back" title="回到顶部"></a>

<script type="text/template" id="J_office">
	<li class="item">
		<div class="link" data-id="{{officeId}}">
			<img src="{{photoUrl}}" alt="" class="image"/>
			<p class="name">{{officeName}}</p>
			<div class="detail">
				<span class="summary">{{officeDesc}}</span>
				<div class="price">
					&yen;<b>{{officeMoney}}</b>/月
				</div>
			</div>
			
		</div>
		<div class="shadow">
			<a target="_blank" class="url" href="${webUrl}/officeDetail?officeId={{officeId}}"></a>
		</div>
	</li>
</script>
<jsp:include page="../../../view/layout/footer.jsp">
	<jsp:param name="pageName" value="filter" />
	<jsp:param name="pluginJs" value="lib/jquery-cookie/jquery.cookie-min" />
</jsp:include>
