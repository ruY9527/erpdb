/**
 * API 统一导出入口
 * 
 * 模块化拆分说明：
 * - types.ts    : 公共类型定义
 * - auth.ts     : 认证相关（登录、用户信息）
 * - emp.ts      : 员工管理
 * - dept.ts     : 部门管理
 * - role.ts     : 角色管理
 * - menu.ts     : 菜单管理
 * - goods.ts    : 商品管理
 * - goodsType.ts: 商品类型管理
 * - supplier.ts : 供应商/客户管理
 * - store.ts    : 仓库管理、库存详情
 * - orders.ts   : 订单管理、退货订单
 * - report.ts   : 统计报表
 * - log.ts      : 操作日志
 */

// 导出类型
export * from './types'

// 导出各模块 API
export * from './auth'
export * from './emp'
export * from './dept'
export * from './role'
export * from './menu'
export * from './goods'
export * from './goodsType'
export * from './supplier'
export * from './store'
export * from './orders'
export * from './report'
export * from './log'

// 导出 request 工具（供特殊场景使用）
export { default as request } from '@/utils/request'