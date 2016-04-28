<%@ page language="java" import="java.util.*" import="com.pre.team.uoffice.domain.*" import="com.pre.team.uoffice.dao.impl.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>  
<%
    User user = (User)request.getSession().getAttribute("current_user");
	OfficeLocation offLocation = (OfficeLocation)request.getSession().getAttribute("current_city");
	String cityName = offLocation == null?"杭州":offLocation.getLocationName();
	//String cityName = offLocation.getLocationName();
    String userName = user == null ? "":user.getUserName(); 
    String userType = user == null ? "":user.getUserType();
%>
<html lang="zh">
<head>
    <jsp:include page="../../../constants/constants.jsp"></jsp:include>
    <title>Needoffice | 关心从办公室开始</title>
    <meta http-equiv="cache-control" content="no-cache">
    <link rel="icon" href="${webUrl}/img/partners/none.png" type="image/png" sizes="16*16">
    <link rel="stylesheet" href="${webUrl}/staticfile/development/base.css">
    <link rel="stylesheet" href="${webUrl}/staticfile/src/biz/home/home.css">
</head>
<body >
    <div class="g-hd" >
        <div class="c-hd-bar-home">
            <div class="c-hd-bar-main">
                <a href="#" class="c-logo-wrapper">
                    <img src="${webUrl}/img/Needoffice.png" class="c-logo" alt=""/>
                </a>
                <div class="nav-c">
                    <img src="${webUrl}/img/uoffice_slogen.png" class="c-care" alt="">
                </div>

                <ul class="nav-R">
                    <li class="nav-list">
                        <a href="${webUrl}/view/screen/server/server.jsp" class="nav-list-item">服务</a>
                    </li>
                <%
                    if(user == null){
                %>
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
        <div class="wrapper">
            <form class="pure-form c-scr J_scr" action="${webUrl}/search" method="GET">
                <div class="bg"></div>
                <fieldset>      

                    <div class="c-city">
                        <i class="fa fa-map-marker"></i>
                        <span class="J_location_name"><%=cityName %></span>
                        
                        <div class="J_selector_parent" style="display:none">
                            <div class="triangle"></div>
                            <div class="selector J_selector">
<%--                                 <span data-id="0101">杭州</span>
                                <span data-id="0102">北京</span>
                                <span data-id="0103">深圳</span>
                                <span data-id="0104">上海</span> --%>
                            </div>
                        </div>
                    </div>
                    <input type="number" placeholder="人数" name="CompanySize" min="1" />
                    <!-- <input type="hidden" name="location_code" value="0101" /> -->
                    <button type="submit" class="pure-button pure-button-primary">搜索</button>
                </fieldset>

            </form>
        </div>
        <div id="carousel-container">
            <div id="carousel-list">
                <img src="${webUrl}/img/carousel5.png" alt="5"/>
                <img src="${webUrl}/img/carousel1.jpg" alt="1"/>
                <img src="${webUrl}/img/carousel2.jpg" alt="2"/>
                <img src="${webUrl}/img/carousel3.jpg" alt="3"/>
                <img src="${webUrl}/img/carousel4.jpg" alt="4"/>
                <img src="${webUrl}/img/carousel5.png" alt="5"/>
                <img src="${webUrl}/img/carousel1.jpg" alt="1"/>
            </div>
            <div id="carousel-buttons" class="f-dn">
                <span index="1" class="carousel-on"></span>
                <span index="2"></span>
                <span index="3"></span>
                <span index="4"></span>
                <span index="5"></span>
            </div>
            <a href="javascript:;" id="carousel-prev" class="carousel-arrow">&lt;</a>
            <a href="javascript:;" id="carousel-next" class="carousel-arrow">&gt;</a>
            <div class="shadow"></div>
        </div>
    </div>
    
    <div class="g-bd" >
        <div class="c-countUp f-clearfix">
            <div class="c-center f-clearfix">
                <div class="count-user">
                    <div id="J_userCount" data-num="<s:property value="#request.registUserCount" />" class="c-num"></div>
                    <div class="c-font">注册用户</div>
                </div>
                <div class="count-office">
                    <div id="J_officeCount" data-num="<s:property value="#request.officeCount" />" class="c-num"></div>
                    <div class="c-font">办公室</div>
                </div>
                <div class="count-success">
                    <div id="J_successCount" data-num="<s:property value="#request.successCount" />" class="c-num"></div>
                    <div class="c-font">对接项目</div>
                </div> 
            </div>
        </div>
        <div class="c-info-bg f-clearfix">
            <div class="wrapper">
                <div class="box">
                    <img src="${webUrl}/img/logo_4.png" >
                    <p>真实房源</p>
                </div>
                <div class="box">
                    <img src="${webUrl}/img/logo_1.png" >
                    <p>个性找房</p>
                </div>
                <div class="box">
                    <img src="${webUrl}/img/logo_3.png" >
                    <p>双向免费</p>
                </div>
                <div class="box last">
                    <img src="${webUrl}/img/logo_2.png" >
                    <p>配套服务</p>
                </div>
            </div>
        </div>
        <div class="wrapper c-office">
            <h1 class="title">————　办公室类型　 ————</h1>
            <ul class="list f-clearfix">
                <li class="item">
                    <a href="${webUrl}/getOfficeByType?officeType=04" class="link">
                        <img src="${webUrl}/img/office/fuhuaqi.jpg" alt="" class="image"/>
                    </a>
                </li> 
                <li class="item">
                    <a href="${webUrl}/getOfficeByType?officeType=01" class="link">
                        <img src="${webUrl}/img/office/duanqizulin.jpg" alt="" class="image"/>
                    </a>
                </li> 
                <li class="item">
                    <a href="${webUrl}/getOfficeByType?officeType=03" class="link">
                        <img src="${webUrl}/img/office/lianhebangong.jpg" alt="" class="image"/>
                    </a>
                </li> 
                <li class="item">
                    <a href="${webUrl}/getOfficeByType?officeType=02" class="link">
                        <img src="${webUrl}/img/office/changqizulin.jpg" alt="" class="image"/>
                    </a>
                </li> 
            </ul>
        </div>

        <div class="wrapper c-recommend">
            <h1 class="title">————　每周推荐　 ————</h1>
            <ul class="container f-clearfix">
            
				<s:iterator value="moreViewOfficeList" id="id">
					<s:if test="#id.officeAdvert==05">
		                <li class="item">
		                    <img src="<s:property value="#id.advertPhotoUrl" />" alt="" class="img long" />
		                    <div class="info">
		                        <p class="name"><s:property value="#id.officeName" /></p>
		                        <span class="price">
		                        	<s:if test="#id.eachPrice!=0 && #id.eachPrice!=null">
		                        		&yen; <b><s:property value="#id.eachPrice" /></b>元/位
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
		                        </span>
		                    </div>
		                    <div class="shadow">
		                        <a target="_blank" href="${webUrl}/officeDetail?officeId=<s:property value="#id.officeId" />" class="link"></a>
		                    </div>
		                </li>
					</s:if>
					<s:else>
		                <li class="item">
		                    <img src="<s:property value="#id.advertPhotoUrl" />" alt="" class="img" />
		                    <div class="info">
		                        <p class="name"><s:property value="#id.officeName" /></p>
		                        <span class="price">
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
		                        </span>
		                    </div>
		                    <div class="shadow">
		                        <a target="_blank" href="${webUrl}/officeDetail?officeId=<s:property value="#id.officeId" />" class="link"></a>
		                    </div>
		                </li>
					</s:else>
					
				</s:iterator>
            
            
<%--                 <li class="item">
                    <img src="${webUrl}/officeImg/2015-07-19-1bd5fe45.jpg" alt="" class="img long" />
                    <div class="info">
                        <p class="name">你是一只渣渣森</p>
                        <span class="price">&yen;<em>5毛</em></span>
                    </div>
                    <div class="shadow">
                        <a href="#" class="link"></a>
                    </div>
                </li>
                <li class="item">
                    <img src="${webUrl}/officeImg/2015-07-19-1bd5fe45.jpg" alt="" class="img" />
                    <div class="info">
                        <p class="name">你是一只渣渣森111111</p>
                        <span class="price">&yen;<em>5毛</em></span>
                    </div>
                    <div class="shadow">
                        <a href="#" class="link"></a>
                    </div>
                </li>
                <li class="item">
                    <img src="${webUrl}/officeImg/2015-07-19-1bd5fe45.jpg" alt="" class="img" />
                    <div class="info">
                        <p class="name">你是一只渣渣森</p>
                        <span class="price">&yen;<em>5毛</em></span>
                    </div>
                    <div class="shadow">
                        <a href="#" class="link"></a>
                    </div>
                </li>
                <li class="item">
                    <img src="${webUrl}/officeImg/2015-07-19-1bd5fe45.jpg" alt="" class="img" />
                    <div class="info">
                        <p class="name">你是一只渣渣森</p>
                        <span class="price">&yen;<em>5毛</em></span>
                    </div>
                    <div class="shadow">
                        <a href="#" class="link"></a>
                    </div>
                </li>
                <li class="item">
                    <img src="${webUrl}/officeImg/2015-07-19-1bd5fe45.jpg" alt="" class="img" />
                    <div class="info">
                        <p class="name">你是一只渣渣森</p>
                        <span class="price">&yen;<em>5毛</em></span>
                    </div>
                    <div class="shadow">
                        <a href="#" class="link"></a>
                    </div>
                </li> --%>
                
            </ul>
        </div>
        
        <div class="wrapper c-partners ">
            <h1 class="title">————　他们都选择了Needoffice　 ————</h1>
            <ul class="pure-g-r f-clearfix">
                <li class="pure-u-1-5 f-fl c-border-r c-border-b"><a href="http://www.dreamvillage.com.cn/dreamTown/index.htm" target="view-window"><img src="${webUrl}/img/partners/mengxiangxiaozhen.png" alt=""></a></li>
                <li class="pure-u-1-5 f-fl c-border-r c-border-b"><a href="http://itjuzi.com/company/666" target="view-window"><img src="${webUrl}/img/partners/juzikongjian.png" alt=""></a></li>
                <li class="pure-u-1-5 f-fl c-border-r c-border-b"><a href="###" ><img src="${webUrl}/img/partners/xihuchuangkehui.png" alt=""></a></li>
                <li class="pure-u-1-5 f-fl c-border-r c-border-b"><a href="###" target=""><img src="${webUrl}/img/partners/jikechuangyeying.png" alt=""></a></li>
                <li class="pure-u-1-5 f-fl c-border-b"><a href="http://wuspace.com/" target="view-window"><img src="${webUrl}/img/partners/wukongjian.png" alt=""></a></li>
                <li class="pure-u-1-5 f-fl c-border-r"><a href="http://angelcrunch.com/" target="view-window"><img src="${webUrl}/img/partners/tianshihui.png" alt=""></a></li>
                <li class="pure-u-1-5 f-fl c-border-r"><a href="https://www.nashangban.com/" target="view-window"><img src="${webUrl}/img/partners/nashangban.png" alt=""></a></li>
                <li class="pure-u-1-5 f-fl c-border-r"><a href="###" ><img src="${webUrl}/img/partners/yingjia.png" alt=""></a></li>
                <li class="pure-u-1-5 f-fl c-border-r"><a href="http://www.edianzu.cn/" target="view-window"><img src="${webUrl}/img/partners/yidianzu.png" alt=""></a></li>
                <li class="pure-u-1-5 f-fl"><a href="https://www.ilaw66.com/" target="view-window"><img src="${webUrl}/img/partners/falvwang.png" alt=""></a></li>
               
                
            </ul>
        </div>
        
        <div class="c-steps">
            <img src="${webUrl}/img/steps2.png" alt="" style="display:block;">
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
            <textarea name="message" id="" rows="10"></textarea>
        </form>
    </script>

<jsp:include page="../../../view/layout/footer.jsp">
    <jsp:param name="pageName" value="home" />
</jsp:include>