import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Device} from "../../shared/entity/device";

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

    addDevice(device:Device):Observable<Device> {
      const options = { headers: new HttpHeaders({'Content-Type': 'application/json'}) };
      return this.http.post<Device>(this.baseUrl + '/device', device, options);
    }
}
