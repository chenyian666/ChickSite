package org.yian.chicksiteblog.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

public class AuthzSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

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

        super.onAuthenticationSuccess(request, response, authentication);

    }

}
