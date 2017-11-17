var h_rbtn = document.getElementById('text');
var h_black = document.getElementById('h_black');
var h_popup = document.getElementById('h_popup');
var h_oh = document.documentElement.offsetHeight;
var h_ow = document.documentElement.offsetWidth;
/*h_black.style.width=h_ow+"px";
 h_black.style.height=h_oh+"px";*/
h_rbtn.onclick = function () {
    h_black.style.display = "block";
    h_popup.style.display = "block";
    if (document.documentElement.scrollTop) {
        document.documentElement.scrollTop = 0;
    } else {
        document.body.scrollTop = 0;
    }
}
/*var h_yes=document.getElementById('h_yes');
 var h_no=document.getElementById('h_no');
 h_yes.onclick=function (){
 h_black.style.display="none";
 h_popup.style.display="none";
 }
 h_no.onclick=function () {
 h_black.style.display="none";
 h_popup.style.display="none";
 }*/