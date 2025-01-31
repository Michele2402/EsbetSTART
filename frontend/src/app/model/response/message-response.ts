import {Sender} from "../enum/sender";

export interface MessageResponse{

  id: string;
  text:string;
  date:string;
  sender:Sender[];
  read:boolean;
}
