import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from '../model/player';
import { Item } from '../model/item';
import { Room } from '../model/room';
import { Exit } from '../model/exit';
import { MonstersEsp } from '../model/monstersEsp';
import { Monster } from '../model/monster';

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

  spawnPlayer(user: Player): Observable<Player> {
    return this.http.get<Player>("http://localhost:8080/api/player/spawn/" + user.id);
  }

  attackMonsterByPlayer(user: Player, attack: number): Observable<Player>{
    return this.http.get<Player>("http://localhost:8080/api/player/attackmonster/" + user.id + "/" + attack);
  }

  nextRoom(exit: Exit): Observable<Room>{
    return this.http.get<Room>("http://localhost:8080/api/exit/nextroom/" + exit.id);
  }

  changeRoom(user: Player, room: Room): Observable<Player>{
    return this.http.get<Player>("http://localhost:8080/api/player/changeroom/" + user.id + "/" + room.id);
  }

  getMonsterType(monster: Monster):Observable<MonstersEsp>{
    return this.http.get<MonstersEsp>("http://localhost:8080/api/monster/type/" + monster.id);
  }

  attackPlayerByMonster(user: Player, attack: number): Observable<Player>{
    return this.http.get<Player>("http://localhost:8080/api/player/attackplayer/" + user.id + "/" + attack);
  }

  getPlayerList(room: Room):Observable<Player[]>{
    return this.http.get<Player[]>("http://localhost:8080/api/room/roomplayers/" + room.id);
  }

}
