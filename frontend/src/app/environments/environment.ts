export const environmentPaths = {

  //BASE
  base_ui_path: 'http://localhost:4200/',

  //PAGES ROUTES
  home_page: 'homepage',
  e_sports: 'esports',
  login_page: 'login',
  registration_page: 'registration',
  e_sports_admin_page: 'esports-admin',
  competitions_admin_page: 'competitions-admin',
  events_admin_page: 'events-admin',
  search_page: 'search',
  promotion_page: 'promotion',
  profile_page: 'profile',
  bets_page: 'bets',
  message_page: 'message',
  bank_account_page: 'bank-account',
  customer_service_ticket_page: 'customer-service-ticket',
  competition_page: 'competition',
  event_page: 'event',
  current_bets_page: 'current-bets',
  bets_concluded_page: 'bets-concluded',
  recharge_page: 'recharge',
  withdraw_page: 'withdraw',

  //BACKEND
  base_path: 'http://localhost:8080/',

  //API ENDPOINTS
  get_all_games: 'games/get-all',
  add_game: 'games/add',
  remove_game: 'games/remove',
  update_game: 'games/update',

  get_all_competitions: 'competitions/get-all-by-game',
  add_competition: 'competitions/add',
  remove_competition: 'competitions/remove',
  update_competition: 'competitions/update',

  get_all_events: 'events/get-all-by-competition',
  add_event: 'events/add',
  update_event: 'events/update',
  update_odd: 'events/odds/update',
  end_event: 'events/end',

  register: 'users/register',
  login: 'users/login',
  get: 'users/',
  update: 'users/update',
  balance: 'users/balance',
  transactions: 'users/transaction/create',

  accept_offer: 'offers/accept',
  get_all_offer: 'offers/get-all',
  get_activated_offer: 'offers/get-activated-by',

  get_slip: 'slip/get-slip',
  save_slip: 'slip/save',
  place_bet: 'slip/place-bet',

  show_transaction:'transactions/show',
  show_all_transaction:'transactions/showAll',
  show_bets:'bets/show',

  open_ticket:'tickets/open',
  accept_ticket:'tickets/accept',
  send_ticket:'tickets/sendMessage',

}
