import { Component, Input, OnInit } from '@angular/core';
import { ConnectionDAO } from '../connectiondao';

@Component({
  selector: 'app-connection-card',
  templateUrl: './connection-card.component.html',
  styleUrls: ['./connection-card.component.scss']
})
export class ConnectionCardComponent implements OnInit {

  @Input() connection: ConnectionDAO|null=null;
  @Input() selected = false;

    select() {
        this.selected = true;
    }

    deselect() {
        this.selected = false;
    }

  constructor() { }

  ngOnInit(): void {
  }

}
