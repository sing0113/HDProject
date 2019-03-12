<template>
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
      <div>
        <i class="el-icon-search"></i>
        <span>筛选搜索</span>
        <el-button style="float: right;" type="primary" size="small" @click="handleSearchList()">查询搜索</el-button>
        <el-button style="float: right; margin-right: 15px" size="small" @click="handleResetSearch()">重置</el-button>
      </div>

      <div style="margin-top: 18px">
        <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
          <el-form-item label="输入搜索：">
            <el-input v-model="listQuery.orderSn" class="input-width" placeholder="订单编号" clearable></el-input>
          </el-form-item>

          <el-form-item label="收货人：">
            <el-input v-model="listQuery.receiverKeyword"
                      class="input-width"
                      placeholder="收货人姓名/手机号码"
                      clearable></el-input>
          </el-form-item>

          <el-form-item label="提交时间：">
            <el-date-picker class="input-width"
                            v-model="listQuery.createTime"
                            value-format="yyyy-MM-dd"
                            type="date"
                            placeholder="请选择时间"
                            clearable></el-date-picker>
          </el-form-item>

          <el-form-item label="订单状态：">
            <el-select v-model="listQuery.status" class="input-width" placeholder="全部" clearable>
              <el-option v-for="item in statusOptions"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="订单分类：">
            <el-select v-model="listQuery.orderType" class="input-width" placeholder="全部" clearable>
              <el-option v-for="item in orderTypeOptions"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="订单来源：">
            <el-select v-model="listQuery.sourceType" class="input-width" placeholder="全部" clearable>
              <el-option v-for="item in sourceTypeOptions"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
    </el-card>

    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
    </el-card>

    <div class="table-container">
      <el-table ref="orderTable"
                style="width: 100%;"
                :data="list"
                @selection-change="handleSelectionChange"
                v-loading="listLoading" border>
        <el-table-column type="selection" width="60" align="center"></el-table-column>
        <el-table-column label="编号" width="80" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="订单编号" width="180" align="center">
          <template slot-scope="scope">{{scope.row.orderSn}}</template>
        </el-table-column>
        <el-table-column label="提交时间" width="180" align="center">
          <template slot-scope="scope">{{scope.row.createTime | formatCreateTime}}</template>
        </el-table-column>
        <el-table-column label="用户账号" align="center">
          <template slot-scope="scope">{{scope.row.memberUsername}}</template>
        </el-table-column>
        <el-table-column label="订单金额" width="120" align="center">
          <template slot-scope="scope">￥{{scope.row.totalAmount}}</template>
        </el-table-column>
        <el-table-column label="支付方式" width="120" align="center">
          <template slot-scope="scope">{{scope.row.payType | formatPayType}}</template>
        </el-table-column>
        <el-table-column label="订单来源" width="120" align="center">
          <template slot-scope="scope">{{scope.row.sourceType | formatSourceType}}</template>
        </el-table-column>
        <el-table-column label="订单状态" width="120" align="center">
          <template slot-scope="scope">{{scope.row.status | formatStatus}}</template>
        </el-table-column>
        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleViewOrder(scope.$index,scope.row)">查看订单</el-button>
            <el-button size="mini"
                       @click="handleCloseOrder(scope.$index,scope.row)"
                       v-show="scope.row.status===0">关闭订单</el-button>
            <el-button size="mini"
                       @click="handleDeliveryOrder(scope.$index,scope.row)"
                       v-show="scope.row.status===1">订单发货</el-button>
            <el-button size="mini"
                       @click="handleViewLogistics(scope.$index,scope.row)"
                       v-show="scope.row.status===2||scope.row.status===3">订单跟踪</el-button>
            <el-button size="mini"
                       type="danger"
                       @click="handleDeleteOrder(scope.$index,scope.row)"
                       v-show="scope.row.status===4">删除订单</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="batch-operate-container">
      <el-select size="small" placeholder="批量操作" v-model="operateType">
        <el-option v-for="item in operateOptions"
                   :key="item.value"
                   :label="item.label"
                   :value="item.value"></el-option>
      </el-select>
      <el-button style="margin-left: 20px"
                 @click="handleBatchOperate()"
                 class="search-button"
                 type="primary"
                 size="small">确定</el-button>
    </div>

    <div class="pagination-container">
      <el-pagination background
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     layout="total,sizes,prev,pager,next,jumper"
                     :current-page.sync="listQuery.pageNum"
                     :page-size="listQuery.pageSize"
                     :page-sizes="[5,10,15]"
                     :total="total"></el-pagination>
    </div>

    <!--<el-dialog title="关闭的订单" width="30%">
      <span style="vertical-align: top">操作备注：</span>
      <el-input style="width: 80%;" type="textarea" placeholder="请输入内容"></el-input>
    </el-dialog>
    <logistics-dialog></logistics-dialog>-->
  </div>
