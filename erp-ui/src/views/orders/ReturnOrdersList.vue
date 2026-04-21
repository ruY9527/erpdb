<template>
  <div class="page-container">
    <!-- 搜索栏 -->
    <div class="search-bar">
      <el-form inline>
        <el-form-item label="订单类型">
          <el-select v-model="searchForm.type" style="width: 120px">
            <el-option label="采购退货" value="1" />
            <el-option label="销售退货" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchForm.state" clearable style="width: 120px">
            <el-option label="待审核" value="0" />
            <el-option label="已审核" value="1" />
            <el-option label="已出库" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 操作按钮 -->
    <div style="margin-bottom: 15px">
      <el-button type="primary" @click="handleAdd">新增退货订单</el-button>
    </div>

    <!-- 表格 -->
    <div class="table-container">
      <el-table :data="tableData" border stripe style="width: 100%" height="calc(100vh - 280px)">
        <el-table-column prop="roid" label="ID" width="80" />
        <el-table-column prop="createtime" label="创建时间" min-width="150" />
        <el-table-column prop="type" label="订单类型" width="100">
          <template #default="{ row }">
            <el-tag :type="row.type === '1' ? 'success' : 'warning'">
              {{ row.type === '1' ? '采购退货' : '销售退货' }}
            </el-tag>
          </template>
        </el-table-column>
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
        <el-table-column prop="state" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="getStateType(row.state)">{{ getStateText(row.state) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createEmp?.name" label="创建人" width="100">
          <template #default="{ row }">
            {{ row.createEmp?.name || '-' }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="handleView(row)">详情</el-button>
            <el-button v-if="row.state === '0'" type="success" link @click="handleCheck(row)">审核</el-button>
            <el-button v-if="row.state === '1'" type="warning" link @click="handleOut(row)">出库</el-button>
            <el-button type="danger" link @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          v-model:current-page="pagination.page"
          v-model:page-size="pagination.rows"
          :total="pagination.total"
          :page-sizes="[10, 20, 50, 100]"
          layout="total, sizes, prev, pager, next"
          @size-change="loadData"
          @current-change="loadData"
        />
      </div>
    </div>

    <!-- 详情弹窗 -->
    <el-dialog v-model="detailVisible" title="退货订单详情" width="600px">
      <el-descriptions :column="2" border>
        <el-descriptions-item label="订单ID">{{ currentOrder.roid }}</el-descriptions-item>
        <el-descriptions-item label="订单类型">
          <el-tag :type="currentOrder.type === '1' ? 'success' : 'warning'">
            {{ currentOrder.type === '1' ? '采购退货' : '销售退货' }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ currentOrder.createtime }}</el-descriptions-item>
        <el-descriptions-item label="状态">
          <el-tag :type="getStateType(currentOrder.state)">{{ getStateText(currentOrder.state) }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="供应商/客户">{{ currentOrder.supplier?.name || '-' }}</el-descriptions-item>
        <el-descriptions-item label="总金额">¥{{ currentOrder.totalmoney }}</el-descriptions-item>
        <el-descriptions-item label="创建人">{{ currentOrder.createEmp?.name || '-' }}</el-descriptions-item>
        <el-descriptions-item label="审核人">{{ currentOrder.checkEmp?.name || '-' }}</el-descriptions-item>
        <el-descriptions-item label="审核时间">{{ currentOrder.checktime || '-' }}</el-descriptions-item>
        <el-descriptions-item label="出库人">{{ currentOrder.endEmp?.name || '-' }}</el-descriptions-item>
        <el-descriptions-item label="出库时间">{{ currentOrder.endtime || '-' }}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>

    <!-- 新增弹窗 -->
    <el-dialog v-model="addVisible" title="新增退货订单" width="500px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="订单类型" prop="type">
          <el-select v-model="form.type" style="width: 100%">
            <el-option label="采购退货" value="1" />
            <el-option label="销售退货" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="供应商/客户" prop="supplierId">
          <el-select v-model="form.supplierId" filterable style="width: 100%">
            <el-option v-for="item in supplierList" :key="item.suid" :label="item.name" :value="item.suid" />
          </el-select>
        </el-form-item>
        <el-form-item label="总金额" prop="totalmoney">
          <el-input-number v-model="form.totalmoney" :precision="2" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="addVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getReturnOrdersList, addReturnOrders, checkReturnOrders, outReturnOrders, deleteReturnOrders } from '@/api/returnOrders'
import { getSupplierList } from '@/api/supplier'
import request from '@/utils/request'
import type { ReturnOrdersInfo } from '@/api/returnOrders'
import type { SupplierInfo } from '@/api/types'

const tableData = ref<ReturnOrdersInfo[]>([])
const supplierList = ref<SupplierInfo[]>([])
const detailVisible = ref(false)
const addVisible = ref(false)
const formRef = ref()
const currentOrder = ref<ReturnOrdersInfo>({} as ReturnOrdersInfo)

const searchForm = reactive({
  type: '1',
  state: ''
})

const pagination = reactive({
  page: 1,
  rows: 10,
  total: 0
})

const form = reactive({
  type: '1',
  supplierId: undefined as number | undefined,
  totalmoney: 0
})

const rules = {
  type: [{ required: true, message: '请选择订单类型', trigger: 'change' }],
  supplierId: [{ required: true, message: '请选择供应商/客户', trigger: 'change' }],
  totalmoney: [{ required: true, message: '请输入总金额', trigger: 'blur' }]
}

const loadData = async () => {
  try {
    const res = await getReturnOrdersList({ page: pagination.page, rows: pagination.rows }, searchForm)
    if (res && res.data) {
      tableData.value = res.data.rows || []
      pagination.total = res.data.total || 0
    }
  } catch (error) {
    ElMessage.error('加载退货订单失败')
  }
}

const loadSupplierList = async () => {
  try {
    const type = searchForm.type === '1' ? '1' : '2'
    const res = await getSupplierList(type)
    if (res && res.data) {
      supplierList.value = res.data
    }
  } catch (error) {
    console.error('加载供应商列表失败:', error)
  }
}

const getStateType = (state: string) => {
  const types: Record<string, string> = { '0': 'warning', '1': 'success', '2': 'info' }
  return types[state] || 'info'
}

const getStateText = (state: string) => {
  const texts: Record<string, string> = { '0': '待审核', '1': '已审核', '2': '已出库' }
  return texts[state] || '未知'
}

const handleSearch = () => {
  pagination.page = 1
  loadData()
}

const handleReset = () => {
  searchForm.state = ''
  handleSearch()
}

const handleAdd = () => {
  loadSupplierList()
  Object.assign(form, { type: searchForm.type, supplierId: undefined, totalmoney: 0 })
  addVisible.value = true
}

const handleView = (row: ReturnOrdersInfo) => {
  currentOrder.value = row
  detailVisible.value = true
}

const handleCheck = (row: ReturnOrdersInfo) => {
  ElMessageBox.confirm('确定审核该退货订单吗？', '提示', { type: 'warning' }).then(async () => {
    try {
      const userRes = await request.get('/emp/current')
      await checkReturnOrders(row.roid, userRes.data.eid)
      ElMessage.success('审核成功')
      loadData()
    } catch (error) {
      ElMessage.error('审核失败')
    }
  })
}

const handleOut = (row: ReturnOrdersInfo) => {
  ElMessageBox.confirm('确定执行退货出库吗？', '提示', { type: 'warning' }).then(async () => {
    try {
      const userRes = await request.get('/emp/current')
      await outReturnOrders(row.roid, userRes.data.eid)
      ElMessage.success('出库成功')
      loadData()
    } catch (error) {
      ElMessage.error('出库失败')
    }
  })
}

const handleDelete = (row: ReturnOrdersInfo) => {
  ElMessageBox.confirm('确定删除该退货订单吗？', '提示', { type: 'warning' }).then(async () => {
    try {
      await deleteReturnOrders(row.roid)
      ElMessage.success('删除成功')
      loadData()
    } catch (error) {
      ElMessage.error('删除失败')
    }
  })
}

const handleSave = async () => {
  const valid = await formRef.value?.validate()
  if (!valid) return

  try {
    await addReturnOrders({
      type: form.type,
      supplierId: form.supplierId!,
      totalmoney: form.totalmoney
    } as Partial<ReturnOrdersInfo>)
    ElMessage.success('添加成功')
    addVisible.value = false
    loadData()
  } catch (error) {
    ElMessage.error('添加失败')
  }
}

onMounted(() => {
  loadData()
})
</script>

<style scoped>
.page-container {
  padding: 20px;
}

.search-bar {
  margin-bottom: 15px;
  padding: 15px;
  background: #fff;
  border-radius: 4px;
}

.table-container {
  background: #fff;
  padding: 15px;
  border-radius: 4px;
}

.pagination {
  margin-top: 15px;
  display: flex;
  justify-content: flex-end;
}
</style>