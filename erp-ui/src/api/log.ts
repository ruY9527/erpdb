/**
 * 操作日志管理 API
 */
import request from '@/utils/request'
import type { LogInfo, PageResult, PageParams } from './types'

/**
 * 分页查询操作日志
 * @param params 分页参数
 * @param query 查询条件
 */
export function getLogList(params: PageParams, query?: {
  type?: string
  title?: string
  userId?: string
  startDate?: string
  endDate?: string
}) {
  return request.get<PageResult<LogInfo>>('/log/page', { params: { ...params, ...query } })
}

/**
 * 获取所有操作日志
 */
export function getLogAll() {
  return request.get<LogInfo[]>('/log/list')
}

/**
 * 根据ID获取日志详情
 * @param id 日志ID
 */
export function getLogById(id: string) {
  return request.get<LogInfo>(`/log/get/${id}`)
}

/**
 * 删除日志
 * @param id 日志ID
 */
export function deleteLog(id: string) {
  return request.post(`/log/delete/${id}`)
}

/**
 * 批量删除日志
 * @param ids 日志ID列表
 */
export function deleteLogBatch(ids: string[]) {
  return request.post('/log/deleteBatch', ids)
}

/**
 * 清空所有日志
 */
export function clearLog() {
  return request.post('/log/clear')
}

/**
 * 获取日志类型统计
 */
export function getLogStatsByType() {
  return request.get<{ type: string; count: number }[]>('/log/stats/type')
}