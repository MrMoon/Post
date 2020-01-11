import { Component, OnInit } from '@angular/core';
import { User } from '../models/user/User'
import { AuthService } from '../service/auth/auth.service'
import { Router } from '@angular/router'
import { UserService } from '../service/user/user.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { log } from 'util';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit {

  users: Array<any>;
  isLoadingResults = true;
  private user: User = JSON.parse(sessionStorage.getItem('user'));

  constructor(private userService: UserService, private http: HttpClient
    , private authService: AuthService , private router: Router) { }

  ngOnInit() {
    this.userService.getAllUsers().subscribe(data => this.users = data);
    this.userService.getFriends(this.user.id).subscribe(data => this.user.friends = data);
  }

  addFriend(id: string){
    console.log('Friend ID is ' + id);
    this.userService.addFriend(this.user.id , id).subscribe(data => console.log(data));
  }
}
