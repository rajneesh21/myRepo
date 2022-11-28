import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AlertModule } from 'node_modules/ngx-bootstrap/alert';
import { LoginComponent } from './login/login.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { NotesComponent } from './notes/notes.component';
import { NotesCardComponent } from './notes-card/notes-card.component';
import { HomePageComponent } from './home-page/home-page.component';
import {HttpClientModule} from '@angular/common/http'
import { RestapiService } from './restapi.service';

import { RegistrationPageHospitalUserComponent } from './registration-page-hospital-user/registration-page-hospital-user.component';
import { RegistrationPagePatientUserComponent } from './registration-page-patient-user/registration-page-patient-user.component';
import { LogoutComponent } from './logout/logout.component';
import { PatientComponent } from './patient/patient.component';
import { LeftPaneComponent } from './left-pane/left-pane.component';
import { AdminComponent } from './admin/admin.component';
import { DoctorComponent } from './doctor/doctor.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTabsModule} from '@angular/material/tabs';

import {MatExpansionModule} from '@angular/material/expansion';
import { DemographicInfoComponent } from './patient-information/demographic-info/demographic-info.component';
import { PatientDetailsComponent } from './patient-information/patient-details/patient-details.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';
import {MatDividerModule} from '@angular/material/divider';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import { AllergyInfoComponent } from './patient-information/allergy-info/allergy-info.component';
import {MatTableModule} from '@angular/material/table';
import { NurseComponent } from './nurse/nurse.component';

import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    ChangePasswordComponent,
    NotesComponent,
    NotesCardComponent,
    HomePageComponent,
    RegistrationPageHospitalUserComponent,
    RegistrationPagePatientUserComponent,
    LogoutComponent,
    PatientComponent,
    LeftPaneComponent,
    AdminComponent,
    DoctorComponent,
    ScheduleComponent,
    ForgotPasswordComponent,
    DemographicInfoComponent,
    PatientDetailsComponent,
    AllergyInfoComponent,
    NurseComponent,
  ],
  imports: [
    BrowserModule,
    AlertModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot([]),
    RouterModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    MatTabsModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSlideToggleModule,
    MatRadioModule,
    MatDividerModule,
    MatDatepickerModule,
    MatIconModule,
    MatNativeDateModule,
    MatSelectModule,
    MatPaginatorModule,
    MatTableModule
  ],
  providers: [RestapiService],
  bootstrap: [AppComponent],
})
export class AppModule {}
