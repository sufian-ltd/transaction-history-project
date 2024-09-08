import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AUTH, BASE_URL } from '../core/constant';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private apiURL = BASE_URL + AUTH;
  private tokenKey = 'authToken';

  constructor(private http: HttpClient, private router: Router) {}

  signIn(credentials: { username: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiURL}/sign-in`, credentials);
  }

  signUp(user: { username: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiURL}/sign-up`, user);
  }

  saveToken(token: string) {
    localStorage.setItem(this.tokenKey, token);
  }

  getToken() {
    return localStorage.getItem(this.tokenKey);
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  logout() {
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(['/signin']);
  }
}
