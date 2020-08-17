import instance from './index';
import {IUser} from '@/types/interface';
import {AxiosResponse} from 'axios';

export function userLogin(
  {email, password}: {email: string, password: string})
  : Promise<AxiosResponse<{
    headers: {
      Authorization: string
    },
    data: {
      msg: string,
      userName: string
    },
    status: number
  }>> {
  return instance.post('/users/login', {email, password});
}

export function userSignup(newUserInfo: IUser)
  : Promise<AxiosResponse<{data: { msg: string }}>> {
  return instance.post('/users/signup', newUserInfo);
}

export function userEmailCheck({email}: {email: string})
  : Promise<AxiosResponse<{ data: { msg: string } }>> {
  return instance.get(`/users/check/${email}`);
}
