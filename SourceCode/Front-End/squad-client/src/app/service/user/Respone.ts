import { User } from 'src/app/models/user/User';

export class Respone{
    _embedded: {
        users: User[];
        _links: { self: { href: string } };
    };
    users?: User[];
}