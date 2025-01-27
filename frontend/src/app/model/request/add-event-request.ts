import {AddOddRequest} from "./add-odd-request";

export interface AddEventRequest {
  competitionId: string
  name: string;
  date: string;
  odds: AddOddRequest[];
}
