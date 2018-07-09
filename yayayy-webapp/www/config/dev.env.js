var merge = require('webpack-merge')
var prodEnv = require('./prod.env')

// module.exports = merge(prodEnv, {
//   NODE_ENV: '"development"',
//   BASE_API: '"http://wx.sunfintech.com.cn/sunvue-gateway-webapp"',
// })

module.exports = {
  NODE_ENV: '"development"',
  //BASE_API: '"http://localhost:9999/"',
  BASE_API: '"http://10.0.1.152:9999/sunvue-gateway-webapp/"',
}