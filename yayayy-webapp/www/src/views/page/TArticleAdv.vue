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
.a-upload {
    padding: 4px 10px;
    height: 28px;
    line-height: 20px;
    position: relative;
    cursor: pointer;
    color: #888;
    background: #fafafa;
    border: 1px solid #ddd;
    border-radius: 4px;
    overflow: hidden;
    display: inline-block;
    *display: inline;
    *zoom: 1
}

.a-upload  input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
    filter: alpha(opacity=0);
    cursor: pointer
}

.a-upload:hover {
    color: #444;
    background: #eee;
    border-color: #ccc;
    text-decoration: none
}
</style>

<template>
<div class="app-container calendar-list-container">

    <div class="handle-box">
        <el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
        <!--
		<el-input v-model="searchForm.adv_name" placeholder="adv名称" class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.adv_pic" placeholder="adv图片地址" class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.adv_html" placeholder="adv关联HTML" class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.show_flag" placeholder="是否显示(0.否  1.是 )" class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.createtime" placeholder="创建时间" class="handle-input mr10"></el-input>
		<el-button type="primary" icon="search" @click="handleSearch">搜索</el-button>
    	-->
    </div>
    <el-table :data="data" border style="width: 100%" ref="multipleTable" @sort-change="handleSort">
    
    		    			    		    	
        <el-table-column prop="adv_name" label="广告名称" align="center" min-width="200">
        </el-table-column>
	    	
	    			    		    	
    
	    			    		    	
        <el-table-column prop="adv_html" label="关联HTML" align="center" min-width="200">
        </el-table-column>
        
          <el-table-column prop="adv_pic" label="Adv图片" align="center"  min-width="300">
			  <template slot-scope="scope"  >
                    <img :src="scope.row.adv_pic" width="300"  />
                </template>
        </el-table-column>
	    	
	    			    		    	
        <el-table-column prop="show_flag" label="激活状态"  align="center" min-width="120">
        </el-table-column>
	    	
	    			    		    	
        <el-table-column prop="createtime" label="创建时间" align="center" width="180">
        </el-table-column>
	    	
	    		
        <el-table-column label="操作" width="160">
            <template slot-scope="scope">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    
    <!--
    <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="searchForm.totalRow">
        </el-pagination>
    </div>
    -->

    <el-dialog title="广告编辑" :visible.sync="dialogFormVisible" size="tiny">
        <el-form ref="editForm" :model="editForm" label-width="80px" v-loading="lookloading">
        
			<input v-model="editForm.id" type="hidden"></input>
        		
    		<el-form-item label="广告名称">
          		<el-input v-model="editForm.adv_name"></el-input>
            </el-form-item>
            
            <el-form-item label="广告图片">
				<div class="imgSrc">
				<span style="white-space:pre;"> </span><img :src="imgDataUrl"  width="200" >
				</div>
				<div class="a-upload">
    				<input type="file" name="file" class="upload__input" @change="uploadChange" accept="image/png,image/jpeg">选择文件上传
				</div>
            </el-form-item>
            
	    	<el-form-item label="关联HTML">
      			<el-input v-model="editForm.adv_html" ></el-input>
            </el-form-item>
            
	    	<el-form-item label="是否激活">
         		<el-switch
				    v-model="editForm.show_flag"
				     active-value="1"
					 inactive-value="0">
				</el-switch>
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
                getdataurl: process.env.BASE_API + 'tArticleAdv/getId.do',
                dataurl: process.env.BASE_API + 'tArticleAdv/dataList.do',
                saveurl: process.env.BASE_API + 'tArticleAdv/save.do',
                deleteurl: process.env.BASE_API + 'tArticleAdv/delete.do',
                dialogFormVisible: false ,
                editLoading: false,
                lookloading: false,
                imgDataUrl:'',
                editForm: {
    		    			    				adv_name: '',
	    			    				adv_pic: '',
	    			    				adv_html: '',
	    			    				show_flag: '',
	    			    				createtime: '',
	    		                    abled: {

    		    			    				adv_name: true ,
	    			    				adv_pic: true,
	    			    				adv_html: true,
	    			    				show_flag: true,
	    			    				createtime: true,
	    		                    }
                },
                editForm_sysMenu_ids:[],
                searchForm: {
    		    			    				adv_name: '',
	    			    				adv_pic: '',
	    			    				adv_html: '',
	    			    				show_flag: '',
	    			    				createtime: '',
	    		                    
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
                        	let show_flag = self.tableData[i].show_flag;
                            if("0"==show_flag){
                              self.tableData[i].show_flag="";
                            }
                            if("1"==show_flag){
                              self.tableData[i].show_flag="激活中";
                            }
                        
							self.tableData[i].adv_pic = 'http://img-demo.oss-cn-hangzhou.aliyuncs.com/'+self.tableData[i].adv_pic+'?x-oss-process=image/resize,w_300';
                        
                        	if( self.tableData[i].createtime!=null){
                            	self.tableData[i].createtime = fdate(self.tableData[i].createtime);
                       		}
                        }
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
                	let self = this;
                	if(self.tableData.length=='9'){
						this.$message.error({
                        	title: '错误',
                        	message:'Banner个数不能超过10个',
                        });

					}else{
						this.dialogFormVisible = true;
	                    this.imgDataUrl='';
	                    self.lookloading=false;
	                    this.editForm = {
	    		  			adv_name: '',
		    				adv_pic: '',
		    				adv_html: '',
		    				show_flag: '',
		    				createtime: '',
		    		      	abled: {
	    		    			adv_name: true,
		    			    	adv_pic: true,
		    			    	adv_html: true,
		    			    	show_flag: true,
		    			   		createtime: true,
		    		     	}
	                    };
					}
                	
                	
                    
                },
                handleEdit(index, row) {
                    let self = this;
                    self.dialogFormVisible = true;
                    self.lookloading=false;
                    self.editForm = Object.assign({}, row);
                    if (!self.editForm.abled) {
                        self.editForm.abled = {};
                    }
                    self.editForm.abled.rolecode = false;
                    http.post(self.getdataurl + '?id=' + self.editForm.id, {},response => {
    		    			    		self.editForm.adv_name=response.data.adv_name;
	    			    				self.editForm.adv_pic=response.data.adv_pic;
	    			    				this.imgDataUrl='http://img-demo.oss-cn-hangzhou.aliyuncs.com/'+response.data.adv_pic+'?x-oss-process=image/resize,w_200';
	    			    				self.editForm.adv_html=response.data.adv_html;
	    			    				self.editForm.show_flag=response.data.show_flag;
	    			    				self.editForm.createtime=response.data.createtime;
	    		                        return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })

                },
                handleDelete(index, row) {
                    const self = this;
                    this.$confirm('确定删除此条广告？', '提示', {
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
					this.lookloading=true;
                    http.post(self.saveurl, {
    				id:self.editForm.id,
						adv_name:self.editForm.adv_name,
						adv_pic:self.editForm.adv_pic,
						adv_html:self.editForm.adv_html,
						show_flag:self.editForm.show_flag,
						createtime:self.editForm.createtime,
						pic_base:self.editForm.pic_base,
	       			},response => {
                       this.lookloading=false;
	                	if(response.status==200&&!!response.data&&response.data.statusCode==200){
		                	self.loadData();
	                        self.dialogFormVisible = false;
	                        return;
	                	}else{
	                	 	this.$message.error({
                            	title: '错误',
                            	message: response.data.msg
                            });
	                	}
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })
                },
                uploadChange(event){
					const self = this;

				    if(event.target.files.length>0){
				        this.files = event.target.files[0];  //提交的图片
				        this.getBase64(event.target,(url)=>{
							this.imgDataUrl = 'data:image/png;base64,'+url;   //显示的图片
							self.editForm.pic_base = url;
				        });
				    }

                },
                getBase64(file,callback){

		            if(file.files && file.files[0]){
		                var thisFile = file.files[0];
		                if(thisFile.size>2019200){
		                    // mualert.alertBox("图片不能超过800K");
		                    alert("图片不能超过2M");
		                    return
		                };
		                var reader = new FileReader();
		                reader.onload = function(event){
		                    var imgUrl = event.target.result;
		                    var img = new Image();
		                    img.onload = function(){
		                        var canvasId = 'canvasBase64Imgid',
		                        canvas = document.getElementById(canvasId);
		                        if(canvas!=null){document.body.removeChild(canvas);}
		                        var canvas = document.createElement("canvas");
		                        canvas.innerHTML = 'New Canvas';
		                        canvas.setAttribute("id", canvasId);
		                        canvas.style.display='none';
		                        document.body.appendChild(canvas);
		                        canvas.width = this.width;
		                        canvas.height = this.height;
		                        var imageWidth = this.width,
		                        imageHeight = this.height;

		                        var context = canvas.getContext('2d');
		                        context.clearRect(0, 0, imageWidth, imageHeight);
		                        context.drawImage(this, 0, 0, imageWidth, imageHeight);
		                        var base64 = canvas.toDataURL('image/png',1);
		                        var imgbase = base64.substr(22);
		                        callback(imgbase)
		                    }
		                    img.src = imgUrl;
		                }
		                reader.readAsDataURL(file.files[0]);
		            }
				},
                handleFalse() {
                    return false;
                }
        }
}

</script>
