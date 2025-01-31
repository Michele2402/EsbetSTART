import {AddRuleRequest} from "./add-rule-request";

export interface AddGameRequest {
  name: string;
  rules: AddRuleRequest[];
}
