import { TestBed } from '@angular/core/testing';

import { CheefService } from './cheef.service';

describe('CheefService', () => {
  let service: CheefService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheefService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
