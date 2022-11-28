import { Component, OnInit } from '@angular/core';
import jwt_decode from 'jwt-decode';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-left-pane',
  templateUrl: './left-pane.component.html',
  styleUrls: ['./left-pane.component.css']
})
export class LeftPaneComponent implements OnInit {

  constructor(private auth : AuthService) { }

  userName!:string;
  role!:string
  roleArray!:string[]
  ngOnInit(): void {
    this.setValuesForLeftPane()
  }


  setValuesForLeftPane(): void{
    var decoded = this.getDecodedAccessToken(this.auth.getToken())
    this.userName = decoded['name']
    this.roleArray = decoded['realm_access']['roles']
    if(this.roleArray.includes('patient')){
      this.role ='patient'
    }else if(this.roleArray.includes('doctor')){
      this.role ='doctor'
    }else if(this.roleArray.includes('nurse')){
      this.role ='nurse'
    }
  }

  getDecodedAccessToken(token: string): any {
    try{
        return jwt_decode(token);
    }
    catch(Error){
        return null;
    }

  }
}


