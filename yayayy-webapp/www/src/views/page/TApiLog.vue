<style scoped>

.handle-box {
    margin-bottom: 20px;
    line-height:40px;
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
    <div class="handle-box" >

		<el-input v-model="searchForm.user_id" placeholder="登录用户" class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.api_url" placeholder="接口地址" class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.api_json" placeholder="接口内容" class="handle-input mr10"></el-input>

		<el-input v-model="searchForm.ret_json" placeholder="接口返回内容" v-show="expandAll" class="handle-input mr10"></el-input>
		<el-date-picker v-model="searchForm.time_s":editable="false" type="datetime" v-show="expandAll" placeholder="调用时间（起）"></el-date-picker>
		<el-date-picker v-model="searchForm.time_e":editable="false" type="datetime" v-show="expandAll" placeholder="调用时间（终）"></el-date-picker>
		<el-button-group>
	        <el-button type="primary" @click="handleSearch">搜索</el-button>
	        <el-button type="primary" @click="handleReset">重置</el-button>
	    </el-button-group> 

    <a style="margin-left: 8px;font-size:10px;color:#409EFF;" v-show="!expandAll"  @click="expandSearchForm">展开<i  class="el-icon el-icon-arrow-down" aria-hidden="true"></i></a>
    <a style="margin-left: 8px;font-size:10px;color:#409EFF;" v-show="expandAll"  @click="collapseSearchForm">收回<i  class="el-icon el-icon-arrow-up" aria-hidden="true"></i></a>

    </div>
    <el-table :data="data" border style="width: 100%" ref="multipleTable" @sort-change="handleSort">

	     <el-table-column type="expand">
	      <template slot-scope="props">
	        <el-form label-position="left" inline class="demo-table-expand">
	          <el-form-item label="接口内容">
	            <span>{{ props.row.api_json }}</span>
	          </el-form-item>
	          <el-form-item label="接口返回内容">
	            <span>{{ props.row.ret_json }}</span>
	          </el-form-item>
	        </el-form>
	      </template>
	    </el-table-column>

        <el-table-column prop="user_id" label="登录用户" width="150">
        </el-table-column>


        <el-table-column prop="api_url" label="接口地址"  min-width="180">
        </el-table-column>


        <el-table-column prop="ajson_str" label="接口内容" min-width="180">
        </el-table-column>


        <el-table-column prop="call_time" label="调用时间" width="180">
        </el-table-column>

        <el-table-column prop="rjson_str" label="接口返回内容" min-width="200">
        </el-table-column>

	    <el-table-column prop="ret_time" label="返回时间" width="180">
        </el-table-column>


    </el-table>
    <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="searchForm.totalRow">
        </el-pagination>
    </div>

    <el-dialog title="编辑接口日志表" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px">
        		<input v-model="editForm.id" type="hidden"></input>

    		    			    	            <el-form-item label="登录用户">
              <el-input v-model="editForm.user_id" :disabled="!editForm.abled.user_id"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="接口地址">
              <el-input v-model="editForm.api_url" :disabled="!editForm.abled.api_url"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="接口内容">
              <el-input v-model="editForm.api_json" ></el-input>
            </el-form-item>
	    			    	            <el-form-item label="调用时间">
              <el-input v-model="editForm.call_time" :disabled="!editForm.abled.call_time"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="接口返回内容">
              <el-input v-model="editForm.ret_json" :disabled="!editForm.abled.ret_json"></el-input>
            </el-form-item>
	    			    	            <el-form-item label="返回时间">
              <el-input v-model="editForm.ret_time" :disabled="!editForm.abled.ret_time"></el-input>
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
                getdataurl: process.env.BASE_API + 'tApiLog/getId.do',
                dataurl: process.env.BASE_API + 'tApiLog/dataList.do',
                saveurl: process.env.BASE_API + 'tApiLog/save.do',
                deleteurl: process.env.BASE_API + 'tApiLog/delete.do',
                dialogFormVisible: false,
                editLoading: false,
                expandAll:false,
                editForm: {
    		    	user_id: '',
	    			api_url: '',
	    			api_json: '',
	    			call_time: '',
	    			ret_json: '',
	    			ret_time: '',
	    		    abled: {

    		    		user_id: true,
	    				api_url: true,
	    			    api_json: true,
	    			    call_time: true,
	    			    ret_json: true,
	    			    ret_time: true,
	    			}
                },
                editForm_sysMenu_ids:[],
                searchForm: {
                  
    		    	user_id: '',
	    			api_url: '',
	    			api_json: '',
	    			call_time: '',
	    			ret_json: '',
	    			ret_time: '',
	    			time_s: '',
	    			time_e: '',

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
                        
                        for (var i = 0; i < self.tableData.length; i++) {
                     		if( self.tableData[i].call_time!=null){
                  				self.tableData[i].call_time = fdate(self.tableData[i].call_time);
                   			}
                     		if( self.tableData[i].ret_time!=null){
                       			self.tableData[i].ret_time = fdate(self.tableData[i].ret_time);
                         	}
                        }
                        
                        this.searchForm.is_search = false;
                        console.log("tableData=",self.tableData);
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
                expandSearchForm() {
                    this.expandAll=true;
                },
                collapseSearchForm() {
                    this.expandAll=false;
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
    		    			    		user_id: '',
	    			    				api_url: '',
	    			    				api_json: '',
	    			    				call_time: '',
	    			    				ret_json: '',
	    			    				ret_time: '',
	    		                        abled: {
    		    			    		user_id: true,
	    			    				api_url: true,
	    			    				api_json: false,
	    			    				call_time: true,
	    			    				ret_json: true,
	    			    				ret_time: true,
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
    		    			    		self.editForm.user_id=response.data.user_id;
	    			    				self.editForm.api_url=response.data.api_url;
	    			    				self.editForm.api_json=response.data.api_json;
	    			    				self.editForm.call_time=response.data.call_time;
	    			    				self.editForm.ret_json=response.data.ret_json;
	    			    				self.editForm.ret_time=response.data.ret_time;
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
                 handleReset(){
                  this.searchForm.is_search = false;
                  this.searchForm = {
      		    	user_id: '',
	    			api_url: '',
	    			api_json: '',
	    			call_time: '',
	    			ret_json: '',
	    			ret_time: '',
	    			time_s: '',
	    			time_e: '',


                      is_search: false,
                      order: '',
                  }
                },
                handleSave() {
                    const self = this;

                    http.post(self.saveurl, {
    				id:self.editForm.id,
				user_id:self.editForm.user_id,
				api_url:self.editForm.api_url,
				api_json:self.editForm.api_json,
				call_time:self.editForm.call_time,
				ret_json:self.editForm.ret_json,
				ret_time:self.editForm.ret_time,
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
