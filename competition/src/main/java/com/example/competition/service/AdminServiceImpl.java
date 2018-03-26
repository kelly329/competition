package com.example.competition.service;

import com.example.competition.NotFoundException;
import com.example.competition.dao.AdminRepository;
import com.example.competition.po.Admin;
import com.example.competition.util.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public Admin checkUser(String username, String password) {
        Admin admin = adminRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        if(admin.getId()!=null){
            admin.setLastime(new Date());
        }
        return adminRepository.save(admin);
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminByName(String username) {
        return adminRepository. findByUsername(username);
    }

    @Override
    public Page<Admin> listAdmin(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @Override
    public Admin getAdmin(Long id) {
        return adminRepository.findOne(id);
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) {
        Admin a = adminRepository.findOne(id);
        if(a==null){
            throw new NotFoundException("该管理员不存在");
        }
        //获取type查询到的内容 type复制到t
        BeanUtils.copyProperties(admin,a);
        return adminRepository.save(a);
    }
}
