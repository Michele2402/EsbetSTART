import { TestBed } from '@angular/core/testing';

import { TransactionManagerGuardService } from './transaction-manager-guard.service';

describe('TransactionManagerGuardService', () => {
  let service: TransactionManagerGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransactionManagerGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
