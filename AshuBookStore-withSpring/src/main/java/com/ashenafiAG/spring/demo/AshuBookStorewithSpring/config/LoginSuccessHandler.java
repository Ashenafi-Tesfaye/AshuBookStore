package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.enums.RoleType;

@Configuration 
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	  @Override
	  protected void handle(HttpServletRequest request, HttpServletResponse
	  response, Authentication authentication) throws IOException, ServletException
	  { 
		  String targetUrl = determineTargetUrl(authentication);
		  
	  if(response.isCommitted()) {
		  return;
	 	  }
	  
	  RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	  redirectStrategy.sendRedirect(request, response, targetUrl);
 }
	 
	
	protected String determineTargetUrl(Authentication authentication) {
		String url = "/login?error=true";
		
		// fetch the roles from Authentication object
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		
		for(GrantedAuthority auth:authorities) {
			roles.add(auth.getAuthority());
		}
		
		//check user role and decide the redirect URL
		
		if(roles.contains(RoleType.ADMIN.toString())) {
			//url = "loginSuccessful?role=admin";
     		url = "/admin";
		} else if (roles.contains(RoleType.CUSTOMER.toString())){
			//url = "/loginSuccessful?role=customer";
			url = "/customer";
		}
		
		return url;
	}


}
