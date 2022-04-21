<template>
  <el-card>
    <el-row :gutter="20" class="header">
      <el-col :span="7">
        <el-input
          placeholder="请输入关键词"
          clearable
          v-model="queryForm.query"
        ></el-input>
      </el-col>
      <el-button type="primary" :icon="Search">搜索</el-button>
      <el-button type="primary" :icon="Plus">添加数据</el-button>
    </el-row>

    <el-table :data="tableData" style="width: 100%">
      <el-table-column
        v-for="(item, index) in options"
        :key="index"
        :prop="item.prop"
        :label="item.label"
        :width="item.width"
      >
        <template #default v-if="item.prop === 'action'">
          <el-button :icon="Edit">编辑</el-button>
          <el-button type="danger" :icon="Delete">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script setup>
import { ref } from 'vue'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'
import { options } from './options'
const queryForm = ref({
  query: '',
  pagenum: 1,
  pagesize: 2
})

const tableData = ref([
  {
    title: 'Tom的地址',
    username: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  },
  {
    title: 'John的地址',
    username: 'John',
    address: 'No. 189, Grove St, Los Angeles'
  },
  {
    title: 'Morgan的地址',
    username: 'Morgan',
    address: 'No. 189, Grove St, Los Angeles'
  }
])
</script>

<style lang="scss" scoped>
.header {
  padding-bottom: 16px;
}
</style>
