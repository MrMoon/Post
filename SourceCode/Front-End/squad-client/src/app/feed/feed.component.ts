import { Component, OnInit } from '@angular/core';
import { User } from '../models/User'
import { AuthService } from '../service/auth/auth.service'
import { Router } from '@angular/router'
import { UserService } from '../service/user/user.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit {

  users: User[];
  isLoadingResults = true;

  constructor(private userService: UserService
    , private authService: AuthService , private router: Router) { }

  ngOnInit() {
    this.getAll()
  }

  getAll() {
    this.userService.getUsers().subscribe(data => {
      this.users = data.result;
    });
  }

  logout() {
    sessionStorage.removeItem('token');
    this.router.navigate(['login']);
  }

}
