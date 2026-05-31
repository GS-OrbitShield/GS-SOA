package com.gs.orbitshield.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.orbitshield.model.ApiKey;
import com.gs.orbitshield.repository.ApiKeyRepository;
import com.gs.orbitshield.util.HashUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApiKeyAuthFilterTest {

    @Mock
    private ApiKeyRepository apiKeyRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ApiKeyAuthFilter apiKeyAuthFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    private ApiKey apiKey;

    @BeforeEach
    void setUp() {
        apiKey = new ApiKey();
        apiKey.setId("id1");
        apiKey.setKeyHash(HashUtil.sha256("test-key"));
        apiKey.setActive(true);
    }

    @Test
    void doFilterInternal_WhenExcludedPath_ShouldProceed() throws Exception {
        when(request.getRequestURI()).thenReturn("/health");

        apiKeyAuthFilter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_WhenValidKey_ShouldProceed() throws Exception {
        when(request.getRequestURI()).thenReturn("/api/v1/satellites");
        when(request.getHeader("X-API-Key")).thenReturn("test-key");
        when(apiKeyRepository.findByKeyHash(anyString())).thenReturn(Optional.of(apiKey));

        apiKeyAuthFilter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_WhenMissingKey_ShouldReturnUnauthorized() throws Exception {
        when(request.getRequestURI()).thenReturn("/api/v1/satellites");
        when(request.getHeader("X-API-Key")).thenReturn(null);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        when(objectMapper.writeValueAsString(any())).thenReturn("{}");

        apiKeyAuthFilter.doFilter(request, response, filterChain);

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(filterChain, never()).doFilter(any(), any());
    }

    @Test
    void doFilterInternal_WhenInvalidKey_ShouldReturnUnauthorized() throws Exception {
        when(request.getRequestURI()).thenReturn("/api/v1/satellites");
        when(request.getHeader("X-API-Key")).thenReturn("wrong-key");
        when(apiKeyRepository.findByKeyHash(anyString())).thenReturn(Optional.empty());
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        when(objectMapper.writeValueAsString(any())).thenReturn("{}");

        apiKeyAuthFilter.doFilter(request, response, filterChain);

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
