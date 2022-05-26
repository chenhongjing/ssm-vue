<template>
  <div class="dynamic-param-view">
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
          @click="initGetDynamicParamRecords"
          >搜索</el-button
        >
        <el-button type="primary" :icon="Plus" @click="handleDialogValue()"
          >添加数据</el-button
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
          <template v-slot="{ row }" v-if="item.prop === 'action'">
            <el-button :icon="Edit" @click="handleDialogValue(row)"
              >编辑</el-button
            >
            <el-button type="danger" :icon="Delete" @click="deleteRecord(row)"
              >删除</el-button
            >
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

    <!-- 加上v-if保证每次点开表单后都是全新的，没有之前的残留数据 -->
    <dynamic-param-dialog
      v-model:model-value="dialogVisible"
      :dialogTitle="dialogTitle"
      v-if="dialogVisible"
      :dialogTableValue="dialogTableValue"
      @initDynamicParamRecords="initGetDynamicParamRecords"
      :category="props.category"
    />
    <!-- Dialog还有一个事件要绑定： @initUserList="initGetUserList" -->
  </div>
</template>

<script setup>
import { ref, defineProps, watch } from 'vue'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'
import { options } from './options'
import { ElMessageBox, ElMessage } from 'element-plus'
import DynamicParamDialog from './components/DynamicParamDialog.vue'
import {
  getDynamicParamRecords,
  deleteDynamicParamRecord
} from '@/api/dynamic_param'
import { isNull } from '@/utils/filter'

const dialogVisible = ref(false)
const dialogTitle = ref('')
const dialogTableValue = ref({})

const props = defineProps({
  category: {
    type: String
  }
})

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
    dialogTitle.value = '添加数据：动态参数'
    dialogTableValue.value = {}
  } else {
    dialogTitle.value = '编辑数据：动态参数'
    // console.log(row)
    dialogTableValue.value = JSON.parse(JSON.stringify(row))
  }
  dialogVisible.value = true
}

const initGetDynamicParamRecords = async () => {
  const res = await getDynamicParamRecords(props.category, queryForm.value)
  totalRecordNum.value = res.total
  tableData.value = res.list
}

const handleSizeChange = (pageSize) => {
  queryForm.value.pageNum = 1
  queryForm.value.pageSize = pageSize
  initGetDynamicParamRecords()
}

const handleCurrentChange = (pageNum) => {
  queryForm.value.pageNum = pageNum
  initGetDynamicParamRecords()
}

const deleteRecord = (row) => {
  console.log(row)
  ElMessageBox.confirm('确定要删除记录', 'Warning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(async () => {
      await deleteDynamicParamRecord(row.paramId)
      ElMessage({
        type: 'success',
        message: '删除成功'
      })
      initGetDynamicParamRecords()
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消删除'
      })
    })
}

const displayData = (row) => {
  dialogTitle.value = '预览数据：动态力学参数'
  dialogTableValue.value = JSON.parse(JSON.stringify(row))
  dialogVisible.value = true
}
initGetDynamicParamRecords()

watch(
  () => props.category,
  () => {
    initGetDynamicParamRecords()
  },
  {
    immediate: true
  }
)
</script>

<style lang="scss" scoped>
.header {
  padding-bottom: 16px;
}

.el-pagination {
  padding-top: 16px;
}
</style>
