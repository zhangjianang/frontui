/*
jQWidgets v8.1.3 (2019-July)
Copyright (c) 2011-2019 jQWidgets.
License: https://jqwidgets.com/license/
*/
/* eslint-disable */

(function(a){a.extend(a.jqx._jqxGrid.prototype,{exportdata:function(s,C,B,q,t,v,h){if(!a.jqx.dataAdapter.ArrayExporter){throw"jqxGrid: Missing reference to jqxdata.export.js!"}if(B==undefined){B=true}var L=this;if(q==undefined){var q=this.getrows();if(q.length==0){throw"No data to export."}}this.exporting=true;if(!this.pageable){this.loadondemand=true}if(this.altrows){this._renderrows(this.virtualsizeinfo)}var A=this.hScrollInstance.value;this.hScrollInstance.setPosition(0);this._renderrows(this.virtualsizeinfo);var J=t!=undefined?t:false;var H={};var p={};var x=[];var n=this.host.find(".jqx-grid-cell:first");var y=this.host.find(".jqx-grid-cell-alt:first");n.removeClass(this.toThemeProperty("jqx-grid-cell-selected"));n.removeClass(this.toThemeProperty("jqx-fill-state-pressed"));y.removeClass(this.toThemeProperty("jqx-grid-cell-selected"));y.removeClass(this.toThemeProperty("jqx-fill-state-pressed"));n.removeClass(this.toThemeProperty("jqx-grid-cell-hover"));n.removeClass(this.toThemeProperty("jqx-fill-state-hover"));y.removeClass(this.toThemeProperty("jqx-grid-cell-hover"));y.removeClass(this.toThemeProperty("jqx-fill-state-hover"));var k="cell";var g=1;var I="column";var e=1;var l=[];for(var E=0;E<this.columns.records.length;E++){var f=this.columns.records[E];if(f.cellclassname!=""){f.customCellStyles=new Array();if(typeof f.cellclassname=="string"){for(var F=0;F<q.length;F++){f.customCellStyles[F]=f.cellclassname}}else{for(var F=0;F<q.length;F++){var w=this.getrowboundindex(F);var d=f.cellclassname(w,f.displayfield,q[F][f.displayfield],q[F]);if(d){f.customCellStyles[F]=d}}}}}var z=new Array();var K=null;var c=null;var b=null;a.each(this.columns.records,function(O){var S=a(L.table[0].rows[0].cells[O]);if(L.table[0].rows.length>1){var j=a(L.table[0].rows[1].cells[O]);if(!b){b=j}}if(!c){c=S}var N=this;var P=function(U){U.removeClass(L.toThemeProperty("jqx-grid-cell-selected"));U.removeClass(L.toThemeProperty("jqx-fill-state-pressed"));U.removeClass(L.toThemeProperty("jqx-grid-cell-hover"));U.removeClass(L.toThemeProperty("jqx-fill-state-hover"));if(N.customCellStyles){for(var V in N.customCellStyles){U.removeClass(N.customCellStyles[V])}}};P(S);if(j){P(j)}if(this.displayfield==null){return true}if(L.showaggregates){if(L.getcolumnaggregateddata){l.push(L.getcolumnaggregateddata(this.displayfield,this.aggregates,true,q))}}var R=L._getexportcolumntype(this);if(this.exportable&&(!this.hidden||J)){H[this.displayfield]={};H[this.displayfield].text=this.text;H[this.displayfield].width=parseInt(this.width);if(isNaN(H[this.displayfield].width)){H[this.displayfield].width=60}H[this.displayfield].formatString=this.cellsformat;H[this.displayfield].localization=L.gridlocalization;H[this.displayfield].type=R;H[this.displayfield].cellsAlign=this.cellsalign;H[this.displayfield].hidden=!B;H[this.displayfield].displayfield=this.displayfield;z.push(H[this.displayfield])}k="cell"+g;var T=a(this.element);if(this.element==undefined){T=a(this.uielement)}if(!K){K=T}else{if(!N._rendered){T=K;S=c;j=b;var Q=L.toTP("jqx-grid-cell")+" "+L.toTP("jqx-item");S[0].className=Q;Q+=L.toTP("jqx-grid-cell-alt");if(j){j[0].className=Q}}}I="column"+e;if(s=="html"||s=="xls"||s=="pdf"){var i=function(U,ac,ab,V,aa,X,W,Y,Z){p[U]={};if(ac==undefined){return}if(ac[0].offsetWidth==0||ac[0].offsetHeight==0){return}p[U]["font-size"]=ac.css("font-size");p[U]["font-weight"]=ac.css("font-weight");p[U]["font-style"]=ac.css("font-style");p[U]["background-color"]=X._getexportcolor(ac.css("background-color"));p[U]["color"]=X._getexportcolor(ac.css("color"));p[U]["border-color"]=X._getexportcolor(ac.css("border-top-color"));if(ab){p[U]["text-align"]=aa.align}else{p[U]["text-align"]=aa.cellsalign;p[U]["formatString"]=aa.cellsformat;p[U]["dataType"]=R}if(s=="html"||s=="pdf"){p[U]["border-top-width"]=ac.css("border-top-width");p[U]["border-left-width"]=ac.css("border-left-width");p[U]["border-right-width"]=ac.css("border-right-width");p[U]["border-bottom-width"]=ac.css("border-bottom-width");p[U]["border-top-style"]=ac.css("border-top-style");p[U]["border-left-style"]=ac.css("border-left-style");p[U]["border-right-style"]=ac.css("border-right-style");p[U]["border-bottom-style"]=ac.css("border-bottom-style");if(ab){if(W==0){p[U]["border-left-width"]=ac.css("border-right-width")}p[U]["border-top-width"]=ac.css("border-right-width");p[U]["border-bottom-width"]=ac.css("border-bottom-width")}else{if(W==0){p[U]["border-left-width"]=ac.css("border-right-width")}}p[U]["height"]=ac.css("height")}if(aa.exportable&&(!aa.hidden||J)){if(Y==true){if(!H[aa.displayfield].customCellStyles){H[aa.displayfield].customCellStyles=new Array()}H[aa.displayfield].customCellStyles[Z]=U}else{if(ab){H[aa.displayfield].style=U}else{if(!V){H[aa.displayfield].cellStyle=U}else{H[aa.displayfield].cellAltStyle=U}}}}};i(I,T,true,false,this,L,O);e++;i(k,S,false,false,this,L,O);if(L.altrows){k="cellalt"+g;i(k,j,false,true,this,L,O)}if(this.customCellStyles){for(var M in N.customCellStyles){S.removeClass(N.customCellStyles[M])}for(var M in N.customCellStyles){S.addClass(N.customCellStyles[M]);i(k+N.customCellStyles[M],S,false,false,this,L,O,true,M);S.removeClass(N.customCellStyles[M])}}g++}});a.each(this.columns.records,function(i){if(H[this.displayfield]){H[this.displayfield].columnsDataFields=z}});if(this.showaggregates){var G=[];var D=s=="xls"?"_AG":"";var m=this.groupable?this.groups.length:0;if(this.rowdetails){m++}if(this.selectionmode==="checkbox"){m++}if(l.length>0){a.each(this.columns.records,function(j){if(this.aggregates){for(var N=0;N<this.aggregates.length;N++){if(!G[N]){G[N]={}}if(G[N]){var O=L._getaggregatename(this.aggregates[N]);var P=L._getaggregatetype(this.aggregates[N]);var M=l[j-m];if(M){G[N][this.displayfield]=D+O+": "+M[P]}}}}});a.each(this.columns.records,function(j){for(var M=0;M<G.length;M++){if(G[M][this.displayfield]==undefined){G[M][this.displayfield]=D}}})}a.each(G,function(){q.push(this)})}var o=this;var u=a.jqx.dataAdapter.ArrayExporter(q,H,p);if(C==undefined){this._renderrows(this.virtualsizeinfo);var r=u.exportTo(s);if(this.showaggregates){a.each(G,function(){q.pop(this)})}setTimeout(function(){o.exporting=false},50);this.hScrollInstance.setPosition(A);this._renderrows(this.virtualsizeinfo);return r}else{u.exportToFile(s,C,v,h)}if(this.showaggregates){a.each(G,function(){q.pop(this)})}this._renderrows(this.virtualsizeinfo);setTimeout(function(){o.exporting=false},50);this.hScrollInstance.setPosition(A);this._renderrows(this.virtualsizeinfo)},_getexportcolor:function(l){var f=l;if(l=="transparent"){f="#FFFFFF"}if(!f||!f.toString()){f="#FFFFFF"}if(f.toString().indexOf("rgb")!=-1){var i=f.split(",");if(f.toString().indexOf("rgba")!=-1){var d=parseInt(i[0].substring(5));var h=parseInt(i[1]);var j=parseInt(i[2]);var k=parseInt(i[3].substring(1,4));var m={r:d,g:h,b:j};var e=this._rgbToHex(m);if(d==0&&h==0&&j==0&&k==0){return"#ffffff"}return"#"+e}var d=parseInt(i[0].substring(4));var h=parseInt(i[1]);var j=parseInt(i[2].substring(1,4));var m={r:d,g:h,b:j};var e=this._rgbToHex(m);return"#"+e}else{if(f.toString().indexOf("#")!=-1){if(f.toString().length==4){var c=f.toString().substring(1,4);f+=c}}}return f},_rgbToHex:function(b){return this._intToHex(b.r)+this._intToHex(b.g)+this._intToHex(b.b)},_intToHex:function(c){var b=(parseInt(c).toString(16));if(b.length==1){b=("0"+b)}return b.toUpperCase()},_getexportcolumntype:function(f){var g=this;var e="string";var d=g.source.datafields||((g.source._source)?g.source._source.datafields:null);if(d){var i="";a.each(d,function(){if(this.name==f.displayfield){if(this.type){i=this.type}return false}});if(i){return i}}if(f!=null){if(this.dataview.cachedrecords==undefined){return e}var b=null;if(!this.virtualmode){if(this.dataview.cachedrecords.length==0){return e}b=this.dataview.cachedrecords[0][f.displayfield];if(b!=null&&b.toString()==""){return"string"}}else{a.each(this.dataview.cachedrecords,function(){b=this[f.displayfield];return false})}if(b!=null){if(f.cellsformat.indexOf("c")!=-1){return"number"}if(f.cellsformat.indexOf("n")!=-1){return"number"}if(f.cellsformat.indexOf("p")!=-1){return"number"}if(f.cellsformat.indexOf("d")!=-1){return"date"}if(f.cellsformat.indexOf("y")!=-1){return"date"}if(f.cellsformat.indexOf("M")!=-1){return"date"}if(f.cellsformat.indexOf("m")!=-1){return"date"}if(f.cellsformat.indexOf("t")!=-1){return"date"}if(typeof b=="boolean"){e="boolean"}else{if(a.jqx.dataFormat.isNumber(b)){e="number"}else{var h=new Date(b);if(h.toString()=="NaN"||h.toString()=="Invalid Date"){if(a.jqx.dataFormat){h=a.jqx.dataFormat.tryparsedate(b);if(h!=null){if(h&&h.getFullYear()){if(h.getFullYear()==1970&&h.getMonth()==0&&h.getDate()==1){var c=new Number(b);if(!isNaN(c)){return"number"}return"string"}}return"date"}else{e="string"}}else{e="string"}}else{e="date"}}}}}return e}})})(jqxBaseFramework);

