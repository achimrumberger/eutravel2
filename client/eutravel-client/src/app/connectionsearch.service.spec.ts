import { TestBed } from '@angular/core/testing';

import { ConnectionsearchService } from './connectionsearch.service';

describe('ConnectionsearchService', () => {
  let service: ConnectionsearchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConnectionsearchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
