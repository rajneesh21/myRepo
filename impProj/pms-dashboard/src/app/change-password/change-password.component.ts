import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ErrorService } from '../error.service';
import { User } from '../User';
import { UserEmail } from '../UserEmail';
import { UserLogin } from '../UserLogin';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
})
export class ChangePasswordComponent implements OnInit {
  public changePasswordForm!: FormGroup;
  public submitted = false;
  public passChanged = false;
  isLoggedIn: boolean = false;
  user: User = new User();
  userEmail: UserEmail = new UserEmail();
  userLogin: UserLogin = new UserLogin();
  public uid: number = -1;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private alert: ErrorService,
    private http: HttpClient
  ) {}

  MustMatch(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];
      if (matchingControl.errors && !matchingControl.errors.mustMatch) {
        return;
      }
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ mustMatch: true });
      } else {
        matchingControl.setErrors(null);
      }
    };
  }

  ngOnInit(): void {
    this.changePasswordForm = this.formBuilder.group(
      {
        tempPassword: ['', [Validators.required, Validators.minLength(6)]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', Validators.required],
      },
      {
        validator: this.MustMatch('password', 'confirmPassword'),
      }
    );
  }

  get formControl() {
    return this.changePasswordForm.controls;
  }
  onSubmit(): void {
    this.submitted = true;
    // stop here if form is invalid
    if (this.changePasswordForm.invalid) {
      return;
    }
    console.log(this.changePasswordForm.value);
    this.submitted = true;
    if (this.changePasswordForm.valid) {
      this.getUserIdFromAuthCode(this.changePasswordForm.value.tempPassword);
      console.log('Calling Reset Password End Point');
    }
  }

  onReset() {
    this.submitted = false;
    this.changePasswordForm.reset();
  }

  getUserIdFromAuthCode(authCode: string) {
    this.http
      .get<UserEmail>('http://localhost:8802/getUserEmailDetail/' + authCode)
      .subscribe((res: UserEmail) => {
        this.userEmail = { ...res };
        this.getUserDetailsFromUserId(this.userEmail.userId);
      });
  }

  getUserDetailsFromUserId(userId: number) {
    this.http
      .get<User>('http://localhost:8802/getUserInfo/' + userId)
      .subscribe((res: User) => {
        this.user = { ...res };
        this.userLogin.username = this.user.email;
        this.userLogin.password = this.changePasswordForm.value.password;
        this.resetPassword(this.userLogin);
      });
  }

  resetPassword(userLogin: UserLogin) {
    this.http.post('http://localhost:8801/resetPassword', userLogin).subscribe(
      (res) => {
        if (res != null) {
          this.passChanged = true;
          this.alert.showError('Password Changed Successfully !');
          this.router.navigateByUrl('login');
        }
      },
      (err) => {
        console.log(err);
        this.alert.showError('Failed To Change Password !');
        this.router.navigateByUrl('login');
      }
    );
  }
}
