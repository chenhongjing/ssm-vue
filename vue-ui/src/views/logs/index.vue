<template>
  <div class="log-management-view">
    <el-card>
      <el-row :gutter="20" class="header">
        <el-col :span="7">
          <el-input
            placeholder="请输入关键词"
            clearable
            v-model="queryForm.query"
          ></el-input>
        </el-col>
        <el-button type="primary" :icon="Search" @click="initGetLogsInfo"
          >搜索</el-button
        >
        <el-button type="primary" :icon="Refresh" @click="initGetLogsInfo"
          >刷新</el-button
        >
      </el-row>

      <el-table
        :data="tableData"
        style="width: 100%"
        @row-dblclick="displayData"
      >
        <el-table-column
          v-for="(item, index) in options"
          :key="index"
          :prop="item.prop"
          :label="item.label"
          :width="item.width"
        >
          <template
            v-slot="{ row }"
            v-if="item.prop === 'operStatus' || item.prop === 'requestMethod'"
          >
            <div v-if="item.prop === 'operStatus'">
              <div v-if="row.operStatus === 200">
                <el-tag type="success">成功</el-tag>
              </div>
              <div v-else><el-tag type="danger">错误</el-tag></div>
            </div>
            <div v-else>
              <el-tag>{{ row.requestMethod }}</el-tag>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <!--分页器-->
      <el-pagination
        v-model:currentPage="queryForm.pageNum"
        v-model:page-size="queryForm.pageSize"
        :page-sizes="[1, 2, 5, 10, 20]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalRecordNum"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { Search, Refresh } from '@element-plus/icons-vue'
import { options } from './options'
// import { ElMessageBox, ElMessage } from 'element-plus'
import { getLogs } from '@/api/management'
// import { isNull } from '@/utils/filter'

// const dialogVisible = ref(false)
// const dialogTitle = ref('')
// const dialogTableValue = ref({})

const queryForm = ref({
  query: '',
  pageNum: 1,
  pageSize: 5
})

const totalRecordNum = ref(0)

const tableData = ref([])

const small = ref(false)
const background = ref(true)
const disabled = ref(false)

const initGetLogsInfo = async () => {
  const res = await getLogs(queryForm.value)
  totalRecordNum.value = res.total
  tableData.value = res.list
}

const handleSizeChange = (pageSize) => {
  queryForm.value.pageNum = 1
  queryForm.value.pageSize = pageSize
  initGetLogsInfo()
}

const handleCurrentChange = (pageNum) => {
  queryForm.value.pageNum = pageNum
  console.log(queryForm)
  initGetLogsInfo()
}

initGetLogsInfo()
</script>

<style lang="scss" scoped>
.header {
  padding-bottom: 16px;
}

.el-pagination {
  padding-top: 16px;
}
</style>
