import { Room } from "./room";

export class Exit {
    constructor(

      public id : number,
      public before : Room,
      public after: Room,

    ){}
}
