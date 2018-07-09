<style scoped>

.handle-box {
    margin-bottom: 20px;
    line-height:40px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 150px;
    display: inline-block;
}

</style>

<template>
<div class="app-container calendar-list-container">
    <div class="handle-box">
		<el-input v-model="searchForm.promotion_name" placeholder="活动名称"  class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.promotion_id" placeholder="活动编号"  type="number" class="handle-input mr10"></el-input>
		<el-date-picker v-model="searchForm.searchbegintime":editable="false" placeholder="创建时间(起)"  type="datetime"  ></el-date-picker>
		<el-date-picker v-model="searchForm.searchendtime":editable="false" placeholder="创建时间(终)"  type="datetime"  ></el-date-picker>
        <el-button-group>
	        <el-button type="primary" @click="handleSearch">搜索</el-button>
	        <el-button type="primary" @click="handleReset">重置</el-button>
	          
        </el-button-group>	
    </div>

    <div class="handle-box">
      <el-button-group>
		<el-button size="primary" type="primary" icon="el-icon-search"  @click="handleLook">查看</el-button>
		<el-button size="primary" type="primary" icon="el-icon-plus"  @click="handleAdd">新增</el-button>
		<el-button size="primary" type="warning"icon="el-icon-edit" @click="handleEdit">编辑</el-button>
		<el-button size="primary" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
	</el-button-group>		
    </div>

    <el-table :data="data" border fit highlight-current-row style="width: 100%" ref="singleTable" @sort-change="handleSort">

    	 <el-table-column type="index" label="序号" width="80">
    	  <template slot-scope="scope">
                  <el-radio :label="(scope.$index+1)" v-model="radio" @change.native="getCurrentRow(scope.$index+1,scope.row)"></el-radio>
          </template>
        </el-table-column>


        <el-table-column prop="promotion_name" label="活动名称" min-width="200"align="center">
        </el-table-column>


        <el-table-column prop="promotion_id" label="活动编号" width="150"align="center">
        </el-table-column>


        <el-table-column prop="begintime" label="活动开始时间" width="180"align="center">
        </el-table-column>


        <el-table-column prop="endtime" label="活动结束时间" width="180"align="center">
        </el-table-column>


        <el-table-column prop="promotion_interface" label="活动接口"min-width="150">
        </el-table-column>


        <el-table-column prop="promotion_amt_string" label="活动预算(元)"  width="130" align="center">
        </el-table-column>


        <el-table-column prop="createtime" label="创建时间" width="180"align="center">
        </el-table-column>


        <el-table-column prop="creater" label="创建人" width="140" align="center">
        </el-table-column>


        <el-table-column prop="remark" label="备注" min-width="180">
        </el-table-column>




    </el-table>
    <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="searchForm.totalRow">
        </el-pagination>
    </div>

    <el-dialog title="查看" :visible.sync="dialogLookVisible" >
     <el-form v-loading="lookloading">
    	<div class="handle-box">
			<el-row :gutter="20">
				<el-col :span="10">
					<span>活动名称： {{editForm.promotion_name}}</span>
				</el-col>
				<el-col :span="10">
					<span>活动编号： {{editForm.promotion_id}}</span>
				</el-col>
			</el-row>
		</div>
    	<div class="handle-box">
			<el-row :gutter="20">
				<el-col :span="10">
					<span>活动预算： {{editForm.promotion_amt_string}}</span>
				</el-col>
				<el-col :span="10">
					<span>有效时间： {{editForm.begintime}} - {{editForm.endtime}}</span>
				</el-col>
			</el-row>
		</div>
    	<div class="handle-box">
			<el-row :gutter="20">
				<el-col :span="10">
					<span>活动接口： {{editForm.promotion_interface}}</span>
				</el-col>
				<el-col :span="10">
					<span>创建时间： {{editForm.createtime}}</span>
				</el-col>
			</el-row>
		</div>
		<div class="handle-box">
			<span>活动类型： {{editForm.promotion_type_str}}</span>
		</div>
		<div class="handle-box">
			<span>活动参数： {{editForm.promotion_json}}</span>
		</div>

		<div class="handle-box">
			<span>活动规则：</span> <span v-show="!look.detail" > {{look.detailstr}}</span>
			<el-input v-model="editForm.detail" :maxlength="511":autosize="{minRows:2, maxRows: 6}" type="textarea"  v-show="look.detail" ></el-input>

		</div>


        <el-button @click="look.detail=true" v-show="!look.detail">编辑规则</el-button>
        <el-button type="primary" @click="handleSaveDetail" v-show="look.detail">保存</el-button>
        <el-button @click="look.detail=false" v-show="look.detail">取消编辑</el-button>
        <div class="handle-box">
           	<span>  </span>
        </div>
        <div class="grid-content bg-purple-color">
        	<span class="el-dialog__title">参加活动用户名单</span>

        	<div>
    		<el-button @click="exportTable">导出表格</el-button> </div>
            	<div>
                    <el-table :data="PromCustData">
                        <el-table-column property="custome_invest_no" label="订单编号" align="center" min-width="160"></el-table-column>
                        <el-table-column property="custom_name" label="姓名" align="center" min-width="100"></el-table-column>
                        <el-table-column property="custom_mobile" label="注册手机号" align="center" min-width="120"></el-table-column>
                        <el-table-column property="invest_amt_string" label="投资金额(元)" align="center" min-width="120"></el-table-column>
                        <el-table-column property="reward_amt_string" label="活动奖励(元)" align="center"min-width="120"></el-table-column>
                        <el-table-column property="use_flag" label="奖励状态"align="center" min-width="100"></el-table-column>
                 	</el-table>
                 </div>
        </div>
        <div class="pagination">
        <el-pagination @current-change="handleLookChange" layout="prev, pager, next" :total="userForm.totalRow">
        </el-pagination>
    </div>

      </el-form>
    </el-dialog>


    <el-dialog  title="活动管理"  :visible.sync="dialogFormVisible" size="tiny">
		<el-form v-loading="loading":ref="editForm" :model="editForm" label-width="100px">
			<input v-model="editForm.id" type="hidden"></input>
			<el-form-item label="活动名称"  prop="promotion_name">
				<el-input v-model="editForm.promotion_name" :maxlength="19":disabled="!editForm.abled.promotion_name" ></el-input>
			</el-form-item>
			<el-form-item label="活动编号"  prop="promotion_id">
				<el-input  type="number" v-model="editForm.promotion_id":maxlength="15":disabled="!editForm.abled.promotion_id" placeholder="例：20180315"></el-input>
			</el-form-item>
			<el-form-item label="活动类型" prop="promotion_type">
				<el-select v-model="editForm.promotion_type"   filterable placeholder="请选择活动类型">
					<el-option
						v-for="item in options"
						:key="item.value"
						:label="item.label"
						:value="item.value">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="开始时间" prop="begintime">
				<el-date-picker v-model="editForm.begintime":editable="false" type="datetime"  ></el-date-picker>
			</el-form-item>
			<el-form-item label="结束时间" prop="endtime">
				<el-date-picker v-model="editForm.endtime":editable="false" type="datetime"  ></el-date-picker>
			</el-form-item>
			
			<el-form-item label="活动接口" prop="promotion_interface">
				<el-input v-model="editForm.promotion_interface" :maxlength="49" placeholder="例：activity20180315"></el-input>
			</el-form-item>
			<el-form-item label="活动预算(元)" prop="promotion_amt">
				<el-input type="number" v-model="editForm.promotion_amt" :maxlength="11"  min="0"></el-input>
			</el-form-item>
		
			<el-form-item label="活动参数" prop="promotion_json">
				<el-input v-model="editForm.promotion_json" ></el-input>
			</el-form-item>
			<el-form-item label="备注">
				<el-input v-model="editForm.remark":maxlength="127"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="handleSave" :loading="editLoading">保存</el-button>
				<el-button @click="dialogFormVisible = false">取消</el-button>
			</el-form-item>
		</el-form>
    </el-dialog>


