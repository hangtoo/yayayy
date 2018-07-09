<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ page isELIgnored="false" %>
<div class="bjui-pageContent">
    <form action="${lowerName}/save.do" class="datagrid-edit-form" data-toggle="ajaxform" data-data-type="jsonp" name="editForm">
    	<input type="hidden" name="id" value="${data.id}">
    	#set ($perfix ='${data.') 
    	#foreach($po in $!{columnDatas})
    	#if  ($po.columnName !='id')
    	<p>
    		<label class="control-label x120" for="datagrid-dialog-edit-column-${po.columnName}">${po.columnComment}：</label>
    		<span class="datagrid-dialog-column-span">
    			<input type="text" name="${po.columnName}" value="${perfix}${po.columnName}}" id="datagrid-dialog-edit-column-${po.columnName}" size="30" class="form-control" style="width: 300px;">
    		</span>
    	</p>
    	#end
		#end
    </form>
</div>

<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn btn-default save" data-icon="save" onclick="${lowerName}.save()">保存</button></li>
    	<li><button type="button" class="btn btn-green" data-icon="refresh" onclick="${lowerName}.refresh()"> 刷新</button></li>
    	<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
    </ul>
</div>

<script>
(function (doc, win) {
	function refresh(){
		BJUI.dialog('refresh', 'edit_dialog');
	}
	function save(){
		BJUI.ajax('doajax', {
		    url: '${lowerName}/save.do',
		    data:{'json':JSON.stringify($('form[name="editForm"]').serializeJson())},
		    loadingmask: false,
		    okCallback: function(json, options) {
		        BJUI.dialog('close',"edit_dialog"); 
		        setTimeout(function() {
		        	$('#${lowerName}').datagrid('refresh',true);
		        },500);
		    }
		})
	}
	if(win.${lowerName}==null){
		win.${lowerName}={};
	}
	win.${lowerName}.save=save;
	win.${lowerName}.refresh=refresh;
})(document, window);	
</script>