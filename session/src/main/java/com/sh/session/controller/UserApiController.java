package com.sh.session.controller;


import com.sh.session.model.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @GetMapping("/me")
    public UserDto me(
            HttpSession httpSession
    ){
        //로그인 성공한 유저만 세션을 가지고 있음
        var userObject = httpSession.getAttribute("USER");

        //세션이 만료되거나 없어졌을때를 검사
        if(userObject != null){
            var userDto = (UserDto) userObject;
            return userDto;
        }else{
            return null;
        }
    }

}
