import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  ss:SharedService
  constructor(private auth:AuthService, ss:SharedService) { 
    this.ss = ss;
  }

  ngOnInit(): void {
    this.ss.change()
    this.auth.logout();
    this.auth.toLogin();
  }

}
