import { Component, OnInit } from '@angular/core';
import { User } from '../models/user/User';
import { UserService } from '../service';
import { FriendsService } from '../service/friends/friends.service';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.scss']
})
export class FriendsComponent implements OnInit {

  private user: User = JSON.parse(sessionStorage.getItem('user'));
  friends: Array<User>;

  constructor(private friendService: FriendsService , private userService: UserService) { }

  ngOnInit() {
    this.friendService.getFriends().subscribe(data => this.friends = data);
  }

  

}
