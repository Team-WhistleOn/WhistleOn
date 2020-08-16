import Axios, {AxiosInstance} from 'axios';
import {setInterceptor} from './common/interceptors';

const instance: AxiosInstance = Axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 10000,
});

export default setInterceptor(instance);
