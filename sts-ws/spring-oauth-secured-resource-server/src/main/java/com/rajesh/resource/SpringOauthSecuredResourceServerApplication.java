package com.rajesh.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@SpringBootApplication
public class SpringOauthSecuredResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOauthSecuredResourceServerApplication.class, args);
	}
	
	/*@Bean
	public RemoteTokenServices tokenService() {
		RemoteTokenServices tokenServices=new RemoteTokenServices();
		tokenServices.setCheckTokenEndpointUrl("http://localhost:8081/auth/oauth/check_token");
		tokenServices.setClientId("microclient");
		tokenServices.setClientSecret("secret");
		return tokenServices;
	}*/

}
