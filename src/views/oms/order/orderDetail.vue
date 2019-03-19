<template>
  <div class="detail-container">
    <div>
      <el-steps :active="1" finish-status="success" align-center>
        <el-step title="提交订单" description="2018-09-15 12:24:27"></el-step>
        <el-step title="支付订单" description="2018-10-11 14:04:19"></el-step>
        <el-step title="平台发货" description=""></el-step>
        <el-step title="确认收货" description=""></el-step>
        <el-step title="完成评价" description=""></el-step>
      </el-steps>
    </div>

    <el-card shadow="never" style="margin-top: 15px">
      <div class="operate-container">
        <i class="el-icon-warning color-danger" style="margin-left: 20px;"></i>
        <span class="color-danger">当前订单状态：待发货</span>
        <div class="operate-button-container">
          <el-button size="mini">修改收货人信息</el-button>
          <!--<el-button size="mini">修改商品信息</el-button>
          <el-button size="mini">修改费用信息</el-button>-->
          <el-button size="mini">发送站内信</el-button>
          <el-button size="mini">关闭订单</el-button>
          <el-button size="mini">备注订单</el-button>
        </div>
      </div>

      <div style="margin-top: 20px">
        <svg-icon icon-class="marker" style="color: #606266"></svg-icon>
        <span class="font-small">基本信息</span>
      </div>

      <div class="table-layout">
        <el-row>
          <el-col :span="4" class="table-cell-title">订单编号</el-col>
          <el-col :span="4" class="table-cell-title">发货单流水号</el-col>
          <el-col :span="4" class="table-cell-title">用户账号</el-col>
          <el-col :span="4" class="table-cell-title">支付方式</el-col>
          <el-col :span="4" class="table-cell-title">订单来源</el-col>
          <el-col :span="4" class="table-cell-title">订单类型</el-col>
        </el-row>

        <el-row>
          <el-col :span="4" class="table-cell">201809150101000001</el-col>
          <el-col :span="4" class="table-cell">暂无</el-col>
          <el-col :span="4" class="table-cell">test</el-col>
          <el-col :span="4" class="table-cell">未支付</el-col>
          <el-col :span="4" class="table-cell">APP订单</el-col>
          <el-col :span="4" class="table-cell">正常订单</el-col>
        </el-row>

        <el-row>
          <el-col :span="4" class="table-cell-title">配送方式</el-col>
          <el-col :span="4" class="table-cell-title">物流单号</el-col>
          <el-col :span="4" class="table-cell-title">自动确认收货时间</el-col>
          <el-col :span="4" class="table-cell-title">订单可得优币</el-col>
          <el-col :span="4" class="table-cell-title">订单可得成长值</el-col>
          <el-col :span="4" class="table-cell-title">活动信息</el-col>
        </el-row>

        <el-row>
          <el-col :span="4" class="table-cell">暂无</el-col>
          <el-col :span="4" class="table-cell">暂无</el-col>
          <el-col :span="4" class="table-cell">15天</el-col>
          <el-col :span="4" class="table-cell">13284</el-col>
          <el-col :span="4" class="table-cell">13284</el-col>
          <el-col :span="4" class="table-cell">单品促销</el-col>
        </el-row>
      </div>

      <div style="margin-top: 20px">
        <svg-icon icon-class="marker" style="color: #606266"></svg-icon>
        <span class="font-small">收货人信息</span>
      </div>

      <div class="table-layout">
        <el-row>
          <el-col :span="6" class="table-cell-title">收货人</el-col>
          <el-col :span="6" class="table-cell-title">手机号码</el-col>
          <el-col :span="6" class="table-cell-title">邮政编码</el-col>
          <el-col :span="6" class="table-cell-title">收货地址</el-col>
        </el-row>

        <el-row>
          <el-col :span="6" class="table-cell">大梨</el-col>
          <el-col :span="6" class="table-cell">18033441849</el-col>
          <el-col :span="6" class="table-cell">518000</el-col>
          <el-col :span="6" class="table-cell">苏州省 常州市 天宁区 东晓街道</el-col>
        </el-row>
      </div>

      <div style="margin-top: 20px">
        <svg-icon icon-class="marker" style="color: #606266"></svg-icon>
        <span class="font-small">商品信息</span>
      </div>

      <div style="margin-top: 20px">
        <el-table :data="tableData1" border style="width: 100%;">
          <el-table-column prop="productPic" label="商品图片" width="180"></el-table-column>
          <el-table-column prop="productName" label="商品名称" width="180"></el-table-column>
          <el-table-column prop="productPrice" label="价格/货号" width="180"></el-table-column>
          <el-table-column prop="productAttr" label="属性" width="180"></el-table-column>
          <el-table-column prop="productQuantity" label="数量" width="180"></el-table-column>
          <el-table-column prop="productPricexproductQuantity" label="小计" width="180"></el-table-column>
        </el-table>
        <div style="float: right;margin-top: 20px">合计：<span class="color-danger">￥18732</span></div>
      </div>

      <div style="margin-top: 40px">
        <svg-icon icon-class="marker" style="color: #606266;"></svg-icon>
        <span class="font-small">费用信息</span>
      </div>

      <div class="table-layout">
        <el-row>
          <el-col :span="6" class="table-cell-title">商品合计</el-col>
          <el-col :span="6" class="table-cell-title">运费</el-col>
          <el-col :span="6" class="table-cell-title">优惠券</el-col>
          <el-col :span="6" class="table-cell-title">积分抵扣</el-col>
        </el-row>

        <el-row>
          <el-col :span="6" class="table-cell">￥18732</el-col>
          <el-col :span="6" class="table-cell">￥0</el-col>
          <el-col :span="6" class="table-cell">-￥10</el-col>
          <el-col :span="6" class="table-cell">-￥0</el-col>
        </el-row>
      </div>

      <div style="margin-top: 20px">
        <svg-icon icon-class="marker" style="color: #606266;"></svg-icon>
        <span class="font-small">操作信息</span>
      </div>

      <div class="table-layout">
        <el-table :data="tableData2" border style="width: 100%;">
          <el-table-column prop="operateMan" label="操作者"></el-table-column>
          <el-table-column prop="createTime" label="操作时间"></el-table-column>
          <el-table-column prop="orderStatus" label="订单状态"></el-table-column>
          <el-table-column prop="payStatus" label="付款状态"></el-table-column>
          <el-table-column prop="shipStatus" label="发货状态"></el-table-column>
          <el-table-column prop="note" label="备注"></el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "orderDetail",
    data() {
      return {
        tableData1: [
          {
            productPic: '商品图片',
            productName: '华为 P20',
            productPrice: '价格：￥3788',
            productAttr: '颜色：金色；容量：16G',
            productQuantity: '1',
            productPricexproductQuantity: '￥3788',
          }
        ],
        tableData2: [
          {
            operateMan: '后台管理员',
            createTime: '2018-10-16 14:42:17',
            orderStatus: '已发货',
            payStatus: '已支付',
            shipStatus: '已发货',
            note: '完成发货'
          }
        ]
      }
    }
  }
</script>

<style scoped>
  .detail-container {
    width: 80%;
    margin: 20px auto;
    padding: 20px;
  }
  .operate-container {
    background: #F2F6FC;
    height: 80px;
    line-height: 80px;
    margin: -20px -20px 0;
  }
  .operate-button-container {
    float: right;
    margin-right: 20px;
  }
  .table-layout {
    margin-top: 20px;
    border-left: 1px solid #DCDFE6;
    border-top: 1px solid #DCDFE6;
  }
  .table-cell-title {
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    background: #F2F6FC;
    text-align: center;
    font-size: 14px;
    color: #303133;
    padding: 10px;
  }
  .table-cell {
    height: 60px;
    line-height: 40px;
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    text-align: center;
    font-size: 14px;
    color: #303133;
    overflow: hidden;
    padding: 10px;
  }
</style>
