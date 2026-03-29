<template>
  <div class="page-card">
    <h2>留言墙</h2>
    <el-input v-model="content" type="textarea" placeholder="说点什么..." />
    <el-button type="primary" @click="post" style="margin:10px 0">发布</el-button>
    <el-empty v-if="!rows.length" description="还没有留言，快来第一条" />
    <el-timeline>
      <el-timeline-item v-for="m in rows" :key="m.id" :timestamp="m.createTime">
        <div class="msg">
          <b>{{ m.senderName }}</b>
          <p>{{ m.content }}</p>
          <el-button link type="danger" @click="del(m.id)">删除</el-button>
        </div>
      </el-timeline-item>
    </el-timeline>
    <el-pagination v-model:current-page="page" :page-size="10" layout="prev, pager, next" :total="total" @current-change="load" />
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { api } from '@/api'

const content = ref('')
const rows = ref<any[]>([])
const total = ref(0)
const page = ref(1)
const load = async () => {
  const res: any = await api.messagePage({ page: page.value, pageSize: 10 })
  rows.value = res.records
  total.value = res.total
}
const post = async () => {
  if (!content.value.trim()) return
  await api.createMessage({ content: content.value })
  content.value = ''
  load()
}
const del = async (id: number) => {
  await api.deleteMessage(id)
  load()
}
onMounted(load)
</script>
<style scoped>
.msg { background: #f8fbff; border-radius: 10px; padding: 10px; }
</style>
