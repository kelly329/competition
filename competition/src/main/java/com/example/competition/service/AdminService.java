package com.example.competition.service;

import com.example.competition.po.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    Admin checkUser(String username, String password);

    Admin saveAdmin(Admin admin);

    Admin getAdminByName(String username);

    //分页获取数据
    Page<Admin> listAdmin(Pageable pageable);

    Admin getAdmin(Long id);
    //修改
    Admin updateAdmin(Long id, Admin admin);

}
