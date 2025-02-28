package com.sh.cookie.service;

import com.sh.cookie.db.UserRepository;
import com.sh.cookie.model.UserLoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void login(
            UserLoginRequest userLoginRequest,
            HttpServletResponse response
    ){
        var id = userLoginRequest.getId();
        var password = userLoginRequest.getPassword();


        //유니크 아이디여서 getbyname으로?
        var optionalUser = userRepository.findByName(id);

        if(optionalUser.isPresent()){
            var userDto = optionalUser.get();
            if(userDto.getPassword().equals(password)){
                //cookie에 해당 정보를 저장
                var cookie = new Cookie("authorization-cookie",userDto.getId());
                cookie.setDomain("localhost"); //해당 도메인에서만 쿠키 사용 가능 ex)naver.com daum.net
                cookie.setPath("/");
                cookie.setMaxAge(-1); //session과 동일, 연결되어있는 동안만
                cookie.setHttpOnly(true); //웹에서 javascript 값으로 읽을 수 없음
                //cookie.setSecure(true); //https에서만 사용되도록 설정

                response.addCookie(cookie);

            }
        }else{
            throw new RuntimeException("User not Found");
        }
    }
}
