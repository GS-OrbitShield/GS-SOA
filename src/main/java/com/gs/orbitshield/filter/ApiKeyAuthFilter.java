package com.gs.orbitshield.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.gs.orbitshield.context.ApiKeyContext;
import com.gs.orbitshield.dto.response.ErrorResponse;
import com.gs.orbitshield.exception.UnauthorizedException;
import com.gs.orbitshield.model.ApiKey;
import com.gs.orbitshield.repository.ApiKeyRepository;
import com.gs.orbitshield.util.HashUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(ApiKeyAuthFilter.class);
    private final ApiKeyRepository apiKeyRepository;
    private final ObjectMapper objectMapper;

    public ApiKeyAuthFilter(ApiKeyRepository apiKeyRepository, ObjectMapper objectMapper) {
        this.apiKeyRepository = apiKeyRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestPath = request.getRequestURI();
        logger.debug("Processing request for path: {}", requestPath);

        // Exclude paths that don't require authentication
        if (isExcludedPath(requestPath)) {
            logger.debug("Path {} is excluded from authentication", requestPath);
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String apiKey = extractApiKey(request);

            if (apiKey == null) {
                logger.warn("Missing API Key for path: {}", requestPath);
                throw new UnauthorizedException("API Key is required.");
            }

            // Hash the API key and validate
            String apiKeyHash = HashUtil.sha256(apiKey);
            ApiKey validApiKey = apiKeyRepository.findByKeyHash(apiKeyHash)
                    .orElseThrow(() -> {
                        logger.warn("Invalid API Key provided for path: {}", requestPath);
                        return new UnauthorizedException("Invalid or inactive API Key.");
                    });

            if (!validApiKey.getActive()) {
                logger.warn("Inactive API Key used for path: {}", requestPath);
                throw new UnauthorizedException("Invalid or inactive API Key.");
            }

            // Store in context
            ApiKeyContext.set(validApiKey);

            filterChain.doFilter(request, response);
        } catch (UnauthorizedException ex) {
            ApiKeyContext.clear();
            sendErrorResponse(response, "UNAUTHORIZED", ex.getMessage(), request.getRequestURI());
        } catch (Exception ex) {
            logger.error("Unexpected error in ApiKeyAuthFilter for path: {}", requestPath, ex);
            ApiKeyContext.clear();
            sendErrorResponse(response, "INTERNAL_ERROR", "An unexpected error occurred.", request.getRequestURI());
        } finally {
            ApiKeyContext.clear();
        }
    }

    private String extractApiKey(HttpServletRequest request) {
        String headerValue = request.getHeader("X-API-Key");
        if (headerValue != null && !headerValue.isEmpty()) {
            return headerValue;
        }
        return null;
    }

    private boolean isExcludedPath(String path) {
        return path.startsWith("/health") ||
               path.startsWith("/swagger-ui") ||
               path.startsWith("/v3/api-docs") ||
               path.startsWith("/swagger-resources") ||
               path.startsWith("/webjars") ||
               path.equals("/favicon.ico");
    }

    private void sendErrorResponse(HttpServletResponse response, String code, String message, String path)
            throws IOException {
        response.setStatus(code.equals("INTERNAL_ERROR") ? HttpServletResponse.SC_INTERNAL_SERVER_ERROR : HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        ErrorResponse errorResponse = ErrorResponse.of(code, message, path);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}

