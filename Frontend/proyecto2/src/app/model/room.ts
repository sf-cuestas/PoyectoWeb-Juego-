import { Decorative } from "./decorative";
import { Exit } from "./exit";
import { Item } from "./item";
import { Monster } from "./monster";
import { Player } from "./player";

export class Room {
    constructor(

      public id : number,
      public name : string,
      public description : string,
      public items : Item[],
      public decoratives : Decorative[],
      public monster : Monster | undefined,
      public exits : Exit[],
      public players : Player[],

    ){}
}
