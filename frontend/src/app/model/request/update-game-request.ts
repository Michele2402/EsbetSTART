import {AddRuleRequest} from "./add-rule-request";

export interface  UpdateGameRequest {
  gameId: string
  name: string
  rules: AddRuleRequest[]
}
