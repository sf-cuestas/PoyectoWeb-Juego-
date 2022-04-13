import { MonstersEsp } from "./monstersEsp";
import { Room } from "./room";

export class Monster {
    constructor(
        public id: number,
        public name: string,
        public hp: number,
        public type: MonstersEsp,
        public room: Room,
    ){}
}
