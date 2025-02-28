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

    public String login(
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
                //로그인 성공시 사용자의 id return
                return userDto.getId();
            }
        }else{
            throw new RuntimeException("User not Found");
        }

        return null;
    }
}
