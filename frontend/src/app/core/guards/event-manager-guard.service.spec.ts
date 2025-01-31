import { TestBed } from '@angular/core/testing';

import { EventManagerGuardService } from './event-manager-guard.service';

describe('EventManagerGuardService', () => {
  let service: EventManagerGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventManagerGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
