import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Result } from 'src/app/models/agile/Result';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user/User';
import { map, tap } from 'rxjs/operators';

interface GetResponse {
  _embedded: {
    results: Result[];
    _links: { self: { href: string } };
  };
  results: Result[];
}

@Injectable({
  providedIn: 'root'
})
export class ResultService {

  private user: User = JSON.parse(sessionStorage.getItem('user'));
  private url: string = environment.apiUrl + '/api/results/' + this.user.id + '/';

  constructor(private http: HttpClient) { }

  getResult(id: string): Observable<Result[]>{
    return this.http.get<any>(this.url , { observe: 'response'}).pipe(map(data => data.body))
  }

  createResult(result: Object): Observable<Object>{
    console.log(this.url);
    return this.http.post(`${this.url}`, result);
  }

  deleteResult(id: string): Observable<Object>{
    return this.http.delete(this.url + id + '/');
  }

  getAllResults() : Observable<Result[]>{
    return this.http.get<GetResponse>(environment.apiUrl + '/results/').pipe(map(data => data._embedded.results));
  }
}
