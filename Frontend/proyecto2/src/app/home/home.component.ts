import { Component, OnInit } from '@angular/core';
import { Player } from '../model/player';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isPlayer:boolean = false;
  isAdmin:boolean = true;
  isDesigner:boolean = true;
  actualPlayer: Player | undefined;


  constructor() { }

  ngOnInit(): void {
    this.actualPlayer = JSON.parse(sessionStorage.getItem("actualPlayer")!)
    console.log("se paso el jugador", this.actualPlayer?.username);
  }
  startGame(): void{
    this.isAdmin = !this.isAdmin;

  }

}
