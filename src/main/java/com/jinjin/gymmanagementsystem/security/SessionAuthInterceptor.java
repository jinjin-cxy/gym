package com.jinjin.gymmanagementsystem.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjin
 * @date 2026/4/26
 */
@Component
public class SessionAuthInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper;

    public SessionAuthInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Boolean isPublicApi(String uri) {
        return uri == null || uri.startsWith("/api/adminLogin")
                || uri.startsWith("/api/userLogin")
                || uri.startsWith("/api/logout");
    }

    public Boolean isMemberApi(String uri) {
        return uri == null || uri.startsWith("/api/member");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String uri = request.getRequestURI();
        if (uri == null || !uri.startsWith("/api")) {
            return true;
        }

        if (isPublicApi(uri)) {
            return true;
        }

        String requiredSessionKey = isMemberApi(uri) ? "member" : "admin";
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(requiredSessionKey) != null) {
            return true;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> resp = new HashMap<>();
        resp.put("success", false);
        resp.put("message", "请先登录");
        response.getWriter().write(objectMapper.writeValueAsString(resp));
        return false;
    }
}
