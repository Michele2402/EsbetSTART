import {OddStaticResponse} from "./odd-static-response";

export interface BetPlacedResponse{

  amount: number;
  date:string;
  result:string;
  oddStatic: OddStaticResponse[];

}
