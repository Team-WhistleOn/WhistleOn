import instance from './index';
import {IUser} from '@/store/store.interface';
import {AxiosResponse} from 'axios';

export function userLogin({email, password}: {email: string, password: string}): Promise<AxiosResponse<{msg: string, userName: string}>> {
  return instance.post('/users/login', {email, password});
}

export function userSignup(newUserInfo: IUser): Promise<AxiosResponse<{msg: string}>> {
  return instance.post('/users/signup', newUserInfo);
}

export function userEmailCheck({email}: {email: string}): Promise<AxiosResponse<{msg: string}>> {
  return instance.get(`/users/check/${email}`);
}
