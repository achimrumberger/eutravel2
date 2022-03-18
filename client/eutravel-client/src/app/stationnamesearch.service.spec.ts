import { TestBed } from '@angular/core/testing';

import { StationnamesearchService } from './stationnamesearch.service';

describe('StationnamesearchService', () => {
  let service: StationnamesearchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StationnamesearchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
