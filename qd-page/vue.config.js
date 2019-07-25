const path = require('path')

const resolve = dir => {
  return path.join(__dirname, dir)
}

// 项目部署基础
// 默认情况下，我们假设你的应用将被部署在域的根目录下,
// 例如：https://www.my-app.com/
// 默认：'/'
// 如果您的应用程序部署在子路径中，则需要在这指定子路径
// 例如：https://www.foobar.com/my-app/
// 需要将它改为'/my-app/'
// iview-admin线上演示打包路径： https://file.iviewui.com/admin-dist/
const BASE_URL = (process.env.NODE_ENV === 'production')
  ? '/'
  : '/'

module.exports = {
  publicPath: BASE_URL,
  // 如果你不需要使用eslint，把lintOnSave设为false即可
  lintOnSave: true,
  chainWebpack: config => {
    config.resolve.alias
      .set('@', resolve('src'))
      .set('_c', resolve('src/components'))
  },
  css: {
    loaderOptions: {
      less: {
        // 解决less3.x版本bezierEasingMixin错误
        javascriptEnabled: true
      }
    }
  },
  // 设为false打包时不生成.map文件
  productionSourceMap: false,
  devServer: {
    host: 'localhost',
    port: '20190',
    // 此处解决开发跨域问题，需要将axios的baseUrl设置为/api，标识接口请求
    // 此处拦截/api请求，然后重定向至target，并再重定向时将/api去除
    proxy: {
      '/api': {
        target: 'http://localhost:20191',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
}
