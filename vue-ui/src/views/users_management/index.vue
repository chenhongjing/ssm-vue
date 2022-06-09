<template>
  <div class="user-management-view">
    <el-card>
      <el-row :gutter="20" class="header">
        <el-col :span="7">
          <el-input
            placeholder="请输入关键词"
            clearable
            v-model="queryForm.query"
          ></el-input>
        </el-col>
        <el-button type="primary" :icon="Search" @click="initGetUsersInfo"
          >搜索</el-button
        >
        <!-- <el-button type="primary" :icon="Plus" @click="handleDialogValue()"
          >添加数据</el-button
        > -->
        <el-button type="primary" :icon="Refresh" @click="initGetUsersInfo"
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
            v-if="
              item.prop === 'role' ||
              item.prop === 'action' ||
              item.prop === 'userPassword'
            "
          >
            <div v-if="item.prop === 'action'">
              <el-button :icon="Edit" @click="handleDialogValue(row)"
                >编辑</el-button
              >
              <el-button type="danger" :icon="Delete" @click="deleteRecord(row)"
                >删除</el-button
              >
            </div>
            <div v-else-if="item.prop === 'role'">
              <div v-if="row.role === true">管理员用户</div>
              <div v-else>普通用户</div>
            </div>
            <div v-else>******</div>
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

    <users-management-dialog
      v-model:model-value="dialogVisible"
      :dialogTitle="dialogTitle"
      v-if="dialogVisible"
      :dialogTableValue="dialogTableValue"
      @initGetUsersInfo="initGetUsersInfo"
    />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { Search, Edit, Delete, Refresh } from '@element-plus/icons-vue'
import { options } from './options'
import { ElMessageBox, ElMessage } from 'element-plus'
import UsersManagementDialog from './components/UsersManagementDialog.vue'
import { getUsers, deleteUser } from '@/api/management'
import { isNull } from '@/utils/filter'

const dialogVisible = ref(false)
const dialogTitle = ref('')
const dialogTableValue = ref({})

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

const handleDialogValue = (row) => {
  if (isNull(row)) {
    dialogTitle.value = '添加数据：用户信息'
    dialogTableValue.value = {}
  } else {
    dialogTitle.value = '编辑数据：用户信息'
    // console.log(row)
    dialogTableValue.value = JSON.parse(JSON.stringify(row))
  }
  dialogVisible.value = true
}

const initGetUsersInfo = async () => {
  const res = await getUsers(queryForm.value)
  totalRecordNum.value = res.total
  tableData.value = res.list
}

const handleSizeChange = (pageSize) => {
  queryForm.value.pageNum = 1
  queryForm.value.pageSize = pageSize
  initGetUsersInfo()
}

const handleCurrentChange = (pageNum) => {
  queryForm.value.pageNum = pageNum
  console.log(queryForm)
  initGetUsersInfo()
}

const deleteRecord = (row) => {
  console.log(row)
  ElMessageBox.confirm('确定要删除记录', 'Warning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(async () => {
      await deleteUser(row.userId)
      ElMessage({
        type: 'success',
        message: '删除成功'
      })
      initGetUsersInfo()
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消删除'
      })
    })
}

const displayData = (row) => {
  dialogTitle.value = '预览数据：用户信息'
  dialogTableValue.value = JSON.parse(JSON.stringify(row))
  dialogVisible.value = true
}

initGetUsersInfo()
</script>

<style lang="scss" scoped>
.header {
  padding-bottom: 16px;
}

.el-pagination {
  padding-top: 16px;
}
</style>
