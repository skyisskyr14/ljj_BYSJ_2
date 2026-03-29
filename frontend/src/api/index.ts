import http from './http'

export const api = {
  login: (data:any) => http.post('/api/auth/login', data),
  register: (data:any) => http.post('/api/auth/register', data),
  me: () => http.get('/api/user/me'),
  updateProfile: (data:any) => http.put('/api/user/me', data),
  searchDirectory: (data:any) => http.post('/api/directory/search', data),
  detail: (id:number) => http.get(`/api/directory/${id}`),
  messagePage: (params:any) => http.get('/api/messages', { params }),
  createMessage: (data:any) => http.post('/api/messages', data),
  deleteMessage: (id:number) => http.delete(`/api/messages/${id}`),
  classes: () => http.get('/api/meta/classes'),
  saveClass: (data:any) => http.post('/api/meta/classes', data),
  deleteClass: (id:number) => http.delete(`/api/meta/classes/${id}`),
  tags: () => http.get('/api/meta/tags'),
  saveTag: (data:any) => http.post('/api/meta/tags', data),
  deleteTag: (id:number) => http.delete(`/api/meta/tags/${id}`),
  dashboard: () => http.get('/api/admin/dashboard'),
  adminUsers: (params:any) => http.get('/api/admin/users', { params }),
  updateUserStatus: (id:number, status:number) => http.put(`/api/admin/users/${id}/status`, null, { params: { status } })
}
