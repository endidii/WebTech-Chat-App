import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ChatUIView from '../views/ChatUIView.vue'
import LogInView from "@/views/LogInView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/users/:userId',
      name: 'ChatUI',
      component: ChatUIView
    },
    {
      path: '/login',
      name: 'LoginView',
      component: LogInView
    },
  ]
})

export default router
