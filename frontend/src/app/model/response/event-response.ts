import {OddResponse} from "./odd-response";

export interface EventResponse {
  id: string,
  name: string,
  date: string,
  isEnded: boolean,
  odds: OddResponse[]
}
