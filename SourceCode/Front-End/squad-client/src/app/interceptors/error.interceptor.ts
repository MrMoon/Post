import { Injectable } from '@angular/core';

import  { HttpRequest , HttpHandler , HttpEvent , HttpInterceptor } from "@angular/common/http";

import { Observable , throwError } from "rxjs";

import { catchError } from "rxjs/operators";

import  { AuthService } from "../service/auth/auth.service";
import {AlertService} from "../service/alert/alert.service";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor{
  constructor(private authenticationService: AuthService , private alertService: AlertService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(catchError(error => {
      if([401 , 403].indexOf(error.status) !== -1){
        this.authenticationService.logout();
        location.reload(true);
      }
      console.log(JSON.stringify(error));
      const err = error.error.message || error.statusText;
      this.alertService.error(err);
      return throwError(err);
    }))
  }
}
