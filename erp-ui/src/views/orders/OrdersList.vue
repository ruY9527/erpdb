<template>
  <div class="page-container">
    <div class="search-bar">
      <el-form inline>
        <el-form-item label="订单类型">
          <el-select v-model="searchForm.type" style="width: 120px">
            <el-option label="采购订单" value="1" />
            <el-option label="销售订单" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchForm.state" clearable style="width: 120px">
            <el-option label="未审核" value="0" />
            <el-option label="已审核" value="1" />
            <el-option label="已确认" value="2" />
            <el-option label="已入库" value="3" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="loadData">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div style="margin-bottom: 15px">
      <el-button type="primary" @click="handleAdd">新增订单</el-button>
    </div>
    <div class="table-container">
      <el-table :data="tableData" border stripe style="width: 100%" height="calc(100vh - 280px)">
        <el-table-column prop="oid" label="ID" width="80" />
        <el-table-column prop="createtime" label="创建时间" min-width="150" />
        <el-table-column prop="supplier?.name" label="供应商/客户" min-width="150">
          <template #default="{ row }">
            {{ row.supplier?.name || '-' }}
          </template>
        </el-table-column>
        <el-table-column prop="totalmoney" label="总金额" width="120">
          <template #default="{ row }">
            ¥{{ row.totalmoney }}
          </template>
        </el-table-column>
        <el-table-column prop="type" label="订单类型" width="100">
          <template #default="{ row }">
            <el-tag :type="row.type === '1' ? 'success' : 'warning'">
              {{ row.type === '1' ? '采购' : '销售' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="state" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="getStateType(row.state)">{{ getStateText(row.state) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="handleView(row)">查看</el-button>
            <el-button v-if="row.state === '0'" type="success" link @click="handleCheck(row)">审核</el-button>
            <el-button v-if="row.state === '1'" type="warning" link @click="handleConfirm(row)">确认</el-button>
            <el-button v-if="row.state === '2'" type="info" link @click="handleComplete(row)">入库</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination v-model:current-page="pagination.page" v-model:page-size="pagination.rows" :total="pagination.total" layout="total, prev, pager, next" @current-change="loadData" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getOrdersPage, checkOrders, confirmOrders, completeOrders } from '@/api'
import type { OrdersInfo } from '@/api/types'

const tableData = ref<OrdersInfo[]>([])
const pagination = reactive({ page: 1, rows: 10, total: 0 })
const searchForm = reactive({ type: '1', state: '' })

const loadData = async () => {
  const res = await getOrdersPage({ page: pagination.page, rows: pagination.rows }, searchForm)
  tableData.value = res.data.rows
  pagination.total = res.data.total
}

const getStateType = (state: string) => {
  const types: Record<string, string> = { '0': 'info', '1': 'warning', '2': 'primary', '3': 'success' }
  return types[state] || 'info'
}

const getStateText = (state: string) => {
  const texts: Record<string, string> = { '0': '未审核', '1': '已审核', '2': '已确认', '3': '已入库' }
  return texts[state] || '未知'
}

const handleAdd = () => { ElMessage.info('新增订单功能开发中') }
const handleView = (_row: any) => { ElMessage.info('查看订单详情功能开发中') }
const handleCheck = (row: any) => { ElMessageBox.confirm('确定审核？', '提示', { type: 'warning' }).then(async () => { await checkOrders(row.oid); ElMessage.success('审核成功'); loadData() }) }
const handleConfirm = (row: any) => { ElMessageBox.confirm('确定确认？', '提示', { type: 'warning' }).then(async () => { await confirmOrders(row.oid); ElMessage.success('确认成功'); loadData() }) }
const handleComplete = (row: any) => { ElMessageBox.confirm('确定入库？', '提示', { type: 'warning' }).then(async () => { await completeOrders(row.oid); ElMessage.success('入库成功'); loadData() }) }

onMounted(() => { loadData() })
</script>