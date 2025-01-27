import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  getRoleFromToken(token: string): string | null {
    if (token) {
      try {
        const payload = JSON.parse(atob(token.split('.')[1]));
        return payload.role || null;
      } catch (error) {
        return null;
      }
    }
    return null
  }

  getCurrentUserRole(): string | null {
    const token = sessionStorage.getItem('token');
    if(token) {
      return this.getRoleFromToken(token)
    }
    return null;
  }

}
