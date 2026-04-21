<template>
  <div class="page-container">
    <!-- 搜索栏 -->
    <div class="search-bar">
      <el-form inline>
        <el-form-item label="部门名称">
          <el-select v-model="searchForm.name" placeholder="请选择部门" clearable filterable style="width: 200px">
            <el-option v-for="item in deptOptions" :key="item.did" :label="item.name" :value="item.name" />
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
      <el-button type="primary" @click="handleAdd">新增</el-button>
    </div>

    <!-- 表格 -->
    <div class="table-container">
      <el-table :data="tableData" border stripe style="width: 100%" height="calc(100vh - 280px)">
        <el-table-column prop="did" label="ID" width="80" />
        <el-table-column prop="name" label="部门名称" min-width="150" />
        <el-table-column prop="tele" label="联系电话" width="150">
          <template #default="{ row }">
            {{ row.tele || '-' }}
          </template>
        </el-table-column>
        <el-table-column prop="state" label="状态" width="80">
          <template #default="{ row }">
            <el-tag :type="row.state === '1' ? 'success' : 'danger'">
              {{ row.state === '1' ? '正常' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="handleEdit(row)">编辑</el-button>
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

    <!-- 编辑弹窗 -->
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="400px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="部门名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入部门名称" />
        </el-form-item>
        <el-form-item label="联系电话" prop="tele">
          <el-input v-model="form.tele" placeholder="请输入联系电话" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getDeptList, getDeptAll, addDept, updateDept, deleteDept } from '@/api'
import type { DeptInfo } from '@/api/types'

const tableData = ref<DeptInfo[]>([])
const deptOptions = ref<DeptInfo[]>([])
const dialogVisible = ref(false)
const dialogTitle = ref('新增部门')
const formRef = ref()

const searchForm = reactive({
  name: ''
})

const pagination = reactive({
  page: 1,
  rows: 10,
  total: 0
})

const form = reactive<Partial<DeptInfo>>({
  did: undefined,
  name: '',
  tele: ''
})

const rules = {
  name: [{ required: true, message: '请输入部门名称', trigger: 'blur' }]
}

const loadData = async () => {
  const res = await getDeptList({ page: pagination.page, rows: pagination.rows }, searchForm)
  tableData.value = res.data.rows
  pagination.total = res.data.total
}

const loadDeptOptions = async () => {
  const res = await getDeptAll()
  deptOptions.value = res.data
}

const handleSearch = () => {
  pagination.page = 1
  loadData()
}

const handleReset = () => {
  searchForm.name = ''
  handleSearch()
}

const handleAdd = () => {
  dialogTitle.value = '新增部门'
  Object.assign(form, { did: undefined, name: '', tele: '' })
  dialogVisible.value = true
}

const handleEdit = (row: DeptInfo) => {
  dialogTitle.value = '编辑部门'
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleDelete = (row: DeptInfo) => {
  ElMessageBox.confirm('确定要删除该部门吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deleteDept(row.did!)
    ElMessage.success('删除成功')
    loadData()
    loadDeptOptions()
  })
}

const handleSave = async () => {
  const valid = await formRef.value?.validate()
  if (!valid) return

  if (form.did) {
    await updateDept(form)
    ElMessage.success('修改成功')
  } else {
    await addDept(form)
    ElMessage.success('添加成功')
  }
  dialogVisible.value = false
  loadData()
  loadDeptOptions()
}

onMounted(() => {
  loadData()
  loadDeptOptions()
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