import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ErrorService } from '../error.service';

@Component({
  selector: 'app-registration-page-hospital-user',
  templateUrl: './registration-page-hospital-user.component.html',
  styleUrls: ['./registration-page-hospital-user.component.css'],
})
export class RegistrationPageHospitalUserComponent implements OnInit {
  regForm: FormGroup | any;
  submitted = false;
  rolesArray = {
    3: 'Doctor',
    4: 'Nurse',
  };
  selectedRole: string | undefined;

  userpayload = {};
  result: any;

  constructor(
    private formbuilder: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private errors: ErrorService
  ) {}

  ngOnInit(): void {
    this.regForm = this.formbuilder.group({
      title: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      dateOfBirth: ['', Validators.required],
      roleId: ['', Validators.required],
    });
  }

  get formData() {
    return this.regForm.controls;
  }

  selected: number = 0;

  selectOption(id: any) {
    //getted from event
    console.log(this.selected);
  }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.regForm.invalid) {
      return;
    }
    let roleInt = +this.selected;
    this.regForm.value.roleId = roleInt;
    this.inviteUser(this.regForm.value);
  }

  inviteUser(val: any) {
    this.http.post('http://localhost:8802/add', val).subscribe((res) => {
      if (res != null) {
        this.sendResetPasswordEmail(val.email);
      } else {
        this.errors.showError('Email Id is already in use');
        this.reloadCurrentRoute();
      }
    });
  }

  reloadCurrentRoute() {
    let currentUrl = this.router.url;
    this.router
      .navigateByUrl('/patient', { skipLocationChange: true })
      .then(() => {
        this.router.navigate([currentUrl]);
        console.log(currentUrl);
      });
  }

  get title() {
    return this.regForm.get('title');
  }

  get roleId() {
    return this.regForm.get('roleId');
  }

  get firstName() {
    return this.regForm.get('firstName');
  }

  get lastName() {
    return this.regForm.get('lastName');
  }

  get email() {
    return this.regForm.get('email');
  }

  get dateOfBirth() {
    return this.regForm.get('dateOfBirth');
  }

  onReset() {
    this.submitted = false;
    this.regForm.reset();
  }

  sendResetPasswordEmail(email: string) {
    this.http
      .get('http://localhost:8802/forgetPassword?', {
        params: { emailId: email },
      })
      .subscribe(
        (response) => {
          if (response != null) {
            this.errors.showError('Registration Successful !!');
            this.router.navigateByUrl('patient');
          }
        },
        (err) => {
          this.errors.showError('Unable To Send Change Password Email');
        }
      );
  }
}
