import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  static user_id:number;

  constructor(private router : Router) { 

  }

  public static getUserId(){
    return this.user_id;
  }

  public static setUserId(id:number){
    this.user_id = id
    return
  }

  public login(token:string) {
    localStorage.setItem('ACCESS_TOKEN', token)
  }

  public isLoggedIn() {
    return localStorage.getItem('ACCESS_TOKEN')
  }

  public getToken() :any{
    return localStorage.getItem('ACCESS_TOKEN')
  }

  public logout() {
    localStorage.removeItem('ACCESS_TOKEN')
    localStorage.removeItem('id')
    localStorage.removeItem('role')
    return
  }

  public addId(id:string){
    return localStorage.setItem("id", id)
  }

  public getId():any{
    return localStorage.getItem("id")
  }

  public addRole(role:string){
    return localStorage.setItem("role", role)
  }

  public getRole():any{
    return localStorage.getItem("role")
  }


  /**
   * toDashboard
   */
  public toDashboard() {
    this.router.navigateByUrl('')
  }
  /**
   * toLogin
   */
  public toLogin() {
    window.location.replace('')
    return
  }
  
  public makeid(length: number) {
    var result = '';
    var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for (var i = 0; i < length; i++) {
      result += characters.charAt(Math.floor(Math.random() *
        charactersLength));
    }
    return result;
  }

}
