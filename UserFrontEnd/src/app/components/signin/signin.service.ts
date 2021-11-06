import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {map} from "rxjs/operators";
import {AuthService} from "../../service/auth.service";

@Injectable({
  providedIn: 'root'
})
export class SigninService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authService: AuthService) { }

  signIn(email: string, password: string): Observable<any> {
    const options = { headers: new HttpHeaders({'Content-Type': 'application/json'}) };
    const data = {username:email, password:password}
    return this.http.post<{token:string}>(this.baseUrl + '/authenticate', data, options).pipe(map(data => {
      localStorage.setItem('iotToken', data.token);
      const tokenPayload = decodeToken(data.token);
      // this.authService.currentUser = {username: email, email: email, name: tokenPayload.name, id: tokenPayload.sub};
      return tokenPayload;
    }));
  }
}
export function decodeToken(token) {
  const _decodeToken = (token) => {
    try {
      return JSON.parse(atob(token));
    } catch {
      return;
    }
  };
  return token
      .split('.')
      .map(token => _decodeToken(token))
      .reduce((acc, curr) => {
        if (!!curr) acc = { ...acc, ...curr };
        return acc;
      }, Object.create(null));
}