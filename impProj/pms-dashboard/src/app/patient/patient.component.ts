import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Schedule } from '../schedule';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  upcomming_schedule: Schedule[] | undefined
  cancelled_schedule: Schedule[] | undefined
  noData: boolean = false;
  noDeclinedData: boolean = false;
  id!:number
  constructor(private http: HttpClient, private auth:AuthService) {
  }

  ngOnInit(): void {
    this.id = this.auth.getId()
    this.getAppointments(this.id)
    this.getDeclinedAppointments(this.id)
  }

  getAppointments(id:number) {
    this.http.get<Schedule[]>("http://localhost:8804/schedules/" + id).subscribe(res => {
    this.upcomming_schedule = res
     if (this.upcomming_schedule.length == 0) {
      this.noData = true;
    }
  })
  }

  getDeclinedAppointments(id:number) {
    this.http.get<Schedule[]>("http://localhost:8804/declinedSchedules/" +id).subscribe(res => {
      this.cancelled_schedule = res
       if (this.cancelled_schedule.length == 0) {
      this.noDeclinedData = true
    }
    })
  }

}
