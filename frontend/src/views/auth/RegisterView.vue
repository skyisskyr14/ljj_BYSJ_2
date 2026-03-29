<template>
  <div class="auth-bg">
    <div class="auth-card page-card">
      <h2>创建账号</h2>
      <el-form :model="form">
        <el-form-item><el-input v-model="form.username" placeholder="用户名" /></el-form-item>
        <el-form-item><el-input v-model="form.nickname" placeholder="昵称" /></el-form-item>
        <el-form-item><el-input v-model="form.password" type="password" placeholder="密码" /></el-form-item>
        <el-button type="primary" @click="submit" style="width:100%">注册并登录</el-button>
      </el-form>
    </div>
  </div>
</template>
<script setup lang="ts">
import { reactive } from 'vue'
import { api } from '@/api'
import { useAuthStore } from '@/stores/auth'
import { useRouter } from 'vue-router'

const form = reactive({ username: '', nickname: '', password: '' })
const auth = useAuthStore()
const router = useRouter()
const submit = async () => {
  const res: any = await api.register(form)
  auth.setAuth(res)
  router.push('/')
}
</script>
<style scoped>
.auth-bg { min-height: 100vh; display: grid; place-items: center; background: radial-gradient(circle at top, #d8e6ff, #f5f8ff); }
.auth-card { width: 400px; }
</style>
