import {NgModule} from '@angular/core';
import {CommonModule,} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';

import {ComponentsComponent} from './components/components.component';
import {SignupComponent} from './components/signup/signup.component';
import {NucleoiconsComponent} from './components/nucleoicons/nucleoicons.component';
import {SigninComponent} from "./components/signin/signin.component";
import {ControlBoardComponent} from "./components/control-board/control-board.component";

const routes: Routes =[
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home',             component: ComponentsComponent },
    { path: 'signup',           component: SignupComponent },
    { path: 'login',           component: SigninComponent },
    { path: 'nucleoicons',      component: NucleoiconsComponent },
    { path: 'control',      component: ControlBoardComponent }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes,{
      //useHash: true
    })
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
