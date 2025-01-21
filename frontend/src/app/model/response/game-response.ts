import {RuleResponse} from "./rule-response";

export interface GameWithRulesResponse {
  id: string,
  name: string;
  rules: RuleResponse[];
}
