package com.blackwell.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import sun.security.acl.PrincipalImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeIntegrationTest extends IntegrationTest {

    private static final int STATUS_REDIRECT = 302;

    @Test
    public void homeTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is(STATUS_REDIRECT))
                .andExpect(view().name("redirect:/book"));

    }

    @Test
    public void loginTest() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void getCurrentUserTest() throws Exception {
        final String username = "username";
        final String password = "pass";

        UserDetails user = User.withUsername(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .roles("ROLES_ADMIN")
                .build();
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);
        mockMvc.perform(get("/getCurrentUser"))
                .andExpect(status().is(STATUS_REDIRECT))
                .andExpect(view().name("redirect:/users/" + username));
    }

}
