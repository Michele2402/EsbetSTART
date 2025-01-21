package unisa.esbetstart.user.registration;

public class UserRegistrationJsonPayloads {

    // Valid request
    public static final String VALID_REGISTER_REQUEST = """
        {
            "name": "John",
            "surname": "Doe",
            "email": "john.doe@example.com",
            "username": "john_doe",
            "password": "Password123!"
        }
        """;

    // Null name
    public static final String NULL_NAME_REQUEST = """
        {
            "name": null,
            "surname": "Doe",
            "email": "john.doe@example.com",
            "username": "john_doe",
            "password": "Password123!"
        }
        """;

    // Null surname
    public static final String NULL_SURNAME_REQUEST = """
        {
            "name": "John",
            "surname": null,
            "email": "john.doe@example.com",
            "username": "john_doe",
            "password": "Password123!"
        }
        """;

    // Null email
    public static final String NULL_EMAIL_REQUEST = """
        {
            "name": "John",
            "surname": "Doe",
            "email": null,
            "username": "john_doe",
            "password": "Password123!"
        }
        """;

    // Null username
    public static final String NULL_USERNAME_REQUEST = """
        {
            "name": "John",
            "surname": "Doe",
            "email": "john.doe@example.com",
            "username": null,
            "password": "Password123!"
        }
        """;

    // Null password
    public static final String NULL_PASSWORD_REQUEST = """
        {
            "name": "John",
            "surname": "Doe",
            "email": "john.doe@example.com",
            "username": "john_doe",
            "password": null
        }
        """;

    // Username longer than 30 characters
    public static final String USERNAME_TOO_LONG_REQUEST = """
        {
            "name": "John",
            "surname": "Doe",
            "email": "john.doe@example.com",
            "username": "john_doe_with_a_really_long_username",
            "password": "Password123!"
        }
        """;

    // Invalid password (does not match regex)
    public static final String INVALID_PASSWORD_REQUEST = """
        {
            "name": "John",
            "surname": "Doe",
            "email": "john.doe@example.com",
            "username": "john_doe",
            "password": "password123"
        }
        """;

    // Invalid email format
    public static final String INVALID_EMAIL_REQUEST = """
        {
            "name": "John",
            "surname": "Doe",
            "email": "john.doe.invalid",
            "username": "john_doe",
            "password": "Password123!"
        }
        """;
}
