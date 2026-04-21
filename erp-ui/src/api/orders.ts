/**
 * 订单管理 API
 */
import request from '@/utils/request'
import type { OrdersInfo, PageResult, PageParams } from './types'

/**
 * 根据类型和状态查询订单列表
 * @param type 类型：1-采购，2-销售
 * @param state 状态
 */
export function getOrdersList(type: string, state?: string) {
  return request.get<OrdersInfo[]>('/orders/list', { params: { type, state } })
}

/**
 * 分页查询订单
 * @param params 分页参数
 * @param query 查询条件
 */
export function getOrdersPage(params: PageParams, query?: { type?: string; state?: string }) {
  return request.get<PageResult<OrdersInfo>>('/orders/page', { params: { ...params, ...query } })
}

/**
 * 根据ID获取订单详情
 * @param id 订单ID
 */
export function getOrdersById(id: number) {
  return request.get<OrdersInfo>(`/orders/get/${id}`)
}

/**
 * 新增订单
 * @param data 订单信息
 */
export function addOrders(data: Partial<OrdersInfo>) {
  return request.post('/orders/add', data)
}

/**
 * 审核订单
 * @param id 订单ID
 */
export function checkOrders(id: number) {
  return request.post(`/orders/check/${id}`)
}

/**
 * 确认订单
 * @param id 订单ID
 */
export function confirmOrders(id: number) {
  return request.post(`/orders/confirm/${id}`)
}

/**
 * 完成订单（入库/出库）
 * @param id 订单ID
 */
export function completeOrders(id: number) {
  return request.post(`/orders/complete/${id}`)
}

// ========== 退货订单相关 ==========

/**
 * 分页查询退货订单
 * @param params 分页参数
 * @param query 查询条件
 */
export function getReturnOrdersPage(params: PageParams, query?: { type?: string; state?: string }) {
  return request.get<PageResult<any>>('/returnOrders/page', { params: { ...params, ...query } })
}

/**
 * 新增退货订单
 * @param data 退货订单信息
 */
export function addReturnOrders(data: any) {
  return request.post('/returnOrders/add', data)
}

/**
 * 审核退货订单
 * @param id 退货订单ID
 */
export function checkReturnOrders(id: number) {
  return request.post(`/returnOrders/check/${id}`)
}

/**
 * 退货出库
 * @param id 退货订单ID
 */
export function outReturnOrders(id: number) {
  return request.post(`/returnOrders/out/${id}`)
}