import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Connection } from './connection';
import { environment } from './../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConnectionsearchService {

  constructor(private http: HttpClient) { }

  findsConnections(con: Connection):Observable<String[]> {
    console.log(con.destinationStation);
    console.log(con.startStation);
    //eutravel-service-app
    console.log(environment.apiURL+'/connections')
    //const url = 'http://eutravel-service-app/eutravel/connections';
    const url = 'http://localhost:8084/eutravel/connections';
    const headers = new HttpHeaders()
    .append('Content-Type', 'application/json')
    .append('Access-Control-Allow-Origin', '*');
    const body=JSON.stringify(con);
    console.log('------------------');
    console.log(body);
    // const params = new HttpParams()
    // .set('startStation', con.startStation)
    // .set('destinationStation', con.destinationStation)
    // .set('travelStartTime', con.travelStartTime)
    // .set('travelStartDate', con.travelStartDate)
    // .set('tariffClass', con.tariffClass)
    // .set('numberOfTravellers', con.numberOfTravellers);
    return this.http.post<String[]>(url, body, {headers});
  }
}
