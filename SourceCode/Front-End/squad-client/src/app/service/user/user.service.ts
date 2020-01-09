import { Injectable } from '@angular/core';
import { User } from '../../models/User';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http'
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { headersToString } from 'selenium-webdriver/http';
import { ApiResponse } from 'src/app/models/api.respone';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl: string = `${environment.apiUrl}/users`;
  private header: HttpHeaders = new HttpHeaders();

  constructor(private http: HttpClient) { 
    this.header.set('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
  }

  getUsers(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl , {headers : this.header});
  }

}
