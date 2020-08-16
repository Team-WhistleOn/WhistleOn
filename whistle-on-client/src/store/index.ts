import Vue from 'vue';
import Vuex, {ActionContext, Commit, Dispatch, StoreOptions} from 'vuex';
import team from './modules/team';
import {IRoot} from '../types/interface';
import state from '@/store/state';
import getters from '@/store/getters';
import mutations from '@/store/mutations';
import actions from '@/store/actions';

Vue.use(Vuex);


const store: StoreOptions<IRoot> = {
  modules: {
    team,
  },
  state,
  getters,
  mutations,
  actions,
};

export default new Vuex.Store(store);
