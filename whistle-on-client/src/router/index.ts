import Vue from 'vue';
import VueRouter, {RouteConfig} from 'vue-router';
import publicRoutes from '@/router/public';
import userRoutes from '@/router/user';

Vue.use(VueRouter);

const routes: RouteConfig[] = [
  ...publicRoutes,
  ...userRoutes,
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
