import { Component, OnInit } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { AdminDataService } from '../admin-data.service';
import { ErrorService } from '../error.service';
import { PatientComponent } from '../patient/patient.component';
import { User } from '../User';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';
import { HttpClient } from '@angular/common/http';
import { analyzeAndValidateNgModules } from '@angular/compiler';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  background: ThemePalette = 'primary';
  result: any[] | undefined;
  checked: boolean = false;
  displayedColumns: string[] = ['userId', 'firstName', 'createdAt', 'isActive', 'action','blocked'];

  constructor(private adminDataService: AdminDataService, private http: HttpClient,
    private errorService: ErrorService, private router: ActivatedRoute, private auth: AuthService) {

  }

  physician: User[] = [];
  nurse: User[] = [];
  patient: User[] = [];
  errorMsg: string | undefined;
  isPhysicianAvailable: boolean = false;
  isNurseAvailable: boolean = false;
  isPatientAvailable: boolean = false;

  ngOnInit(): void {

    this.getAllPatient();
    this.getAllPhysician();
    this.getAllNurse();

  }


  getAllPatient() {
    this.adminDataService.getPatient().subscribe(res => {
      this.patient = res;

      if (this.patient.length != 0) {
        this.isPatientAvailable = true;

      }
    }, error => {
      this.errorMsg = error;
      this.errorService.showError("Something went wrong while fetching patient data");
    });

  }

  getAllPhysician() {
    this.adminDataService.getPhysician().subscribe(res => {
      this.physician = res;

      if (this.physician.length != 0) {
        this.isPhysicianAvailable = true;

      }
    }, error => {
      this.errorMsg = error;
      this.errorService.showError("Something went wrong while fetching physiscian data");
    });
  }

  getAllNurse() {
    this.adminDataService.getNurses().subscribe(res => {
      this.nurse = res;
      if (this.nurse.length != 0) {
        this.isNurseAvailable = true;
      }
    }, error => {
      this.errorMsg = error;
      this.errorService.showError("Something went wrong while fetching nurses data");
    });
  }

  userObj: User = new User
  onChange(status: any, id: any) {

    this.userObj.userId = id;

    this.userObj.isActive = status;

    this.http.post<User>("http://localhost:8804/statusUpdate", this.userObj).subscribe(res => {
      console.log("value fetched" + res)


    });
  }

}


