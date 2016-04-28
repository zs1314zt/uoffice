<%@ page language="java" import="java.util.*" import="com.pre.team.uoffice.domain.User" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
User user = (User)request.getSession().getAttribute("current_user");
String userType = user == null ? "":user.getUserType(); 
%>
<jsp:include page="../../../constants/constants.jsp"></jsp:include>
<jsp:include page="../../../view/layout/header.jsp">
	<jsp:param name="pageName" value="detail" />
	<jsp:param name="pageTitle" value="Needoffice | 详情" />
</jsp:include>
<div class="g-bd">
	<div class="c-banner">
		<img src="${officeDetail.bannerUrl}" class="image" alt="" />
	</div>
	<input class="officeId" value="${officeDetail.officeId}" type="hidden"></input>
	<div class="c-content pure-g-r">
		<div class="wrapper c-officeindex">
				<div class="pure-u-3-4">
					<div class="f-clearfix">
						<div class="c-officeName f-fl">
							<label>${officeDetail.officeName}</label>
							<span>${officeDetail.officeDesc}</span>
						</div>
						<%-- <a href="###" class="c-share f-fl color-ching">分享&nbsp;&nbsp;<span class="fa fa-share-alt"></span></a> --%>
						<% 
							if("02".equals(userType)){ 
						%>
									<button class="f-fr J_changeMessage control-btn">修改信息</button>
									<button class="f-fr J_deleteMessage control-btn">删除信息</button>
						<%
							}
						%>
					</div>
					<div class="f-flex">
						<div class="c-rectangle">
							<label class="color-red fa fa-credit-card"></label>
							<span class="f-color1">${officeDetail.officeMoney}元/月</span>
						</div>


						<div class="c-rectangle">
							<label class="f-color1 fa fa-gears"></label>
							<span class="f-color1">${officeDetail.officeType}</span>
						</div>
						<div class="c-rectangle">
							<label class="color-green fa fa-calendar"></label>
							<span class="f-color1">${officeDetail.rentDate}个月</span>
						</div>
						<div class="c-rectangle">
							<label class="color-brown fa fa-group"></label>
							<span class="f-color1">${officeDetail.rentDate}人</span>
						</div>
					</div>
				</div>
				<div class="pure-u-1-4 c-scan">
					<div class="f-tac">扫码有惊喜</div>
					<img src="${webUrl}/img/2code.jpg"  alt="" class="c-two-code-img">
				</div>

		</div>
			<div class="wrapper pure-g-r c-officeinfo">
				<div class="pure-u-3-4 box-white c-info">
					<ul id="J_nav" class="c-nav f-fixed">
						<li class="nav-item"><a href="###" date="J_details" class="current"><span class="fa fa-tasks"></span>&nbsp;&nbsp;详情介绍</a></li>
						<li class="nav-item"><a href="###" date="J_picture"><span class="fa fa-photo"></span> &nbsp;&nbsp;图片</a></li>
						<li class="nav-item"><a href="###" date="J_map"><span class="fa fa-taxi"></span>&nbsp;&nbsp;交通状况</a></li>
						<li class="nav-item"><a href="###" date="J_step"><span class="fa fa-binoculars"></span>&nbsp;&nbsp;租赁流程</a></li>
						<li class="nav-item"><a href="###" date="J_comment"><span class="fa fa-thumbs-up"></span>&nbsp;&nbsp;评论</a></li>
						<li id="J_booking"><a href="###" date="J_booking"><span class="fa fa-archive"></span>&nbsp;&nbsp;预约看房</a></li>
					</ul>
					<div id="J_details" class="pure-g-r item target_0">
						<div class="pure-u-1-4">
							<label class="title">详情介绍</label>
						</div>
						<div class="pure-u-3-4">
							<pre class="f-ff">${officeDetail.officeContent}</pre></br>
							<s:if test="#request.officeDetail.officeArea!=0&& #request.officeDetail.officeArea!=null">
								<span>办公室面积:</span><span>${officeDetail.officeArea}</span>平方米
								</br></br>
							</s:if>
							<span>联系方式:</span><span>${officeDetail.officeContact}</span></br>
							</br>
							<span>详细地址:</span><span>[${officeDetail.locationAddress}] ${officeDetail.detailAddr}</span>
						</div>
					</div>
					<div class="c-break"></div>
					<div id="J_picture" class="pure-g-r item target_1">
						<div class="pure-u-1-4">
							<label class="title">图片</label>
						</div>
						<div class="pure-u-3-4 ">
							<s:iterator value="officeDetail.photoUrl" id="id">
								<a class="pure-u-1-2 example-image-link" href="<s:property  value="id"/>" data-lightbox="example-set">
									<img src="<s:property  value="id"/>" alt="" class="c-image example-image" />
								</a>
							</s:iterator>
						</div>
					</div>
					<div class="c-break"></div>
					<div id="J_map" class="pure-g-r item target_2">
						<div class="pure-u-1-4">
							<label class="title">交通状况</label>
						</div>
						<div class="pure-u-3-4">
							<pre class="f-ff">${officeDetail.mapDesc}</pre></br>
							<a class="pure-u-1-2 example-image-link c-mapimage" href="${officeDetail.mapUrl}" data-lightbox="example-set">
								<img src="${officeDetail.mapUrl}" alt="" class="c-mapimage example-image"/>
							</a>
						</div>
					</div>
					<div class="c-break"></div>	
					<div id="J_step" class="pure-g-r c-step item target_3">
						<div class="pure-u-1-4">
							<label class="title">租赁流程</label>
						</div>
						<div class="pure-u-3-4">
							<ul class="f-flex">
								<li class="c-step-ls">在线搜索&nbsp;&nbsp;
									<span class="fa fa-arrow-right"></span>
								</li>
								<li class="c-step-ls">预约看房&nbsp;&nbsp;
									<span class="fa fa-arrow-right"></span>
								</li>
								<li class="c-step-ls">免费带看&nbsp;&nbsp;
									<span class="fa fa-arrow-right"></span>
								</li>
								<li class="c-step-ls">签约入驻</li>
							</ul>
						</div>
					</div>
					<div class="c-break"></div>
					<div id="J_comment" class="pure-g-r comment item messageCont target_4">
						<label class="title">评论</label>
						<s:iterator value="queryResult.messageList" id="id">

							<div class="pure-u-1-4">
								<label class="c-username ">
									<s:property value="#id.userName" />
								</label>
							</div>
							<div class="pure-u-3-4">
								<span>
										<s:property value="#id.messContent" />
									</span>
								<div class="f-tar">
									<s:property value="#id.time" />
								</div>
							</div>
						</s:iterator>
					</div>
					<div class="c-break"></div>
					<div class="pure-g-r c-commond item">
						<div class="pure-u-1-4">
							<label class="title">我的评论</label>
						</div>
						<div class="pure-u-3-4">
							<textarea cols="8" rows="3" class="c-message" type="text"></textarea>
							<div class="f-tar">
								<button class="J_commond btn-commond">发表评论</button>
								<input class="J_verify f-dn" type="text" placeholder="请输入验证码">
								<img class="J_verifyImg f-dn" src=""/>
							</div>
						</div>
					</div>
					<div class="c-break"></div>
					<div id="J_comment" class="pure-g-r comment item messageCont target_4">
						<label class="title">分享到</label>
						<div class="c-share-box">
							<div class="c-share-box-btn weibo">
								<wb:share-button appkey="2708180154" addition="simple" type="button" ralateUid="2845023532" default_text="Needoffice“你的办公”是提供办公场地租赁及其他企业硬服务的C2B,O2O平台。真正的双向免费，真实房源保证。关心，从办公室开始"></wb:share-button>
								<!-- <i class="fa fa-weibo"></i>微博 -->
							</div>
							<div class="c-share-box-btn weixin"><i class="fa fa-weixin"></i>微信</div>
						</div>
					</div>

				</div>
				<div class="pure-u-1-4 c-sidebar box-white">
					<!--<p class="f-tac color-blue ">友情链接</p>
					<ul class="c-office-list c-friend-link J_list ">
						<li class="item">
							<div class="link">
									<img src="" alt="" class="image" />
									<p class="name">
										都比森
									</p>
									<div class="introduce">
										啦啦啦
									</div>
								</div>
								<div class="shadow">
									<a target="_blank" class="url" href="www.baidu.com"></a>
								</div>
							</li>
					</ul>-->
					<p class="f-tac color-blue">您可能感兴趣的办公室</p>
					<ul class="c-office-list J_list">
						<s:iterator value="officeList" id="item">
							<li class="item">
								<div class="link" data-id="<s:property value=" #item.officeId " />">
									<img src="<s:property value=" #item.photoUrl " />" alt="" class="image" />
									<p class="name">
										<s:property value="#item.officeName" />
									</p>
									<div class="detail">
										<span class="summary">
										<s:property value="#item.type" />
									</span>
										<div class="price">
										<s:if test="#item.eachPrice!=0 && #item.eachPrice!=null">
                    						&yen; <b><s:property value="#item.eachPrice" /></b>元/位/月
                    					</s:if>
			                        	<s:else>
				                        	<s:if test="#item.officeMoney!=0 && #item.officeMoney!=null">
				                        		<s:if test="#item.officeArea!=0 && #item.officeArea!=null">
				                        			&yen; <b><s:property value="#item.officeMoney/#item.officeArea" /></b>元/平方/月
				                        		</s:if>
				                        		<s:else>
				                        			&yen; <b><s:property value="#item.officeMoney" /></b>元/月
				                        		</s:else>
				                        	</s:if>
				                        	<s:else>
				                        		免费
				                        	</s:else>
			                        	</s:else>
									</div>
								</div>
								<div class="shadow">
									<a target="_blank" class="url" href="${webUrl}/officeDetail?officeId=<s:property value=" #item.officeId " />"></a>
								</div>
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
	</div>
</div>
<script type="text/template" id="J-message">
	<div class="pure-u-1-4">
		<label class="c-username">{{userName}}</label>
	</div>
	<div class="pure-u-3-4">
		<span>{{messContent}}</span>
		<div class="f-tar">
			{{time}}
		</div>
	</div>
</script>
<script type="text" id="J_bookingid">
	<form class="pure-form pure-form-stacked J_replay c-minid">

		<input name="officeid" type="hidden"/>

		<input name="officename" type="hidden"/>
		
		<label>电话</label>
		<input type="tel" name="phone"/>

		<label>邮箱</label>
		<input type="email" name="email"/>
	</form>
</script>
<script type="text" id="weibofenxiang">
	<form class="pure-form pure-form-stacked J_replay c-minid">
		<img id="weiboerweima" src="${webUrl}/img/2code.jpg"  alt="" class="c-two-code-img" style="width:270px; height:270px;">
	</form>
</script>


<jsp:include page="../../../view/layout/footer.jsp">
	<jsp:param name="pageName" value="detail" />
</jsp:include>
