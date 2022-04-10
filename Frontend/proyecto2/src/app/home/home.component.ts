import { Component, OnInit } from '@angular/core';
import { Player } from '../model/player';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isPlayer:boolean = true;
  isAdmin:boolean = true;
  isDesigner:boolean = true;
  actualPlayer: Player | undefined;


  constructor() { }

  ngOnInit(): void {
    this.actualPlayer = JSON.parse(sessionStorage.getItem("actualPlayer")!)
    console.log("se paso el jugador", this.actualPlayer?.username);
    if(this.actualPlayer?.role == 'PLAYER'){
      this.isPlayer = false;
    }else if (this.actualPlayer?.role == 'DESIGNER') {
      this.isDesigner = false;
    } else {
      this.isAdmin = false;
    }
  }
  startGame(): void{

  }

}
