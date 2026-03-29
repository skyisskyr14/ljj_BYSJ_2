import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const routes = [
  { path: '/login', component: () => import('@/views/auth/LoginView.vue') },
  { path: '/register', component: () => import('@/views/auth/RegisterView.vue') },
  {
    path: '/', component: () => import('@/layouts/MainLayout.vue'), children: [
      { path: '', component: () => import('@/views/home/HomeView.vue') },
      { path: 'directory', component: () => import('@/views/directory/DirectoryView.vue') },
      { path: 'directory/:id', component: () => import('@/views/directory/MemberDetailView.vue') },
      { path: 'wall', component: () => import('@/views/wall/WallView.vue') },
      { path: 'profile', component: () => import('@/views/profile/ProfileView.vue') },
      { path: 'admin', component: () => import('@/views/admin/AdminView.vue') }
    ]
  }
]
const router = createRouter({ history: createWebHistory(), routes })
router.beforeEach((to) => {
  const auth = useAuthStore()
  if (!auth.token && !['/login', '/register'].includes(to.path)) return '/login'
})
export default router
