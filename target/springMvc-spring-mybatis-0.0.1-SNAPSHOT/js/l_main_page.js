jQuery.noConflict();
jQuery(document).ready(function () {
    //左侧导航栏按钮点击后样式
    jQuery(".l_l_nav li a").click(function () {
        jQuery(this).css('color', '#09a25f').parent().siblings().children().css('color', '#5a5858');

    });


    //更改编辑按钮和提交按钮样式，
    jQuery(".l_hr_innerl .btn #edit").toggle(function () {
        jQuery(this).html('保存');
        jQuery("#submit").css('background', '#666');
        jQuery("#tj").css('display', 'block');
        jQuery("table").css('color', '#aaa');
        jQuery("textarea").removeAttr('readonly');
    }, function () {
        jQuery(this).html('编辑');
        jQuery("table").css('color', '#585858');
        jQuery("#submit").css('background', '#09a25f');
        jQuery("#tj").css('display', 'none');
        jQuery('textarea').attr("readonly", "readonly");
        jQuery("#submit").click(function () {
            return;
        });

    });


    //弹出蒙版
    jQuery(".l_hr_innerl .btn #submit").click(function () {
        jQuery("#mask").css('display', 'block');
        jQuery(".promot").css('display', 'block');
        var bodyT = (jQuery(document.body).height() - jQuery(".promot").height()) / 2;
        var bodyW = (jQuery(document.body).width() - jQuery(".promot").width()) / 2;
        jQuery(".promot").css('top', bodyT + 'px');
        jQuery(".promot").css('left', bodyW + 'px');
        // jQuery(".promot").find(0).style.top=bodyT;
    });

    //蒙版提示框，点击后隐藏蒙版
    jQuery(".promot #yes").click(function () {
        jQuery("#mask").css('display', 'none');
        jQuery(".promot").css('display', 'none');
    });


    jQuery(".promot #no").click(function () {
        jQuery("#mask").css('display', 'none');
        jQuery(".promot").css('display', 'none');
    });


});
