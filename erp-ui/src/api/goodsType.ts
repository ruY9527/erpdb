/**
 * 商品类型管理 API
 */
import request from '@/utils/request'
import type { GoodsTypeInfo } from './types'

/**
 * 获取所有商品类型列表
 */
export function getGoodsTypeList() {
  return request.get<GoodsTypeInfo[]>('/goodsType/list')
}

/**
 * 根据ID获取商品类型详情
 * @param id 商品类型ID
 */
export function getGoodsTypeById(id: number) {
  return request.get<GoodsTypeInfo>(`/goodsType/get/${id}`)
}

/**
 * 新增商品类型
 * @param data 商品类型信息
 */
export function addGoodsType(data: Partial<GoodsTypeInfo>) {
  return request.post('/goodsType/add', data)
}

/**
 * 更新商品类型信息
 * @param data 商品类型信息
 */
export function updateGoodsType(data: Partial<GoodsTypeInfo>) {
  return request.post('/goodsType/update', data)
}

/**
 * 删除商品类型
 * @param id 商品类型ID
 */
export function deleteGoodsType(id: number) {
  return request.post(`/goodsType/delete/${id}`)
}