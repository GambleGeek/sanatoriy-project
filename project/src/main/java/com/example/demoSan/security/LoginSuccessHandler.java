package com.example.demoSan.security;

import com.example.demoSan.security.auth.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        User userDetails = (User) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        if (userDetails.role().equalsIgnoreCase("CLIENT")) {
            redirectURL = "client/";
        } else if (userDetails.role().equalsIgnoreCase("ASSISTANT")) {
            redirectURL = "assistant/";
        }else if (userDetails.role().equalsIgnoreCase("MANAGER")) {
            redirectURL = "manager/";
        }else if (userDetails.role().equalsIgnoreCase("ASSISTANT")) {
            redirectURL = "director/";
        }

        response.sendRedirect(redirectURL);

    }

}