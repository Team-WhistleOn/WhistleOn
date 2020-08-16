import {AxiosInstance} from 'axios';
import store from '@/store';

export function setInterceptor(instance: AxiosInstance) {
  instance.interceptors.request.use(
    (config) => {
      config.headers.Authorization = store.state.accessToken;
      return config;
    },
    (error) => Promise.reject(error)
  );

  instance.interceptors.response.use(
    (config) => config,
    (error) => Promise.reject(error)
  );
  return instance;
}
