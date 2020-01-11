import {Component, OnInit} from '@angular/core';
import {
  FormControl,
  FormGroupDirective,
  FormBuilder,
  FormGroup,
  NgForm,
  Validators
} from '@angular/forms';
import {AuthService} from '../../service/auth/auth.service';
import {Router, ActivatedRoute} from '@angular/router';
import {ErrorStateMatcher} from '@angular/material/core';
import {tokenName} from '@angular/compiler';
import {AlertService} from 'src/app/service/alert/alert.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  email: string = '';
  password: string = '';
  returnUrl: string = '';
  matcher = new MyErrorStateMatcher();
  isLoadingResults: boolean = false;
  showErrorMessage: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthService, private route: ActivatedRoute, private alertService: AlertService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      'email': [null, [Validators.required, Validators.email]],
      'password': [null, [Validators.required, Validators.min(6)]]
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onFormSubmit(form: NgForm): void {
    if (this.loginForm.invalid) return;
    console.log(form);
    this.alertService.clear();
    this.authService.login(form).subscribe(data => {
      console.log(data);
      if (data.token) {
        sessionStorage.setItem('token', data.token);
        this.router.navigate([this.returnUrl]);
      }
    }, (error) => {
      this.alertService.error(error);
      console.log(error);
      this.showErrorMessage = true;
    });
  }

  register() {
    this.router.navigate(['register']);
  }
}

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
