$(document).ready(
	
	 
);

function setDisabled() {
	  editor.setDisabled('fullscreen');
	  disableBtn("enable");
	} 
//灰掉工具栏按钮
function disableBtn(str) {
   var div = document.getElementById('btns');
   var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
   for (var i = 0, btn; btn = btns[i++];) {
       if (btn.id == str) {
      UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
       } else {
      btn.setAttribute("disabled", "true");
       }
   }
    }

//加载完页面后执行
window.onload=function(){ 

	setDisabled();//禁用富文本编辑器 

	} ;


