import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http'
import { Observable, of, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from 'src/app/models/user/User';
import { map, tap, catchError } from 'rxjs/operators';

interface GetResponse {
  _embedded: {
    users: User[];
    _links: { self: { href: string } };
  };
  users?: User[];
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<User[]> {
    return this.http.get<any>(environment.apiUrl + '/api/users/', { observe: 'response' }).pipe(map(data => data.body));
  }

  getFriends(id: string): Observable<any>{
    return this.http.get(environment.apiUrl + '/api/users/' + id + '/friends').pipe(map(data => console.log(data)));
  }

  addFriend(userId: string, friendId: string): Observable<any> {
    return this.http.get(environment.apiUrl + '/api/users/' + userId + '/' + friendId).pipe(map(data => console.log(data)));
  }

}
