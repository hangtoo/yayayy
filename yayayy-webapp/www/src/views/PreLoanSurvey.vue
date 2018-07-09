<template>
    <div class="box">
        <div class="main_top">
            
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="姓名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="身份证号">
                    <el-input v-model="form.iDcard"></el-input>
                </el-form-item>
                <el-form-item label="导出状态">
                    <el-select v-model="form.region" placeholder="请选择状态">
                        <el-option label="全部" value="all"></el-option>
                        <el-option label="已导出" value="over"></el-option>
                        <el-option label="未导出" value="no"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="资金方">
                    <el-select v-model="form.fundSide" placeholder="请选择">
                        <el-option label="全部" value="zjf_all"></el-option>
                        <el-option label="优栎" value="youle"></el-option>
                        <el-option label="泛泰测试2" value="text"></el-option>
                        <el-option label="资金方" value="zjf"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="资产方">
                    <el-select v-model="form.fundProducing" placeholder="请选择">
                        <el-option label="全部" value="zcf_all"></el-option>
                        <el-option label="51速贷" value="51sd"></el-option>
                        <el-option label="啪啪钱包123" value="money_wallet"></el-option>
                        <el-option label="资产方" value="zcf"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="放款日期">
                    <el-col :span="11">
                        <el-date-picker type="date" v-model="form.date1" style="width: 100%;"></el-date-picker>
                    </el-col>
                    <el-col class="line" :span="2">-</el-col>
                    <el-col :span="11">
                        <el-date-picker type="date" v-model="form.date2" style="width: 100%;"></el-date-picker>
                    </el-col>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" class="query">查 询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="middle">
            <div class="gongneng">
                <!--<el-tag>新增</el-tag>-->
                <!--<el-tag>查看</el-tag>
                <el-tag>删除</el-tag>
                <el-tag>编辑</el-tag>
                <el-tag>导入</el-tag>
                <el-tag>导出</el-tag>-->

                <button class="delect" @click="clickDelect1()">新增</button>
                <div id="j_mask1" class="mask1 mask"></div>
                    <!--添加区域-->
                <div id="j_formAdd1" class="form-add1 form-add">
                    <div class="form-add-title">
                        <span>新增信息</span>
                        <div class="gn_icon">
                            <img src="../assets/enlarge.png" class="show enlarge1" @click="clickEnlarge1()" alt="">
                            <img src="../assets/reduction.png" class="hide reduction1" @click="clickReduction1()">
                        </div>

                        <!-- 具体写某个放大缩小的东西  id-->
                        
                        <div id="j_hideFormAdd1" class="hideClose" @click="clickClose1()">×</div>
                    </div>
                    <div class="content">
                          <template>
                            <el-tabs v-model="activeName" @tab-click="handleClick">
                                <el-tab-pane label="客户信息" name="first" class="info">
                                <div class="ziliao image">
                                        <h6>基本信息 </h6>
                                        <el-form :model="form1" ref="form" class="clearfix">
                                            <el-form-item label="客户号" :label-width="formLabelWidth">
                                                <el-input v-model="form1.number" auto-complete="off">6552778</el-input>
                                            </el-form-item>
                                            <el-form-item label="姓名" :label-width="formLabelWidth">
                                                <el-input v-model="form1.name" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="性别" :label-width="formLabelWidth">
                                                <el-select v-model="form1.gender" placeholder="请选择性别">
                                                    <el-option label="男" value="man"></el-option>
                                                    <el-option label="女" value="woman"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="教育程度" :label-width="formLabelWidth">
                                                <el-select v-model="form1.education" placeholder="请选择">
                                                    <el-option label="大专" value="dazhuan"></el-option>
                                                    <el-option label="本科" value="benke"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="婚姻状态" :label-width="formLabelWidth">
                                                <el-select v-model="form1.marriage" placeholder="请选择">
                                                    <el-option label="已婚" value="married"></el-option>
                                                    <el-option label="未婚" value="unmarried"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="住宅性质" :label-width="formLabelWidth">
                                                <el-input v-model="form1.residence" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="现居住地" :label-width="formLabelWidth">
                                                <el-input v-model="form1.address" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="证件类型" :label-width="formLabelWidth">
                                                <el-input v-model="form1.documentType" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="证件号码" :label-width="formLabelWidth">
                                                <el-input v-model="form1.documentNumber" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="电子邮箱" :label-width="formLabelWidth">
                                                <el-input v-model="form1.email" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="职业状态" :label-width="formLabelWidth">
                                                <el-input v-model="form1.occupation" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="客户来源" :label-width="formLabelWidth">
                                                <el-select v-model="form1.customer" placeholder="请选择">
                                                    <el-option label="学生" value="stu"></el-option>
                                                    <el-option label="成人" value="adult"></el-option>
                                                    <el-option label="老人" value="old"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-form>
                                        <h6>详细资料</h6>
                                        <el-form :model="form1" ref="form" class="clearfix">
                                            <el-form-item label="客户号" :label-width="formLabelWidth">
                                                <el-input v-model="form.number" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="姓名" :label-width="formLabelWidth">
                                                <el-input v-model="form.name" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="性别" :label-width="formLabelWidth">
                                                <el-select v-model="form.gender" placeholder="请选择性别">
                                                    <el-option label="男" value="man"></el-option>
                                                    <el-option label="女" value="woman"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="教育程度" :label-width="formLabelWidth">
                                                <el-select v-model="form.education" placeholder="请选择">
                                                    <el-option label="大专" value="dazhuan"></el-option>
                                                    <el-option label="本科" value="benke"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="婚姻状态" :label-width="formLabelWidth">
                                                <el-select v-model="form.marriage" placeholder="请选择">
                                                    <el-option label="已婚" value="married"></el-option>
                                                    <el-option label="未婚" value="unmarried"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="住宅性质" :label-width="formLabelWidth">
                                                <el-input v-model="form.residence" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="现居住地" :label-width="formLabelWidth">
                                                <el-input v-model="form.address" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="证件类型" :label-width="formLabelWidth">
                                                <el-input v-model="form.documentType" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="证件号码" :label-width="formLabelWidth">
                                                <el-input v-model="form.documentNumber" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="电子邮箱" :label-width="formLabelWidth">
                                                <el-input v-model="form.email" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="职业状态" :label-width="formLabelWidth">
                                                <el-input v-model="form.occupation" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="客户来源" :label-width="formLabelWidth">
                                                <el-select v-model="form.customer" placeholder="请选择">
                                                    <el-option label="学生" value="stu"></el-option>
                                                    <el-option label="成人" value="adult"></el-option>
                                                    <el-option label="老人" value="old"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-form>
                                </div>
                                
                                </el-tab-pane>
                                <el-tab-pane label="影像资料" name="second">
                                    <h6>影像资料</h6>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="C:/Users/Administrator/Pictures/Camera Roll/timg.jpg"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">身份证正面</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">身份证反面</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">房产证</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">结婚证</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">户口簿</span>
                                    </div>
                                    <!--<h6>影像资料</h6>-->
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">身份证正面</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">身份证反面</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">房产证</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">结婚证</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">户口簿</span>
                    
                                    </div>
                                </el-tab-pane>
                                <el-tab-pane label="历史信息" name="third">历史信息</el-tab-pane>
                            </el-tabs>
                        </template>
                    </div>
                    
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="toSecond">下一步</el-button>
                    </div>
                </div>

                 <button class="delect" @click="clickDelect2()">查看</button>
                <div id="j_mask2" class="mask2 mask"></div>
                    <!--添加区域-->
                <div id="j_formAdd2" class="form-add2 form-add">
                    <div class="form-add-title">
                        <span>查看信息</span>
                        <div class="gn_icon">
                            <img src="../assets/enlarge.png" class="show enlarge2" @click="clickEnlarge2()" alt="">
                            <img src="../assets/reduction.png" class="hide reduction2" @click="clickReduction2()">
                        </div>
                        <div id="j_hideFormAdd2" class="hideClose" @click="clickClose2()">×</div>
                    </div>
                    <div class="content">
                          <template>
                            <el-tabs v-model="activeName" @tab-click="handleClick">
                                <el-tab-pane label="客户信息" name="first" class="info">
                                    <div class="clearfix">
                                        <h6>基本信息</h6>
                                        <div class="deta_info">
                                            <span class="quester">客户号&nbsp;:</span>
                                            <span class="answer">c29787489</span>
                                        </div>
                                        <div class="deta_info">
                                            <span class="quester">姓名&nbsp;:</span>
                                            <span class="answer">黎吧啦</span>
                                        </div>
                                        <div class="deta_info">
                                            <span class="quester">性别&nbsp;:</span>
                                            <span class="answer">女</span>
                                        </div>
                                        <div class="deta_info">
                                            <span class="quester">教育程度&nbsp;:</span>
                                            <span class="answer">本科</span>
                                        </div>
                                        <div class="deta_info">
                                            <span class="quester">婚姻状态&nbsp;:</span>
                                            <span class="answer">未婚</span>
                                        </div>
                                        <div class="deta_info">
                                            <span class="quester">住宅性质&nbsp;:</span>
                                            <span class="answer">父母同住</span>
                                        </div>
                                        <div class="deta_info">
                                            <span class="quester">现居住地&nbsp;:</span>
                                            <span class="answer">浙江省杭州市滨江区江南大道3888号</span>
                                        </div>
                                        <div class="deta_info">
                                            <span class="quester">证件类型&nbsp;:</span>
                                            <span class="answer">身份证</span>
                                        </div>
                                        <div class="deta_info">
                                            <span class="quester">证件号码&nbsp;:</span>
                                            <span class="answer">877635277898763457</span>
                                        </div>
                                        <div class="deta_info">
                                            <span class="quester">电子邮箱&nbsp;:</span>
                                            <span class="answer">8723987623@qq.com</span>
                                        </div>
                                        <div class="deta_info">
                                            <span class="quester">职业状态&nbsp;:</span>
                                            <span class="answer">在职</span>
                                        </div>
                                        <div class="deta_info">
                                            <span class="quester">客户来源&nbsp;:</span>
                                            <span class="answer">APP</span>
                                        </div>
                                    </div>

                                </el-tab-pane>
                                <el-tab-pane label="影像资料" name="second">
                                    <h6>影像资料</h6>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="C:/Users/Administrator/Pictures/Camera Roll/timg.jpg "
                                        multiple>
                                        </el-upload>
                                        <span class="state">身份证正面</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        </el-upload>
                                        <span class="state">身份证反面</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        </el-upload>
                                        <span class="state">房产证</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        </el-upload>
                                        <span class="state">结婚证</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        </el-upload>
                                        <span class="state">户口簿</span>
                                    </div>
                                    <!--<h6>影像资料</h6>-->
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        </el-upload>
                                        <span class="state">身份证正面</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        </el-upload>
                                        <span class="state">身份证反面</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        </el-upload>
                                        <span class="state">房产证</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        </el-upload>
                                        <span class="state">结婚证</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        </el-upload>
                                        <span class="state">户口簿</span>
                    
                                    </div>
                                </el-tab-pane>
                                <el-tab-pane label="历史信息" name="third">历史信息</el-tab-pane>
                            </el-tabs>
                        </template>
                    </div>
                     <div slot="footer" class="dialog-footer">
                        <el-button @click="toSecond">下一步</el-button>
                    </div>
                </div>

                <button class="delect" @click="clickDelect3()">删除</button>
                <div id="j_mask3" class="mask3 mask"></div>
                    <!--添加区域-->
                <div id="j_formAdd3" class="form-add3 form-add">
                    <div class="form-add-title">
                        <span>提示信息</span>
                        <div id="j_hideFormAdd3" class="hideClose" @click="clickClose3()">×</div>
                    </div>
                    <div class="content">
                          <div class="del_title">删除文件</div>
                          <div class="del_con">
                              删除相关文件后不可恢复，您确定要删除该文件？
                          </div>
                    </div>
                    
                    <div class="footer">
                        <button  class="decision" @click="clickCancel1()"><a href="#">取消</a></button>
                        <button class="decision decision_shanchu"><a href="#">删除</a></button>
                    </div>
                </div>

                <button class="delect" @click="clickDelect4()">编辑</button>
                <div id="j_mask4" class="mask4"></div>
                    <!--添加区域-->
                <div id="j_formAdd4" class="form-add4 form-add">
                    <div class="form-add-title">
                        <span>编辑信息</span>
                        <div class="gn_icon">
                            <img src="../assets/enlarge.png" class="show enlarge4" @click="clickEnlarge4()" alt="">
                            <img src="../assets/reduction.png" class="hide reduction4" @click="clickReduction4()">
                        </div>
                        <div id="j_hideFormAdd4" class="hideClose" @click="clickClose4()">×</div>
                    </div>
                    <div class="content">
                          <template>
                            <el-tabs v-model="activeName" @tab-click="handleClick">
                                <el-tab-pane label="客户信息" name="first" class="info">
                                <div class="ziliao image">
                                        <h6>基本信息 </h6>
                                        <el-form :model="form1" ref="form" class="clearfix">
                                            <el-form-item label="客户号" :label-width="formLabelWidth">
                                                <el-input v-model="form1.number" auto-complete="off">6552778</el-input>
                                            </el-form-item>
                                            <el-form-item label="姓名" :label-width="formLabelWidth">
                                                <el-input v-model="form1.name" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="性别" :label-width="formLabelWidth">
                                                <el-select v-model="form1.gender" placeholder="请选择性别">
                                                    <el-option label="男" value="man"></el-option>
                                                    <el-option label="女" value="woman"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="教育程度" :label-width="formLabelWidth">
                                                <el-select v-model="form1.education" placeholder="请选择">
                                                    <el-option label="大专" value="dazhuan"></el-option>
                                                    <el-option label="本科" value="benke"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="婚姻状态" :label-width="formLabelWidth">
                                                <el-select v-model="form1.marriage" placeholder="请选择">
                                                    <el-option label="已婚" value="married"></el-option>
                                                    <el-option label="未婚" value="unmarried"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="住宅性质" :label-width="formLabelWidth">
                                                <el-input v-model="form1.residence" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="现居住地" :label-width="formLabelWidth">
                                                <el-input v-model="form1.address" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="证件类型" :label-width="formLabelWidth">
                                                <el-input v-model="form1.documentType" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="证件号码" :label-width="formLabelWidth">
                                                <el-input v-model="form1.documentNumber" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="电子邮箱" :label-width="formLabelWidth">
                                                <el-input v-model="form1.email" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="职业状态" :label-width="formLabelWidth">
                                                <el-input v-model="form1.occupation" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="客户来源" :label-width="formLabelWidth">
                                                <el-select v-model="form1.customer" placeholder="请选择">
                                                    <el-option label="学生" value="stu"></el-option>
                                                    <el-option label="成人" value="adult"></el-option>
                                                    <el-option label="老人" value="old"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-form>
                                        <h6>详细资料</h6>
                                        <el-form :model="form1" ref="form" class="clearfix">
                                            <el-form-item label="客户号" :label-width="formLabelWidth">
                                                <el-input v-model="form.number" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="姓名" :label-width="formLabelWidth">
                                                <el-input v-model="form.name" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="性别" :label-width="formLabelWidth">
                                                <el-select v-model="form.gender" placeholder="请选择性别">
                                                    <el-option label="男" value="man"></el-option>
                                                    <el-option label="女" value="woman"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="教育程度" :label-width="formLabelWidth">
                                                <el-select v-model="form.education" placeholder="请选择">
                                                    <el-option label="大专" value="dazhuan"></el-option>
                                                    <el-option label="本科" value="benke"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="婚姻状态" :label-width="formLabelWidth">
                                                <el-select v-model="form.marriage" placeholder="请选择">
                                                    <el-option label="已婚" value="married"></el-option>
                                                    <el-option label="未婚" value="unmarried"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="住宅性质" :label-width="formLabelWidth">
                                                <el-input v-model="form.residence" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="现居住地" :label-width="formLabelWidth">
                                                <el-input v-model="form.address" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="证件类型" :label-width="formLabelWidth">
                                                <el-input v-model="form.documentType" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="证件号码" :label-width="formLabelWidth">
                                                <el-input v-model="form.documentNumber" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="电子邮箱" :label-width="formLabelWidth">
                                                <el-input v-model="form.email" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="职业状态" :label-width="formLabelWidth">
                                                <el-input v-model="form.occupation" auto-complete="off"></el-input>
                                            </el-form-item>
                                            <el-form-item label="客户来源" :label-width="formLabelWidth">
                                                <el-select v-model="form.customer" placeholder="请选择">
                                                    <el-option label="学生" value="stu"></el-option>
                                                    <el-option label="成人" value="adult"></el-option>
                                                    <el-option label="老人" value="old"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-form>
                                </div>
                                
                                </el-tab-pane>
                                <el-tab-pane label="影像资料" name="second">
                                    <h6>影像资料</h6>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">身份证正面</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">身份证反面</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">房产证</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">结婚证</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">户口簿</span>
                                    </div>
                                    <!--<h6>影像资料</h6>-->
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">身份证正面</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">身份证反面</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">房产证</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">结婚证</span>
                                    </div>
                                    <div class="photo">
                                        <el-upload
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                        <i class="el-icon-upload"></i>
                                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                        
                                        <!--<div class="el-upload__tip" slot="tip">身份证正面</div>-->
                                        </el-upload>
                                        <span class="state">户口簿</span>
                    
                                    </div>
                                </el-tab-pane>
                                <el-tab-pane label="历史信息" name="third">历史信息</el-tab-pane>
                            </el-tabs>
                        </template>
                    </div>
                    
                    <div slot="footer" class="dialog-footer">
                            <!--<el-button @click="dialogFormVisible = false">下一步</el-button>-->
                            <el-button @click="toSecond">下一步</el-button>
                            
                            <!--<el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>-->
                        </div>
                </div>

                 <button class="delect" @click="clickDelect5()">导入</button>
                <div id="j_mask5" class="mask5 mask"></div>
                    <!--添加区域-->
                <div id="j_formAdd5" class="form-add5 form-add">
                    <div class="form-add-title">
                        <span>借款信息导入</span>
                        <div id="j_hideFormAdd5" class="hideClose" @click="clickClose5()">×</div>
                    </div>
                    <div class="content">
                          <div class="jk_file">
                              上传导入文件：<input type="file" class="daoru">
                          </div>
                          <div class="lianjie">
                             <a href="#">导入模板文件下载</a>
                          </div>
                          <!--<div class="daoru_xuanxiang">-->
                              <el-form :inline="true" :model="formInline" class="demo-form-inline">
                                    <el-form-item label="资金方">
                                        <el-select v-model="formInline.region" placeholder="请选择">
                                        <el-option label="资金方" value="zcf"></el-option>
                                        <el-option label="资产方" value="zjf"></el-option>
                                        </el-select>
                                    </el-form-item>
                                    
                                </el-form>
                          <!--</div>-->
                          <div class="footer">
                            <button  class="decision decision_btn" @click="clickCancel2()"><a href="#">取消</a></button>
                            <button class="decision " style="background-color: #1F92FF;"><a href="#" style="color:#fff;">确认</a></button>
                        </div>
                    </div>
                    
                </div>

            </div>
            <div class="list">
                <!--<template>
                    <el-table ref="multipleTable" :data="tableData3" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
                        <el-table-column type="selection" min-width="30">
                        </el-table-column>
                        <el-table-column prop="number" label="序号" min-width="50">
                        </el-table-column>
                        <el-table-column prop="name" label="姓名" min-width="80">
                        </el-table-column>
                        <el-table-column prop="id_card" label="身份证号" min-width="180">
                        </el-table-column>
                        <el-table-column prop="loan_money" label="借款金额" sortable show-overflow-tooltip min-width="102">
                        </el-table-column>
                        <el-table-column prop="loan_time" label="借款时间" sortable show-overflow-tooltip min-width="102">
                        </el-table-column>
                        <el-table-column prop="principal" label="放款本金" sortable show-overflow-tooltip min-width="102">
                        </el-table-column>
                        <el-table-column prop="Interest" label="利息" show-overflow-tooltip min-width="80">
                        </el-table-column>
                        <el-table-column prop="risk" label="风险评分" sortable show-overflow-tooltip min-width="102">
                        </el-table-column>
                        <el-table-column prop="state" label="审批状态" show-overflow-tooltip min-width="80">
                        </el-table-column>
                    </el-table>
                    <div class="block">
                        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="currentPage1" :page-size="10" layout="total, prev, pager, next" :total="100">
                        </el-pagination>
                    </div>
                </template>-->
                  <table cellspacing="0" cellpadding="0">
                     <thead>
                        <tr>
                            <th class="xx">
                                <input type="checkbox" id="theadInp" @click="clickCheck()">
                            </th>
                            <th class="xh">序号</th>
                            <th class="user_name">姓名</th>
                            <th class="id_card">身份证号</th>
                            <th class="money" sortable>借款金额</th>
                            <th class="time">借款时间</th>
                            <th class="principal">放款本金</th>
                            <th class="lx">利息</th>
                            <th class="risk">风险评估</th>
                            <th class="approval">审批状态</th>
                        </tr>
                     </thead id="tbody">
                     <tbody>
                        <!--<tr v-for="data in getCheckBoxes(currentPage1)">
                            <td>
                                <input type="checkbox" class="checkbox">
                            </th>
                            <td>{{data.number}}</th>
                            <td>{{data.user_name}}</th>
                            <td>{{data.id_card}}</th>
                            <td>{{data.loan_money}}</th>
                            <td>{{data.loan_time}}</th>
                            <td>{{data.principal}}</th>
                            <td>{{data.Interest}}</th>
                            <td>{{data.risk}}</th>
                            <td>{{data.approval}}</td>
                        </tr>-->
                        <tr v-for="data in getCheckBoxesData">
                            <td>
                                <input type="checkbox" class="checkbox">
                            </th>
                            <td>{{data.number}}</th>
                            <td>{{data.user_name}}</th>
                            <td>{{data.id_card}}</th>
                            <td>{{data.loan_money}}</th>
                            <td>{{data.loan_time}}</th>
                            <td>{{data.principal}}</th>
                            <td>{{data.Interest}}</th>
                            <td>{{data.risk}}</th>
                            <td>{{data.approval}}</td>
                        </tr>
                        
                     </tbody>
                  </table>
                  <div class="block">
                        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="currentPage1" :page-size="10" layout="total, prev, pager, next" :total="datas.total">
                        </el-pagination>
                  </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
        export default {
            data() {
                return {
                    getCheckBoxesData:[],
                    currentPage1: 1,
                    baseUrl: 'http://localhost:3034',
                    dialogVisible: false,
                    activeName: 'first',
                    datas: [],
                    total: "",
                    forms: [],
                    form: {
                        name: '',
                        iDcard: '',
                        region: '',
                        date1: '',
                        date2: '',
                        fundSide: '',
                        fundProducing: '',
                        delivery: false,
                        type: [],
                        resource: '',
                        desc: ''
                    },
                    tableData3: [
                        {
                            // date: '2016-05-03',
                            number: 1,
                            user_name: '王小虎',
                            id_card: '622426199709435245',
                            loan_money: 2000,
                            loan_time: '2015-08-13',
                            principal: 2000,
                            Interest: 94444445,
                            risk: 14,
                            approval: '已导出'
                        },
                        {
                            number: 2,
                            user_name: '王小虎',
                            id_card: '622426199709435245',
                            loan_money: 2000,
                            loan_time: '2015-08-14',
                            principal: 2000000,
                            Interest: 95,
                            risk: 14,
                            approval: '已导出'
                        },
                        {
                            number: 3,
                            user_name: '王小虎',
                            id_card: '622426199709435245',
                            loan_money: "2,000,000.34",
                            loan_time: '2015-08-16',
                            principal: 2000,
                            Interest: 95,
                            risk: 14,
                            approval: '已导出'
                        },
                        {
                            number: 4,
                            user_name: '王小虎',
                            id_card: '622426199709435245',
                            loan_money: 2000,
                            loan_time: '2015-08-24',
                            principal: 2000,
                            Interest: 95,
                            risk: 14,
                            approval: '已导出'
                        },
                        {
                            number: 5,
                            user_name: '王小虎',
                            id_card: '622426199709435245',
                            loan_money: 2000,
                            loan_time: '2015-08-25',
                            principal: 2000,
                            Interest: 95,
                            risk: 14,
                            approval: '已导出'
                        },
                        {
                            number: 6,
                            user_name: '王小虎',
                            id_card: '622426199709435245',
                            loan_money: 2000,
                            loan_time: '2015-08-13',
                            principal: 2000,
                            Interest: 95,
                            risk: 14,
                            approval: '已导出'
                        },
                        {
                            number: 7,
                            user_name: '王小虎',
                            id_card: '622426199709435245',
                            loan_money: 2000,
                            loan_time: '2015-08-13',
                            principal: 2000,
                            Interest: 95,
                            risk: 14,
                            approval: '已导出'
                        },
                        {
                            number: 8,
                            user_name: '王小虎',
                            id_card: '622426199709435245',
                            loan_money: 2000,
                            loan_time: '2015-08-13',
                            principal: 2000,
                            Interest: 95,
                            risk: 14,
                            approval: '已导出'
                        },
                        {
                            number: 9,
                            user_name: '王小虎',
                            id_card: '622426199709435245',
                            loan_money: 2000,
                            loan_time: '2015-08-29',
                            principal: 2000,
                            Interest: 95,
                            risk: 14,
                            approval: '已导出'
                        },
                        {
                            number: 10,
                            user_name: '王小虎',
                            id_card: '622426199709435245',
                            loan_money: 2000,
                            loan_time: '2015-08-13',
                            principal: 2000,
                            Interest: 95,
                            risk: 14,
                            approval: '已导出'
                        },
                    ],
                    multipleSelection: [],
                    dialogFormVisible: false,
                    form1: {
                        number: '',
                        name: '',
                        gender: '',
                        education: '',
                        marriage: '',
                        residence: '',
                        address: '',
                        documentType: '',
                        documentNumber: '',
                        email: '',
                        occupation: '',
                        customer: '',
                        // region: '',
                        // date1: '',
                        // date2: '',
                        delivery: false,
                        type: [],
                        resource: '',
                        desc: ''
                    },
                    formLabelWidth: '80px',
                    formInline: {
                        region: ''
                    },
                    
                }
            },
            // component: {
            //     List
            // },
            created (){
                this.ListInfo();
                // this.getCheckBoxes(val);
            },
            methods: {
                getCheckBoxes:function(page){

                    console.log(1);
                    console.log(this.datas.info)
                    console.log(2);
                    var leng = this.datas.info.length;

                    if(leng<=10){
                        return this.datas.info;
                    }
                    var start,end;
                    if(page == '1'){
                        start = '0';
                        end = '10'
                    }else{
                        start = (page-1)*10;
                        end = page*10;
                    }
                    // return this.datas.info.slice(start,end)
                    this.getCheckBoxesData = this.datas.info.slice(start,end);
                },
                onSubmit() {
                    // console.log('submit!');
                },
                // 和具体显示的列表有关
                toggleSelection(rows) {
                    if (rows) {
                        rows.forEach(row => {
                            this.$refs.multipleTable.toggleRowSelection(row);
                        });
                    } else {
                        this.$refs.multipleTable.clearSelection();
                    }
                },
                handleSelectionChange(val) {
                    this.multipleSelection = val;
                },
                // 分页
                handleSizeChange(val) {
                    // console.log(`每页 ${val} 条`);
                },
                handleCurrentChange(val) {
                    this.getCheckBoxes(val);
                },
                handleClick(tab, event) {
                    // console.log(tab, event);
                },
                toSecond(){
                    document.getElementById("tab-second").click();
                },
                // 遮罩层
                clickDelect1: function(){
                    var mask = document.getElementById("j_mask1"),
                        formAdd = document.getElementById("j_formAdd1");
                         //显示遮罩层和添加区域;
                        mask.style.display = "block";
                        formAdd.style.display = "block";
                },
                clickClose1: function(){
                    var mask = document.getElementById("j_mask1"),
                        // delect = document.getElementsByClassName("j_mask")[0],
                        formAdd = document.getElementById("j_formAdd1");
                         //显示遮罩层和添加区域;
                        mask.style.display = "none";
                        formAdd.style.display = "none";
                },
                 // 遮罩层
                clickDelect2: function(){
                    var mask = document.getElementById("j_mask2"),
                        formAdd = document.getElementById("j_formAdd2");
                         //显示遮罩层和添加区域;
                        mask.style.display = "block";
                        formAdd.style.display = "block";
                },
                clickClose2: function(){
                    var mask = document.getElementById("j_mask2"),
                        // delect = document.getElementsByClassName("j_mask")[0],
                        formAdd = document.getElementById("j_formAdd2");
                         //显示遮罩层和添加区域;
                        mask.style.display = "none";
                        formAdd.style.display = "none";
                },
                 // 遮罩层
                clickDelect3: function(){
                    var mask = document.getElementById("j_mask3"),
                        formAdd = document.getElementById("j_formAdd3");
                         //显示遮罩层和添加区域;
                        mask.style.display = "block";
                        formAdd.style.display = "block";
                },
                clickClose3: function(){
                    var mask = document.getElementById("j_mask3"),
                        // delect = document.getElementsByClassName("j_mask")[0],
                        formAdd = document.getElementById("j_formAdd3");
                         //显示遮罩层和添加区域;
                        mask.style.display = "none";
                        formAdd.style.display = "none";
                },
                 // 遮罩层
                clickDelect4: function(){
                    var mask = document.getElementById("j_mask4"),
                        formAdd = document.getElementById("j_formAdd4");
                         //显示遮罩层和添加区域;
                        mask.style.display = "block";
                        formAdd.style.display = "block";
                },
                clickClose4: function(){
                    var mask = document.getElementById("j_mask4"),
                        // delect = document.getElementsByClassName("j_mask")[0],
                        formAdd = document.getElementById("j_formAdd4");
                         //显示遮罩层和添加区域;
                        mask.style.display = "none";
                        formAdd.style.display = "none";
                },
                 // 遮罩层
                clickDelect5: function(){
                    var mask = document.getElementById("j_mask5"),
                        formAdd = document.getElementById("j_formAdd5");
                         //显示遮罩层和添加区域;
                        mask.style.display = "block";
                        formAdd.style.display = "block";
                },
                clickClose5: function(){
                    var mask = document.getElementById("j_mask5"),
                        // delect = document.getElementsByClassName("j_mask")[0],
                        formAdd = document.getElementById("j_formAdd5");
                         //显示遮罩层和添加区域;
                        mask.style.display = "none";
                        formAdd.style.display = "none";
                },
                clickEnlarge1: function () {
                    var formAdd1 = document.getElementById("j_formAdd1");
                     formAdd1.style.width="100%";
                     formAdd1.style.height="100%";
                     formAdd1.style.top="0";
                     formAdd1.style.left="0";
                     formAdd1.className = "form-add1 no_fillet";
                     document.getElementsByClassName("enlarge1")[0].setAttribute("class","hide enlarge1" );
                     document.getElementsByClassName("reduction1")[0].setAttribute("class","show reduction1");
                    document.getElementById("j_hideFormAdd1").style.right = "40px";
                    // document.getElementsByClassName("gn_icon")[0].setAttribute("gn_icon", "gn_icon_margin-right")
                },
                clickReduction1: function(){
                    var formAdd1 = document.getElementById("j_formAdd1");
                     formAdd1.style.width="64%";
                     formAdd1.style.height="72%";
                     formAdd1.style.top="10%";
                     formAdd1.style.left="18%";
                    formAdd1.className = "form-add1 fillet";
                    //  alert(111);
                    document.getElementsByClassName("enlarge1")[0].setAttribute("class","show enlarge1" );
                    document.getElementsByClassName("reduction1")[0].setAttribute("class","hide reduction1");
                     document.getElementById("j_hideFormAdd1").style.right = "38px";
                    // document.getElementsByClassName("gn_icon")[0].setAttribute("gn_icon", "gn_icon_margin-right")
                   
                   
                },
                clickEnlarge2: function () {
                     var formAdd2 = document.getElementById("j_formAdd2");
                     formAdd2.style.width="100%";
                     formAdd2.style.height="100%";
                     formAdd2.style.top="0";
                     formAdd2.style.left="0";
                     formAdd2.className = "form-add2 no_fillet";
                    document.getElementsByClassName("enlarge2")[0].setAttribute("class","hide enlarge2" );
                     document.getElementsByClassName("reduction2")[0].setAttribute("class","show reduction2");
                    document.getElementById("j_hideFormAdd2").style.right = "40px";
                    // document.getElementsByClassName("gn_icon")[0].setAttribute("gn_icon", "gn_icon_margin-right")
                },
                clickReduction2: function(){
                    var formAdd2 = document.getElementById("j_formAdd2");
                     formAdd2.style.width="64%";
                     formAdd2.style.height="72%";
                     formAdd2.style.top="10%";
                     formAdd2.style.left="18%";
                     formAdd2.className = "form-add2 fillet"
                     document.getElementsByClassName("enlarge2")[0].setAttribute("class","show enlarge2" );
                    document.getElementsByClassName("reduction2")[0].setAttribute("class","hide reduction2");
                     document.getElementById("j_hideFormAdd1").style.right = "38px";
                    // document.getElementsByClassName("gn_icon")[0].setAttribute("gn_icon", "gn_icon_margin-right")
                   
                   
                },
                 clickEnlarge4: function () {
                     var formAdd4 = document.getElementById("j_formAdd4");
                     formAdd4.style.width="100%";
                     formAdd4.style.height="100%";
                     formAdd4.style.top="0";
                     formAdd4.style.left="0";
                     formAdd4.className = "form-add4 no_fillet";
                     document.getElementsByClassName("enlarge4")[0].setAttribute("class","hide enlarge4" );
                     document.getElementsByClassName("reduction4")[0].setAttribute("class","show reduction4");
                     document.getElementById("j_hideFormAdd4").style.right = "40px";
                    //  document.getElementsByClassName("gn_icon")[0].setAttribute("class", "gn_icon_margin-right")
                },
                clickReduction4: function(){
                    var formAdd4 = document.getElementById("j_formAdd4");
                     formAdd4.style.width="64%";
                     formAdd4.style.height="72%";
                     formAdd4.style.top="10%";
                     formAdd4.style.left="18%";
                     formAdd4.className = "form-add4 fillet"
                    document.getElementsByClassName("enlarge4")[0].setAttribute("class","show enlarge4" );
                    document.getElementsByClassName("reduction4")[0].setAttribute("class","hide reduction4");
                     document.getElementById("j_hideFormAdd1").style.right = "38px";
                    // document.getElementsByClassName("gn_icon")[0].setAttribute("gn_icon", "gn_icon_margin-right")
                   
                   
                },
                clickCancel1:function(){
                    document.getElementById("j_mask3").style.display = "none";
                
                    document.getElementById("j_formAdd3").style.display = "none";
                },
                 clickCancel2:function(){
                    document.getElementById("j_mask5").style.display = "none";
                
                    document.getElementById("j_formAdd5").style.display = "none";
                },
                // 获取列表数据
                ListInfo() {
                    var vm = this;
                    axios.get(vm.baseUrl + '/listInformation').then(response => {
                      
                    for(var i = 0; i<response.data.length; i++) {
                        if(response.data[i].id_list == 2){
                           
                            vm.datas = response.data[i];

                            this.getCheckBoxes(1);
                            
                        }
                    }

                    }).catch(err => {
                    console.log(err)
                    })
                },
                tablePagination(data=[]){
                    /**
                     * 表格数据分页的方法
                     */
                    let array = [], startNum=0, endNum = 0;
                    this.total = data.length;
                    startNum = (this.currentPage-1)*this.pageSize;
                    if(this.currentPage*this.pageSize<this.total){
                        endNum = this.currentPage*this.pageSize;
                    } else {
                        endNum = this.total;
                    }
                    array = data.slice(startNum, endNum);
                    return array;
                },
                formInfo () {
                    var vm = this
                    axios.get(vm.baseUrl + '/form').then(response => {
                    vm.lists = response.data;
                    // console.log(vm.lists);
                    }).catch(errr => {
                    // console.log(err)
                    })
                },
                // 点击复选框
                clickCheck: function () {
                    var theadInp = document.getElementById('theadInp');
                    var tbody = document.getElementById("tbody");
                    var inpArr = document.getElementsByClassName("checkbox");
                    if(this.checked === true){
                        for(var i=0;i<inpArr.length;i++){
                            inpArr[i].checked = true;
                        }
                    }else{
                         for(var i=0;i<inpArr.length;i++){
                            inpArr[i].checked = false;
                        }
                    }
                }
            },
            computed:{
                showTable(){
                    return this.tablePagination(this.$store.getters.tableData);
                },
                // getCheckBoxes:function(page){
                //     var leng = this.datas.info.length;
                //     if(leng<=10){
                //         return this.datas.info 
                //     }
                //     var start,end;
                //     if(page == '1'){
                //         start = '1';
                //         end = '10'
                //     }else{
                //         start = (page-1)*10 + 1;
                //         end = page*10;
                //     }
                //     this.datas.info.slice(start,end)
                // }	

            }
        }
