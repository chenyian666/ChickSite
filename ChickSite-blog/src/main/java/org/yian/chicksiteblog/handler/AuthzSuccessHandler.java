package org.yian.chicksiteblog.handler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.yian.common.enums.UserRoleEnum;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
public class AuthzSuccessHandler implements AuthenticationSuccessHandler {
    private String targetUrl;
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public AuthzSuccessHandler(String targetUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(targetUrl), "targetUrl must start with '/' or with 'http(s)'");
        this.targetUrl = targetUrl;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // todo 权限验证成功后处理逻辑
        String remoteUser = request.getRemoteUser();
        String remoteAddr = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        String localName = request.getLocalName();
        Locale locale = request.getLocale();
        Enumeration<Locale> locales = request.getLocales();
        System.out.println("remoteUser:" + remoteUser);
        System.out.println("remoteAddr:" + remoteAddr);
        System.out.println("remoteHost:" + remoteHost);
        System.out.println("localName:" + localName);
        System.out.println("locale:" + locale);
        while (locales.hasMoreElements()) {
            System.out.println("locales:" + locales.nextElement());
        }


        SavedRequest savedRequest = this.requestCache.getRequest(request, response);
        if (savedRequest == null) {
            // 未找到目标 URL，重定向到默认 URL
            targetUrl = "https://bing.com";
        }else {
            // 重定向到之前的目标 URL
            targetUrl = savedRequest.getRedirectUrl();
        }
        // 去除尾部的?continue
//        if (targetUrl.contains("?continue")) {
//            targetUrl = targetUrl.substring(0, targetUrl.indexOf("?continue"));
//        }
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
        response.sendRedirect(targetUrl);
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return this.redirectStrategy;
    }
}
