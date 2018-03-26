package com.example.competition.dao;

import com.example.competition.po.Admin;
import com.example.competition.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
      Admin findByUsernameAndPassword(String username, String password);

      Admin findByUsername(String username);
}
