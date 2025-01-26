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

  //BACKEND
  base_path: 'http://79.49.37.224:12312/',

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

  register: 'users/register',
  login: 'users/login',
}
