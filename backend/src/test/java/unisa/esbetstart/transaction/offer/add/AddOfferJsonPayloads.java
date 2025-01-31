package unisa.esbetstart.transaction.offer.add;

public class AddOfferJsonPayloads {

    public static final String VALID_ADD_OFFER_REQUEST = """
        {
            "name": "Special Offer",
            "description": "Get 50% off on your first bet!",
            "expirationDate": "2023-12-31T23:59:59",
            "goal": 100,
            "type": "DEPOSIT",
            "price": 50.0
        }
    """;

    public static final String INVALID_NAME_REQUEST = """
        {
            "name": null,
            "description": "Get 50% off on your first bet!",
            "expirationDate": "2023-12-31T23:59:59",
            "goal": 100,
            "type": "DEPOSIT",
            "price": 50.0
        }
    """;

    public static final String INVALID_DESCRIPTION_REQUEST = """
        {
            "name": "Special Offer",
            "description": null,
            "expirationDate": "2023-12-31T23:59:59",
            "goal": 100,
            "type": "DEPOSIT",
            "price": 50.0
        }
    """;

    public static final String INVALID_GOAL_REQUEST = """
        {
            "name": "Special Offer",
            "description": "Get 50% off on your first bet!",
            "expirationDate": "2023-12-31T23:59:59",
            "goal": -1,
            "type": "DEPOSIT",
            "price": 50.0
        }
    """;

    public static final String INVALID_PRICE_REQUEST = """
        {
            "name": "Special Offer",
            "description": "Get 50% off on your first bet!",
            "expirationDate": "2023-12-31T23:59:59",
            "goal": 100,
            "type": "DEPOSIT",
            "price": -1.0
        }
    """;

    public static final String INVALID_EXPIRATION_DATE_REQUEST = """
        {
            "name": "Special Offer",
            "description": "Get 50% off on your first bet!",
            "expirationDate": "invalid-date",
            "goal": 100,
            "type": "DEPOSIT",
            "price": 50.0
        }
    """;

    public static final String INVALID_OFFER_TYPE_REQUEST = """
        {
            "name": "Special Offer",
            "description": "Get 50% off on your first bet!",
            "expirationDate": "2023-12-31T23:59:59",
            "goal": 100,
            "type": "INVALID_TYPE",
            "price": 50.0
        }
    """;
}