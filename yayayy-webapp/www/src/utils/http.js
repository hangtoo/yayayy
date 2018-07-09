import Vue from 'vue';
import axios from 'axios'
import qs from 'qs'
import rsa from './rsa'
import aes from './aes'
import common from './common'

export default {
  post (url, data,success) {
    var keystr=common.getKey(16);
    var secKeyParams={};
    secKeyParams.encSecKey=rsa.encryptPublic(keystr);
	  secKeyParams.params=aes.encryptAES(keystr,data);
    return axios({
      method: 'post',
      baseURL: 'http://localhost:8080',
      withCredentials:true,
      url,
      data: secKeyParams,
      timeout: 10000,
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json; charset=UTF-8'
      }
    }).then(
      (response) => {
        if(!!response&&!!response.data&&!!response.data.encSecKey){
          var key=rsa.decryptPrivate(response.data.encSecKey);
          console.log(key);

          var params=aes.decryptAES(key,response.data.params);
          console.log(params);

          var  position=params.indexOf(String.fromCharCode(0));
          if(position!=-1){
            params=params.substr(0,position);
          }
          var paramobj=JSON.parse(params);
          console.log(paramobj);
          response.data=paramobj;
          success(response);
        }
      }
    );
  },
  get (url, params,success) {
    return axios({
      method: 'get',
      baseURL: 'http://localhost:8080',
      url,
      params, // get 请求时带的参数
      timeout: 10000,
      headers: {
        'X-Requested-With': 'XMLHttpRequest'
      }
    }).then(
      (response) => {
        if(!!response&&!!response.data){
          var key=rsa.decryptPrivate(response.data.encSecKey);
          console.log(key);

          var params=aes.decryptAES(key,response.data.params);
          console.log(params);

          var  position=params.indexOf(String.fromCharCode(0));
          if(position!=-1){
            params=params.substr(0,position);
          }
          var paramobj=JSON.parse(params);
          console.log(paramobj);
          response.data=paramobj;
          success(response);
        }
      }
    );
  }
}
