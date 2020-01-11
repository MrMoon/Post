import { User } from '../user/User';

export class Event{
    id: string;
    name: string;
    type: string;
    date: Date;
    users: User[];
}