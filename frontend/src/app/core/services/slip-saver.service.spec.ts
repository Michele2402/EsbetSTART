import { TestBed } from '@angular/core/testing';

import { SlipSaverService } from './slip-saver.service';

describe('SlipSaverService', () => {
  let service: SlipSaverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SlipSaverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
