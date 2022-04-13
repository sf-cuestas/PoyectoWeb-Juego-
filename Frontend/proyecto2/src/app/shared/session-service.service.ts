import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from '../model/player';
import { Item } from '../model/item';
import { Room } from '../model/room';

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
  throwPlayerItem(user: Player, item: Item): Observable<Player> {
    return this.http.get<Player>("http://localhost:8080/api/player/" + user.id +"/" + item.id);
  }

  fetchActualPlayerRoom(user: Player): Observable<Room>{
    return this.http.get<Room>("http://localhost:8080/api/room/" + user.id);
  }

  pickUpItemFromRoom(user: Player, item: Item): Observable<Player>{
    return this.http.get<Player>("http://localhost:8080/api/player/pickup/" + user.id + "/" + item.id);
  }

}
