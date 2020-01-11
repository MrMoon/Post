import { Component, OnInit } from '@angular/core';
import { PostService } from '../service/post/post.service';
import { AlertService } from '../service';
import { Post } from '../models/Post';
import { FormGroup, FormBuilder, Validators, NgForm } from '@angular/forms';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  posts: Array<Post>;
  postForm: FormGroup;
  description: string;
  name: string;

  constructor(private postService: PostService , private alertService: AlertService , private formBuilder:FormBuilder) { }

  ngOnInit() {
    this.postForm = this.formBuilder.group({
      'description': [null , [Validators.required , Validators.min(6)]],
      'name': [null , [Validators.required , Validators.min(2)]]
    })
    this.getPosts();
  }

  getPosts(){
    this.postService.getAllPosts().subscribe(data => this.posts = data);
  }

  onFormSubmit(form: NgForm): void{
    if(this.postForm.invalid) return;
    this.postService.createPost(form).subscribe(data => console.log(data));
  }

}
