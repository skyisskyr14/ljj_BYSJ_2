<template>
  <div class="layout">
    <aside>
      <h2>云同学录</h2>
      <el-menu :default-active="$route.path" router>
        <el-menu-item index="/">首页</el-menu-item>
        <el-menu-item index="/directory">同学录</el-menu-item>
        <el-menu-item index="/wall">留言墙</el-menu-item>
        <el-menu-item index="/profile">个人中心</el-menu-item>
        <el-menu-item v-if="auth.isAdmin" index="/admin">后台管理</el-menu-item>
      </el-menu>
      <el-button class="logout" @click="logout">退出登录</el-button>
    </aside>
    <main><router-view /></main>
  </div>
</template>

<script setup lang="ts">
import { useAuthStore } from '@/stores/auth'
import { useRouter } from 'vue-router'
const auth = useAuthStore()
const router = useRouter()
const logout = () => { auth.logout(); router.push('/login') }
</script>

<style scoped>
.layout{display:grid;grid-template-columns:240px 1fr;min-height:100vh}
aside{background:linear-gradient(180deg,#3a68ff,#5b88ff);color:#fff;padding:20px}
main{padding:24px}
.logout{margin-top:20px;width:100%}
</style>
