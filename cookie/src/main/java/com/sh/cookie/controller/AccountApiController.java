package com.sh.cookie.controller;

import com.sh.cookie.model.UserLoginRequest;
import com.sh.cookie.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountApiController {

    private final UserService userService;

    @PostMapping("/login")
    public void login(
            @RequestBody UserLoginRequest loginRequest,
            HttpServletResponse response
    ){
        userService.login(loginRequest, response);
    }
}
