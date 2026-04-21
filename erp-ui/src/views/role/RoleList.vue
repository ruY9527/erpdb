<template>
  <div class="page-container">
    <!-- 操作按钮 -->
    <div style="margin-bottom: 15px">
      <el-button type="primary" @click="handleAdd">新增</el-button>
    </div>

    <!-- 表格 -->
    <div class="table-container">
      <el-table :data="tableData" border stripe>
        <el-table-column prop="rid" label="ID" width="80" />
        <el-table-column prop="name" label="角色名称" />
        <el-table-column prop="state" label="状态" width="80">
          <template #default="{ row }">
            <el-tag :type="row.state === '1' ? 'success' : 'danger'">
              {{ row.state === '1' ? '正常' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button type="primary" link @click="handleEdit(row)">编辑</el-button>
            <el-button type="warning" link @click="handlePermission(row)">权限</el-button>
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
        <el-form-item label="角色名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入角色名称" />
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
import { getRoleList, addRole, updateRole, deleteRole } from '@/api'
import type { RoleInfo } from '@/api/types'

const tableData = ref<RoleInfo[]>([])
const dialogVisible = ref(false)
const dialogTitle = ref('新增角色')
const formRef = ref()

const pagination = reactive({
  page: 1,
  rows: 10,
  total: 0
})

const form = reactive<Partial<RoleInfo>>({
  rid: undefined,
  name: ''
})

const rules = {
  name: [{ required: true, message: '请输入角色名称', trigger: 'blur' }]
}

const loadData = async () => {
  const res = await getRoleList({ page: pagination.page, rows: pagination.rows })
  tableData.value = res.data.rows
  pagination.total = res.data.total
}

const handleAdd = () => {
  dialogTitle.value = '新增角色'
  Object.assign(form, { rid: undefined, name: '' })
  dialogVisible.value = true
}

const handleEdit = (row: RoleInfo) => {
  dialogTitle.value = '编辑角色'
  Object.assign(form, row)
  dialogVisible.value = true
}

const handlePermission = (_row: RoleInfo) => {
  ElMessage.info('权限配置功能开发中')
}

const handleDelete = (row: RoleInfo) => {
  ElMessageBox.confirm('确定要删除该角色吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deleteRole(row.rid!)
    ElMessage.success('删除成功')
    loadData()
  })
}

const handleSave = async () => {
  const valid = await formRef.value?.validate()
  if (!valid) return

  if (form.rid) {
    await updateRole(form)
    ElMessage.success('修改成功')
  } else {
    await addRole(form)
    ElMessage.success('添加成功')
  }
  dialogVisible.value = false
  loadData()
}

onMounted(() => {
  loadData()
})
</script>