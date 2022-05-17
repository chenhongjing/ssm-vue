<template>
  <div class="cards-container">
    <el-card class="box-card-component" style="margin-left: 8px">
      <template #header>
        <div class="box-card-header">
          <img :src="headerImg" />
        </div>
      </template>

      <div style="position: relative">
        <pan-thumb :image="avatar" class="panThumb" />
        <mallki class-name="mallki-text" text="vue-element-admin" />
        <div style="padding-top: 35px" class="progress-item">
          <el-space :size="spaceSize">
            <span class="item-name">用户名</span>
            <span>{{ userData.username }}</span>
          </el-space>
        </div>
        <div class="progress-item">
          <el-space :size="spaceSize">
            <span class="item-name">邮箱</span>
            <span>{{ userData.email }}</span>
          </el-space>
        </div>
        <div class="progress-item">
          <el-space :size="spaceSize">
            <span class="item-name">电话</span>
            <span>{{ userData.phone }}</span>
          </el-space>
        </div>
        <div class="progress-item">
          <el-space :size="spaceSize">
            <span class="item-name">上次登录时间</span>
            <span>{{ userData.loginTime }}</span>
          </el-space>
        </div>
        <div class="edit-button">
          <el-button>编辑</el-button>
        </div>
      </div>
    </el-card>

    <el-card class="box-card-chart" style="margin-left: 8px">
      <PieChart />
    </el-card>
  </div>
</template>

<script setup>
import { getUserInfo } from '@/api/user'
import PanThumb from '@/components/PanThumb'
import Mallki from '@/components/TextHoverEffect/Mallki'
import { ref } from 'vue'
import PieChart from './components/PieChart.vue'
const avatar = ref('/avatar2.gif')
// 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'

// "https://wpimg.wallstcn.com/e7d23d71-cf19-4b90-a1cc-f56af8c0903d.png"

// 'https://sp-ao.shortpixel.ai/client/to_webp,q_glossy,ret_img,w_1200/https://yetiacademy.com/wp-content/uploads/2020/06/STEM-Education.jpg'

const headerImg = ref('user_info_header2.png')
const spaceSize = ref(30)
const userData = ref({})

const initGetUserInfo = async () => {
  const res = await getUserInfo()
  userData.value = res.user
}

initGetUserInfo()
</script>

<style lang="scss" scoped>
.cards-container {
  display: flex;
  justify-content: flex-start;
}
.box-card-component {
  width: 30%;
  height: 80%;
  .box-card-header {
    position: relativev;
    height: 220px;
    img {
      width: 100%;
      height: 100%;
      transition: all 0.2s linear;
      &:hover {
        transform: scale(1.1, 1.1);
        filter: contrast(130%);
      }
    }
  }
  .mallki-text {
    position: absolute;
    top: 0px;
    right: 0px;
    font-size: 20px;
    font-weight: bold;
  }
  .panThumb {
    z-index: 100;
    height: 70px !important;
    width: 70px !important;
    position: absolute !important;
    top: -45px;
    left: 0px;
    border: 5px solid #ffffff;
    background-color: #fff;
    margin: auto;
    box-shadow: none !important;
    ::v-deep .pan-info {
      box-shadow: none !important;
    }
  }
  .progress-item {
    margin-left: 20px;
    margin-bottom: 10px;
    font-size: 14px;
  }
  @media only screen and (max-width: 1510px) {
    .mallki-text {
      display: none;
    }
  }
}

.box-card-chart {
  width: 60%;
  height: 100%;
}
.item-name {
  width: 100px;
  font-weight: bold;
}
.edit-button {
  margin: 0 40%;
  margin-top: 30px;
}
</style>
