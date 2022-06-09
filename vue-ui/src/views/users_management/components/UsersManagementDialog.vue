<template>
  <el-dialog
    :model-value="dialogVisible"
    :title="dialogTitle"
    width="30%"
    @close="handleClose"
  >
    <el-form ref="formRef" :model="form" label-width="100px" :rules="rules">
      <el-form-item label="用户名：" prop="username"
        ><el-col v-if="!formMode" :span="fullSpan"
          ><el-input v-model="form.username"
        /></el-col>
        <div v-else v-html="form.username"></div>
      </el-form-item>

      <el-form-item label="密码：" prop="userPassword"
        ><el-col :span="fullSpan"
          ><el-input
            v-if="!formMode"
            v-model="form.userPassword"
            show-password
          />
          <div v-else>******</div>
        </el-col>
      </el-form-item>

      <el-form-item label="邮箱：" prop="email"
        ><el-col :span="fullSpan">
          <el-input v-if="!formMode" v-model="form.email" />
          <div v-else v-html="form.email"></div>
        </el-col>
      </el-form-item>

      <el-form-item label="电话：" prop="phone">
        <el-col :span="fullSpan">
          <el-input v-if="!formMode" v-model="form.phone"></el-input>
          <div v-else v-html="form.phone"></div>
        </el-col>
      </el-form-item>

      <el-form-item label="角色：" prop="role">
        <el-col :span="fullSpan">
          <el-radio-group v-if="!formMode" v-model="form.role" class="ml-4">
            <el-radio :label="false">普通用户</el-radio>
            <el-radio :label="true">管理员用户</el-radio>
          </el-radio-group>
          <div v-else>
            <div v-if="form.role === false">普通用户</div>
            <div v-else>管理员用户</div>
          </div>
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
import { ref, defineProps, defineEmits, watch } from 'vue'
import { ElMessage } from 'element-plus'
import { editUser } from '@/api/management'

const fullSpan = ref(20)

const formMode = ref(false)

const emits = defineEmits(['update:modelValue', 'initGetUsersInfo'])

const formRef = ref(null)
const form = ref({
  username: String,
  userPassword: String,
  email: String,
  phone: String,
  role: Boolean
})

const props = defineProps({
  dialogTitle: {
    type: String,
    default: '编辑用户信息'
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
  emits('update:modelValue')
}

const handleConfirm = () => {
  formRef.value.validate(async (valid) => {
    if (valid) {
      await editUser(form.value)
      ElMessage({
        message: '修改成功',
        type: 'success'
      })
      emits('initGetUsersInfo')
      handleClose()
    } else {
      return false
    }
  })
}

const rules = ref({
  username: [
    {
      required: true,
      min: 2,
      max: 10,
      message: '长度在 2 到 10 个字符',
      trigger: 'blur'
    },
    {
      pattern: /^[a-zA-Z0-9_-]{1,9}$/,
      message: '以字母开头，长度在2-10之间，只能包含字符、数字和下划线',
      trigger: 'blur'
    }
  ],
  userPassword: [
    {
      required: true,
      min: 5,
      max: 15,
      message: '长度在 5 到 15个字符',
      trigger: 'blur'
    }
  ],
  email: [
    {
      pattern: /^[A-Za-z0-9]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
      message: '邮箱格式不正确',
      trigger: 'blur'
    }
  ],
  phone: [
    {
      pattern: /^1[34578]\d{9}$/,
      message: '电话格式不正确',
      trigger: 'blur'
    }
  ],
  role: [
    {
      required: true
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
</script>

<style></style>
