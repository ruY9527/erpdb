/**
 * 菜单管理 API
 */
import request from '@/utils/request'
import type { MenuInfo, PageResult, PageParams } from './types'

/**
 * 获取菜单树结构
 */
export function getMenuTree() {
  return request.get<MenuInfo[]>('/menu/tree')
}

/**
 * 分页查询菜单
 * @param params 分页参数
 */
export function getMenuList(params: PageParams) {
  return request.get<PageResult<MenuInfo>>('/menu/page', { params })
}

/**
 * 获取所有菜单列表
 */
export function getMenuAll() {
  return request.get<MenuInfo[]>('/menu/list')
}

/**
 * 根据ID获取菜单详情
 * @param id 菜单ID
 */
export function getMenuById(id: number) {
  return request.get<MenuInfo>(`/menu/get/${id}`)
}

/**
 * 新增菜单
 * @param data 菜单信息
 */
export function addMenu(data: Partial<MenuInfo>) {
  return request.post('/menu/add', data)
}

/**
 * 更新菜单信息
 * @param data 菜单信息
 */
export function updateMenu(data: Partial<MenuInfo>) {
  return request.post('/menu/update', data)
}

/**
 * 删除菜单
 * @param id 菜单ID
 */
export function deleteMenu(id: number) {
  return request.post(`/menu/delete/${id}`)
}

/**
 * 根据用户ID获取菜单权限
 * @param userId 用户ID
 */
export function getMenuByUserId(userId: number) {
  return request.get<MenuInfo[]>(`/menu/user/${userId}`)
}