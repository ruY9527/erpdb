<template>
  <div class="page-container">
    <div class="search-bar">
      <el-form inline>
        <el-form-item label="类型">
          <el-select v-model="searchForm.type" clearable style="width: 120px">
            <el-option label="供应商" value="1" />
            <el-option label="客户" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="searchForm.name" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div style="margin-bottom: 15px">
      <el-button type="primary" @click="handleAdd">新增</el-button>
    </div>
    <div class="table-container">
      <el-table :data="tableData" border stripe>
        <el-table-column prop="suid" label="ID" width="80" />
        <el-table-column prop="name" label="名称" width="150" />
        <el-table-column prop="address" label="地址" />
        <el-table-column prop="contact" label="联系人" width="100" />
        <el-table-column prop="tele" label="电话" width="120" />
        <el-table-column prop="email" label="邮箱" width="180" />
        <el-table-column prop="type" label="类型" width="80">
          <template #default="{ row }">{{ row.type === '1' ? '供应商' : '客户' }}</template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="primary" link @click="handleEdit(row)">编辑</el-button>
            <el-button type="danger" link @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination v-model:current-page="pagination.page" v-model:page-size="pagination.rows" :total="pagination.total" layout="total, prev, pager, next" @current-change="loadData" />
      </div>
    </div>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="500px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name"><el-input v-model="form.name" /></el-form-item>
        <el-form-item label="地址" prop="address"><el-input v-model="form.address" /></el-form-item>
        <el-form-item label="联系人" prop="contact"><el-input v-model="form.contact" /></el-form-item>
        <el-form-item label="电话" prop="tele"><el-input v-model="form.tele" /></el-form-item>
        <el-form-item label="邮箱" prop="email"><el-input v-model="form.email" /></el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" style="width: 100%">
            <el-option label="供应商" value="1" />
            <el-option label="客户" value="2" />
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
import { getSupplierPage, addSupplier, updateSupplier, deleteSupplier } from '@/api'
import type { SupplierInfo } from '@/api/types'

const tableData = ref<SupplierInfo[]>([])
const dialogVisible = ref(false)
const dialogTitle = ref('新增')
const formRef = ref()

const searchForm = reactive({ type: '', name: '' })
const pagination = reactive({ page: 1, rows: 10, total: 0 })
const form = reactive<Partial<SupplierInfo>>({
  suid: undefined,
  name: '',
  address: '',
  contact: '',
  tele: '',
  email: '',
  type: '1'
})
const rules = { name: [{ required: true, message: '请输入名称', trigger: 'blur' }] }

const loadData = async () => {
  const res = await getSupplierPage({ page: pagination.page, rows: pagination.rows }, searchForm)
  tableData.value = res.data.rows
  pagination.total = res.data.total
}
const handleSearch = () => { pagination.page = 1; loadData() }
const handleAdd = () => { dialogTitle.value = '新增'; Object.assign(form, { suid: null, name: '', address: '', contact: '', tele: '', email: '', type: '1' }); dialogVisible.value = true }
const handleEdit = (row: any) => { dialogTitle.value = '编辑'; Object.assign(form, row); dialogVisible.value = true }
const handleDelete = (row: any) => { ElMessageBox.confirm('确定删除？', '提示', { type: 'warning' }).then(async () => { await deleteSupplier(row.suid); ElMessage.success('删除成功'); loadData() }) }
const handleSave = async () => {
  const valid = await formRef.value?.validate()
  if (!valid) return
  if (form.suid) { await updateSupplier(form); ElMessage.success('修改成功') }
  else { await addSupplier(form); ElMessage.success('添加成功') }
  dialogVisible.value = false; loadData()
}
onMounted(() => { loadData() })
</script>