import { IRoot } from '../types/interface';
import {getTokenFromCookie, getUserFromCookie} from '@/utils/cookies';

const state: IRoot = {
  accessToken: getTokenFromCookie() || null,
  userName: getUserFromCookie() || null,
};

export default state;
