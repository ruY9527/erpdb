<template>
  <div class="home-container">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="stat-card">
            <div class="stat-icon" style="background: #409EFF">
              <el-icon :size="30"><User /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">100</div>
              <div class="stat-label">员工总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="stat-card">
            <div class="stat-icon" style="background: #67C23A">
              <el-icon :size="30"><Goods /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">500</div>
              <div class="stat-label">商品总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="stat-card">
            <div class="stat-icon" style="background: #E6A23C">
              <el-icon :size="30"><Document /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">200</div>
              <div class="stat-label">订单总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="stat-card">
            <div class="stat-icon" style="background: #F56C6C">
              <el-icon :size="30"><Shop /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">10</div>
              <div class="stat-label">仓库数量</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header>
            <span>销售趋势</span>
          </template>
          <div ref="chartRef" style="height: 300px"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header>
            <span>快捷操作</span>
          </template>
          <div class="quick-actions">
            <el-button type="primary" @click="$router.push('/emp')">员工管理</el-button>
            <el-button type="success" @click="$router.push('/goods')">商品管理</el-button>
            <el-button type="warning" @click="$router.push('/orders')">订单管理</el-button>
            <el-button type="info" @click="$router.push('/store')">仓库管理</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { User, Goods, Document, Shop } from '@element-plus/icons-vue'
import * as echarts from 'echarts'

const chartRef = ref()

onMounted(() => {
  const chart = echarts.init(chartRef.value)
  chart.setOption({
    xAxis: {
      type: 'category',
      data: ['1月', '2月', '3月', '4月', '5月', '6月']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [120, 200, 150, 80, 70, 110],
        type: 'line',
        smooth: true,
        areaStyle: {}
      }
    ]
  })
})
</script>

<style scoped lang="scss">
.home-container {
  .stat-card {
    display: flex;
    align-items: center;
    gap: 15px;

    .stat-icon {
      width: 50px;
      height: 50px;
      border-radius: 8px;
      display: flex;
      justify-content: center;
      align-items: center;
      color: #fff;
    }

    .stat-info {
      .stat-value {
        font-size: 24px;
        font-weight: bold;
        color: #333;
      }
      .stat-label {
        font-size: 14px;
        color: #666;
      }
    }
  }

  .quick-actions {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }
}
</style>