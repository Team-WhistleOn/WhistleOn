import {RouteConfig} from 'vue-router';
import {passByAuth} from '@/router/navGuard';

const publicRoutes: RouteConfig[] = [
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/HomeView.vue'),
    beforeEnter: passByAuth(),
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/LoginView.vue'),
    beforeEnter: passByAuth(),
  },
  {
    path: '/signup',
    name: 'signup',
    component: () => import('@/views/SignupView.vue'),
    beforeEnter: passByAuth(),
  },
];

export default publicRoutes;
