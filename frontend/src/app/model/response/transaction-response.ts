import {TransactionType} from "../enum/transaction-type";

export interface TransactionResponse{

        id:string;
        amount:number;
        date:string;
        gambler: string;
        type:TransactionType[];

}