</div>

</template>

<script>
import http from '@/utils/http';
import { fdate } from '@/utils/fdate';
export default {
    data() {
            return {
                getdataurl: process.env.BASE_API + 'tPromotion/getId.do',
                dataurl: process.env.BASE_API + 'tPromotion/dataList.do',
                saveurl: process.env.BASE_API + 'tPromotion/save.do',
                savedetailurl: process.env.BASE_API + 'tPromotion/savedetail.do',
                deleteurl: process.env.BASE_API + 'tPromotion/delete.do',
                lookurl: process.env.BASE_API + 'tPromotionCustomer/dataList.do',
                dialogFormVisible: false,
                dialogLookVisible: false,
                editLoading: false,
                editForm: {
    		    			    		promotion_name: '',
	    			    				promotion_id: '',
	    			    				begintime: '',
	    			    				endtime: '',
	    			    				promotion_interface: '',
	    			    				promotion_amt: '',
	    			    				promotion_amt_string: '',
	    			    				promotion_type: '',
	    			    				promotion_type_str: '',
	    			    				promotion_json: '',
	    			    				createtime: '',
	    			    				creater: '',
	    			    				remark: '',
	    			    				detail: '',
	    		                    abled: {

    		    			    		promotion_name: true,
	    			    				promotion_id: true,
	    			    				begintime: true,
	    			    				endtime: true,
	    			    				promotion_interface: true,
	    			    				promotion_amt: true,
	    			    				promotion_amt_string: true,
	    			    				createtime: true,
	    			    				creater: true,
	    			    				remark: true,
	    			    				detail: true,
	    		                    }
                },
                look:{
	    			   detailstr:'',
	    			   detail:false
	    		},
                editForm_sysMenu_ids:[],
                searchForm: {
    		    			    	    promotion_name: '',
	    			    				promotion_id: '',
	    			    				begintime: '',
	    			    				endtime: '',
	    			    				promotion_interface: '',
	    			    				promotion_amt: '',
	    			    				promotion_type: '',
	    			    				promotion_json: '',
	    			    				createtime: '',
	    			    				creater: '',
	    			    				remark: '',
	    			    				detail: '',
	    			    				searchbegintime: '',
	    			    				searchendtime: '',

                    pageNum: 1,
                    totalRow: 0,
                    pageSize: 10,
                    is_search: false,
                    order: '',
                },
                userForm:{
                	t_promotion_id:'',
                	pageNum: 1,
                    totalRow: 0,
                    pageSize: 10,
                },
                PromCustData: [],
                tableData: [],
                allData: [],
                radio:'',
                rowData:'',
                loading: false,
                lookloading: false,
                options: [{
			          value: '1',
			          label: '爆款产品'
			        }],

                rules: {
                    promotion_name: [
                        { required: true, message: '请输入活动名称', trigger: 'blur' }
                    ],
                    promotion_id: [
                        { required: true, message: '请输入活动编号', trigger: 'blur' }
                    ],
                    begintime: [
                        { required: true, message: '请选择开始时间', trigger: 'blur' }
                    ],
                    endtime: [
                        { required: true, message: '请选择结束时间', trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            this.loadData();
        },
        computed: {
            data() {
                const self = this;
                return self.tableData;
            }
        },
        methods: {
                loadData() {
                    let self = this;
                    self.rowData='';
                    self.radio='';
                    http.post(self.dataurl, this.searchForm,(res) => {
                        self.tableData = res.data.list;
                        self.searchForm.totalRow = res.data.totalRow;
                        for (var i = 0; i < self.tableData.length; i++) {
	                    	let p_type = self.tableData[i].promotion_type;
	                    	if("1"==p_type){
	                        	self.tableData[i].promotion_type_str="爆款产品";
	                   		}
	                   		
	                   		if( self.tableData[i].createtime!=null){
                            	self.tableData[i].createtime = fdate(self.tableData[i].createtime);
                       		}
                       		if( self.tableData[i].begintime!=null){
                            	self.tableData[i].begintime = fdate(self.tableData[i].begintime);
                       		}
                       		if( self.tableData[i].endtime!=null){
                            	self.tableData[i].endtime = fdate(self.tableData[i].endtime);
                       		}

	                   }


                        this.searchForm.is_search = false;
                    }).catch(err => {
                        this.$eventHub.$emit('error', err);
                    })
                },
                handleCurrentChange(val) {
                    this.searchForm.pageNum = val;
                    this.loadData();
                },
                handleSearch() {
                    this.searchForm.is_search = true;
                    this.loadData();
                },
                handleSort(val) {
                    console.log(val);
                    this.searchForm.order = val.prop + " " + val.order.replace("ending", "");
                    this.handleSearch();
                    return false;
                },
                handleAdd() {
                    this.dialogFormVisible = true;
                    this.loading=false;
                    this.editForm = {
    		    			    		promotion_name: '',
	    			    				promotion_id: '',
	    			    				begintime: '',
	    			    				endtime: '',
	    			    				promotion_interface: '',
	    			    				promotion_amt: '',
	    			    				createtime: '',
	    			    				creater: '',
	    			    				remark: '',
	    			    				detail: '',
	    			    				show:{
	    			    				detailstr:'',
	    			    				detail:false
	    			    				},
	    		                        abled: {
    		    			    				promotion_name: true,
	    			    				promotion_id: true,
	    			    				begintime: true,
	    			    				endtime: true,
	    			    				promotion_interface: true,
	    			    				promotion_amt: true,
	    			    				createtime: true,
	    			    				creater: true,
	    			    				remark: true,
	    			    				detail: true,
	    		                        }
                    };
                },
                handleEdit() {
                    let self = this;
                    this.loading=false;
                    if(self.radio){
                    self.dialogFormVisible = true;
                    self.editForm = Object.assign({}, self.rowData);
                    if (!self.editForm.abled) {
                        self.editForm.abled = {};
                    }
                    self.editForm.abled.rolecode = false;
                    http.post(self.getdataurl + '?id=' + self.editForm.id, {},response => {
    		    			    		self.editForm.promotion_name=response.data.promotion_name;
	    			    				self.editForm.promotion_id=response.data.promotion_id;
	    			    				self.editForm.begintime=response.data.begintime;
	    			    				self.editForm.endtime=response.data.endtime;
	    			    				self.editForm.promotion_interface=response.data.promotion_interface;
	    			    				self.editForm.promotion_amt=response.data.promotion_amt;
	    			    				self.editForm.promotion_type=response.data.promotion_type;
	    			    				self.editForm.promotion_json=response.data.promotion_json;
	    			    				self.editForm.createtime=response.data.createtime;
	    			    				self.editForm.creater=response.data.creater;
	    			    				self.editForm.remark=response.data.remark;
	    			    				self.editForm.detail=response.data.detail;
	    		                        return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })

                    }else{
                    	this.$message('请选择活动');
                    }
                },
                handleLook() {
                	let self = this;
                	self.lookloading=false;
                    if(self.radio){
                    this.userForm.t_promotion_id=self.rowData.id;
                   		http.post(self.lookurl, this.userForm,response => {

		                     self.PromCustData=response.data.list;
		                     self.allData=response.data.alllist;
		                     self.userForm.totalRow = response.data.totalRow;
		                        console.log("response=",response);

		                    	for (var i = 0; i < self.PromCustData.length; i++) {
	                      	 		let use_flag = self.PromCustData[i].use_flag;
	                            	if("1"==use_flag){
	                            		self.PromCustData[i].use_flag="已使用";
	                            	}
	                            	if("0"==use_flag){
	                            		self.PromCustData[i].use_flag="未使用";
	                            	}
	                            }
	                            for (var i = 0; i < self.allData.length; i++) {
	                      	 		let use_flag = self.allData[i].use_flag;
	                            	if("1"==use_flag){
	                            		self.allData[i].use_flag="已使用";
	                            	}
	                            	if("0"==use_flag){
	                            		self.allData[i].use_flag="未使用";
	                            	}
	                            }


		                    self.dialogLookVisible = true;
	                    	self.editForm = Object.assign(self.editForm, self.rowData);
	                    	self.look.detailstr=self.editForm.detail;
							self.look.detail=false;

	                        self.dialogLookVisible=true;

	                    }).catch(err => {
	                        self.$eventHub.$emit('error', err);
	                    })

                    }else{
                    	this.$message('请选择活动');
                    }

                },
                handleLookChange(val) {
                    this.userForm.pageNum = val;
                    this.handleLook();
                },
                handleDelete() {
                    const self = this;

                    if(self.radio){
	                    this.$confirm('确定删除此条活动？', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
						}).then(() => {
							http.post(self.deleteurl, {
	                        id: self.rowData.id,
		                    },response => {
		                        self.loadData();
		                        return;
		                    }).catch(err => {
		                        self.$eventHub.$emit('error', err);
		                    })
						}).catch(() => {

						});
                    }else{
                    	this.$message('请选择活动');
                    }
                },
                handleReset(){
                  this.searchForm.is_search = false;
                  this.searchForm = {
      		    		promotion_name: '',
  	    			    promotion_id: '',
  	    			    searchbegintime: '',
  	    			    searchendtime: '',


                      is_search: false,
                      order: '',
                  }
                },
                handleSave() {
                    const self = this;
                    self.loading=true;
                    http.post(self.saveurl, {
    				id:self.editForm.id,
					promotion_name:self.editForm.promotion_name,
					promotion_id:self.editForm.promotion_id,
					begintime:self.editForm.begintime,
					endtime:self.editForm.endtime,
					promotion_interface:self.editForm.promotion_interface,
					promotion_amt:self.editForm.promotion_amt,
					promotion_type:self.editForm.promotion_type,
					promotion_json:self.editForm.promotion_json,
					createtime:self.editForm.createtime,
					creater:self.editForm.creater,
					remark:self.editForm.remark,
					detail:self.editForm.detail,
	                },response => {
	                	self.loading=false;
	                	if(response.status==200&&!!response.data&&response.data.statusCode==200){
		                	self.loadData();
	                        self.dialogFormVisible = false;
	                        return;
	                	}else{
	                	 	this.$message.error({
                            	title: '错误',
                            	message: response.data.msg
                            });
	                	}

                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })
                },
                handleSaveDetail() {
                    const self = this;
					self.lookloading=true;
                    http.post(self.savedetailurl, {
    				id:self.editForm.id,
				    detail:self.editForm.detail,
	                    },response => {
	                    self.lookloading=false;
	                	if(response.status==200&&!!response.data&&response.data.statusCode==200){
	                		self.look.detailstr=self.editForm.detail;
	                		self.look.detail=false;
	                		self.loadData();
                        	return;
	                	}else{
	                	 	this.$message.error({
                            	title: '错误',
                            	message: response.data.msg
                            });
	                	}

                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })
                },
                handleFalse() {
                    return false;
                },
                getCurrentRow(index,row){
                  console.log("radio=",this.radio);
                  console.log("row=",row);
                  this.rowData = row;
                  this.$refs.singleTable.setCurrentRow(row);
                },
                exportTable() {
					require.ensure([], () => {
	            	var myDate = new Date().toDateString();
		        	const { export_json_to_excel } = require('../../vendor/Export2Excel');
		            const tHeader = ['订单编号', '姓名', '注册手机号', '投资金额（元）', '活动奖励（元）', '奖励状态'];
		            const filterVal = ['custome_invest_no', 'custom_name', 'custom_mobile', 'invest_amt_string', 'reward_amt_string', 'use_flag'];
		            const list = this.allData;
		            const data = this.formatJson(filterVal, list);
		            export_json_to_excel(tHeader, data, '参加活动用户名单'+myDate);
		          })
				},
	       		formatJson(filterVal, jsonData) {
					return jsonData.map(v => filterVal.map(j => v[j]))
	       		}
        }
}

</script>