</script>

<style>
    .show {
        display: block;
    }
    .hide {
        display: none;
    }
    .no_fillet {
        border: 0 !important;
        border-radius: 0 !important;
    }
    .fillet {
        border: 10px solid #fff !important;
        border-radius: 8px !important;
    }
    .fillet {
        border: 10px solid #fff;
        border-radius: 8px;
    }
    .gn_icon_margin-right {
        margin-right: 55px;
        float: left;
        margin-top: 14px;
    }
    /* 清除浮动 */

    .clearfix:after {
        content: ".";
        display: block;
        height: 0;
        visibility: hidden;
        clear: both;
    }

    .clearfix {
        zoom: 1;
    }


    /* 最大容器的设置 */

    .box {
        width: 100%;
        margin-right: 20px;
    }


    /* 数据查询 */

    .main_top {
        /*margin-top: 40px;*/
        width: 100%;
        background-color: #fff;
        color: #333;
        font: 14px "SimSun Regular";
        margin-bottom: 10px;
        padding-top: 12px;
        border-radius: 8px;
        padding-bottom: 4px;
    }

    .main_top fieldset {
        width: 90%;
        height: 100%;
        color: #333;
        border: 1px solid #ccc;
        padding: 15px 30px 5px 30px;
    }

    /* 多选框 边框*/

    .el-checkbox__inner {
        border: 1px solid #999 !important;
        z-index: 0;
    }

    /* input 框的宽度*/

    .el-input__inner {
        width: 170px !important;
        height: 34px !important;
        padding: 0px 8px !important;
    }
    /* 日期距离左边的距离 */
    .el-input--prefix .el-input__inner {
        padding-left: 30px !important;
    }
    /* 第一个日期插件的宽度*/

    .el-col-11 {
        width: 200px !important;
        margin-right: -16px;
    }

    /*  日期间 - 的宽度及属性*/

    .el-col-2 {
        width: 0 !important;
        margin-right: 22px;
    }
    .el-form {
        overflow: hidden;
    }
    /* 每个div 方格的样式*/

    .el-form-item {
        margin-bottom: 8px !important;
        float: left;
        margin-right: 0.5%;
    }

    /* 查询样式的设置 */

    .query {
        width: 120px;
        margin-left: -80px;
    }

    .el-button {
        padding: 7px 18px !important;
    }

    /* 列表展示 */

    .middle {
        width: 99%;
        height: 360px;
        background-color: #fff;
        padding-top: 10px;
        padding-left: 10px;
        border-radius: 8px;
        margin-bottom: 5px;
    }

    /* 功能列表的设置*/

    .el-tag {
        background-color: #fff !important;
        padding: 0 25px !important;
        margin-right: 6px;
        height: 30px !important;
        line-height: 30px !important;
        font-size: 14px !important;
        font-family: "宋体";
        color: #333 !important;
        border-radius: 4px;
        box-sizing: border-box;
        border: 1px solid rgba(64, 158, 255, .2);
    }

    .middle .list {
        width: 100%;
        margin-top: 10px;
    }

    .gongneng .gongneng_list {
        background-color: #fff;
        color: #333;
        border: 1px solid #ccc;
        padding: 4px 25px !important;
        border-radius: 4px;
    }

    /* 表头居中*/

    .el-table th {
        text-align: left !important;
    }

    .el-table td,
    .el-table th.is-leaf {
        background-color: #f5f7fa;
        text-align: left;
    }

    /* 列表100%显示*/

    .el-table__body,
    .el-table__footer,
    .el-table__header {
        width: 100% !important;
    }

    /* 列表共东条的设置 */

    .el-table__body-wrapper {
        /*height: 270px;*/
        overflow: auto;
    }
    .el-table td,
    .el-table th {
        padding: 7px 0 !important;
    }

    /* 给table  的td加背景色 */

    .el-table__body>tr:nth-child(even)>td {
        background-color: #fff !important;
    }

    /*（奇数行）*/

    .el-table__body>tr:nth-child(odd)>td {
        background-color: #EFF7FF !important;
    }

    /* 分页 */

    .block {
        text-align: center;
        margin-top: 8px;
    }
    .el-pagination__total {
        padding-right: 10px !important;
        padding-left: 10px;
        background-color: #fff;
        border-radius: 5px;
    }

    /* 模态框尺寸 */

    .el-dialog {
        width: 65%;
        height: 75%;
        overflow: auto;
        /*border-radius: 8px;*/
        padding: 5px;
        border: 10px solid #fff;
        /*border-top: 10px solid #f9f9f9;*/
        border-radius: 8px;
    }

    /* 滚动条 */
    .el-dialog::-webkit-scrollbar{  
        width:6px;  
        height:4px;  
        border-radius: 8px;
        margin-right:30px;
    }  
    .el-dialog::-webkit-scrollbar-track{  
        background: #fff;  
        border-radius:2px;  
    }  
    .el-dialog::-webkit-scrollbar-thumb{  
        background: #ccc;  
        border-radius:4px;  
    }  
    .el-dialog::-webkit-scrollbar-corner{  
        background: #fff;  
    }

    .el-dialog__header {
        padding: 0 0 10px 21px !important;
        color: #D9D9D9;
        font: 14px "宋体";
        border-bottom: 1px solid #eee;
        margin-bottom: 5px;
    }
    .el-dialog__title {
        font-size: 16px !important;
    }

    /* tab 栏的样式 */

    .el-dialog__body {
        padding: 0 0 0 20px !important;
    }

    h6 {
        margin: 0;
        font: 16px "宋体";
        border-left: 4px solid #1F92FF;
        padding-left: 10px;
        margin-bottom: 15px;
        margin-top: 8px;
    }
    .title {
        margin: 0;
        font: 16px "宋体";
        border-left: 4px solid #1F92FF;
        padding-left: 10px;
        margin-bottom: 15px;
        margin-top: 8px;
    }
    .gongneng_img {
        float: right;
    }
    .el-tabs__item {
        color: #999999;
        font-size: 16px !important;
    }
    .el-form-item__label {
        width: 90px;
    }
    .el-form-item {
        /*margin-right: 2.5% !important;*/
    }

    .info .info:focus,
    .info:hover,
    .is-active {
        border-left: none !important;
    }

    /* 下一步样式 */

    .el-dialog__footer {
    text-align: center;
    }

    .el-button {
        background-color: #1F92FF;
        font: 14px "宋体";
        color: #fff;
    }
    /* 图框距离上方文字的距离 */
    
    /* 图框的大小 */
    .el-upload-dragger {
        width: 140px;
        height: 140px;
        border: 1px dashed #ccc;
        border-radius: 0 !important;
    }
    /* 图框中的图标的间距*/
    .el-upload-dragger .el-icon-upload {
        margin: 20px 0 0 0 !important;
    }

    .el-upload-dragger .el-icon-upload {
        font-size: 50px !important;
    }

    .state {
        font:14px "宋体";
        color: #333;
        margin-top: 10px;
    }

    .photo {
        text-align: center;
        float: left;
        margin-right: 24px;
        margin-bottom: 16px;
    }
    .photo span {
        font: 14px "宋体";
        color: #999;
    }

    /* 查看信息 */ 
    .deta_info {
        float: left;
    }
    .quester {
        display: inline-block;
        min-width: 100px;
        padding: 8px 0;
        font: 14px "宋体";
        color: #666;
    }
    .answer {
        min-width: 300px;
        padding: 8px 0;
        font: 14px "宋体";
        color: #333;
        display: inline-block;
    }
    /* 遮罩层*/
    .mask1, .mask2, .mask3,  .mask4,  .mask5 {
        position: absolute;
        top: 0px;
        left: 0px;
        width: 100%;
        height: 100%;
        background: #000;
        opacity: 0.5;
        display: none;
        z-index: 1;
        display: none;
    }
    /* 中间区域的位置 */
    .form-add1, .form-add2,.form-add4 {
        z-index: 2;
        width: 64%;
        height: 72%;
        position: fixed;
        top: 10%;
        left: 18%;
        padding-bottom: 20px;
        background: #fff;
        border-radius: 8px;
        border: 10px solid #fff;
        display: none;
        overflow: auto;
        padding-left: 20px;
        padding-right: 20px;
    }

    .form-add3, .form-add5  {
        z-index: 2;
        width: 34%;
        height: 40%;
        position: absolute;
        top: 28%;
        left: 32%;
        background: #fff;
        border-radius: 8px;
        /*border: 10px solid #fff;*/
        display: none;
        overflow: auto;
    }
    .form-add3 .form-add-title span {
        padding: 20px;
    }
    .form-add3 .content {
        text-align: center;
    }
    .form-add3 .content .del_title {
        font-size: 24px;
        color: #333;
        margin-top: 30px;
        margin-bottom: 25px;
    }
    .form-add3 .content .del_con {
        font-size: 16px;
        color: #7a7a7a;
        margin-bottom:60px;
    }
    .form-add5 .content {
        padding-top:43px;
        text-align: center;
        padding-left: 20px;
        font-size: 14px;
        font-family: "宋体";
    }
     .form-add5 .form-add-title span {
        padding: 20px;
    }
    .jk_file {
        margin-left: -14px;
    }
    .form-add5 .content .lianjie {
         margin-top: 13px;
        margin-bottom: 13px;
    }
    .form-add5 .content .lianjie a {
        font-size: 14px;
        text-decoration: none;
        color: #1f92ff;
        margin-left: -40px;
    }
    .daoru_xuanxiang {
        text-align: center;
    }
    .footer {
        text-align: center;
    }
    .footer .decision {
        border: none;
        border: 1px solid #ccc;
        padding: 5px 30px;
        margin-right: 10px;
        cursor: pointer;
        width: 120px;
        height: 32px;
        font-family: "宋体";
        background-color: #f1f1f1;
        outline:none;
        border-radius: 4px;
    }
    .decision a {
        font-size: 14px;
        color: #666;
        text-decoration: none;
    }
  
    .decision_shanchu {
        background-color: #fef0e5 !important;
        border: 1px solid #FF7D20 !important;
        margin-left: 5px;   
    }
    .decision_shanchu a {
        color: #FF7D20;
       
    }
    .form-add5 .footer {
        margin-top: 12px;
    }
    .form-add5 .footer 
    button {
        border: none;
        border: 1px solid #ccc;
        background-color: #f1f1f1;
        border-radius: 4px; 
        outline:none;
    }        
    .form-add1::-webkit-scrollbar{  
        width:6px;  
        height:4px;  
        border-radius: 8px;
        margin-right:30px;
    }  
   .form-add1::-webkit-scrollbar-track{  
        background: #fff;  
        border-radius:2px;  
    }  
   .form-add1::-webkit-scrollbar-thumb{  
        background: #ccc;  
        border-radius:4px;  
    }  
   .form-add1::-webkit-scrollbar-corner{  
        background: #fff;  
    }



    .form-add2::-webkit-scrollbar{  
        width:6px;  
        height:4px;  
        border-radius: 8px;
        margin-right:30px;
    }  
   .form-add2::-webkit-scrollbar-track{  
        background: #fff;  
        border-radius:2px;  
    }  
   .form-add2::-webkit-scrollbar-thumb{  
        background: #ccc;  
        border-radius:4px;  
    }  
   .form-add2::-webkit-scrollbar-corner{  
        background: #fff;  
    }

      .form-add3::-webkit-scrollbar{  
        width:6px;  
        height:4px;  
        border-radius: 8px;
        margin-right:30px;
    }  
   .form-add3::-webkit-scrollbar-track{  
        background: #fff;  
        border-radius:2px;  
    }  
   .form-add3::-webkit-scrollbar-thumb{  
        background: #ccc;  
        border-radius:4px;  
    }  
   .form-add3::-webkit-scrollbar-corner{  
        background: #fff;  
    }

      .form-add4::-webkit-scrollbar{  
        width:6px;  
        height:4px;  
        border-radius: 8px;
        margin-right:30px;
    }  
   .form-add4::-webkit-scrollbar-track{  
        background: #fff;  
        border-radius:2px;  
    }  
   .form-add4::-webkit-scrollbar-thumb{  
        background: #ccc;  
        border-radius:4px;  
    }  
   .form-add4::-webkit-scrollbar-corner{  
        background: #fff;  
    }

      .form-add5::-webkit-scrollbar{  
        width:6px;  
        height:4px;  
        border-radius: 8px;
        margin-right:30px;
    }  
   .form-add5::-webkit-scrollbar-track{  
        background: #fff;  
        border-radius:2px;  
    }  
   .form-add5::-webkit-scrollbar-thumb{  
        background: #ccc;  
        border-radius:4px;  
    }  
   .form-add5::-webkit-scrollbar-corner{  
        background: #fff;  
    }



    .el-dialog__header {
        padding: 0 0 10px 21px !important;
        color: #D9D9D9;
        font: 14px "宋体";
        border-bottom: 1px solid #eee;
        margin-bottom: 5px;
    }
    .el-dialog__title {
        font-size: 16px !important;
    }


    /* 标题的样式 */
    .form-add-title {
        height: 40px;
        line-height: 40px;
        border-bottom: 1px solid #ccc;
        position: relative;
    }
    /* 标题*/
    .form-add-title span {
        font-size: 16px;
        font-family: "微软雅黑";
        color: #333;
        margin-right: 10px;
    }
    /* 放大缩小 */
    .form-add-title .gn_icon {
        float: right;
        margin-right: 65px;
        margin-top: 14px;
    }
    .form-add-title .gn_icon img {
        margin-left: 8px;
    }
    /* 关闭的按钮 */
    .form-add-title .hideClose {
        width: 16px;
        height: 20px;
        position: absolute;
        right: 38px;
        top: 8px;
        cursor: pointer;
        font: 24px "宋体";
        color: #ccc;
    }
    /*.content {
        padding-left: 20px;
        overflow: auto;
    }*/
    .information {
        float: left;
        margin-right: 24px;
    }
    .information span {
        display: inline-block;
        width: 70px;
        padding: 8px 0;
        font: 14px "宋体";
        color: #666;
    }
    .information input {
        width: 180px;
        height: 32px;
    }
    .xuanxiang {
        width: 180px;
        height: 38px;
    }
   /* 下一步 居中*/
   .dialog-footer {
        text-align: center;
        margin-top: 10px;
    }
    .el-button:focus, .el-button:hover {
            background-color: #3a8ee6;
            color: #fff;
            
    }
    .el-button {
        width: 120px;
            height: 32px;
    }
    button.delect {
        padding: 5px 25px;
        border-radius: 5px;
        border: none;
        border: 1px solid #ccc;
        background-color: #fff;
        color: #333;
        font-size: 14px;
        font-family: 宋体;
        cursor:pointer;
    }


    table {
        width: 100%;
        text-align: center;
        font-size: 14px;
        font-family: "微软雅黑";
        border-color:red;
        background-color: #fff;
    }
    th {
        color: #333;
        background-color: #f0f0f0;
        height: 42px;
        line-height: 42px;
    }
    td {
        color: #666;
        height: 42px;
        line-height: 42px;
    }
    .xx {
        min-width: 30px;
    }
    .xh {
        min-width: 50px;
    }
    .user_name {
        min-width: 80px;
    }

    .id_card {
        min-width: 180px;
    }
    .money {
        min-width: 102px;
    }
    .time {
        min-width: 102px;
    }
    .principal {
        min-width: 102px;
    }
    .lx {
        min-width: 80px;
    }
    .risk {
        min-width: 102px;
    }
    .approval {
        min-width: 80px;;
    }
    tr:nth-child(even)>td {
        background-color: #f0f0f0;
    }

    /*（奇数行）*/

    tr:nth-child(odd)>td {
        background-color: #fff;
    }

</style>

