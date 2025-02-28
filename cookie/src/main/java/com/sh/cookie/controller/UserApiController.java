package com.sh.cookie.controller;

import com.sh.cookie.db.UserRepository;
import com.sh.cookie.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserDto me(
            HttpServletRequest request,
            //required=false <- 쿠키가 없는 경우 에러, name 만들어줬던 쿠키 이름
            @CookieValue(name = "authorization-cookie", required = false)
            String authorizationCookie
    ){
        //쿠키를 찾아내는 방법
        var cookies = request.getCookies();

        if(cookies != null){
            for(Cookie cookie:cookies){
                log.info("key : {}, value : {}", cookie.getName(), cookie.getValue());
            }
        }

        //cookie를 바로 받는 방법
        System.out.println("[2]");
        log.info("cookies: {}", authorizationCookie);

        var optionalUserDto = userRepository.findById(authorizationCookie);
        if(optionalUserDto.isPresent()){
            return optionalUserDto.get();
        }

        return null;
    }
}
