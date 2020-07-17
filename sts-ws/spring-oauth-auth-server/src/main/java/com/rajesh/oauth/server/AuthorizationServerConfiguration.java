package com.rajesh.oauth.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients.inMemory()
			.withClient("rajeshclientid")
			.authorizedGrantTypes("password", "authorization_code")
			.secret(encoder().encode("secret"))
			.scopes("user_info")
			.redirectUris("http://localhost:8443/myapp/login/oauth2/code/sampleapp")
			.autoApprove(false)
			
			.and()
			
			.withClient("microclient")
			.authorizedGrantTypes("password", "authorization_code","client_credentials")
			.secret(encoder().encode("secret"))
			.scopes("user_info")
			.autoApprove(false)
			;
		
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.authenticationManager(authenticationManager);
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception{
		oauthServer.checkTokenAccess("isAuthenticated()");
	}
	
	@Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
