import { defineStore } from 'pinia'
import { ref } from 'vue'

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
  dept?: { did?: number; name?: string } | null
}

export const useUserStore = defineStore('user', () => {
  const token = ref<string>(localStorage.getItem('token') || '')
  const userInfo = ref<EmpInfo | null>(null)
  const menus = ref<any[]>([])

  const setToken = (newToken: string) => {
    token.value = newToken
    localStorage.setItem('token', newToken)
  }

  const setUserInfo = (info: EmpInfo) => {
    userInfo.value = info
    localStorage.setItem('userInfo', JSON.stringify(info))
  }

  const setMenus = (menuList: any[]) => {
    menus.value = menuList
  }

  const logout = () => {
    token.value = ''
    userInfo.value = null
    menus.value = []
    localStorage.removeItem('token')
    localStorage.removeItem('userInfo')
  }

  const loadUserInfo = () => {
    const storedInfo = localStorage.getItem('userInfo')
    if (storedInfo) {
      userInfo.value = JSON.parse(storedInfo)
    }
  }

  return {
    token,
    userInfo,
    menus,
    setToken,
    setUserInfo,
    setMenus,
    logout,
    loadUserInfo
  }
})