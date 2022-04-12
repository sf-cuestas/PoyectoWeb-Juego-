import { Component, OnInit } from '@angular/core';
import { Item } from '../model/item';
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
  pruebaItem: Item = new Item(-100,"DSF","dsfasdf",324,0,"sdfs","dsfdsdf",[],[]);
  actualRoom: Room | undefined;

  constructor(private sessionService:SessionService) { }

  ngOnInit(): void {
    this.actualPlayer = JSON.parse(sessionStorage.getItem("actualPlayer")!);
    this.actualPlayer?.backpack.push(this.pruebaItem);
    this.actualRoom = new Room(892743,"habitacion prueba","prueba",[this.pruebaItem],[],undefined,[],[this.actualPlayer as Player]);
    console.log(this.actualPlayer);
    console.log(this.actualRoom);
    
  }
  throwItem(item: Item): void {
    console.log(item);
    this.sessionService.throwPlayerItem(this.actualPlayer as Player, item).subscribe((player) => {
      this.actualPlayer = player;
      sessionStorage.setItem("actualPlayer",JSON.stringify(player));
      this.actualRoom?.items.push(item);
    });
  }

  pickUpItem(item: Item): void {
    console.log(item);
  }

}
