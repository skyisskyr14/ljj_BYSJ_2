<template>
  <div class="page-card">
    <h2>成员详情</h2>
    <el-descriptions :column="1" border>
      <el-descriptions-item label="昵称">{{ detail.nickname }}</el-descriptions-item>
      <el-descriptions-item label="邮箱">{{ detail.email }}</el-descriptions-item>
      <el-descriptions-item label="电话">{{ detail.phone }}</el-descriptions-item>
      <el-descriptions-item label="毕业去向">{{ detail.graduationDestination }}</el-descriptions-item>
      <el-descriptions-item label="简介">{{ detail.personalProfile }}</el-descriptions-item>
    </el-descriptions>
  </div>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { api } from '@/api'
import { useRoute } from 'vue-router'

const route = useRoute()
const detail = ref<any>({})
onMounted(async () => {
  detail.value = await api.detail(Number(route.params.id))
})
</script>
