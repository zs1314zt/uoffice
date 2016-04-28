<%@ page language="java" import="java.util.*" import="java.lang.String" import="com.pre.team.uoffice.domain.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../../../view/layout/header.jsp">
    <jsp:param name="pageName" value="release" />
    <jsp:param name="pageTitle" value="房源发布" />
</jsp:include>

<% 
    Office uoffice = (Office) request.getAttribute("office");
    String officeType = null;
    String officeAdvert = null;
    String advertPhotoUrl = null;
    String bannerUrl = null;
    String mapDesc = null;
    String mapUrl = null;
    String photoUrl = null;
    
    if(uoffice != null){
        officeType = uoffice.getOfficeType();
        officeAdvert = uoffice.getOfficeAdvert();  
        advertPhotoUrl = uoffice.getAdvertPhotoUrl();
        bannerUrl = uoffice.getBannerUrl();
        mapDesc = uoffice.getMapDesc();
        mapUrl = uoffice.getMapUrl();
        photoUrl = uoffice.getPhotoUrl();
    }
    
    Map<String, String> offTypMap = new HashMap<String, String>();
    offTypMap.put("01", "联合办公");
    offTypMap.put("02", "孵化器");
    offTypMap.put("03", "短期租赁");
    offTypMap.put("04", "长期租赁");
    
    if(uoffice != null){
        officeType = uoffice.getOfficeType();
    }
    
    Map<String, String> offAdvMap = new HashMap<String, String>();
    offAdvMap.put("01", "01");
    offAdvMap.put("02", "02");
    offAdvMap.put("03", "03");
    offAdvMap.put("04", "04");
    offAdvMap.put("05", "05");
    
    String areaCode = (String) request.getAttribute("areaCode");
    String townCode = (String) request.getAttribute("townCode");
    
%>

