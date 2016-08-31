function checkpic(){  
	var flag=false;
	var pic=document.getElementById("pic");
	var str=pic.value.toLowerCase();
	var ext=str.split('.')[str.split('.').length-1];  
	if(ext=='png'||ext=='jpg'||ext=='bmp'||ext=='jpeg'||ext=='gif'){  
		flag=true;
		document.getElementById("submit").disabled=true;
		document.getElementById("staffPicture").display='none';
	}  
}
$(document).ready(function(e){
});