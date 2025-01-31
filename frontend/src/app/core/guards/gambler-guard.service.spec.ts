import { TestBed } from '@angular/core/testing';

import { GamblerGuardService } from './gambler-guard.service';

describe('GamblerGuardService', () => {
  let service: GamblerGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GamblerGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
