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
        <el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
    </div>

    <tree-table :data="dataSource" :columns="columns" :expandAll="treeParams.defaultExpandAll" border :handleSelectionChange="handleSelectionChange">
        <el-table-column label="操作" width="200">
          <template slot-scope="scope">
            <el-button size="small" @click="handleTreeEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleTreeDel(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
    </tree-table>

    <el-dialog title="编辑菜单信息" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px">
            <el-form-item label="菜单名称">
                <el-input v-model="editForm.name" width="150" ></el-input>
                <input v-model="editForm.id" type="hidden"></input>
                <input v-model="editForm.parentid" type="hidden"></input>
            </el-form-item>
            <el-form-item label="菜单编码">
                <el-input v-model="editForm.code"></el-input>
            </el-form-item>
            <el-form-item label="链接">
                <el-input v-model="editForm.url"></el-input>
            </el-form-item>
            <el-form-item label="图标">
                <el-input v-model="editForm.icon"></el-input>
            </el-form-item>
            <el-form-item label="排序">
                <el-input v-model="editForm.rank"></el-input>
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
    name: 'Menus',
    data() {
        return {
            dataurl: process.env.BASE_API+'sysMenu/getMenusTable.do',
            saveurl: process.env.BASE_API+'sysMenu/save.do',
            deleteurl: process.env.BASE_API+'sysMenu/delete.do',
            dialogFormVisible: false,
            editForm: {
                name: '',
                url: '',
                rank: 1,
            },
            editLoading: false,
            columns: [{
                text: '菜单名称',
                value: 'name',
                dataIndex: 'name'
            }, {
                text: '菜单编码',
                value: 'code',
                dataIndex: 'code'
            }, {
                text: '链接',
                value: 'url',
                dataIndex: 'url'
            }, {
                text: '图标',
                value: 'icon',
                dataIndex: 'icon'
            }
            // , {
            //     text: '排序',
            //     value: 'rank',
            //     dataIndex: 'rank'
            // }
            ],
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
                    url: self.editForm.url,
                    parentid: self.editForm.parentid,
                    icon: self.editForm.icon
                },response => {
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
                const self = this;
                 this.$confirm('确定删除此条菜单？', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
						}).then(() => {
							http.post(self.deleteurl, {
                    		id: row.id
			                },response => {
			                    this.loadData();
			                    this.dialogFormVisible = false;
			                    return;
			                }).catch(err => {
			                    this.$eventHub.$emit('error', err);
			                })
						}).catch(() => {

						});

            },
            handleSelectionChange: function(val) {
                //console.log(val);
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
