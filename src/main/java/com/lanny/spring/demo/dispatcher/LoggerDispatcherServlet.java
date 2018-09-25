package com.lanny.spring.demo.dispatcher;

import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

/**
 * @author Lanny Yao
 * @date 9/19/2018 4:50 PM
 */
@Slf4j
public class LoggerDispatcherServlet extends DispatcherServlet {

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }

        try {
            super.doDispatch(request, response);
        } finally {
            if (!request.getRequestURI().contains("health")) {
                logRequest(request);
                logResponse(response);
                updateResponse(response);
            }
        }
    }

    private void logRequest(HttpServletRequest request) {
        try {
            log.info("HTTP request method: {}", request.getMethod());
            log.info("HTTP request URI: {}", request.getRequestURI());
            log.info("HTTP request headers: {}", Collections.list(request.getHeaderNames())
                    .stream()
                    .filter(headerName -> !"authorization".equalsIgnoreCase(headerName))
                    .map(headerName -> headerName + "=" + request.getHeader(headerName))
                    .collect(joining(";", "[", "]")));
        } catch (Exception e) {
            log.error("Unable to log HTTP request: ", e);
        }
    }

    private void logResponse(HttpServletResponse response) {
        try {
            log.info("HTTP response status: {}", response.getStatus());
            log.info("HTTP response headers: {}", response.getHeaderNames()
                    .stream()
                    .map(headerName -> headerName + "=" + response.getHeader(headerName))
                    .collect(joining(";", "[", "]")));
        } catch (Exception e) {
            log.error("Unable to log HTTP response: ", e);
        }
    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        responseWrapper.copyBodyToResponse();
    }
}
