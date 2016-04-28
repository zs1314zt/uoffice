<%@ page language="java" import="java.util.*" import="java.lang.String" import="com.pre.team.uoffice.domain.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../../../view/layout/header.jsp">
    <jsp:param name="pageName" value="server" />
    <jsp:param name="pageTitle" value="Needoffice" />
</jsp:include>

<div class="g-bd f-clearfix">
    <div class="wrapper">
        <div class="c-nav J_nav">
            <div class="title">公司概况</div>
            <div class="nav" data-name="about">关于我们</div>
            <div class="nav" data-name="biz">商务合作</div>
            <div class="nav" data-name="join">加入我们</div>
            <div class="nav" data-name="find">寻找办公场所</div>
            <div class="nav" data-name="release">发布办公场所</div>
            <div class="nav" data-name="contact">联系我们</div>
        </div>
        <div class="g-mnc">
            <div class="banner">关心，从办公室开始</div>
            <div class="box J_box about">
                <div class="title"><span>关于我们</span></div>
                <p>关心，从办公室开始</p>
                <p>Needoffice“你的办公”是提供办公场地租赁及其他企业硬服务的C2B,O2O平台。真正的双向免费，真实房源保证。关心，从办公室开始。</p>
                <p>传统的租房平台充斥着各式各样的房源发布者，一般要求付中介或其他形式的费用，另外一个弊端就是其本质并没有解决需找办公场地的问题，各类信息膨胀的租赁网站让用户无法针对性的寻找自己需要的办公场地。Needoffice所做的并不是什么颠覆性的事情，我们只是做好一件事，为创业者找到适合的场地。</p>
                <p> 在这条路上，我们结实了很多意气相合的小伙伴与Needoffice合作，如果您需要办公家具定制，电脑租赁等服务，也可以联系我们。</p>
            </div>
            <div class="box J_box biz" style="display: none">
                <div class="title"><span>商务合作</span></div>
                <p>Needoffice欢迎各类孵化器，联合办公、科技园、创业园、投资机构与我们合作，也希望有更多的各行业为创业者服务的小伙伴加入我们的行列。欢迎联系邮箱： <a href="###">bd@needoffice.cn</a></p>
                
            </div>
            <div class="box J_box join" style="display: none">
                <div class="title"><span>加入我们</span></div>
                <p>岗位：BD商务推广（2名）</p>
                <p>工作：主要负责和创业孵化区，园区，政府机构进行沟通，建立长期合作的关系，不断给我们输出客户。</p>
                <p style="margin:0">要求：女性 学历大专及以上学历</p>
                <p class="indent3">长相气质，人品好，有上进心，有责任感，有团队意识</p>
                <p class="indent3">语言表达能力强 社交能力强</p>
                <p class="indent3">对办公楼租赁行业熟悉者优先</p>
                <p class="indent3">房地产行业者熟悉者优先</p>
                <p class="indent3">家具销售多年经验者优先</p>
                <p>岗位：UI设计师（1名）</p>
                <p>工作：负责公司形象设计，如易拉宝，宣传单，名片等形象的设计，负责两个公众号的日常运营。</p>
                <p style="margin:0">要求：男女不限 学历大专及以上学历 文科专业优先</p>
                <p class="indent3">P图基本能力强，有创新意识，沟通能力强，有团队意识</p>
                <p class="indent3">有微信号运营经验者优先</p>
                <p class="indent3">有淘宝美工经验者优先</p>
            </div>
            <div class="box J_box find" style="display: none">
                <div class="title"><span>寻找办公场所</span></div>
                <p>方法一</p>
                <p>点击首页上部导航栏右侧的‘我要找房’的按钮，跳出提示表格。</p>
                <p>根据表格内容填写并提交，之后便会有运营人员与您对接，为您服务。</p>
                <p>方法二</p>
                <p>输入（可以不填）你需要寻找的地址和人数，点击搜索，加入搜索列表界面，您可以根据自己的要求点选筛选条件，挑选您心仪的办公场所。</p>
            </div>
            <div class="box J_box release" style="display: none">
                <div class="title"><span>发布办公场所</span></div>
                <p>邮件发送到 <a href="####">fabu@needoffice.cn</a></p>
                <p>主题：发布办公场所</p>
                <p>内容：您的联系方式，办公场所简介等</p>
                <p>收到邮件后，我们会有运营人员与您洽谈具体的合作方式，请耐心等待。</p>
            </div>
            <div class="box J_box contact" style="display: none">
                <div class="title"><span>联系我们</span></div>
                <p>客服电话：18868875384  </p>
                <p>客服邮箱：<a href="####">service@needoffice.cn</a></p>
            </div>
        </div>
    </div>
    
</div>

<jsp:include page="../../../view/layout/footer.jsp">
    <jsp:param name="pageName" value="server" />
</jsp:include>
