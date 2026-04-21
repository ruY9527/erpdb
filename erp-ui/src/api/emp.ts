/**
 * 员工管理 API
 */
import request from '@/utils/request'
import type { EmpInfo, PageResult, PageParams } from './types'

/**
 * 分页查询员工
 * @param params 分页参数
 * @param query 查询条件
 */
export function getEmpList(params: PageParams, query?: Partial<EmpInfo>) {
  return request.get<PageResult<EmpInfo>>('/emp/page', { params: { ...params, ...query } })
}

/**
 * 获取所有员工列表
 */
export function getEmpAll() {
  return request.get<EmpInfo[]>('/emp/list')
}

/**
 * 根据ID获取员工详情
 * @param id 员工ID
 */
export function getEmpById(id: number) {
  return request.get<EmpInfo>(`/emp/get/${id}`)
}

/**
 * 新增员工
 * @param data 员工信息
 */
export function addEmp(data: Partial<EmpInfo>) {
  return request.post('/emp/add', data)
}

/**
 * 更新员工信息
 * @param data 员工信息
 */
export function updateEmp(data: Partial<EmpInfo>) {
  return request.post('/emp/update', data)
}

/**
 * 删除员工（逻辑删除）
 * @param id 员工ID
 */
export function deleteEmp(id: number) {
  return request.post(`/emp/delete/${id}`)
}

/**
 * 导出员工Excel
 * @param query 查询条件
 */
export function exportEmp(query?: Partial<EmpInfo>) {
  return request.get('/emp/export', { params: query, responseType: 'blob' })
}

/**
 * 导入员工Excel
 * @param file 文件
 */
export function importEmp(file: File) {
  const formData = new FormData()
  formData.append('file', file)
  return request.post('/emp/import', formData, {
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}