package unisa.esbetstart.user.login;

public class UserLoginJsonPayloads {

    public static final String VALID_LOGIN_REQUEST = """
            {
                "email": "john.doe@example.com",
                "password": "Password123!"
            }
            """;

    public static final String INVALID_EMAIL_REQUEST = """
            {
                "email": "john.doe@example.com",
                "password": "password123!"
            }
            """;

    public static final String INVALID_PASSWORD_REQUEST = """
            {
                "email": "john.doe@example.com",
                "password": "Password123"
            }
            """;
}
