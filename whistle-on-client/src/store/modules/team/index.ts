import state from './state';
import getters from './getters';
import mutations from './mutations';
import actions from './actions';
import {Module} from 'vuex';
import {ITeam, IRoot} from '@/types/interface';

const teamModules: Module<ITeam, IRoot> = {
  state,
  getters,
  mutations,
  actions,
};

export default teamModules;
