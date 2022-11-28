import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private auth: AuthService
) { }
  
canActivate(
  next: ActivatedRouteSnapshot,
  state: RouterStateSnapshot): Observable<boolean>  | boolean {
  // If the previous URL was blank, then the user is directly accessing this page
  if (this.auth.getId() === null) {
    this.router.navigateByUrl('');// Navigate away to some other page
    return false;
  }
  return true;
}
}
