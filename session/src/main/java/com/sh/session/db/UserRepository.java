package com.sh.session.db;

import com.sh.session.model.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//db로 사용
@Service
public class UserRepository {

    private List<UserDto> userList = new ArrayList<UserDto>();

    public Optional<UserDto> findByName(String name) {
        return userList.stream()
                .filter(it->{
                    return it.getName().equals(name);
                }).findFirst();
    }

    @PostConstruct //해당 빈이 초기화됐을때 호출되는 메소드 설정
    public void init(){
        userList.add(
                new UserDto("홍길동", "1111")
        );
        userList.add(
                new UserDto("고길동", "1111")
        );
        userList.add(
                new UserDto("둘리", "1111")
        );
    }

}
