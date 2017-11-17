/*
 *
 */

/*---------------排序方式------------------------*/
var hpx = document.getElementById('c_paixu'),
    hpd = document.getElementById('c_pdown'),
    hpp = document.getElementById('c_pp'),
    /*hpp=document.getElementsByClassName('c_pp')[0];*/

    plis = hpp.getElementsByTagName('li');
// alert(plis.length);
// alert(hpp);
var hsot = document.getElementById('c_sort'),
    opts = hsot.getElementsByTagName('option');
// alert(opts.length);
var sign = 1;
hpd.onclick = function () {
    if (sign == 1) {
        hpp.style.display = 'block';
        hpd.style.background = 'url(../../images/s_up.png) 80px 7px no-repeat';
        sign = 0;
    } else {
        hpp.style.display = 'none';
        hpd.style.background = 'url(../../images/s_down.png) 80px 7px no-repeat';
        sign = 1;
    }
}
for (var i = 0; i < plis.length; i++) {
    plis[i].index = i;
    plis[i].onclick = function () {
        // var _this[i]=this[i];
        for (var v = 0; v < opts.length; v++) {
            opts[v].removeAttribute("selected");
        }
        opts[this.index].setAttribute("selected", "selected");
    }
}
;
/*---------------批量---------------------*/
var c_chacks = document.getElementsByClassName("c_chackc");
document.onclick = function (ev) {
    var e = ev || window.event;
    var Target = e.target || e.srcElement;
    if (Target.parentNode.id == 'c_chackc') {
        if (Target.parentNode.style.background == "") {
            Target.parentNode.style.cssText = "background: url(../../images/duihao_40.jpg) 0 0 no-repeat;border:none;width:20px;height:20px;";
        } else {
            Target.parentNode.style.cssText = "width:18px;height:18px;background:;border:1px solid #c9c9c9;";
        }
        ;
    }
    ;
}

