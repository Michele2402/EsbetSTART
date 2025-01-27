
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
    path: environmentPaths.e_sports,
    loadChildren: () => import('./features/esports-page/esports-page.module').then(m => m.EsportsPageModule)
  },
  {
    path: environmentPaths.e_sports_admin_page,
    loadChildren: () => import('./features/esports-admin-page/esports-admin-page.module').then(m => m.EsportsAdminPageModule)
  },
  {
    path: environmentPaths.promotion_page,
    loadChildren: () => import('./features/promotion-page/promotion-page.module').then(m => m.PromotionPageModule)
  },
  {
    path: environmentPaths.profile_page,
    loadChildren: () => import('./features/profile-page/profile-page.module').then(m => m.ProfilePageModule)
  },
  {
    path: environmentPaths.bets_page,
    loadChildren: () => import('./features/bets-page/bets-page.module').then(m => m.BetsPageModule)
  },
  {
    path: environmentPaths.message_page,
    loadChildren: () => import('./features/message-page/message-page.module').then(m => m.MessagePageModule)
  },
  {
    path: environmentPaths.bank_account_page,
    loadChildren: () => import('./features/bank-account-page/bank-account-page.module').then(m => m.BankAccountPageModule)
  },
  {
    path: environmentPaths.customer_service_ticket_page,
    loadChildren: () => import('./features/home-page/components/customer-service-ticket/customer-service-ticket.module').then(m => m.CustomerServiceTicketModule)
  },
  {
    path: environmentPaths.competitions_admin_page,
    loadChildren: () => import('./features/competitions-admin-page/competitions-admin-page.module').then(m => m.CompetitionsAdminPageModule)
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
