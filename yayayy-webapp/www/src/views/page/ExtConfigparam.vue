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
            <el-input v-model="searchForm.const_code" placeholder="常量编码" class="handle-input mr10"></el-input>
            <el-input v-model="searchForm.const_name" placeholder="常量名称" class="handle-input mr10"></el-input>
            <el-input v-model="searchForm.const_value" placeholder="常量数值" class="handle-input mr10"></el-input>
            <el-input v-model="searchForm.const_type" placeholder="常量类别" class="handle-input mr10"></el-input>
            <el-input v-model="searchForm.const_type_code" placeholder="常量类别编码" class="handle-input mr10"></el-input>

            <el-button-group>
		        <el-button type="primary"  @click="handleSearch">搜索</el-button>
		        <el-button type="primary"  @click="handleReset">重置</el-button>
			</el-button-group> 
        </div>
        
        <div class="handle-box">
			<el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
   		</div>
        <el-table :data="data" border style="width: 100%" ref="multipleTable" @sort-change="handleSort">


            <el-table-column prop="const_code" label="常量编码">
            </el-table-column>

            <el-table-column prop="const_name" label="常量名称">
            </el-table-column>

            <el-table-column prop="const_value" label="常量数值">
            </el-table-column>

            <el-table-column prop="const_type" label="常量类别">
            </el-table-column>

            <el-table-column prop="const_type_code" label="常量类别编码">
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

        <el-dialog title="编辑参数" :visible.sync="dialogFormVisible" size="tiny">
            <el-form ref="editForm" :model="editForm" label-width="80px">
                <input v-model="editForm.id" type="hidden"></input>

                <el-form-item label="常量编码">
                    <el-input v-model="editForm.const_code" ></el-input>
                </el-form-item>

                <el-form-item label="常量名称">
                    <el-input v-model="editForm.const_name" ></el-input>
                </el-form-item>

                <el-form-item label="常量数值">
                    <el-input v-model="editForm.const_value" ></el-input>
                </el-form-item>

                <el-form-item label="常量类别">
                    <el-input v-model="editForm.const_type" ></el-input>
                </el-form-item>

                <el-form-item label="常量类别编码">
                    <el-input v-model="editForm.const_type_code" ></el-input>
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
                getdataurl: process.env.BASE_API + 'extConfigparam/getId.do',
                dataurl: process.env.BASE_API + 'extConfigparam/dataList.do',
                saveurl: process.env.BASE_API + 'extConfigparam/save.do',
                deleteurl: process.env.BASE_API + 'extConfigparam/delete.do',
                dialogFormVisible: false,
                editLoading: false,
                editForm: {
                    const_code: '',
                    const_name: '',
                    const_value: '',
                    const_type:'',
                    const_type_code:'',
                    abled: {

                        const_code: true,
                        const_name: true,
                        const_value: true,
                        const_type:true,
                    	const_type_code:true,
                    }
                },
                editForm_sysMenu_ids:[],
                searchForm: {
                    const_code: '',
                    const_name: '',
                    const_value: '',
					const_type:'',
                    const_type_code:'',
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
                    const_code: '',
                    const_name: '',
                    const_value: '',
                    const_type:'',
                    const_type_code:'',
                    abled: {
                        const_code: true,
                        const_name: true,
                        const_value: true,
                        const_type:true,
                    	const_type_code:true,
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
                    self.editForm.const_code=response.data.const_code;
                self.editForm.const_name=response.data.const_name;
                self.editForm.const_value=response.data.const_value;
                return;
            }).catch(err => {
                    self.$eventHub.$emit('error', err);
            })

            },
            handleDelete(index, row) {
                const self = this;
                console.log(row);
                this.$confirm('确定删除这条参数？', '提示', {
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
                    const_code:self.editForm.const_code,
                    const_name:self.editForm.const_name,
                    const_value:self.editForm.const_value,
                    const_type:self.editForm.const_type,
                    const_type_code:self.editForm.const_type_code,
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
      		    		const_code: '',
  	    			    const_name: '',
  	    			    const_value: '',
  	    			    const_type: '',
  	    			    const_type_code: '',


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
