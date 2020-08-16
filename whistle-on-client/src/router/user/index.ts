import {RouteConfig} from 'vue-router';
import {requireAuth} from '../navGuard';

const userRoutes: RouteConfig[] = [
  {
    path: '/main',
    alias: '/',
    name: 'main',
    component: () => import('@/views/MainView.vue'),
    beforeEnter: requireAuth(),
  },
];

export default userRoutes;
