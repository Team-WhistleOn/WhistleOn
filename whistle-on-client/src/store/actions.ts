import {ActionContext, ActionTree, Commit, Dispatch} from 'vuex';
import {IRoot, IUser} from '@/types/interface';
import {userLogin, userSignup, userEmailCheck} from '@/api/auth';
import {saveTokenToCookie, deleteCookies} from '@/utils/cookies';
import {AxiosResponse} from 'axios';

const actions: ActionTree<IRoot, IRoot> = {
  async LOG_IN(
    {commit}: {commit: Commit},
    {email, password}: {email: string, password: string}
  ) {
    try {
      const {
        headers: {Authorization},
        data: {msg, userName},
        status,
      }: AxiosResponse = await userLogin({email, password});

      if (status === 200) {
        saveTokenToCookie(Authorization);
        commit('SET_TOKEN', Authorization);
        commit('SET_USER', userName);
        alert(msg);
        return true;
      }
      return false;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async SIGN_UP({commit, dispatch}: {commit: Commit, dispatch: Dispatch}, newUserInfo: IUser) {
    try {
      const {status, data: {msg}}: AxiosResponse = await userSignup(newUserInfo);
      if (status === 201) {
        alert(msg);
        const loginResult = await dispatch('LOG_IN', {
          email: newUserInfo.email,
          password: newUserInfo.password,
        });
        return loginResult;
      }
      return false;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async CHECK_EMAIL(context: ActionContext<IRoot, IRoot>, {email}: {email: string}): Promise<boolean> {
    try {
      const {status, data: {msg}}: AxiosResponse = await userEmailCheck({email});
      if (status === 200) {
        alert(msg);
        return true;
      } else if (status === 409) { // 409 충돌 (사용자가 해결할 수 있는 에러)
        throw new Error('이미 존재하는 이메일입니다.');
      }
      return false;
    } catch (error) {
      alert(error.message);
      console.error(error);
      return false;
    }
  },

  async LOG_OUT({ commit }: { commit: Commit }) {
    try {
      const confirmLogout = confirm('로그아웃 하시겠습니까?');
      if (confirmLogout) {
        deleteCookies('whistle-on_token');
        deleteCookies('whistle-on_user');
        commit('SET_TOKEN', null);
        commit('SET_USER', null);
        return true;
      }
      return false;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
};

export default actions;
