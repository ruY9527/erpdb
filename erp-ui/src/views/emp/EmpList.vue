<template>
  <div class="page-container">
    <!-- 搜索栏 -->
    <div class="search-bar">
      <el-form inline>
        <el-form-item label="用户名">
          <el-input v-model="searchForm.username" placeholder="请输入用户名" clearable />
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="searchForm.name" placeholder="请输入姓名" clearable />
        </el-form-item>
        <el-form-item label="部门">
          <el-select v-model="searchForm.deptId" placeholder="请选择部门" clearable style="width: 150px">
            <el-option v-for="item in deptList" :key="item.did" :label="item.name" :value="item.did" />
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
      <el-table :data="tableData" border stripe>
        <el-table-column prop="eid" label="ID" width="80" />
        <el-table-column prop="username" label="用户名" width="120" />
        <el-table-column prop="name" label="姓名" width="120" />
        <el-table-column prop="gender" label="性别" width="80">
          <template #default="{ row }">
            {{ row.gender === 1 ? '男' : '女' }}
          </template>
        </el-table-column>
        <el-table-column prop="email" label="邮箱" width="180" />
        <el-table-column prop="tele" label="电话" width="120" />
        <el-table-column prop="address" label="地址" />
        <el-table-column prop="birthday" label="生日" width="120" />
        <el-table-column prop="dept.name" label="部门" width="120" />
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
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="500px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="form.gender">
            <el-radio :value="1">男</el-radio>
            <el-radio :value="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="电话" prop="tele">
          <el-input v-model="form.tele" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker v-model="form.birthday" type="date" placeholder="请选择生日" value-format="YYYY-MM-DD" />
        </el-form-item>
        <el-form-item label="部门" prop="deptId">
          <el-select v-model="form.deptId" placeholder="请选择部门" style="width: 100%">
            <el-option v-for="item in deptList" :key="item.did" :label="item.name" :value="item.did" />
          </el-select>
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
import { getEmpList, addEmp, updateEmp, deleteEmp, getDeptAll } from '@/api'
import type { EmpInfo, DeptInfo } from '@/api/types'

const tableData = ref<EmpInfo[]>([])
const deptList = ref<DeptInfo[]>([])
const dialogVisible = ref(false)
const dialogTitle = ref('新增员工')
const formRef = ref()

const searchForm = reactive({
  username: '',
  name: '',
  deptId: undefined as number | undefined
})

const pagination = reactive({
  page: 1,
  rows: 10,
  total: 0
})

const form = reactive<Partial<EmpInfo> & { deptId?: number }>({
  eid: undefined,
  username: '',
  name: '',
  gender: 1,
  email: '',
  tele: '',
  address: '',
  birthday: '',
  deptId: undefined
})

const rules = {
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
  gender: [{ required: true, message: '请选择性别', trigger: 'change' }]
}

const loadData = async () => {
  const res = await getEmpList({ page: pagination.page, rows: pagination.rows }, searchForm)
  tableData.value = res.data.rows
  pagination.total = res.data.total
}

const loadDeptList = async () => {
  const res = await getDeptAll()
  deptList.value = res.data
}

const handleSearch = () => {
  pagination.page = 1
  loadData()
}

const handleReset = () => {
  searchForm.username = ''
  searchForm.name = ''
  searchForm.deptId = undefined
  handleSearch()
}

const handleAdd = () => {
  dialogTitle.value = '新增员工'
  Object.assign(form, {
    eid: undefined,
    username: '',
    name: '',
    gender: 1,
    email: '',
    tele: '',
    address: '',
    birthday: '',
    deptId: undefined
  })
  dialogVisible.value = true
}

const handleEdit = (row: EmpInfo) => {
  dialogTitle.value = '编辑员工'
  Object.assign(form, {
    ...row,
    deptId: row.dept?.did
  })
  dialogVisible.value = true
}

const handleDelete = (row: EmpInfo) => {
  ElMessageBox.confirm('确定要删除该员工吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deleteEmp(row.eid!)
    ElMessage.success('删除成功')
    loadData()
  })
}

const handleSave = async () => {
  const valid = await formRef.value?.validate()
  if (!valid) return

  const submitData = { ...form, dept: { did: form.deptId } as Partial<DeptInfo> }
  
  if (form.eid) {
    await updateEmp(submitData)
    ElMessage.success('修改成功')
  } else {
    await addEmp(submitData)
    ElMessage.success('添加成功')
  }
  dialogVisible.value = false
  loadData()
}

onMounted(() => {
  loadData()
  loadDeptList()
})
</script>