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
        <el-input v-model="searchForm.rolecode" placeholder="角色编码" class="handle-input mr10"></el-input>
        <el-input v-model="searchForm.rolename" placeholder="角色名称" class="handle-input mr10"></el-input>
        <el-button-group>
	        <el-button type="primary" @click="handleSearch">搜索</el-button>
	        <el-button type="primary" @click="handleReset">重置</el-button>
        </el-button-group> 
    </div>
     <div class="handle-box">
        <el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
    </div>
    
    <el-table :data="data" border style="width: 100%" ref="multipleTable" @sort-change="handleSort">
        <el-table-column prop="rolecode" label="角色编码" sortable width="120">
        </el-table-column>
        <el-table-column prop="rolename" label="角色名称" sortable width="150">
        </el-table-column>
        <el-table-column prop="state" label="状态" width="150" :formatter="stateformatter">
        </el-table-column>
        <el-table-column prop="descr" label="角色描述" >
        </el-table-column>
        <el-table-column prop="sysflag" label="角色类型" width="100" :formatter="sysflagformatter">
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

    <el-dialog title="编辑角色信息" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px">
          <input v-model="editForm.id" type="hidden"></input>
            <el-form-item label="角色编码">
              <el-input v-model="editForm.rolecode" :disabled="!editForm.abled.rolecode"></el-input>
            </el-form-item>
            <el-form-item label="角色名称">
                <el-input v-model="editForm.rolename"></el-input>
            </el-form-item>
            <el-form-item label="状态">
                <el-select v-model="editForm.state" placeholder="请选择">
                  <el-option
                    v-for="item in stateoptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>

            </el-form-item>
            <el-form-item label="角色描述">
                <el-input v-model="editForm.descr"></el-input>
            </el-form-item>
            <el-form-item label="角色类型">
                <el-select v-model="editForm.sysflag" placeholder="请选择">
                  <el-option
                    v-for="item in sysflagoptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="功能权限">

                        <el-tree
                          :data="menuoptions"
                          show-checkbox
                          default-expand-all
                          node-key="id"
                          ref="menutree"
                          :default-checked-keys="editForm_sysMenu_ids">
                        </el-tree>

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
                getdataurl: process.env.BASE_API + 'sysRole/getId.do',
                dataurl: process.env.BASE_API + 'sysRole/dataList.do',
                saveurl: process.env.BASE_API + 'sysRole/save.do',
                deleteurl: process.env.BASE_API + 'sysRole/delete.do',
                menutreeurl: process.env.BASE_API + 'sysMenu/getMenusTable.do',
                dialogFormVisible: false,
                editLoading: false,
                editForm: {
                    rolename: '',
                    rolecode: '',
                    state: '',
                    descr: '',
                    sysflag:0,
                    abled: {
                        rolename: true,
                    }
                },
                editForm_sysMenu_ids:[],
                searchForm: {
                    rolename: '',
                    rolecode: '',
                    pageNum: 1,
                    totalRow: 0,
                    pageSize: 10,
                    is_search: false,
                    order: '',
                },
                tableData: [],
                multipleSelection: [],
                stateoptions:[
                  {
                    value: 0,
                    label: '启用'
                  }, {
                    value: 1,
                    label: '未启用'
                  }
                ],

                sysflagoptions:[
                  {
                    value: 0,
                    label: '功能权限'
                  }, {
                    value: 1,
                    label: '审批权限'
                  }
                ],
                menuoptions: [],
                imenutree: 0,
            }
        },
        created() {
            this.loadData();
            this.loadMenuData();
        },
        computed: {
            data() {
                const self = this;
                return self.tableData;
            }
        },
        methods: {
                handleCheckChange(data, node) {
                    this.imenutree++;
                    if (this.imenutree % 2 == 0) {
                        this.$refs.menutree.setCheckedNodes([]);
                        this.$refs.menutree.setCheckedNodes([data]);
                    }
                },
                handleCurrentChange(val) {
                    this.searchForm.pageNum = val;
                    this.loadData();
                },
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
                loadMenuData() {
                    let self = this;
                    http.post(self.menutreeurl, {},(res) => {
                        self.menuoptions = res.data;
                    }).catch(err => {
                        this.$eventHub.$emit('error', err);
                    })
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
                stateformatter(row, column) {
                    if (row.state == '0') {
                        return "启用"
                    }
                    return "未启用"
                },
                sysflagformatter(row, column) {
                    if (row.sysflag == '0') {
                        return "功能权限"
                    }
                    return "审批权限"
                },
                handleAdd() {
                    this.dialogFormVisible = true;
                    this.editForm = {
                        state:0,
                        sysflag:0,
                        abled: {
                            rolecode: true,
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
                        self.editForm_menuids = response.data.menuids;
                        self.editForm_btnids = response.data.btnids;
                        if (!!response.data.menuids) {
                            self.editForm_sysMenu_ids = response.data.menuids;
                            self.$refs.menutree.setCheckedKeys(self.editForm_sysMenu_ids);
                        }
                        return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })

                },
                handleDelete(index, row) {
                	this.$confirm('此操作将永久删除该角色, 是否继续?', '提示', {
          			confirmButtonText: '确定',
          			cancelButtonText: '取消',
         			type: 'warning'
        		}).then(() => {

          			const self = this;
                    console.log(row);
                    http.post(self.deleteurl, {
                        id: row.id,
                    },response => {
                        self.loadData();
                        this.$message({
            			type: 'success',
            			message: '删除成功!'
          			});
                        return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })
       			}).catch(() => {
          			this.$message({
            			type: 'info',
            			message: '已取消删除'
          			});
        			});


                },
                handleSave() {
                    const self = this;
                    var sysMenu_checkedKeys = self.$refs.menutree.getCheckedKeys();

                    http.post(self.saveurl, {
                        id: self.editForm.id,
                        rolecode: self.editForm.rolecode,
                        rolename: self.editForm.rolename,
                        state: self.editForm.state,
                        sysflag: self.editForm.sysflag,
                        descr: self.editForm.descr,
                        menuids: sysMenu_checkedKeys,
                    },response => {
                    	this.$message({
            				message: response.data.msg
          				});
                        self.loadData();
                        self.dialogFormVisible = false;
                        return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })
                },
                handleSelectionChange(val) {
                    this.multipleSelection = val;
                },
                handleReset(){
                  this.searchForm.is_search = false;
                  this.searchForm = {
      		    		rolecode: '',
  	    			    rolename: '',

                      pageNum: 1,
                      totalRow: 0,
                      pageSize: 10,
                      is_search: false,
                      order: '',
                  }
                },
                handleFalse() {
                    return false;
                },
                 handleReset(){
                  this.searchForm.is_search = false;
                  this.searchForm = {
                        rolecode: '',
                        rolename: '',


                    pageNum: 1,
                    totalRow: 0,
                    pageSize: 10,
                    is_search: false,
                    order: '',
                  }
                },
        }
}

</script>
