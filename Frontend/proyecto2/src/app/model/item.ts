import { Player } from "./player";
import { Room } from "./room";

export class Item {
    constructor(

      public id : number,
      public name : string,
      public last_updated: string,
      public cost: number,
      public weight: number,
      public examine: string,
      public wiki_url: string,
      public rooms: Room[],
      public players: Player[],
    ){}
}
