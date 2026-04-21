/**
 * API 类型定义
 */

// 通用分页响应
export interface PageResult<T> {
  total: number
  rows: T[]
}

// 通用响应
export interface Result<T> {
  code: number
  message: string
  data: T
}

// 登录请求
export interface LoginParams {
  username: string
  pwd: string
}

// 登录响应
export interface LoginResult {
  token: string
  emp: EmpInfo
}

// 用户信息
export interface EmpInfo {
  eid?: number
  username: string
  name: string
  gender: number
  email: string
  tele: string
  address: string
  birthday: string
  state?: string
  dept?: Partial<DeptInfo>
}

// 部门信息
export interface DeptInfo {
  did?: number
  name: string
  tele: string
  state: string
}

// 角色信息
export interface RoleInfo {
  rid?: number
  name: string
  state: string
}

// 菜单信息
export interface MenuInfo {
  menuid?: number
  menuname: string
  url: string
  icon: string
  pid: number
  state: string
  menus?: MenuInfo[]
}

// 商品信息
export interface GoodsInfo {
  gsid?: number
  name: string
  origin: string
  producer: string
  unit: string
  inprice: number
  outprice: number
  goodsType?: GoodsTypeInfo
  state: string
}

// 商品类型
export interface GoodsTypeInfo {
  gid?: number
  name: string
  state: string
}

// 供应商/客户
export interface SupplierInfo {
  suid?: number
  name: string
  address: string
  contact: string
  tele: string
  email: string
  type: string // 1:供应商 2:客户
  state: string
}

// 仓库信息
export interface StoreInfo {
  sid?: number
  name: string
  address?: string
  emp?: EmpInfo
  state: string
}

// 库存详情
export interface StoreDetailInfo {
  sdid?: number
  storeId: number
  store?: StoreInfo
  goodsId: number
  goods?: GoodsInfo
  num: number
}

// 订单信息
export interface OrdersInfo {
  oid?: number
  createtime: string
  checktime: string
  starttime: string
  endtime: string
  type: string // 1:采购 2:销售
  createer: number
  checker: number
  starter: number
  ender: number
  supplierId: number
  supplier?: SupplierInfo
  totalmoney: number
  state: string // 0:未审核 1:已审核 2:已确认 3:已入库
  waybillsn: string
}

// 分页请求参数
export interface PageParams {
  page: number
  rows: number
}

// 操作日志信息
export interface LogInfo {
  logId: string
  type: string
  title: string
  remoteAddr: string
  requestUri: string
  method: string
  params: string
  exception: string
  operateDate: string
  timeout: string
  userId: string
  emp?: EmpInfo
}