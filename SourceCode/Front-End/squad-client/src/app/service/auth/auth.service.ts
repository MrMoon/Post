import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { User } from 'src/app/models/User';
import { Router } from '@angular/router';

const apiUrl = 'http://localhost:8081/auth/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<string>;
  public currentUser: Observable<string>;
  isLoggedIn:boolean = false;
  redirectUrl:string; 
  
  constructor(private http:HttpClient , private router: Router) {
    this.currentUserSubject = new BehaviorSubject<string>(sessionStorage.getItem('token'));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): string{
    return this.currentUserSubject.value;
  }

  login(data: any): Observable<any> {
    console.log(data);
    return this.http.post<any>(apiUrl + 'login', data)
      .pipe(
        map(user => {
          if(user && user.token){
            let token = user.token;
            this.currentUserSubject.next(user);
            sessionStorage.setItem('token', 'Bearer ' + token);
          }
          return user;
        }),
        tap(_ => this.isLoggedIn = true),
        catchError(this.handleError('login', []))
      );
  }

  register(data: any): Observable<any> {
    console.log(data);
    return this.http.post<User>(apiUrl + 'register', data)
      .pipe(
        tap(_ => this.log('register')),
        catchError(this.handleError('register', []))
      );
  }


  logout() {
    sessionStorage.removeItem('token');
    this.currentUserSubject.next(null);
    return this.router.navigate(['']);
  }


  isUserLoggedIn() {
    return !(sessionStorage.getItem('token') === null)
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);
      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

  private log(message: string) {
    console.log(message);
  }
}
