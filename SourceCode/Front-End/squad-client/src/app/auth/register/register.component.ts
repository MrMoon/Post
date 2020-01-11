import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators, AbstractControl } from '@angular/forms';
import { AuthService } from '../../service/auth/auth.service';
import { Router } from '@angular/router';
import { ErrorStateMatcher } from '@angular/material/core';
import { MyErrorStateMatcher } from '../login/login.component';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  name: string = '';
  email: string = '';
  password: string = '';
  jobTitle: string = '';
  passwordConfirm: string = '';
  phoneNumber: string = '';
  type: string = 'User';
  isLoadingResults: boolean = false;
  matcher = new MyErrorStateMatcher();
  returnUrl: string = 'login';

  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      'name': [null, [Validators.required, Validators.min(2)]],
      'password': [null, [Validators.required, Validators.min(6)]],
      'passwordConfirm': new FormControl(null, [Validators.required, Validators.min(6)]),
      'email': [null, [Validators.required, Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-z]{2,4}$")]],
      'jobTitle':[null , Validators.required],
      'phoneNumber': [null, [Validators.required, Validators.pattern("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$")]],
    });
  }

  onFormSubmit(form: NgForm) {
    if (this.registerForm.invalid) return;
    this.authService.register(form).pipe(first()).subscribe(data => {
      this.router.navigate([this.returnUrl]);
    }, (error) => {
      console.log(error);
    });
  }

  passwordConfirming(c: AbstractControl): { invalid: boolean } {
    if (c.get('password').value !== c.get('confirmPassword').value) {
      return { invalid: true };
    }
  }
}