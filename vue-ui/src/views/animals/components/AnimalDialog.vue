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
      <el-form-item label="动物名：" prop="animalName">
        <el-col :span="halfSpan">
          <el-input v-if="!formMode" v-model="form.animalName" />
          <div v-else v-html="form.animalName"></div>
        </el-col>
      </el-form-item>

      <el-form-item label="拉丁学名：" prop="sciName">
        <el-col :span="halfSpan">
          <el-input v-if="!formMode" v-model="form.sciName" />
          <div v-else v-html="form.sciName"></div>
        </el-col>
      </el-form-item>

      <el-form-item label="特征描述：">
        <el-col :span="fullSpan">
          <el-input v-if="!formMode" v-model="form.info" />
          <div v-else v-html="form.info"></div>
        </el-col>
      </el-form-item>

      <el-form-item label="图片：">
        <el-col :span="halfSpan">
          <!--编辑模式-->
          <el-upload
            class="avatar-uploader"
            :action="actionUrl"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            v-if="!formMode"
          >
            <el-image
              v-if="form.pictureUrl"
              :src="form.pictureUrl"
              class="avatar"
            />
            <el-icon v-else class="avatar-uploader-icon"> <Plus /></el-icon>
          </el-upload>
          <!--预览模式-->
          <div v-else>
            <el-image
              style="width: 200px; height: 200px"
              :src="form.pictureUrl"
              class="avatar"
              fit="cover"
            >
              <template #error>
                <div class="image-slot">
                  <el-icon><Picture /></el-icon>
                </div>
              </template>
            </el-image>
          </div>
        </el-col>
      </el-form-item>

      <!--上次更新时间：updated_time (自动更新，不允许用户修改)-->
      <el-form-item label="更新时间：">
        <el-col :span="fullSpan">
          <el-input
            disabled
            v-model="form.updatedTime"
            :placeholder="form.updatedTime"
            v-if="!formMode"
          />
          <div v-else v-html="form.updatedTime"></div>
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
import { addAnimalRecord, editAnimalRecord } from '@/api/animal'
import { Plus } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

const halfSpan = ref(12)
const fullSpan = ref(23)

// 表单是否禁用
const formMode = ref(false)

const emits = defineEmits(['update:modelValue', 'initAnimalRecords'])

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

const handleClose = () => {
  emits('update:modelValue', false)
}

const handleConfirm = () => {
  if (props.dialogTitle.includes('预览')) {
    handleClose()
  } else {
    formRef.value.validate(async (valid) => {
      if (valid) {
        if (props.dialogTitle.includes('添加')) {
          await addAnimalRecord(form.value)
        } else {
          await editAnimalRecord(form.value)
        }

        ElMessage({
          message: props.dialogTitle.includes('添加') ? '添加成功' : '修改成功',
          type: 'success'
        })

        emits('initAnimalRecords')
        handleClose()
      } else {
        return false
      }
    })
  }
}

// 表单的数据源
const formRef = ref(null)
const form = ref({
  animalName: '',
  sciName: '',
  info: '',
  pictureUrl: String,
  updatedTime: ''
})

// 表单校验
const rules = ref({
  animalName: [
    {
      required: true,
      message: '动物名不能为空',
      trigger: 'blur'
    }
  ]
})

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

const handleAvatarSuccess = (res, file) => {
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
</style>
