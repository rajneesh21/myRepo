import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationPagePatientUserComponent } from './registration-page-patient-user.component';

describe('RegistrationPagePatientUserComponent', () => {
  let component: RegistrationPagePatientUserComponent;
  let fixture: ComponentFixture<RegistrationPagePatientUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationPagePatientUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationPagePatientUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
