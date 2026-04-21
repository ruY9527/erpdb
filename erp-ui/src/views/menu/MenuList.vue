<template>
  <div class="page-container">
    <div style="margin-bottom: 15px">
      <el-button type="primary" @click="handleAdd">新增</el-button>
    </div>
    <div class="table-container">
      <el-table :data="tableData" border stripe row-key="menuid" default-expand-all>
        <el-table-column prop="menuid" label="ID" width="80" />
        <el-table-column prop="menuname" label="菜单名称" />
        <el-table-column prop="url" label="URL" />
        <el-table-column prop="icon" label="图标" width="100" />
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
        <el-form-item label="菜单名称" prop="menuname">
          <el-input v-model="form.menuname" />
        </el-form-item>
        <el-form-item label="URL" prop="url">
          <el-input v-model="form.url" />
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-input v-model="form.icon" />
        </el-form-item>
        <el-form-item label="上级菜单" prop="pid">
          <el-tree-select v-model="form.pid" :data="menuTree" check-strictly />
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
import { ref, reactive, onMounted, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getMenuTree, addMenu, updateMenu, deleteMenu } from '@/api'
import type { MenuInfo } from '@/api/types'

const tableData = ref<MenuInfo[]>([])
const dialogVisible = ref(false)
const dialogTitle = ref('新增菜单')
const formRef = ref()

const form = reactive<Partial<MenuInfo>>({
  menuid: undefined,
  menuname: '',
  url: '',
  icon: '',
  pid: 0
})

const rules = {
  menuname: [{ required: true, message: '请输入菜单名称', trigger: 'blur' }]
}

const menuTree = computed(() => {
  const root = { value: 0, label: '根菜单', children: [] as any[] }
  tableData.value.forEach(item => {
    root.children.push({ value: item.menuid, label: item.menuname })
  })
  return [root]
})

const loadData = async () => {
  const res = await getMenuTree()
  tableData.value = res.data
}

const handleAdd = () => {
  dialogTitle.value = '新增菜单'
  Object.assign(form, { menuid: undefined, menuname: '', url: '', icon: '', pid: 0 })
  dialogVisible.value = true
}

const handleEdit = (row: MenuInfo) => {
  dialogTitle.value = '编辑菜单'
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleDelete = (row: MenuInfo) => {
  ElMessageBox.confirm('确定要删除该菜单吗？', '提示', {
    type: 'warning'
  }).then(async () => {
    await deleteMenu(row.menuid!)
    ElMessage.success('删除成功')
    loadData()
  })
}

const handleSave = async () => {
  const valid = await formRef.value?.validate()
  if (!valid) return

  if (form.menuid) {
    await updateMenu(form)
    ElMessage.success('修改成功')
  } else {
    await addMenu(form)
    ElMessage.success('添加成功')
  }
  dialogVisible.value = false
  loadData()
}

onMounted(() => {
  loadData()
})
</script>