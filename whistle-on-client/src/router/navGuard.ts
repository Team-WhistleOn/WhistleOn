import {NavigationGuardNext, Route} from 'vue-router';
import store from '@/store';

export const requireAuth = () => (to: Route, from: Route, next: NavigationGuardNext) => {
  if (store.getters.isLoggedIn) {
    next();
  } else {
    next('/home');
  }
};

export const passByAuth = () => (to: Route, from: Route, next: NavigationGuardNext) => {
  if (store.getters.isLoggedIn) {
    next('/');
  } else {
    next();
  }
};
