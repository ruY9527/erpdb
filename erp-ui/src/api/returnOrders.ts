/**
 * 退货订单管理 API
 */
import request from '@/utils/request'
import type { PageResult, PageParams } from './types'

/**
 * 退货订单信息
 */
export interface ReturnOrdersInfo {
  roid: number
  ordersOid: number
  createtime: string
  createer: number
  checker: number
  checktime: string
  ender: number
  endtime: string
  supplierId: number
  totalmoney: number
  type: string
  state: string
  createEmp?: any
  checkEmp?: any
  endEmp?: any
  supplier?: any
}

/**
 * 退货订单详情
 */
export interface ReturnOrdersDetailInfo {
  rosd: number
  returnordersId: number
  goodsId: number
  num: number
  price: number
  money: number
  state: string
  endtime: string
  ender: number
  storeId: number
}

/**
 * 分页查询退货订单
 */
export function getReturnOrdersList(params: PageParams, query?: {
  type?: string
  state?: string
  supplierId?: number
}) {
  return request.get<PageResult<ReturnOrdersInfo>>('/returnOrders/page', { params: { ...params, ...query } })
}

/**
 * 获取退货订单列表
 */
export function getReturnOrdersByType(type: string, state?: string) {
  return request.get<ReturnOrdersInfo[]>('/returnOrders/list', { params: { type, state } })
}

/**
 * 根据ID获取退货订单
 */
export function getReturnOrdersById(id: number) {
  return request.get<ReturnOrdersInfo>(`/returnOrders/get/${id}`)
}

/**
 * 获取退货订单详情列表
 */
export function getReturnOrdersDetails(roid: number) {
  return request.get<ReturnOrdersDetailInfo[]>(`/returnOrders/detail/${roid}`)
}

/**
 * 添加退货订单
 */
export function addReturnOrders(data: Partial<ReturnOrdersInfo>) {
  return request.post('/returnOrders/add', data)
}

/**
 * 添加退货订单详情
 */
export function addReturnOrdersDetail(data: ReturnOrdersDetailInfo) {
  return request.post('/returnOrders/detail/add', data)
}

/**
 * 审核退货订单
 */
export function checkReturnOrders(id: number, checker: number) {
  return request.post(`/returnOrders/check/${id}?checker=${checker}`)
}

/**
 * 退货出库
 */
export function outReturnOrders(id: number, ender: number) {
  return request.post(`/returnOrders/out/${id}?ender=${ender}`)
}

/**
 * 删除退货订单
 */
export function deleteReturnOrders(id: number) {
  return request.post(`/returnOrders/delete/${id}`)
}

/**
 * 统计退货订单
 */
export function getReturnOrdersStats() {
  return request.get('/returnOrders/stats')
}