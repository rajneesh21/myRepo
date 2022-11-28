import { ViewChild } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MatAccordion } from '@angular/material/expansion';

@Component({
  selector: 'app-demographic-info',
  templateUrl: './demographic-info.component.html',
  styleUrls: ['./demographic-info.component.css']
})
export class DemographicInfoComponent implements OnInit{
  @ViewChild(MatAccordion) accordion: MatAccordion | undefined;
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  datePick:string|undefined;
  selected1:string='';
  selected2:string='';
  selected3:string='';
  ddVal_1: string[] = ['Black','Asian','White','Latin','Cuban'];
  ddVal_2: string[] = ['Black','Asian','White','Latin','Cuban'];
  ddVal_3: string[] = ['English','Chinese','Spanish','Polish','Latino'];
  relation: string[] = ['Brother','Sister','Husband','Wife','Child','Friend'];
  constructor() { }

  ngOnInit(): void {
  }
  addEvent(event: MatDatepickerInputEvent<Date>){
    this.datePick = this.getAge(`${event.value}`);
  }

  getAge(dob:string):string{
    var today = new Date();
    var birthDate = new Date(dob);
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) 
    {
        age--;
    }
    return `${age}`;
  }
  

}
