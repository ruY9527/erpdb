/**
 * 部门管理 API
 */
import request from '@/utils/request'
import type { DeptInfo, PageResult, PageParams } from './types'

/**
 * 分页查询部门
 * @param params 分页参数
 * @param query 查询条件
 */
export function getDeptList(params: PageParams, query?: { name?: string }) {
  return request.get<PageResult<DeptInfo>>('/dept/page', { params: { ...params, ...query } })
}

/**
 * 获取所有部门列表
 */
export function getDeptAll() {
  return request.get<DeptInfo[]>('/dept/list')
}

/**
 * 根据ID获取部门详情
 * @param id 部门ID
 */
export function getDeptById(id: number) {
  return request.get<DeptInfo>(`/dept/get/${id}`)
}

/**
 * 新增部门
 * @param data 部门信息
 */
export function addDept(data: Partial<DeptInfo>) {
  return request.post('/dept/add', data)
}

/**
 * 更新部门信息
 * @param data 部门信息
 */
export function updateDept(data: Partial<DeptInfo>) {
  return request.post('/dept/update', data)
}

/**
 * 删除部门（逻辑删除）
 * @param id 部门ID
 */
export function deleteDept(id: number) {
  return request.post(`/dept/delete/${id}`)
}