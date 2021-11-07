import {Injectable} from '@angular/core';
import {Customer} from '../../shared/entity/customer';
import {Observable} from 'rxjs';
import {HomeConfig} from '../../shared/entity/home-config';
import {environment} from '../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class HomeConfigService {

    baseUrl = environment.baseUrl;

    constructor(private http: HttpClient) {
    }

    loadHome(currentUser: Customer): Observable<HomeConfig> {
        return this.http.get<any>(`${this.baseUrl}/home/user/${currentUser.id}`);
    }

    createHome(name: string, locationInfo: string): Observable<HomeConfig> {
      const options = { headers: new HttpHeaders({'Content-Type': 'application/json'}) };
      const data = {name:name, locationInfo:locationInfo}
      // return this.http.post<Customer>(this.baseUrl + '/home', data, options);
      return new Observable<HomeConfig>(subscriber => subscriber.next({name:'sweet home',id:'123'}));
    }
}
