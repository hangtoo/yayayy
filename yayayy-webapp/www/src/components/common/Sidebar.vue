<style scoped>

.sidebar {
    display: block;
    position: absolute;
    width: 220px;
    left: 0;
    top: 70px;
    bottom: 0;
    background: #2E363F;
}

.sidebar > ul {
    height: 100%;
}

</style>

<template>

<div class="sidebar">
    <el-menu :default-active="onRoutes" class="el-menu-vertical-demo" theme="dark" unique-opened router>
        <template v-for="item in items">
            <template v-if="item.subs">
                <el-submenu :index="item.index">
                    <template slot="title"><i :class="item.icon"></i>{{ item.title }}</template>

                    <template v-for="subItem in item.subs">
                        <!-- 三级菜单 -->
                        <template v-if="subItem.subs">

                            <el-submenu :index="subItem.index">
                                <template slot="title"><i :class="subItem.icon"></i>{{ subItem.title }}</template>

                                <template v-for="subsubItem in subItem.subs">

                                    <el-menu-item :index="subsubItem.index">
                                        <i :class="subsubItem.icon"></i>{{ subsubItem.title }}
                                    </el-menu-item>

                                </template>

                            </el-submenu>
                        </template>

                        <!-- 二级菜单 -->
                        <template v-else>
                            <el-menu-item :index="subItem.index">
                                {{ subItem.title }}
                            </el-menu-item>
                        </template>
                    </template>


                </el-submenu>
            </template>
            <!-- 一级菜单 -->
            <template v-else>
                <el-menu-item :index="item.index">
                    <i :class="item.icon"></i>{{ item.title }}
                </el-menu-item>
            </template>
        </template>
    </el-menu>
</div>

</template>

<script>
import http from '@/utils/http';
export default {
    data() {
            return {
                treedataurl: process.env.BASE_API + 'sysMenu/getMenus.do',
                items: []
            }
        },
        computed: {
            onRoutes() {
                return this.$route.path.replace('/', '');
            }
        },
        created() {
            const self = this;
            console.log("created");

            http.post(self.treedataurl, {},response => {
                console.log(response);
                for (let i = 0; i < response.data.length; i++) {
                    this.items.push(response.data[i]);
                }

                return;
            }).catch(err => {
                this.$eventHub.$emit('error', err);
            })

        },
        methods: {


        }


}

</script>
