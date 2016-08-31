function nofindimg(){ 
var img=event.srcElement; 
img.src="staffpicture/none.jpg";
img.onerror=null;
}
$(document).ready(function(e){
	if($("#photourl").val().length>0){
		$("#photo").attr("src","staffpicture/"+$("#photourl").val());
	}
	$("#retu").click(function(e){
		if(window.attachEvent) {
			location.href='gotoListStaff';
		}else{
			location.href='staff/gotoListStaff';
		}
	});
});