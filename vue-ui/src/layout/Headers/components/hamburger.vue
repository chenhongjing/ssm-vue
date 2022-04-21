<template>
  <div class="hamburger-container" @click="toggleClick" id="hamburger">
    <!-- <el-icon :icon="icon" color="#409EFC"> -->
    <el-icon :icon="icon" :color="variables.menuBg">
      <arrow-left-bold v-if="icon === 'arrow-left-bold'" />
      <arrow-right-bold v-if="icon === 'arrow-right-bold'" />
    </el-icon>
  </div>
</template>

<script setup>
import { useStore } from 'vuex'
import { computed } from 'vue'
import variables from '@/styles/variables.scss'

const store = useStore()
const toggleClick = () => {
  store.commit('app/changeSiderType')
}
const icon = computed(() => {
  return store.getters.siderType ? 'arrow-left-bold' : 'arrow-right-bold'
})
</script>

<style lang="scss" scoped>
.hamburger-container {
  margin-right: 16px;
  box-sizing: border-box;
  cursor: pointer;
}
</style>
