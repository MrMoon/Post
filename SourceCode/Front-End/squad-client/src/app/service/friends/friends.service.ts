import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { User } from 'src/app/models/user/User';

export class Two{
  idUser: string;
  idFriend: string;
  constructor(private ID0: string , private ID1: string){
    this.idUser = ID0;
    this.idFriend = ID1;
  }
}


@Injectable({
  providedIn: 'root'
})
export class FriendsService {

  private user: User = JSON.parse(sessionStorage.getItem('user'));
  private url: string = environment.apiUrl + '/api/users/' + this.user.id + '/';

  constructor(private http:HttpClient) { }

  getFriends(): Observable<any>{
    return this.http.get(this.url + 'friends'); 
  }

  addFriend(id: string): Observable<any>{
    let myId: string = this.user.id;
    let add: Two = new Two(this.user.id , id);
    return this.http.post(environment.apiUrl + '/api/users/add', add, { responseType: 'text' });
  }
}
