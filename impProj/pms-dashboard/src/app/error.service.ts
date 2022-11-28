import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class ErrorService {

  constructor( private snackbar: MatSnackBar ) { }


  public showError(errorString :string){
    this.snackbar.open(errorString, 'Ok', {
      duration: 3000
    });
  }
}
