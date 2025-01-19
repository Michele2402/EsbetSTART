package unisa.esbetstart.game.add;

public class AddGameJsonPayloads {

    public static final String VALID_ADD_GAME_REQUEST = """
        {
            "name": "LooL",
            "rules": [
                {
                    "name": "Rule1",
                    "position": 1
                },
                {
                    "name": "Rule2",
                    "position": 2
                }
            ]
        }
    """;

    public static final String EMPTY_RULES_REQUEST = """
        {
            "name": "LoL",
            "rules": []
        }
    """;

    public static final String INVALID_GAME_NAME_REQUEST = """
        {
            "name": null,
            "rules": [
                {
                    "name": "Rule1",
                    "position": 1
                }
            ]
        }
    """;

    public static final String INVALID_RULE_NAME_REQUEST = """
        {
            "name": "LoL",
            "rules": [
                {
                    "name": null,
                    "position": 1
                }
            ]
        }
    """;

    public static final String INVALID_RULE_POSITION_REQUEST = """
        {
            "name": "LoL",
            "rules": [
                {
                    "name": "Rule1",
                    "position": -1
                }
            ]
        }
    """;
}
