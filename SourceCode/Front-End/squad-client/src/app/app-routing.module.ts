import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FeedComponent } from './feed/feed.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { AuthGuard } from './auth/auth.guard';
import { ResultComponent } from './result/result.component';
import { ModuleWithProviders } from '@angular/compiler/src/core';


const routes: Routes = [
  {
    path:'',
    canActivate: [AuthGuard],
    component: FeedComponent,
    data: {title: 'Feed'}
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
  },
  {
    path:'results',
    canActivate: [AuthGuard],
    component: ResultComponent,
    data: {title: 'Result'}
  },
  {
    path: '**',
    redirectTo: '' 
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes)

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
