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
       
        <el-input v-model="searchForm.email" placeholder="登录账户" class="handle-input mr10"></el-input>
        <el-input v-model="searchForm.nickname" placeholder="姓名" class="handle-input mr10"></el-input>
        <el-button-group>
	        <el-button type="primary"  @click="handleSearch">搜索</el-button>
	        <el-button type="primary"  @click="handleReset">重置</el-button>
        </el-button-group> 
    </div>
    <div class="handle-box">
		<el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
    </div>
    
    <el-table :data="data" border style="width: 100%" ref="multipleTable" @sort-change="handleSort" @selection-change="handleSelectionChange">
        <el-table-column prop="email" label="登录账户" sortable :sort-method="handleFalse" width="120">
        </el-table-column>
        <el-table-column prop="nickname" label="姓名" sortable width="120">
        </el-table-column>
        <el-table-column prop="name" label="所属部门" width="120" :formatter="departmentformatter">
        </el-table-column>
        <el-table-column prop="logincount" label="登录次数" width="95">
        </el-table-column>
        <el-table-column prop="state" label="状态" width="80" :formatter="stateformatter">
        </el-table-column>
        <el-table-column prop="checknames" label="审批权限">
        </el-table-column>
        <el-table-column prop="rolenames" label="功能权限" width="120">
        </el-table-column>
        <el-table-column label="操作" width="280">
            <template slot-scope="scope">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button size="small" @click="handlePwdEdit(scope.$index, scope.row)">修改密码</el-button>
                <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="searchForm.totalRow">
        </el-pagination>
    </div>

    <el-dialog title="修改用户密码" v-model="dialogPwdFormVisible" :visible.sync="dialogPwdFormVisible"  size="tiny">
        <el-form ref="editPwdForm"  :rules="pwdRules" :model="editPwdForm" label-width="80px">
            <el-form-item label="登录账户">
                <el-input v-model="editPwdForm.email" :disabled="!editPwdForm.abled.email"></el-input>
                <input v-model="editPwdForm.id" type="hidden"></input>
            </el-form-item>
            <el-form-item label="用户名">
                <el-input v-model="editPwdForm.nickname" :disabled="!editPwdForm.abled.nickname" ></el-input>
            </el-form-item>
            <el-form-item label="旧密码(管理员可不填)">
                <el-input v-model="editPwdForm.pwd" type="password" ></el-input>
            </el-form-item>
            <el-form-item label="新密码">
                <el-input v-model="editPwdForm.newpwd" type="password" ></el-input>
            </el-form-item>
            <el-form-item label="再次输入">
                <el-input v-model="editPwdForm.pwdagain" type="password" ></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="handlePwdSave" :loading="editLoading">保存</el-button>
                <el-button @click="dialogPwdFormVisible = false">取消</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>

    <el-dialog title="编辑用户信息" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px">
            <el-form-item label="登录账户">
                <el-input v-model="editForm.email" :disabled="!editForm.abled.email"></el-input>
                <input v-model="editForm.id" type="hidden"></input>
            </el-form-item>
            <el-form-item label="用户名">
                <el-input v-model="editForm.nickname"></el-input>
            </el-form-item>
            <el-form-item label="状态">
                <el-select v-model="editForm.state" placeholder="请选择">
                    <el-option v-for="item in stateoptions" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="所属部门">

                <!-- <el-select v-model="editForm_sysDepartment_id" placeholder="请选择">
                    <el-option v-for="item in departmentoptions" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select> -->

                <el-tree :data="departmentoptions" show-checkbox default-expand-all node-key="id" ref="departmenttree" check-strictly @check-change="handleCheckChange" :default-checked-keys="editForm_sysDepartment_ids">
                </el-tree>

            </el-form-item>
            <el-form-item label="审批权限">

                <el-select v-model="editForm_checkids" multiple placeholder="请选择">
                    <el-option v-for="item in checkoptions" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>

            </el-form-item>
            <el-form-item label="功能权限">

                <el-select v-model="editForm_roleids" multiple placeholder="请选择">
                    <el-option v-for="item in roleoptions" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>

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
                dataurl: process.env.BASE_API + 'sysUser/dataList.do',
                saveurl: process.env.BASE_API + 'sysUser/save.do',
                deleteurl: process.env.BASE_API + 'sysUser/delete.do',
                getdataurl: process.env.BASE_API + 'sysUser/getId.do',
                updatepwdurl: process.env.BASE_API + 'sysUser/updatePwd.do',
                roledataurl: process.env.BASE_API + 'sysRole/dataList.do',
                checkdataurl: process.env.BASE_API + 'sysRole/dataList.do',
                departmentdataurl: process.env.BASE_API + 'sysDepartment/dataList.do',
                departmenttreeurl: process.env.BASE_API + 'sysDepartment/getDepartmentTable.do',
                dialogFormVisible: false,
                dialogPwdFormVisible: false,
                editLoading: false,
                editForm: {
                    email: '',
                    multiple: {
                        roleids: true,
                    },
                    abled: {
                        email: true,
                    },
                },
                editForm_checkids: [],
                editForm_roleids: [],
                editForm_sysDepartment_id: '',
                editForm_sysDepartment_ids: [],
                searchForm: {
                    nickname: '',
                    email: '',
                    pageNum: 1,
                    totalRow: 0,
                    pageSize: 10,
                    is_search: false,
                    order: '',
                },
                tableData: [],
                multipleSelection: [],
                stateoptions: [{
                    value: 0,
                    label: '启用'
                }, {
                    value: 1,
                    label: '未启用'
                }],
                checkoptions: [],
                roleoptions: [],
                departmentoptions: [],
                idepartmenttree: 0,
                editPwdForm:{
                    abled: {
                        email: false,
                        nickname: false,
                    }
                },
                pwdRules:{
                    newpwd: [
                        { required: true, message: '请输入新密码', trigger: 'blur' }
                    ],
                    pwdagain: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                },
            }
        },
        created() {
            this.loadData();
            this.loadRoleData();
            this.loadCheckData();
            this.loadDepartData();
        },
        computed: {
            data() {
                const self = this;
                return self.tableData;
            }
        },
        methods: {
                handleCheckChange(data, node) {
                    this.idepartmenttree++;
                    if (this.idepartmenttree % 2 == 0) {
                        this.$refs.departmenttree.setCheckedNodes([]);
                        this.$refs.departmenttree.setCheckedNodes([data]);
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

                        console.log(self.tableData);
                    }).catch(err => {
                        this.$eventHub.$emit('error', err);
                    })
                },
                loadDepartData() {
                    let self = this;
                    http.post(self.departmenttreeurl, {},(res) => {
                        self.departmentoptions = res.data;
                    }).catch(err => {
                        this.$eventHub.$emit('error', err);
                    })
                },
                loadRoleData() {
                    let self = this;
                    http.post(self.roledataurl, {
                        sysflag: 0,
                        pageSize: 1000
                    },(res) => {
                        var dataele;
                        for (var i in res.data.list) {
                            dataele = res.data.list[i];
                            self.roleoptions[i] = {
                                value: dataele.id,
                                label: dataele.rolename
                            };
                        }
                    }).catch(err => {
                        this.$eventHub.$emit('error', err);
                    })
                },
                loadCheckData() {
                    let self = this;
                    http.post(self.checkdataurl, {
                        sysflag: 1,
                        pageSize: 1000
                    },(res) => {
                        var dataele;
                        for (var i in res.data.list) {
                            dataele = res.data.list[i];
                            self.checkoptions[i] = {
                                value: dataele.id,
                                label: dataele.rolename
                            };
                        }
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
                departmentformatter(row, column) {
                    console.log(row);
                    if (!!row.sysDepartment) {
                        return row.sysDepartment.name
                    }
                    return '';
                },
                handleAdd() {
                    this.dialogFormVisible = true;
                    this.editForm = {
                        abled: {
                            email: true,
                        }
                    };
                    this.editForm_checkids = [];
                    this.editForm_roleids = [];
                },
                handlePwdEdit(index, row) {
                    this.dialogPwdFormVisible=true;
                    //this.editPwdForm = Object.assign({}, row);
                    this.editPwdForm = {
                      id:row.id,
                      email:row.email,
                      nickname:row.nickname,
                    }
                    this.editPwdForm.abled = {
                      email: false,
                      nickname: false,
                    };
                },
                handleEdit(index, row) {
                    this.dialogFormVisible = true;
                    this.editForm = Object.assign({}, row);
                    if (!this.editForm.abled) {
                        this.editForm.abled = {};
                    }
                    this.editForm.abled.email = false;
                    http.post(this.getdataurl + '?id=' + this.editForm.id, {},response => {
                        console.log(response.data.roleids);
                        console.log(response.data.checkids);
                        // for(var i in response.data.roleids){
                        //   this.editForm_roleids[i] = response.data.roleids[i];
                        // }
                        // for(var i in response.data.roleids){
                        //   this.editForm_checkids[i] = response.data.checkids[i];
                        // }
                        this.editForm_roleids = response.data.roleids;
                        this.editForm_checkids = response.data.checkids;
                        if (!!response.data.sysDepartment) {
                            this.editForm_sysDepartment_id = response.data.sysDepartment.id;
                            this.editForm_sysDepartment_ids = [response.data.sysDepartment.id];
                            this.$refs.departmenttree.setCheckedKeys(this.editForm_sysDepartment_ids);
                        }
                        return;
                    }).catch(err => {
                        this.$eventHub.$emit('error', err);
                    })
                },
                handleDelete(index, row) {
                    const self = this;
                    console.log(row);
                    this.$confirm('确定删除这个用户？', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
						}).then(() => {
							http.post(self.deleteurl, {
                        	id: row.id,
		                    },response => {
		                        this.loadData();
		                        return;
		                    }).catch(err => {
		                        console.log(err);
		                        self.$alert(err, '告警', {
		                            confirmButtonText: '确定'
		                        });
		                    }).catch(err => {
		                        this.$eventHub.$emit('error', err);
		                    })
						}).catch(() => {

						});




                },
                handlePwdSave(){
                  let self = this;
                  if(this.editPwdForm.newpwd!=this.editPwdForm.pwdagain){
                    this.$message.error({
                          title: '错误',
                          message: '两次密码输入不正确'
                    });
                    return;
                  }

                  self.$refs['editPwdForm'].validate((valid) => {
                      if (valid) {
                        http.post(self.updatepwdurl, {
                            id: self.editPwdForm.id,
                            newpwd: self.editPwdForm.newpwd,
                            pwd:self.editPwdForm.pwd,
                        },response => {
                            console.log(response);
                            if(!response.data.success){
                              self.$message.error({
                                    title: '错误',
                                    message:response.data.msg
                              });
                            }else{
                              this.loadData();
                              this.dialogPwdFormVisible = false;
                            }
                            return;
                        }).catch(err => {
                            this.$eventHub.$emit('error', err);
                        })
                      }
                  });

                },
                handleSave() {
                    const self = this;
                    //console.log(self.editForm_sysDepartment_ids);
                    //console.log(self.editForm_checkids);
                    //console.log(self.editForm_roleids);
                    var sysDepartment_checkedKeys = self.$refs.departmenttree.getCheckedKeys();
                    var sysDepartment_id;
                    if (!!sysDepartment_checkedKeys && sysDepartment_checkedKeys.length > 0) {
                        sysDepartment_id = sysDepartment_checkedKeys[0];
                    }
                    http.post(self.saveurl, {
                        id: self.editForm.id,
                        email: self.editForm.email,
                        nickname: self.editForm.nickname,
                        state: self.editForm.state,
                        checkids: self.editForm_checkids,
                        roleids: self.editForm_roleids,
                        //sysDepartment:{id:self.editForm_sysDepartment_id},
                        sysDepartment: {
                            id: sysDepartment_id
                        },
                    },response => {
                        this.loadData();
                        this.dialogFormVisible = false;
                        return;
                    }).catch(err => {
                        this.$eventHub.$emit('error', err);
                    })
                },
                handleSelectionChange(val) {
                    this.multipleSelection = val;
                },
                handleReset(){
                  this.searchForm.is_search = false;
                  this.searchForm = {
      		    		email: '',
  	    			    nickname: '',

                      pageNum: 1,
                      totalRow: 0,
                      pageSize: 10,
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
