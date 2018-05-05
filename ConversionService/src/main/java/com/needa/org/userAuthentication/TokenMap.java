package com.needa.org.userAuthentication;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.jodah.expiringmap.ExpiringMap;

@Component
public class TokenMap {

	
	private static int expirationTime = 300000;

	private TokenMap() {

	}

	@Value("${session.timeout}")
	public void setExpirationTime(int expirationTime) {
	TokenMap.expirationTime = expirationTime;
	System.out.println("expTime ::"+ TokenMap.expirationTime);
	}

	private static Map<String, String> tokenMap = ExpiringMap.builder()
	.expiration(expirationTime, TimeUnit.SECONDS).build();

	public static Map<String, String> getTokenMap() {
	return tokenMap;
	}

	public static void setTokenMap(Map<String, String> tokenMap) {
	TokenMap.tokenMap = tokenMap;
	}

}
