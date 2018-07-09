<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ page isELIgnored="false" %>
<div class="bjui-pageContent" id="${lowerName}_look">
    	<input type="hidden" name="id" value="${data.id}">
    	#set ($perfix ='${data.') 
    	#foreach($po in $!{columnDatas})
    	#if  ($po.columnName !='id')
    	<p>
    		<label class="control-label x120" for="datagrid-dialog-edit-column-1">${po.columnComment}：</label>
    		<span class="datagrid-dialog-column-span">
    			${perfix}${po.columnName}}
    		</span>
    	</p>
    	#end
		#end
</div>

<div class="bjui-pageFooter">
    <ul>
    	<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
    	<li><button type="button" class="btn btn-green" data-icon="refresh" onclick="${lowerName}.refresh()"> 刷新</button></li>
    	<li><button type="button" class="btn btn-blue" data-icon="print" onclick="do_print('${lowerName}_look')">打印</button></li>
    </ul>
</div>

<script>
(function (doc, win) {
	function refresh(){
		BJUI.dialog('refresh', 'look_dialog');
	}
	if(win.${lowerName}==null){
		win.${lowerName}={};
	}
	win.${lowerName}.refresh=refresh;
})(document, window);	
</script>