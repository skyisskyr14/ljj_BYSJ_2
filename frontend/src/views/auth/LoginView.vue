<template>
  <div class="auth-bg"><div class="auth-card page-card"><h2>欢迎回到云同学录</h2>
    <el-form :model="form" @submit.prevent>
      <el-form-item><el-input v-model="form.username" placeholder="用户名"/></el-form-item>
      <el-form-item><el-input v-model="form.password" type="password" placeholder="密码"/></el-form-item>
      <el-button type="primary" @click="submit" style="width:100%">登录</el-button>
      <el-button link @click="$router.push('/register')">没有账号？去注册</el-button>
    </el-form></div></div>
</template>
<script setup lang="ts">
import { reactive } from 'vue';import { api } from '@/api';import { useAuthStore } from '@/stores/auth';import { useRouter } from 'vue-router'
const router=useRouter();const auth=useAuthStore();const form=reactive({username:'admin',password:'Admin@123'})
const submit=async()=>{const res:any=await api.login(form);auth.setAuth(res);router.push('/')}
</script>
<style scoped>.auth-bg{min-height:100vh;display:grid;place-items:center;background:radial-gradient(circle at top,#d8e6ff,#f5f8ff)}.auth-card{width:400px}</style>
