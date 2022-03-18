export class Connection {

  startStation: string;
  destinationStation: string;
  travelStartDate: string;
  travelStartTime: string;
  tariffClass:string;
  numberOfTravellers: string;

  public constructor(from:string, to:string, date:string, time:string, numeberOfTravellers: string, tariffClass:string) {
    this.travelStartDate = date;
    this.startStation = from;
    this.numberOfTravellers = numeberOfTravellers;
    this.destinationStation = to;
    this.travelStartTime = time;
    this.tariffClass= tariffClass;
  }
}
