<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#${lowerName}')}">
    <fieldset>
		<legend style="font-weight:normal;">查询条件：</legend>
		<div class="bjui-row col-3" style="margin:0; padding:1px 5px 5px;">

    	#set ($perfix ='${data.') 
    	#foreach($po in $!{columnDatas})
    	#if  ($po.columnName !='id')
            <span class="row-label" style="text-align:right;">${po.columnComment}：</span>
            <input type="text" id="${po.columnName}" name="${po.columnName}" class="row-input form-control" />
    	#end
		#end
			<div style="width:30%;padding-left:2%;" class="btn-group">
                <button type="submit" class="btn-green"  style="padding: 0px 8%;" >查询</button>
	            <button type="reset" class="btn-orange"  style="padding: 0px 8%;margin-left:8%;" >重置</button>
            </div>
		</div>
    </fieldset>
</form>
</div>
<div class="bjui-pageContent">
<table class="table table-bordered" id="${lowerName}" data-toggle="datagrid" data-options="{
    height: '100%',
    tableWidth:'200%',
    width:'200%',
    gridTitle : '${codeName}',
    filterThead:false,
    quickfilter:false,
    showToolbar: true,
    toolbarCustom:${lowerName}.customBars,
    toolbarItem: 'add, |, edit, |, del, |, refresh',
    dataUrl: '${lowerName}/dataList.do',
    editUrl: '${lowerName}/edit.shtml',
    delUrl: '${lowerName}/delete.do',
    editMode: {dialog:{title:'编辑',mask:true,id:'edit_dialog'}},
    addLocation:'first',
    paging: {pageSize:50, pageCurrent:1},
    linenumberAll: true
}">
    <thead>
        <tr>
          #foreach($po in $!{columnDatas})
          #if  ($po.columnName =='id')
            <th data-options="{name:'${po.columnName}',hide:true}">${po.columnComment}</th>
          #end
          #if  ($po.columnName !='id')
            <th data-options="{name:'${po.columnName}'}">${po.columnComment}</th>
          #end
          #end
        </tr>
    </thead>
</table>
</div>
<script>

(function (doc, win) {
	function customBars(){
		var customBars='<button type="button" class="btn btn-blue" data-icon="book" onclick="${lowerName}.look()"><i class="fa fa-plus"></i>查看</button>';
		return customBars;
	}
	function look(){
 		var selectedDatas=$('#${lowerName}').data('selectedDatas');
		if(!!selectedDatas&&selectedDatas.length>0){
			var id=selectedDatas[0].id;		
		    BJUI.dialog({
		    	id:'look_dialog',
		        max:true,
		        url: '${lowerName}/look.shtml?id='+id,
		        title:'查看',
		        mask:true
		    })
	  	}
	}
	win.${lowerName}.customBars=customBars;
	win.${lowerName}.look=look;
})(document, window);	
</script>