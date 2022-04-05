import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from '../model/player';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json"
    })
  };

  constructor(private http: HttpClient) { }

  logIn(userName: String, userPassword: string ): Observable<Player> {
    return this.http.get<Player>("http://localhost:8080/api/player/" + userName);
  }

}
