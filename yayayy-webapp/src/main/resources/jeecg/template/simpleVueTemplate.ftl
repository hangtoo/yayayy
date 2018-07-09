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
#set($dollar = "$") 
<div class="app-container calendar-list-container">

    <div class="handle-box">
        <el-button type="primary" icon="el-icon-plus" @click="handleAdd">{{$t('table.add')}}</el-button>
        
    	#foreach($po in $!{columnDatas})
	    	#if  ($po.columnName !='id')
	    		<el-input v-model="searchForm.${po.columnName}" placeholder="${po.columnComment}" class="handle-input mr10"></el-input>
	    	#end
	#end
	
	    <el-button-group>
	        <el-button type="primary"  @click="handleSearch">{{$t('table.search')}}</el-button>
	        <el-button type="primary"  @click="handleReset">{{$t('table.reset')}}</el-button>
        </el-button-group> 
    </div>
    
    <el-table :data="data" border style="width: 100%" ref="multipleTable" @sort-change="handleSort">
    
    	#foreach($po in $!{columnDatas})
	    	#if  ($po.columnName !='id')
	    	
        <el-table-column prop="${po.columnName}" label="${po.columnComment}">
        </el-table-column>
	    	
	    	#end
	#end

        <el-table-column :label="$t('table.actions')" width="150">
            <template slot-scope="scope">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)">{{$t('table.edit')}}</el-button>
                <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">{{$t('table.delete')}}</el-button>
            </template>
        </el-table-column>
    </el-table>
    <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="searchForm.totalRow">
        </el-pagination>
    </div>

    <el-dialog :title="$t('table.edit')+'${codeName}'" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px">
        		<input v-model="editForm.id" type="hidden"></input>
        		
    	#foreach($po in $!{columnDatas})
	    	#if  ($po.columnName !='id')
            <el-form-item label="${po.columnComment}">
              <el-input v-model="editForm.${po.columnName}" :disabled="!editForm.abled.${po.columnName}"></el-input>
            </el-form-item>
	    	#end
	#end

            <el-form-item>
                <el-button type="primary" @click="handleSave" :loading="editLoading">{{$t('table.confirm')}}</el-button>
                <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
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
                getdataurl: process.env.BASE_API + '${lowerName}/getId.do',
                dataurl: process.env.BASE_API + '${lowerName}/dataList.do',
                saveurl: process.env.BASE_API + '${lowerName}/save.do',
                deleteurl: process.env.BASE_API + '${lowerName}/delete.do',
                dialogFormVisible: false,
                editLoading: false,
                editForm: {
    	#foreach($po in $!{columnDatas})
	    	#if  ($po.columnName !='id')
			${po.columnName}: '',
	    	#end
	#end
                    abled: {

    	#foreach($po in $!{columnDatas})
	    	#if  ($po.columnName !='id')
			${po.columnName}: true,
	    	#end
	#end
                    }
                },
                editForm_sysMenu_ids:[],
                searchForm: {
    	#foreach($po in $!{columnDatas})
	    	#if  ($po.columnName !='id')
			${po.columnName}: '',
	    	#end
	#end
                    
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
                        this.${dollar}eventHub.${dollar}emit('error', err);
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
    	#foreach($po in $!{columnDatas})
	    	#if  ($po.columnName !='id')
			${po.columnName}: '',
	    	#end
	#end
                        abled: {
    	#foreach($po in $!{columnDatas})
	    	#if  ($po.columnName !='id')
			${po.columnName}: true,
	    	#end
	#end
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
    	#foreach($po in $!{columnDatas})
	    	#if  ($po.columnName !='id')
			self.editForm.${po.columnName}=response.data.${po.columnName};
	    	#end
	#end
                        return;
                    }).catch(err => {
                        self.${dollar}eventHub.${dollar}emit('error', err);
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
                        self.${dollar}eventHub.${dollar}emit('error', err);
                    })
                },
                handleSave() {
                    const self = this;

                    http.post(self.saveurl, {
    	#foreach($po in $!{columnDatas})
			${po.columnName}:self.editForm.${po.columnName},
	#end
                    },response => {
                        self.loadData();
                        self.dialogFormVisible = false;
                        return;
                    }).catch(err => {
                        self.${dollar}eventHub.${dollar}emit('error', err);
                    })
                },
                handleReset(){
                  this.searchForm.is_search = false;
                  this.searchForm = {
		    	#foreach($po in $!{columnDatas})
			    	#if  ($po.columnName !='id')
					${po.columnName}: '',
			    	#end
			#end
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
