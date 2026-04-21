/**
 * 角色管理 API
 */
import request from '@/utils/request'
import type { RoleInfo, PageResult, PageParams } from './types'

/**
 * 分页查询角色
 * @param params 分页参数
 */
export function getRoleList(params: PageParams) {
  return request.get<PageResult<RoleInfo>>('/role/page', { params })
}

/**
 * 获取所有角色列表
 */
export function getRoleAll() {
  return request.get<RoleInfo[]>('/role/list')
}

/**
 * 根据ID获取角色详情
 * @param id 角色ID
 */
export function getRoleById(id: number) {
  return request.get<RoleInfo>(`/role/get/${id}`)
}

/**
 * 新增角色
 * @param data 角色信息
 */
export function addRole(data: Partial<RoleInfo>) {
  return request.post('/role/add', data)
}

/**
 * 更新角色信息
 * @param data 角色信息
 */
export function updateRole(data: Partial<RoleInfo>) {
  return request.post('/role/update', data)
}

/**
 * 删除角色
 * @param id 角色ID
 */
export function deleteRole(id: number) {
  return request.post(`/role/delete/${id}`)
}

/**
 * 获取角色的菜单权限
 * @param rid 角色ID
 */
export function getRoleMenus(rid: number) {
  return request.get(`/role/menu/${rid}`)
}

/**
 * 更新角色的菜单权限
 * @param rid 角色ID
 * @param menuIds 菜单ID列表
 */
export function updateRoleMenus(rid: number, menuIds: number[]) {
  return request.post(`/role/menu/${rid}`, { menuIds })
}