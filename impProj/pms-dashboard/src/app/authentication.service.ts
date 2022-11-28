import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from './User';
import { map } from 'rxjs/operators';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;


  constructor(private http: HttpClient, private auth: AuthService) {
   
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(""));
    this.currentUser = this.currentUserSubject.asObservable();
}

public get currentUserValue(): User {
  return this.currentUserSubject.value;
}

login(username: string, password: string) {
  return this.http.post<any>("http://localhost:8802/login", { username, password })
      .pipe(map(user => {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('ACCESS_TOKEN', JSON.stringify(user));
          this.currentUserSubject.next(user);
          return user;
      }));
}

logout() {
  // remove user from local storage to log user out
  localStorage.removeItem('ACCESS_TOKEN');
  this.currentUserSubject.next(new User);
}

}
