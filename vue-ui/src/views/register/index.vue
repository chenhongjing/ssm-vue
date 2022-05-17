<template>
  <el-card class="login-card">
    <span class="login-title">动态参数在线编辑系统</span>
    <span class="login-tip">新用户注册</span>
    <el-form label-width="80px" ref="formRef" :model="form" :rules="rules">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" placeholder="请输入用户名">
        </el-input>
      </el-form-item>
      <el-form-item label="密码" prop="userPassword">
        <el-input
          v-model="form.userPassword"
          type="password"
          placeholder="请输入密码"
          show-password
        >
        </el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input
          v-model="form.confirmPassword"
          type="password"
          placeholder="请再次输入密码"
          show-password
        >
        </el-input>
      </el-form-item>

      <el-form-item label="电子邮箱" prop="email">
        <el-input v-model="form.email" placeholder="请输入电子邮箱"> </el-input>
      </el-form-item>

      <el-form-item label="电话" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入电话号码"> </el-input>
      </el-form-item>

      <el-form-item>
        <el-button class="login-button" type="primary" @click="handleRegister"
          >注册</el-button
        >
      </el-form-item>
      <el-form-item>
        <el-link class="switch-login" :underline="false" @click="switchToLogin"
          >已有账户？点击登录</el-link
        >
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import router from '@/router'
import { ref, reactive } from 'vue'

const form = ref({
  username: 'admin',
  userPassword: '123456',
  confirmPassword: '123456',
  email: '',
  phone: ''
})

const equalToPassword = (rule, value, callback) => {
  if (form.value.userPassword !== value) {
    callback(new Error('两次输入的密码不一致'))
  } else {
    callback()
  }
}

const rules = reactive({
  username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
  userPassword: [{ required: true, message: '密码不能为空', trigger: 'blur' }],
  confirmPassword: [
    { required: true, message: '密码不能为空', trigger: 'blur' },
    { required: true, validator: equalToPassword, trigger: 'blur' }
  ]
})

// 登录时统一检验
const formRef = ref(null)
const handleRegister = () => {
  formRef.value.validate(async (valid) => {
    if (valid) {
      console.log('yes')
      return true
    } else {
      console.log('no')
      return false
    }
  })
}

const switchToLogin = () => {
  router.replace('/login')
}
</script>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark-gray: #333333;
$light-gray: #999999;
$cursor: #fff;

.el-image {
  height: 885px;
  width: 750px;
}
.el-main {
  position: relative;
}
.login-card {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  width: 480px;
  height: 550px;
  padding: 50px;
}
.login-title {
  width: 459px;
  height: 70px;
  font-size: 40px;
  font-family: AlibabaPuHuiTiB;
  color: $dark-gray;
  line-height: 90px;
  letter-spacing: 1px;
  font-weight: 800;
  display: block;
  text-align: center;
}
.login-tip {
  width: 319px;
  height: 70px;
  font-size: 30px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: $light-gray;
  line-height: 90px;
  letter-spacing: 1px;
  display: block;
  text-align: center;
  margin: 0 auto;
  margin-bottom: 30px;
}

::v-deep.login-button {
  position: relative;
  top: 50%;
  left: 40%;
  transform: translate(-50%, -50%);
}

.switch-login {
  text-align: center;
  margin: 0 20%;
}
</style>
