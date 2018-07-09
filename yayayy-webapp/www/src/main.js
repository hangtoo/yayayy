import Vue from 'vue'
import rsa from './utils/rsa'
import aes from './utils/aes'
import 'normalize.css/normalize.css'// A modern alternative to CSS resets

import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import '@/styles/index.scss' // global css

import App from './App'
import router from './router'
import store from './store'

import i18n from './lang' // Internationalization
import './icons' // icon
import './errorLog'// error log

import * as filters from './filters' // global filters

if (!Array.indexOf) {
    Array.prototype.indexOf = function (obj) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] == obj) {
                return i;
            }
        }
        return -1;
    }
}

Vue.use(Element, {
  size: 'medium', // set element-ui default size
  i18n: (key, value) => i18n.t(key, value)
})


import axios from 'axios';
axios.defaults.withCredentials = true
Vue.prototype.$axios = axios;

// register global utility filters.
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

var vue=new Vue({
  el: '#app',
  router,
  store,
  i18n,
  template: '<App/>',
  components: { App }
})

router.afterEach((to,from,next) => {
	  window.scrollTo(0,0);
});
vue.$on('error', (err)=>{
  let paramobj={};
  if(!!err&&!!err.response&&!!err.response.data&&!!err.response.data.encSecKey){
    let key=rsa.decryptPrivate(err.response.data.encSecKey);
    console.log(key);

    let params=aes.decryptAES(key,err.response.data.params);
    console.log(params);

    let position=params.indexOf(String.fromCharCode(0));
    if(position!=-1){
      params=params.substr(0,position);
    }
    paramobj=JSON.parse(params);
  }

  vue.$message.error({
      title: '错误',
      message: paramobj.msg
  });
  let t;
  clearTimeout(t);
  t = setTimeout(function (){
      vue.$message.closeAll();
      vue.$router.push('/login')
  }, 2000);


} )

Vue.prototype.$eventHub= Vue.prototype.$eventHub ||vue
