import { createRouter, createWebHashHistory } from 'vue-router'
import layout from '@/layout/index'
const routes = [
  {
    path: '/',
    name: '主页',
    component: layout,
    redirect: '/dynamic_param',
    children: [
      {
        path: 'human',
        name: '人体介绍',
        component: () => import('@/views/humans/index.vue')
      },
      {
        path: 'organ',
        name: '人体材料',
        component: () => import('@/views/organs/index.vue')
      },
      {
        path: 'animal',
        name: '动物介绍',
        component: () => import('@/views/animals/index.vue')
      },
      {
        path: 'substitute',
        name: '动物材料',
        component: () => import('@/views/substitutes/index.vue')
      },
      {
        path: 'user',
        name: '个人管理',
        component: () => import('@/views/user/index.vue')
      },
      {
        path: 'dynamic_param',
        name: '动态力学参数',
        component: () => import('@/views/dynamic_params/index.vue')
      },
      {
        path: 'other_param',
        name: '其他参数',
        component: () => import('@/views/other_params/index.vue')
      },
      {
        path: 'log',
        name: '日志',
        component: () => import('@/views/logs/index.vue')
      },
      {
        path: 'users_management',
        name: '用户管理',
        component: () => import('@/views/users_management/index.vue')
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/index.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
