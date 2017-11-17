$(document).ready(function(){
	//左侧导航栏按钮点击后样式
	$(".l_l_nav li a").click(function(){
		$(this).css('color','#09a25f').parent().siblings().children().css('color','#5a5858');
	
	});

	//分页器
	$(".l_r_con .page a").click(function(){
		$(this).css('color','#09a25f').siblings().css('color','#5a5858');
	});

	//更改编辑按钮和提交按钮样式，
	$(".l_hr_innerl .btn #edit").toggle(function(){
		$(this).html('保存');
		$("#submit").css('background','#666');
		$("table").css('color','#aaa');
		$("textarea").focus();
		
	},function(){
		$(this).html('编辑');
		$("table").css('color','#585858');
		$("#submit").css('background','#09a25f');
		$("#submit").click(function(){
			return;
		})

	})

	//弹出蒙版
	$(".l_hr_innerl .btn #submit").click(function(){
		$("#mask").css('display','block');
		$(".promot").css('display','block');
		var bodyT=($(document.body).height()-$(".promot").height())/2;
		var bodyW=($(document.body).width()-$(".promot").width())/2;
		$(".promot").css('top',bodyT+'px');
		$(".promot").css('left',bodyW+'px');
		// $(".promot").find(0).style.top=bodyT;
	})

	//蒙版提示框，点击后隐藏蒙版
	$(".promot .qr").click(function(){
		$("#mask").css('display','none');
		$(".promot").css('display','none');
	})


	$(".promot .qx").click(function(){
		$("#mask").css('display','none');
		$(".promot").css('display','none');
	})
	//下拉列表改换样式，显示和隐藏
	var flag1=1,flag2=1;
	$(".new_select .dt2").toggle(function(){
			$(".new_select .dt2").css('background','url(../../images/patient_classify/up.png) 75px 3px no-repeat');
			$(".new_select .dl1 dd").css('display','block');

	},function(){
		if (flag1==0) {
				$(".new_select .dt2").css('background','url(../../images/patient_classify/up.png) 75px 3px no-repeat');
				$(".new_select .dl1 dd").css('display','block');
				flag1=1;
		}
		else{
			$(".new_select .dt2").css('background','url(../../images/patient_classify/down.png) 70px 3px no-repeat')
			$(".new_select .dl1 dd").css('display','none');
		}
		
	})
	
	$(".new_select .dt1").toggle(function(){
		$(".new_select .dt1").css('background','url(../../images/patient_classify/up.png) 75px 3px no-repeat');
		$(".new_select .dl2 dd").css('display','block');

	},function(){
		if (flag2==0) {
			$(".new_select .dt1").css('background','url(../../images/patient_classify/up.png) 75px 3px no-repeat');
			$(".new_select .dl2 dd").css('display','block');
			flag2=1;
		}else{
			$(".new_select .dt1").css('background','url(../../images/patient_classify/down.png) 70px 3px no-repeat')
			$(".new_select .dl2 dd").css('display','none');
		}
		

	})
	//选中排序方式或筛选后隐藏下拉列表
	$("body").click(function(){
		$(".new_select .dt1").css('background','url(../../images/patient_classify/down.png) 70px 3px no-repeat')
		$(".new_select .dt2").css('background','url(../../images/patient_classify/down.png) 70px 3px no-repeat')
		$(".new_select .dl1 dd").css('display','none');
		$(".new_select .dl2 dd").css('display','none');
		flag1=0;
		flag2=0;

	})
	//控制输入框显示
	// var i=1;
	$( ".select .ss").toggle(function(){
		$("#select1").empty();
		$("#select2").empty();
		$(".new_select ").css('display','none');
		$(".select p").css('display','inline-block');
		$(".select p input").focus();
			
	},function(){
		$(".new_select").css('display','inline-block');
		$(".select p").css('display','none');
	}		
	);



//selection下拉列表和div中下拉列表的关联
var select1=['高危','患者','健康'];
var select2=['单位','社区','医院'];
$(".new_select .dl1 dd").each(function (i,target){
	$(target).click(function (){
		$("#select1").empty();
		$("#select1").append('<option>'+select1[i]+'</option>');
		$("#select1 option").attr("selected",true);
		$("#select1 option").attr("value",i);
	})
})
$(".new_select .dl2 dd").each(function(i,target){
	$(target).click(function(){
		$("#select2").empty();
		$("#select2").append('<option>'+select2[i]+'</option>');
		$("#select2 option").attr("selected",true);
		$("#select2 option").attr("value",i);
	})
})







});