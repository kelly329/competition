package com.example.competition.service;

import com.example.competition.po.Type;
import com.example.competition.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    User checkUser(String username, String password);

    User saveUser(User user);

    User getUserByName(String username);

    //分页获取数据
    Page<User> listUser(Pageable pageable);

    User getUser(Long id);

    User updateBanUser(Long id,User user);

    User updateActive(Long id,User user);
}
