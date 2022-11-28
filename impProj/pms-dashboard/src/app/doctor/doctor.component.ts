import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Schedule } from '../schedule';
import { User } from '../User';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit {

  upcomming_schedule: Schedule[] = []
  noData: boolean = false;
  id!:number

  constructor(private auth :AuthService, private http : HttpClient) { }

  ngOnInit(): void {
    this.id = this.auth.getId()
    this.getAppointments(this.id)
  }

  getAppointments(id:number) {
    this.http.get<Schedule[]>("http://localhost:8804/schedules/" + id).subscribe(res => {
    this.upcomming_schedule = res
     if (this.upcomming_schedule.length == 0) {
      this.noData = true;
    }
  })
  }

}
