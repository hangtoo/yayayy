<template>
    <div class="header">
        <div class="logo">直销银行对接服务平台</div>
        <div class="user-info">
            <el-dropdown trigger="click" @command="handleCommand">
                <span class="el-dropdown-link">
                    <img class="user-logo" src="../../../static/img/img.jpg">
                    {{username}}
                </span>
                <el-dropdown-menu slot="dropdown">
                	<el-dropdown-item command="mima">修改密码</el-dropdown-item>
                    <el-dropdown-item command="loginout">退出</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>


        <el-dialog   v-model="dialogPassVisible" size="tiny">
			<el-form ref="editPwdForm"  :rules="pwdRules" :model="editPwdForm" label-width="80px">

			<el-form-item label="旧密码">
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
                <el-button @click="dialogPassVisible = false">取消</el-button>
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
            	updatepwdurl: process.env.BASE_API + 'sysUser/rePwd.do',
                name: '',
                dialogPassVisible: false,
                passloading: false,
                editLoading: false,
                editPwdForm: {
                	pwd:'',
                	newpwd:'',
                	pwdagain:'',
                },
                pwdRules:{
                  	pwd: [
                        { required: true, message: '请输入旧密码', trigger: 'blur' }
                    ],
                    newpwd: [
                        { required: true, message: '请输入新密码', trigger: 'blur' }
                    ],
                    pwdagain: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                },
            }
        },
        computed:{
            username(){
                let username = localStorage.getItem('ms_username');
                return username ? username : this.name;
            }
        },
        methods:{
            handleCommand(command) {
                if(command == 'loginout'){
                    localStorage.removeItem('ms_username')
                    this.$router.push('/login');
                }
                if(command == 'mima'){
                    this.pwd="";
                    this.newpwd="";
                    this.pwdagain=""
                    this.dialogPassVisible=true;
                }
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

                  if(this.editPwdForm.newpwd==this.editPwdForm.pwd){
                    this.$message.error({
                          title: '错误',
                          message: '新密码不能与旧密码相同'
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






        },
    }
</script>
<style scoped>
    .header {
        position: relative;
        box-sizing: border-box;
        width: 100%;
        height: 70px;
        font-size: 22px;
        line-height: 70px;
        color: #fff;
    }
    .header .logo{
        float: left;
        width:250px;
        text-align: center;
    }
    .user-info {
        float: right;
        padding-right: 50px;
        font-size: 16px;
        color: #fff;
    }
    .user-info .el-dropdown-link{
        position: relative;
        display: inline-block;
        padding-left: 50px;
        color: #fff;
        cursor: pointer;
        vertical-align: middle;
    }
    .user-info .user-logo{
        position: absolute;
        left:0;
        top:15px;
        width:40px;
        height:40px;
        border-radius: 50%;
    }
    .el-dropdown-menu__item{
        text-align: center;
    }
</style>
