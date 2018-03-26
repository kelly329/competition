package com.example.competition.service;

import com.example.competition.NotFoundException;
import com.example.competition.dao.UserRepository;
import com.example.competition.po.Type;
import com.example.competition.po.User;
import com.example.competition.util.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password){
        User user = userRepository.findByUserNameAndPassword(username, MD5Utils.code(password));
        //在登录时存储登录时间
        if(user.getId()!=null){

            user.setLastTime(new Date());
        }
        return userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        //在存入数据前把密码md5加密
//        String newpassword = MD5Utils.code(user.getPassword());
//        user.setPassword(newpassword);
        //存储注册时间
        if(user.getId()==null){
            user.setCreateTime(new Date());
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public Page<User> listUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User updateBanUser(Long id, User user) {
        User u = userRepository.findOne(id);
        if(u == null){
            throw new NotFoundException("该用户不存在");
        }
        userRepository.updateBan(id);
        //获取type查询到的内容 type复制到t
        BeanUtils.copyProperties(user,u);
        return userRepository.save(u);
    }

    @Override
    public User updateActive(Long id, User user) {
        User u = userRepository.findOne(id);
        if(u == null){
            throw new NotFoundException("该用户不存在");
        }
        userRepository.updateDelStatus(id);
        //获取type查询到的内容 type复制到t
        BeanUtils.copyProperties(user,u);
        return userRepository.save(u);
    }
}
