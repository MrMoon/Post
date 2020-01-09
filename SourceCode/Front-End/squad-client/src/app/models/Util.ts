import { HttpHeaders } from '@angular/common/http';

export class Utils {
    get token():string {
        return !sessionStorage.getItem("token") ? null : sessionStorage.getItem("token");
    }
    public createTokenHeader(): HttpHeaders {
        let reqOptions = new HttpHeaders().set( 'Content-Type', 'application/json' )
        if(this.token) {
            reqOptions = new HttpHeaders().set( 'Content-Type', 'application/json' ).set('Authorization', 'Bearer ' + this.token);
        }
        return reqOptions;
    }
}