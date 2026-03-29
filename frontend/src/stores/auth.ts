import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', {
  state: () => ({ token: localStorage.getItem('token') || '', user: null as any }),
  getters: { isAdmin: (s) => s.user?.role === 'ADMIN' },
  actions: {
    setAuth(payload:any) { this.token = payload.token; localStorage.setItem('token', payload.token); this.user = payload },
    logout() { this.token='';this.user=null;localStorage.removeItem('token') }
  }
})
