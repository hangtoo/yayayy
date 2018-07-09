<style scoped>

.handle_button {
  padding-left: 15px;
}

.handle-box {
    margin-bottom: 20px;
    line-height:40px;
}

.handle-button_div {
    margin-bottom: 10px;
}

.handle-select {
    width: 150px;
}

.handle-input {
    width: 150px;
    display: inline-block;
}

.el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .el-col-6 {
    width: 22%;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }

</style>

<template>
<div class="app-container calendar-list-container">
    <!-- 搜索项 -->
    <div class="handle-box">

		<el-input v-model="searchForm.custome_invest_no" placeholder="订单号" class="handle-input mr10"></el-input>
		 <el-select v-model="searchForm.flag" clearable class="handle-select mr10" placeholder="订单状态">
			<el-option v-for="option in flag_options" :key="option. label" :value="option.value" :label="option. label">
			</el-option>
		 </el-select>
		<el-select v-model="searchForm.channel_no" clearable class="handle-select mr10" placeholder="渠道">
			<el-option v-for="option in channel_options" :key="option.channel_no" :value="option.channel_no" :label="option.channel_name">
			</el-option>
		</el-select>
		
		<el-input v-model="searchForm.min_invest_amt" v-show="expandAll" placeholder="订单金额（起）" class="handle-input" type="number" min="0"></el-input>
		<el-input v-model="searchForm.max_invest_amt" v-show="expandAll" placeholder="订单金额（终）" class="handle-input" type="number" min="0"></el-input>
		<el-date-picker  v-model="searchForm.min_createtime":editable="false"v-show="expandAll" type="datetime" placeholder="订单成交时间（起）" ></el-date-picker>
     	<el-date-picker  v-model="searchForm.max_createtime":editable="false"v-show="expandAll" type="datetime" placeholder="订单成交时间（终）" ></el-date-picker>
     	<el-button-group>
	     	<el-button type="primary" @click="handleSearch">搜索</el-button>
			<el-button type="primary" @click="handleReset">重置</el-button>
		</el-button-group>
		<a style="margin-left: 8px;font-size:10px;color:#409EFF;" v-show="!expandAll"  @click="expandSearchForm">展开<i  class="el-icon el-icon-arrow-down" aria-hidden="true"></i></a>
    	<a style="margin-left: 8px;font-size:10px;color:#409EFF;" v-show="expandAll"  @click="collapseSearchForm">收回<i  class="el-icon el-icon-arrow-up" aria-hidden="true"></i></a>
	</div>




    <div class="handle-button_div">
     <!-- <el-button size="100px;" @click="handleLook()">查看</el-button>-->
      <!-- <el-button size="100px;" type="danger" @click="handleDelete()">修改</el-button> -->
    </div>
    <el-table :data="data" border fit style="width: 120%" ref="multipleTable" highlight-current-row @sort-change="handleSort">
        <!--
        <el-table-column type="index" label="序号" width="80">
          <template slot-scope="scope">
                  <el-radio :label="(scope.$index+1)" v-model="radio" @change.native="getCurrentRow(scope.$index+1,scope.row)"></el-radio>
          </template>
        </el-table-column>  -->

        <el-table-column prop="custome_invest_no" label="订单号" min-width="170" align="center">
        </el-table-column>


        <el-table-column prop="bank_name" label="银行" min-width="100" align="center">
        </el-table-column>


        <el-table-column prop="invest_name" label="产品名称" min-width="150" align="center">
        </el-table-column>


        <el-table-column prop="channel_name" label="渠道" min-width="100" align="center">
        </el-table-column>


        <el-table-column prop="custom_name" label="姓名" min-width="100" align="center">
        </el-table-column>


        <el-table-column prop="invest_amt" label="订单金额（元）" min-width="150" align="center" >
        </el-table-column>


        <el-table-column prop="createtime" label="订单成交时间" width="200" align="center">
        </el-table-column>

        <el-table-column prop="invest_endDate" label="订单结束时间" width="190" align="center">
        </el-table-column>

          <el-table-column prop="flag" label="订单状态" width="100" align="center">
        </el-table-column>
		<!--
        <el-table-column prop="" label="相关合同及协议" width="170">
        	<template slot-scope="scope">
                <el-button size="small" style="color:blue; background-color:rgba(0,0,0,0); border:0px; text-decoration:underline;" @click="handleViewContractOne(scope.$index)">投融资协议</el-button></br>
                <el-button size="small" style="color:blue; background-color:rgba(0,0,0,0); border:0px; text-decoration:underline;" @click="handleViewContractTwe(scope.$index)">平台服务协议</el-button>
            </template>
        </el-table-column>
        
         <el-table-column label="操作" width="150">
            <template slot-scope="scope">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column> -->
    </el-table>
    <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="searchForm.totalRow">
        </el-pagination>
    </div>

    <el-dialog title="编辑客户投资表" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px">
        		<input v-model="editForm.id" type="hidden"></input>

    		    			    	            <el-form-item label="渠道id(如 大姨妈id )">
              <el-input v-model="editForm.channel_id" :disabled="!editForm.abled.channel_id"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="渠道编号(如 大姨妈代号 )">
              <el-input v-model="editForm.channel_no" :disabled="!editForm.abled.channel_no"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="客户编号">
              <el-input v-model="editForm.custom_no" :disabled="!editForm.abled.custom_no"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="客户身份证">
              <el-input v-model="editForm.custom_idcard" :disabled="!editForm.abled.custom_idcard"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="投资产品编号(晋商银行提供 )">
              <el-input v-model="editForm.invest_no" :disabled="!editForm.abled.invest_no"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="客户手机号">
              <el-input v-model="editForm.custom_mobile" :disabled="!editForm.abled.custom_mobile"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="银行编号(如 晋商银行代号)">
              <el-input v-model="editForm.bank_no" :disabled="!editForm.abled.bank_no"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="二类户银行卡号（电子账户）">
              <el-input v-model="editForm.eaccount_card" :disabled="!editForm.abled.eaccount_card"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="投资金额">
              <el-input v-model="editForm.invest_amt" :disabled="!editForm.abled.invest_amt" ></el-input>
            </el-form-item>
	    			    	            <el-form-item label="预期收益">
              <el-input v-model="editForm.expt_earn" :disabled="!editForm.abled.expt_earn"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="投资项目到期日">
              <el-input v-model="editForm.invest_enddate" :disabled="!editForm.abled.invest_enddate"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="起息日">
              <el-input v-model="editForm.invest_startinterestdate" :disabled="!editForm.abled.invest_startinterestdate"></el-input>
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
import { fmoney } from '@/utils/fmoney';
import { fdate } from '@/utils/fdate';
export default {
    data() {
            return {
                getdataurl: process.env.BASE_API + 'tCustomerInvest/getId.do',
                getbankurl:process.env.BASE_API + 'tCustomerInvest/getBanks.do',
                getchannelurl:process.env.BASE_API + 'tCustomerInvest/getChannels.do',
                dataurl: process.env.BASE_API + 'tCustomerInvest/dataList.do',
                saveurl: process.env.BASE_API + 'tCustomerInvest/save.do',
                deleteurl: process.env.BASE_API + 'tCustomerInvest/delete.do',
                dialogFormVisible: false,
                editLoading: false,
                expandAll:false,
                editForm: {
                        id:'',
    		    			    	channel_id: '',
	    			    				channel_no: '',
	    			    				custom_no: '',
	    			    				custom_idcard: '',
	    			    				invest_no: '',
	    			    				custom_mobile: '',
	    			    				bank_no: '',
	    			    				eaccount_card: '',
	    			    				invest_amt: '',
	    			    				expt_earn: '',
	    			    				invest_enddate: '',
	    			    				invest_startinterestdate: '',
	    			    				flag: '',
	    			    				createtime: '',
	    			    				updatetime: '',
	    		                    abled: {

    		    			    				channel_id: true,
	    			    				channel_no: true,
	    			    				custom_no: true,
	    			    				custom_idcard: true,
	    			    				invest_no: true,
	    			    				custom_mobile: true,
	    			    				bank_no: true,
	    			    				eaccount_card: true,
	    			    				invest_amt: true,
	    			    				expt_earn: true,
	    			    				invest_enddate: true,
	    			    				invest_startinterestdate: true,
	    		                    }
                },
                editForm_sysMenu_ids:[],
                searchForm: {
    		    			    	custome_invest_no: '',
	    			    				bank_no: '',
	    			    				custom_name: '',
	    			    				min_invest_amt: '',
	    			    				max_invest_amt: '',
	    			    				invest_name: '',
	    			    				channel_no: '',
	    			    				contract_no: '',
	    			    				min_createtime: '',
	    			    				max_createtime: '',
	    			    				flag: '',

                    pageNum: 1,
                    totalRow: 0,
                    pageSize: 10,
                    is_search: false,
                    order: '',
                },
                bank_selected:'',
                bank_options:[],
                channel_selected:'',
                channel_options:'',
                flag_options:[{
                  value:'1',
                  label:'成功'
                },{
                  value:'0',
                  label:'失败'
                },{
                  value:'2',
                  label:'处理中'
                }],
                tableData: [],
                aid:'',
                radio:'',
            }
        },
        created() {
            //if(sessionStorage.getItem("flag") == 0) {
        		//sessionStorage.setItem("flag", 1);
        		this.getChannelData();
            	this.getBankData();
            	this.loadData();
        	//}
        },
        computed: {
            data() {
                const self = this;
                return self.tableData;
            },
            banks(){
              let self = this;
              return self.bank_options;
            },
            channels(){
              let self = this;
              return self.channel_options;
            }
        },/*
    	 beforeRouteEnter(to, from, next) {
    	    if(sessionStorage.getItem("keepTCIflag") == 0) {
        		sessionStorage.setItem("keepTCIflag", 1);
        	     to.meta.keepAlive = true;
        	} else {
        		to.meta.keepAlive = true;
        		if ((from.path == '/Contract' && to.path == '/tCustomerInvest') || from.path == '/') {
        	     	to.meta.keepAlive = true;
        		} else {
        		}
        	}

        	next();
    	},
		beforeRouteLeave(to, from, next) {
		if(to.path == '/Contract') {
        	from.meta.keepAlive = true;
		} else {
		 	from.meta.keepAlive = false;
		}
        next();
    	},*/
        methods: {
                getChannelData() {
                  let self = this;
                  http.post(self.getchannelurl,{},(res) => {
                      self.channel_options = res.data.list;
                  }).catch(err => {
                      this.$eventHub.$emit('error', err);
                  })
                },
                getBankData() {
                  let self = this;
                  http.post(self.getbankurl,{},(res) => {
                      self.bank_options = res.data.list;
                  }).catch(err => {
                      this.$eventHub.$emit('error', err);
                  })
                },
                loadData() {
                    let self = this;
                    self.rowData='';
                    self.radio='';
                    http.post(self.dataurl, this.searchForm,(res) => {
                        self.tableData = res.data.list;
                        for (var i = 0; i < self.tableData.length; i++) {
                             console.log("BBBBBBBB=",self.tableData[0].createtime);
                             self.tableData[i].invest_amt = fmoney(self.tableData[i].invest_amt,2);
                             self.tableData[i].createtime = fdate(self.tableData[i].createtime);
                             if( self.tableData[i].invest_endDate!=null){
                              	self.tableData[i].invest_endDate = fdate(self.tableData[i].invest_endDate);
                             }
                            

                            let flag = self.tableData[i].flag;
                            if("0"==flag){
                              self.tableData[i].flag="失败";
                            }
                            if("1"==flag){
                              self.tableData[i].flag="成功";
                            }
                            if("2"==flag){
                              self.tableData[i].flag="处理中";
                            }
                        }
                        self.searchForm.totalRow = res.data.totalRow;
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
                handleReset(){
                  this.searchForm.is_search = false;
                  this.searchForm = {
                        custome_invest_no: '',
                        bank_no: '',
                        custom_name: '',
                        min_invest_amt: '',
                        max_invest_amt: '',
                        invest_name: '',
                        channel_no: '',
                        contract_no: '',
                        min_createtime: '',
                        max_createtime: '',
                        flag: '',


                    is_search: false,
                    order: '',
                  }
                },
                handleSort(val) {
                    //console.log(val);
                    this.searchForm.order = val.prop + " " + val.order.replace("ending", "");
                    this.handleSearch();
                    return false;
                },
                handleAdd() {
                    this.dialogFormVisible = true;
                    this.editForm = {
    		    			    	channel_id: '',
	    			    				channel_no: '',
	    			    				custom_no: '',
	    			    				custom_idcard: '',
	    			    				invest_no: '',
	    			    				custom_mobile: '',
	    			    				bank_no: '',
	    			    				eaccount_card: '',
	    			    				invest_amt: '',
	    			    				expt_earn: '',
	    			    				invest_enddate: '',
	    			    				invest_startinterestdate: '',
	    			    				flag: '',
	    			    				createtime: '',
	    			    				updatetime: '',
	    		                        abled: {
    		    			    				channel_id: true,
	    			    				channel_no: true,
	    			    				custom_no: true,
	    			    				custom_idcard: true,
	    			    				invest_no: true,
	    			    				custom_mobile: true,
	    			    				bank_no: true,
	    			    				eaccount_card: true,
	    			    				invest_amt: true,
	    			    				expt_earn: true,
	    			    				invest_enddate: true,
	    			    				invest_startinterestdate: true,
	    			    				flag: true,
	    			    				createtime: true,
	    			    				updatetime: true,
	    		                        }
                    };
                },
                handleEdit(index, row) {
                    let self = this;
                    self.dialogFormVisible = true;
                    self.editForm = Object.assign({}, row);
                    if (!self.editForm.abled) {
                        self.editForm.abled = {};
                    }
                    self.editForm.abled.rolecode = false;
                    http.post(self.getdataurl + '?id=' + self.editForm.id, {},response => {
    		    			    	self.editForm.channel_id=response.data.channel_id;
	    			    				self.editForm.channel_no=response.data.channel_no;
	    			    				self.editForm.custom_no=response.data.custom_no;
	    			    				self.editForm.custom_idcard=response.data.custom_idcard;
	    			    				self.editForm.invest_no=response.data.invest_no;
	    			    				self.editForm.custom_mobile=response.data.custom_mobile;
	    			    				self.editForm.bank_no=response.data.bank_no;
	    			    				self.editForm.eaccount_card=response.data.eaccount_card;
	    			    				self.editForm.invest_amt=response.data.invest_amt;
	    			    				self.editForm.expt_earn=response.data.expt_earn;
	    			    				self.editForm.invest_enddate=response.data.invest_enddate;
	    			    				self.editForm.invest_startinterestdate=response.data.invest_startinterestdate;
	    			    				self.editForm.flag=response.data.flag;
	    			    				self.editForm.createtime=response.data.createtime;
	    			    				self.editForm.updatetime=response.data.updatetime;
	    		                        return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })

                },
                handleDelete(index, row) {
                    const self = this;
                    //console.log(row);
                    http.post(self.deleteurl, {
                        id: row.id,
                    },response => {
                        self.loadData();
                        return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })
                },
                handleSave() {
                    const self = this;

                    http.post(self.saveurl, {
                				id:self.editForm.id,
                				channel_id:self.editForm.channel_id,
                				channel_no:self.editForm.channel_no,
                				custom_no:self.editForm.custom_no,
                				custom_idcard:self.editForm.custom_idcard,
                				invest_no:self.editForm.invest_no,
                				custom_mobile:self.editForm.custom_mobile,
                				bank_no:self.editForm.bank_no,
                				eaccount_card:self.editForm.eaccount_card,
                				invest_amt:self.editForm.invest_amt,
                				expt_earn:self.editForm.expt_earn,
                				invest_enddate:self.editForm.invest_enddate,
                				invest_startinterestdate:self.editForm.invest_startinterestdate,
                				flag:self.editForm.flag,
                				createtime:self.editForm.createtime,
                				updatetime:self.editForm.updatetime,
	                    },response => {
                        self.loadData();
                        self.dialogFormVisible = false;
                        return;
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
                  this.$refs.multipleTable.setCurrentRow(row);
                },
                handleLook(){
                  // const slef = this.handleCurrentSelected();
                  // console.log("aaaaaaaaaaaa=",self);
                },
                expandSearchForm() {
                    this.expandAll=true;
                },
                collapseSearchForm() {
                    this.expandAll=false;
                },
                handleViewContractOne(index){
    				this.$router.push({
    					path:'/Contract',
    					name:'Contract',
       			    	query: {
       			    	    path:"/tCustomerInvest",
          					title: "投融资协议",
          					titleOne: "订单管理",
          					titleTwe: "订单信息",
          					invest_amt:this.tableData[index].invest_amt.replace(",", ""),
          					invest_no:this.tableData[index].invest_no,
          					bank_no:this.tableData[index].bank_no,
          					custom_name:this.tableData[index].custom_name,
          					custom_no:this.tableData[index].custom_no,
          					type:"S",
        				},
     				});

     			},
                handleViewContractTwe(index){
					this.$router.push({
    					path:'/Contract',
    					name:'Contract',
       			    	query: {
       			    	    path:"/tCustomerInvest",
          					title: "平台服务协议",
          					titleOne: "订单管理",
          					titleTwe: "订单信息",
          					invest_amt:this.tableData[index].invest_amt.replace(",", ""),
          					invest_no:this.tableData[index].invest_no,
          					bank_no:this.tableData[index].bank_no,
          					custom_name:this.tableData[index].custom_name,
          					custom_no:this.tableData[index].custom_no,
          					type:"B",
        				},
     				});
     			},

        }
}

</script>
