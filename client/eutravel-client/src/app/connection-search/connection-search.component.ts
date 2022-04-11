import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { debounceTime, finalize, switchMap, tap } from 'rxjs/operators';
import { formatDate } from '@angular/common';

import {
  Connection
} from '../connection';
import { ConnectionsearchService } from '../connectionsearch.service';

import { Station } from '../station';
import { StationnamesearchService } from '../stationnamesearch.service';
import { ConnectionDAO } from '../connectiondao';

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
  isWaiting = false;
  errorDepMsg: String = '';
  errorDestMsg: String = '';
  travelDate: string = '';
  travelTime: string = '';
  numberOfTRavellers: string = '1';
  tariffClass: string = '2';

  foundConnections: Array<ConnectionDAO> = [];

  constructor(private stationnameservice: StationnamesearchService,
    private connectionsearchservice: ConnectionsearchService) {

  }

  ngOnInit(): void {
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
            let stat: Station = {
              name: s
            };
            this.filteredDepartureStations.push(stat);
          })
        },
        error: (err) => {
          console.error('Error', err);
        }
      }); this.searchDepartureStationsCtrl.value
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
            let stat: Station = {
              name: s
            };
            this.filteredDestinationStations.push(stat);
          })
        },
        error: (err) => {
          console.error('Error', err);
        }
      });
  } //end of ngInit

  searchConnections(): void {
    this.isWaiting = true;
    console.log(this.travelDate);
    console.log(formatDate(this.travelDate, 'dd/MM/yyyy', 'en-DE'));
    console.log(this.travelTime);
    console.log(this.numberOfTRavellers);
    console.log(this.tariffClass);
    console.log(this.searchDestinationStationsCtrl.value);
    console.log(this.searchDepartureStationsCtrl.value);
    console.log("searchConnections: "+this.isWaiting);

    const conny = new Connection(
      this.searchDepartureStationsCtrl.value,
      this.searchDestinationStationsCtrl.value,
      formatDate(this.travelDate, 'dd/MM/yyyy', 'en-DE'),//this.travelDate'',
      this.travelTime,
      this.numberOfTRavellers,
      this.tariffClass
    );
    this.connectionsearchservice.findsConnections(conny)
      .pipe(
        tap(
          () => {
            this.foundConnections = [];
            console.log("tap: "+this.isWaiting);
          }),
          finalize(() => {
            this.isWaiting = false;
            console.log("finalize: "+this.isWaiting);
          })
      )//end of pipe
      .subscribe(
        {
          next: (connections) => {
            this.foundConnections = connections;
            console.log("subscribe: " + this.isWaiting);
          },
          error: (err) => {
            console.error('Error', err);
          }
        });//end of subscribe
  }//end of searchConnections

}
