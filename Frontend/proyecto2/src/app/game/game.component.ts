import { Component, OnInit } from '@angular/core';
import { Exit } from '../model/exit';
import { Item } from '../model/item';
import { Monster } from '../model/monster';
import { MonstersEsp } from '../model/monstersEsp';
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
  isMonsterAlive: Boolean = true;
  sum: number = 0;
  numAux: number = 0;

  constructor(private sessionService:SessionService) { }

  ngOnInit(): void {
    this.actualPlayer = JSON.parse(sessionStorage.getItem("actualPlayer")!);
    console.log(this.actualPlayer);
    if (this.actualPlayer?.room.monster == undefined || this.actualPlayer.room.monster.hp <= 0){
      this.isMonsterAlive = false;
    }
    if (this.actualPlayer?.hp as number <= 0 || this.actualPlayer?.clock as number >= 20){
    }

  }
  throwItem(item: Item): void {
    this.sessionService.throwPlayerItem(this.actualPlayer as Player, item).subscribe((player) => {
      this.actualPlayer = player;
      sessionStorage.setItem("actualPlayer",JSON.stringify(player));
    });
  }

  pickUpItem(item: Item): void {
    if (this.actualPlayer?.weight as number > this.sumWeightBackpack() + item.weight){
      this.sessionService.pickUpItemFromRoom(this.actualPlayer as Player, item).subscribe((player) => {
        this.actualPlayer = player;
        sessionStorage.setItem("actualPlayer",JSON.stringify(player));
      });
    }else{
      console.log("You can not pick up this item, it is too heavy to carry" , this.sumWeightBackpack());
    }
    
  }
  sumWeightBackpack(): number{
    this.sum = 0;
    for( let itemSelect of this.actualPlayer?.backpack as Item[]){
      this.sum = this.sum + itemSelect.weight;
    }
    return this.sum;
  }

  changeRoom(exit: Exit): void {
    //TODO: 
    this.sessionService.nextRoom(exit).subscribe((room)=>{
      console.log(room);
      this.sessionService.changeRoom(this.actualPlayer as Player, room).subscribe((player) =>{
        this.actualPlayer = player;
        sessionStorage.setItem("actualPlayer",JSON.stringify(player));
        window.location.reload();
      });
    });

  }

  escapeRoom(exit: Exit): void {
    this.attackPlayerByMonster();
    this.changeRoom(exit);
  }


  attackMonster(monster : Monster | undefined): void {
    //TODO
    console.log(monster);
    this.sessionService.getMonsterType(this.actualPlayer?.room.monster!).subscribe((espec)=>{
      this.numAux = this.random(0,this.actualPlayer?.attack_level as number +200 ) - this.random(0,-1 * espec.defence_slash);
      this.sessionService.attackMonsterByPlayer(this.actualPlayer as Player, this.actualPlayer?.room.monster?.hp as number - this.numAux).subscribe((player)=>{
        this.actualPlayer = player;
        sessionStorage.setItem("actualPlayer",JSON.stringify(player));
        if(this.actualPlayer.room.monster?.hp as number < 0 ){
          this.isMonsterAlive = !this.isMonsterAlive
        }
      });
    }); 
  }


  attackPlayerByMonster(): number{
    window.location.reload();
    return this.random(0, this.actualPlayer?.room.monster?.type.attack_level as number) - this.random(0, (this.actualPlayer?.defence_slash as number));
  }

  random(min: number, max: number) { 
    return Math.floor(Math.random() * (max - min + 1) + min)
  }
/*
falta 
- monstruo ataca jugador despues de que el jugador lo ataca
- monstruo ataca jugador antes de huir
- contador de tiempo
- bitacora
- que no se conecte la habitacion a si misma 
- mostrar jugadores de la habitacion
*/
}
