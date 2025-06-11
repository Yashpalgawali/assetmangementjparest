package com.example.demo.security;

import java.time.Instant;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTAuthenticationResource {
	private JwtEncoder jwtEncoder;

	public JWTAuthenticationResource(JwtEncoder jwtEncoder) {
		super();
		this.jwtEncoder = jwtEncoder;
	}
private Logger logger = LoggerFactory.getLogger(getClass());

	//This is for Angular
	@PostMapping("/authenticate")
	public 	JwtResponse authenticate(Authentication auth) {
		return new JwtResponse(createToken(auth));
	}


	private String createToken(Authentication auth) {
		
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

		logger.info("User is {} , ID is {} and the name is {} and authorites are {} ",user,user.getUserId(),user.getUsername(),user.getAuthorities());
//		System.err.println("Inside create Token "+user.toString());
		
		var claims = JwtClaimsSet.builder().issuer("self")
								.issuedAt(Instant.now())
								.expiresAt(Instant.now().plusSeconds(60*30))
								.subject(auth.getName()).claim("scope", createScope(auth))
								.claim("userId", user.getUserId()) // 👈 Custom claim: adds user_id to the payload of the token.
								   
								.build() 
								
								;
								
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}


	private String createScope(Authentication auth) {
		// TODO Auto-generated method stub
		String res = auth.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(" "));
		return res;
		
	}
}
record JwtResponse(String token) {}
