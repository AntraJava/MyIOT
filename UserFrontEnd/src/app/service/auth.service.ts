import {Injectable} from '@angular/core';
import {decodeToken} from "../components/signin/signin.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() {}

  getCurrentUser() {
    const token = localStorage.getItem("iotToken");
    if (!token) {
      return undefined;
    }
    const tokenPayload = decodeToken(token);
    // this.authService.currentUser = {username: email, email: email, name: tokenPayload.name, id: tokenPayload.sub};
    return {username: tokenPayload.email, email: tokenPayload.email, name: tokenPayload.name, id: tokenPayload.sub};
  }

}
