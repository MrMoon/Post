import { User } from './User';
import { Report } from '../agile/Report';
import { Project } from '../agile/Project';

export class Team{
    id: string;
    teamName: string;
    users: User[];
    reports: Report[];
    events: Event[];
    projects: Project[];
}