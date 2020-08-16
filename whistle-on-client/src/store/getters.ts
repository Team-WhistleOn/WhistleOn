import {GetterTree} from 'vuex';
import {IRoot} from '@/types/interface';

const getters: GetterTree<IRoot, IRoot> = {
  isLoggedIn(state) {
    return Boolean(state.accessToken);
  },
};

export default getters;
