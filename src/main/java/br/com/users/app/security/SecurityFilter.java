package br.com.users.app.security;

import br.com.users.app.exceptions.AuthorizationHeaderException;
import br.com.users.app.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    private static final List<String> EXCLUDE_URIS = List.of(
            "/swagger",
            "/v3/api-docs",
            "/swagger-ui/index.html",
            "/adm/create",
            "/adm/login"
    );

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException, AuthenticationException {
        if (shouldExcludeUri(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = this.recoverToken(request);

        if (token != null) {
            String userId = tokenService.validateToken(token);

            if (userId != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userId, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean shouldExcludeUri(String uri) {
        return EXCLUDE_URIS.stream().anyMatch(uri::startsWith);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("x-auth");
        if (authHeader == null || authHeader.isBlank()) {
            throw new AuthorizationHeaderException();
        }
        return authHeader;
    }
}