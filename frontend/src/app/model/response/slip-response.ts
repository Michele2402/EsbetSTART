import {SlipOddResponse} from "./slip-odd-response";

export interface SlipResponse {
  amount: number;
  odds: SlipOddResponse[]
}
