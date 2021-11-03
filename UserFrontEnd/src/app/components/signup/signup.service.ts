import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Customer} from "../../shared/entity/customer";

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  register(name:string, email:string, password:string):Observable<Customer> {
    const options = { headers: new HttpHeaders({'Content-Type': 'application/json'}) };
    const data = {username:email, name:name, email:email, password:password}
    return this.http.post<Customer>(this.baseUrl + '/customer', data, options);
  }
}
