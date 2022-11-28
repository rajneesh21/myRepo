import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RestapiService } from '../restapi.service';
import { User } from '../User';
import jwt_decode from 'jwt-decode';
import { AuthService } from '../auth.service';
import { SharedService } from '../shared.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ErrorService } from '../error.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  userDetails: User | undefined;
  message: any = 'sucess-Ful';
  error: string | undefined;
  public loginForm!: FormGroup;
  public submitted = false;
  isLoggedIn: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private loginService: RestapiService,
    private authService: AuthService,
    private sharedService: SharedService,
    private http: HttpClient,
    private errors: ErrorService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.email, Validators.required]],
      password: [
        '',
        [
          Validators.required,
          // Validators.pattern(
          //   "(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!#^~%*?&,.<>\"'\\;:{\\}\\[\\]\\|\\+\\-\\=\\_\\)\\(\\)\\`\\/\\\\\\]])[A-Za-z0-9d$@].{7,}"
          // )
        ],
      ],
    });
  }

  get formControl() {
    return this.loginForm.controls;
  }

  onLogin(): void {
    this.submitted = true;
    if (this.loginForm.valid) {
      this.loginService.login(this.loginForm.value).subscribe((res) => {
        // console.log(res);
        var keysArr = Object.keys(res);
        if (keysArr.includes('access_token')) {
          var jsonObj = JSON.parse(JSON.stringify(res));
          this.authService.login(jsonObj['access_token']);
          this.sharedService.change();
          var decodedObj = this.getDecodedAccessToken(jsonObj['access_token']);
          var userRole = decodedObj['realm_access']['roles'];
          this.getUserDetails(userRole).subscribe((res) => {
            var jsonObj = JSON.parse(JSON.stringify(res));
            this.authService.addId(jsonObj['userId']);
            // this.authService.addRole(userRole)

            if (userRole.includes('patient')) {
              this.authService.addRole('patient');
              this.router.navigateByUrl('patient').then(() => {
                window.location.reload();
              });
            } else if (userRole.includes('doctor')) {
              this.authService.addRole('doctor');
              this.router.navigateByUrl('doctor').then(() => {
                window.location.reload();
              });
            } else if (userRole.includes('nurse')) {
              this.authService.addRole('nurse');
              this.router.navigateByUrl('nurse').then(() => {
                window.location.reload();
              });
            } else if (userRole.includes('admin')) {
              this.authService.addRole('admin');
              this.router.navigateByUrl('admin').then(() => {
                window.location.reload();
              });
            } else {
              // if user does not have roles.
              this.errors.showError(
                'No Roles Associted With Your Account. Contact Admin'
              );
              // Commented below line in case of missing role
              // this.router.navigateByUrl('home')
            }
          });
        } else {
          // alert(JSON.stringify(res))
          var errorData = JSON.parse(JSON.stringify(res));
          this.errors.showError(errorData['error']);
        }
      });
    }
  }

  getUserDetails(role: string): Observable<User> {
    console.log(this.loginForm.value);
    return this.http.get<User>(
      'http://localhost:8802/getUserDetail/' + this.loginForm.value.username
    );
  }

  getRole(rolesArray: any): string {
    return rolesArray[1];
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch (Error) {
      return null;
    }
  }
}
