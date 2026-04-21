<template>
  <div class="layout-container">
    <!-- 侧边栏 -->
    <div class="sidebar">
      <div class="logo">ERP管理系统</div>
      <el-menu
        :default-active="activeMenu"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
        router
      >
        <el-menu-item index="/home">
          <el-icon><HomeFilled /></el-icon>
          <span>首页</span>
        </el-menu-item>

        <el-sub-menu index="basic">
          <template #title>
            <el-icon><Goods /></el-icon>
            <span>基础数据</span>
          </template>
          <el-menu-item index="/goodsType">商品类型</el-menu-item>
          <el-menu-item index="/goods">商品管理</el-menu-item>
          <el-menu-item index="/supplier">供应商管理</el-menu-item>
          <el-menu-item index="/store">仓库管理</el-menu-item>
          <el-menu-item index="/storeDetail">库存详情</el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="hr">
          <template #title>
            <el-icon><User /></el-icon>
            <span>人事管理</span>
          </template>
          <el-menu-item index="/dept">部门管理</el-menu-item>
          <el-menu-item index="/emp">员工管理</el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="orders">
          <template #title>
            <el-icon><Document /></el-icon>
            <span>订单管理</span>
          </template>
          <el-menu-item index="/orders">采购订单</el-menu-item>
          <el-menu-item index="/returnOrders">退货订单</el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="permission">
          <template #title>
            <el-icon><Lock /></el-icon>
            <span>权限管理</span>
          </template>
          <el-menu-item index="/role">角色管理</el-menu-item>
          <el-menu-item index="/menu">菜单管理</el-menu-item>
          <el-menu-item index="/log">操作日志</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>

    <!-- 主体区域 -->
    <div class="main-container">
      <!-- 头部 -->
      <div class="header">
        <div class="breadcrumb">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{ currentTitle }}</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div class="user-info">
          <span>{{ userStore.userInfo?.name }}</span>
          <el-dropdown @command="handleCommand">
            <el-avatar :size="30" :icon="UserFilled" />
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="profile">个人信息</el-dropdown-item>
                <el-dropdown-item command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>

      <!-- 内容区域 -->
      <div class="content">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ElMessageBox } from 'element-plus'
import { HomeFilled, Goods, User, Document, Lock, UserFilled } from '@element-plus/icons-vue'
import { useUserStore } from '@/store/user'

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()

const activeMenu = computed(() => route.path)
const currentTitle = computed(() => route.meta.title || '')

const handleCommand = (command: string) => {
  if (command === 'logout') {
    ElMessageBox.confirm('确定要退出登录吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      userStore.logout()
      router.push('/login')
    })
  }
}
</script>