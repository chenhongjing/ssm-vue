<template>
  <el-dialog
    :model-value="dialogVisible"
    :title="dialogTitle"
    width="50%"
    @close="handleClose"
  >
    <el-form ref="formRef" :model="form" label-width="70px" :rules="rules">
      <el-form-item label="材料名称" prop="materialName">
        <el-input v-model="form.materialName" />
      </el-form-item>
      <el-form-item label="所属动物" prop="animal">
        <el-input v-model="form.animal" />
      </el-form-item>
      <el-form-item label="简介">
        <el-input v-model="form.info" />
      </el-form-item>
      <el-form-item label="图片">
        <el-input v-model="form.picture" />
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
import { defineEmits, defineProps, ref } from 'vue'

const emits = defineEmits(['update:modelValue', 'initUserList'])

defineProps({
  dialogTitle: {
    type: String,
    default: ''
  },
  dialogVisible: {
    type: Boolean
  }
})

const handleClose = () => {
  emits('update:modelValue', false)
}

const handleConfirm = () => {
  // 统一表单验证
  formRef.value.validate(async (valid) => {
    if (valid) {
      // 发送请求
      // ...
      //
      ElMessage({
        message: '添加成功',
        type: 'success'
      })
      // 提交完数据后要刷新表单
      // emits('initUserList')
      handleClose()
    } else {
      console.log('error')
      return false
    }
  })
}

// 表单的数据源
const formRef = ref(null)
const form = ref({
  materialName: '',
  animal: '',
  info: '',
  picture: ''
})

// 表单校验
const rules = ref({
  materialName: [
    {
      required: true,
      message: '材料名称不能为空',
      trigger: 'blur'
    }
  ],
  animal: [
    {
      required: true,
      message: '所属动物不能为空',
      trigger: 'blur'
    }
  ]
})
</script>

<style></style>
