package unisa.esbetstart.competition.add;

public class AddCompetitionJsonPayloads {

    public static final String VALID_ADD_COMPETITION_REQUEST = """
        {
            "gameId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
            "name": "World Championship",
            "originCountry": "USA"
        }
    """;

    public static final String INVALID_GAME_ID_REQUEST = """
        {
            "gameId": null,
            "name": "World Championship",
            "originCountry": "USA"
        }
    """;

    public static final String INVALID_COMPETITION_NAME_REQUEST = """
        {
            "gameId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
            "name": null,
            "originCountry": "USA"
        }
    """;

    public static final String INVALID_ORIGIN_COUNTRY_REQUEST = """
        {
            "gameId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
            "name": "World Championship",
            "originCountry": null
        }
    """;

    public static final String INVALID_GAME_ID_FORMAT_REQUEST = """
        {
            "gameId": "invalid-uuid",
            "name": "World Championship",
            "originCountry": "USA"
        }
    """;

    public static final String WRONG_GAME_ID = """
        {
            "gameId": "f47ac10b-58cc-4372-a567-0e02b2c3d478",
            "name": "World Championship",
            "originCountry": "USA"
        }
    """;
}
