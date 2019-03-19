<template>
  <div class="app-container">
    <el-card class="goods-container" shadow="never" style="margin-top: 20px">
      <i class="el-icon-tickets"></i>
      <span>发货列表</span>
    </el-card>

    <div style="margin-top: 20px">
      <el-table :data="tableData" border style="windth: 100%;">
        <el-table-column prop="orderSn" label="订单编号" width="180">

        </el-table-column>
        <el-table-column prop="receiverName" label="收货人" width="180">

        </el-table-column>
        <el-table-column prop="receiverPhone" label="手机号码" width="180">

        </el-table-column>
        <el-table-column prop="receiverPostCode" label="邮政编码" width="180">

        </el-table-column>
        <el-table-column prop="receiverDetailAddress" label="收货地址">

        </el-table-column>
        <el-table-column label="配送方式" width="180">
          <template>
            <el-select v-model="value" placeholder="请选择物流公司">
              <el-option v-for="item in deliveryCompany"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value"></el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="物流单号" width="180">
          <template>
            <el-input v-model="deliverySn" ></el-input>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  export default {
    name: "deliverOrder",
    data() {
      return {
        tableData: [
          {
            orderSn: '20180915010200000',
            receiverName: '大梨',
            receiverPhone: '18033441849',
            receiverPostCode: '518000',
            receiverDetailAddress: '广东省深圳市福田区东晓街道'
          }
        ],
        deliveryCompany: [
          {
            value: '顺丰快递',
            label: '顺丰快递'
          },
          {
            value: '圆通快递',
            label: '圆通快递'
          }
        ],
        value: '',
        deliverySn: ''
      }
    }
  }
</script>

<style scoped>

</style>
