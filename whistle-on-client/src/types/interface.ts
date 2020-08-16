export interface IRoot {
  accessToken: string | null;
  userName: string | null;
  personal?: IUser;
  team?: ITeam;
  match?: IMatch;
}

export interface ITeam {
  logo?: string;
  location?: string;
  description?: string;
}

export interface IUser {
  email: string;
  password: string;
  userName: string;
  teamName: string | false;
  age: number;
  location: string;
  position: 'GK' | 'ST' | 'LW' | 'RW' | 'CAM' | 'CM' | 'CDM' | 'LB' | 'CB' | 'RB';
  win?: number;
  lose?: number;
  draw?: number;
  manner?: number;
  mvpCount?: number;
  type?: string;
  joinDate?: Date;
  withdrawalDate?: Date;
}

export interface IMatch {
  readonly matchId: number;
  home: ITeam;
  away: ITeam;
  mvp?: IUser;
  date: Date;
  location: string;
  stadium: string;
  status: 'beforeMatch' | 'duringMatch' | 'afterMatch';
  homeScore: number;
  awayScore: number;
  matchUsers: Array<{ user: IUser, userType: boolean }>;
}

export interface IQna {
  readonly qnaId: number;
  readonly userId: number;
  title: string;
  content: string;
  readonly regdate: Date;
}

export interface IQnaReply extends IQna {
  readonly qnaReplyId: number;
  readonly qnaId: number;
  content: string;
  readonly regdate: Date;
}

export interface IBoard {
  boardId: number;
  teamId: number;
  userId: number;
  categoryId: number;
  title: string;
  content: string;
  regdate: Date;
}

export interface IBoardReplay {
  boardReplyId: number;
  userId: number;
  boardId: number;
  content: string;
  readonly regdate: Date;
}

export interface ICategory {
  categoryId: number;
  categoryName: string;
}
