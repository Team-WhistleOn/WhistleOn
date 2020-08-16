import {IRoot} from '@/types/interface';
import {MutationTree} from 'vuex';

const mutations: MutationTree<IRoot> = {
  SET_TOKEN(state: IRoot, token: string | null) {
    state.accessToken = token;
  },

  SET_USER(state: IRoot, userName: string | null) {
    state.userName = userName;
  },
};

export default mutations;


