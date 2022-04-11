import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { SessionService } from '../shared/session-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userName: string = "";
  userPassword: string = "";

  constructor(private router:Router, private sessionService:SessionService ) { }

  ngOnInit(): void {
  }

  logIn(): void {
    console.log('si soy util');
    this.sessionService
      .logIn(this.userName, this.userPassword)
      .subscribe((player) => {
        console.log("Bienvenido");
        console.log(player.username, player.role, player.hp,player.backpack);
        this.router.navigate(['home']);
        sessionStorage.setItem("actualPlayer",JSON.stringify(player));
      });
  }

}
