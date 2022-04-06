import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from './../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StationnamesearchService {

  constructor(private http: HttpClient) { }

  findstation(name: string):Observable<String[]> {
    //eutravel-service-app
    console.log(environment.apiURL+'/stationsearch')

    //const url = 'http://eutravel-service-app/eutravel/stationsearch';
    const url = 'http://localhost:8084/eutravel/stationsearch';
    //const url = environment.apiURL+'/stationsearch';
    const headers = new HttpHeaders()
    .append('Accept', 'application/json')
    .append('Access-Control-Allow-Origin', '*');
    const params = new HttpParams().set('name', name);
    return this.http.get<String[]>(url, {headers, params});
  }
}
