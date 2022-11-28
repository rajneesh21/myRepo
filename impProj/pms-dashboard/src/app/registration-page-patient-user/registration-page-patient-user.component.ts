import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ErrorService } from '../error.service';

@Component({
  selector: 'app-registration-page-patient-user',
  templateUrl: './registration-page-patient-user.component.html',
  styleUrls: ['./registration-page-patient-user.component.css']
})
export class RegistrationPagePatientUserComponent implements OnInit {

  regForm: FormGroup|any;

  userpayload = {}
  result:any;

  constructor(private formbuilder:FormBuilder, private http:HttpClient, private router: Router, private errors : ErrorService) { }

  ngOnInit(): void {
    this.regForm=this.formbuilder.group({
      title:['', Validators.required],
      firstName:['', Validators.required],
      lastName:['', Validators.required],
      email:['', [Validators.required, Validators.email]],
      dateOfBirth:['', Validators.required],
      password:['',[ Validators.required, Validators.minLength(6)]],
      roleId: 2
      // confirmPassword:['', Validators.required]
  });
}

get formData(){return this.regForm.controls}



onSubmit(){
  console.log(this.regForm.value);
  this.http.post('http://localhost:8802/add',this.regForm.value).subscribe(res => {

    if(res!=null){
      this.errors.showError("Registration Successful !!")
      this.router.navigateByUrl('login');
    }else{
      this.errors.showError("Email Id is already in use")
      this.reloadCurrentRoute();
    }
    console.log(res)
  })
}

reloadCurrentRoute() {
  let currentUrl = this.router.url;
  this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate([currentUrl]);
      console.log(currentUrl);
  });
}

get title(){
  return this.regForm.get('title');
}

get role(){
  return this.regForm.get('role');
}

get firstName(){
  return this.regForm.get('firstName');
}

get lastName(){
  return this.regForm.get('lastName');
}

get email(){
  return this.regForm.get('email');
}

get dateOfBirth(){
  return this.regForm.get('dateOfBirth');
}

get password(){
  return this.regForm.get('password');
}

}
