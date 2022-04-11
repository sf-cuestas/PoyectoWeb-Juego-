import { Component, OnInit } from '@angular/core';
import { Item } from '../model/item';
import { Player } from '../model/player';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  actualPlayer: Player | undefined;
  pruebaItem: Item = new Item(1,"DSF","dsfasdf",324,0,"sdfs","dsfdsdf",[],[]);

  constructor() { }

  ngOnInit(): void {
    this.actualPlayer = JSON.parse(sessionStorage.getItem("actualPlayer")!);
    this.actualPlayer?.backpack.push(this.pruebaItem);
  }

}
