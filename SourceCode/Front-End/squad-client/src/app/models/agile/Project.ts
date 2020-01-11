import { User } from '../user/User';
import { Task } from './Task';

export class Project{
    id: string;
    projectName: string;
    teamName: string;
    date: Date;
    users: User[];
    tasks: Task[];
}