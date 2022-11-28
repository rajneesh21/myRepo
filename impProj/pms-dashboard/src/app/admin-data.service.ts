import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './User';
import { ErrorService } from './error.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminDataService {
  user: User[] | undefined;
  errorMsg: string | undefined;
  constructor(private http: HttpClient) { }

  getPhysician(): Observable<User[]> {
    return this.http.get<User[]>("http://localhost:8804/users/3");
  }

  getNurses() {
    return this.http.get<User[]>("http://localhost:8804/users/4");
  }

  getPatient() {
    return this.http.get<User[]>("http://localhost:8804/users/2");
  }

}
