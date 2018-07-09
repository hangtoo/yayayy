<style rel="stylesheet/scss" lang="scss" scoped>

.navbar {
    height: 50px;
    line-height: 50px;
    border-radius: 0 !important;
    .hamburger-container {
        line-height: 58px;
        height: 50px;
        float: left;
        padding: 0 10px;
    }
    .breadcrumb-container {
        float: left;
    }
    .errLog-container {
        display: inline-block;
        vertical-align: top;
    }
    .right-menu {
        float: right;
        height: 100%;
        &:focus {
            outline: none;
        }
        .right-menu-item {
            display: inline-block;
            margin: 0 8px;
        }
        .screenfull {
            height: 20px;
        }
        .international {
            vertical-align: 15px;
        }
        .theme-switch {
            vertical-align: 15px;
        }
        .avatar-container {
            vertical-align: 15px;
            height: 50px;
            margin-right: 30px;
            .avatar-wrapper {
                cursor: pointer;
                margin-top: 5px;
                position: relative;
                .user-avatar {
                    width: 40px;
                    height: 40px;
                    border-radius: 10px;
                }
                .el-icon-caret-bottom {
                    position: absolute;
                    right: -20px;
                    top: 25px;
                    font-size: 12px;
                }
            }
        }
    }
}

</style>

<template>

<div>
    <el-menu class="navbar" mode="horizontal">
        <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>

        <breadcrumb class="breadcrumb-container"></breadcrumb>

        <div class="right-menu">
            <error-log class="errLog-container right-menu-item"></error-log>

            <el-tooltip effect="dark" :content="$t('navbar.screenfull')" placement="bottom">
                <screenfull class="screenfull right-menu-item"></screenfull>
            </el-tooltip>

            <lang-select class="international right-menu-item"></lang-select>

            <el-tooltip effect="dark" :content="$t('navbar.theme')" placement="bottom">
              <theme-picker class="theme-switch right-menu-item"></theme-picker>
            </el-tooltip>

            <el-dropdown class="avatar-container right-menu-item" trigger="click">
                <div class="avatar-wrapper">
                    {{username}}
                    <i class="el-icon-caret-bottom"></i>
                </div>
                <el-dropdown-menu slot="dropdown">
                    <router-link to="/home/dashboard">
                        <el-dropdown-item>
                            {{$t('navbar.dashboard')}}
                        </el-dropdown-item>
                    </router-link>
                    <el-dropdown-item>
                      <span @click="mima" style="display:block;">{{$t('navbar.modifypwd')}}</span>
                    </el-dropdown-item>
                    <el-dropdown-item>
                        <span @click="logout" style="display:block;">{{$t('navbar.logOut')}}</span>
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </el-menu>

    <el-dialog title="修改密码" :visible.sync="dialogPassVisible" size="tiny">
        <el-form ref="editPwdForm" :rules="pwdRules" :model="editPwdForm" label-width="120px">

            <el-form-item :label="$t('login.pwd')">
                <el-input v-model="editPwdForm.pwd" type="password"></el-input>
            </el-form-item>
            <el-form-item :label="$t('login.newpwd')">
                <el-input v-model="editPwdForm.newpwd" type="password"></el-input>
            </el-form-item>
            <el-form-item :label="$t('login.pwdagain')">
                <el-input v-model="editPwdForm.pwdagain" type="password"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="handlePwdSave" :loading="editLoading">{{$t('table.confirm')}}</el-button>
                <el-button @click="dialogPassVisible = false">{{$t('table.cancel')}}</el-button>
            </el-form-item>

        </el-form>
    </el-dialog>
</div>

</template>

<script>

import {
    mapGetters
}
from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import ErrorLog from '@/components/ErrorLog'
import Screenfull from '@/components/Screenfull'
import LangSelect from '@/components/LangSelect'
import ThemePicker from '@/components/ThemePicker'
import http from '@/utils/http';
export default {
    data() {
            return {
                updatepwdurl: process.env.BASE_API + 'sysUser/rePwd.do',
                dialogPassVisible: false,
                editLoading: false,
                editPwdForm: {
                    pwd: '',
                    newpwd: '',
                    pwdagain: '',
                },
                pwdRules: {
                    pwd: [{
                        required: true,
                        message: '请输入旧密码',
                        trigger: 'blur'
                    }],
                    newpwd: [{
                        required: true,
                        message: '请输入新密码',
                        trigger: 'blur'
                    }],
                    pwdagain: [{
                        required: true,
                        message: '请输入密码',
                        trigger: 'blur'
                    }]
                },

            }
        },
        components: {
            Breadcrumb,
            Hamburger,
            ErrorLog,
            Screenfull,
            LangSelect,
            ThemePicker
        },
        computed: {
            ...mapGetters([
                    'sidebar',
                    'name',
                    'avatar'
                ]),
                username() {
                    let username = localStorage.getItem('ms_username');
                    return username ? username : this.name;
                }
        },
        methods: {
            toggleSideBar() {
                    this.$store.dispatch('toggleSideBar')
                },
                logout() {
                    this.$store.dispatch('LogOut').then(() => {
                        this.$router.push('/login');
                        //location.reload()// In order to re-instantiate the vue-router object to avoid bugs
                    })
                },
                mima() {
                    this.pwd = "";
                    this.newpwd = "";
                    this.pwdagain = ""
                    this.dialogPassVisible = true;
                },
                handlePwdSave() {
                    let self = this;
                    if (this.editPwdForm.newpwd != this.editPwdForm.pwdagain) {
                        this.$message.error({
                            title: '错误',
                            message: '两次密码输入不正确'
                        });
                        return;
                    }

                    if (this.editPwdForm.newpwd == this.editPwdForm.pwd) {
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
                                pwd: self.editPwdForm.pwd,
                            }, response => {
                                console.log(response);
                                if (!response.data.success) {
                                    self.$message.error({
                                        message: response.data.msg
                                    });
                                } else {
                                    this.$message.info({
                                        message: response.data.msg
                                    });
                                    this.dialogPassVisible = false;
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
