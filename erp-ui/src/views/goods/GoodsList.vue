<template>
  <div class="page-container">
    <div class="search-bar">
      <el-form inline>
        <el-form-item label="商品名称">
          <el-input v-model="searchForm.name" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div style="margin-bottom: 15px">
      <el-button type="primary" @click="handleAdd">新增</el-button>
    </div>

    <div class="table-container">
      <el-table :data="tableData" border stripe style="width: 100%" height="calc(100vh - 280px)">
        <el-table-column prop="gsid" label="ID" width="80" />
        <el-table-column prop="name" label="商品名称" min-width="150" />
        <el-table-column prop="origin" label="产地" min-width="100" />
        <el-table-column prop="producer" label="厂商" min-width="150" />
        <el-table-column prop="unit" label="单位" width="80" />
        <el-table-column prop="inprice" label="进价" width="100">
          <template #default="{ row }">
            ¥{{ row.inprice }}
          </template>
        </el-table-column>
        <el-table-column prop="outprice" label="售价" width="100">
          <template #default="{ row }">
            ¥{{ row.outprice }}
          </template>
        </el-table-column>
        <el-table-column label="类型" min-width="120">
          <template #default="{ row }">
            {{ row.goodsType?.name || '-' }}
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
          layout="total, sizes, prev, pager, next"
          @size-change="loadData"
          @current-change="loadData"
        />
      </div>
    </div>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="500px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="产地" prop="origin">
          <el-input v-model="form.origin" />
        </el-form-item>
        <el-form-item label="厂商" prop="producer">
          <el-input v-model="form.producer" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" />
        </el-form-item>
        <el-form-item label="进价" prop="inprice">
          <el-input-number v-model="form.inprice" :precision="2" />
        </el-form-item>
        <el-form-item label="售价" prop="outprice">
          <el-input-number v-model="form.outprice" :precision="2" />
        </el-form-item>
        <el-form-item label="类型" prop="goodsTypeId">
          <el-select v-model="form.goodsTypeId" style="width: 100%">
            <el-option v-for="item in typeList" :key="item.gid" :label="item.name" :value="item.gid" />
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
import { getGoodsList, addGoods, updateGoods, deleteGoods, getGoodsTypeList } from '@/api'
import type { GoodsInfo, GoodsTypeInfo } from '@/api/types'

const tableData = ref<GoodsInfo[]>([])
const typeList = ref<GoodsTypeInfo[]>([])
const dialogVisible = ref(false)
const dialogTitle = ref('新增商品')
const formRef = ref()

const searchForm = reactive({ name: '' })
const pagination = reactive({ page: 1, rows: 10, total: 0 })
const form = reactive<Partial<GoodsInfo> & { goodsTypeId?: number }>({
  gsid: undefined,
  name: '',
  origin: '',
  producer: '',
  unit: '',
  inprice: 0,
  outprice: 0,
  goodsTypeId: undefined
})

const rules = {
  name: [{ required: true, message: '请输入商品名称', trigger: 'blur' }]
}

const loadData = async () => {
  const res = await getGoodsList({ page: pagination.page, rows: pagination.rows }, searchForm)
  tableData.value = res.data.rows
  pagination.total = res.data.total
}

const loadTypeList = async () => {
  const res = await getGoodsTypeList()
  typeList.value = res.data
}

const handleSearch = () => { pagination.page = 1; loadData() }
const handleReset = () => { searchForm.name = ''; handleSearch() }
const handleAdd = () => {
  dialogTitle.value = '新增商品'
  Object.assign(form, { gsid: undefined, name: '', origin: '', producer: '', unit: '', inprice: 0, outprice: 0, goodsTypeId: undefined })
  dialogVisible.value = true
}
const handleEdit = (row: GoodsInfo) => {
  dialogTitle.value = '编辑商品'
  Object.assign(form, { ...row, goodsTypeId: row.goodsType?.gid })
  dialogVisible.value = true
}
const handleDelete = (row: GoodsInfo) => {
  ElMessageBox.confirm('确定删除？', '提示', { type: 'warning' }).then(async () => {
    await deleteGoods(row.gsid!)
    ElMessage.success('删除成功')
    loadData()
  })
}
const handleSave = async () => {
  const valid = await formRef.value?.validate()
  if (!valid) return
  if (form.gsid) {
    await updateGoods(form)
    ElMessage.success('修改成功')
  } else {
    await addGoods(form)
    ElMessage.success('添加成功')
  }
  dialogVisible.value = false
  loadData()
}

onMounted(() => { loadData(); loadTypeList() })
</script>