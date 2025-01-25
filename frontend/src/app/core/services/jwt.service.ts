import { Injectable } from '@angular/core';
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  getRoleFromToken(token: string): string | null {
    if (token) {
      try {
        const payload = JSON.parse(atob(token.split('.')[1]));
        console.log(payload)
        return payload.role || null;
      } catch (error) {
        return null;
      }
    }
    return null
  }


}
