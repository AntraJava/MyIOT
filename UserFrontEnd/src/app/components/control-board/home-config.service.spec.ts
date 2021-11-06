import {TestBed} from '@angular/core/testing';

import {HomeConfigService} from './home-config.service';

describe('HomeConfigService', () => {
  let service: HomeConfigService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HomeConfigService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
