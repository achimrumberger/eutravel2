import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { debounceTime, finalize, map, startWith, switchMap, tap } from 'rxjs/operators';

import { Connection
 } from '../connection';
import { ConnectionsearchService } from '../connectionsearch.service';

 import { Station } from '../station';
import { StationnamesearchService } from '../stationnamesearch.service';

@Component({
  selector: 'app-connection-search',
  templateUrl: './connection-search.component.html',
  styleUrls: ['./connection-search.component.scss']
})
export class ConnectionSearchComponent implements OnInit {

  searchDepartureStationsCtrl = new FormControl();
  searchDestinationStationsCtrl = new FormControl();
  filteredDepartureStations: Array<Station> = [];
  filteredDestinationStations: Array<Station> = [];
  isLoading = false;
  errorDepMsg: String = '';
  errorDestMsg: String = '';
  travelDate: string = '';
  travelTime: string = '';
  numberOfTRavellers: string = '1';
  tariffClass: string = '2';

  foundConnections: Array<Station>  = [];

  constructor(private stationnameservice: StationnamesearchService,
    private connectionsearchservice: ConnectionsearchService) {

   }

  ngOnInit():void {
    this.searchDepartureStationsCtrl.valueChanges
      .pipe(
        debounceTime(500),
        tap(() => {
          this.errorDepMsg = "";
          this.filteredDepartureStations = [];
          this.isLoading = true;
        }),
        switchMap(value => this.stationnameservice.findstation(value)
          .pipe(
            finalize(() => {
              this.isLoading = false
            }),
          )//end of pipe from Observable return stationnameservice
        )//end of switchmap
      )//end of pipe from searchStationsCtrl
      .subscribe({
        next: (depstationName) => {
          depstationName.forEach((s) => {
            let stat:Station = {
              name:s
            };
            this.filteredDepartureStations.push(stat);
          })
        },
        error: (err) => {
            console.error('Error', err);
        }
      });this.searchDepartureStationsCtrl.value
//----------------------------------------------------------------
      this.searchDestinationStationsCtrl.valueChanges
      .pipe(
        debounceTime(500),
        tap(() => {
          this.errorDestMsg = "";
          this.filteredDestinationStations = [];
          this.isLoading = true;
        }),
        switchMap(name => this.stationnameservice.findstation(name)
          .pipe(
            finalize(() => {
              this.isLoading = false
            }),
          )//end of pipe from Observable return stationnameservice
        )//end of switchmap
      )//end of pipe from searchStationsCtrl
      .subscribe({
        next: (deststationName) => {
          deststationName.forEach((s) => {
            let stat:Station = {
              name:s
            };
            this.filteredDestinationStations.push(stat);
          })
        },
        error: (err) => {
            console.error('Error', err);
        }
      });
  } //end of ngInit

  search():void {
    console.log(this.travelDate);
    console.log(this.travelTime);
    console.log(this.numberOfTRavellers);
    console.log(this.tariffClass);
    console.log(this.searchDestinationStationsCtrl.value);
    console.log(this.searchDepartureStationsCtrl.value);
  }

  searchConnections():void {
    console.log(this.travelDate);
    console.log(this.travelTime);
    console.log(this.numberOfTRavellers);
    console.log(this.tariffClass);
    console.log(this.searchDestinationStationsCtrl.value);
    console.log(this.searchDepartureStationsCtrl.value);

    const conny = new Connection(
        this.searchDepartureStationsCtrl.value,
        this.searchDestinationStationsCtrl.value,
        '03/06/2022',//this.travelDate'',
        this.travelTime,
        this.numberOfTRavellers,
        this.tariffClass
    );
  this.connectionsearchservice.findsConnections(conny)
  .subscribe({
    next: (connections) => {
      connections.forEach((s) => {
        let stat:Station = {
          name:s
        };
        this.foundConnections.push(stat);
      })
    },
    error: (err) => {
        console.error('Error', err);
    }
  });
    /**
     * {
	"startStation":"Kirchheim (Teck)",
	"destinationStation":"Stuttgart Hbf",
	"travelStartTime":"12:00",
	"travelStartDate":"03/06/2022",
	"tariffClass":"1",
	"numberOfTravellers":"1"
}
     */

  }

}
