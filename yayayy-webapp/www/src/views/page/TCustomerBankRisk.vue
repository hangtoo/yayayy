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

    		    			    		    		<el-input v-model="searchForm.custom_id" placeholder="客户信息id(用于关联)" class="handle-input mr10"></el-input>
	    			    		    		<el-input v-model="searchForm.bank_id" placeholder="银行id(用于关联)" class="handle-input mr10"></el-input>
	    			    		    		<el-input v-model="searchForm.risk_lev" placeholder="风险等级(1=保守型2=谨慎型3=稳健型4=积极型5=激进型)" class="handle-input mr10"></el-input>
	    			    		    		<el-input v-model="searchForm.risk_score" placeholder="风险测评分数" class="handle-input mr10"></el-input>
	    			    		    		<el-input v-model="searchForm.risk_desc" placeholder="风险测评描述" class="handle-input mr10"></el-input>
	    			    		    		<el-input v-model="searchForm.createtime" placeholder="创建时间" class="handle-input mr10"></el-input>
	    			    		    		<el-input v-model="searchForm.updatetime" placeholder="更新时间" class="handle-input mr10"></el-input>

        <el-button type="primary" icon="search" @click="handleSearch">搜索</el-button>
    </div>
    <el-table :data="data" border style="width: 100%" ref="multipleTable" @sort-change="handleSort">


        <el-table-column prop="custom_id" label="客户信息id(用于关联)">
        </el-table-column>


        <el-table-column prop="bank_id" label="银行id(用于关联)">
        </el-table-column>


        <el-table-column prop="risk_lev" label="风险等级(1=保守型2=谨慎型3=稳健型4=积极型5=激进型)">
        </el-table-column>


        <el-table-column prop="risk_score" label="风险测评分数">
        </el-table-column>


        <el-table-column prop="risk_desc" label="风险测评描述">
        </el-table-column>


        <el-table-column prop="createtime" label="创建时间">
        </el-table-column>


        <el-table-column prop="updatetime" label="更新时间">
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

    <el-dialog title="编辑用户银行风险表" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px">
        		<input v-model="editForm.id" type="hidden"></input>

    		    			    	            <el-form-item label="客户信息id(用于关联)">
              <el-input v-model="editForm.custom_id" :disabled="!editForm.abled.custom_id"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="银行id(用于关联)">
              <el-input v-model="editForm.bank_id" :disabled="!editForm.abled.bank_id"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="风险等级(1=保守型2=谨慎型3=稳健型4=积极型5=激进型)">
              <el-input v-model="editForm.risk_lev" :disabled="!editForm.abled.risk_lev"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="风险测评分数">
              <el-input v-model="editForm.risk_score" :disabled="!editForm.abled.risk_score"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="风险测评描述">
              <el-input v-model="editForm.risk_desc" :disabled="!editForm.abled.risk_desc"></el-input>
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

</div>

</template>

<script>
import http from '@/utils/http';
export default {
    data() {
            return {
                getdataurl: process.env.BASE_API + 'tCustomerBankRisk/getId.do',
                dataurl: process.env.BASE_API + 'tCustomerBankRisk/dataList.do',
                saveurl: process.env.BASE_API + 'tCustomerBankRisk/save.do',
                deleteurl: process.env.BASE_API + 'tCustomerBankRisk/delete.do',
                dialogFormVisible: false,
                editLoading: false,
                editForm: {
    		    			    				custom_id: '',
	    			    				bank_id: '',
	    			    				risk_lev: '',
	    			    				risk_score: '',
	    			    				risk_desc: '',
	    			    				createtime: '',
	    			    				updatetime: '',
	    		                    abled: {

    		    			    				custom_id: true,
	    			    				bank_id: true,
	    			    				risk_lev: true,
	    			    				risk_score: true,
	    			    				risk_desc: true,
	    			    				createtime: true,
	    			    				updatetime: true,
	    		                    }
                },
                editForm_sysMenu_ids:[],
                searchForm: {
    		    			    				custom_id: '',
	    			    				bank_id: '',
	    			    				risk_lev: '',
	    			    				risk_score: '',
	    			    				risk_desc: '',
	    			    				createtime: '',
	    			    				updatetime: '',

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
    		    			    				custom_id: '',
	    			    				bank_id: '',
	    			    				risk_lev: '',
	    			    				risk_score: '',
	    			    				risk_desc: '',
	    			    				createtime: '',
	    			    				updatetime: '',
	    		                        abled: {
    		    			    				custom_id: true,
	    			    				bank_id: true,
	    			    				risk_lev: true,
	    			    				risk_score: true,
	    			    				risk_desc: true,
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
    		    			    				self.editForm.custom_id=response.data.custom_id;
	    			    				self.editForm.bank_id=response.data.bank_id;
	    			    				self.editForm.risk_lev=response.data.risk_lev;
	    			    				self.editForm.risk_score=response.data.risk_score;
	    			    				self.editForm.risk_desc=response.data.risk_desc;
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
				custom_id:self.editForm.custom_id,
				bank_id:self.editForm.bank_id,
				risk_lev:self.editForm.risk_lev,
				risk_score:self.editForm.risk_score,
				risk_desc:self.editForm.risk_desc,
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
                }
        }
}

</script>
