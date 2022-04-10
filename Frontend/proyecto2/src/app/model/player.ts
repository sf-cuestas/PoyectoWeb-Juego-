import { Item } from "./item";
import { Room } from "./room";

export class Player {
    constructor(

      public id : number,
      public username : string,
      public password : string,
      public hp : number,
      public attack_level : number,
      public defence_slash : number,
      public size : number,
      public weight : number,
      public backpack : Item[],
      public room : Room,
      public clock: number,
      public role: string

    ){}
}
