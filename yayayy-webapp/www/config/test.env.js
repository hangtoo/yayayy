'use strict'
const merge = require('webpack-merge')
const devEnv = require('./dev.env')

module.exports = merge(devEnv, {
  NODE_ENV: '"testing"',
  BASE_API: '"https://10.0.1.152:9999/sunvue-gateway-webapp/"',
})
