package az.task.parceldelivery.security.filter;

import az.task.parceldelivery.model.security.UserrDetails;
import az.task.parceldelivery.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Get Arrays (https://www.getarrays.io/)
 * @version 1.0
 * @since 7/10/2021
 */
@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private static final String jwtSecret = "c3VwZXJzZWNyZXRqd3QK";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username is: {}", username);
        log.info("Password is: {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        UserrDetails user = (UserrDetails) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        var userId = userService.getUser(user.getUsername()).getId();

        String access_token = JWT.create()
                .withSubject(userId.toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        response.setHeader("token", access_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
