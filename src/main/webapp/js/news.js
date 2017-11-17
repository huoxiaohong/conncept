window.onload = function() {
	var hpx = document.getElementById('hh_paixu');
	var hpd = hpx.getElementsByTagName('p')[0];
	var hpp = document.getElementsByClassName('hh_pp')[0];
	var sign1 = 1;
	var hsx = document.getElementById('hh_saixuan');
	var hsd = hsx.getElementsByTagName('p')[0];
	var hss = document.getElementsByClassName('hh_ss')[0];
	var sign2 = 1;
	hpx.onclick = function() {
		var e = event || window.event;
		e.stopPropagation();
		if (sign1 == 1 || sign2 == 0) {
			hpp.style.display = 'block';
			hpd.style.background = 'url(../../images/news/images/hh_up.png) 80px 7px no-repeat';
			hpd.style.color = '#09a25f';
			hss.style.display = 'none';
			hsd.style.background = 'url(../../images/news/images/hh_down.png) 62px 7px no-repeat';
			hsd.style.color = '#000';
			sign1 = 0;
			sign2 = 1;
		} else {
			hpp.style.display = 'none';
			hpd.style.background = 'url(../../images/news/images/hh_down.png) 80px 7px no-repeat';
			hpd.style.color = '#000';
			sign1 = 1;
		}
	};

	hsx.onclick = function() {
		var e = event || window.event;
		e.stopPropagation();
		if (sign2 == 1 || sign1 == 0) {
			hss.style.display = 'block';
			hsd.style.background = 'url(../../images/news/images/hh_up.png) 62px 7px no-repeat';
			hsd.style.color = '#09a25f';
			hpp.style.display = 'none';
			hpd.style.background = 'url(../../images/news/images/hh_down.png) 80px 7px no-repeat';
			hpd.style.color = '#000';
			sign2 = 0;
			sign1 = 1;
		} else {
			hss.style.display = 'none';
			hsd.style.background = 'url(../../images/news/images/hh_down.png) 62px 7px no-repeat';
			hsd.style.color = '#000';
			sign2 = 1;
		}
	};
	document.onclick = function() {
		if (sign1 == 0) {
			hpp.style.display = 'none';
			hpd.style.background = 'url(../../images/news/images/hh_down.png) 80px 7px no-repeat';
			hpd.style.color = '#000';
			sign1 = 1;
		}
		if (sign2 == 0) {
			hss.style.display = 'none';
			hsd.style.background = 'url(../../images/news/images/hh_down.png) 62px 7px no-repeat';
			hsd.style.color = '#000';
			sign2 = 1;
		}
	};

	var search = document.getElementById('sb-icon-search');
	search.onclick = function() {
		hpp.style.display = 'none';
		hpd.style.background = 'url(../../images/news/images/hh_down.png) 80px 7px no-repeat';
		hpd.style.color = '#000';
		hss.style.display = 'none';
		hsd.style.background = 'url(../../images/news/images/hh_down.png) 62px 7px no-repeat';
		hsd.style.color = '#000';
	};

};
