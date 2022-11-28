import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ErrorService } from '../error.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css'],
})
export class ForgotPasswordComponent implements OnInit {
  public forgotForm!: FormGroup;
  public submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private alert: ErrorService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.forgotForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
    });
  }

  get formControl() {
    return this.forgotForm.controls;
  }

  onSendEmail() {
    this.submitted = true;
    if (this.forgotForm.valid) {
      this.http.get(
        'http://localhost:8802/forgetPassword?' ,{params: {emailId: this.forgotForm.value.email}}
      ).subscribe(
        (res) => {
          if(res==null){
            this.alert.showError('Password Reset Email Not Sent');
            this.forgotForm.reset({});
          }else{
           this.alert.showError('Password Reset Email Sent Successfully');
           this.router.navigateByUrl('changePassword');
          }
        }
      );
    }
  }

  onBack() {
    this.router.navigateByUrl('login');
  }

}
