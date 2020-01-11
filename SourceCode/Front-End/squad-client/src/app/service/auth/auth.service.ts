import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { User } from 'src/app/models/user/User';
import { Router } from '@angular/router';
import { AlertService } from '../alert/alert.service';

const apiUrl = 'http://localhost:8081/auth/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn: boolean = false;
  redirectUrl: string;

  constructor(private http: HttpClient, private router: Router ,private alertServie: AlertService) { }

  login(data: any): Observable<any> {
    return this.http.post<any>(apiUrl + 'login', data).pipe(map(user => {
      if (user && user.token) {
        delete user.USER.password;
        sessionStorage.setItem('user', JSON.stringify(user.USER));
        sessionStorage.setItem('token', 'Bearer ' + user.token);
      }
      return user;
    }),
      tap(_ => this.isLoggedIn = true),
      catchError(this.handleError('login', []))
    );
  }

  register(data: any): Observable<any> {
    this.alertServie.clear();
    console.log(data);
    return this.http.post<User>(apiUrl + 'register', data)
      .pipe(
        tap(_ => this.log('register')),
        catchError(this.handleError('register', []))
      );
  }


  logout() {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('user');
    return this.router.navigate(['login']);
  }


  isUserLoggedIn(): boolean {
    return !(sessionStorage.getItem('token') === null)
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      this.alertServie.error(error);
      return of(result as T);
    };
  }

  private log(message: string) {
    this.alertServie.error(message);
  }
}
