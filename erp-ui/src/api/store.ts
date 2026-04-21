/**
 * 仓库管理 API
 */
import request from '@/utils/request'
import type { StoreInfo, StoreDetailInfo, PageResult, PageParams } from './types'

/**
 * 分页查询仓库
 * @param params 分页参数
 * @param query 查询条件
 */
export function getStoreList(params: PageParams, query?: { name?: string }) {
  return request.get<PageResult<StoreInfo>>('/store/page', { params: { ...params, ...query } })
}

/**
 * 获取所有仓库列表
 */
export function getStoreAll() {
  return request.get<StoreInfo[]>('/store/list')
}

/**
 * 根据ID获取仓库详情
 * @param id 仓库ID
 */
export function getStoreById(id: number) {
  return request.get<StoreInfo>(`/store/get/${id}`)
}

/**
 * 新增仓库
 * @param data 仓库信息
 */
export function addStore(data: Partial<StoreInfo>) {
  return request.post('/store/add', data)
}

/**
 * 更新仓库信息
 * @param data 仓库信息
 */
export function updateStore(data: Partial<StoreInfo>) {
  return request.post('/store/update', data)
}

/**
 * 删除仓库
 * @param id 仓库ID
 */
export function deleteStore(id: number) {
  return request.post(`/store/delete/${id}`)
}

// ========== 库存详情相关 ==========

/**
 * 分页查询库存详情
 * @param params 分页参数
 * @param query 查询条件
 */
export function getStoreDetailList(params: PageParams, query?: { storeId?: number; goodsId?: number }) {
  return request.get<PageResult<StoreDetailInfo>>('/storeDetail/page', { params: { ...params, ...query } })
}

/**
 * 根据仓库和商品查询库存
 * @param storeId 仓库ID
 * @param goodsId 商品ID
 */
export function getStoreDetailByGoods(storeId: number, goodsId: number) {
  return request.get<StoreDetailInfo>('/storeDetail/query', { params: { storeId, goodsId } })
}

// ========== 库存预警相关 ==========

/**
 * 获取库存预警列表
 */
export function getStoreAlertList() {
  return request.get('/storeAlert/list')
}

/**
 * 发送库存预警邮件
 */
export function sendStoreAlertEmail() {
  return request.post('/storeAlert/sendEmail')
}