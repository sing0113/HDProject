# hjmall-admin-web

## 说明

>该项目是基于Vue+Element的电商后台管理系统


## 环境配置

需要nodejs vue的支持

### nodejs的安装 

32 位安装包下载地址 : https://nodejs.org/dist/v4.4.3/node-v4.4.3-x86.msi

64 位安装包下载地址 : https://nodejs.org/dist/v4.4.3/node-v4.4.3-x64.msi

下载完成后依照 下一步下一步..... 除安装地址需要按照个人习惯更改外 其他按照默认即可

安装完成后 可在dos中利用 下述命令分别查看安装是否成功 出现版本号即表示安装成功
```
node --version

npm -v
```

### 在本地安装vue 

在dos中执行如下命令即可
```
 npm i -g vue-cli
```
安装完成后 用 vue -V 查看是否安装成功

## idea中相关的配置

1. File --- Settings --- Languages&Frameworks ---   JavaScript : 修改JavaScript language version为ECMAScript 6，确认
2. File --- Settings --- Plugins：搜索vue，安装Vue.js
3. Run --- Edit Configurations...：点击加号，选择npm，Name为Dev，package.json选择你工程中的package.json，Command为run，Scripts为dev，然后点确定。
4. 继续点击加号，选择npm，Name为install，package.json选择你工程中的package.json，Command为install，然后点确定。

## 启动项目

启动之前需要先对项目进行初始化，即先执行install命令 （第一次执行该命令可能需要花费点时间）

对项目初始化之后，即可运行dev命令启动项目了 

## 项目布局

``` lua
src -- 源码目录
├── api -- axios网络请求定义
├── assets -- 静态图片资源文件
├── components -- 通用组件封装
├── icons -- svg矢量图片文件
├── router -- vue-router路由配置
├── store -- vuex的状态管理
├── styles -- 全局css样式
├── utils -- 工具类
└── views -- 前端页面
    ├── home -- 首页
    ├── layout -- 通用页面加载框架
    ├── login -- 登录页
    ├── oms -- 订单模块页面
    ├── pms -- 商品模块页面
    └── sms -- 营销模块页面
```

## 技术选型

技术 | 说明 | 官网
----|----|----
Vue | 前端框架 | [https://vuejs.org/](https://vuejs.org/)
Vue-router | 路由框架 | [https://router.vuejs.org/](https://router.vuejs.org/)
Vuex | 全局状态管理框架 | [https://vuex.vuejs.org/](https://vuex.vuejs.org/)
Element | 前端UI框架 | [https://element.eleme.io/](https://element.eleme.io/)
Axios | 前端HTTP框架 | [https://github.com/axios/axios](https://github.com/axios/axios)
v-charts | 基于Echarts的图表框架 | [https://v-charts.js.org/](https://v-charts.js.org/)
Js-cookie | cookie管理工具 | [https://github.com/js-cookie/js-cookie](https://github.com/js-cookie/js-cookie)
nprogress | 进度条控件 | [https://github.com/rstacruz/nprogress](https://github.com/rstacruz/nprogress)
vue-element-admin | 项目脚手架参考 | [https://github.com/PanJiaChen/vue-element-admin](https://github.com/PanJiaChen/vue-element-admin)

