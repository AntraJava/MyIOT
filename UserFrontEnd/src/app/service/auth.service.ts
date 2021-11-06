import {Injectable} from '@angular/core';
import {Customer} from "../shared/entity/customer";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  currentUser: Customer;

  constructor() { }
}
