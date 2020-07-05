import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor() { }

    public setToken(token: string): void {
        sessionStorage.setItem('access_token', token);
    }

    public getToken(): string {
        return sessionStorage.getItem('access_token');
    }
}
