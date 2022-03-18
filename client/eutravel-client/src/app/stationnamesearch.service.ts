import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StationnamesearchService {

  constructor(private http: HttpClient) { }

  findstation(name: string):Observable<String[]> {
    const url = 'http://localhost:8084/eutravel/stationsearch';
    const headers = new HttpHeaders()
    .set('Accept', 'application/json');
    const params = new HttpParams().set('name', name);
    return this.http.get<String[]>(url, {params});
  }
}
