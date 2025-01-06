package com.example.videolibrarybe.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Order(0)
public class GlobalRequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest) {
            ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
            // todo -- change order of lines 27 and 28
            filterChain.doFilter(wrappedRequest, servletResponse);
            logRequestDetails(wrappedRequest);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void logRequestDetails(ContentCachingRequestWrapper request) {
        log.info("Incoming Request: [{} {}]", request.getMethod(), request.getRequestURI());

        // Log headers
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headersMap = new HashMap<>();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headersMap.put(headerName, request.getHeader(headerName));
        }
        log.info("Headers: {}", headersMap);

        // Log query parameters
        request.getParameterMap().forEach((key, values) ->
                log.info("Query Parameter: {} = {}", key, String.join(", ", values))
        );

        // Log cached body
        try {
            byte[] content = request.getContentAsByteArray();
            if (content.length > 0) {
                String requestBody = new String(content, request.getCharacterEncoding());
                log.info("Request Body: {}", requestBody);
            } else {
                log.info("Request Body is empty.");
            }
        } catch (UnsupportedEncodingException e) {
            log.error("Error decoding request body", e);
        }

    }
}
