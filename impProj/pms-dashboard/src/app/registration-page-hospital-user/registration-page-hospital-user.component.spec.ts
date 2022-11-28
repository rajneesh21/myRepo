import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationPageHospitalUserComponent } from './registration-page-hospital-user.component';

describe('RegistrationPageHospitalUserComponent', () => {
  let component: RegistrationPageHospitalUserComponent;
  let fixture: ComponentFixture<RegistrationPageHospitalUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationPageHospitalUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationPageHospitalUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
