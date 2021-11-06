import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';
import {AuthService} from './auth.service';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(public auth: AuthService, public router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRole = route.data.expectedRole;
    const token = localStorage.getItem("iotToken");
    if (!token) {
      console.log("Token doesn't exist.");
      this.router.navigate(['login']);
      return false;
    }
    // const tokenPayload = token ? decodeToken(token) : {};
    // console.log(tokenPayload);
    return true;
  }

}
