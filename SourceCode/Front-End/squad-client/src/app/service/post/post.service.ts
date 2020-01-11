import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Post } from 'src/app/models/Post';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { User } from 'src/app/models/user/User';

interface GetResponse {
  _embedded: {
    posts: Post[];
    _links: { self: { href: string } };
  };
}

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private user: User = JSON.parse(sessionStorage.getItem('user'));
  private url: string = environment.apiUrl + '/api/posts/';

  constructor(private http: HttpClient) { }

  getPosts(): Observable<Post[]>{
    return this.http.get<any>(this.url, { observe: 'response' }).pipe(map(data => data.body));
  }

  getAllPosts(): Observable<Post[]>{
    return this.http.get<GetResponse>(environment.apiUrl + '/posts/').pipe(map(data => data._embedded.posts));
  }

  createPost(data: Object): Observable<Object>{
    let post: Post = JSON.parse(JSON.stringify(data));
    post.author = this.user.name;
    console.log(JSON.stringify(post));
    return this.http.post(`${this.url}` , post);
  }

  deleteResult(id: string): Observable<Object> {
    return this.http.delete(this.url + id + '/');
  }
}
