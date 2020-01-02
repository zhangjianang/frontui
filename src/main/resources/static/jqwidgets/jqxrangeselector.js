/*
jQWidgets v8.1.3 (2019-July)
Copyright (c) 2011-2019 jQWidgets.
License: https://jqwidgets.com/license/
*/
/* eslint-disable */

(function(a){a.jqx.jqxWidget("jqxRangeSelector","",{});a.extend(a.jqx._jqxRangeSelector.prototype,{defineInstance:function(){var b={width:400,height:100,min:0,max:100,range:{from:0,to:Infinity,min:0,max:Infinity},majorTicksInterval:10,minorTicksInterval:1,showMajorTicks:true,showMinorTicks:false,snapToTicks:true,labelsFormat:null,markersFormat:null,showLabels:true,labelsOnTicks:true,markersPosition:"top",labelsFormatFunction:null,groupLabelsFormatFunction:null,markersFormatFunction:null,showGroupLabels:false,showMarkers:true,resizable:true,moveOnClick:true,disabled:false,rtl:false,padding:"auto",events:["change"]};if(this===a.jqx._jqxRangeSelector.prototype){return b}a.extend(true,this,b);return b},createInstance:function(c){var e=this;this._isTouchDevice=a.jqx.mobile.isTouchDevice();if(!a.jqx.dataAdapter){throw new Error("jqxRangeSelector: Missing reference to the following module: 'jqxdata.js'.")}var f=a.jqx.isHidden(this.host);this.render();var d=this.host.width();var b=this.host.height();a.jqx.utilities.resize(this.host,function(){var h=e.host.width();var g=e.host.height();e.range=e.getRange();if(f){e.refresh();f=false}else{if(d!=h||b!=g){e.refresh()}}d=e.host.width();b=e.host.height()})},render:function(){if(this.host.children().length>1||this.rangeSelector){this._removeHandlers();if(this.rangeSelector){this.rangeSelector.remove()}}this.host.addClass(this.toThemeProperty("jqx-widget"));this.host.addClass(this.toThemeProperty("jqx-rangeselector"));this.host.children(":eq(0)").addClass(this.toThemeProperty("jqx-rangeselector-content"));this._id=this.element.id;if(typeof this.min=="string"||this.min instanceof Date){this._dataType="date"}else{this._dataType="number"}this._privateProperties();this._checkProperties();this._setSize();this._scale();this._initSlider()},refresh:function(b){if(b==true){return}this.host.children(".jqx-rangeselector-ticks-container").remove();this._removeHandlers();this._privateProperties();this._checkProperties();this._setSize();this._scale();this._initSlider()},destroy:function(){this._removeHandlers();this.host.remove()},setRange:function(k,l){if(k>l){throw new Error("jqxRangeSelector: range object initialization error. 'min' should be less than 'max'");return}var g=this._getValue();if(g.from!=k||g.to!=l){var d=this._dataType=="number"?"numeric":"date";var i="The set values are in the wrong format. Please set "+d+" values.";if(typeof k=="string"||k instanceof Date){if(this._dataType=="number"){throw new Error(i)}}else{if(this._dataType=="date"){throw new Error(i)}}k=this._validateInput(k);l=this._validateInput(l);if(k>this._max){k=this._max}if(k<this._min){k=this._min}if(l>this._max){l=this._max}if(l<this._min){l=this._min}var f=l-k;if(f>this._range._max){l=k+this._range._max}else{if(f<this._range._min){l=k+this._range._min}}var j=this._valuesArray.indexOf(k);var e=this._valuesArray.indexOf(l);var m=this._ticksArray[j];var h=this._ticksArray[e];var b=Math.abs(h-m);this.slider[0].style.width=b+"px";var c=!this.rtl?m:h;this.slider[0].style.left=c;this._moveSlider(c);if(this._dataType=="date"){var k=new Date(k);var l=new Date(l)}this._raiseEvent("0",{type:null,from:k,to:l})}},val:function(b){if(arguments.length==0){return this.getRange()}if(b.from!=undefined){this.setRange(b.from,b.to)}},getRange:function(){var b=this._getValue();return b},propertyChangedHandler:function(b,c,e,d){switch(c){case"showMinorTicks":if(d==true){a("#"+this._id+" .jqx-rangeselector-ticks-minor").css("visibility","visible")}else{a("#"+this._id+" .jqx-rangeselector-ticks-minor").css("visibility","hidden")}break;case"showMarkers":var f=a("#"+this._id+"LeftMarker, #"+this._id+"RightMarker, #"+this._id+"LeftMarkerArrow, #"+this._id+"RightMarkerArrow");if(d==true){f.css("visibility","visible")}else{f.css("visibility","hidden")}break;default:this.refresh()}},_raiseEvent:function(g,e){var c=this.events[g];var f=new a.Event(c);f.owner=this;f.args=e;try{var b=this.host.trigger(f)}catch(d){}return b},_setSize:function(){this.host.width(this.width);this.host.height(this.height)},resize:function(c,b){this.width=c;this.height=b;this.refresh()},_scale:function(){var c=this.host.width();var b=this._max-this._min;this._unitPerPixel=parseFloat((b/c).toFixed(4));this._pixelPerUnit=c/b;4;this._minWidth=this._roundNumber(this._range._min/this._unitPerPixel);this._maxWidth=this._roundNumber(this._range._max/this._unitPerPixel);this._minWidth=parseInt(this._minWidth);this._maxWidth=parseInt(this._maxWidth);if(this._dataType=="number"){this._majorTicksCount=b/this.majorTicksInterval;this._majorTicksCount=Math.floor(this._majorTicksCount)+1;this._majorTicksDistance=parseInt(this._roundNumber(c/(b/this.majorTicksInterval)));this._unitsCount=b/this.minorTicksInterval;this._unitsCount=Math.floor(this._unitsCount)+1;this._unitsDistance=parseInt(this._roundNumber(c/(b/this.minorTicksInterval)))}this._addTicks()},_addTicks:function(){var f=this;this.host.append("<div id='"+this._id+"TicksContainer' class='jqx-rangeselector-ticks-container'></div>");this.rangeSelector=a("#"+this._id+"TicksContainer");this._majorTicksArray=new Array();this._ticksArray=new Array();this._valuesArray=new Array();var c=new String();a("#"+this._id+"TicksContainer").append("<div id='labelPlaceholder' style='visibility: hidden; position: absolute;'></div>");var e=this.rangeSelector.height();if(this._dataType=="number"){c=this._addNumericTicks(e)}else{c=this._addDateTicks(e)}var b=0;if(this.showLabels){b+=a("#labelPlaceholder").outerHeight()+6}if(this._dataType!="number"){if(this.showGroupLabels){b+=a("#labelPlaceholder").outerHeight()+6}}if(this.padding=="auto"){this.host.css("padding-bottom",b)}a("#labelPlaceholder").remove();a("#"+this._id+"TicksContainer").append(c);this._ticksArray.sort(function(h,g){return h-g});for(var d=1;d<this._ticksArray.length;d++){this._ticksArray[d]=this._roundNumber(this._ticksArray[d])}this._valuesArray.sort(function(h,g){return h-g});if(this._dataType=="number"){for(var d=1;d<this._valuesArray.length;d++){this._valuesArray[d]=this._roundNumber(this._valuesArray[d],"marker",true)}}for(var d=1;d<this._ticksArray.length;d++){if(this._ticksArray[d-1]==this._ticksArray[d]){this._ticksArray.splice(d,1);this._valuesArray.splice(d,1)}}if(this.rtl){this._valuesArray=this._valuesArray.reverse()}},_addNumericTicks:function(g){var n=this;var l=new String();var h=0;var f=this._min;var r=this._max;for(var k=0;k<this._majorTicksCount;k++){var e=this._id+"LabelTick"+(k+1);if(k==this._majorTicksCount-1){h=this.host.width()}var m=n.showMajorTicks?"visible":"hidden";l+="<div id='"+e+"' class='"+this.toThemeProperty("jqx-rangeselector-ticks")+" "+this.toThemeProperty("jqx-slider-tick-horizontal")+"' style='visibility: "+m+"; left: "+h+"px;'></div>";this._ticksArray.push(h);this._majorTicksArray.push(h);var c=this._id+"Label"+(k+1);var j=f;this._valuesArray.push(parseFloat(j.toFixed(4)));if(n.rtl){j=r}j=this._formatOutput(j,this.labelsFormat,this.minorTicksInterval>=1?0:2,"label");a("#labelPlaceholder").html(j);var q=a("#labelPlaceholder").width();var p=n.showLabels?"visible":"hidden";if(n.labelsOnTicks){l+="<div id='"+c+"' class='"+this.toThemeProperty("jqx-rangeselector-labels")+"' style='visibility: "+p+"; left: "+(h-q/2)+"px; top: "+g+"px;'>"+j+"</div>"}var d=h;f=f+this.majorTicksInterval;r=r-this.majorTicksInterval;var h=(f-n._min)/n._unitPerPixel;h=parseInt(h);if(!this.labelsOnTicks&&k<this._majorTicksCount-1){var o=Math.abs(d-h);l+="<div id='"+c+"' class='"+this.toThemeProperty("jqx-rangeselector-labels")+"' style='visibility: "+p+"; left: "+(d+o/2-q/2)+"px; top: "+g+"px;'>"+j+"</div>"}}var h=0;var b=this.showMinorTicks?"visible":"hidden";var f=this._min;for(var k=0;k<this._unitsCount;k++){var e=this._id+"MinorTick"+(k+1);if(k==this._unitsCount-1){h=this.host.width()}l+="<div id='"+e+"' class='"+this.toThemeProperty("jqx-rangeselector-ticks")+" "+this.toThemeProperty("jqx-rangeselector-ticks-minor")+" "+this.toThemeProperty("jqx-slider-tick-horizontal")+"' style='visibility: "+b+"; left: "+h+"px;'></div>";var s=f;if(this._valuesArray.indexOf(parseFloat(s.toFixed(4)))===-1){this._valuesArray.push(parseFloat(s.toFixed(4)));this._ticksArray.push(h)}f=f+this.minorTicksInterval;var h=(f-n._min)/n._unitPerPixel;h=parseInt(h)}return l},_getMillisecondsByInterval:function(c){var b={};if(c=="year"||c.years){b.divisor=c.years?c.years:1;return b.divisor*(365*24*3600*1000)}else{if(c=="month"||c.months){b.divisor=c.months?c.months:1;return b.divisor*(30*24*3600*1000)}else{if(c=="week"||c.weeks){b.divisor=c.weeks?c.weeks:1;return b.divisor*(7*24*3600*1000)}else{if(c=="day"||c.days){b.divisor=c.days?c.days:1;return b.divisor*(24*3600*1000)}else{if(c=="hour"||c.hours){b.divisor=c.hours?c.hours:1;return b.divisor*(3600*1000)}else{if(c=="minute"||c.minutes){b.divisor=c.minutes?c.minutes:1;return b.divisor*60*1000}else{if(c=="second"||c.seconds){b.divisor=c.seconds?c.seconds:1;return b.divisor*1000}else{if(c=="millisecond"||c.milliseconds){b.divisor=c.milliseconds?c.milliseconds:1;return b.divisor*1}}}}}}}}return b},_addDateTicks:function(e){var d=this;var c=new String();var b=function(k){var j=k=="majorTicksInterval"?d.majorTicksInterval:d.minorTicksInterval;var i=new Object();if(j=="year"||j.years){i.period="year";i.interval=86400000;i.divisor=j.years?j.years:1;i.value=i.divisor*(365*24*3600*1000)}else{if(j=="month"||j.months){i.period="month";i.interval=86400000;i.divisor=j.months?j.months:1;i.value=i.divisor*(30*24*3600*1000)}else{if(j=="week"||j.weeks){i.period="week";i.interval=86400000;i.divisor=j.weeks?j.weeks:1;i.value=i.divisor*(7*24*3600*1000)}else{if(j=="day"||j.days){i.period="day";i.interval=3600000;i.divisor=j.days?j.days:1;i.value=i.divisor*(24*3600*1000)}else{if(j=="hour"||j.hours){i.period="hour";i.interval=60000;i.divisor=j.hours?j.hours:1;i.value=i.divisor*(3600*1000)}else{if(j=="minute"||j.minutes){i.period="minute";i.interval=60*1000;i.divisor=j.minutes?j.minutes:1;i.value=i.divisor*60*1000}else{if(j=="second"||j.seconds){i.period="second";i.interval=1000;i.divisor=j.seconds?j.seconds:1;i.value=i.divisor*1000}else{if(j=="millisecond"||j.milliseconds){i.period="millisecond";i.interval=1;i.divisor=j.milliseconds?j.milliseconds:1;i.value=i.divisor*1}}}}}}}}return i};var f=function(n,q){var l=new Date(n);var r=l.getDate();var s=q=="year"&&l.getMonth()==0&&r==1;var k=q=="month"&&r==1;var t=q=="week"&&l.getDay()==0;var j=q=="day"&&l.getHours()==0;var i=q=="hour"&&l.getMinutes()==0;var o=q=="minute"&&l.getSeconds()==0;var m=q=="minute"&&l.getMilliseconds()==0;var p=q=="millisecond";if(s||k||t||j||i||o||m||p){return true}else{return false}};var h=function(p,u,n){var l=(p-d._min)/d._unitPerPixel;if(d.rtl){if(u=="majorTicksInterval"){p=d._dateMajorTicks[d._dateMajorTicks.length-n]}else{p=d._dateMinorTicks[d._dateMinorTicks.length-n]}}l=parseInt(l);var v=p;if(d._valuesArray.indexOf(v)===-1){d._ticksArray.push(l);d._valuesArray.push(v);if(u=="majorTicksInterval"){d._majorTicksArray.push(l)}}if(u=="majorTicksInterval"){var q=d._id+"LabelTick"+n;var k=d.showMajorTicks?"visible":"hidden";c+="<div id='"+q+"' class='"+d.toThemeProperty("jqx-rangeselector-ticks")+" "+d.toThemeProperty("jqx-slider-tick-horizontal")+"' style='visibility: "+k+"; left: "+l+"px;'></div>";var o=v;o=d._formatOutput(o,d.labelsFormat,d.labelPrecision,"label");a("#labelPlaceholder").html(o);var t=a("#labelPlaceholder").width();var j=d._id+"Label"+n;var s=d.showLabels?"visible":"hidden";if(!d.labelsOnTicks){var w=d._getMillisecondsByInterval(d.majorTicksInterval)/d._unitPerPixel;var r=w/2;c+="<div id='"+j+"' class='"+d.toThemeProperty("jqx-rangeselector-labels")+"' style='visibility: "+s+"; left: "+(r+l-t/2)+"px; top: "+e+"px;'>"+o+"</div>"}else{if(d.labelsOnTicks){c+="<div id='"+j+"' class='"+d.toThemeProperty("jqx-rangeselector-labels")+"' style='visibility: "+s+"; left: "+(l-t/2)+"px; top: "+e+"px;'>"+o+"</div>"}}}else{var k=d.showMinorTicks?"visible":"hidden";var m=d._id+"MinorTick"+n;c+="<div id='"+m+"' class='"+d.toThemeProperty("jqx-rangeselector-ticks")+" "+d.toThemeProperty("jqx-rangeselector-ticks-minor")+" "+d.toThemeProperty("jqx-slider-tick-horizontal")+"' style='visibility: "+k+"; left: "+l+"px;'></div>"}};var g=function(s,o,j){var p=0;var l=1;var m=new Date(d._min).getHours();var r=o.interval==86400000?true:false;var k=0;var t=new Array();for(var n=d._min;n<=d._max;n+=o.interval){if(r==true){var u=new Date(n).getHours();if(m!=u){var q;if(u==1){q=1}else{if(u==23){q=-1}}n=n-q*3600000;m=new Date(n).getHours()}}var v=f(n,o.period);if(v==true){if(p%o.divisor==0){if(j){t.push(n)}else{h(n,s,l,o.interval)}l++}p++}}return t};d._dateMajorTicks=g("majorTicksInterval",b("majorTicksInterval"),true);d._dateMinorTicks=g("minorTicksInterval",b("minorTicksInterval"),true);g("majorTicksInterval",b("majorTicksInterval"));g("minorTicksInterval",b("minorTicksInterval"));if(this.showGroupLabels==true&&this.showLabels){this._addGroupLabels(a("#labelPlaceholder").height()+e)}return c},_addGroupLabels:function(n){var k=this;var e=new Date(this._min);var m=new Date(this._max);if(m.getFullYear()-e.getFullYear()>0){var l="year";var b=86400000}else{if(m.getMonth()-e.getMonth()>0){var l="month";var b=86400000}else{if(m.getDate()-e.getDate()>0){var l="day";var b=3600000}else{return}}}var j=function(q){var p=new Date(q);var s=p.getFullYear();var r=p.getMonth();var u=p.getDate();var v;var i=true;if(l=="year"&&r==0&&u==1){v=s}else{if(l=="month"&&u==1){v=a.jqx.dataFormat.formatdate(p,"MMMM");if(r==0){v=s+" "+v}}else{if(l=="day"&&p.getHours()==0){v=a.jqx.dataFormat.formatdate(p,"dddd")}else{i=false}}}var t;if((i==true)&&k.groupLabelsFormatFunction){t=k.groupLabelsFormatFunction(v,p)}else{t=v}var w={check:i,value:t};return w};var h=new String();var g=this.toThemeProperty("jqx-rangeselector-group-labels-ticks")+" "+this.toThemeProperty("jqx-slider-tick-horizontal");var d=1;for(var f=this._min;f<this._max;f+=b){var o=j(f);if(o.check==true){var c=(f-this._min)/this._unitPerPixel;h+="<div class='"+this.toThemeProperty("jqx-rangeselector-labels")+"' style='left: "+c+"px; top: "+n+"px;'><div class='"+g+"'></div><div id='"+this._id+"GroupLabel"+d+"' class='"+this.toThemeProperty("jqx-rangeselector-group-labels")+"' style='margin-left: 5px;'>"+o.value+"</div></div>";d++}}a("#"+this._id+"TicksContainer").append(h)},_updateCursor:function(b,g){var f=this.element.style.cursor;var e=this.slider.offset().left;var d=parseInt(this.slider[0].style.width);var c=e+d;if((((b>e-5)&&(b<e+5))||((b>c-5)&&(b<c+5)))){if(f==""||f=="auto"){this.element.style.cursor="e-resize"}}else{if(f=="e-resize"){this.element.style.cursor="auto"}}},_handleMouseMove:function(z){var k=this;var t=k.slider;var m=z.pageX;var l=z.pageY;if(k._isTouchDevice){var i=a.jqx.position(z);m=i.left;l=i.top}var E=k._hostOffset.left;var u=k._hostWidth;if(k.resizable&&!k.dragging&&k.resizeDirection=="none"){if(m>=E&&m<=E+u){if(l>=k._hostOffset.top&&l<=k._hostOffset.top+k._hostHeight){this._updateCursor(m,l)}}}if(!k.isMouseDown){return true}if(k._isTouchDevice){if(l<k._hostOffset.top||l>k._hostOffset.top+k._hostHeight){return true}}var g=k._findNearestTick(k._sliderLeftOffset+m-k._mouseDownX);var d=parseInt(g);if(d<0){return true}if(d<0){d=0}var b=parseInt(t[0].style.width);var j=d+b;var s=function(H){var y=parseInt(k._maxWidth);var x=parseInt(k._minWidth);if(H<x||H>y){return false}var J=parseInt(k.rightMarker[0].style.left);var I=parseInt(k.leftMarker[0].style.left);if(I>J){return false}return true};if(k.resizable==true&&!k.dragging){var B=d*k._unitPerPixel+k._min;if(k.resizeDirection=="left"||k.isLeftMarkerCaptured){var F=k.isLeftMarkerCaptured?k.leftMarker.outerWidth():0;if(m<E-F){m=E-F}if(m>E+u+F){m=E+u+F+1}var r=t[0].style.left;var h=d-parseInt(r);var p=parseInt(b-h);if(!s(p)){if(m>k._mouseDownX){m=k.sliderRight-k._minWidth-E;p=k._minWidth;if(b==p){return true}var g=k._findNearestTick(m);d=g;if(d<0){return true}var q=parseInt(r)*k._unitPerPixel+k._min;var B=d*k._unitPerPixel+k._min}else{if(k._maxWidth!=0&&m<k._mouseDownX&&p>k._maxWidth){m=k.sliderRight-k._maxWidth-E;p=k._maxWidth;if(b==p){return true}var g=k._findNearestTick(m);d=g;if(d<0){return true}var q=parseInt(r)*k._unitPerPixel+k._min;var B=d*k._unitPerPixel+k._min}else{return true}}}k.slider[0].style.left=d+"px";if(r!=t[0].style.left){k.slider[0].style.width=p+"px"}var o=k._findNearestTick(d);var w=k._valuesArray[k._ticksArray.indexOf(o)];if(w!=undefined){k.leftMarkerValue[0].innerHTML=k._formatOutput(w,k.markersFormat,0,"left");if(d!=o){k.slider[0].style.left=o+"px"}}else{k.leftMarkerValue[0].innerHTML=k._formatOutput(B,k.markersFormat,0,"left")}k.oldX=m;k.moved=true}else{if(k.resizeDirection=="right"||k.isRightMarkerCaptured){var F=k.isRightMarkerCaptured?k.rightMarker.outerWidth():0;var G=false;var v=false;if(m<E-F){m=E-F;v=true}if(m>E+u+F){m=E+u+F;G=true}var f=k._sliderInitialWidth;var n=k._findNearestTick(f+k._sliderLeftOffset);var D=k._findNearestTick(f+m-k._mouseDownX+k._sliderLeftOffset);if(D<0){return true}var h=n-D;var p=f-h;if(p<=0){h=f;p=0}var C=parseInt(k.element.style.width);if(k.element.style.width.indexOf("%")>=0){C=k.host.width()}if(G||(k._sliderLeftOffset+p>=C)){p=C-k._sliderLeftOffset;var c=true}if(p>parseInt(this._maxWidth)){p=parseInt(this._maxWidth)}if(p<parseInt(this._minWidth)){p=parseInt(this._minWidth)}k.slider[0].style.width=p+"px";var B=(k._sliderLeftOffset+p)*k._unitPerPixel+k._min;var A=k._findNearestTick(k._sliderLeftOffset+p);var e=k._valuesArray[k._ticksArray.indexOf(A)];if(e!=undefined){if(k._sliderLeftOffset+p!=A){k.slider[0].style.width=A-k._sliderLeftOffset+"px"}k.rightMarkerValue[0].innerHTML=k._formatOutput(e,k.markersFormat,0,"right")}else{k.rightMarkerValue[0].innerHTML=(k._formatOutput(B,k.markersFormat,0,"right"))}k.oldX=m}}k._layoutShutter();k._layoutMarkers();k.moved=true}if(k.dragging==1){k._moveSlider(d,true);k.oldX=m}},_moveSlider:function(b,d){var g=this;g.moved=true;var e=parseInt(this.slider[0].style.width);var c=parseInt((b+e));var n=this._hostWidth;var j=b;if(j<0){j=0;b=j}if(j+e>n){j=n-e;b=j}if((j>=0)&&((j+e)<=(n))){this.slider[0].style.left=j+"px";var o=this._findNearestTick(j);var k=this._majorTicksArray.indexOf(o)!=-1;var m=this._valuesArray[this._ticksArray.indexOf(o)];if(m!=undefined){this.leftMarkerValue[0].innerHTML=this._formatOutput(m,this.markersFormat,0,"left");if(b!=o&&k){if(d){this.slider[0].style.left=o+"px";var c=parseInt((o+e))}}}else{this.leftMarkerValue[0].innerHTML=this._formatOutput(((j)*this._unitPerPixel+this._min),this.markersFormat,0,"left")}var i=this._findNearestTick(c);var l=this._valuesArray[this._ticksArray.indexOf(i)];if(l!=undefined){var h=this._majorTicksArray.indexOf(i)!=-1;this.rightMarkerValue[0].innerHTML=this._formatOutput(l,this.markersFormat,0,"right");if(c!=i&&h&&k){if(d){var f=(i-o);this.slider[0].style.width=f+"px"}}}else{this.rightMarkerValue[0].innerHTML=this._formatOutput(((j+e)*this._unitPerPixel+this._min),this.markersFormat,0,"right")}}this._layoutShutter();this._layoutMarkers()},_initSlider:function(){var n=this;var r=this.toThemeProperty("jqx-rangeselector-shutter")+" "+this.toThemeProperty("jqx-scrollbar-state-normal");a("#"+this._id+"TicksContainer").append("<div id='"+this._id+"ShutterLeft' class='"+r+"'></div><div id='"+this._id+"Slider' class='"+this.toThemeProperty("jqx-rangeselector-slider")+" "+this.toThemeProperty("jqx-scrollbar-thumb-state-normal")+"'><div class='"+this.toThemeProperty("jqx-rangeselector-inner-slider")+"'></div></div><div id='"+this._id+"ShutterRight' class='"+r+"'></div>");this.slider=a("#"+this._id+"Slider");this.shutterLeft=a("#"+this._id+"ShutterLeft");this.shutterRight=a("#"+this._id+"ShutterRight");this._hostOffset=this.rangeSelector.offset();this._hostWidth=this.rangeSelector.width();this._hostHeight=this.rangeSelector.height();var o=this._hostOffset;var m=this._initRange();var q=m.left;var c=m.right-q;var g=c/this._unitPerPixel;this.slider[0].style.width=Math.round(g)+"px";var h=o.left+parseInt((q-this._min)/this._unitPerPixel);this.slider.offset({left:h});this._layoutShutter();this._initMarkers();if(this.disabled==false){this.host.removeClass(this.toThemeProperty("jqx-fill-state-disabled"));this.addHandler(this.host,"dragstart.rangeselector"+this._id,function(){return false});this.addHandler(a(window),"jqxReady.rangeselector",function(){n._layoutMarkers();return false});this.isSliderCaptured=false;this.resizeDirection="none";this.isLeftMarkerCaptured=false;this.isRightMarkerCaptured=false;this.dragging=false;this._mouseDownX;var d;var f;var i="mousedown.rangeselector"+this.element.id;if(this._isTouchDevice){i=a.jqx.mobile.getTouchEventName("touchstart")+".rangeselector"+this.element.id}this.addHandler(this.host,i,function(t){n.isMouseDown=true;n._hostOffset=n.rangeSelector.offset();n._hostWidth=n.rangeSelector.width();n._hostHeight=n.rangeSelector.height();n._sliderLeftOffset=parseInt(n.slider[0].style.left);var A=t.pageX;var v=t.pageY;if(n._isTouchDevice){var z=a.jqx.position(t);A=z.left;v=z.top}n._initialSliderOffset=A-n.slider.offset().left;var B=n.slider.width();n._sliderInitialWidth=B;d=n.slider.offset().left;f=n._sliderInitialWidth;n.initialOffset=d;var s=parseInt((d+f));n.oldX=A;n._mouseDownX=A;n.resizeDirection="none";n.sliderRight=s;if((A>d-5)&&(A<d+5)&&n._heightCheck(v)){n.isSliderCaptured=false;n.dragging=false;n.resizeDirection="left"}else{if((A>s-5)&&(A<s+5)&&n._heightCheck(v)){n.isSliderCaptured=false;n.dragging=false;n.resizeDirection="right"}else{if((A>=d+5)&&(A<=s+5)&&n._heightCheck(v)){n.isSliderCaptured=true;n.dragging=true}else{n.isSliderCaptured=false;n.dragging=false;if(n.moveOnClick){if(n.isLeftMarkerCaptured||n.isRightMarkerCaptured){return false}var l=n._sliderLeftOffset+n._initialSliderOffset;var C=n._findNearestTick(l);l=C;if(l<0){l=0}var u=parseInt(n.slider[0].style.width);if(v>=n.slider.offset().top){if(A>s){n._moveSlider(l-u,true)}else{n._moveSlider(l,true)}}}}}}});this.addHandler(n.leftMarker,i,function(l){n.leftMarkerAndArrow.addClass(n.toThemeProperty("jqx-fill-state-pressed"));n.oldLeftX=l.pageX;if(n._isTouchDevice){var s=a.jqx.position(l);n.oldLeftX=s.left}n._mouseDownX=n.oldLeftX;n.isLeftMarkerCaptured=true});this.addHandler(n.rightMarker,i,function(l){n.rightMarkerAndArrow.addClass(n.toThemeProperty("jqx-fill-state-pressed"));n.oldRightX=l.pageX;if(n._isTouchDevice){var s=a.jqx.position(l);n.oldRightX=s.left}n._mouseDownX=n.oldRightX;n.isRightMarkerCaptured=true});this.addHandler(a(document),"selectstart.rangeselector"+this._id,function(l){if(n.isSliderCaptured==true||n.isLeftMarkerCaptured==true||n.isRightMarkerCaptured==true||n.dragging==true){l.preventDefault();return false}});var b="mousemove.rangeselector"+this.element.id;if(this._isTouchDevice){b=a.jqx.mobile.getTouchEventName("touchmove")+".rangeselector"+this.element.id}this.addHandler(a(document),b,function(l){n._handleMouseMove(l)});var k=function(t){try{var s=n.moved;n.moved=false;n.isMouseDown=false;n.dragging=false;n.resizeDirection="none";if(n.isLeftMarkerCaptured==true){n.leftMarkerAndArrow.removeClass(n.toThemeProperty("jqx-fill-state-pressed"));n.isLeftMarkerCaptured=false}if(n.isRightMarkerCaptured==true){n.rightMarkerAndArrow.removeClass(n.toThemeProperty("jqx-fill-state-pressed"));n.isRightMarkerCaptured=false}if(s){var u=n._getValue();n._raiseEvent("0",{type:"mouse",from:u.from,to:u.to})}}catch(l){}};this.addHandler(a(document),"mouseup.rangeselector"+this._id,function(l){k(l)});try{if(document.referrer!=""||window.frameElement){if(window.top!=null&&window.top!=window.self){var j=function(l){k(l)};var e=null;if(window.parent&&document.referrer){e=document.referrer}if(e&&e.indexOf(document.location.host)!=-1){if(window.top.document){if(window.top.document.addEventListener){window.top.document.addEventListener("mouseup",j,false)}else{if(window.top.document.attachEvent){window.top.document.attachEvent("onmouseup",j)}}}}}}}catch(p){}}else{this.host.addClass(this.toThemeProperty("jqx-fill-state-disabled"))}this._moveSlider(parseInt(n.slider[0].style.left));this.moved=false},_initMarkers:function(){var c=a("#"+this._id+"TicksContainer");var d=this.toThemeProperty("jqx-rangeselector-markers")+" "+this.toThemeProperty("jqx-disableselect")+" "+this.toThemeProperty("jqx-fill-state-normal");c.append("<div id='"+this._id+"LeftMarker' class='"+d+"'></div><div id='"+this._id+"RightMarker' class='"+d+"'></div>");var e=this.toThemeProperty("jqx-rangeselector-marker-arrow")+" "+this.toThemeProperty("jqx-fill-state-normal");if(this.markersPosition=="bottom"){e+=" "+this.toThemeProperty("jqx-rangeselector-marker-arrow-bottom")}else{e+=" "+this.toThemeProperty("jqx-rangeselector-marker-arrow-top")}c.append("<div id='"+this._id+"LeftMarkerArrow' class='"+e+" "+this.toThemeProperty("jqx-rangeselector-marker-left-arrow")+"'></div>");c.append("<div id='"+this._id+"RightMarkerArrow' class='"+e+" "+this.toThemeProperty("jqx-rangeselector-marker-right-arrow")+"'></div>");a("#"+this._id+"LeftMarker").append("<div id='"+this._id+"LeftMarkerValue' class='"+this.toThemeProperty("jqx-disableselect")+" "+this.toThemeProperty("jqx-rangeselector-markers-value")+"'></div>");a("#"+this._id+"RightMarker").append("<div id='"+this._id+"RightMarkerValue' class='"+this.toThemeProperty("jqx-disableselect")+" "+this.toThemeProperty("jqx-rangeselector-markers-value")+"'></div>");var i=a("#"+this._id+"LeftMarker, #"+this._id+"RightMarker, #"+this._id+"LeftMarkerArrow, #"+this._id+"RightMarkerArrow");var h=a("#"+this._id+"LeftMarker, #"+this._id+"LeftMarkerArrow");var b=a("#"+this._id+"RightMarker, #"+this._id+"RightMarkerArrow");if(this.showMarkers==true){i.css("visibility","visible")}else{i.css("visibility","hidden")}if(this.disabled==false&&this.resizable==true){var f=this;this.addHandler(h,"mouseenter.rangeselector"+this._id,function(j){f.element.style.cursor="pointer";h.addClass(f.toThemeProperty("jqx-fill-state-hover"))});this.addHandler(h,"mouseleave.rangeselector"+this._id,function(j){f.element.style.cursor="auto";h.removeClass(f.toThemeProperty("jqx-fill-state-hover"))});this.addHandler(b,"mouseenter.rangeselector"+this._id,function(j){f.element.style.cursor="pointer";b.addClass(f.toThemeProperty("jqx-fill-state-hover"))});this.addHandler(b,"mouseleave.rangeselector"+this._id,function(j){f.element.style.cursor="auto";b.removeClass(f.toThemeProperty("jqx-fill-state-hover"))})}this.leftMarkerAndArrow=h;this.rightMarkerAndArrow=b;this.leftMarkerArrow=a("#"+this._id+"LeftMarkerArrow");this.rightMarkerArrow=a("#"+this._id+"RightMarkerArrow");this.leftMarker=a("#"+this._id+"LeftMarker");this.rightMarker=a("#"+this._id+"RightMarker");this.leftMarkerValue=a("#"+this._id+"LeftMarkerValue");this.rightMarkerValue=a("#"+this._id+"RightMarkerValue");var g=this._initRange();this._updateMarkersValues(g.left,g.right);this._layoutMarkers();if(this.padding=="auto"){this.host.css("padding-left",this.leftMarker[0].offsetWidth);this.host.css("padding-right",this.rightMarker[0].offsetWidth);this.host.css("padding-top",this._leftMarkerHeight+7)}else{this.host.css("padding",this.padding)}},_layoutMarkers:function(){if(this.showMarkers!=true){return}if(!this._hostOffset){this._hostOffset=this.rangeSelector.offset()}if(!this._leftMarkerHeight){this._leftMarkerHeight=this.leftMarker.outerHeight();this._rightMarkerHeight=this.rightMarker.outerHeight()}var e=this._hostOffset.top;var g=parseInt(this.slider[0].style.left)+this._hostOffset.left;var d=-5;if(this.markersPosition=="bottom"){d=parseInt(this.element.style.height)+4+this._rightMarkerHeight}var c=d-this._leftMarkerHeight;var f=d-this._rightMarkerHeight;if(this.markersPosition=="bottom"){d=parseInt(this.element.style.height)-6}var i=this.leftMarker[0].offsetWidth;var b=1+g-i-this._hostOffset.left;this.leftMarker[0].style.left=b+"px";this.leftMarker[0].style.top=c+"px";this.leftMarkerArrow[0].style.left=2+b+i+"px";this.leftMarkerArrow[0].style.top=6+d+"px";var h=g+parseInt(this.slider[0].style.width)-this._hostOffset.left;this.rightMarker[0].style.left=h+"px";this.rightMarker[0].style.top=f+"px";this.rightMarkerArrow[0].style.left=7+h+"px";this.rightMarkerArrow[0].style.top=6+d+"px"},_updateMarkersValues:function(e,d){var c=e;var b=d;this.leftMarkerValue[0].innerHTML=this._formatOutput(c,this.markersFormat,0,"left",true);this.rightMarkerValue[0].innerHTML=this._formatOutput(b,this.markersFormat,0,"right",true)},_removeHandlers:function(){var f=this.element.id;var e=a("#"+f+"LeftMarker, #"+f+"LeftMarkerArrow");var b=a("#"+f+"RightMarker, #"+f+"RightMarkerArrow");var d="mousemove.rangeselector"+f;var c="mousedown.rangeselector"+f;if(this._isTouchDevice){d=a.jqx.mobile.getTouchEventName("touchmove")+".rangeselector"+f;c=a.jqx.mobile.getTouchEventName("touchstart")+".rangeselector"+f}this.removeHandler(a(document),d);this.removeHandler(a(document),"mouseup.rangeselector"+f);this.removeHandler(this.host,c);this.removeHandler(this.host,"click.rangeselector"+f);this.removeHandler(this.host,"dragstart.rangeselector"+f);this.removeHandler(e,"mouseenter.rangeselector"+f);this.removeHandler(e,"mouseleave.rangeselector"+f);this.removeHandler(b,"mouseenter.rangeselector"+f);this.removeHandler(b,"mouseleave.rangeselector"+f);this.removeHandler(a("#"+f+"LeftMarker"),c);this.removeHandler(a("#"+f+"RightMarker"),c);this.removeHandler(a("#"+f+"LeftMarkerValue, #"+f+"RightMarkerValue"),"selectstart.rangeselector"+f)},_heightCheck:function(b){var d=this.slider;var c=d.offset().top;if(b>=c&&b<=c+d.height()){return true}else{return false}},_checkProperties:function(){if(this._range._from<this._min){this._range._from=this._min}else{if(this._range._from>this._min&&this._range._from>this._max){this._range._from=this._min}}if(this._range._to>this._max){this._range._to=this._max}else{if(this._range._to<this._min&&this._range._to<this._max){this._range._to=this._max}}var c=this._max-this._min;if(this._range._min>c){this._range._min=c}if(this._range._max>c){this._range._max=c}var b=this._range._to-this._range._from;if(b<this._range._min){this._range._to=this._range._from+this._range._min}else{if(b>this._range._max){this._range._to=this._range._from+this._range._max}}},_findNearestTick:function(d){var f=0;var e=Math.abs(d-this._ticksArray[0]);for(var c=1;c<this._ticksArray.length;c++){var b=Math.abs(d-this._ticksArray[c]);if(e>b){e=b;f=c}}return this._ticksArray[f]},_privateProperties:function(){this._min=this._validateInput(this.min);this._max=this._validateInput(this.max);this._range=new Object();this._range._from=this._validateInput(this.range.from!=undefined?this.range.from:0);this._range._to=this._validateInput(this.range.to!=undefined?this.range.to:Infinity);this._range._min=this._minMaxDate(this.range.min!=undefined?this.range.min:0);this._range._max=this._minMaxDate(this.range.max!=undefined?this.range.max:Infinity)},_validateInput:function(c){var b;if(typeof c=="number"){b=c}else{if(typeof c=="string"){b=Date.parse(c)}else{if(c instanceof Date){b=c.getTime()}}}return b},_minMaxDate:function(c){if(typeof c!="number"){var b;switch(c){case"millisecond":b=1;break;case"second":b=1000;break;case"minute":b=60000;break;case"hour":b=3600000;break;case"day":b=86400000;break;case"week":b=604800000;break;default:b=c.milliseconds?c.milliseconds:0+c.seconds?1000*c.seconds:0+c.minutes?60000*c.minutes:0+c.hours?3600000*c.hours:0+c.days?86400000*c.days:0+c.weeks?604800000*c.weeks:0}return b}else{return c}},_formatOutput:function(f,g,c,e,h){var d;if(!this.values){this.values=new Array()}this.values[e]=f;if((e=="label")&&this.labelsFormatFunction){if(this._dataType=="date"){d=this._roundDate(f)}else{d=this._roundNumber(f,"label")}d=this.labelsFormatFunction(d)}else{if(e!="label"&&this.markersFormatFunction){if(this._dataType=="date"){d=this._roundDate(f)}d=this.markersFormatFunction(f,e)}else{if(!g){if(this._dataType=="date"){var b;if(this.labelsFormat==null&&this.markersFormat==null){b="both labelsFormat and markersFormat"}else{if(this.labelsFormat==null){b="labelsFormat"}else{if(this.markersFormat==null){b="markersFormat"}}}var i="When the data format is date, "+b+" should be set.";throw new Error(i)}d=f.toFixed(c)}else{if(this._dataType=="number"){d=a.jqx.dataFormat.formatnumber(f,g)}else{d=this._roundDate(f);d=a.jqx.dataFormat.formatdate(d,g)}}}}return d},_getValue:function(l){var i=this,h=i.minorTicksInterval;function j(m){if(i._dataType==="number"){return Math.round(m/h)*h}else{return i._roundNumber(m,"marker")}}var c=this.slider;var d=c.width();var g=new Object();var f=c.offset().left-this.rangeSelector.offset().left;var k=(f*this._unitPerPixel+this._min);g.from=j(k);g.to=j(k+d*this._unitPerPixel);if(!l&&this.snapToTicks==true){var b=this._findNearestTick((g.from-this._min)/this._unitPerPixel);g.from=this._valuesArray[this._ticksArray.indexOf(b)];var e=this._findNearestTick((g.to-this._min)/this._unitPerPixel);g.to=this._valuesArray[this._ticksArray.indexOf(e)]}if(this._dataType=="date"){g.from=new Date(g.from);g.to=new Date(g.to)}return g},_roundNumber:function(e,b,d){var c;if(b=="marker"){if(d==true){e=parseFloat(e)}c=parseFloat(e)}else{if(b=="label"){c=parseFloat(e)}else{c=parseFloat(e)}}return c},_roundDate:function(c){if(typeof c=="number"){c=new Date(c)}var d=this._max-this._min;if(d>1209600000){var e=c.getDate();var b=c.getHours();if(b>12){c.setDate(e+1);c.setHours(0);c.setMinutes(0);c.setSeconds(0)}}else{if(d>172800000){c.setHours(c.getHours()+Math.round(c.getMinutes()/60));c.setMinutes(0);c.setSeconds(0)}}return c},_layoutShutter:function(){var d=parseInt(this.slider[0].style.left);this.shutterLeft[0].style.width=d+"px";this.shutterLeft[0].style.left="0px";if(a.jqx.browser.msie&&a.jqx.browser.version<9){this.shutterLeft[0].style.filter="progid:DXImageTransform.Microsoft.Alpha(Opacity=75)";this.shutterRight[0].style.filter="progid:DXImageTransform.Microsoft.Alpha(Opacity=75)"}var c=1+d+parseInt(this.slider[0].style.width);this.shutterRight[0].style.left=c+"px";var e=parseInt(this.element.style.width);if(this.element.style.width.indexOf("%")>=0){var e=parseInt(this.host.width())}var b=e-1-d-parseInt(this.slider[0].style.width);if(b<0){b=0}this.shutterRight[0].style.width=1+b+"px";if(c+1+b<2+e){this.shutterRight[0].style.width=2+b+"px"}if(b==0){this.shutterRight[0].style.width="0px"}},_initRange:function(){if(this._range._from>this._range._to){throw new Error("jqxRangeSelector: range object initialization error. 'min' should be less than 'max'");return}var c=this;var e=this._range._from;var b=this._range._to;var d={left:e,right:b};return d}})})(jqxBaseFramework);

