import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FeedComponent } from './feed/feed.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { AuthGuard } from './auth/auth.guard';


const routes: Routes = [
  {
    path:'',
    canActivate: [AuthGuard],
    component: FeedComponent,
    data: {title: 'User Feed'}
  },
  {
    path:'login',
    component: LoginComponent,
    data: {title: 'Login'}
  },
  {
    path:'register',
    component: RegisterComponent,
    data: {title: 'Register'}
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
