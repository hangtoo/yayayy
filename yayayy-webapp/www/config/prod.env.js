let prod = process.argv.splice(2)[0] || 'prod';
let url= "http://dblink.sunfintech.com.cn/sunvue-gateway-webapp/";
if(prod!='prod'){
  url="http://10.0.1.152:9999/sunvue-gateway-webapp/";
}
module.exports = {
  NODE_ENV: '"production"',
  BASE_API: '"'+url+'"',
}
