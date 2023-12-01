import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ChatUIView from '../views/ChatUIView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: 'https://blendr-five.vercel.app',
      name: 'home',
      component: HomeView
    },
    {
      path: '/chat',
      name: 'chatUI',
      component: ChatUIView
    }
  ]
})

export default router
