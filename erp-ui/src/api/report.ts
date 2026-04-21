/**
 * 统计报表 API
 */
import request from '@/utils/request'

/**
 * 获取订单统计报表
 * @param startDate 开始日期
 * @param endDate 结束日期
 */
export function getOrderReport(startDate: string, endDate: string) {
  return request.get('/count/orderReport', { params: { startDate, endDate } })
}

/**
 * 获取年度月度销售额统计
 * @param year 年份
 */
export function getYearlySales(year: number) {
  return request.get('/count/getSumMoney', { params: { years: year } })
}

/**
 * 获取销售趋势数据
 */
export function getSalesTrend() {
  return request.get('/count/trend')
}