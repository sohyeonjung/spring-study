package com.sh.jwt;

import com.sh.jwt.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootTest
class JwtApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
	}

	@Test
	void tokenCreate(){
		var claims = new HashMap<String, Object>();
		claims.put("user_id", 923);

		var expiredAt = LocalDateTime.now().plusSeconds(20); //10분 동안 유효

		var jwtToken = jwtService.create(claims, expiredAt);
		System.out.println(jwtToken);
	}

	@Test
	void tokenValidation(){
		var token = "emJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo5MjMsImV4cCI6MTc0MDcxMTk4Mn0.rAi_Ku3EdZAonHMvyKv-tvVEcYoeatSB8nwk2VUctJI";
		jwtService.validation(token);
	}

}
