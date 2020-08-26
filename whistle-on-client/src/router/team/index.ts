import {RouteConfig} from 'vue-router';

const teamRoutes: RouteConfig[] = [
  {
    path: '/team/new',
    component: () => import('@/views/NewTeamView.vue')
  },
];

export default teamRoutes;
