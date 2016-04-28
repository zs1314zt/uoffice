<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>  
<jsp:include page="../../../constants/constants.jsp"></jsp:include>
<jsp:include page="../../../view/layout/header.jsp">
	<jsp:param name="pageName" value="officeList" />
		<jsp:param name="pageTitle" value="办公室列表" />
</jsp:include>
<div class="g-bd">
	<div class="c-nav wrapper">
		<ul>
			<li class="J_page_item current_page_item">
				<a href="${webUrl}/uoffice/getOfficeByType?officeType=01" style="background-position: 0px 60px;">短期租赁</a>
			</li>
			<li class="line"></li>
			<li class="J_page_item">
				<a href="${webUrl}/uoffice/getOfficeByType?officeType=02" style="background-position: 0px 60px;">长期租赁</a>
			</li>
			<li class="line"></li>
			<li class="J_page_item">
				<a href="${webUrl}/uoffice/getOfficeByType?officeType=03" style="background-position: 0px 60px;">联合办公</a>
			</li>
			<li class="line"></li>
			<li class="J_page_item">
				<a href="${webUrl}/uoffice/getOfficeByType?officeType=04" style="background-position: 0px 60px;">孵化器</a>
			</li>
			<li class="line"></li>
		</ul>
	</div>
	<div class="wrapper f-tac c-banner">
	<!--  
		<div class='bg'><img src='${webUrl}/img/fuhuaqi.jpg'></div>
		<div class='f-tal summary'>
			翻炒几下后倒入调好的“小荔枝口味”宫保汁(香醋2勺、生抽1勺、老抽半勺、白糖2勺、盐半勺、干淀粉1勺放入碗中调和均匀) 或经典鱼香汁 (鱼香汁的具体做法见这里)(调味汁根据菜量的多少，看着倒，不一定要全部倒入)翻炒几下后倒入调好的“小荔枝口味”宫保汁翻炒几下后倒入调好的“小荔枝口味”宫保汁 
			翻炒几下后倒入调好的“小荔枝口味”宫保汁 
		</div>
	-->	
		<%
			String webUrl = (String) request.getAttribute("webUrl");
			String officeType = request.getParameter("officeType") ;
			if("01".equals(officeType)){
				out.println("<div class='bg'><img src='"+webUrl+"/img/office/duanqizulin-ad.jpg'></div>");
				out.println("<div class='f-tal summary'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;短租模式在互联网办公中掀起了一个流行浪潮，适合创业初期的团队进行一个过度的阶段，不需要一下次支付昂贵的房租，不需要担心团队的发展带来的办公场地问题。我们希望通过提供更多性价比高的短租场地，为创业者们的每个阶段做好准备。</div>");
			}else if("02".equals(officeType)){
				out.println("<div class='bg'><img src='"+webUrl+"/img/office/changqizulin-ad.png'></div>");
				out.println("<div class='f-tal summary'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;给企业提供一个长期稳定的办公场地，让办公者不必每天为了场地租赁这样的琐事而烦恼。虽然这是一个比较传统的租赁方式，但我们竭力为您需找最优质的产地资源，给您提供最具有个性的办公环境。</div>");
			}else if("03".equals(officeType)){
				out.println("<div class='bg'><img src='"+webUrl+"/img/office/lianhebangong-ad.jpg'></div>");
				out.println("<div class='f-tal summary'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联合办公是一种为降低办公室租赁成本而进行共享办公空间的办公模式，来自不同公司的个人在联合办公空间中共同工作。在特别设计和安排的办公空间中共享办公环境，彼此独立完成各自项目。办公者可与其他团队分享信息、知识、技能、想法和拓宽社交圈子等。服务商包括柔性办公基地、协纵国际企业港、创业SOHO等。</div>");
			}else{
				out.println("<div class='bg'><img src='"+webUrl+"/img/office/fuhuaqi-ad.jpg'></div>");
				out.println("<div class='f-tal summary'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;孵化器在中国也称高新技术创业服务中心，它通过为新创办的科技型中小企业提供物理空间和基础设施，提供一系列的服务支持，进而降低创业者的创业风险和创业成本，提高创业成功率，促进科技成果转化，培养成功的企业和企业家。</div>");
			}
		%>
		
		
	</div>
	
	<div class="wrapper c-recommend">
		<s:iterator value="officeList" id="id" status="stuts">
			<s:if test="#stuts.odd == true">
				<div class="container f-clearfix">
			</s:if>
				<div class='box f-fl'>
					<a target="_blank" href="${webUrl}/view/screen/officeList/officeDetail?officeId=<s:property value="#id.officeId" />" class="link">
						<img src="<s:property value="#id.photoUrl" />" class="image" alt="" />
						<p class="name"><s:property value="#id.officeName" /></p>
						<p class="summary"><s:property value="#id.officeDesc" /></p>
						<div class="price">
                        	<s:if test="#id.eachPrice!=0 && #id.eachPrice!=null">
	                        &yen; <b><s:property value="#id.eachPrice" /></b>元/位/月
                    			</s:if>
                        	<s:else>
	                        	<s:if test="#id.officeMoney!=0 && #id.officeMoney!=null">
	                        		<s:if test="#id.officeArea!=0 && #id.officeArea!=null">
	                        			&yen; <b><s:property value="#id.officeMoney/#id.officeArea" /></b>元/平方/月
	                        		</s:if>
	                        		<s:else>
	                        			&yen; <b><s:property value="#id.officeMoney" /></b>元/月
	                        		</s:else>
	                        	</s:if>
	                        	<s:else>
	                        		免费
	                        	</s:else>
                        	</s:else>
						</div>
					</a>
				</div>
 			<s:if test="#stuts.odd == false">
				</div>
			</s:if>
 			<s:if test="#stuts.last == true">
				</div>
			</s:if>
		</s:iterator>
	
		

		<div class="wrapper f-tac">
			<a class="pure-button c-more" href="${webUrl}/search?officeType=<s:property value="#request.officeType" />">MORE>></a>
		</div>
	
	</div>

	
</div>	

<jsp:include page="../../../view/layout/footer.jsp">
	<jsp:param name="pageName" value="officeList" />
</jsp:include>
