<template>
  <div class="page-card">
    <h2>同学录检索</h2>
    <div class="bar">
      <el-input v-model="query.keyword" placeholder="搜索姓名/用户名" style="max-width:280px" />
      <el-button type="primary" @click="load">搜索</el-button>
    </div>
    <el-table :data="rows">
      <el-table-column prop="id" label="ID" width="70" />
      <el-table-column prop="nickname" label="昵称" />
      <el-table-column prop="graduationDestination" label="毕业去向" />
      <el-table-column label="操作">
        <template #default="s"><el-button link @click="$router.push('/directory/' + s.row.id)">查看详情</el-button></template>
      </el-table-column>
    </el-table>
    <el-pagination v-model:current-page="query.page" :page-size="query.pageSize" layout="prev, pager, next" :total="total" @current-change="load" />
  </div>
</template>
<script setup lang="ts">
import { reactive, ref, onMounted } from 'vue'
import { api } from '@/api'

const rows = ref<any[]>([])
const total = ref(0)
const query = reactive({ keyword: '', page: 1, pageSize: 10, sortBy: 'id', sortDirection: 'desc' })
const load = async () => {
  const res: any = await api.searchDirectory(query)
  rows.value = res.records
  total.value = res.total
}
onMounted(load)
</script>
<style scoped>
.bar { display: flex; gap: 12px; margin: 12px 0; }
</style>
