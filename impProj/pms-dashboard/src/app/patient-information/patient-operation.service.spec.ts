import { TestBed } from '@angular/core/testing';

import { PatientOperationService } from './patient-operation.service';

describe('PatientOperationService', () => {
  let service: PatientOperationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientOperationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
