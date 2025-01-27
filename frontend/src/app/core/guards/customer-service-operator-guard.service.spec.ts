import { TestBed } from '@angular/core/testing';

import { CustomerServiceOperatorGuardService } from './customer-service-operator-guard.service';

describe('CustomerServiceOperatorGuardService', () => {
  let service: CustomerServiceOperatorGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomerServiceOperatorGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
