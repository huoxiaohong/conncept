/*
* @Author: Marte
* @Date:   2017-06-24 09:48:18
* @Last Modified by:   Marte
* @Last Modified time: 2017-07-04 15:37:57
*/

// document.write("<script language=javascript src='./c_my.js'></script>");
// document.write("<script language=javascript src='./jquery-1.10.2.min.js'></script>");
$(function(){
    $('.c_nvg li').each(function (i,target){
    $(target).click(function (){
        $(target).addClass('c_nvgl').siblings().removeClass('c_nvgl');
        $('.c_conter div').eq(i+1).addClass('c_conterrs').siblings().removeClass('c_conterrs');
    })
})
})
var hpx=document.getElementById('c_paixu'),
    hpd=document.getElementById('c_pdown'),
    hpp=document.getElementById('c_pp'),
    /*hpp=document.getElementsByClassName('c_pp')[0];*/

    plis=hpp.getElementsByTagName('li');
    // alert(plis.length);
    // alert(hpp);
var hsot=document.getElementById('c_sort'),
    opts=hsot.getElementsByTagName('option');
    // alert(opts.length);
var sign=1;
    hpd.onclick=function () {
        if (sign==1) {
            hpp.style.display='block';
            hpd.style.background='url(../../images/s_up.png) 80px 7px no-repeat';
            sign=0;
        }else{
            hpp.style.display='none';
            hpd.style.background='url(../../images/s_down.png) 80px 7px no-repeat';
            sign=1;
        }
    }
    for (var i = 0; i < plis.length; i++) {
        plis[i].index=i;
        plis[i].onclick=function () {
            // var _this[i]=this[i];
            for (var v = 0; v < opts.length; v++) {
                opts[v].removeAttribute("selected");
            }
            opts[this.index].setAttribute("selected", "selected");
        }
    };