package com.tuplv.security;

import com.tuplv.util.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        String url = "";

        // Nếu role là ADMIN thì redirect sang controller: /admin/trang-chu
        // Nếu role là USER thì redirect sang controller: /trang-chu
        List<String> roles = SecurityUtils.getAuthorities();
        if (isAdmin(roles)) {
            url = "/admin/trang-chu";
        } else if ((isUser(roles))) {
            url = "/trang-chu";
        }
        return url;
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ADMIN")) {
            return true;
        }
        return false;
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("USER")) {
            return true;
        }
        return false;
    }

    @Override
    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
