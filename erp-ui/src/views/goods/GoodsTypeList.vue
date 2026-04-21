<template>
  <div class="page-container">
    <div style="margin-bottom: 15px">
      <el-button type="primary" @click="handleAdd">新增</el-button>
    </div>
    <div class="table-container">
      <el-table :data="tableData" border stripe>
        <el-table-column prop="gid" label="ID" width="80" />
        <el-table-column prop="name" label="类型名称" />
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="primary" link @click="handleEdit(row)">编辑</el-button>
            <el-button type="danger" link @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="400px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类型名称" prop="name">
          <el-input v-model="form.name" />
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
import { getGoodsTypeList, addGoodsType, updateGoodsType, deleteGoodsType } from '@/api'
import type { GoodsTypeInfo } from '@/api/types'

const tableData = ref<GoodsTypeInfo[]>([])
const dialogVisible = ref(false)
const dialogTitle = ref('新增类型')
const formRef = ref()

const form = reactive<Partial<GoodsTypeInfo>>({ gid: undefined, name: '' })
const rules = { name: [{ required: true, message: '请输入类型名称', trigger: 'blur' }] }

const loadData = async () => {
  const res = await getGoodsTypeList()
  tableData.value = res.data
}

const handleAdd = () => {
  dialogTitle.value = '新增类型'
  Object.assign(form, { gid: null, name: '' })
  dialogVisible.value = true
}
const handleEdit = (row: any) => {
  dialogTitle.value = '编辑类型'
  Object.assign(form, row)
  dialogVisible.value = true
}
const handleDelete = (row: any) => {
  ElMessageBox.confirm('确定删除？', '提示', { type: 'warning' }).then(async () => {
    await deleteGoodsType(row.gid)
    ElMessage.success('删除成功')
    loadData()
  })
}
const handleSave = async () => {
  const valid = await formRef.value?.validate()
  if (!valid) return
  if (form.gid) {
    await updateGoodsType(form)
    ElMessage.success('修改成功')
  } else {
    await addGoodsType(form)
    ElMessage.success('添加成功')
  }
  dialogVisible.value = false
  loadData()
}

onMounted(() => { loadData() })
</script>