import { EventEmitter, Injectable, Output } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  @Output() fire: EventEmitter<any> = new EventEmitter();
   constructor() {
    //  console.log('shared service started');
   }
   change() {
    // console.log('change started');
     this.fire.emit('true');
   }
   changeFalse() {
    // console.log('change started');
     this.fire.emit('false');
   }
   getEmittedValue() {
     return this.fire;
   }



}
