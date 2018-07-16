import { TestBed, inject } from '@angular/core/testing';

import { ComputerComponentService } from './computer-component.service';

describe('ComputerComponentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ComputerComponentService]
    });
  });

  it('should be created', inject([ComputerComponentService], (service: ComputerComponentService) => {
    expect(service).toBeTruthy();
  }));
});
