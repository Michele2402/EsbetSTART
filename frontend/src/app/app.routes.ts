
import { Routes } from '@angular/router';
import {environmentPaths} from "./environments/environment";

export const routes: Routes = [
  {
    path: environmentPaths.home_page,
    loadChildren: () => import('./features/home-page/home-page.module').then(m => m.HomePageModule)
  },
  {
    path: environmentPaths.login_page,
    loadChildren: () => import('./features/login-page/login-page.module').then(m => m.LoginPageModule)
  },
  {
    path: environmentPaths.search_page,
    loadChildren: () => import('./features/search-page/search-page.module').then(m => m.SearchPageModule)
  },
  {
    path: environmentPaths.registration_page,
    loadChildren: () => import('./features/registration-page/registration-page.module').then(m => m.RegistrationPageModule)
  },
  {
    path: environmentPaths.e_sports_admin_page,
    loadChildren: () => import('./features/esports-admin-page/esports-admin-page.module').then(m => m.EsportsAdminPageModule)},
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
