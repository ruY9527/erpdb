<template>
  <div class="page-container">
    <!-- 搜索栏 -->
    <div class="search-bar">
      <el-form inline>
        <el-form-item label="仓库">
          <el-select v-model="searchForm.storeId" placeholder="请选择仓库" clearable style="width: 150px">
            <el-option v-for="item in storeList" :key="item.sid" :label="item.name" :value="item.sid" />
          </el-select>
        </el-form-item>
        <el-form-item label="商品">
          <el-select v-model="searchForm.goodsId" placeholder="请选择商品" clearable filterable style="width: 200px">
            <el-option v-for="item in goodsList" :key="item.gsid" :label="item.name" :value="item.gsid" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 统计卡片 -->
    <div class="stats-container">
      <el-card class="stat-card">
        <div class="stat-content">
          <div class="stat-title">仓库总数</div>
          <div class="stat-value">{{ stats.totalStores }}</div>
        </div>
      </el-card>
      <el-card class="stat-card">
        <div class="stat-content">
          <div class="stat-title">商品种类</div>
          <div class="stat-value">{{ stats.totalGoods }}</div>
        </div>
      </el-card>
      <el-card class="stat-card">
        <div class="stat-content">
          <div class="stat-title">库存记录</div>
          <div class="stat-value">{{ stats.totalItems }}</div>
        </div>
      </el-card>
    </div>

    <!-- 表格 -->
    <div class="table-container">
      <el-table :data="tableData" border stripe style="width: 100%" height="calc(100vh - 380px)">
        <el-table-column prop="sdid" label="ID" width="80" />
        <el-table-column prop="store.name" label="仓库" min-width="150">
          <template #default="{ row }">
            {{ row.store?.name || '-' }}
          </template>
        </el-table-column>
        <el-table-column prop="goods.name" label="商品" min-width="200">
          <template #default="{ row }">
            {{ row.goods?.name || '-' }}
          </template>
        </el-table-column>
        <el-table-column prop="goods.origin" label="产地" min-width="120">
          <template #default="{ row }">
            {{ row.goods?.origin || '-' }}
          </template>
        </el-table-column>
        <el-table-column prop="goods.unit" label="单位" width="80">
          <template #default="{ row }">
            {{ row.goods?.unit || '-' }}
          </template>
        </el-table-column>
        <el-table-column prop="goods.inprice" label="进价" width="100">
          <template #default="{ row }">
            {{ row.goods?.inprice ? `¥${row.goods.inprice}` : '-' }}
          </template>
        </el-table-column>
        <el-table-column prop="goods.outprice" label="售价" width="100">
          <template #default="{ row }">
            {{ row.goods?.outprice ? `¥${row.goods.outprice}` : '-' }}
          </template>
        </el-table-column>
        <el-table-column prop="num" label="库存数量" width="100">
          <template #default="{ row }">
            <el-tag :type="row.num > 100 ? 'success' : row.num > 50 ? 'warning' : 'danger'">
              {{ row.num }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="库存金额" min-width="120">
          <template #default="{ row }">
            {{ row.goods?.inprice && row.num ? `¥${(row.goods.inprice * row.num).toFixed(2)}` : '-' }}
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
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { getStoreDetailList, getStoreAll } from '@/api/store'
import { getGoodsAll } from '@/api/goods'
import request from '@/utils/request'
import type { StoreDetailInfo, StoreInfo, GoodsInfo } from '@/api/types'

// 表格数据
const tableData = ref<StoreDetailInfo[]>([])

// 仓库列表
const storeList = ref<StoreInfo[]>([])

// 商品列表
const goodsList = ref<GoodsInfo[]>([])

// 统计数据
const stats = reactive({
  totalStores: 0,
  totalGoods: 0,
  totalItems: 0
})

// 搜索表单
const searchForm = reactive({
  storeId: undefined as number | undefined,
  goodsId: undefined as number | undefined
})

// 分页
const pagination = reactive({
  page: 1,
  rows: 10,
  total: 0
})

// 加载库存详情数据
const loadData = async () => {
  try {
    const res = await getStoreDetailList(
      { page: pagination.page, rows: pagination.rows },
      {
        storeId: searchForm.storeId,
        goodsId: searchForm.goodsId
      }
    )
    if (res && res.data) {
      tableData.value = res.data.rows || []
      pagination.total = res.data.total || 0
    }
  } catch (error) {
    console.error('加载库存详情失败:', error)
    ElMessage.error('加载库存详情失败')
  }
}

// 加载仓库列表
const loadStoreList = async () => {
  try {
    const res = await getStoreAll()
    if (res && res.data) {
      storeList.value = res.data
    }
  } catch (error) {
    console.error('加载仓库列表失败:', error)
  }
}

// 加载商品列表
const loadGoodsList = async () => {
  try {
    const res = await getGoodsAll()
    if (res && res.data) {
      goodsList.value = res.data
    }
  } catch (error) {
    console.error('加载商品列表失败:', error)
  }
}

// 加载统计数据
const loadStats = async () => {
  try {
    const res = await request.get('/storeDetail/stats')
    if (res && res.data) {
      stats.totalStores = res.data.totalStores || 0
      stats.totalGoods = res.data.totalGoods || 0
      stats.totalItems = res.data.totalItems || 0
    }
  } catch (error) {
    console.error('加载统计数据失败:', error)
  }
}

// 搜索
const handleSearch = () => {
  pagination.page = 1
  loadData()
}

// 重置
const handleReset = () => {
  searchForm.storeId = undefined
  searchForm.goodsId = undefined
  pagination.page = 1
  loadData()
}

// 初始化
onMounted(() => {
  loadData()
  loadStoreList()
  loadGoodsList()
  loadStats()
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

.stats-container {
  display: flex;
  gap: 15px;
  margin-bottom: 15px;
}

.stat-card {
  width: 200px;
}

.stat-content {
  text-align: center;
}

.stat-title {
  font-size: 14px;
  color: #909399;
  margin-bottom: 10px;
}

.stat-value {
  font-size: 28px;
  font-weight: bold;
  color: #409eff;
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