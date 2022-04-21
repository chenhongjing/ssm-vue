<template>
  <el-breadcrumb separator="/">
    <el-breadcrumb-item v-for="(item, index) in breadcrumbList" :key="index">
      <!-- 面包屑上的路径的最后一项路径是灰色的不能跳转，前面的路径都可以跳转 -->
      <span
        class="not-allow-redirect"
        v-if="index === breadcrumbList.length - 1"
        >{{ item.name }}</span
      >
      <span class="allow-redirect" v-else @click="handleRedirect(item.path)">{{
        item.name
      }}</span>
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { watch, ref } from 'vue'

// 面包屑的数据从路由表中取
const route = useRoute()
// 每次点击菜单到新的子页面时，要更新路由表
const breadcrumbList = ref([])

const initBreadcrumbList = () => {
  // 获取完整的路由表
  console.log(route.matched)
  breadcrumbList.value = route.matched
}

// 处理点击面包屑上都项目进行跳转
const router = useRouter()
const handleRedirect = (path) => {
  router.push(path)
}

watch(
  route,
  () => {
    initBreadcrumbList()
  },
  { deep: true, immediate: true }
)
</script>

<style lang="scss" scoped>
.not-allow-redirect {
  color: #97a8be;
  cursor: text;
}
.allow-redirect {
  color: #666;
  font-weight: 600;
  cursor: pointer;
  &:hover {
    color: $menuBg;
  }
}
</style>
