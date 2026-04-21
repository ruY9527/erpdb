import { createRouter, createWebHistory } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router'
import { useUserStore } from '@/store/user'

const routes: RouteRecordRaw[] = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/',
    name: 'Layout',
    component: () => import('@/views/Layout.vue'),
    redirect: '/home',
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('@/views/Home.vue'),
        meta: { title: '首页' }
      },
      {
        path: 'emp',
        name: 'Emp',
        component: () => import('@/views/emp/EmpList.vue'),
        meta: { title: '员工管理' }
      },
      {
        path: 'dept',
        name: 'Dept',
        component: () => import('@/views/dept/DeptList.vue'),
        meta: { title: '部门管理' }
      },
      {
        path: 'role',
        name: 'Role',
        component: () => import('@/views/role/RoleList.vue'),
        meta: { title: '角色管理' }
      },
      {
        path: 'menu',
        name: 'Menu',
        component: () => import('@/views/menu/MenuList.vue'),
        meta: { title: '菜单管理' }
      },
      {
        path: 'goods',
        name: 'Goods',
        component: () => import('@/views/goods/GoodsList.vue'),
        meta: { title: '商品管理' }
      },
      {
        path: 'goodsType',
        name: 'GoodsType',
        component: () => import('@/views/goods/GoodsTypeList.vue'),
        meta: { title: '商品类型' }
      },
      {
        path: 'supplier',
        name: 'Supplier',
        component: () => import('@/views/supplier/SupplierList.vue'),
        meta: { title: '供应商管理' }
      },
      {
        path: 'store',
        name: 'Store',
        component: () => import('@/views/store/StoreList.vue'),
        meta: { title: '仓库管理' }
      },
      {
        path: 'storeDetail',
        name: 'StoreDetail',
        component: () => import('@/views/store/StoreDetailList.vue'),
        meta: { title: '库存详情' }
      },
      {
        path: 'orders',
        name: 'Orders',
        component: () => import('@/views/orders/OrdersList.vue'),
        meta: { title: '订单管理' }
      },
      {
        path: 'returnOrders',
        name: 'ReturnOrders',
        component: () => import('@/views/orders/ReturnOrdersList.vue'),
        meta: { title: '退货订单' }
      },
      {
        path: 'log',
        name: 'Log',
        component: () => import('@/views/log/LogList.vue'),
        meta: { title: '操作日志' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, _from, next) => {
  const userStore = useUserStore()
  const token = userStore.token
  
  if (to.path === '/login') {
    next()
  } else {
    if (!token) {
      next('/login')
    } else {
      next()
    }
  }
})

export default router