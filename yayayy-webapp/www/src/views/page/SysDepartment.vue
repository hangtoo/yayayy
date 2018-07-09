<style scoped>

.search-box {
    margin-bottom: 0px;
}

.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}

</style>

<template>

<div class="app-container calendar-list-container">

    <div class="handle-box">
        <el-button type="primary"  icon="el-icon-plus" @click="handleAdd">新增</el-button>
    </div>

<tree-table :data="dataSource" :columns="columns" :expandAll="treeParams.defaultExpandAll" border :handleSelectionChange="handleSelectionChange">
    <el-table-column label="操作" width="200">
      <template slot-scope="scope">
        <el-button size="small" @click="handleTreeEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button size="small" type="danger" @click="handleTreeDel(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
</tree-table>

    <el-dialog title="编辑部门信息" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px">
            <el-form-item label="部门名称">
                <el-input v-model="editForm.name"></el-input>
                <input v-model="editForm.id" type="hidden"></input>
                <input v-model="editForm.parentid" type="hidden"></input>
            </el-form-item>
            <el-form-item label="部门编码">
                <el-input v-model="editForm.code"></el-input>
            </el-form-item>
            <el-form-item label="部门描述">
                <el-input v-model="editForm.descr"></el-input>
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

import treeTable from '@/components/TreeTable'
import http from '@/utils/http';
export default {
    name: 'Department',
    data() {
        return {
            dataurl: process.env.BASE_API+'sysDepartment/getDepartmentTable.do',
            saveurl: process.env.BASE_API+'sysDepartment/save.do',
            deleteurl: process.env.BASE_API+'sysDepartment/delete.do',
            dialogFormVisible: false,
            editForm: {
                name: '',
                url: '',
                rank: 1,
            },
            editLoading: false,
            columns: [{
                text: '部门名称',
                value: 'name',
                dataIndex: 'name'
            }, {
                text: '部门编码',
                value: 'code',
                dataIndex: 'code'
            }, {
                text: '部门描述',
                value: 'descr',
                dataIndex: 'descr'
            }],
            dataSource: [],
            treeParams: {
                requestUrl: "menu",
                treeType: "normal",
                defaultExpandAll: true,
                table_id: null,
            },
        }
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
                const self = this;
                //console.log("created");
                http.post(self.dataurl, {},response => {
                    console.log(response);
                    this.dataSource = [];
                    for (let i = 0; i < response.data.length; i++) {
                        this.dataSource.push(response.data[i]);
                    }
                    return;
                }).catch(err => {
                    this.$eventHub.$emit('error', err);
                })
            },
            handleAdd() {
                this.dialogFormVisible = true;
                if (!!this.treeParams.table_id) {
                    this.editForm = {
                        parentid: this.treeParams.table_id
                    };
                } else {
                    this.editForm = {};
                }
            },
            handleSave() {
                const self = this;
                http.post(self.saveurl, {
                    id: self.editForm.id,
                    name: self.editForm.name,
                    code: self.editForm.code,
                    parentid: self.editForm.parentid,
                    descr: self.editForm.descr
                },response => {
                	this.$message({
            			message: response.data.msg
          			});
                    this.loadData();
                    this.dialogFormVisible = false;
                    return;
                }).catch(err => {
                    this.$eventHub.$emit('error', err);
                })
            },
            handleTreeEdit: function(index, row) {
                //console.log(index);
                //console.log(row);
                this.dialogFormVisible = true;
                this.editForm = Object.assign({}, row);
                this.table_index = index;
            },

            handleTreeDel: function(index, row) {
            this.$confirm('此操作将永久删除该权限, 是否继续?', '提示', {
          			confirmButtonText: '确定',
          			cancelButtonText: '取消',
         			type: 'warning'
        		}).then(() => {

          			const self = this;
                http.post(self.deleteurl, {
                    id: row.id
                },response => {
                    this.loadData();
                    this.dialogFormVisible = false;
                    return;
                    this.$message({
            			type: 'success',
            			message: '删除成功!'
          			});
                }).catch(err => {
                    this.$eventHub.$emit('error', err);
                })
       			}).catch(() => {
          			this.$message({
            			type: 'info',
            			message: '已取消删除'
          			});
        			});

            },
            handleSelectionChange: function(val) {
                console.log(val);
                if (val instanceof Array) {
                    if (val.length == 0) {
                        this.treeParams.table_id = null;
                    }
                    for (var i in val) {
                        if (i == 0) {
                            this.treeParams.table_id = val[i].id;
                        } else if (this.treeParams.table_id == val[i].parentid) {
                            this.treeParams.table_id = val[i].id;
                        }
                    }
                    //console.log(this.treeParams.table_id);
                }
            }
    },
    components: {
        treeTable
    }
}

</script>
