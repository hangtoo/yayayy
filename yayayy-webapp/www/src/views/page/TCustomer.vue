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

	<!--
    <div class="handle-box">
    	<span>泛泰UID：</span><el-input v-model="searchForm.custom_no" class="handle-input mr10"></el-input>
    	<span>&#12288身份证号：</span> <el-input v-model="searchForm.custom_idcard" class="handle-input mr10"></el-input>
	    <span>&#12288姓名：</span> <el-input v-model="searchForm.custom_name"  class="handle-input mr10"></el-input>
	    <span>&#12288手机号：</span> <el-input v-model="searchForm.custom_mobile" type="number" min="0" max="99999999999" class="handle-input mr10"></el-input>
    </div>

    <div class="handle-box">

              <span>用户状态：</span>
              <el-select v-model="searchForm.flag"  placeholder="请选择">
                <el-option v-for="option in flag_options" :key="option.value" :value="option.value" :label="option.label">
                </el-option>
              </el-select>

               <span>&#12288风险测评等级：</span>
              <el-select v-model="searchForm.risk_lev"  placeholder="请选择">
                <el-option v-for="option in risk_options" :key="option.value" :value="option.value" :label="option.label">
                </el-option>
              </el-select>
     <span>&#12288&#12288</span>
     <el-button type="primary" icon="search" @click="handleSearch">搜索</el-button>
     <el-button type="primary" icon="search" @click="handleReset">重置</el-button>
    </div>
    -->

    <div class="handle-box">
    	<el-input v-model="searchForm.custom_no" placeholder="泛泰UID" class="handle-input mr10"></el-input>
    	<el-input v-model="searchForm.custom_idcard" placeholder="身份证号" class="handle-input mr10"></el-input>
	    <el-input v-model="searchForm.custom_name" placeholder="客户姓名"  class="handle-input mr10"></el-input>
	    <el-input v-model="searchForm.custom_mobile" placeholder="手机号"v-show="expandAll" type="number" min="0" max="99999999999" class="handle-input mr10"></el-input>
	   	<el-select v-model="searchForm.flag" clearable  placeholder="用户状态" v-show="expandAll">
         	<el-option v-for="option in flag_options" :key="option.value" :value="option.value" :label="option.label">
     		</el-option>
      	</el-select>
		<el-select v-model="searchForm.risk_lev" clearable  placeholder="风险测评等级" v-show="expandAll">
                <el-option v-for="option in risk_options" :key="option.value" :value="option.value" :label="option.label">
                </el-option>
     	</el-select>
     	<el-button-group>
	     	<el-button type="primary"  @click="handleSearch">搜索</el-button>
	     	<el-button type="primary"  @click="handleReset">重置</el-button>
     	</el-button-group>
     	<a style="margin-left: 8px;font-size:10px;color:#409EFF;" v-show="!expandAll"  @click="expandSearchForm">展开<i  class="el-icon el-icon-arrow-down" aria-hidden="true"></i></a>
    	<a style="margin-left: 8px;font-size:10px;color:#409EFF;" v-show="expandAll"  @click="collapseSearchForm">收回<i  class="el-icon el-icon-arrow-up" aria-hidden="true"></i></a>
    </div>


	<!--
    <div class="handle-box">
       <el-button size="primary" type="info" icon="el-icon-search"  @click="handleLookDetail">查看</el-button>
    </div>
    -->

    <el-table :data="data" border fit highlight-current-row style="width: 100%" ref="singleTable" @sort-change="handleSort"  @row-click="handleLookDetail">
 
 		<!--
 		<el-table-column type="index" label="序号" width="80">
    	  <template slot-scope="scope">
                  <el-radio :label="(scope.$index+1)" v-model="radio" @change.native="getCurrentRow(scope.$index+1,scope.row)"></el-radio>
          </template>
        </el-table-column>
        -->

        <el-table-column prop="custom_no" label="泛泰UID" min-width="150" align="center"  >
        	<!--
    	   <template slot-scope="scope">
           	   <el-button type="text" @click="handleLookDetail(scope.$index, scope.row)">{{scope.row.custom_no}}</el-button>
           </template>
             -->
        </el-table-column>


        <el-table-column prop="custom_name" label="客户姓名" min-width="95" align="center">
        </el-table-column>


        <el-table-column prop="custom_idcard" label="客户身份证" width="170" align="center">
        </el-table-column>

        <el-table-column prop="channel_info" label="流量渠道(UID)"  min-width="160">
        </el-table-column>


         <el-table-column prop="custom_mobile" label="客户手机号"min-width="120" align="center">
        </el-table-column>
         <el-table-column prop="flag" label="用户状态" min-width="130" align="center">
        </el-table-column>
         <el-table-column prop="risk_lev" label="风险测评等级"min-width="130" align="center">
        </el-table-column>

        <el-table-column prop="referrer_mobile" label="推荐人手机号"min-width="120" align="center">
        </el-table-column>



    </el-table>
    <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="searchForm.totalRow">
        </el-pagination>
    </div>

    <el-dialog title="编辑客户基本信息表" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px">
        		<input v-model="editForm.id" type="hidden"></input>

    		    			    	            <el-form-item label="客户编号(自己生成)">
              <el-input v-model="editForm.custom_no" :disabled="!editForm.abled.custom_no"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="客户手机号(填)">
              <el-input v-model="editForm.custom_mobile" :disabled="!editForm.abled.custom_mobile"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="推荐人">
              <el-input v-model="editForm.custom_referrer" :disabled="!editForm.abled.custom_referrer"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="客户姓名(填)">
              <el-input v-model="editForm.custom_name" :disabled="!editForm.abled.custom_name"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="客户身份证(填)">
              <el-input v-model="editForm.custom_idcard" :disabled="!editForm.abled.custom_idcard"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="客户职业(填)">
              <el-input v-model="editForm.custom_duty" :disabled="!editForm.abled.custom_duty"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="客户学历(填)">
              <el-input v-model="editForm.custom_education" :disabled="!editForm.abled.custom_education"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="客户身份证正面图片地址id（t_image表的id）(填)">
              <el-input v-model="editForm.custom_identityfront" :disabled="!editForm.abled.custom_identityfront"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="客户身份证反面图片地址id（t_image表的id）(填)">
              <el-input v-model="editForm.custom_identityback" :disabled="!editForm.abled.custom_identityback"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="创建时间">
              <el-input v-model="editForm.createtime" :disabled="!editForm.abled.createtime"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="更新时间">
              <el-input v-model="editForm.updatetime" :disabled="!editForm.abled.updatetime"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="handleSave" :loading="editLoading">保存</el-button>
                <el-button @click="dialogFormVisible = false">取消</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>

    <el-dialog title="客户信息" :visible.sync="dialogLookVisible" width="80%" >
    <div class="handle-box" v-loading="loading">

			<div class="handle-box" >
				<el-row :gutter="20">
					<el-col :span="10">
						<span>姓名： {{rowData.custom_name}}</span>
					</el-col>
					<el-col :span="10">
						<span>泛泰号： {{rowData.custom_no}}</span>
					</el-col>
				</el-row>
			</div>
			<div class="handle-box">
				<el-row :gutter="20">
					<el-col :span="10">
						<span>身份证号： {{rowData.custom_idcard}}</span>
					</el-col>
					<el-col :span="10">
						<span>手机号： {{rowData.custom_mobile}}</span>
					</el-col>
				</el-row>
			</div>
			<div class="handle-box">
				<el-row :gutter="20">
					<el-col :span="10">
						<span>职业： {{rowData.custom_duty}}</span>
					</el-col>
					<el-col :span="10">
						<span>学历： {{rowData.custom_education}}</span>
					</el-col>
				</el-row>
			</div>
			<div class="handle-box">
				<el-row :gutter="20">
					<el-col :span="10">
						<span>用户状态： {{rowData.flag}}</span>
					</el-col>
					<el-col :span="10">
						<span>风险测评等级： {{rowData.risk_lev}}</span>
					</el-col>
				</el-row>
			</div>
			<el-tabs  v-model="activeTabs" type="card" >
				<el-tab-pane label="历史订单" name="1">
					<el-table :data="investData">
                        <el-table-column property="custome_invest_no" label="订单号" min-width="170" align="center"></el-table-column>
                        <el-table-column property="invest_name" label="产品名" min-width="150" align="center"></el-table-column>
                        <el-table-column property="bank_name" label="银行" align="center" min-width="130"></el-table-column>
                        <el-table-column property="invest_tpye" label="种类" align="center"  width="70"></el-table-column>
                        <el-table-column property="invest_amt_string" label="认购份额（元）" width="140" align="center" ></el-table-column>
                        <el-table-column property="expt_earn_string" label="收益（元）" width="120" align="center"></el-table-column>
                        <el-table-column property="createtime" label="订单时间" align="center" min-width="110"></el-table-column>
                        <el-table-column property="ratestate" label="订单状态" align="center" width="100"></el-table-column>
              		</el-table>
				</el-tab-pane>
				<el-tab-pane label="账户信息" name="2">
					 <el-table :data="bankInfoData">
                        <el-table-column property="eaccount_deptname" label="二类户开户行" align="center" min-width="130"></el-table-column>
                        <el-table-column property="eaccount_card" label="二类户账户" align="center" min-width="150"></el-table-column>
                        <!--
                        <el-table-column property="" label="二类户协议" width="120" align="center">
        					<template slot-scope="scope">
                				<el-button size="small" style="color:blue; background-color:rgba(0,0,0,0); border:0px;" @click="handleViewContractOne()">查看协议</el-button>
            				</template>
        				</el-table-column>
        				-->
                        <el-table-column property="all_amt" label="总资产（元）"  width="140" align="center"></el-table-column>
                        <el-table-column property="unable_amt" label="余额（元）"   width="140" align="center"></el-table-column>
                        <el-table-column property="custom_payeebankname" label="绑定的一类户开户行"  width="170"align="center" ></el-table-column>
                        <el-table-column property="custom_acno" label="一类户账户" min-width="120"align="center"></el-table-column>
                        <el-table-column label="操作" width="80"  align="center" >
            				<template slot-scope="scope"   >
                				<el-button size="small" @click="handleMoney(scope.$index, scope.row)" >查看</el-button>
           					</template>
        				</el-table-column>
                  	</el-table>
				</el-tab-pane>
				<el-tab-pane label="充值提现记录"  name="3">
					  <el-table :data="transferData"  max-height="220">
                        <el-table-column property="mark" label="类型" align="center" min-width="70"></el-table-column>
                        <el-table-column property="id" label="流水号" align="center" min-width="100"></el-table-column>
                        <el-table-column property="trs_amount_string" label="金额"  width="120" align="center"></el-table-column>
                        <el-table-column property="createtime" label="时间"   width="170" align="center"></el-table-column>
                        <el-table-column property="custom_payeebankname" label="余额"  width="120"align="center" ></el-table-column>
                        <el-table-column property="eaccount_card" label="账户" min-width="120"align="center"></el-table-column>
                  	</el-table>
				</el-tab-pane>
				<el-tab-pane label="渠道来源"  name="4">
					<el-table :data="fromData">
                        <el-table-column property="channel_name" label="渠道名"min-width="200"></el-table-column>
                        <el-table-column property="channel_uid" label="渠道UID"min-width="200"></el-table-column>
                        <el-table-column property="channel_mobile" label="注册手机号"min-width="200"></el-table-column>
                  	</el-table>
				</el-tab-pane>
				<el-tab-pane label="推荐人信息" name="5">
					<div class="handle-box" v-show="!refFlag" >
						<span>无推荐人信息</span>
					</div>
					<div class="handle-box" v-show="refFlag">
						<el-row :gutter="20">
							<el-col :span="10">
								<span>姓名： {{referrerData.custom_name}}</span>
							</el-col>
							<el-col :span="10">
								<span>泛泰号： {{referrerData.custom_no}}</span>
							</el-col>
						</el-row>
					</div>
					<div class="handle-box" v-show="refFlag">
						<el-row :gutter="20">
							<el-col :span="10">
								<span>手机号： {{referrerData.custom_mobile}}</span>
							</el-col>
							<el-col :span="10">
								<span>邀请码： {{referrerData.referrer_no}}</span>
							</el-col>
						</el-row>
					</div>
				</el-tab-pane>
			</el-tabs>

			<!--
			<div class="grid-content bg-purple-color">
                  <span class="el-dialog__title">历史订单</span>
                  <div>
                    <el-table :data="investData">
                        <el-table-column property="custome_invest_no" label="订单号" min-width="170" align="center"></el-table-column>
                        <el-table-column property="invest_name" label="产品名" min-width="150" align="center"></el-table-column>
                        <el-table-column property="bank_name" label="银行" align="center" min-width="130"></el-table-column>
                        <el-table-column property="invest_tpye" label="种类" align="center"  width="70"></el-table-column>
                        <el-table-column property="invest_amt_string" label="认购份额（元）" width="140" align="center" ></el-table-column>
                        <el-table-column property="expt_earn_string" label="收益（元）" width="120" align="center"></el-table-column>
                        <el-table-column property="createtime" label="订单时间" align="center" min-width="110"></el-table-column>
                        <el-table-column property="ratestate" label="订单状态" align="center" width="100"></el-table-column>
                      </el-table>
                  </div>
           </div>
           <div class="handle-box">
           	<span>  </span>
           </div>
           <div class="grid-content bg-purple-color">
                  <span class="el-dialog__title">账户信息</span>
                  <div>
                    <el-table :data="bankInfoData">
                        <el-table-column property="eaccount_deptname" label="二类户开户行" align="center" min-width="130"></el-table-column>
                        <el-table-column property="eaccount_card" label="二类户账户" align="center" min-width="150"></el-table-column>
                        <el-table-column property="" label="二类户协议" width="120" align="center">
        					<template slot-scope="scope">
                				<el-button size="small" style="color:blue; background-color:rgba(0,0,0,0); border:0px;" @click="handleViewContractOne()">查看协议</el-button>
            				</template>
        				</el-table-column>
                        <el-table-column property="all_amt" label="总资产（元）"  width="140" align="center"></el-table-column>
                        <el-table-column property="unable_amt" label="余额（元）"   width="140" align="center"></el-table-column>
                        <el-table-column property="custom_payeebankname" label="绑定的一类户开户行"  width="170"align="center" ></el-table-column>
                        <el-table-column property="custom_acno" label="一类户账户" min-width="120"align="center"></el-table-column>
                        <el-table-column label="操作" width="80"  align="center" >
            				<template slot-scope="scope"   >
                				<el-button size="small" @click="handleMoney(scope.$index, scope.row)" >查看</el-button>
           					</template>
        				</el-table-column>
                  	</el-table>
                  </div>
          </div>

           <div class="handle-box">
           	<span>  </span>
           </div>
           <div class="grid-content bg-purple-color">
                  <span class="el-dialog__title">充值提现记录</span>
                  <div>
                    <el-table :data="transferData"  max-height="220">
                        <el-table-column property="mark" label="类型" align="center" min-width="70"></el-table-column>
                        <el-table-column property="id" label="流水号" align="center" min-width="100"></el-table-column>
                        <el-table-column property="trs_amount_string" label="金额"  width="120" align="center"></el-table-column>
                        <el-table-column property="createtime" label="时间"   width="170" align="center"></el-table-column>
                        <el-table-column property="custom_payeebankname" label="余额"  width="120"align="center" ></el-table-column>
                        <el-table-column property="eaccount_card" label="账户" min-width="120"align="center"></el-table-column>
                  	</el-table>
                  </div>
          </div>


           <div class="handle-box">
           	<span>  </span>
           </div>
           <div class="grid-content bg-purple-color">
                  <span class="el-dialog__title">渠道来源</span>
                  <div>
                    <el-table :data="fromData">
                        <el-table-column property="channel_name" label="渠道名"min-width="200"></el-table-column>
                        <el-table-column property="channel_uid" label="渠道UID"min-width="200"></el-table-column>
                        <el-table-column property="channel_mobile" label="注册手机号"min-width="200"></el-table-column>
                      </el-table>
                  </div>
          </div>

		   <div class="handle-box">
           	<span>  </span>
           </div>
			<div class="grid-content bg-purple-color">
				<span class="el-dialog__title">推荐人信息</span>
				<div class="handle-box">
           			<span>  </span>
           		</div>

           		<div class="handle-box" v-show="!refFlag" >
					<span>无推荐人信息</span>
				</div>
				<div class="handle-box" v-show="refFlag">
					<el-row :gutter="20">
						<el-col :span="10">
							<span>姓名： {{referrerData.custom_name}}</span>
						</el-col>
						<el-col :span="10">
							<span>泛泰号： {{referrerData.custom_no}}</span>
						</el-col>
					</el-row>
				</div>
				<div class="handle-box" v-show="refFlag">
					<el-row :gutter="20">
						<el-col :span="10">
							<span>手机号： {{referrerData.custom_mobile}}</span>
						</el-col>
						<el-col :span="10">
							<span>邀请码： {{referrerData.referrer_no}}</span>
						</el-col>
					</el-row>
				</div>
          </div>
		-->





		</div>

	</el-dialog>
