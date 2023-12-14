import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/SignupView.vue'
import ChatUIView from '../views/ChatUIView.vue'
import LogInView from "@/views/LogInView.vue";
import SignupView from "@/views/SignupView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/signup',
      name: 'Signup',
      component: SignupView
    },
    {
      path: '/users/:userId',
      name: 'ChatUI',
      component: ChatUIView
    },
    {
      path: '/',
      name: 'LoginView',
      component: LogInView
    },
  ]
})

export default router
