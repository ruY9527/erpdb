/**
 * 认证相关 API
 */
import request from '@/utils/request'
import type { LoginParams, LoginResult, EmpInfo } from './types'

/**
 * 用户登录
 * @param data 登录参数
 */
export function login(data: LoginParams) {
  return request.post<LoginResult>('/login', data)
}

/**
 * 获取当前用户信息
 */
export function getUserInfo() {
  return request.get<EmpInfo>('/user/info')
}

/**
 * 修改密码
 * @param eid 员工ID
 * @param pwd 新密码
 */
export function updatePassword(eid: number, pwd: string) {
  return request.post('/emp/updatePwd', { eid, pwd })
}