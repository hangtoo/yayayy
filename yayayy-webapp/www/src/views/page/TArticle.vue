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
        <el-button type="primary"  icon="el-icon-plus"  @click="handleAdd">新增</el-button>
        <!--
		<el-input v-model="searchForm.title" placeholder="头条名称" class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.invest_link" placeholder="关联理财产品" class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.publishtime" placeholder="发布时间" class="handle-input mr10"></el-input>
		<el-input v-model="searchForm.createtime" placeholder="创建时间" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="handleSearch">搜索</el-button>
        -->
    </div>
    <el-table :data="data" border style="width: 100%" ref="multipleTable" @sort-change="handleSort">

    	<el-table-column prop="id" label="头条id"  align="center" width="160">
        </el-table-column>

        <el-table-column prop="title" label="头条名称" align="center" min-width="200">
        </el-table-column>

        <el-table-column prop="article_type" label="头条类型" align="center" min-width="100">
        </el-table-column>

        <el-table-column prop="publishtime" label="发布时间" align="center" width="180">
        </el-table-column>

        <el-table-column prop="top_flag" label="置顶状态" align="center" width="120" >
        </el-table-column>

        <el-table-column prop="createtime" label="创建时间" align="center" width="180">
        </el-table-column>


        <el-table-column label="操作" width="220">
             <template slot-scope="scope">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button size="small" @click="handlePage(scope.$index, scope.row)">文章</el-button>
                <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="searchForm.totalRow">
        </el-pagination>
    </div>

    <el-dialog title="理财头条" :visible.sync="dialogFormVisible" size="tiny">
		<el-form ref="editForm" :model="editForm" label-width="80px" v-loading="lookloading">
			<input v-model="editForm.id" type="hidden"></input>

			<el-form-item label="头条名称">
				<el-input v-model="editForm.title" ></el-input>
            </el-form-item>

	    	 <el-form-item label="头条图片">
	    	 	<div class="imgSrc">
				<span style="white-space:pre;"> </span><img :src="imgDataUrl"   width="200" >
				</div>
				<div class="a-upload">
    				<input type="file" name="file" class="upload__input" @change="uploadChange" accept="image/png,image/jpeg">选择文件上传
				</div>

            </el-form-item>

			<el-form-item label="头条类型">
			<el-select v-model="editForm.article_type" filterable placeholder="请选择">
				<el-option
					v-for="item in art_type"
					:key="item.value"
					:label="item.label"
					:value="item.value">
				</el-option>
			</el-select>

            </el-form-item>
	    	<el-form-item label="发布时间" >
            	<el-date-picker v-model="editForm.publishtime":editable="false" type="datetime"  ></el-date-picker>	
            </el-form-item>

	    	<el-form-item label="是否置顶">
				<el-switch
				    v-model="editForm.top_flag"
				     active-value="1"
					 inactive-value="0">
				</el-switch>
            </el-form-item>
            <!--
			<el-form-item label="关联HTML">
				<el-input v-model="editForm.html_link" :disabled="!editForm.abled.html_link"></el-input>
            </el-form-item>
	    	-->
            <el-form-item>
                <el-button type="primary" @click="handleSave" :loading="editLoading">保存</el-button>
                <el-button @click="dialogFormVisible = false">取消</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>

    <el-dialog title="头条页面" :visible.sync="dialogPageVisible" width="80%">
        <el-form ref="editForm" :model="editForm" label-width="80px">
        <input v-model="editForm.id" type="hidden"></input>


        <div class="handle-box">

			<el-input v-model="editForm.htmlpage" :autosize="{minRows:12, maxRows: 20}" type="textarea"  ></el-input>
		</div>

             <div class="handle-box" >
                <el-button type="primary" @click="handleSavePage" :loading="editLoading">保存</el-button>
                <el-button @click="dialogPageVisible = false">取消</el-button>
           </div>
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
                getdataurl: process.env.BASE_API + 'tArticle/getId.do',
                dataurl: process.env.BASE_API + 'tArticle/dataList.do',
                saveurl: process.env.BASE_API + 'tArticle/save.do',
                saveimgurl: process.env.BASE_API + 'tArticle/saveImg.do',
                savehtmlurl: process.env.BASE_API + 'tArticle/saveHtml.do',
                gethtmlurl: process.env.BASE_API + 'tArticle/getHtml.do',
                deleteurl: process.env.BASE_API + 'tArticle/delete.do',
                getinvestlurl:process.env.BASE_API + 'tInvestInfo/getInvest.do',
                uploadurl:process.env.BASE_API + 'file/upload.do',
                dialogFormVisible: false,
                dialogPageVisible: false,
                editLoading: false,
                lookloading: false,
                imgDataUrl:'',
                editForm: {
					title: '',
					title_pic: '',
					pic_base: '',
					invest_link: '',
					publishtime: '',
					show_flag: '',
					top_flag: '',
					html_link: '',
					htmlpage: '',
					createtime: '',
					abled: {
						title:  false,
						title_pic: false,
						invest_link: false,
						publishtime: false,
						show_flag: false,
						top_flag: false,
						html_link: false,
						createtime: true,
					}
                },
                art_type: [
                	{
			          value: '1',
			          label: '投资推荐'
			        },
			        {
			          value: '2',
			          label: '经验分享'
			        },
			        {
			          value: '3',
			          label: '理财资讯'
			        },
			        {
			          value: '4',
			          label: '其他'
			        },],
                searchForm: {
					title: '',
					title_pic: '',
					invest_link: '',
					publishtime: '',
					show_flag: '',
					top_flag: '',
					html_link: '',
					createtime: '',

                    pageNum: 1,
                    totalRow: 0,
                    pageSize: 10,
                    is_search: false,
                    order: '',
                },
                tableData: [],
                invest_options:'',
                files:[],
            }
        },
        created() {
        	/*this.getInvestData();*/
            this.loadData();
        },
        computed: {
            data() {
                const self = this;
                return self.tableData;
            }
        },
        methods: {
        		getInvestData() {
                  let self = this;
                  http.post(self.getinvestlurl,{},(res) => {
                      self.invest_options = res.data.list;
                      console.log(self.invest_options);
                  }).catch(err => {
                      this.$eventHub.$emit('error', err);
                  })
                },
                loadData() {
                    let self = this;
                    http.post(self.dataurl, this.searchForm,(res) => {
                        self.tableData = res.data.list;
                        self.searchForm.totalRow = res.data.totalRow;
                        this.searchForm.is_search = false;

                         for (var i = 0; i < self.tableData.length; i++) {
                      	  let top_flag = self.tableData[i].top_flag;
                            if("0"==top_flag){
                              self.tableData[i].top_flag="";
                            }
                            if("1"==top_flag){
                              self.tableData[i].top_flag="置顶中";
                            }
                            let article_type = self.tableData[i].article_type;
                            if("1"==article_type){
                              self.tableData[i].article_type="投资推荐";
                            }
                            if("2"==article_type){
                              self.tableData[i].article_type="经验分享";
                            }
                            if("3"==article_type){
                              self.tableData[i].article_type="理财资讯";
                            }
                            if("4"==article_type){
                              self.tableData[i].article_type="其他";
                            }
                            
                            if( self.tableData[i].createtime!=null){
                            	self.tableData[i].createtime = fdate(self.tableData[i].createtime);
                       		}
                       		
                       		if( self.tableData[i].publishtime!=null){
                            	self.tableData[i].publishtime = fdate(self.tableData[i].publishtime);
                       		}
                            
                          }
                          
                          

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
                    this.lookloading=false;
                    this.imgDataUrl='';
                    this.editForm = {
    		    			    		title: '',
	    			    				title_pic: '',
	    			    				invest_link: '',
	    			    				publishtime: '',
	    			    				show_flag: '',
	    			    				top_flag: '',
	    			    				html_link: '',
	    			    				createtime: '',
	    		                  abled: {
    		    			    		title: false,
	    			    				title_pic: false,
	    			    				invest_link: false,
	    			    				publishtime: false,
	    			    				show_flag: false,
	    			    				top_flag: false,
	    			    				html_link: false,
	    			    				createtime: true,
	    		                  }
                    };
                },
                handleEdit(index, row) {
                    let self = this;
                    self.dialogFormVisible = true;
                    this.lookloading=false;
                    self.editForm = Object.assign({}, row);
                    if (!self.editForm.abled) {
                        self.editForm.abled = {};
                    }
                    self.editForm.abled.rolecode = false;
                    http.post(self.getdataurl + '?id=' + self.editForm.id, {},response => {
    		    		self.editForm.title=response.data.title;
	    				self.editForm.title_pic=response.data.title_pic;
	    				self.editForm.invest_link=response.data.invest_link;
	    				self.editForm.publishtime=response.data.publishtime;
	    				self.editForm.show_flag=response.data.show_flag;
	    				self.editForm.top_flag=response.data.top_flag;
	    				self.editForm.html_link=response.data.html_link;
	    				self.editForm.article_type=response.data.article_type;
	    				self.editForm.createtime=response.data.createtime;
	    				this.imgDataUrl='http://img-demo.oss-cn-hangzhou.aliyuncs.com/'+response.data.title_pic+'?x-oss-process=image/resize,w_200';
	    		   		return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })

                },
                handlePage(index, row) {
                    let self = this;
                    self.editForm = Object.assign({}, row);
                    http.post(self.gethtmlurl, {
		    			id:self.editForm.id,
			        },response => {

	    				self.editForm.htmlpage=response.data.htmlpage;

	    	 			self.dialogPageVisible = true;

	    		   		return;
                    }).catch(err => {
                        self.$eventHub.$emit('error', err);
                    })
					

                },
                handleDelete(index, row) {
                    const self = this;
                    this.$confirm('确定删除此条头条？', '提示', {
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
                handleSave() {
                    const self = this;
					this.lookloading=true;
                    http.post(self.saveurl, {
	    				id:self.editForm.id,
						title:self.editForm.title,
						title_pic:self.editForm.title_pic,
						invest_link:self.editForm.invest_link,
						publishtime:self.editForm.publishtime,
						show_flag:self.editForm.show_flag,
						top_flag:self.editForm.top_flag,
						html_link:self.editForm.html_link,
						article_type:self.editForm.article_type,
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

                handleSavePage(){
	                const self = this;

	                http.post(self.savehtmlurl, {
						id:self.editForm.id,
						html_link:self.editForm.htmlpage,
			        },response => {

		               this.dialogPageVisible=false;

		            }).catch(err => {
		               self.$eventHub.$emit('error', err);
		            })


                },



                handleFalse() {
                    return false;
                },
				getBase64(file,callback){
		           /* var maxWidth = 640;  */
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
		                      /*  if (this.width > maxWidth){
		                            imageWidth = maxWidth;
		                            imageHeight = this.height * maxWidth/this.width;
		                            canvas.width = imageWidth;
		                            canvas.height = imageHeight;
		                        }  */
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

				addSub(){
				    let data  = {};
				    let files = this.files;
				    let self = this;
				    let param = new FormData(); //创建form对象
				    if(files!=''){
				        param.append('file', files,files.name); //单个图片 ，多个用循环 append 添加
				    }else{
				        this.$message.error('请添加图片');
				    }
				    param.append('param', JSON.stringify(data));//添加form表单中其他数据
				    let config = {
				        headers:{'Content-Type':'multipart/form-data'}
				    };  //添加请求头
				    this.$ajax.post(this.uploadurl,param,config)
				      .then(response=>{
				        var ret = response.data;
				        if(ret.status){

				            this.$message({
				                message : '新增成功',
				                type : 'success'
				            })
				            //清空数据
				            this.imgDataUrl = '';
				            this.files = [];
				        }else{
				            this.msg(ret.msg);
				        }
				    })
				},

        }
}

</script>