<div class="g-bd">
    <div class="wrapper">
        <div class="c-leftbox">
            <form class="pure-form pure-form-aligned c-form J_release_form">
                <!-- 办公室id -->
                <input type="hidden" name="officeId" value="${office.officeId}"/>
                <div class="pure-control-group">
                    <label>办公室类型</label>
                    <select name="officeType">    
                       <%
                       for(Map.Entry<String, String> entity : offTypMap.entrySet()){
                           if(entity.getKey().equals(officeType)){
                               out.println("<option value='"+entity.getKey()+"' selected='selected'>"+entity.getValue()+"</option>");
                           }else{
                               out.println("<option value='"+entity.getKey()+"'>"+entity.getValue()+"</option>");
                           }
                       }
                       %>
                    </select>
                </div>
                <div class="pure-control-group">
                    <label >区域位置:</label>
                    <div class="pure-u-1-6">
                        <select name="county">
                            <%
                                if(areaCode == null || "".equals(areaCode)){
                            %>
                                    <option value="">请选择区县</option>
                            <% 
                                }
                            %>
                            <s:iterator value="arealist" id="item" status="st">
                                <s:if test="#item.locationCode==#request.areaCode">
                                    <option value="<s:property value="#item.locationCode" />" selected="selected"><s:property value="#item.locationName" /></option>
                                </s:if>
                                <s:else>
                                    <option value="<s:property value="#item.locationCode" />"><s:property value="#item.locationName" /></option>
                                </s:else>
                            </s:iterator>
                        </select>
                    </div>
                    <div class="pure-u-1-9">
                        <select name="locationId">
                            <%
                                if(townCode == null || "".equals(townCode)){
                            %>
                                    <option value="">请选择板块</option>
                            <% 
                                }
                            %>
                            <s:iterator value="townlist" id="item" status="st">
                                <s:if test="#item.locationCode==#request.townCode">
                                    <option value="<s:property value="#item.locationCode" />" selected="selected"><s:property value="#item.locationName" /></option>
                                </s:if>
                                <s:else>
                                    <option value="<s:property value="#item.locationCode" />"><s:property value="#item.locationName" /></option>
                                </s:else>
                            </s:iterator>
                        </select>
                    </div>
                </div>
                <div class="pure-control-group">
                    <label >详细地址:</label>
                    <input type="text" name="detailAddr" value="${office.detailAddr}" class="pure-u-2-7"/>
                </div>
                <div class="pure-control-group">
                    <label >办公人数:</label>
                    <input type="number" name="officeSize" min="1" value="${office.officeSize}" class="pure-u-2-7"/>
                </div>
                <div class="pure-control-group">
                    <label >月租金:</label>
                    <input type="number" name="officeMoney" min="1" step="500" value="${office.officeMoney}" class="pure-u-2-7"/>
                </div>
               	<div class="pure-control-group">
                    <label >办公室面积:</label>
                    <input type="number" name="officeArea" min="1" step="1" value="${office.officeArea}" class="pure-u-2-7"/>
                </div>
                <div class="pure-control-group">
                    <label >单个工位价钱:</label>
                    <input type="number" name="eachPrice" min="1" step="100" value="${office.eachPrice}" class="pure-u-2-7"/>
                </div>
                <div class="pure-control-group">
                    <label >最短租期:</label>
                    <input type="number" name="rentDate" min="1" value="${office.rentDate}" class="pure-u-2-7"/>
                </div>
               
                <div class="pure-control-group">
                    <label >办公室名称:</label>
                    <input type="text" name="officeName" value="${office.officeName}" class="pure-u-2-7"/>
                </div>
                <div class="pure-control-group">
                    <label >办公室简单描述:</label>
                    <input type="text" name="officeDesc" value="${office.officeDesc}" class="pure-u-2-7"/>
                </div>
                <div class="pure-control-group">
                    <label >联系方式:</label>
                    <input type="text" name="officeContact" value="${office.officeContact}" class="pure-u-2-7"/>
                </div>
                <div class="pure-control-group">
                    <label >详情介绍:</label>
                    <textarea name="officeContent" class="pure-u-2-7">${office.officeContent}</textarea>
                </div>
                <div class="pure-control-group">
                    <label >交通信息:</label>
                    <textarea name="mapDesc" class="pure-u-2-7">${office.mapDesc}</textarea>
                </div>
                <div class="pure-control-group">
                    <label >交通地图:</label>
                    <div class="J_map pure-u-2-7">
                        <input type="file" name="mapUrl" id="J_up_map"/>
                        <div class="J_uploadify_map c-uploadify-map">
	                    <%
	                        if(mapUrl != null){
	                    %>
	                        <div class="up-img">
	                            <img src="<%=mapUrl %>" width="285" height="105" data-src="<%=mapUrl %>">
	                            <div class="cancel" data-src="<%=mapUrl %>" style="visibility: hidden;">x</div>
	                        </div>
	                    <%
	                        }
	                    %>
                        </div>
                    </div>
                </div>
                <input type="hidden" value="${office.photoUrl}" name="photoUrl"/>
                <input type="hidden" value="${office.advertPhotoUrl}" name="adUrl"/>
                <input type="hidden" value="${office.bannerUrl}" name="bannerUrl"/>
                <input type="hidden" value="${office.mapUrl}" name="mapUrl"/>
            </form>
        </div>

        <div class="c-centerbox">
            <div class="J_fileform c-uploader">
                <p>办公室照片:</p>
                <input type="file" name="file" id="J_up_office"/>
                <div class="J_uploadify_img c-uploadify-img">
                <s:iterator value="office.photoUrls" id='number'>
                    <div class="up-img">
                        <img src="<s:property value='number'/>" width="85" height="105" data-src="<s:property value='number'/>">
                        <div class="cancel" data-src="<s:property value='number'/>" style="visibility: hidden;">x</div>
                    </div>
                </s:iterator>
                </div>
            </div>
        </div>

        <div class="c-rightbox">
            <div class="detail">
                <p>1.所有字段和办公室照片图片必填！</p>
                <p>2.详细地址填写小区、单元号、门牌号；</p>
                <p>3.联系方式可填写手机或者邮箱号；</p>
                <p>4.办公室照片图片要至少上传一张才能提交表单，上限为五张；</p>
            </div>
            <div class="c-ad J_adForm">
                <div class="adtype">
                    <label>广告:</label>
                    <select name="officeAdvert" id="" class="pure-u-1-7">
                    
                       <%
                           if(officeAdvert == null || "".equals(officeAdvert)){
                       %>
                               <option value="">请选择</option>
                       <% 
                           }
                       %>
                       <%
                       for(Map.Entry<String, String> entity : offAdvMap.entrySet()){
                           if(entity.getKey().equals(officeAdvert)){
                               out.println("<option value='"+entity.getKey()+"' selected='selected'>"+entity.getValue()+"</option>");
                           }else{
                               out.println("<option value='"+entity.getKey()+"'>"+entity.getValue()+"</option>");
                           }
                       }
                       %>
                    </select>
                </div>
                <input type="file" name="advertPhotoUrl" id="J_up_ad"/>
                <div class="J_uploadify_ad c-uploadify-ad">
                    <%
                        if(advertPhotoUrl != null){
                    %>
                        <div class="up-img">
                            <img src="<%=advertPhotoUrl %>" width="312" height="105" data-src="<%=advertPhotoUrl %>">
                            <div class="cancel" data-src="<%=advertPhotoUrl %>" style="visibility: hidden;">x</div>
                        </div>
                    <%
                        }
                    %>
                </div>
            </div>
            <div class="J_bannerform c-uploader">
                <p>详情页横幅栏位照片:</p>
                <input type="file" name="bannerUrl" id="J_up_banner"/>
                <div class="J_uploadify_banner c-uploadify-img">
                    <%
                        if(bannerUrl != null){
                    %>
                        <div class="up-img">
                            <img src="<%=bannerUrl %>" width="312" height="105" data-src="<%=bannerUrl %>">
                            <div class="cancel" data-src="<%=bannerUrl %>" style="visibility: hidden;">x</div>
                        </div>
                    <%
                        }
                    %>
                </div>
            </div>
            <div class="c-submit J_submit">
                <button type="submit" class="pure-button pure-button-primary J_officebtn">提交房源</button>
            </div>
            
        </div>
    </div>