</div>

</template>

<script>
import http from '@/utils/http';
import { fdate } from '@/utils/fdate';
export default {
    data() {
            return {
                getdataurl: process.env.BASE_API + 'tCustomer/getId.do',
                dataurl: process.env.BASE_API + 'tCustomer/dataList.do',
                saveurl: process.env.BASE_API + 'tCustomer/save.do',
                deleteurl: process.env.BASE_API + 'tCustomer/delete.do',
                detailurl: process.env.BASE_API + 'tCustomer/customerdetail.do',
                moneyurl: process.env.BASE_API + 'tCustomer/customermoney.do',
                dialogFormVisible: false,
                dialogLookVisible: false,
                editLoading: false,
                refFlag: true,
                expandAll:false,
                editForm: {
    		    			    	    custom_no: '',
	    			    				custom_mobile: '',
	    			    				custom_referrer: '',
	    			    				custom_name: '',
	    			    				custom_idcard: '',
	    			    				custom_duty: '',
	    			    				custom_education: '',
	    			    				custom_identityfront: '',
	    			    				custom_identityback: '',
	    			    				createtime: '',
	    			    				updatetime: '',
	    		                    abled: {

    		    			    				custom_no: true,
	    			    				custom_mobile: true,
	    			    				custom_referrer: true,
	    			    				custom_name: true,
	    			    				custom_idcard: true,
	    			    				custom_duty: true,
	    			    				custom_education: true,
	    			    				custom_identityfront: true,
	    			    				custom_identityback: true,
	    			    				createtime: true,
	    			    				updatetime: true,
	    		                    }
                },
                editForm_sysMenu_ids:[],
                searchForm: {
    		    			    		custom_no: '',
	    			    				custom_mobile: '',
	    			    				custom_referrer: '',
	    			    				custom_name: '',
	    			    				custom_idcard: '',
	    			    				custom_duty: '',
	    			    				custom_education: '',
	    			    				custom_identityfront: '',
	    			    				custom_identityback: '',
	    			    				createtime: '',
	    			    				updatetime: '',
	    			    				flag: '',
	    			    				risk_lev: '',

                    pageNum: 1,
                    totalRow: 0,
                    pageSize: 10,
                    is_search: false,
                    order: '',
                },
                tableData: [],
                investData: [],
                bankInfoData: [],
                transferData: [],
                referrerData: [],
                fromData:'',
                radio:'',
                rowData:'',
                activeTabs:'1',
                loading: false,
                flag_options:[{
                  value:'0',
                  label:'未开户'
                },{
                  value:'1',
                  label:'已开户未购买'
                },{
                  value:'2',
                  label:'已开户已购买'
                }],
                risk_options:[{
                  value:'1',
                  label:'保守型'
                },{
                  value:'2',
                  label:'谨慎型'
                },{
                  value:'3',
                  label:'稳健型'
                },{
                  value:'4',
                  label:'积极型'
                },{
                  value:'5',
                  label:'激进型'
                }],
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
        },/*
    	 beforeRouteEnter(to, from, next) {
        	 if(sessionStorage.getItem("keepTCflag") == 0) {
        		sessionStorage.setItem("keepTCflag", 1);
        	     to.meta.keepAlive = true;
        	} else {
        	    to.meta.keepAlive = true;

        		if ((from.path == '/Contract' && to.path == '/tCustomer') || from.path == '/') {
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
                loadData() {
                    let self = this;
                    self.rowData='';
                    self.radio='';
                    http.post(self.dataurl, this.searchForm,(res) => {
                        console.log(res.data.list);
                        self.tableData = res.data.list;
                        self.searchForm.totalRow = res.data.totalRow;
                        this.searchForm.is_search = false;
                         for (var i = 0; i < self.tableData.length; i++) {
                      	  let flag = self.tableData[i].flag;
                            if("0"==flag){
                              self.tableData[i].flag="未开户";
                            }
                            if("1"==flag){
                              self.tableData[i].flag="已开户未购买";
                            }
                            if("2"==flag){
                              self.tableData[i].flag="已开户已购买";
                            }

                          let risk_lev = self.tableData[i].risk_lev;
                            if("0"==risk_lev){
                              self.tableData[i].risk_lev="";
                            }
                            if("1"==risk_lev){
                              self.tableData[i].risk_lev="保守型";
                            }
                            if("2"==risk_lev){
                              self.tableData[i].risk_lev="谨慎型";
                            }
                            if("3"==risk_lev){
                              self.tableData[i].risk_lev="稳健型";
                            }
                            if("4"==risk_lev){
                              self.tableData[i].risk_lev="积极型";
                            }
                            if("5"==risk_lev){
                              self.tableData[i].risk_lev="激进型";
                            }

                            let custom_education = self.tableData[i].custom_education;
                            if("0"==custom_education){
                              self.tableData[i].custom_education="";
                            }
                            if("10"==custom_education){
                              self.tableData[i].custom_education="研究生";
                            }
                            if("20"==custom_education){
                              self.tableData[i].custom_education="大学本科";
                            }
                            if("30"==custom_education){
                              self.tableData[i].custom_education="大学专科或专科学校";
                            }
                            if("40"==custom_education){
                              self.tableData[i].custom_education="中等专业学校或中等技术学校";
                            }
                            if("50"==custom_education){
                              self.tableData[i].custom_education="技术学校";
                            }
                            if("60"==custom_education){
                              self.tableData[i].custom_education="高中";
                            }
                            if("70"==custom_education){
                              self.tableData[i].custom_education="初中";
                            }
                            if("80"==custom_education){
                              self.tableData[i].custom_education="小学";
                            }
                            if("90"==custom_education){
                              self.tableData[i].custom_education="文盲或半文盲";
                            }
                            if("99"==custom_education){
                              self.tableData[i].custom_education="未知";
                            }

                            let custom_duty = self.tableData[i].custom_duty;
                            if("0"==custom_duty){
                              self.tableData[i].custom_duty="国家机关、社会组织、企事业单位负责人";
                            }
                            if("1"==custom_duty){
                              self.tableData[i].custom_duty="科、教、工、贸等专业技术人员";
                            }
                            if("2"==custom_duty){
                              self.tableData[i].custom_duty="批发、零售业服务人员";
                            }
                            if("3"==custom_duty){
                              self.tableData[i].custom_duty="交通运输、仓储、邮政业服务人员";
                            }
                            if("4"==custom_duty){
                              self.tableData[i].custom_duty="信息传输、软件和信息技术服务人员";
                            }
                            if("5"==custom_duty){
                              self.tableData[i].custom_duty="水利、环境和公共设施管理服务人员";
                            }
                            if("6"==custom_duty){
                              self.tableData[i].custom_duty="电力、燃气及水供应服务人员";
                            }
                            if("7"==custom_duty){
                              self.tableData[i].custom_duty="文化、体育和娱乐服务人员";
                            }
                            if("8"==custom_duty){
                              self.tableData[i].custom_duty="旅游、餐饮、住宿服务人员";
                            }
                            if("9"==custom_duty){
                              self.tableData[i].custom_duty="金融服务人员";
                            }
                            if("10"==custom_duty){
                              self.tableData[i].custom_duty="房地产、物业、建筑、装修服务人员";
                            }
                            if("11"==custom_duty){
                              self.tableData[i].custom_duty="居民生活服务人员";
                            }
                            if("12"==custom_duty){
                              self.tableData[i].custom_duty="军人、警察、安全、消防人员";
                            }
                            if("13"==custom_duty){
                              self.tableData[i].custom_duty="农林牧渔业生产及辅助人员";
                            }
                            if("14"==custom_duty){
                              self.tableData[i].custom_duty="学生";
                            }
                            if("15"==custom_duty){
                              self.tableData[i].custom_duty="退休人员";
                            }
                            if("16"==custom_duty){
                              self.tableData[i].custom_duty="不便分类劳动者";
                            }
                        }


                    }).catch(err => {
                        this.$eventHub.$emit('error', err);
                    })
                },
                handleLookDetail(row, event, column){
                	const self = this;
                	 console.log(row);
                	 
                	self.loading = false;
                	this.rowData = row;
	                	http.post(self.detailurl, {
	    				    custom_no:row.custom_no,
	                    },response => {

	                    	self.investData=response.data.tcientity;
	                    	self.bankInfoData=response.data.tcbientity;
	                    	self.fromData=response.data.tcfentity;
	                    	self.transferData=response.data.ttentity;
	                    	self.referrerData=response.data.referrer;

	                    	if(self.referrerData.custom_name==null){
	                    		self.refFlag=false;
	                    	}else{
	                    		self.refFlag=true;
	                    	}

	                    	for (var i = 0; i < self.investData.length; i++) {
                      	 		let ratestate = self.investData[i].ratestate;
                            	if("1"==ratestate){
                            		self.investData[i].ratestate="计息中";
                            	}
                            	if("0"==ratestate){
                            		self.investData[i].ratestate="已赎回";
                            	}
                            	if( self.investData[i].createtime!=null){
                          			self.investData[i].createtime = fdate(self.investData[i].createtime);
                        		}
                            }

                            for (var i = 0; i < self.transferData.length; i++) {
                      	 		let mark = self.transferData[i].mark;
                            	if("1"==mark){
                            		self.transferData[i].mark="提现";
                            	}
                            	if("0"==mark){
                            		self.transferData[i].mark="充值";
                            	}
                            	if( self.transferData[i].createtime!=null){
                          			self.transferData[i].createtime = fdate(self.transferData[i].createtime);
                        		}
                            }

	                      console.log("response=",response);
	                      console.log("investData=",self.investData);
	                      console.log("bankInfoData=",self.bankInfoData);
	                      console.log("transferData=",self.transferData);
	                      console.log("fromData=",self.fromData);

                       self.dialogLookVisible=true;

                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
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
                expandSearchForm() {
                    this.expandAll=true;
                },
                collapseSearchForm() {
                    this.expandAll=false;
                },
                handleAdd() {
                    this.dialogFormVisible = true;
                    this.editForm = {
    		    			    		custom_no: '',
	    			    				custom_mobile: '',
	    			    				custom_referrer: '',
	    			    				custom_name: '',
	    			    				custom_idcard: '',
	    			    				custom_duty: '',
	    			    				custom_education: '',
	    			    				custom_identityfront: '',
	    			    				custom_identityback: '',
	    			    				createtime: '',
	    			    				updatetime: '',
	    		                        abled: {
    		    			    				custom_no: true,
	    			    				custom_mobile: true,
	    			    				custom_referrer: true,
	    			    				custom_name: true,
	    			    				custom_idcard: true,
	    			    				custom_duty: true,
	    			    				custom_education: true,
	    			    				custom_identityfront: true,
	    			    				custom_identityback: true,
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
    		    			    		self.editForm.custom_no=response.data.custom_no;
	    			    				self.editForm.custom_mobile=response.data.custom_mobile;
	    			    				self.editForm.custom_referrer=response.data.custom_referrer;
	    			    				self.editForm.custom_name=response.data.custom_name;
	    			    				self.editForm.custom_idcard=response.data.custom_idcard;
	    			    				self.editForm.custom_duty=response.data.custom_duty;
	    			    				self.editForm.custom_education=response.data.custom_education;
	    			    				self.editForm.custom_identityfront=response.data.custom_identityfront;
	    			    				self.editForm.custom_identityback=response.data.custom_identityback;
	    			    				self.editForm.createtime=response.data.createtime;
	    			    				self.editForm.updatetime=response.data.updatetime;
	    		                        return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })

                },
                handleMoney(index, row) {
                    const self = this;
                    self.loading = true;
                    http.post(self.moneyurl, row,response => {

                    	self.loading = false;
                   		if(response.status==200&&!!response.data&&response.data.statusCode==200){
	                		self.bankInfoData[index].unable_amt=response.data.unable_amt;
                        	self.bankInfoData[index].all_amt=response.data.all_amt;
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
                handleSave() {
                    const self = this;

                    http.post(self.saveurl, {
    					id:self.editForm.id,
						custom_no:self.editForm.custom_no,
						custom_mobile:self.editForm.custom_mobile,
						custom_referrer:self.editForm.custom_referrer,
						custom_name:self.editForm.custom_name,
						custom_idcard:self.editForm.custom_idcard,
						custom_duty:self.editForm.custom_duty,
						custom_education:self.editForm.custom_education,
						custom_identityfront:self.editForm.custom_identityfront,
						custom_identityback:self.editForm.custom_identityback,
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
                  this.$refs.singleTable.setCurrentRow(row);
                },
                handleReset(){
                  this.searchForm.is_search = false;
                  this.searchForm = {
      		    		custom_mobile: '',
  	    			    custom_name: '',
  	    			    custom_idcard: '',
  	    			    custom_no: '',
  	    			    flag: '',
  	    			    risk_lev: '',



                      is_search: false,
                      order: '',
                  }
                },
                handleViewContractOne(){
                	this.dialogLookVisible =false;
    				this.$router.push({
    					path:'/Contract',
    					name:'Contract',
       			    	query: {
       			    	    path:"/tCustomer",
          					title: "二类户开户协议",
          					titleOne: "用户管理",
          					titleTwe: "用户信息",
          					custom_name:this.rowData.custom_name,
          					custom_no:this.rowData.custom_no,
          					type:"E",
        				},
     				});

     			},
        }
}

</script>
