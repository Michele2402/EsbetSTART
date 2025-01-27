import {Routes} from '@angular/router';
import {environmentPaths} from "./environments/environment";
import {GamblerGuardService} from "./core/guards/gambler-guard.service";
import {EventManagerGuardService} from "./core/guards/event-manager-guard.service";
import {NotLoggedGuardService} from "./core/guards/not-logged-guard.service";

export const routes: Routes = [
  {
    path: environmentPaths.home_page,
    loadChildren: () => import('./features/home-page/home-page.module').then(m => m.HomePageModule)
  },
  {
    path: environmentPaths.login_page,
    loadChildren: () => import('./features/login-page/login-page.module').then(m => m.LoginPageModule),
    canActivate: [NotLoggedGuardService]
  },
  {
    path: environmentPaths.registration_page,
    loadChildren: () => import('./features/registration-page/registration-page.module').then(m => m.RegistrationPageModule),
    canActivate: [NotLoggedGuardService]
  },
  {
    path: environmentPaths.search_page,
    loadChildren: () => import('./features/search-page/search-page.module').then(m => m.SearchPageModule)
  },
  {
    path: environmentPaths.e_sports,
    loadChildren: () => import('./features/esports-page/esports-page.module').then(m => m.EsportsPageModule)
  },
  {
    path: environmentPaths.e_sports_admin_page,
    loadChildren: () => import('./features/esports-admin-page/esports-admin-page.module').then(m => m.EsportsAdminPageModule),
    canActivate: [EventManagerGuardService]
  },
  {
    path: environmentPaths.competitions_admin_page,
    loadChildren: () => import('./features/competitions-admin-page/competitions-admin-page.module').then(m => m.CompetitionsAdminPageModule),
    canActivate: [EventManagerGuardService]
  },
  {
    path: environmentPaths.events_admin_page,
    loadChildren: () => import('./features/events-admin-page/events-admin-page.module').then(m => m.EventsAdminPageModule),
    canActivate: [EventManagerGuardService]
  },
  {
    path: '',
    redirectTo: environmentPaths.home_page,
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: environmentPaths.home_page
  }
];
