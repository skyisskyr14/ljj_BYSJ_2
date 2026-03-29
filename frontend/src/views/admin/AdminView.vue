<template>
  <div class="page-card">
    <h2>后台管理</h2>
    <el-row :gutter="12">
      <el-col :span="8"><el-statistic title="用户数" :value="dash.userCount || 0" /></el-col>
      <el-col :span="8"><el-statistic title="班级数" :value="dash.classCount || 0" /></el-col>
      <el-col :span="8"><el-statistic title="留言数" :value="dash.messageCount || 0" /></el-col>
    </el-row>

    <h3>用户管理</h3>
    <el-input v-model="keyword" placeholder="检索用户" style="max-width:260px" />
    <el-button @click="loadUsers">查询</el-button>
    <el-table :data="users">
      <el-table-column prop="id" label="ID" width="70" />
      <el-table-column prop="username" label="用户名" />
      <el-table-column prop="nickname" label="昵称" />
      <el-table-column prop="role" label="角色" />
      <el-table-column label="状态"><template #default="s">{{ s.row.status === 0 ? '正常' : '禁用' }}</template></el-table-column>
      <el-table-column label="操作"><template #default="s"><el-button size="small" @click="toggle(s.row)">{{ s.row.status === 0 ? '禁用' : '恢复' }}</el-button></template></el-table-column>
    </el-table>

    <h3>班级管理</h3>
    <el-button type="primary" @click="addClass">新增班级</el-button>
    <el-table :data="classes">
      <el-table-column prop="className" label="班级" />
      <el-table-column prop="grade" label="年级" />
      <el-table-column label="操作"><template #default="s"><el-button link type="danger" @click="delClass(s.row.id)">删除</el-button></template></el-table-column>
    </el-table>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { api } from '@/api'
import { ElMessageBox } from 'element-plus'

const dash = ref<any>({})
const users = ref<any[]>([])
const keyword = ref('')
const classes = ref<any[]>([])

const load = async () => {
  dash.value = await api.dashboard()
  await loadUsers()
  classes.value = await api.classes()
}
const loadUsers = async () => {
  const r: any = await api.adminUsers({ page: 1, pageSize: 20, keyword: keyword.value })
  users.value = r.records
}
const toggle = async (row: any) => {
  await api.updateUserStatus(row.id, row.status === 0 ? 1 : 0)
  await loadUsers()
}
const addClass = async () => {
  const r = await ElMessageBox.prompt('班级名', '新增班级')
  if (r.value) {
    await api.saveClass({ className: r.value, grade: '2020级' })
    await load()
  }
}
const delClass = async (id: number) => {
  await api.deleteClass(id)
  await load()
}
onMounted(load)
</script>