</template>

<script>
  import {fetchList} from '@/api/order'
  import {formatDate} from '@/utils/date'

  const defaultListQuery = {
    pageNum: 1,
    pageSize: 5,
    orderSn: null,
    receiverKeyword: null,
    createTime: null,
    status: null,
    orderType: null,
    sourceType: null
  };
  export default {
    name: 'order',
    data() {
      return {
        listQuery: Object.assign({},defaultListQuery),
        listLoading: true,
        list: null,
        total: null,
        operateType: null,
        multipleSelection: [],
        statusOptions: [
          {
            label: '待付款',
            value: 0
          },
          {
            label: '待发货',
            value: 1
          },
          {
            label: '已发货',
            value: 2
          },
          {
            label: '已完成',
            value: 3
          },
          {
            label: '已关闭',
            value: 4
          },
          {
            label: '无效订单',
            value: 5
          }
        ],
        orderTypeOptions: [
          {
            label: '正常订单',
            value: 0
          },
          {
            label: '秒杀订单',
            value: 1
          }
        ],
        sourceTypeOptions: [
          {
            label: 'PC订单',
            value: 0
          },
          {
            label: 'APP订单',
            value: 1
          }
        ],
        operateOptions: [
          {
            label: '批量发货',
            value: 1
          },
          {
            label: '关闭订单',
            value: 2
          },
          {
            label: '删除订单',
            value: 3
          }
        ]
      }
    },
    created() {
      this.getList();
    },
    filters: {
      formatCreateTime(time) {
        let date = new Date(time);
        return formatDate(date,'yyyy-MM-dd hh:mm:ss')
      },
      formatPayType(value) {
        if (value === 1) {
          return '支付宝';
        } else if (value === 2) {
          return '微信';
        } else {
          return '未支付';
        }
      },
      formatSourceType(value) {
        if (value === 0) {
          return 'PC订单';
        } else if (value === 1) {
          return 'APP订单';
        }
      },
      formatStatus(value) {
        if (value === 0) {
          return '待付款';
        } else if (value === 1) {
          return '待发货';
        } else if (value === 2) {
          return '已发货';
        } else if (value === 3) {
          return '已完成';
        } else if (value === 4) {
          return '已关闭';
        } else if (value === 5) {
          return '无效订单';
        }
      }
    },
    methods: {
      handleSearchList() {
        this.listQuery.pageNum = 1;
        this.getList();
      },
      handleResetSearch() {
        this.listQuery = Object.assign({},defaultListQuery);
      },
      handleSelectionChange(val) {
        /*this.multipleSelection = val;*/
      },
      handleViewOrder(index,row) {
        /*this.$router.push({path:'/oms/orderDetail',query:{id:row.id}});*/
      },
      handleCloseOrder(index,row) {
        /*this.closeOrder.dialogVisible=true;
        this.closeOrder.orderIds=[row.id];*/
      },
      handleDeliveryOrder(index,row) {
        /*let listItem = this.covertOrder(row);
        this.$router.push({path:'/oms/deliverOrderList',query:{list:[listItem]}});*/
      },
      handleViewLogistics(index,row) {
        /*this.logisticsDialogVisible = true;*/
      },
      handleDeleteOrder(index,row) {
        /*let ids = [];
        ids.push(row.id);
        this.deleteOrder(ids);*/
      },
      handleBatchOperate() {

      },
      handleSizeChange(val) {
        this.listQuery.pageNum = 1;
        this.listQuery.pageSize = val;
        this.getList();
      },
      handleCurrentChange(val) {
        this.listQuery.pageNum = val;
        this.getList();
      },
      getList() {
        this.listLoading = true;
        fetchList(this.listQuery).then(response => {
          this.listLoading = false;
          this.list = response.data.list;
          this.total = response.data.total;
        });
      }
    }
  }
</script>

<style scoped>
  .input-width {
    width: 203px;
  }
</style>
