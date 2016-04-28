define(function(require,exports,module){
    var uploadify = require('../../lib/uploadify/jquery.uploadify-3.1.js');

    //页面初始化
    (function(win){
        var $county = $('.J_release_form [name="county"]');
        $county.on('change', function(e){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: window.location.origin + '/uoffice/getLocation',
                data: 'location_code=' + $county.val(),
            }).done(function(resp){
                var $location = $('.J_release_form [name="locationId"]');
                if (resp && resp.length) {
                    $location.empty();
                    $location.append('<option value="">请选择板块</option>');
                    for (var i = 0, j = resp.length; i < j; i++) {
                        $location.append('<option value="' + resp[i].locationCode +'">' + 
                            resp[i].locationName + '</option>');
                    }
                }
            })
        });
    })(window);

    //office图片处理相关
    (function(win){
        var officeform = document.forms[0],
            $form = $('.J_fileform'),
            $upOffice = $('#J_up_office'),
            $imgs = $('.J_uploadify_img'),
            pageData = win.pageData;
        
        $upOffice.uploadify({
            swf: pageData.office.swf,
            uploader: pageData.office.uploader,
            cancelImg: pageData.office.cancelImg,
            folder: pageData.office.folder,
            buttonText: pageData.office.buttonText,
            fileTypeDesc: pageData.office.fileTypeDesc,
            fileTypeExts: pageData.office.fileTypeExts,
            fileObjName: pageData.office.fileObjName,
            fileSizeLimit : pageData.office.fileSizeLimit,
            queueSizeLimit: pageData.office.queueSizeLimit,
            removeTimeout: pageData.office.removeTimeout,
            auto: pageData.office.auto,
            multi: pageData.office.multi,
            onUploadStart : function(file) {//当文件选择对话框关闭时触发
                var length = $imgs.find('.up-img').length;
                var myself = this; 
                if(length >= 8) {
                    myself.cancelUpload(file.id); 
                    $('#' + file.id).remove();
                    window.lib.noty('办公室类型图片不得超过8张', {
                        type:'danger'
                    });
                }
            },
            onUploadSuccess: function (file, data, response) {
                var jsonData = JSON.parse(data);
                var url = jsonData.photoUrl;
                $imgs.append('<div class="up-img">' +
                    '<img src="' + url + '" width="85" height="105" data-src="' + jsonData.photoUrl + '"/>' + 
                    '<div class="cancel" data-src="' + jsonData.photoUrl + '">x</div>' +
                    '</div>'
                );
                officeform.photoUrl.value += jsonData.photoUrl + ';';
            }
        });
        $imgs.on('mouseover mouseenter', '.up-img', function(e){
            var $this = $(this);
            $this.find('.cancel').css('visibility', 'visible');
        });
        $imgs.on('mouseout mouseleave', '.up-img', function(e){
            var $this = $(this);
            $this.find('.cancel').css('visibility', 'hidden');
        });
        $imgs.on('click', '.cancel', function(){
            var $this = $(this),
                dataSrc = $this.attr('data-src'),
                urlSub = dataSrc.indexOf(';') === -1 ? dataSrc + ';' : dataSrc,
                urlTotal = officeform.photoUrl.value,
                urlResult = urlTotal.slice(0, urlTotal.indexOf(urlSub)) + 
                    urlTotal.slice(urlTotal.indexOf(urlSub) + urlSub.length);
                
            $this.parent().remove();
            officeform.photoUrl.value = urlResult;
        });
    })(window); 
    
    //ad图片处理相关
    (function(win){

        var officeform = document.forms[0],
            $form = $('.J_fileform'),
            $upAd = $('#J_up_ad'),
            $imgs = $('.J_uploadify_ad'),
            pageData = win.pageData;
        
        $upAd.uploadify({
            swf: pageData.ad.swf,
            uploader: pageData.ad.uploader,
            cancelImg: pageData.ad.cancelImg,
            folder: pageData.ad.folder,
            buttonText: pageData.ad.buttonText,
            fileTypeDesc: pageData.ad.fileTypeDesc,
            fileTypeExts: pageData.ad.fileTypeExts,
            fileObjName: pageData.ad.fileObjName,
            fileSizeLimit : pageData.ad.fileSizeLimit,
            removeTimeout: pageData.ad.removeTimeout,
            auto: pageData.ad.auto,
            multi: pageData.ad.multi,
            onUploadStart : function(file) {//当文件选择对话框关闭时触发
                var length = $imgs.find('.up-img').length;
                var myself = this; 
                if(length >= 1) {
                    myself.cancelUpload(file.id); 
                    $('#' + file.id).remove();
                    window.lib.noty('广告图只能有一张', {
                        type:'danger'
                    });
                }
            },
            onUploadSuccess: function (file, data, response) {
                var jsonData = JSON.parse(data);
                var url = jsonData.photoUrl;
                $imgs.append('<div class="up-img" style="width:312px;">' +
                    '<img src="' + url + '" width="312" height="105" data-src="' + jsonData.photoUrl + '"/>' + 
                    '<div class="cancel" data-src="' + jsonData.photoUrl + '">x</div>' +
                    '</div>'
                );
                officeform.adUrl.value += jsonData.photoUrl + ';';
            }
        });
        $imgs.on('mouseover mouseenter', '.up-img', function(e){
            var $this = $(this);
            $this.find('.cancel').css('visibility', 'visible');
        });
        $imgs.on('mouseout mouseleave', '.up-img', function(e){
            var $this = $(this);
            $this.find('.cancel').css('visibility', 'hidden');
        });
        $imgs.on('click', '.cancel', function(){
            var $this = $(this);  
            $this.parent().remove();
            officeform.adUrl.value = '';
        });
    })(window); 

    //banner图片处理相关
    (function(win){

        var officeform = document.forms[0],
            $form = $('.J_fileform'),
            $upAd = $('#J_up_banner'),
            $imgs = $('.J_uploadify_banner'),
            pageData = win.pageData;
        
        $upAd.uploadify({
            swf: pageData.ad.swf,
            uploader: pageData.ad.uploader,
            cancelImg: pageData.ad.cancelImg,
            folder: pageData.ad.folder,
            buttonText: pageData.ad.buttonText,
            fileTypeDesc: pageData.ad.fileTypeDesc,
            fileTypeExts: pageData.ad.fileTypeExts,
            fileObjName: pageData.ad.fileObjName,
            fileSizeLimit : pageData.ad.fileSizeLimit,
            removeTimeout: pageData.ad.removeTimeout,
            auto: pageData.ad.auto,
            multi: pageData.ad.multi,
            onUploadStart : function(file) {//当文件选择对话框关闭时触发
                var length = $imgs.find('.up-img').length;
                var myself = this; 
                if(length >= 1) {
                    myself.cancelUpload(file.id); 
                    $('#' + file.id).remove();
                    window.lib.noty('详情页横幅图只能有一张', {
                        type:'danger'
                    });
                }
            },
            onUploadSuccess: function (file, data, response) {
                var jsonData = JSON.parse(data);
                var url = jsonData.photoUrl;
                $imgs.append('<div class="up-img" style="width:312px;">' +
                    '<img src="' + url + '" width="312" height="105" data-src="' + jsonData.photoUrl + '"/>' + 
                    '<div class="cancel" data-src="' + jsonData.photoUrl + '">x</div>' +
                    '</div>'
                );
                officeform.bannerUrl.value += jsonData.photoUrl + ';';
            }
        });
        $imgs.on('mouseover mouseenter', '.up-img', function(e){
            var $this = $(this);
            $this.find('.cancel').css('visibility', 'visible');
        });
        $imgs.on('mouseout mouseleave', '.up-img', function(e){
            var $this = $(this);
            $this.find('.cancel').css('visibility', 'hidden');
        });
        $imgs.on('click', '.cancel', function(){
            var $this = $(this);
            $this.parent().remove();
            officeform.bannerUrl.value = '';

        });
    })(window); 
    
    //map图片处理相关
    (function(win){

        var officeform = document.forms[0],
            $form = $('.J_fileform'),
            $upAd = $('#J_up_map'),
            $imgs = $('.J_uploadify_map'),
            pageData = win.pageData;
        
        $upAd.uploadify({
            swf: pageData.ad.swf,
            uploader: pageData.ad.uploader,
            cancelImg: pageData.ad.cancelImg,
            folder: pageData.ad.folder,
            buttonText: pageData.ad.buttonText,
            fileTypeDesc: pageData.ad.fileTypeDesc,
            fileTypeExts: pageData.ad.fileTypeExts,
            fileObjName: pageData.ad.fileObjName,
            fileSizeLimit : pageData.ad.fileSizeLimit,
            removeTimeout: pageData.ad.removeTimeout,
            auto: pageData.ad.auto,
            multi: pageData.ad.multi,
            onUploadStart : function(file) {//当文件选择对话框关闭时触发
                var length = $imgs.find('.up-img').length;
                var myself = this; 
                if(length >= 1) {
                    myself.cancelUpload(file.id); 
                    $('#' + file.id).remove();
                    window.lib.noty('地图只能有一张', {
                        type:'danger'
                    });
                }
            },
            onUploadSuccess: function (file, data, response) {
                var jsonData = JSON.parse(data);
                var url = jsonData.photoUrl;
                $imgs.append('<div class="up-img" style="width:285px;">' +
                    '<img src="' + url + '" width="285" height="105" data-src="' + jsonData.photoUrl + '"/>' + 
                    '<div class="cancel" data-src="' + jsonData.photoUrl + '">x</div>' +
                    '</div>'
                );
                officeform.mapUrl.value += jsonData.photoUrl + ';';
            }
        });
        $imgs.on('mouseover mouseenter', '.up-img', function(e){
            var $this = $(this);
            $this.find('.cancel').css('visibility', 'visible');
        });
        $imgs.on('mouseout mouseleave', '.up-img', function(e){
            var $this = $(this);
            $this.find('.cancel').css('visibility', 'hidden');
        });
        $imgs.on('click', '.cancel', function(){
            var $this = $(this);
            $this.parent().remove();
            officeform.mapUrl.value = '';

        });
    })(window); 

    //表单提交相关
    function hasEmpty(data) {
        var check = false;
        _.each(data, function(v,k){
            if(!v){
                check = true;
                return false;
            }
        });
        return check;
    }
    
    $('.J_officebtn').on('click', function(e){
        e.preventDefault();
        
        var form = document.forms[0];
        var office = {
            officeType: form.officeType.value,
            locationId: form.locationId.value,
            detailAddr: form.detailAddr.value,
            officeSize: form.officeSize.value,
            officeMoney: form.officeMoney.value,
            officeArea: form.officeArea.value,
            eachPrice: form.eachPrice.value,
            rentDate: form.rentDate.value,
            officeContent: form.officeContent.value,
            officeName: form.officeName.value,
            officeDesc: form.officeDesc.value,
            officeContact: form.officeContact.value,
            photoUrl : form.photoUrl.value,
            bannerUrl: form.bannerUrl.value,
            mapDesc: form.mapDesc.value,
            mapUrl: form.mapUrl.value
        };

        console.log(office);
        if (hasEmpty(office)) {
            window.lib.noty('请填写完所有的字段', {
                type:'danger'
            });
            return false;
        }

        office.officeId = form.officeId.value;
        office.officeAdvert = $('.J_adForm select').val();
        office.advertPhotoUrl = form.adUrl.value;

        $.ajax({
            type : 'POST',
            url : window.location.origin + '/uoffice/publishOrUpdateOfficeInfo',
            data: 'office.officeType=' + office.officeType + '&office.locationId=' + office.locationId +
                '&office.detailAddr=' + office.detailAddr + '&office.officeSize=' + office.officeSize + 
                '&office.officeMoney=' + office.officeMoney + '&office.eachPrice=' + office.eachPrice +
                '&office.officeArea=' + office.officeArea +
                '&office.rentDate=' + office.rentDate + '&office.officeContent=' + office.officeContent +
                '&office.officeName=' + office.officeName + '&office.officeDesc=' + office.officeDesc + 
                '&office.officeContact=' + office.officeContact + '&office.photoUrl=' + office.photoUrl+
                '&office.advertPhotoUrl=' + office.advertPhotoUrl + '&office.officeAdvert=' + office.officeAdvert + 
                '&office.bannerUrl=' + office.bannerUrl + '&office.officeId=' + office.officeId +
                '&office.mapDesc=' + office.mapDesc + '&office.mapUrl=' + office.mapUrl,
            success: function(data){
                window.lib.noty('发布成功', {
                    type:'success'
                });
            }
        });
    });
});