</div>

<script>
window.pageData = {

};
//保存上传图片的设置
window.pageData.office = {
    swf: '${webUrl}/staticfile/src/lib/uploadify/uploadify.swf',
    uploader: '${webUrl}/upLoadOfficeImg',
    cancelImg: '${webUrl}/staticfile/src/lib/uploadify/uploadify-cancel.png',
    folder: '${webUrl}/officeImg',
    buttonText: '上传图片',
    fileTypeDesc: 'Image Files',
    fileTypeExts: '*.gif; *.jpg; *.png',
    fileSizeLimit : 10485760,
    queueSizeLimit: 8,
    removeTimeout: 3,
    fileObjName: 'file',
    auto: true,
    multi: true
}
window.pageData.ad = {
    swf: '${webUrl}/staticfile/src/lib/uploadify/uploadify.swf',
    uploader: '${webUrl}/upLoadOfficeImg',
    cancelImg: '${webUrl}/staticfile/src/lib/uploadify/uploadify-cancel.png',
    folder: '${webUrl}/officeImg',
    buttonText: '上传图片',
    fileTypeDesc: 'Image Files',
    fileTypeExts: '*.gif; *.jpg; *.png',
    fileSizeLimit : 10485760,
    removeTimeout: 3,
    fileObjName: 'file',
    auto: true,
    multi: false
}
window.pageData.map = {
    swf: '${webUrl}/staticfile/src/lib/uploadify/uploadify.swf',
    uploader: '${webUrl}/upLoadOfficeImg',
    cancelImg: '${webUrl}/staticfile/src/lib/uploadify/uploadify-cancel.png',
    folder: '${webUrl}/officeImg',
    buttonText: '上传图片',
    fileTypeDesc: 'Image Files',
    fileTypeExts: '*.gif; *.jpg; *.png',
    fileSizeLimit : 10485760,
    removeTimeout: 3,
    fileObjName: 'file',
    auto: true,
    multi: false
}
</script>

<jsp:include page="../../../view/layout/footer.jsp">
    <jsp:param name="pageName" value="release" />
</jsp:include>
