import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Item } from '../model/item';
import { Player } from '../model/player';

@Component({
  selector: 'app-end',
  templateUrl: './end.component.html',
  styleUrls: ['./end.component.css']
})
export class EndComponent implements OnInit {
  actualPlayer: Player | undefined;
  finalScore: number = 0;
  constructor(private router:Router) { }

  ngOnInit(): void {
    this.actualPlayer = JSON.parse(sessionStorage.getItem("actualPlayer")!);
    for( let itemSelect of this.actualPlayer?.backpack as Item[]){
      this.finalScore = this.finalScore + itemSelect.cost;
    }
  }

  restartGame(): void {
    this.router.navigate(['home']);
  }

}
