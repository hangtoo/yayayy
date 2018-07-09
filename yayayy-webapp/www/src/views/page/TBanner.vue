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
        <el-button type="primary"  icon="el-icon-plus" @click="handleAdd">新增</el-button>
    </div>
    <el-table :data="data" border style="width: 100%" ref="multipleTable" @sort-change="handleSort">

    	 <el-table-column type="index" label="序号" width="80" align="center">
        </el-table-column>

        <el-table-column prop="banner_name" label="banner名称" align="center" min-width="200">
        </el-table-column>

        <el-table-column prop="banner_html" label="关联HTML" align="center" min-width="200">
        </el-table-column>

        <el-table-column prop="banner_pic" label="banner图片" align="center"  min-width="200">
			  <template slot-scope="scope"  >
                    <img :src="scope.row.banner_pic" width="200"  />
                </template>
        </el-table-column>

        <el-table-column prop="createtime" label="创建时间" align="center" width="180">
        </el-table-column>

	    <el-table-column  label="顺序" width="150" align="center">
        	<template slot-scope="scope">
                <el-button type="text" @click="handleUp(scope.$index, scope.row)"  v-if="scope.row.banner_order!=='1'">上移</el-button>
                <el-button type="text" @click="handleDown(scope.$index, scope.row)"  v-if="scope.row.banner_order!==(scope.row.tote+'')" >下移</el-button>
                <el-button type="text" @click="handleTop(scope.$index, scope.row)"  v-if="scope.row.banner_order!=='1'">置顶</el-button>
            </template>
        </el-table-column>

        <el-table-column label="操作" width="150">
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

	<el-dialog title="Banner" :visible.sync="dialogFormVisible" size="tiny">
		<el-form ref="editForm" :model="editForm" label-width="90px" v-loading="lookloading">

			<input v-model="editForm.id" type="hidden"></input>

			<el-form-item label="banner名称">
				<el-input v-model="editForm.banner_name" ></el-input>
            </el-form-item>

			<el-form-item label="关联图片">
				<div class="imgSrc">
				<span style="white-space:pre;"> </span><img :src="imgDataUrl"  width="200" >
				</div>
				<div class="a-upload">
    				<input type="file" name="file" class="upload__input" @change="uploadChange" accept="image/png,image/jpeg">选择文件上传
				</div>
            </el-form-item>

			<el-form-item label="关联HTML">
				<el-input v-model="editForm.banner_html" ></el-input>
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
                getdataurl: process.env.BASE_API + 'tBanner/getId.do',
                dataurl: process.env.BASE_API + 'tBanner/dataList.do',
                saveurl: process.env.BASE_API + 'tBanner/save.do',
                orderupurl: process.env.BASE_API + 'tBanner/orderUp.do',
                orderdownurl: process.env.BASE_API + 'tBanner/orderDown.do',
                ordertopurl: process.env.BASE_API + 'tBanner/orderTop.do',
                deleteurl: process.env.BASE_API + 'tBanner/delete.do',
                dialogFormVisible: false,
                editLoading: false,
                lookloading: false,
                imgDataUrl:'',
                editForm: {
						banner_name: '',
						banner_pic: '',
						banner_html: '',
						banner_order: '',
						createtime: '',
						pic_base: '',
						tota: '',
						abled: {
							banner_name: false,
							banner_pic: false,
							banner_html: false,
							banner_order: false,
							createtime: false,
						}
                },
                editForm_sysMenu_ids:[],
                searchForm: {
					banner_name: '',
					banner_pic: '',
					banner_html: '',
					banner_order: '',
					createtime: '',
					tota: '',
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
							self.tableData[i].banner_pic = 'http://img-demo.oss-cn-hangzhou.aliyuncs.com/'+self.tableData[i].banner_pic+'?x-oss-process=image/resize,w_200';
                        
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
						self.lookloading=false;
						this.imgDataUrl='';
	                    this.editForm = {
    		    			    		banner_name: '',
	    			    				banner_pic: '',
	    			    				banner_html: '',
	    			    				banner_order: '',
	    			    				createtime: '',
	    			    				tota: '',
	    		                        abled: {
    		    			    				banner_name: false,
	    			    				banner_pic: false,
	    			    				banner_html: false,
	    			    				banner_order: false,
	    			    				createtime: false,
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
    		    		self.editForm.banner_name=response.data.banner_name;
	    			    self.editForm.banner_pic=response.data.banner_pic;
	    			    this.imgDataUrl='http://img-demo.oss-cn-hangzhou.aliyuncs.com/'+response.data.banner_pic+'?x-oss-process=image/resize,w_200';
	    			  	self.editForm.banner_html=response.data.banner_html;
	    			    self.editForm.banner_order=response.data.banner_order;
	    			    self.editForm.createtime=response.data.createtime;
	    		        return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })

                },
                handleUp(index, row) {
                    const self = this;
                    if(row.banner_order=="1"){
                    	this.$message.error({
                        	title: '错误',
                        	message:'已经在最上,无法上移',
                        });
                    }else{
	                    http.post(self.orderupurl, {
	                        id: row.id,
	                        banner_order: row.banner_order,
	                    },response => {
	                        self.loadData();
	                        return;
	                    }).catch(err => {
	                        self.$eventHub.$emit('error', err);
	                    })
                    }



                },
                handleTop(index, row) {
               		let self = this;
	            	http.post(self.ordertopurl, {
	                 	id: row.id,
	                  	banner_order: row.banner_order,
	                  	tote:self.tableData.length,
	            	},response => {
	                  	self.loadData();
	               		return;
	             	}).catch(err => {
	                   	self.$eventHub.$emit('error', err);
	             	})
               



                },
                handleDown(index, row) {
                    const self = this;
                    console.log(row);
                    if(row.banner_order==self.tableData.length){
                    	this.$message.error({
                        	title: '错误',
                        	message:'已经在最下,无法下移',
                        });
                    }else{
	                    http.post(self.orderdownurl, {
	                        id: row.id,
	                        banner_order: row.banner_order,
	                    },response => {
	                        self.loadData();
	                        return;
	                    }).catch(err => {
	                        self.$eventHub.$emit('error', err);
	                    })
                    }
                },
                handleDelete(index, row) {
                    const self = this;
                    this.$confirm('确定删除此条Banner？', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {
					http.post(self.deleteurl, {
	                  	id: row.id,
	                  	banner_order: row.banner_order,
	                  	tote:self.tableData.length,
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
                    if(self.editForm.banner_order==''||self.editForm.banner_order==null){
                    	self.editForm.banner_order=1;
                    }

                    http.post(self.saveurl, {
    					id:self.editForm.id,
						banner_name:self.editForm.banner_name,
						banner_pic:self.editForm.banner_pic,
						banner_html:self.editForm.banner_html,
						banner_order:self.editForm.banner_order,
						pic_base:self.editForm.pic_base,
						tote:self.tableData.length,
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
                     	this.lookloading=false;
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
		                        //this.imgUrl =
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
