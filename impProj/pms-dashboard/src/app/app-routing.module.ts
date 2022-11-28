import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard } from './auth.guard';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { DoctorComponent } from './doctor/doctor.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LeftPaneComponent } from './left-pane/left-pane.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';

import { NotesComponent } from './notes/notes.component';
import { NurseComponent } from './nurse/nurse.component';
import { PatientDetailsComponent } from './patient-information/patient-details/patient-details.component';
import { PatientComponent } from './patient/patient.component';
import { RegistrationPageHospitalUserComponent } from './registration-page-hospital-user/registration-page-hospital-user.component';
import { RegistrationPagePatientUserComponent } from './registration-page-patient-user/registration-page-patient-user.component';
import { ScheduleComponent } from './schedule/schedule.component';

const routes: Routes = [
  {
    path: '', component: LoginComponent,
  },
  {
    path: 'login', component: LoginComponent,
  },
  {
    path:'forgotPassword', component:ForgotPasswordComponent,
  },

  {
    path:'changePassword', component:ChangePasswordComponent,
  },

  {
    path: 'signup', component: RegistrationPagePatientUserComponent,
    // path:'signup', component:NewLoginComponent,
  },
  {
    path: 'home', component: HomePageComponent, canActivate: [AuthGuard]
  },
  {
    path: 'logout', component: LogoutComponent,
  },
  {
    path: 'patient', component: PatientComponent,
  },
  {
    path: 'admin', component: AdminComponent
  },
  {
    path: 'doctor', component: DoctorComponent
  },
  {
    path: 'schedule', component: ScheduleComponent
  },
  {
    path: 'patient-info', component: PatientDetailsComponent
  },
  {path:'notes', component:NotesComponent},
  {path:'nurse', component:NurseComponent},
  {path:'hospitalUserRegistration', component:RegistrationPageHospitalUserComponent}

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
