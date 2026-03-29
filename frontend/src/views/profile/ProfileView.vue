<template>
  <div class="page-card">
    <h2>个人中心</h2>
    <el-form :model="form" label-width="100px">
      <el-form-item label="昵称"><el-input v-model="form.nickname" /></el-form-item>
      <el-form-item label="手机"><el-input v-model="form.phone" /></el-form-item>
      <el-form-item label="邮箱"><el-input v-model="form.email" /></el-form-item>
      <el-form-item label="毕业去向"><el-input v-model="form.graduationDestination" /></el-form-item>
      <el-form-item label="个人简介"><el-input v-model="form.personalProfile" type="textarea" /></el-form-item>
      <el-form-item>
        <el-upload action="http://localhost:8080/api/file/avatar" :headers="headers" :show-file-list="false" :on-success="onUpload">
          <el-button>上传头像</el-button>
        </el-upload>
      </el-form-item>
      <el-button type="primary" @click="save">保存</el-button>
    </el-form>
  </div>
</template>
<script setup lang="ts">
import { onMounted, reactive, computed } from 'vue'
import { api } from '@/api'
import { useAuthStore } from '@/stores/auth'

const form = reactive<any>({})
const auth = useAuthStore()
const headers = computed(() => ({ Authorization: `Bearer ${auth.token}` }))
const load = async () => Object.assign(form, await api.me())
const save = async () => {
  await api.updateProfile(form)
  load()
}
const onUpload = () => load()
onMounted(load)
</script>
