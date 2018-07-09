<style scoped>

.handle-box {
    margin-bottom: 20px;
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

	    <el-select v-model="searchForm.channel_id"  clearable filterable placeholder="请选择渠道">
			<el-option
			v-for="item in channel_options"
			:key="item.id"
			:label="item.channel_name"
			:value="item.id">
			</el-option>
		</el-select>

		<el-select v-model="searchForm.promotion_id"  clearable filterable placeholder="请选择活动">
			<el-option
			v-for="item in promotion_options"
			:key="item.promotion_id"
			:label="item.promotion_name"
			:value="item.promotion_id">
			</el-option>
		</el-select>


		<el-button-group>
	        <el-button type="primary"  @click="handleSearch">搜索</el-button>
	        <el-button type="primary"  @click="handleReset">重置</el-button>
		</el-button-group>     
    </div>
    <el-table :data="data" border style="width: 100%" ref="multipleTable" @sort-change="handleSort">

        <el-table-column prop="channel_name" label="渠道" min-width="120" align="center">
        </el-table-column>

       <el-table-column prop="promotion_name" label="活动名称" min-width="120" align="center">
        </el-table-column>

        <el-table-column prop="promotion_begintime" label="活动开始时间" width="180"align="center">
        </el-table-column>

        <el-table-column prop="promotion_endtime" label="活动结束时间" width="180"align="center">
        </el-table-column>

        <el-table-column prop="register_num_string" label="注册人数" min-width="100" align="center">
        </el-table-column>

	    <el-table-column prop="custom_num_string" label="参加人数" min-width="100" align="center">
        </el-table-column>

        <el-table-column prop="volume_string" label="成交量" min-width="100" align="center">
        </el-table-column>

    </el-table>
    <!--
    <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="searchForm.totalRow">
        </el-pagination>
    </div>
	-->
    <el-dialog title="编辑客户来源表" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px">
        		<input v-model="editForm.id" type="hidden"></input>

    		    			    	            <el-form-item label="渠道id （自己表中的大姨妈的id）  (如 大姨妈id )">
              <el-input v-model="editForm.channel_id" :disabled="!editForm.abled.channel_id"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="渠道编号(如 大姨妈代号 )">
              <el-input v-model="editForm.channel_no" :disabled="!editForm.abled.channel_no"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="渠道对应的uid">
              <el-input v-model="editForm.channel_uid" :disabled="!editForm.abled.channel_uid"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="客户信息id(用于关联)(如果有则表示泛泰有这个用户的信息
											 但是不一定有这个用户要投资产品的二类户 这个二类户要去用户的信息中查询)">
              <el-input v-model="editForm.custom_id" :disabled="!editForm.abled.custom_id"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="客户编号(自己生成)">
              <el-input v-model="editForm.custom_no" :disabled="!editForm.abled.custom_no"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="活动编号">
              <el-input v-model="editForm.promotion_id" :disabled="!editForm.abled.promotion_id"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="退出登陆的标识（默认静默登陆）1静默登陆 0重新登陆">
              <el-input v-model="editForm.flag" :disabled="!editForm.abled.flag"></el-input>
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
                getdataurl: process.env.BASE_API + 'tCustomerFrom/getId.do',
                dataurl: process.env.BASE_API + 'tCustomerFrom/dataList.do',
                saveurl: process.env.BASE_API + 'tCustomerFrom/save.do',
                deleteurl: process.env.BASE_API + 'tCustomerFrom/delete.do',
                searchurl: process.env.BASE_API + 'tCustomerFrom/searchlist.do',
                dialogFormVisible: false,
                editLoading: false,
                editForm: {
    		    			    				channel_id: '',
	    			    				channel_no: '',
	    			    				channel_uid: '',
	    			    				custom_id: '',
	    			    				custom_no: '',
	    			    				promotion_id: '',
	    			    				flag: '',
	    		                    abled: {

    		    			    				channel_id: true,
	    			    				channel_no: true,
	    			    				channel_uid: true,
	    			    				custom_id: true,
	    			    				custom_no: true,
	    			    				promotion_id: true,
	    			    				flag: true,
	    		                    }
                },
                editForm_sysMenu_ids:[],
                searchForm: {
    		    			    				channel_id: '',
	    			    				channel_no: '',
	    			    				channel_uid: '',
	    			    				custom_id: '',
	    			    				custom_no: '',
	    			    				promotion_id: '',
	    			    				flag: '',


                    is_search: false,
                    order: '',
                },
                tableData: [],
                promotion_options:'',
                channel_options:'',
            }
        },
        created() {
        	this.getSearchData();
            this.loadData();
        },
        computed: {
            data() {
                const self = this;
                return self.tableData;
            }
        },
        methods: {
        		getSearchData() {
                  let self = this;
                  http.post(self.searchurl,{},(res) => {
                      self.promotion_options = res.data.promotionList;
                      self.channel_options = res.data.channelList;
                      console.log(self.promotion_options);
                      console.log(self.channel_options);
                  }).catch(err => {
                      this.$eventHub.$emit('error', err);
                  })
                },
                loadData() {
                    let self = this;
                    http.post(self.dataurl, this.searchForm,(res) => {
                        self.tableData = res.data.list;
                        
                        for (var i = 0; i < self.tableData.length; i++) {
	                    
                       		if( self.tableData[i].promotion_begintime!=null){
                            	self.tableData[i].promotion_begintime = fdate(self.tableData[i].promotion_begintime);
                       		}
                       		if( self.tableData[i].promotion_endtime!=null){
                            	self.tableData[i].promotion_endtime = fdate(self.tableData[i].promotion_endtime);
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
                    this.editForm = {
    		    			    				channel_id: '',
	    			    				channel_no: '',
	    			    				channel_uid: '',
	    			    				custom_id: '',
	    			    				custom_no: '',
	    			    				promotion_id: '',
	    			    				flag: '',
	    		                        abled: {
    		    			    				channel_id: true,
	    			    				channel_no: true,
	    			    				channel_uid: true,
	    			    				custom_id: true,
	    			    				custom_no: true,
	    			    				promotion_id: true,
	    			    				flag: true,
	    		                        }
                    };
                },
                handleReset(){
                  this.searchForm.is_search = false;
                  this.searchForm = {
      		    		channel_id: '',
  	    			    promotion_id: '',



                      is_search: false,
                      order: '',
                  }
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
	    			    				self.editForm.channel_uid=response.data.channel_uid;
	    			    				self.editForm.custom_id=response.data.custom_id;
	    			    				self.editForm.custom_no=response.data.custom_no;
	    			    				self.editForm.promotion_id=response.data.promotion_id;
	    			    				self.editForm.flag=response.data.flag;
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
				channel_id:self.editForm.channel_id,
				channel_no:self.editForm.channel_no,
				channel_uid:self.editForm.channel_uid,
				custom_id:self.editForm.custom_id,
				custom_no:self.editForm.custom_no,
				promotion_id:self.editForm.promotion_id,
				flag:self.editForm.flag,
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
                }
        }
}

</script>
