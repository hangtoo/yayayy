webpackJsonp([46],{G2ze:function(e,t,a){t=e.exports=a("FZ+f")(!1),t.push([e.i,".handle-box[data-v-04884412]{margin-bottom:20px}.handle-select[data-v-04884412]{width:120px}.handle-input[data-v-04884412]{width:150px;display:inline-block}",""])},GOYg:function(e,t,a){"use strict";function i(e){a("TxM1")}Object.defineProperty(t,"__esModule",{value:!0});var r=a("gDYl"),s=a.n(r),o=a("VsrN"),l=a("VU/8"),d=i,n=l(s.a,o.a,!1,d,"data-v-04884412",null);t.default=n.exports},TxM1:function(e,t,a){var i=a("G2ze");"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);a("rjj0")("6d2fbfac",i,!0)},VsrN:function(e,t,a){"use strict";var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container calendar-list-container"},[a("div",{staticClass:"handle-box"},[a("el-button",{staticClass:"handle-add mr10",attrs:{type:"primary",icon:"add"},on:{click:e.handleAdd}},[e._v("新增")]),e._v(" "),a("el-input",{staticClass:"handle-input mr10",attrs:{placeholder:"客户信息id(用于关联)"},model:{value:e.searchForm.custom_id,callback:function(t){e.$set(e.searchForm,"custom_id",t)},expression:"searchForm.custom_id"}}),e._v(" "),a("el-input",{staticClass:"handle-input mr10",attrs:{placeholder:"银行id(用于关联)"},model:{value:e.searchForm.bank_id,callback:function(t){e.$set(e.searchForm,"bank_id",t)},expression:"searchForm.bank_id"}}),e._v(" "),a("el-input",{staticClass:"handle-input mr10",attrs:{placeholder:"风险等级(1=保守型2=谨慎型3=稳健型4=积极型5=激进型)"},model:{value:e.searchForm.risk_lev,callback:function(t){e.$set(e.searchForm,"risk_lev",t)},expression:"searchForm.risk_lev"}}),e._v(" "),a("el-input",{staticClass:"handle-input mr10",attrs:{placeholder:"风险测评分数"},model:{value:e.searchForm.risk_score,callback:function(t){e.$set(e.searchForm,"risk_score",t)},expression:"searchForm.risk_score"}}),e._v(" "),a("el-input",{staticClass:"handle-input mr10",attrs:{placeholder:"风险测评描述"},model:{value:e.searchForm.risk_desc,callback:function(t){e.$set(e.searchForm,"risk_desc",t)},expression:"searchForm.risk_desc"}}),e._v(" "),a("el-input",{staticClass:"handle-input mr10",attrs:{placeholder:"创建时间"},model:{value:e.searchForm.createtime,callback:function(t){e.$set(e.searchForm,"createtime",t)},expression:"searchForm.createtime"}}),e._v(" "),a("el-input",{staticClass:"handle-input mr10",attrs:{placeholder:"更新时间"},model:{value:e.searchForm.updatetime,callback:function(t){e.$set(e.searchForm,"updatetime",t)},expression:"searchForm.updatetime"}}),e._v(" "),a("el-button",{attrs:{type:"primary",icon:"search"},on:{click:e.handleSearch}},[e._v("搜索")])],1),e._v(" "),a("el-table",{ref:"multipleTable",staticStyle:{width:"100%"},attrs:{data:e.data,border:""},on:{"sort-change":e.handleSort}},[a("el-table-column",{attrs:{prop:"custom_id",label:"客户信息id(用于关联)"}}),e._v(" "),a("el-table-column",{attrs:{prop:"bank_id",label:"银行id(用于关联)"}}),e._v(" "),a("el-table-column",{attrs:{prop:"risk_lev",label:"风险等级(1=保守型2=谨慎型3=稳健型4=积极型5=激进型)"}}),e._v(" "),a("el-table-column",{attrs:{prop:"risk_score",label:"风险测评分数"}}),e._v(" "),a("el-table-column",{attrs:{prop:"risk_desc",label:"风险测评描述"}}),e._v(" "),a("el-table-column",{attrs:{prop:"createtime",label:"创建时间"}}),e._v(" "),a("el-table-column",{attrs:{prop:"updatetime",label:"更新时间"}}),e._v(" "),a("el-table-column",{attrs:{label:"操作",width:"150"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"small"},on:{click:function(a){e.handleEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(a){e.handleDelete(t.$index,t.row)}}},[e._v("删除")])]}}])})],1),e._v(" "),a("div",{staticClass:"pagination"},[a("el-pagination",{attrs:{layout:"prev, pager, next",total:e.searchForm.totalRow},on:{"current-change":e.handleCurrentChange}})],1),e._v(" "),a("el-dialog",{attrs:{title:"编辑用户银行风险表",visible:e.dialogFormVisible,size:"tiny"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"editForm",attrs:{model:e.editForm,"label-width":"80px"}},[a("input",{directives:[{name:"model",rawName:"v-model",value:e.editForm.id,expression:"editForm.id"}],attrs:{type:"hidden"},domProps:{value:e.editForm.id},on:{input:function(t){t.target.composing||e.$set(e.editForm,"id",t.target.value)}}}),e._v(" "),a("el-form-item",{attrs:{label:"客户信息id(用于关联)"}},[a("el-input",{attrs:{disabled:!e.editForm.abled.custom_id},model:{value:e.editForm.custom_id,callback:function(t){e.$set(e.editForm,"custom_id",t)},expression:"editForm.custom_id"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"银行id(用于关联)"}},[a("el-input",{attrs:{disabled:!e.editForm.abled.bank_id},model:{value:e.editForm.bank_id,callback:function(t){e.$set(e.editForm,"bank_id",t)},expression:"editForm.bank_id"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"风险等级(1=保守型2=谨慎型3=稳健型4=积极型5=激进型)"}},[a("el-input",{attrs:{disabled:!e.editForm.abled.risk_lev},model:{value:e.editForm.risk_lev,callback:function(t){e.$set(e.editForm,"risk_lev",t)},expression:"editForm.risk_lev"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"风险测评分数"}},[a("el-input",{attrs:{disabled:!e.editForm.abled.risk_score},model:{value:e.editForm.risk_score,callback:function(t){e.$set(e.editForm,"risk_score",t)},expression:"editForm.risk_score"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"风险测评描述"}},[a("el-input",{attrs:{disabled:!e.editForm.abled.risk_desc},model:{value:e.editForm.risk_desc,callback:function(t){e.$set(e.editForm,"risk_desc",t)},expression:"editForm.risk_desc"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"创建时间"}},[a("el-input",{attrs:{disabled:!e.editForm.abled.createtime},model:{value:e.editForm.createtime,callback:function(t){e.$set(e.editForm,"createtime",t)},expression:"editForm.createtime"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"更新时间"}},[a("el-input",{attrs:{disabled:!e.editForm.abled.updatetime},model:{value:e.editForm.updatetime,callback:function(t){e.$set(e.editForm,"updatetime",t)},expression:"editForm.updatetime"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary",loading:e.editLoading},on:{click:e.handleSave}},[e._v("保存")]),e._v(" "),a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")])],1)],1)],1)],1)},r=[],s={render:i,staticRenderFns:r};t.a=s},gDYl:function(e,t,a){"use strict";function i(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var r=a("woOf"),s=i(r),o=a("3pLw"),l=i(o);t.default={data:function(){return{getdataurl:"http://10.0.1.152:9999/sunvue-gateway-webapp/tCustomerBankRisk/getId.do",dataurl:"http://10.0.1.152:9999/sunvue-gateway-webapp/tCustomerBankRisk/dataList.do",saveurl:"http://10.0.1.152:9999/sunvue-gateway-webapp/tCustomerBankRisk/save.do",deleteurl:"http://10.0.1.152:9999/sunvue-gateway-webapp/tCustomerBankRisk/delete.do",dialogFormVisible:!1,editLoading:!1,editForm:{custom_id:"",bank_id:"",risk_lev:"",risk_score:"",risk_desc:"",createtime:"",updatetime:"",abled:{custom_id:!0,bank_id:!0,risk_lev:!0,risk_score:!0,risk_desc:!0,createtime:!0,updatetime:!0}},editForm_sysMenu_ids:[],searchForm:{custom_id:"",bank_id:"",risk_lev:"",risk_score:"",risk_desc:"",createtime:"",updatetime:"",pageNum:1,totalRow:0,pageSize:10,is_search:!1,order:""},tableData:[]}},created:function(){this.loadData()},computed:{data:function(){return this.tableData}},methods:{loadData:function(){var e=this,t=this;l.default.post(t.dataurl,this.searchForm,function(a){t.tableData=a.data.list,t.searchForm.totalRow=a.data.totalRow,e.searchForm.is_search=!1}).catch(function(t){e.$eventHub.$emit("error",t)})},handleCurrentChange:function(e){this.searchForm.pageNum=e,this.loadData()},handleSearch:function(){this.searchForm.is_search=!0,this.loadData()},handleSort:function(e){return console.log(e),this.searchForm.order=e.prop+" "+e.order.replace("ending",""),this.handleSearch(),!1},handleAdd:function(){this.dialogFormVisible=!0,this.editForm={custom_id:"",bank_id:"",risk_lev:"",risk_score:"",risk_desc:"",createtime:"",updatetime:"",abled:{custom_id:!0,bank_id:!0,risk_lev:!0,risk_score:!0,risk_desc:!0,createtime:!0,updatetime:!0}}},handleEdit:function(e,t){var a=this;a.dialogFormVisible=!0,a.editForm=(0,s.default)({},t),a.editForm.abled||(a.editForm.abled={}),a.editForm.abled.rolecode=!1,l.default.post(a.getdataurl+"?id="+a.editForm.id,{},function(e){a.editForm.custom_id=e.data.custom_id,a.editForm.bank_id=e.data.bank_id,a.editForm.risk_lev=e.data.risk_lev,a.editForm.risk_score=e.data.risk_score,a.editForm.risk_desc=e.data.risk_desc,a.editForm.createtime=e.data.createtime,a.editForm.updatetime=e.data.updatetime}).catch(function(e){a.$eventHub.$emit("error",e)})},handleDelete:function(e,t){var a=this;console.log(t),l.default.post(a.deleteurl,{id:t.id},function(e){a.loadData()}).catch(function(e){a.$eventHub.$emit("error",e)})},handleSave:function(){var e=this;l.default.post(e.saveurl,{id:e.editForm.id,custom_id:e.editForm.custom_id,bank_id:e.editForm.bank_id,risk_lev:e.editForm.risk_lev,risk_score:e.editForm.risk_score,risk_desc:e.editForm.risk_desc,createtime:e.editForm.createtime,updatetime:e.editForm.updatetime},function(t){e.loadData(),e.dialogFormVisible=!1}).catch(function(t){e.$eventHub.$emit("error",t)})},handleFalse:function(){return!1}}}}});