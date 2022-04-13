import { Component, OnInit } from '@angular/core';
import { Item } from '../model/item';
import { Monster } from '../model/monster';
import { Player } from '../model/player';
import { Room } from '../model/room';
import { SessionService } from '../shared/session-service.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  actualPlayer: Player | undefined;

  constructor(private sessionService:SessionService) { }

  ngOnInit(): void {
    this.actualPlayer = JSON.parse(sessionStorage.getItem("actualPlayer")!);
    console.log(this.actualPlayer);

  }
  throwItem(item: Item): void {
    this.sessionService.throwPlayerItem(this.actualPlayer as Player, item).subscribe((player) => {
      this.actualPlayer = player;
      sessionStorage.setItem("actualPlayer",JSON.stringify(player));
    });
  }

  pickUpItem(item: Item): void {
    this.sessionService.pickUpItemFromRoom(this.actualPlayer as Player, item).subscribe((player) => {
      this.actualPlayer = player;
      sessionStorage.setItem("actualPlayer",JSON.stringify(player));
    });
  }

  changeRoom(exit: Room): void {
    //TODO: 
    console.log(exit);
  }

  attackMonster(monster : Monster | undefined): void {
    //TODO
    console.log(monster);

  }

}
