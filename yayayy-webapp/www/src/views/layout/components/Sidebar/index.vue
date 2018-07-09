<template>
  <scroll-bar>
    <el-menu
      mode="vertical"
      :show-timeout="200"
      :default-active="$route.path"
      :collapse="isCollapse"
      background-color="#304156"
      text-color="#bfcbd9"
      active-text-color="#409EFF"
    >
      <sidebar-item :routes="permission_routers"></sidebar-item>
    </el-menu>
  </scroll-bar>
</template>

<script>
import { mapGetters } from 'vuex'
import SidebarItem from './SidebarItem'
import ScrollBar from '@/components/ScrollBar'
import http from '@/utils/http';
import { asyncRouterMap, constantRouterMap } from '@/router'

export default {
  data () {
    return {
        permission_routers: []
    }
  },
  components: { SidebarItem, ScrollBar },
  mounted(){
    console.log(process.env);

    this.loading = true;
    http.post(process.env.BASE_API + 'sysMenu/getMenus.do',{},response => {

      if(!response||!response.data){
          this.$router.push('/login');
          return;
      }

      let codes=new Array();
      let ele;
      let subele;
      let subsubele;
      for(let n in response.data){
        ele=response.data[n];
        codes.push(ele.code);
        if(ele.subs){
          for(let m in ele.subs){
            subele=ele.subs[m];
            codes.push(subele.code);

            if(subele.subs){
              for(let o in subele.subs){
                subsubele=subele.subs[o];
                codes.push(subsubele.code);
              }
            }

          }
        }
      }
      console.log(codes);
      let tmp=constantRouterMap;
      for(let i in tmp){
        if(tmp[i].name=='dashboard'||codes.indexOf(tmp[i].name)==-1){
          console.log(tmp[i].name);
          tmp[i].hidden=true;
        }
        let children=tmp[i].children;
        if(children&&children.length>0){

          for(let j in children){
            if(children[j].name=='tinymce-demo'||codes.indexOf(tmp[i].name)==-1){
              console.log(tmp[i].name);
              children[j].hidden=true;
            }
          }

        }

      }
      //console.log(tmp);
      this.permission_routers=tmp;

      this.loading = false
    }).catch(err => {
       this.loading = false
    });
  },
  computed: {
    ...mapGetters([
      //'permission_routers',
      'sidebar'
    ]),
    isCollapse() {
      return !this.sidebar.opened
    }
  }
}
</script>
