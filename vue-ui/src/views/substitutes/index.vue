<template>
  <div class="subsitute-view">
    <el-card>
      <el-row :gutter="20" class="header">
        <el-col :span="7">
          <el-input
            placeholder="请输入关键词"
            clearable
            v-model="queryForm.query"
          ></el-input>
        </el-col>
        <el-button
          type="primary"
          :icon="Search"
          @click="initGetSubstituteRecords"
          >搜索</el-button
        >
        <el-button type="primary" :icon="Plus" @click="handleDialogValue()"
          >添加数据</el-button
        >
      </el-row>
      <!--数据展示-->
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
            v-if="item.prop === 'action' || item.prop === 'picture'"
          >
            <div v-if="item.prop === 'action'">
              <el-button :icon="Edit" @click="handleDialogValue(row)"
                >编辑</el-button
              >
              <el-button type="danger" :icon="Delete" @click="deleteRecord(row)"
                >删除</el-button
              >
            </div>
            <div v-else>
              <el-image
                style="width: 100px; height: 100px"
                :src="row.pictureUrl"
                :preview-src-list="[row.pictureUrl]"
                fit="cover"
              >
                <template #error>
                  <div class="image-slot">
                    <el-icon><Picture /></el-icon>
                  </div> </template
              ></el-image>
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
    <div>
      <substitute-dialog
        v-model:model-value="dialogVisible"
        :dialogTitle="dialogTitle"
        v-if="dialogVisible"
        :dialogTableValue="dialogTableValue"
        @initSubstituteRecords="initGetSubstituteRecords"
      />
    </div>
  </div>
</template>

<script setup>
import SubstituteDialog from './components/SubstituteDialog.vue'
import { ref } from 'vue'
import { Search, Plus, Picture } from '@element-plus/icons-vue'
import { isNull } from '@/utils/filter'
import { options } from './options'
import { ElMessageBox, ElMessage } from 'element-plus'
import { getSubstituteRecords, deleteSubstituteRecord } from '@/api/substitute'

const dialogVisible = ref(false)
const dialogTitle = ref('')
const dialogTableValue = ref({})
// const imageUrl = ref('')
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
    dialogTitle.value = '添加数据：动物组织器官信息'
    dialogTableValue.value = {}
  } else {
    dialogTitle.value = '编辑数据：动物组织器官信息'
    console.log(row)
    dialogTableValue.value = JSON.parse(JSON.stringify(row))
  }
  dialogVisible.value = true
}

const handleSizeChange = (pageSize) => {
  queryForm.value.pageNum = 1
  queryForm.value.pageSize = pageSize
  initGetSubstituteRecords()
}

const handleCurrentChange = (pageNum) => {
  queryForm.value.pageNum = pageNum
  initGetSubstituteRecords()
}

const initGetSubstituteRecords = async () => {
  const res = await getSubstituteRecords(queryForm.value)
  console.log(res)
  totalRecordNum.value = res.total
  tableData.value = res.list
  console.log(tableData.value)
}

const deleteRecord = (row) => {
  console.log(row)
  ElMessageBox.confirm('确定要删除记录', 'Warning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(async () => {
      await deleteSubstituteRecord(row.substituteId)
      ElMessage({
        type: 'success',
        message: '删除成功'
      })
      initGetSubstituteRecords()
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消删除'
      })
    })
}

const displayData = (row) => {
  dialogTitle.value = '预览数据：动物组织器官信息'
  dialogTableValue.value = JSON.parse(JSON.stringify(row))
  dialogVisible.value = true
}

initGetSubstituteRecords()
</script>

<style lang="scss" scoped>
.header {
  padding-bottom: 16px;
}

.el-pagination {
  padding-top: 16px;
}
.image-slot {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: #f5f7fa;
  color: #909399;
  font-size: 30px;
}
.image-slot .el-icon {
  font-size: 30px;
}
::v-deep .el-table .el-table__cell {
  position: static;
}
</style>
