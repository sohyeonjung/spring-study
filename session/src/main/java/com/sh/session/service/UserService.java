package com.sh.session.service;

import com.sh.session.db.UserRepository;
import com.sh.session.model.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //login logic
    public void login(
        LoginRequest loginRequest,
        HttpSession httpSession
    ){
        var id = loginRequest.getId();
        var password = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id);

        if(optionalUser.isPresent()) {
            var userDto = optionalUser.get();

            if(userDto.getPassword().equals(password)) {
                //세션에 정보저장
                httpSession.setAttribute("USER", userDto);
            }else{
                throw new RuntimeException("Passwords do not match");
            }

        }else{
            throw new RuntimeException("User Not Found");
        }

    }

}
