var hpx=document.getElementById('hh_paixu'),
	hpd=hpx.getElementsByTagName('p')[0],
	hpp=document.getElementsByClassName('hh_pp')[0];
	plis=hpp.getElementsByTagName('li');
	// alert(plis.length);
var hsot=document.getElementById('hh_sort'),
	opts=hsot.getElementsByTagName('option');
	// alert(opts.length);
var sign=1;
	hpd.onclick=function () {
		if (sign==1) {
			hpp.style.display='block';
			hpd.style.background='url(../../images/news/images/hh_up.png) 80px 7px no-repeat';
			sign=0;
		}else{
			hpp.style.display='none';
			hpd.style.background='url(../../images/news/images/hh_down.png) 80px 7px no-repeat';
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

var hsx=document.getElementById('hh_saixuan'),
	hsd=hsx.getElementsByTagName('p')[0],
	hss=document.getElementsByClassName('hh_ss')[0],
	slis=hss.getElementsByTagName('li'),
	hsrn=document.getElementById('hh_screen'),
	opts_2=hsrn.getElementsByTagName('option');
	// alert(opts_2.length);
	hsd.onclick=function () {
		if (sign==1) {
			hss.style.display='block';
			hsd.style.background='url(../../images/news/images/hh_up.png) 62px 8px no-repeat';
			sign=0;
		}else{
			hss.style.display='none';
			hsd.style.background='url(../../images/news/images/hh_down.png) 62px 8px no-repeat';
			sign=1;
		}
	}
	for (var i = 0; i < slis.length; i++) {
		slis[i].index=i;
		slis[i].onclick=function () {
			// var _this[i]=this[i];
			for (var v = 0; v < opts.length; v++) {
				opts_2[v].removeAttribute("selected");
			}
			opts_2[this.index].setAttribute("selected", "selected");
		}
	};