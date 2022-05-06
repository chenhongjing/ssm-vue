<template>
  <el-dialog
    :model-value="dialogVisible"
    :title="dialogTitle"
    width="70%"
    @close="handleClose"
  >
    <el-form
      ref="formRef"
      :model="form"
      label-width="100px"
      :rules="rules"
      :disabled="formMode"
    >
      <!--材料名称：material_id -> material_name -->

      <el-form-item label="材料名称" prop="materialName">
        <el-col :span="halfSpan">
          <el-input v-model="form.materialName"
        /></el-col>
      </el-form-item>
      <!--简介：info-->
      <el-form-item label="简介">
        <el-col :span="fullSpan">
          <el-input v-model="form.info" />
        </el-col>
      </el-form-item>

      <!--参数记录（富文本编辑器）：param_data-->
      <el-form-item label="参数记录">
        <el-col :span="fullSpan">
          <vue-ueditor-wrap
            v-if="!formMode"
            v-model="form.paramData"
            :config="editorConfig"
            editor-id="editor-demo-01"
          ></vue-ueditor-wrap>
          <div v-else v-html="form.paramData"></div>
        </el-col>
      </el-form-item>
      <!--记录者：recorder-->
      <el-form-item label="记录者">
        <el-col :span="halfSpan">
          <el-input v-model="form.recorder" />
        </el-col>
      </el-form-item>

      <el-form-item label="备注">
        <el-col :span="fullSpan">
          <el-input v-model="form.commentNotes" />
        </el-col>
      </el-form-item>

      <!--上次更新时间：updated_time (自动更新，不允许用户修改)-->
      <el-form-item label="更新时间">
        <el-col :span="fullSpan">
          <el-input
            disabled
            v-model="form.updatedTime"
            :placeholder="form.updatedTime"
          />
        </el-col>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="handleConfirm">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ElMessage } from 'element-plus'
import { defineEmits, defineProps, ref, watch } from 'vue'
import {
  addDynamicParamRecord,
  editDynamicParamRecord
} from '@/api/dynamic_param'

const halfSpan = ref(12)
const fullSpan = ref(23)

const emits = defineEmits(['update:modelValue', 'initDynamicParamRecords'])

const props = defineProps({
  dialogTitle: {
    type: String,
    default: ''
  },
  dialogVisible: {
    type: Boolean
  },
  dialogTableValue: {
    type: Object,
    default: () => {}
  }
})

// 表单是否禁用
const formMode = ref(false)

const handleClose = () => {
  emits('update:modelValue', false)
}

const handleConfirm = () => {
  // 统一表单验证
  formRef.value.validate(async (valid) => {
    if (valid) {
      // 发送请求：添加/修改
      if (props.dialogTitle.includes('添加')) {
        await addDynamicParamRecord(form.value)
      } else {
        await editDynamicParamRecord(form.value)
      }

      ElMessage({
        message: props.dialogTitle.includes('添加') ? '添加成功' : '修改成功',
        type: 'success'
      })
      // 提交完数据后要刷新表单
      emits('initDynamicParamRecords')
      handleClose()
    } else {
      console.log('error')
      return false
    }
  })
}

const editorConfig = ref({
  // 访问 UEditor 静态资源的根路径，可参考 https://hc199421.gitee.io/vue-ueditor-wrap/#/faq
  UEDITOR_HOME_URL: '/UEditor/',
  // 服务端接口（need to fix！)
  // serverUrl: 'http://localhost:9090/api/',
  // 初始容器高度
  initialFrameHeight: 300,
  // 初始容器宽度
  initialFrameWidth: '100%',
  enableAutoSave: false
})

// 表单的数据源
const formRef = ref(null)
const form = ref({
  materialName: '',
  info: '',
  paramData: '',
  recorder: '',
  commentNotes: '',
  updatedTime: ''
})

// 表单校验
const rules = ref({
  materialName: [
    {
      required: true,
      message: '材料名称不能为空',
      trigger: 'blur'
    }
  ]
})

// 三个参数：监听的对象，监听的回调函数，监听的配置
watch(
  () => props.dialogTableValue,
  () => {
    form.value = props.dialogTableValue
  },
  { deep: true, immediate: true }
)

watch(
  () => props.dialogTitle,
  () => {
    if (props.dialogTitle.includes('预览')) {
      formMode.value = true
    } else {
      formMode.value = false
    }
  },
  { deep: true, immediate: true }
)
</script>

<style></style>
