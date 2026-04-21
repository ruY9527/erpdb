<template>
  <div class="page-container">
    <!-- 搜索栏 -->
    <div class="search-bar">
      <el-form inline>
        <el-form-item label="日志类型">
          <el-select v-model="searchForm.type" placeholder="请选择类型" clearable style="width: 150px">
            <el-option label="访问日志" value="访问日志" />
            <el-option label="操作日志" value="操作日志" />
            <el-option label="异常日志" value="异常日志" />
          </el-select>
        </el-form-item>
        <el-form-item label="日志标题">
          <el-input v-model="searchForm.title" placeholder="请输入标题" clearable style="width: 200px" />
        </el-form-item>
        <el-form-item label="用户ID">
          <el-input v-model="searchForm.userId" placeholder="请输入用户ID" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="操作时间">
          <el-date-picker
            v-model="dateRange"
            type="daterange"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            value-format="YYYY-MM-DD"
            style="width: 240px"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 操作按钮 -->
    <div style="margin-bottom: 15px">
      <el-button type="danger" @click="handleClear">清空日志</el-button>
      <el-button type="danger" @click="handleBatchDelete" :disabled="selectedRows.length === 0">批量删除</el-button>
    </div>

    <!-- 表格 -->
    <div class="table-container">
      <el-table
        :data="tableData"
        border
        stripe
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column prop="logId" label="日志ID" width="180" />
        <el-table-column prop="type" label="日志类型" width="100">
          <template #default="{ row }">
            <el-tag :type="getTypeColor(row.type)">
              {{ row.type }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="title" label="日志标题" width="150" />
        <el-table-column prop="remoteAddr" label="请求IP" width="130" />
        <el-table-column prop="requestUri" label="请求URL" width="200" show-overflow-tooltip />
        <el-table-column prop="method" label="请求方式" width="80">
          <template #default="{ row }">
            <el-tag :type="row.method === 'GET' ? 'success' : row.method === 'POST' ? 'warning' : 'info'" size="small">
              {{ row.method }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作用户" width="100">
          <template #default="{ row }">
            {{ row.emp?.name || row.userId || '-' }}
          </template>
        </el-table-column>
        <el-table-column prop="operateDate" label="操作时间" width="160" />
        <el-table-column prop="timeout" label="耗时(ms)" width="100" />
        <el-table-column prop="exception" label="异常信息" width="150" show-overflow-tooltip>
          <template #default="{ row }">
            <el-tag v-if="row.exception" type="danger" size="small">
              {{ row.exception }}
            </el-tag>
            <span v-else>-</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="handleView(row)">详情</el-button>
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
    <el-dialog v-model="detailVisible" title="日志详情" width="600px">
      <el-descriptions :column="2" border>
        <el-descriptions-item label="日志ID">{{ currentLog.logId }}</el-descriptions-item>
        <el-descriptions-item label="日志类型">
          <el-tag :type="getTypeColor(currentLog.type)">{{ currentLog.type }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="日志标题">{{ currentLog.title }}</el-descriptions-item>
        <el-descriptions-item label="请求IP">{{ currentLog.remoteAddr }}</el-descriptions-item>
        <el-descriptions-item label="请求URL">{{ currentLog.requestUri }}</el-descriptions-item>
        <el-descriptions-item label="请求方式">
          <el-tag :type="currentLog.method === 'GET' ? 'success' : currentLog.method === 'POST' ? 'warning' : 'info'" size="small">
            {{ currentLog.method }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="操作用户">{{ currentLog.emp?.name || currentLog.userId || '-' }}</el-descriptions-item>
        <el-descriptions-item label="操作时间">{{ currentLog.operateDate }}</el-descriptions-item>
        <el-descriptions-item label="耗时">{{ currentLog.timeout }} ms</el-descriptions-item>
        <el-descriptions-item label="异常信息">
          <el-tag v-if="currentLog.exception" type="danger">{{ currentLog.exception }}</el-tag>
          <span v-else>无</span>
        </el-descriptions-item>
        <el-descriptions-item label="请求参数" :span="2">
          <div style="max-height: 200px; overflow-y: auto">
            <pre style="white-space: pre-wrap; word-break: break-all">{{ formatParams(currentLog.params) }}</pre>
          </div>
        </el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getLogList, deleteLog, deleteLogBatch, clearLog } from '@/api/log'
import type { LogInfo } from '@/api/types'

// 搜索表单
const searchForm = reactive({
  type: '',
  title: '',
  userId: ''
})

// 日期范围
const dateRange = ref<[string, string] | null>(null)

// 分页
const pagination = reactive({
  page: 1,
  rows: 10,
  total: 0
})

// 表格数据
const tableData = ref<LogInfo[]>([])

// 选中的行
const selectedRows = ref<LogInfo[]>([])

// 详情弹窗
const detailVisible = ref(false)
const currentLog = ref<LogInfo>({
  logId: '',
  type: '',
  title: '',
  remoteAddr: '',
  requestUri: '',
  method: '',
  params: '',
  exception: '',
  operateDate: '',
  timeout: '',
  userId: ''
})

// 获取类型颜色
const getTypeColor = (type: string) => {
  if (type === '访问日志') return 'success'
  if (type === '操作日志') return 'warning'
  if (type === '异常日志') return 'danger'
  return 'info'
}

// 格式化参数
const formatParams = (params: string) => {
  if (!params) return '无'
  try {
    const obj = JSON.parse(params)
    return JSON.stringify(obj, null, 2)
  } catch {
    return params
  }
}

// 加载数据
const loadData = async () => {
  try {
    const query = {
      type: searchForm.type,
      title: searchForm.title,
      userId: searchForm.userId,
      startDate: dateRange.value ? dateRange.value[0] : undefined,
      endDate: dateRange.value ? dateRange.value[1] : undefined
    }
    
    const res = await getLogList({ page: pagination.page, rows: pagination.rows }, query)
    if (res && res.data) {
      tableData.value = res.data.rows || []
      pagination.total = res.data.total || 0
    }
  } catch (error) {
    console.error('加载日志数据失败:', error)
  }
}

// 搜索
const handleSearch = () => {
  pagination.page = 1
  loadData()
}

// 重置
const handleReset = () => {
  searchForm.type = ''
  searchForm.title = ''
  searchForm.userId = ''
  dateRange.value = null
  pagination.page = 1
  loadData()
}

// 选择变化
const handleSelectionChange = (rows: LogInfo[]) => {
  selectedRows.value = rows
}

// 查看详情
const handleView = (row: LogInfo) => {
  currentLog.value = row
  detailVisible.value = true
}

// 删除
const handleDelete = async (row: LogInfo) => {
  try {
    await ElMessageBox.confirm('确定要删除该日志吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await deleteLog(row.logId)
    ElMessage.success('删除成功')
    loadData()
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除失败')
    }
  }
}

// 批量删除
const handleBatchDelete = async () => {
  try {
    await ElMessageBox.confirm(`确定要删除选中的 ${selectedRows.value.length} 条日志吗?`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    const ids = selectedRows.value.map(row => row.logId)
    await deleteLogBatch(ids)
    ElMessage.success('批量删除成功')
    loadData()
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('批量删除失败')
    }
  }
}

// 清空日志
const handleClear = async () => {
  try {
    await ElMessageBox.confirm('确定要清空所有日志吗？此操作不可恢复!', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await clearLog()
    ElMessage.success('清空成功')
    loadData()
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('清空失败')
    }
  }
}

// 初始化
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