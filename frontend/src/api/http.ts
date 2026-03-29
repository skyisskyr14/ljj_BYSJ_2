import axios from 'axios'
import { useAuthStore } from '@/stores/auth'
import { ElMessage } from 'element-plus'

const http = axios.create({ baseURL: 'http://localhost:8080', timeout: 8000 })
http.interceptors.request.use((config) => {
  const auth = useAuthStore()
  if (auth.token) config.headers.Authorization = `Bearer ${auth.token}`
  return config
})
http.interceptors.response.use((res) => {
  const body = res.data
  if (body.code !== 0) {
    ElMessage.error(body.message || '请求失败')
    return Promise.reject(body)
  }
  return body.data
})
export default http
