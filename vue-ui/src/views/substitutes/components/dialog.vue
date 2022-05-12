<template>
  <el-dialog
    :model-value="dialogVisible"
    :title="dialogTitle"
    width="50%"
    @close="handleClose"
  >
    <el-form
      ref="formRef"
      :model="form"
      label-width="100px"
      :rules="rules"
      :disabled="formMode"
    >
      <!--材料名称： material_name -->

      <el-form-item label="组织/器官名" prop="materialName">
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

      <!--图片：picture-->
      <el-form-item label="图片">
        <el-col :span="halfSpan">
          <el-upload
            class="avatar-uploader"
            :action="actionUrl"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <el-image
              v-if="form.pictureUrl"
              :src="form.pictureUrl"
              class="avatar"
            />
            <el-icon v-else class="avatar-uploader-icon"> <Plus /></el-icon>
          </el-upload>
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
import { defineEmits, defineProps, ref, watch } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { addSubstituteRecord, editSubstituteRecord } from '@/api/substitute'

const halfSpan = ref(12)
const fullSpan = ref(23)

const emits = defineEmits(['update:modelValue', 'initSubstituteRecords'])

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
  if (props.dialogTitle.includes('预览')) {
    handleClose()
  } else {
    // 统一表单验证
    formRef.value.validate(async (valid) => {
      if (valid) {
        if (props.dialogTitle.includes('添加')) {
          await addSubstituteRecord(form.value)
        } else {
          await editSubstituteRecord(form.value)
        }

        ElMessage({
          message: props.dialogTitle.includes('添加') ? '添加成功' : '修改成功',
          type: 'success'
        })
        // 提交完数据后要刷新表单
        emits('initSubstituteRecords')
        handleClose()
      } else {
        console.log('error')
        return false
      }
    })
  }
}

// 表单的数据源
const formRef = ref(null)
const form = ref({
  materialName: '',
  info: '',
  pictureUrl: String,
  updatedTime: ''
})

// 表单校验
const rules = ref({
  materialName: [
    {
      required: true,
      message: '组织/器官名不能为空',
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

// 文件上传相关
const actionUrl = ref('http://localhost:9090/api/file')
// const imageUrl = ref('')

const handleAvatarSuccess = (res, file) => {
  // imageUrl.value = URL.createObjectURL(file.raw)
  console.log(res)
  form.value.pictureUrl = res
}

const beforeAvatarUpload = (file) => {
  return true
}
</script>

<style lang="scss" scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
::v-deep .avatar-uploader .el-upload {
  border: 1px dashed #dcdfe6;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: 0.2s;
}

::v-deep .avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
