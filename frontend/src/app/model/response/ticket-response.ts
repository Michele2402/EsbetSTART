import {MessageResponse} from "./message-response";
import {Status} from "../enum/status";

export interface TicketResponse{

  id:string;
  category:string;
  status:Status[];
  assignedOperator:string;
  openedBy:string;
  messages:MessageResponse[];

}
