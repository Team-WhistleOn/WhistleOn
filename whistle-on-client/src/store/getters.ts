import {GetterTree} from 'vuex';
import {IRoot} from '@/store/store.interface';

const getters: GetterTree<IRoot, IRoot> = {
  isLoggedIn(state) {
    return Boolean(state.accessToken);
  },
};

export default getters;
