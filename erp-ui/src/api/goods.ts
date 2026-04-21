/**
 * 商品管理 API
 */
import request from '@/utils/request'
import type { GoodsInfo, PageResult, PageParams } from './types'

/**
 * 分页查询商品
 * @param params 分页参数
 * @param query 查询条件
 */
export function getGoodsList(params: PageParams, query?: Partial<GoodsInfo>) {
  return request.get<PageResult<GoodsInfo>>('/goods/page', { params: { ...params, ...query } })
}

/**
 * 获取所有商品列表
 */
export function getGoodsAll() {
  return request.get<GoodsInfo[]>('/goods/list')
}

/**
 * 根据ID获取商品详情
 * @param id 商品ID
 */
export function getGoodsById(id: number) {
  return request.get<GoodsInfo>(`/goods/get/${id}`)
}

/**
 * 新增商品
 * @param data 商品信息
 */
export function addGoods(data: Partial<GoodsInfo>) {
  return request.post('/goods/add', data)
}

/**
 * 更新商品信息
 * @param data 商品信息
 */
export function updateGoods(data: Partial<GoodsInfo>) {
  return request.post('/goods/update', data)
}

/**
 * 删除商品（逻辑删除）
 * @param id 商品ID
 */
export function deleteGoods(id: number) {
  return request.post(`/goods/delete/${id}`)
}

/**
 * 根据名称搜索商品
 * @param name 商品名称
 */
export function searchGoodsByName(name: string) {
  return request.get<GoodsInfo[]>('/goods/search', { params: { name } })
}