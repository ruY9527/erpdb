/**
 * 供应商/客户管理 API
 */
import request from '@/utils/request'
import type { SupplierInfo, PageResult, PageParams } from './types'

/**
 * 根据类型获取供应商/客户列表
 * @param type 类型：1-供应商，2-客户
 */
export function getSupplierList(type: string) {
  return request.get<SupplierInfo[]>(`/supplier/list/${type}`)
}

/**
 * 分页查询供应商/客户
 * @param params 分页参数
 */
export function getSupplierPage(params: PageParams, query?: { type?: string; name?: string }) {
  return request.get<PageResult<SupplierInfo>>('/supplier/page', { params: { ...params, ...query } })
}

/**
 * 根据ID获取供应商/客户详情
 * @param id 供应商/客户ID
 */
export function getSupplierById(id: number) {
  return request.get<SupplierInfo>(`/supplier/get/${id}`)
}

/**
 * 新增供应商/客户
 * @param data 供应商/客户信息
 */
export function addSupplier(data: Partial<SupplierInfo>) {
  return request.post('/supplier/add', data)
}

/**
 * 更新供应商/客户信息
 * @param data 供应商/客户信息
 */
export function updateSupplier(data: Partial<SupplierInfo>) {
  return request.post('/supplier/update', data)
}

/**
 * 删除供应商/客户
 * @param id 供应商/客户ID
 */
export function deleteSupplier(id: number) {
  return request.post(`/supplier/delete/${id}`)
}