(function(ns){
        function HorizontalScroll(element){
              
            var content = document.createElement("div");
            var container = document.createElement("div");
            var _this =this;
            var cssText = "position: absolute; visibility:hidden; left:0; white-space:nowrap;";
            this.element = element; 
            this.contentWidth = 0;
            this.stop = false;
              
            content.innerHTML = element.innerHTML;
            content.style.cssText = cssText;
            element.appendChild(content);
            this.contentWidth = content.offsetWidth;
              
            content.style.cssText= "float:left;";
            if(element.id=="pc2")
            {
                content.style.cssText= "float:left;width:50%;";
            }
            container.style.cssText = "width: " + (this.contentWidth*2) + "px; overflow:hidden";
            container.appendChild(content);
            container.appendChild(content.cloneNode(true));
            element.innerHTML = "";
            element.appendChild(container);
              
            container.onmouseover = function(e){
                clearInterval(_this.timer);
                  
            };
              
            container.onmouseout = function(e){
                _this.timer = setInterval(function(){ 
                    _this.run();
                },20);          
  
                  
            };
            _this.timer = setInterval(function(){ 
                _this.run();
            }, 50);
        }
          
        HorizontalScroll.prototype = {
              
            run: function(){
                  
                var _this = this;
                var element = _this.element;
                  
                element.scrollLeft = element.scrollLeft + 1;
                  
                if(element.scrollLeft >=  this.contentWidth ) {
                        element.scrollLeft = 0;
                }
            }
        };  
    ns.HorizontalScroll = HorizontalScroll; 
}(window));