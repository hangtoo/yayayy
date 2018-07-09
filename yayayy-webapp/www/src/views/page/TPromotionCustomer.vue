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
        <el-button type="primary" icon="add" class="handle-add mr10" @click="handleAdd">新增</el-button>

		<el-input v-model="searchForm.t_promotion_id" placeholder="活动表id" class="handle-input mr10"></el-input>
	    <el-input v-model="searchForm.custome_invest_no" placeholder="订单编号" class="handle-input mr10"></el-input>
	    <el-input v-model="searchForm.custom_name" placeholder="姓名" class="handle-input mr10"></el-input>
	    <el-input v-model="searchForm.custom_mobile" placeholder="注册手机号" class="handle-input mr10"></el-input>
	    <el-input v-model="searchForm.invest_amt" placeholder="投资金额(元)" class="handle-input mr10"></el-input>
	    <el-input v-model="searchForm.reward_amt" placeholder="活动奖励(元)" class="handle-input mr10"></el-input>
	    <el-input v-model="searchForm.use_flag" placeholder="使用标志(0.未使用  1.已使用 )" class="handle-input mr10"></el-input>

        <el-button type="primary" icon="search" @click="handleSearch">搜索</el-button>
    </div>
    <el-table :data="data" border style="width: 100%" ref="multipleTable" @sort-change="handleSort">


        <el-table-column prop="t_promotion_id" label="活动表id">
        </el-table-column>


        <el-table-column prop="custome_invest_no" label="订单编号">
        </el-table-column>


        <el-table-column prop="custom_name" label="姓名">
        </el-table-column>


        <el-table-column prop="custom_mobile" label="注册手机号">
        </el-table-column>


        <el-table-column prop="invest_amt" label="投资金额(元)">
        </el-table-column>


        <el-table-column prop="reward_amt" label="活动奖励(元)">
        </el-table-column>


        <el-table-column prop="use_flag" label="使用标志(0.未使用  1.已使用 )">
        </el-table-column>


        <el-table-column label="操作" width="150">
            <template slot-scope="scope">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="searchForm.totalRow">
        </el-pagination>
    </div>

    <el-dialog title="编辑活动参与表" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px">
        		<input v-model="editForm.id" type="hidden"></input>

    		    			    	            <el-form-item label="活动表id">
              <el-input v-model="editForm.t_promotion_id" :disabled="!editForm.abled.t_promotion_id"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="订单编号">
              <el-input v-model="editForm.custome_invest_no" :disabled="!editForm.abled.custome_invest_no"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="姓名">
              <el-input v-model="editForm.custom_name" :disabled="!editForm.abled.custom_name"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="注册手机号">
              <el-input v-model="editForm.custom_mobile" :disabled="!editForm.abled.custom_mobile"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="投资金额(元)">
              <el-input v-model="editForm.invest_amt" :disabled="!editForm.abled.invest_amt"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="活动奖励(元)">
              <el-input v-model="editForm.reward_amt" :disabled="!editForm.abled.reward_amt"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="使用标志(0.未使用  1.已使用 )">
              <el-input v-model="editForm.use_flag" :disabled="!editForm.abled.use_flag"></el-input>
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
export default {
    data() {
            return {
                getdataurl: process.env.BASE_API + 'tPromotionCustomer/getId.do',
                dataurl: process.env.BASE_API + 'tPromotionCustomer/dataList.do',
                saveurl: process.env.BASE_API + 'tPromotionCustomer/save.do',
                deleteurl: process.env.BASE_API + 'tPromotionCustomer/delete.do',
                dialogFormVisible: false,
                editLoading: false,
                editForm: {
    		    			    				t_promotion_id: '',
	    			    				custome_invest_no: '',
	    			    				custom_name: '',
	    			    				custom_mobile: '',
	    			    				invest_amt: '',
	    			    				reward_amt: '',
	    			    				use_flag: '',
	    		                    abled: {

    		    			    				t_promotion_id: true,
	    			    				custome_invest_no: true,
	    			    				custom_name: true,
	    			    				custom_mobile: true,
	    			    				invest_amt: true,
	    			    				reward_amt: true,
	    			    				use_flag: true,
	    		                    }
                },
                editForm_sysMenu_ids:[],
                searchForm: {
    		    			    				t_promotion_id: '',
	    			    				custome_invest_no: '',
	    			    				custom_name: '',
	    			    				custom_mobile: '',
	    			    				invest_amt: '',
	    			    				reward_amt: '',
	    			    				use_flag: '',

                    pageNum: 1,
                    totalRow: 0,
                    pageSize: 10,
                    is_search: false,
                    order: '',
                },
                tableData: [],
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
                    http.post(self.dataurl, this.searchForm,(res) => {
                        self.tableData = res.data.list;
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
                handleSort(val) {
                    console.log(val);
                    this.searchForm.order = val.prop + " " + val.order.replace("ending", "");
                    this.handleSearch();
                    return false;
                },
                handleAdd() {
                    this.dialogFormVisible = true;
                    this.editForm = {
    		    			    				t_promotion_id: '',
	    			    				custome_invest_no: '',
	    			    				custom_name: '',
	    			    				custom_mobile: '',
	    			    				invest_amt: '',
	    			    				reward_amt: '',
	    			    				use_flag: '',
	    		                        abled: {
    		    			    				t_promotion_id: true,
	    			    				custome_invest_no: true,
	    			    				custom_name: true,
	    			    				custom_mobile: true,
	    			    				invest_amt: true,
	    			    				reward_amt: true,
	    			    				use_flag: true,
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
    		    			    				self.editForm.t_promotion_id=response.data.t_promotion_id;
	    			    				self.editForm.custome_invest_no=response.data.custome_invest_no;
	    			    				self.editForm.custom_name=response.data.custom_name;
	    			    				self.editForm.custom_mobile=response.data.custom_mobile;
	    			    				self.editForm.invest_amt=response.data.invest_amt;
	    			    				self.editForm.reward_amt=response.data.reward_amt;
	    			    				self.editForm.use_flag=response.data.use_flag;
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
				t_promotion_id:self.editForm.t_promotion_id,
				custome_invest_no:self.editForm.custome_invest_no,
				custom_name:self.editForm.custom_name,
				custom_mobile:self.editForm.custom_mobile,
				invest_amt:self.editForm.invest_amt,
				reward_amt:self.editForm.reward_amt,
				use_flag:self.editForm.use_flag,
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
