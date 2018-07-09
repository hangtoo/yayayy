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
   

		<el-input v-model="searchForm.bank_id" placeholder="银行id" class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.facade_name" placeholder="接口名" class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.provider_name" placeholder="接口实现类名id" class="handle-input mr10"></el-input>
		<el-button-group>
	        <el-button type="primary"  @click="handleSearch">搜索</el-button>
	        <el-button type="primary"  @click="handleReset">重置</el-button>
		</el-button-group> 
    </div>
    
    <div class="handle-box">
		<el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
    </div>
    
    
    <el-table :data="data" border style="width: 100%" ref="multipleTable" @sort-change="handleSort">


        <el-table-column prop="bank_id" label="银行id">
        </el-table-column>


        <el-table-column prop="facade_name" label="接口名">
        </el-table-column>


        <el-table-column prop="provider_name" label="接口实现类名id">
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

    <el-dialog title="编辑银行接口" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px">
        		<input v-model="editForm.id" type="hidden"></input>

    		    			    	            <el-form-item label="银行id">
              <el-input v-model="editForm.bank_id" :disabled="!editForm.abled.bank_id"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="接口名">
              <el-input v-model="editForm.facade_name" :disabled="!editForm.abled.facade_name"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="接口实现类名id">
              <el-input v-model="editForm.provider_name" ></el-input>
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
                getdataurl: process.env.BASE_API + 'tBankFacade/getId.do',
                dataurl: process.env.BASE_API + 'tBankFacade/dataList.do',
                saveurl: process.env.BASE_API + 'tBankFacade/save.do',
                deleteurl: process.env.BASE_API + 'tBankFacade/delete.do',
                dialogFormVisible: false,
                editLoading: false,
                editForm: {
    		    			    				bank_id: '',
	    			    				facade_name: '',
	    			    				provider_name: '',
	    		                    abled: {

    		    			    				bank_id: true,
	    			    				facade_name: true,
	    			    				provider_name: true,
	    		                    }
                },
                editForm_sysMenu_ids:[],
                searchForm: {
    		    			    				bank_id: '',
	    			    				facade_name: '',
	    			    				provider_name: '',

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
    		    			    				bank_id: '',
	    			    				facade_name: '',
	    			    				provider_name: '',
	    		                        abled: {
    		    			    				bank_id: true,
	    			    				facade_name: true,
	    			    				provider_name: true,
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
	    			    				self.editForm.facade_name=response.data.facade_name;
	    			    				self.editForm.provider_name=response.data.provider_name;
	    		                        return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })

                },
                handleDelete(index, row) {
                    const self = this;
                    console.log(row);
                    this.$confirm('确定删除此条活动？', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {
						http.post(self.deleteurl, {
	                        id: row.id,
	                    },response => {
	                        self.loadData();
	                        return;
	                    }).catch(err => {
	                        self.$eventHub.$emit('error', err);
	                    })
					}).catch(() => {
					});



                },
                handleSave() {
                    const self = this;

                    http.post(self.saveurl, {
    				id:self.editForm.id,
				bank_id:self.editForm.bank_id,
				facade_name:self.editForm.facade_name,
				provider_name:self.editForm.provider_name,
	                    },response => {
                        self.loadData();
                        self.dialogFormVisible = false;
                        return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })
                },
                handleReset(){
                  this.searchForm.is_search = false;
                  this.searchForm = {
      		    		bank_id: '',
  	    			    facade_name: '',
  	    			    provider_name: '',


                      is_search: false,
                      order: '',
                  }
                },
                handleFalse() {
                    return false;
                }
        }
}

</script>
