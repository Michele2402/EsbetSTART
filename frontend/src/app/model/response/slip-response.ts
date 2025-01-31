import {SlipOddResponse} from "./slip-odd-response";

export interface SlipResponse {
  id: string;
  amount: number;
  odds: SlipOddResponse[]
}
