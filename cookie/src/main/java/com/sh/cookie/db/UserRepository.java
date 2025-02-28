package com.sh.cookie.db;

import com.sh.cookie.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserRepository {

    private List<UserDto> userList = new ArrayList<>();

    public Optional<UserDto> findById(String id){
        return userList.stream()
                .filter(it->{
                    return it.getId().equals(id);
                }).findFirst();
    }

    public Optional<UserDto> findByName(String name){
        return userList.stream()
                .filter(it->{
                    return it.getName().equals(name);
                }).findFirst();
    }


    //초기화 함수
    public void init(){
        userList.add(
                new UserDto(
                        //unique한 random id 자동 생성
                        UUID.randomUUID().toString(),
                        "홍길동",
                        "1234"
                )
        );
        userList.add(
                new UserDto(
                        UUID.randomUUID().toString(),
                        "고길동",
                        "1234"
                )
        );
        userList.add(
                new UserDto(
                        UUID.randomUUID().toString(),
                        "둘리",
                        "1234"
                )
        );

    }

}
