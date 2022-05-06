<template>
  <el-card class="login-card">
    <span class="login-title">用户登录</span>
    <span class="login-tip">Hello! Welcome!</span>
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
      <el-form-item>
        <el-button class="login-button" type="primary" @click="handleLogin"
          >登录</el-button
        >
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

const form = ref({
  username: 'admin',
  userPassword: '123456'
})

const rules = reactive({
  username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
  userPassword: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
})

// 登录时统一检验
const formRef = ref(null)
const handleLogin = () => {
  formRef.value.validate(async (valid) => {
    if (valid) {
      store.dispatch('app/login', form.value)
      return true
    } else {
      return false
    }
  })
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
  height: 400px;
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
  margin-bottom: 30px;
}

::v-deep.login-button {
  position: relative;
  top: 50%;
  left: 40%;
  transform: translate(-50%, -50%);
}
</style>
