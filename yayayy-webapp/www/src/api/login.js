import request from '@/utils/request'
import rsa from '@/utils/rsa'
import aes from '@/utils/aes'
import common from '@/utils/common'
import http from '@/utils/http';

export function loginByUsername1(username, password) {
  const data = {
    email:username,
    pwd:password
  }
  return request({
    url: process.env.BASE_API+'sysUser/login.do',
    method: 'post',
    withCredentials : true,
    data
  })
}

export function loginByUsername(username, password) {
  const data = {
    email:username,
    pwd:password
  }

  let keystr=common.getKey(16);
  let secKeyParams={};
  secKeyParams.encSecKey=rsa.encryptPublic(keystr);
  secKeyParams.params=aes.encryptAES(keystr,data);

  return this.$axios({
    method: 'post',
    withCredentials : true,
    baseURL: 'http://localhost:8080',
    url:process.env.BASE_API+'sysUser/login.do',
    data: secKeyParams,
    timeout: 10000,
    headers: {
      'X-Requested-With': 'XMLHttpRequest',
      'Content-Type': 'application/json; charset=UTF-8'
    }
  });


}

export function logout() {
  console.log(process.env.BASE_API);
  return http.post(process.env.BASE_API+ 'sysUser/logout.do', {},(res) => {
      console.log(res.data);
  }).catch(err => {
      console.log(err);
  })
}

// export function getUserInfo(token) {
//   return request({
//     url: '/user/info',
//     method: 'get',
//     params: { token }
//   })
// }

export function getUserInfo(token) {
  console.log(process.env);
  return request({
    url: process.env.BASE_API+'/sysMenu/getMenus.do',
    method: 'post',
    withCredentials : true,
    params1: { token },
    data: {
    }
  })
}
