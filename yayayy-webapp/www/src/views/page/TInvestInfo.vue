<style scoped>

.handle-span {
  padding-left: 20px;
}

.handle-dialog-span {
  margin-bottom: 20px;
}

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

.handle-select {
    width: 150px;
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

  .bg-purple-color {
    background: #f6f6f6;
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

  .handle-button_div {
      margin-bottom: 10px;
  }


  .time {
		width: 90%;
		height:70px;
		padding: 20px 0;
		margin-top: -11px;
		background-color: #fff;
	}
	.time-top,.time-bottom,.time-middle {
		width:100%;
		font-size: 0;
	}
	.time-top,.time-bottom{
		height:24px;
	}
	.time-detail{
		width:33.33%;
		display: inline-block;
		font-size: 19px;
		color:#666;
		font-family: "PingFang SC";
		text-align: center;
	}
  .time-detail-active{
    color:#ddd !important;
  }
	.time-middle{
		height: 20px;
		line-height: 20px;
		text-align: center;
		margin: 14px 0;
	}
	.time-middle-circle {
		display: inline-block;
		width: 18px;
		height: 18px;
		border-radius: 9px;
		border: 2px solid #ddd;
		vertical-align: middle;
	}
	.time-top-end {
		color: #ff6680;
	}
	.time-middle-line {
		display: inline-block;
		width: 28%;
		margin: 0 16px;
		height:1px;
		border:1px solid #ddd;
	}
	.timeout {
		width:100%;
		height:90px;
		line-height: 125px;
		text-align: center;
		font-size: 22px;
		font-family: "PingFang SC";
	}

  .project-message{
    width:100%;
    height:auto;
    padding: 10px 10px 0 10px;
  }
  .project-message ul{
    width:100%;
    height:auto;
    list-style: none;
  }
  .project-message ul li{
    width:100%;
    height:70px;
    line-height: 70px;
    background-color: #fff;
  }
  .project-message ul li span{
    display: inline-block;
    width:49%;
    text-align: center;
    font-family: "PingFang SC";
    font-size: 16px;
    color:#9e9e9e;
    border-bottom:0.03rem solid #e0e0e0;
    border-right:0.03rem solid #e0e0e0;
  }
  .project-message ul li span img{
    width:30px;
    height:30px;
  }

  .borderTopLeft{
    border-top:0.03rem solid #e0e0e0;
    border-left:0.03rem solid #e0e0e0;
  }
  .borderTop{
    border-top:0.03rem solid #e0e0e0;
  }

</style>

<template>
<div class="app-container calendar-list-container">
	<div class="handle-box">
		<el-input v-model="searchForm.invest_no" placeholder="产品编号" class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.project_name" placeholder="产品名称" class="handle-input mr10"></el-input>
    	<el-select v-model="searchForm.bank_no" clearable class="handle-select mr10" placeholder="银行">
       		<el-option v-for="option in bank_options" :key="option.bank_no" :value="option.bank_no" :label="option.bank_name"></el-option>
      	</el-select>
      	<el-select v-model="searchForm.status" clearable class="handle-select mr10" placeholder="状态">
    		<el-option v-for="option in status_options" :key="option.bank_no" :value="option.value" :label="option.label"></el-option>
     	</el-select>
     	<el-button-group>
	     	<el-button type="primary"  @click="handleSearch">搜索</el-button>
	        <el-button type="primary"  @click="handleReset">重置</el-button>
        </el-button-group>
          <!-- <el-col :span="6">
            <div class="grid-content">
              <span>期限&#12288&#12288：</span>
              <el-select v-model="searchForm.invest_limit" class="handle-select mr10" placeholder="请选择">
                <el-option v-for="option in invest_limit_options" :key="option.value" :value="option.value" :label="option.label">
                </el-option>
              </el-select>
            </div>
          </el-col>  -->
         <!-- <el-col :span="7">
            <div class="grid-content">
              <span>利率(%)&#12288:</span>
              <el-input v-model="searchForm.min_expect_yield" placeholder="利率" class="handle-input mr10" type="number" min="0"></el-input>
              -
              <el-input v-model="searchForm.max_expect_yield" placeholder="利率" class="handle-input mr10" type="number" min="0"></el-input>
            </div>
          </el-col> -->
    </div>
    <!--
   <div class="handle-box">
       <el-button size="primary" type="info" icon="el-icon-search"  @click="handleLook">查看</el-button>
    </div>
    -->
    <el-table :data="data" border fit highlight-current-row style="width: 100%" ref="singleTable" @sort-change="handleSort"  @row-click="handleLook">
   		 <!--
        <el-table-column type="index" label="序号" width="80">
          <template slot-scope="scope">
                  <el-radio :label="(scope.$index+1)" v-model="radio" @change.native="getCurrentRow(scope.$index+1,scope.row)"></el-radio>
          </template>
        </el-table-column>
 		-->
        <el-table-column prop="invest_no" label="产品编号" width="120" align="center">
        </el-table-column>

        <el-table-column prop="project_name" label="产品名称" min-width="150" align="center">
        </el-table-column>

        <el-table-column prop="bank_name" label="银行" min-width="100" align="center">
        </el-table-column>

        <el-table-column prop="invest_kind" label="种类" width="80" align="center">
        </el-table-column>

        <el-table-column prop="invest_limit" label="期限(天)"  width="100" align="center">
        </el-table-column>

        <el-table-column prop="expect_yield" label="利率(%)" width="100" align="center">
        </el-table-column>

        <el-table-column prop="min_invest_amt" label="起投金额(元)" width="130" align="center">
        </el-table-column>

        <el-table-column prop="project_scale" label="投资规模(元)" width="150" align="center">
        </el-table-column>

        <el-table-column prop="invest_progress" label="融资进度(%)" width="120" align="center">
        </el-table-column>

        <el-table-column prop="my_invest_progress" label="通过我司渠道融资的进度(%)" width="220" align="center">
        </el-table-column>

        <el-table-column prop="status" label="状态" align="center">
        </el-table-column>
    </el-table>
    <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="searchForm.totalRow">
        </el-pagination>
    </div>

    <el-dialog title="产品基本信息" :visible.sync="dialogFormVisible" width="80%">
            <el-row :gutter="20">
              <el-col :span="8">
                <!-- <div class="grid-content bg-purple"></div> -->
                <label>产品名称:</label><span class="handle-span">{{investInfoData.project_name}}</span>
              </el-col>
              <el-col :span="7">
                <!-- <div class="grid-content bg-purple"></div> -->
                <label>产品编号:</label><span class="handle-span">{{investInfoData.invest_no}}</span>
              </el-col>
              <el-col :span="8">
                <!-- <div class="grid-content bg-purple"></div> -->
                <label>所属银行:</label><span class="handle-span">{{investInfoData.bank_name}}</span>
              </el-col>
            </el-row>

            <el-row :gutter="20">
              <el-col :span="8">
                <!-- <div class="grid-content bg-purple"></div> -->
                <label>种类&#12288&#12288:</label><span class="handle-span">{{investInfoData.invest_kind}}</span>
              </el-col>
              <el-col :span="7">
                <!-- <div class="grid-content bg-purple"></div> -->
                <label>期限&#12288&#12288:</label><span class="handle-span">{{investInfoData.invest_limit}}天</span>
              </el-col>
              <el-col :span="8">
                <!-- <div class="grid-content bg-purple"></div> -->
                <label>利率&#12288&#12288:</label><span class="handle-span">{{investInfoData.expect_yield}}%</span>
              </el-col>
            </el-row>

            <el-row :gutter="20">
              <el-col :span="8">
                <label>起投金额:</label><span class="handle-span">{{investInfoData.min_invest_amt}}元</span>
              </el-col>
              <el-col :span="7">
                <label>投资规模:</label><span class="handle-span">{{investInfoData.project_scale}}元</span>
              </el-col>
              <el-col :span="8">
                <label>最小追加金额:</label><span class="handle-span">{{investInfoData.each_amt}}元</span>
              </el-col>
            </el-row>

            <el-row :gutter="20">
              <el-col :span="8">
                <label>融资进度:</label><span class="handle-span">{{investInfoData.invest_progress}}%</span>
              </el-col>
              <el-col :span="7">
                <label>剩余额度:</label><span class="handle-span">{{investInfoData.remain_amt}}元</span>
              </el-col>
              <el-col :span="8">
                <label>通过我司渠道融资的进度:</label><span class="handle-span">{{investInfoData.my_invest_progress}}%</span>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24"><div class="grid-content">
                <div class="time">
                  <div class="time-top">
                    <span class="time-detail">{{ investInfoData.invest_startInterestDate}}</span>
                    <span class="time-detail">{{ investInfoData.invest_endDate}}</span>
                    <span class="time-detail">到期日+3个工作日</span>
                  </div>
                  <div class="time-middle">
                      <span class="time-middle-circle"></span>
                      <span class="time-middle-line"></span>
                      <span class="time-middle-circle"></span>
                      <span class="time-middle-line"></span>
                      <span class="time-middle-circle"></span>
                  </div>
                  <div class="time-bottom">
                    <span class="time-detail">起息日</span>
                    <span class="time-detail">到期日</span>
                    <span class="time-detail">本息结账日</span>
                  </div>
                </div>
              </div>
              
              <div class="timeout">
          			距募集结束：<count-down :endTime="raise_date"  endText="已经结束了"></count-down>
          		</div>
            </el-col>
           </el-row>

            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple-color">
                  <span class="el-dialog__title">借款用途</span>
                  <div>{{investInfoData.purpose}}</div>
                </div>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple-color">
                  <span class="el-dialog__title handle-dialog-span">投资须知信息</span>
                  <div>
                  			<span>
                  			&nbsp;&nbsp;&nbsp;1、晋商银行为项目见证机构,已对融资方在投融资平台上的披露信息进行表面真实性审核；项目委托晋商银行进行资金监管及本金、利息清算。
                      </span><br>
            						<span>
            							&nbsp;&nbsp;&nbsp;2、在进行本融资项目投资前,投资人应确保自身属于符合法律法规规定可以进行投资的合规投资人,并完全理解本项目的性质和风险。
            						</span><br>
            						<span>
            							&nbsp;&nbsp;&nbsp;3、除本融资项目说明中明确规定的收益及收益分配方式外,任何预期收益、预计收益、测算收益或类似表述均属不具有法律约束力的用语,不代表投资人可能获得的实际收益,亦不构成投融资平台和项目见证机构对本项目的任何收益承诺。客户所能获得的最终收益以本项目说明所规定形式实际支付的款项为准,且不超过根据项目说明预期投资收益率计算所得的金额。
            						</span><br>
            						<span>
            							&nbsp;&nbsp;&nbsp;4、在本融资项目存续期内,如因国家法律法规、监管规定发生变化,在最大可能不损害投资人利益的前提下,投融资平台有权单方对本项目说明进行修订。投融资平台决定对项目说明进行修订的,将通知投资人。
            						</span><br>
            						<span>
            							&nbsp;&nbsp;&nbsp;5、投融资平台有权依法对本项目说明进行解释。投资人若对本融资项目有任何疑问,可向项目见证机构咨询。
            						</span><br>
			   	          </div>
                </div>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple-color">
                  <span class="el-dialog__title">项目见证信息</span>
                  <div class="project-message">
                    <ul>
                      <li>
                        <span class="borderTopLeft">银行见证内容</span><span class="borderTop">是否已见证</span>
                      </li>
                      <li v-for="winteness_info in winteness_infos">
                        <span>{{winteness_info}}</span><span><img src="../../assets/dui_icon.png" alt=""></span>
                      </li>
                    </ul>
                  </div>
                </div>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple-color">
                  <span class="el-dialog__title">投资人列表</span>
                  <div>
                    <el-table :data="investInfoData.tCustomerInvests">
                        <el-table-column type="index" label="序号" width="80"></el-table-column>
                        <el-table-column property="custom_name" label="投资人"></el-table-column>
                        <el-table-column property="custom_mobile" label="手机号"></el-table-column>
                        <el-table-column property="invest_amt" label="出资金额（元）"></el-table-column>
                        <el-table-column property="updatetime" label="出资时间" width="200"></el-table-column>
                      </el-table>
                  </div>
                </div>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple-color">
                  <span class="el-dialog__title">涉及的协议和合同</span>
                  <div>暂无数据</div>
                </div>
              </el-col>
            </el-row>
    </el-dialog>

</div>

</template>

<script>
import http from '@/utils/http';
import countDown from '../../components/common/countDown.vue';
import { fmoney } from '@/utils/fmoney';
export default {
    data() {
            return {
                getdataurl: process.env.BASE_API + 'tInvestInfo/getId.do',
                getbankurl:process.env.BASE_API + 'tInvestInfo/getBanks.do',
                dataurl: process.env.BASE_API + 'tInvestInfo/dataList.do',
                saveurl: process.env.BASE_API + 'tInvestInfo/save.do',
                deleteurl: process.env.BASE_API + 'tInvestInfo/delete.do',
                lookurl: process.env.BASE_API + 'tInvestInfo/look.do',
                dialogFormVisible: false,
                editLoading: false,
                editForm: {
    		    			    				bank_id: '',
	    			    				bank_no: '',
	    			    				invest_no: '',
	    			    				project_name: '',
	    			    				invest_risk_level: '',
	    			    				expect_yield: '',
	    			    				invest_progress: '',
	    			    				project_scale: '',
	    			    				min_invest_amt: '',
	    			    				max_invest_amt: '',
	    			    				remain_amt: '',
	    			    				each_amt: '',
	    			    				invest_limit: '',
	    			    				begin_date: '',
	    			    				invest_enddate: '',
	    			    				invest_startinterestdate: '',
	    			    				prd_type: '',
	    			    				status: '',
	    			    				pay_way: '',
	    			    				purpose: '',
	    			    				createtime: '',
	    			    				updatetime: '',
	    		              abled: {

            		    			    	bank_id: true,
        	    			    				bank_no: true,
        	    			    				invest_no: true,
        	    			    				project_name: true,
        	    			    				invest_risk_level: true,
        	    			    				expect_yield: true,
        	    			    				invest_progress: true,
        	    			    				project_scale: true,
        	    			    				min_invest_amt: true,
        	    			    				max_invest_amt: true,
        	    			    				remain_amt: true,
        	    			    				each_amt: true,
        	    			    				invest_limit: true,
        	    			    				begin_date: true,
        	    			    				invest_enddate: true,
        	    			    				invest_startinterestdate: true,
        	    			    				prd_type: true,
        	    			    				status: true,
        	    			    				pay_way: true,
        	    			    				purpose: true,
        	    			    				createtime: true,
        	    			    				updatetime: true,
	    		              }
                },
                editForm_sysMenu_ids:[],
                searchForm: {
	    			    				bank_no: '',
	    			    				invest_no: '',
	    			    				project_name: '',
	    			    				expect_yield: '',
                        min_expect_yield:'',
                        max_expect_yield:'',
	    			    				status: '',

                    pageNum: 1,
                    totalRow: 0,
                    pageSize: 10,
                    is_search: false,
                    order: '',
                },
                radio:'',
                rowData:'',
                investInfoData:'',
                tableData: [],
                bank_options:[],
                winteness_infos:[],
                raise_date:'',
                status_options:[{
                  value:'1',
                  label:'在售中'
                },{
                  value:'5',
                  label:'已售罄'
                },{
                  value:'7',
                  label:'募集结束'
                },{
                  value:'3',
                  label:'预售中'
                }],
                invest_limit_options:[{
                  value:'',
                  label:'全部'
                },{
                  value:'30',
                  label:'30天'
                },{
                  value:'60',
                  label:'60天'
                },{
                  value:'90',
                  label:'90天'
                },{
                  value:'180',
                  label:'180天'
                },{
                  value:'365',
                  label:'365天'
                }],
                invest_kind_options:[{
                  value:'',
                  label:'全部'
                },{
                  value:'1',
                  label:'定期'
                },{
                  value:'0',
                  label:'基金'
                }],
            }
        },
        created() {
            this.getBankData();
            this.loadData();
        },
        components : { countDown},
        computed: {
            data() {
                const self = this;
                return self.tableData;
            },
            banks(){
              let self = this;
              return self.bank_options;
            },
        },
        methods: {
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
                            self.tableData[i].min_invest_amt = fmoney(self.tableData[i].min_invest_amt,2);
                            self.tableData[i].project_scale = fmoney(self.tableData[i].project_scale,2);

                            self.tableData[i].invest_progress=fmoney(self.tableData[i].invest_progress*100,1);
                            self.tableData[i].my_invest_progress=fmoney(self.tableData[i].my_invest_progress*100,1);
                            self.tableData[i].expect_yield=fmoney(self.tableData[i].expect_yield*100,1);

                            let status = self.tableData[i].status;
                            if("A"==status||"1"==status){
                              self.tableData[i].status="在售中";
                            }
                            if("B"==status||"5"==status){
                              self.tableData[i].status="已售罄";
                            }
                            if("C"==status||"7"==status){
                              self.tableData[i].status="募集结束";
                            }
                            if("D"==status||"3"==status){
                              self.tableData[i].status="预售中";
                            }

                            let invest_kind = self.tableData[i].invest_kind;
                            if("0"==invest_kind){
                              self.tableData[i].invest_kind = "基金"
                            }
                            if("1"==invest_kind){
                              self.tableData[i].invest_kind = "定期"
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
      		    			    				bank_id: '',
  	    			    				bank_no: '',
  	    			    				invest_no: '',
  	    			    				project_name: '',
  	    			    				invest_risk_level: '',
  	    			    				expect_yield: '',
  	    			    				invest_progress: '',
  	    			    				project_scale: '',
  	    			    				min_invest_amt: '',
  	    			    				max_invest_amt: '',
  	    			    				remain_amt: '',
  	    			    				each_amt: '',
  	    			    				invest_limit: '',
  	    			    				begin_date: '',
  	    			    				invest_enddate: '',
  	    			    				invest_startinterestdate: '',
  	    			    				prd_type: '',
  	    			    				status: '',
  	    			    				pay_way: '',
  	    			    				purpose: '',
  	    			    				createtime: '',
  	    			    				updatetime: '',


                      is_search: false,
                      order: '',
                  }
                },
                handleSort(val) {
                    console.log(val);
                    this.searchForm.order = val.prop + " " + val.order.replace("ending", "");
                    this.handleSearch();
                    return false;
                },
                handleAdd() {
                    this.dialogFormVisible = true;
                    this.editForm = {
    		    			    				bank_id: '',
	    			    				bank_no: '',
	    			    				invest_no: '',
	    			    				project_name: '',
	    			    				invest_risk_level: '',
	    			    				expect_yield: '',
	    			    				invest_progress: '',
	    			    				project_scale: '',
	    			    				min_invest_amt: '',
	    			    				max_invest_amt: '',
	    			    				remain_amt: '',
	    			    				each_amt: '',
	    			    				invest_limit: '',
	    			    				begin_date: '',
	    			    				invest_enddate: '',
	    			    				invest_startinterestdate: '',
	    			    				prd_type: '',
	    			    				status: '',
	    			    				pay_way: '',
	    			    				purpose: '',
	    			    				createtime: '',
	    			    				updatetime: '',
	    		                        abled: {
    		    			    				bank_id: true,
	    			    				bank_no: true,
	    			    				invest_no: true,
	    			    				project_name: true,
	    			    				invest_risk_level: true,
	    			    				expect_yield: true,
	    			    				invest_progress: true,
	    			    				project_scale: true,
	    			    				min_invest_amt: true,
	    			    				max_invest_amt: true,
	    			    				remain_amt: true,
	    			    				each_amt: true,
	    			    				invest_limit: true,
	    			    				begin_date: true,
	    			    				invest_enddate: true,
	    			    				invest_startinterestdate: true,
	    			    				prd_type: true,
	    			    				status: true,
	    			    				pay_way: true,
	    			    				purpose: true,
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
    		    			    				self.editForm.bank_id=response.data.bank_id;
	    			    				self.editForm.bank_no=response.data.bank_no;
	    			    				self.editForm.invest_no=response.data.invest_no;
	    			    				self.editForm.project_name=response.data.project_name;
	    			    				self.editForm.invest_risk_level=response.data.invest_risk_level;
	    			    				self.editForm.expect_yield=response.data.expect_yield;
	    			    				self.editForm.invest_progress=response.data.invest_progress;
	    			    				self.editForm.project_scale=response.data.project_scale;
	    			    				self.editForm.min_invest_amt=response.data.min_invest_amt;
	    			    				self.editForm.max_invest_amt=response.data.max_invest_amt;
	    			    				self.editForm.remain_amt=response.data.remain_amt;
	    			    				self.editForm.each_amt=response.data.each_amt;
	    			    				self.editForm.invest_limit=response.data.invest_limit;
	    			    				self.editForm.begin_date=response.data.begin_date;
	    			    				self.editForm.invest_enddate=response.data.invest_enddate;
	    			    				self.editForm.invest_startinterestdate=response.data.invest_startinterestdate;
	    			    				self.editForm.prd_type=response.data.prd_type;
	    			    				self.editForm.status=response.data.status;
	    			    				self.editForm.pay_way=response.data.pay_way;
	    			    				self.editForm.purpose=response.data.purpose;
	    			    				self.editForm.createtime=response.data.createtime;
	    			    				self.editForm.updatetime=response.data.updatetime;
	    		                        return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })

                },
                handleDelete(index, row) {
                    const self = this;
                    console.log(row);
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
				bank_id:self.editForm.bank_id,
				bank_no:self.editForm.bank_no,
				invest_no:self.editForm.invest_no,
				project_name:self.editForm.project_name,
				invest_risk_level:self.editForm.invest_risk_level,
				expect_yield:self.editForm.expect_yield,
				invest_progress:self.editForm.invest_progress,
				project_scale:self.editForm.project_scale,
				min_invest_amt:self.editForm.min_invest_amt,
				max_invest_amt:self.editForm.max_invest_amt,
				remain_amt:self.editForm.remain_amt,
				each_amt:self.editForm.each_amt,
				invest_limit:self.editForm.invest_limit,
				begin_date:self.editForm.begin_date,
				invest_enddate:self.editForm.invest_enddate,
				invest_startinterestdate:self.editForm.invest_startinterestdate,
				prd_type:self.editForm.prd_type,
				status:self.editForm.status,
				pay_way:self.editForm.pay_way,
				purpose:self.editForm.purpose,
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
                handleLook(row, event, column){
                 	let self = this;
                  	/*if(self.radio){*/
                  		http.post(self.lookurl + '?id=' + row.id, {},(res) => {
                                this.investInfoData = res.data;
								console.log("raise_date=",this.raise_date);
                                this.investInfoData.min_invest_amt = fmoney(this.investInfoData.min_invest_amt,2);
                                this.investInfoData.project_scale = fmoney(this.investInfoData.project_scale,2);
                                this.investInfoData.each_amt = fmoney(this.investInfoData.each_amt,2);
                                this.investInfoData.remain_amt = fmoney(this.investInfoData.remain_amt,2);


                                this.investInfoData.invest_progress = fmoney(this.investInfoData.invest_progress*100,1);
                                this.investInfoData.my_invest_progress = fmoney(this.investInfoData.my_invest_progress*100,1);
                                this.investInfoData.expect_yield = fmoney(this.investInfoData.expect_yield*100,1);

                                this.investInfoData.invest_endDate = this.investInfoData.invest_endDate.split(' ')[0];
                                this.investInfoData.invest_startInterestDate = this.investInfoData.invest_startInterestDate.split(' ')[0];
                                this.raise_date = this.investInfoData.raise_date.toString();

                                console.log("raise_date=",this.raise_date);

                                let datas = this.investInfoData.tCustomerInvests;
                                if(datas!=null){
                                 	for (var i = 0; i < datas.length; i++) {
                                  		datas[i].invest_amt = fmoney(datas[i].invest_amt,2);
                                	}
                                }


                                let invest_kind = this.investInfoData.invest_kind;
                                if("0"==invest_kind){
                                  this.investInfoData.invest_kind = "基金"
                                }
                                if("1"==invest_kind){
                                  this.investInfoData.invest_kind = "定期"
                                }

                                let winteness_info = this.investInfoData.winteness_info;
                                if(winteness_info!=null){
                                	this.winteness_infos = winteness_info.split("|");
                                }
                                self.dialogFormVisible = true;
                                return;
							}).catch(err => {
			                	self.$eventHub.$emit('error', err);
							})


                  	/*}else{
                  		this.$message('请选择产品');
					}*/

             	}

        }
}

</script>
