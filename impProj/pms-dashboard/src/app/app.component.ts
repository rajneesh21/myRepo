import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { SharedService } from './shared.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'PMS Login';

  isValid :boolean;
  ss: SharedService
  subscription:any
  role!:string 
  constructor(ss: SharedService, private auth:AuthService) {
    this.isValid = false
    this.ss = ss
  }

  ngOnInit() {
    if(this.auth.getId() != null){
      this.isValid = true;
      this.role = this.auth.getRole()
    }
  this.subscription = this.ss.getEmittedValue().subscribe(item => this.isValid=item);
 }

}
