import { Injectable } from '@angular/core'
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
    HttpResponse,
    HttpErrorResponse
} from '@angular/common/http'
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { from } from 'rxjs';
import { AlertService } from '../service/alert/alert.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    constructor(private router: Router, private alert: AlertService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log("Hello World From intercept");
        const token = 'Bearer ' + sessionStorage.getItem('token');
        const regexLogin = /login/gi;
        const regexReg = /register/gi;
        if (request.url.search(regexLogin) === -1 && request.url.search(regexReg) === -1 && token) {
            request = request.clone({setHeaders: { 'Authorization': token }});
        }
        request = request.clone({ headers: request.headers.set('Accept', 'application/json') });
        return next.handle(request).pipe(
            map((event: HttpEvent<any>) => {
                if (event instanceof HttpResponse) {
                    if (event.status != 200){
                        this.alert.error(event.statusText);
                        console.error(event.statusText);
                    }
                    else console.log('(HttpRespone) event--->>>', event);
                }
                return event;
            }),
            catchError((error: HttpErrorResponse) => {
                if (error.status === 401) {
                    this.router.navigate(['login']);
                }
                if (error.status === 400) {
                    alert(error.error);
                }
                if(error.status === 500){
                    console.log(error);
                }
                return throwError(error);
            }));
    }
}