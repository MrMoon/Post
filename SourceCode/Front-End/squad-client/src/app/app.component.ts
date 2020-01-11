import { Component, OnInit } from '@angular/core';
import { User } from './models/user/User';
import { Router } from '@angular/router';
import { AuthService, UserService } from './service';
import { ResultComponent } from './result/result.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  isLoggedIn: boolean = false;
  name: string = '';
  description: string = '';
  title = 'squad-client';

  constructor(private router: Router , private authService: AuthService){ }

  ngOnInit(){
    this.isLoggedIn = this.authService.isUserLoggedIn();
    if (!this.isLoggedIn) this.router.navigate(['login']);
  }

  logout() {
    this.isLoggedIn = false;
    this.authService.logout();
  }
}
