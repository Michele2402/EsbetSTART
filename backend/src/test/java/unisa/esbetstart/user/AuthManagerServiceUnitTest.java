package unisa.esbetstart.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import unisa.esbetstart.common.exceptions.AuthorizationException;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.usermanagment.application.port.out.CreateUserPortOut;
import unisa.esbetstart.usermanagment.application.port.out.GetUserPortOut;
import unisa.esbetstart.usermanagment.application.service.AuthManagerService;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.domain.model.User;
import unisa.esbetstart.usermanagment.presentation.request.LoginRequest;
import unisa.esbetstart.usermanagment.presentation.request.RegisterRequest;
import unisa.esbetstart.usermanagment.security.CustomUserDetails;
import unisa.esbetstart.usermanagment.security.JwtService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthManagerServiceUnitTest {

    @Mock
    private CheckTypeAttribute checkTypeAttribute;

    @Mock
    private GetUserPortOut getUserPortOut;

    @Mock
    private CreateUserPortOut createUserPortOut;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AuthManagerService authManagerService;

    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest();
        registerRequest.setName("John");
        registerRequest.setSurname("Doe");
        registerRequest.setEmail("john@example.com");
        registerRequest.setPassword("password123");
        registerRequest.setUsername("johndoe");

        loginRequest = new LoginRequest();
        loginRequest.setEmail("john@example.com");
        loginRequest.setPassword("password123");
    }

    @Test
    void register_Success() {
        when(getUserPortOut.getUserByEmail(registerRequest.getEmail())).thenReturn(null);

        assertDoesNotThrow(() -> authManagerService.register(registerRequest));

        verify(getUserPortOut, times(1)).getUserByEmail(registerRequest.getEmail());
        verify(createUserPortOut, times(1)).createUser(any(Gambler.class));
    }

    @Test
    void register_Fails_WhenUserAlreadyExists() {
        when(getUserPortOut.getUserByEmail(registerRequest.getEmail())).thenReturn(new User());

        AuthorizationException exception = assertThrows(AuthorizationException.class,
                () -> authManagerService.register(registerRequest));

        assertEquals("User with email john@example.com already exists", exception.getMessage());
        verify(createUserPortOut, never()).createUser(any(Gambler.class));
    }

    @Test
    void login_Success() {
        CustomUserDetails userDetails = mock(CustomUserDetails.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenAnswer(invocation -> authentication);
        when(authentication.isAuthenticated()).thenAnswer(invocation -> true);
        when(authentication.getPrincipal()).thenAnswer(invocation -> userDetails);
        when(userDetails.getAuthorities()).thenAnswer(invocation ->
                Collections.singletonList((GrantedAuthority) () -> "GAMBLER")
        );
        when(jwtService.generateToken(anyString(), anyString())).thenAnswer(invocation ->
                "jwt_token"
        );

        String token = authManagerService.login(loginRequest);

        assertEquals("jwt_token", token);

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, times(1)).generateToken(loginRequest.getEmail(), "GAMBLER");
    }

    @Test
    void login_Fails_WhenAuthenticationFails() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(false);

        String result = authManagerService.login(loginRequest);

        assertEquals("failure", result);
    }

    @Test
    void register_ThrowsException_WhenRequestIsNull() {
        ObjectIsNullException exception = assertThrows(ObjectIsNullException.class,
                () -> authManagerService.register(null));

        assertEquals("RegisterRequest is null", exception.getMessage());
    }

    @Test
    void login_ThrowsException_WhenRequestIsNull() {
        ObjectIsNullException exception = assertThrows(ObjectIsNullException.class,
                () -> authManagerService.login(null));

        assertEquals("LoginRequest is null", exception.getMessage());
    }
}