package unisa.esbetstart.event.add;

public class AddEventJsonPayloads {

    public static final String VALID_ADD_EVENT_REQUEST = """
                {
                    "competitionId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
                    "name": "Final Match",
                    "date": "2025-12-31T18:00:00",
                    "odds": [
                        {
                            "name": "Win",
                            "value": 1.5
                        },
                        {
                            "name": "Lose",
                            "value": 2.5
                        }
                    ]
                }
            """;

    public static final String NULL_COMPETITION_ID_REQUEST = """
                {
                    "competitionId": null,
                    "name": "Final Match",
                    "date": "2025-12-31T18:00:00",
                    "odds": [
                        {
                            "name": "Win",
                            "value": 1.5
                        },
                         {
                            "name": "Lose",
                            "value": 2.5
                        }
                    ]
                }
            """;

    public static final String INVALID_COMPETITION_ID_REQUEST = """
                {
                    "competitionId": "invalid",
                    "name": "Final Match",
                    "date": "2025-12-31T18:00:00",
                    "odds": [
                        {
                            "name": "Win",
                            "value": 1.5
                        },
                        {
                            "name": "Lose",
                            "value": 2.5
                        }
                    ]
                }
            """;

    public static final String WRONG_COMPETITION_ID_REQUEST = """
                {
                    "competitionId": "f47ac10b-58cc-4372-a567-0e02b2c3d478",
                    "name": "Final Match",
                    "date": "2025-12-31T18:00:00",
                    "odds": [
                        {
                            "name": "Win",
                            "value": 1.5
                        },
                        {
                            "name": "Lose",
                            "value": 2.5
                        }
                    ]
                }
            """;

    public static final String NULL_NAME_REQUEST = """
                {
                    "competitionId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
                    "name": null,
                    "date": "2025-12-31T18:00:00",
                    "odds": [
                        {
                            "name": "Win",
                            "value": 1.5
                        },
                        {
                            "name": "Lose",
                            "value": 2.5
                        }
                    ]
                }
            """;

    public static final String INVALID_DATE_REQUEST = """
                {
                    "competitionId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
                    "name": "Final Match",
                    "date": "2020-01-01T18:00:00",
                   "odds": [
                        {
                            "name": "Win",
                            "value": 1.5
                        },
                        {
                            "name": "Lose",
                            "value": 2.5
                        }
                    ]
                }
            """;

    public static final String NULL_ODD_NAME_REQUEST = """
                {
                    "competitionId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
                    "name": "Final Match",
                    "date": "2025-12-31T18:00:00",
                    "odds": [
                        {
                            "name": null,
                            "value": 2
                        },
                        {
                            "name": "Lose",
                            "value": 2.5
                        }
                    ]
                }
            """;

    public static final String NEGATIVE_ODD_VALUE_REQUEST = """
                {
                    "competitionId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
                    "name": "Final Match",
                    "date": "2025-12-31T18:00:00",
                    "odds": [
                        {
                            "name": "Win",
                            "value": -1
                        },
                        {
                            "name": "Lose",
                            "value": 2.5
                        }
                    ]
                }
            """;

    public static final String ODD_SIZE_MISMATCH_REQUEST = """
                {
                    "competitionId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
                    "name": "Final Match",
                    "date": "2025-12-31T18:00:00",
                    "odds": [
                        {
                            "name": "Win",
                            "value": 1.5
                        }
                    ]
                }
            """;

    public static final String ODD_NAME_MISMATCH_REQUEST = """
                {
                    "competitionId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
                    "name": "Final Match",
                    "date": "2025-12-31T18:00:00",
                    "odds": [
                        {
                            "name": "Win",
                            "value": 1.5
                        },
                        {
                            "name": "Draw",
                            "value": 2.5
                        }
                    ]
                }
            """;

    public static final String DUPLICATE_ODD_NAME_REQUEST = """
                {
                    "competitionId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
                    "name": "Final Match",
                    "date": "2025-12-31T18:00:00",
                    "odds": [
                        {
                            "name": "Win",
                            "value": 1.5
                        },
                        {
                            "name": "Win",
                            "value": 2.5
                        }
                    ]
                }
            """;
}
